package org.cdp.rhasta.algorithm.sort

class QuickSort1 {
  def quickSort(list: List[Int]): List[Int] = list match {
    case Nil => Nil
    case List() => List()
    case head :: tail =>
      val (left, right) = tail.partition(_ < head)
      quickSort(left) ::: head :: quickSort(right)
  }

  def distinctQuickSort(list: List[Int]): List[Int] = list match {
    case Nil => Nil
    case List() => List()
    case head :: tail =>
      val (left, right) = tail.partition(_ < head)
      quickSort(left) ::: head :: quickSort(right)
  }

  def sort(xs: List[Int]): List[Int] = xs match {
    case Nil => Nil
    case head :: tail =>
      val (less, notLess) = tail.partition(_ < head) // Arbitrarily partition list in two
      sort(less) ++ (head :: sort(notLess))          // Sort each half
  }

  def qsort[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = xs match {
    case List() => xs
    case x :: xs1 => {
      val (left, right): (List[T], List[T]) = xs1.partition(ord.lt(_, x))
      qsort(left)(ord) ::: List(x) ::: qsort(right)(ord)
    }
  }
}

object QuickSort1 {
  val self = new QuickSort1

  def main(args: Array[String]): Unit = {
    val a = List(2,4,1,10,9,13,2)

    val b = self.quickSort(a)
    val c = self.distinctQuickSort(a)
    val d = self.qsort(a.toList)

    println(b)
    println(c)
    println(d)
  }
}
