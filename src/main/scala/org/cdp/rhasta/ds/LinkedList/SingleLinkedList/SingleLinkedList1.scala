package org.cdp.rhasta.ds.LinkedList.SingleLinkedList

import scala.collection.mutable
import scala.util.control.Breaks._

/**
  * java式 scala单向链表实现
  */

class HeroNode(hNo: Int, hName: String, hNickName: String) {
  var no: Int = hNo
  var name: String = hName
  var nickName: String = hNickName
  var next: HeroNode = _
}

class SingleLinkedList1 {
  val head = new HeroNode(0, "", "")

  def insert(heroNode: HeroNode): Unit = {
    var temp = head

    breakable {
      while (true) {
        if (temp.next == null) {
          break()
        }
        temp = temp.next
      }
    }
    temp.next = heroNode
  }

  def orderInsert(heroNode: HeroNode): Unit = {
    var temp = head
    var flag = false

    breakable {
      while (true) {
        if (temp.next == null) {
          break()
        }
        if (temp.next.no > heroNode.no) { // 顺序
          break()
        } else if (temp.next.no == heroNode.no) {
          flag = true
          break()
        }
        temp = temp.next
      }
    }

    if (!flag) {
      heroNode.next = temp.next
      temp.next = heroNode
    } else {
      println(s"插入的编号已经存在 ${heroNode.no}")
    }
  }

  def update(heroNode: HeroNode): Unit = {
    if (head.next == null) {
      println("链表为空")
      return
    }

    var temp = head
    var flag = false

    breakable {
      while(true) {
        if (temp.next == null) {
          break()
        }
        if (temp.no == heroNode.no) {
          flag = true
          break()
        }
        temp = temp.next
      }
    }

    if (flag) {
      temp.name = heroNode.name
      temp.nickName = heroNode.nickName
    } else {
      println("没有找到可修改的")
    }
  }

  def delete(no: Int): Unit = {
    if (head.next == null) {
      println("链表为空")
      return
    }

    var temp = head
    var flag = false

    breakable {
      while(true) {
        if (temp.next == null) {
          break()
        }
        if (temp.next.no == no) {
          flag = true
          break()
        }
        temp = temp.next
      }
    }

    if (flag) {
      temp.next = temp.next.next
    } else {
      println("没有找到可删除的")
    }
  }

  def display(): Unit = {
    if (head.next == null) {
      return
    }

    var temp = head.next

    breakable {
      while (true) {
        if (temp == null) {
          break()
        }
        println(s"节点信息：no=${temp.no}, name=${temp.name}, nickName=${temp.nickName}")
        temp = temp.next
      }
    }
  }
}

object SingleLinkedList1 {
  def main(args: Array[String]): Unit = {
    val hero1 = new HeroNode(1, "宋江", "及时雨")
    val hero2 = new HeroNode(3, "吴用", "智多星")
    val hero3 = new HeroNode(4, "公孙胜", "入云龙")
    val hero4 = new HeroNode(2, "卢俊义", "玉麒麟")

    //    val ll = new SingleLinkedList1()
    //    ll.add(hero1)
    //    ll.add(hero2)
    //    ll.add(hero3)
    //
    //    ll.display()

    val ll = new SingleLinkedList1()
    ll.orderInsert(hero1)
    ll.orderInsert(hero2)
    ll.orderInsert(hero3)
    ll.orderInsert(hero4)

    ll.display()

    println("==========修改")
    val hero5 = new HeroNode(2,"陈大炮","牛逼")
    ll.update(hero5)
    ll.display()

    println("==========删除头")
    ll.delete(1)
    ll.display()

    println("==========删除尾")
    ll.delete(4)
    ll.delete(41)
    ll.display()
  }
}
