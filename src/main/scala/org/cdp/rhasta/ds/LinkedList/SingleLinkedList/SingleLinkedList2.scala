package org.cdp.rhasta.ds.LinkedList.SingleLinkedList


/**
  * scala 函数式单向链表实现
  * @tparam A
  */
sealed trait SingleLinkedList2[+A]

case object Nil extends SingleLinkedList2[Nothing]

case class Cons[+A](head: A, tail: SingleLinkedList2[A]) extends SingleLinkedList2[A]

object SingleLinkedList2 {
  def apply[A](as: A*): SingleLinkedList2[A] = {
    if (as.isEmpty) {
      Nil
    } else {
      Cons(as.head, apply(as.tail: _*))
    }
  }

  def append[A](a1: SingleLinkedList2[A], a2: SingleLinkedList2[A]): SingleLinkedList2[A] = a1 match {
    case Nil => a2
    case Cons(head, tail) => Cons(head, append(tail, a2))
  }
}
