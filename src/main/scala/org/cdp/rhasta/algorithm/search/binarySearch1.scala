package org.cdp.rhasta.algorithm.search

class BinarySearch1 {
  def binarySearch(arr: Array[Int], find: Int): Int = {
    var low: Int = 0
    var high: Int = arr.length - 1
    while (low <= high) {
      (low + high) / 2 match {
        case mid if find > arr(mid) => low = mid + 1
        case mid if find < arr(mid) => high = mid - 1
        case mid => return mid
      }
    }
   -1
  }
}

object BinarySearch1 {
  val self = new BinarySearch1

  def main(args: Array[String]): Unit = {
    val a = Array(1,9,100,200,1828,1999,10000)

    val b = self.binarySearch(a, 1999)

    println(b)
  }
}
