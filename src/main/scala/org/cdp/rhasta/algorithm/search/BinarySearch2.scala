package org.cdp.rhasta.algorithm.search

import scala.annotation.tailrec
import scala.math

class BinarySearch2 {
  def binarSearch(arr: Array[Int], find: Int): Int = {
    @tailrec
    def loop(low: Int, high: Int): Int = (low + high) / 2 match {
      case _ if high < low => -1
      case mid if arr(mid) > find => loop(low, mid - 1)
      case mid if arr(mid) < find => loop(mid + 1, high)
      case mid => mid
    }
    loop(0, arr.length - 1)
  }
}

object BinarySearch2 {
  val self = new BinarySearch2

  def main(args: Array[String]): Unit = {
    val a = Array(1,9,100,200,1828,1999,10000)

    val b = self.binarSearch(a, 1999)

    println(b)

    println(math.pow(2,3))
  }
}
