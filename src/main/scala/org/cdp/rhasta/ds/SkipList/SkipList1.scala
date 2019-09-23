package org.cdp.rhasta.ds.SkipList

import scala.collection.mutable.ListBuffer

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

object Element {
  def apply(next: Option[Element] = None,
            prev: Option[Element] = None,
            up: Option[Element] = None,
            down: Option[Element] = None,
            kV: (Int, Any)): Element = {
    new Element(next, prev, up, down, kV)
  }
}

class SkipList1(val inputList: List[Int]) {
  // Store the reference to the upper left-most element here
  val startNode: Option[Element] = Some(generateSkipList(inputList))


  // Use a stack structure to save the descent references
  def insert(kV: (Int, Any)): Unit = {
    var levelStack: ListBuffer[Option[Element]] = searchAndMark(kV._1)
    var groundElement: Option[Element] = Some(Element(kV = kV))

    // Insert ground level
    val neighbour: Option[Element] = groundElement.get.next
    connectLeftRight(levelStack(0), groundElement)
    connectLeftRight(groundElement, neighbour)

    levelStack = levelStack.tail


  }

  // Same as get but leaves markers for insertion
  private def searchAndMark(key: Int): ListBuffer[Option[Element]] = {
    var traversal: Option[Element] = startNode
    val levelStack: ListBuffer[Option[Element]] = ListBuffer()

    while (traversal.get.down.isDefined) {
      levelStack.prepend(traversal)
      traversal = traversal.get.down
      while (key >= traversal.get.next.get.key) {
        traversal = traversal.get.next
      }
    }
    levelStack.prepend(traversal)
    levelStack
  }

  def get(key: Int): Any = {
    var traversal: Option[Element] = startNode
    while (traversal.get.down.isDefined) {
      traversal = traversal.get.down
      while (key >= traversal.get.next.get.key) {
        traversal = traversal.get.next
      }
    }
    traversal.get.value
  }


  def remove(key: Int): Unit = ???

  private def connectLeftRight(el1: Option[Element], el2: Option[Element]): Unit = {
    (el1, el2) match {
      case (Some(e1), None) =>
        e1.setNext(None)
      case (None, Some(e2)) =>
        e2.setPrev(None)
      case (a@Some(e1), b@Some(e2)) =>
        println("HORIZONTALLY: Linking " + e1.kV._1 + " and " + e2.kV._1)
        e1.setNext(b)
        e2.setPrev(a)
    }
  }

  private def connectDownUp(el1: Option[Element], el2: Option[Element]): Unit = {
    (el1, el2) match {
      case (Some(e1), None) =>
        e1.setUp(None)
      case (None, Some(e2)) =>
        e2.setDown(None)
      case (a@Some(e1), b@Some(e2)) =>
        println("VERTICALLY: Linking " + e1.kV._1 + " and " + e2.kV._1)
        e1.setUp(b)
        e2.setDown(a)
    }
  }

  // Redundant. Just use the insert function several times
  private def generateSkipList(sortedList: List[Int]): Element = {
    // Pointer to top left most element
    var leftTopStart: Option[Element] = None

    val initialList = (Int.MinValue :: sortedList) :+ Int.MaxValue
    // Create Initial doubly linked list
    var el: Option[Element] = None
    initialList.foreach { x =>
      val newElement = Some(Element(kV = (x, x)))
      connectLeftRight(el, newElement)
      el = newElement
      if (x == Int.MinValue) leftTopStart = el
    }

    el = leftTopStart
    var lastCreated: Option[Element] = None
    val subsetList: ListBuffer[Int] = initialList.to[ListBuffer]
    val newSubsetList: ListBuffer[Int] = ListBuffer()

    while (subsetList.size > 2) {
      println(subsetList.toString())
      subsetList.foreach({ x =>
        if (scala.util.Random.nextFloat > 0.5 || x == Int.MinValue || x == Int.MaxValue) {
          newSubsetList.append(x)
          val newElement = Some(Element(kV = (x, x)))
          connectDownUp(el, newElement)
          connectLeftRight(lastCreated, newElement)
          lastCreated = newElement

          // Set new starting element
          if (x == Int.MinValue) leftTopStart = newElement
        }
        // Traverse right
        el = el.get.next
      })
      println("Refresh")
      subsetList.clear
      subsetList.appendAll(newSubsetList)
      newSubsetList.clear
      lastCreated = None

      // Set the traversal to the new leftmost top element
      el = leftTopStart
    }

    leftTopStart.get
  }

}

object SkipList1 {
  def apply(inputList: List[Int]) = new SkipList1(inputList)
}