package org.cdp.rhasta.algorithm.sort

class BubbleSort {

  // 类java
  def bubbleSort1(arr: Array[Int]): Array[Int] = {
    for {
      i <- 0 until arr.length - 1
      j <- 0 until arr.length - i - 1
    } {
      if (arr(j) >= arr(j + 1)) {
        val temp = arr(j)
        arr(j) = arr(j + 1)
        arr(j + 1) = temp
      }
    }
    arr
  }

  // 纯scala
  import scala.annotation.tailrec
  def bubbleSort2(xt: List[Int]): List[Int] = {
    @tailrec
    def bubble(xs: List[Int],rest: List[Int], sorted: List[Int]): List[Int] = xs match {
      case x :: Nil =>
        if (rest.isEmpty) x :: sorted
        else bubble(rest, Nil, x :: sorted)
      case a :: b :: xs =>
        if (a > b) bubble(a :: xs, b :: rest, sorted)
        else bubble(b :: xs, a :: rest, sorted)
    }
    bubble(xt,Nil, Nil)
  }
}

object BubbleSort {
  val self = new BubbleSort

  def main(args: Array[String]): Unit = {
    val a = Array(5,3,19,2,1,10)

    val b = self.bubbleSort1(a)
    println(b.mkString(","))

    val c = self.bubbleSort2(a.toList)
    println(c)
  }
}
