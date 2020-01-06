package org.cdp.rhasta.algorithm.sort

class QuickSort1 {
  def quickSort(list: List[Int]): List[Int] = list match {
    case Nil => Nil
    case List() => List()
    case head :: tail =>
      val (left, right) = tail.partition(_ < head)
      quickSort(left) ::: head :: quickSort(right)
  }
}

object QuickSort1 {
  val self = new QuickSort1

  def main(args: Array[String]): Unit = {
    val a = List(2,4,1,10,9,13)

    val b = self.quickSort(a)

    println(b)
  }
}
