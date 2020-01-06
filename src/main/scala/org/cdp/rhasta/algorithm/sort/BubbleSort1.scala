package org.cdp.rhasta.algorithm.sort

class BubbleSort1 {
  def bubbleSort(arr: Array[Int]): Array[Int] = {
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
}

object BubbleSort1 {
  val self = new BubbleSort1

  def main(args: Array[String]): Unit = {
    val a = Array(5,3,19,2,1,10)

    val b = self.bubbleSort(a)

    println(b.mkString(","))
  }
}
