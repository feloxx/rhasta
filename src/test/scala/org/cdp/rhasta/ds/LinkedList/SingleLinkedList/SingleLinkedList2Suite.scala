package org.cdp.rhasta.ds.LinkedList.SingleLinkedList

import org.scalatest.FunSuite

class SingleLinkedList2Suite extends FunSuite {
  test("SingleLinked") {
    SingleLinkedList2(1, 2, 3)
      .equals(Cons(1, Cons(2, Cons(3, Nil))))
  }
}
