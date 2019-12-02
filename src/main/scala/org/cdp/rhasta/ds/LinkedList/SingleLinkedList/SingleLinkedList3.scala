package org.cdp.rhasta.ds.LinkedList.SingleLinkedList

/**
  * 第三种单链表的实现
  */

class SingleLinkedList3 {

  case class Data(var obj: Any, var next: Data)

  private var first = Data(null,null)

  def insertFirst(obj: Any): Unit = {
    first = Data(obj, first)
  }

  @throws[Exception]
  def deleteFirst(): Any = {
    if (first == null) {
      throw new Exception("empty!")
    }
    val temp = first
    first = first.next
    temp.obj
  }

  @throws[Exception]
  def find(obj: Any): Any = {
    if (first == null) {
      throw new Exception("LinkedList is empty!")
    }
    var cur = first
    while (cur != null) {
      if (cur.obj == obj) return cur.obj
      cur = cur.next
    }
    null
  }

  @throws[Exception]
  def remove(obj: Any): Unit = {
    first match {
      case null => throw new Exception("LinkedList is empty!")
      case f if f.obj == obj => first = first.next
      case _ =>
        var pre: Data = first
        var cur: Data = first.next
        while (cur != Data(null,null)) {
          if (cur.obj == obj) {
            pre.next = cur.next
          }
          pre = cur
          cur = cur.next
        }
    }
    //if (first == null) {
    //  throw new Exception("LinkedList is empty!")
    //}
    //if (first.obj == obj) {
    //  first = first.next
    //} else {
    //  var pre: Data = first
    //  var cur: Data = first.next
    //  while (cur != null) {
    //    if (cur.obj == obj) pre.next = cur.next
    //    pre = cur
    //    cur = cur.next
    //  }
    //}
  }

  def isEmpty: Boolean = first == null

  def display(): Unit = {
    if (first == null) {
      println("empty")
    }
    var cur: Data = first
    while (cur != Data(null,null)) {
      print(cur.obj.toString + " -> ")
      cur = cur.next
    }
    println
  }
}

object SingleLinkedList3 {
  @throws[Exception]
  def main(args: Array[String]): Unit = {
    println("实例化链表")
    val ll = new SingleLinkedList3

    println("插入数据4")
    ll.insertFirst(4)
    println("插入数据3")
    ll.insertFirst(3)
    println("插入数据2")
    ll.insertFirst(2)
    println("插入数据1")
    ll.insertFirst(1)

    println("display数据")
    ll.display()

    println("删除第一条")
    ll.deleteFirst()

    println("display数据")
    ll.display()

    println("删除下标3的数据")
    ll.remove(3)

    println("display数据")
    ll.display()

    println("查找下标1的数据")
    println(ll.find(1))
    println("查找下标4的数据")
    println(ll.find(4))
  }
}

