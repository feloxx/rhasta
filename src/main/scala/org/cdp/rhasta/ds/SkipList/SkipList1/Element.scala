package org.cdp.rhasta.ds.SkipList.SkipList1

object Element {
  def apply(next: Option[Element] = None, prev: Option[Element] = None,
            up: Option[Element] = None, down: Option[Element] = None, kV: (Int, Any)) = new Element(next = next, prev = prev,
    up = up, down = down, kV = kV)
}

// Element structure in the skip list
class Element(var next: Option[Element] = None,
              var prev: Option[Element] = None,
              var up: Option[Element] = None,
              var down: Option[Element] = None,
              val kV: (Int, Any)) {
  def setNext(el: Option[Element]): Unit = next = el

  def setPrev(el: Option[Element]): Unit = prev = el

  def setUp(el: Option[Element]): Unit = up = el

  def setDown(el: Option[Element]): Unit = down = el

  def key(): Int = kV._1

  def value(): Any = kV._2
}