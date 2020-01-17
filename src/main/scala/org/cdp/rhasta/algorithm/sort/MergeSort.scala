package org.cdp.rhasta.algorithm.sort

class MergeSort {
  def mergeSort1(arr: Array[Int], left: Int, right: Int, temp: Array[Int]): Unit = {
    if (left < right) {
      val mid = (left + right) / 2
      mergeSort1(arr, left, mid, temp)
      mergeSort1(arr, mid + 1, right, temp)
      merge(arr, left, mid, right, temp)
    }

    def merge(arr: Array[Int], left: Int, mid: Int, right: Int, temp: Array[Int]): Unit = {
      var i = left // 左边的下标
      var j = mid + 1 // 右边的下标
      var t = 0 // 临时数组的下标
      // 比较2个数组，按照顺序放入临时数组放
      while (i <= mid && j <= right) {
        if (arr(i) < arr(j)) { // 如果是当前的左边的有序列表的值小于当前的右边的有序列表的值，就把它拷贝到temp数组
          temp(t) = arr(i)
          t += 1
          i += 1
        } else { // 如果是当前的右边的有序列表的值小于当前的左边的有序列表的值，就把它拷贝到temp数组
          temp(t) = arr(j)
          t += 1
          j += 1
        }
      }

      // 比较完有可能某个数组已经放完了，另一个还有余下的，这里是余下的数据以此放入临时数组放
      // 右边的已经空了，左边的还有剩余数据
      while (i <= mid) {
        temp(t) = arr(i)
        t += 1
        i += 1
      }
      // 左边的已经空了，右边的还有剩余数据
      while (j <= right) {
        temp(t) = arr(j)
        t += 1
        j += 1
      }

      // 将本次临时数组拷贝回原数组
      t = 0
      var tempLeft = left
      while (tempLeft <= right) {
        arr(tempLeft) = temp(t)
        t += 1
        tempLeft += 1
      }
    }
  }

  def mergeSort3(list: List[Int]): List[Int] = {
    def merge(left: List[Int], right: List[Int]): List[Int] = (left, right) match {
      case (Nil, _) => right
      case (_, Nil) => left
      case (x :: xs, y :: ys) =>
        if (x <= y) x :: merge(xs, right)
        else y :: merge(left, ys)
    }

    if (list.length == 1) {
      list
    } else {
      val (l, r) = list.splitAt(list.length / 2)
      merge(mergeSort3(l), mergeSort3(r))
    }
  }
}

object MergeSort {
  val self = new MergeSort

  def main(args: Array[String]): Unit = {
    var arr = Array(101, 22, 199, 11, -1, 144, 23, 89, 34)
    val temp = new Array[Int](arr.length)

    //    self.mergeSort1(arr, 0, arr.length - 1, temp)
    //    println(arr.mkString(", "))

    //    val b = self.mergeSort3(a.toList)
    //    println(b)
  }
}
