package org.cdp.rhasta.ds.SkipList

import util.Random._

class SkipList3[T <% Ordered[T]] {

  // 随机化的概率,每层节点拥有上一层指针的概率
  private val kBranching = 4

  // 最高层级为12，则 N的合适范围在 2^^12
  private val kMaxHeight = 12

  // 头结点
  private val header: SkipNode[T] = new SkipNode[T](kMaxHeight)

  // 当前的最大层级编号，从0开始编号
  private var level = 0

  // 随机产生插入节点的层高
  def randomLevel: Int = {
    // 随机算法1
    //    val lvl = (Math.log(1.0 - Math.random()) / Math.log(1 - P)).toInt
    //    Math.min(lvl, kMaxHeight)

    //随机算法2
    var height = 1
    while (height < kMaxHeight && (nextInt(kBranching) == 0)) {
      height += 1
    }
    height
  }

  // 判断是否包含
  def contains(c: T): Boolean = {
    var x = header
    for (i <- level.to(0, -1)) {
      while (x.forward(i) != null && x.forward(i).value < c) {
        x = x.forward(i)
      }
    }
    x = x.forward(0)
    x != null && x.value.equals(c)
  }

  // 插入元素
  def insert(ins: T) {
    var x = header
    val update = new Array[SkipNode[T]](kMaxHeight + 1)
    for (i <- level.to(0, -1)) {
      while (x.forward(i) != null && x.forward(i).value < ins) {
        x = x.forward(i)
      }
      update(i) = x
    }
    x = x.forward(0)

    // 如果不存在，则创建节点
    if (x == null || x.value != ins) {
      val lvl = randomLevel
      if (lvl > level) {
        for (i <- level to lvl) {
          update(i) = header
        }
        level = lvl
      }
      x = new SkipNode[T](ins, lvl)
      for (i <- 0 to lvl) {
        x.forward(i) = update(i).forward(i)
        update(i).forward(i) = x
      }
    }

  }

  // 删除操作
  def delete(del: T) {
    var x = header
    val update = new Array[SkipNode[T]](kMaxHeight + 1)
    for (i <- level.to(0, -1)) {
      while (x.forward(i) != null && x.forward(i).value < del) {
        x = x.forward(i)
      }
      update(i) = x
    }
    x = x.forward(0)
    //元素存在的情况下才需要删除
    if (x != null && x.value == del) {
      for (i <- 0 to level) {
        if (update(i).forward(i) == x) {
          update(i).forward(i) = x.forward(i)
        }
      }
      while (level > 0 && header.forward(level) == null) {
        level = level - 1
      }
    }
  }
}


// 跳跃表中的结点
private class SkipNode[T](val level: Int) {
  var value: T = _

  // 指向多个层级的下个节点的指针数组
  val forward: Array[SkipNode[T]] = new Array[SkipNode[T]](level + 1)

  def this(ve: T, level: Int) = {
    this(level)
    value = ve
  }
}

/**
  *
  */

object SkipList3 {
  def main(args: Array[String]) {
    val sList = new SkipList3[Int]
    sList.insert(5)
    sList.insert(4)
    sList.insert(6)
    sList.insert(9)
    println(sList contains 6)
    sList delete 6
    println(sList contains 6)

    println(sList.header)


  }
}

