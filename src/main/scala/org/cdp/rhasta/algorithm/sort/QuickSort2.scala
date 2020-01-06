package org.cdp.rhasta.algorithm.sort

class QuickSort2 {
  def distinctQuickSort(arr: Array[Int]): Array[Int] = {
    if (arr.isEmpty) {
      arr
    } else {
      distinctQuickSort(arr.filter(_ < arr.head)) ++
      Array(arr.head) ++
      distinctQuickSort(arr.filter(_ > arr.head))
    }
  }

  def quickSort(arr: Array[Int]): Array[Int] = {
    if (arr.isEmpty) {
      arr
    } else {
      distinctQuickSort(arr.filter(_ < arr.head)) ++
        arr.filter(_ == arr.head) ++
        distinctQuickSort(arr.filter(_ > arr.head))
    }
  }
}

object QuickSort2 {
  val self = new QuickSort2

  def main(args: Array[String]): Unit = {
    val a = Array(1,4,3,7,19,33,2,21,1,1,2,3)

    val b = self.distinctQuickSort(a)
    val c = self.quickSort(a)

    println(b.mkString(","))
    println(c.mkString(","))


    val aa = List(("a",1),("a",3),("b",3),("b",5),("c",4))

    val result = aa.groupBy(_._1).map(x => (x._1, x._2.map(_._2).sum / x._2.length.toDouble))
    println(result)
  }
}
