package org.cdp.rhasta.algorithm.sort

class InsertSort1 {
  def insertSort(arr: Array[Int]): Array[Int] = {
    for (i <- 1 until arr.length) {
      val ins = arr(i)
      var index = i - 1
      while (index >= 0 && ins < arr(index)) {
        arr(index + 1) = arr(index)
        index -= 1
      }
      arr(index + 1) = ins
    }
    arr
  }

  def aa(list: List[Int])(implicit ord: Ordering[Int]) = {
    def insert(list: List[Int], value: Int): List[Int] = list.span(x => ord.lt(x, value)) match {
      case (lower, upper) => lower ::: value :: upper
    }
    list.foldLeft(List.empty[Int])(insert)
  }
}

object InsertSort1 {
  val self = new InsertSort1

  def main(args: Array[String]): Unit = {
    val a = Array(99,2,100,1)
    val b = self.insertSort(a)
    val c = self.aa(a.toList)
    println(b.mkString(", "))
    println(c)
  }
}
