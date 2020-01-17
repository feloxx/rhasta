package org.cdp.rhasta.algorithm.sort

class SelectSort1 {
  def selectSort(arr: Array[Int]): Array[Int] = {
    for (i <- 0 until arr.length - 1) {
      var min = arr(i)
      var minIndex = i
      for (j <- (i + 1) until arr.length) {
        if (min > arr(j)) {
          min = arr(j)
          minIndex = j
        }
      }
      if (minIndex != i) {
        arr(minIndex) = arr(i)
        arr(i) = min
      }
    }
    arr
  }

}

object SelectSort1 {
  val self = new SelectSort1

  def main(args: Array[String]): Unit = {
    val a = Array(101,2,99,1)

    val b = self.selectSort(a)

    println(a.mkString(", "))
    println(b.mkString(", "))
  }
}
