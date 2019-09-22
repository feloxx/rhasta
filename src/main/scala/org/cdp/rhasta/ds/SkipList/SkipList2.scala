package org.cdp.rhasta.ds.SkipList

import scala.util.Random

class SkipList2[K <% Ordered[K], V] {

  // points to the "top-left" node
  var head = new ListNode(null, null, null)
  val random = new Random

  class KVPair(val key: K, val value: V)

  /**
    * Represents any node inside the skip list.
    *
    * INVARIANTS:
    * (1) dummy nodes starting each list have null kvpairs
    * (2) all down pointers all non-null, except for ListNode objects which
    * make up the bottom-most list
    * (3) all next pointers all non-null except for those at the end of each list
    */
  class ListNode(val kvpair: KVPair, var next: ListNode, var down: ListNode)

  def oneListToString(node: ListNode): String =
    if (null == node) "END\n"
    else if (null == node.kvpair) "START -> " + oneListToString(node.next)
    else "(" + node.kvpair.key + "," + node.kvpair.value + ") -> " + oneListToString(node.next)

  def toStringHelper(node: ListNode): String =
    if (null == node) ""
    else oneListToString(node) + toStringHelper(node.down)

  override def toString: String = toStringHelper(this.head).trim

  def insertHelper(key: K): List[ListNode] = {
    var end = false
    var curNode: ListNode = head
    var nodes: List[ListNode] = Nil
    while (!end) {
      while (curNode.next != null && curNode.next.kvpair.key.<(key)) {
        curNode = curNode.next
      }

      nodes = curNode :: nodes
      if (curNode.down == null) end = true
      else curNode = curNode.down
    }
    nodes
  }

  /**
    * @param key   -- the key to insert
    * @param value -- the value associated with a key
    */
  def insert(key: K, value: V): Unit = {
    var lst: List[ListNode] = insertHelper(key)

    var done = false
    var lnode: ListNode = null
    while (!done) {
      //println(done+" "+key)
      if (lst.isEmpty) {
        lnode = new ListNode(new KVPair(key, value), null, lnode)
        if (!done) {
          var l: ListNode = new ListNode(head.kvpair, lnode, head)
          head = l
          done = true
        }
      }
      else {
        lnode = new ListNode(new KVPair(key, value), lst.head.next, lnode)
        lst.head.next = lnode
        if (!done) lst = lst.tail
      }
      done = done || (random.nextInt(2) == 0)
    }
    return
  }

  /**
    * Find all nodes which precede those to be deleted,
    * and make pointers skip the deleted nodes.
    *
    * @param key - the key to be deleted
    */
  def delete(key: K): Unit = {
    var lst: List[ListNode] = insertHelper(key)
    lst.foreach(e =>

      if (e.next != null && e.next.kvpair.key == key) {

        e.next = e.next.next
      }
    )

    while (head.next == null) {
      head = head.down
    }
  }


  /**
    * @param key -- a key to look up
    * @return the value associated with the key
    */
  def lookup(key: K): Option[V] = {
    var found: Boolean = false
    var curNode: ListNode = head
    var pNode: ListNode = curNode;

    while (!found) {
      //horizontal search

      while (curNode.next != null && curNode.next.kvpair.key.<=(key)) { //if this is not the end of the list

        if (curNode.next.kvpair.key.==(key)) {
          return Option(curNode.next.kvpair.value) //return the option
        }
        pNode = curNode
        curNode = curNode.next //crawl forward
      }
      if (curNode.next == null) { //end of list
        //return None;
        pNode = curNode
        if (curNode.down != null) {
          curNode = curNode.down
          pNode = curNode
        }
        else return None
      }
      else if (curNode.next != null && curNode.next.kvpair.key.>(key)) {
        //return None
        if (pNode.down != null) {
          curNode = pNode.down
          pNode = curNode
        }
        else return None
      }

    }
    None
  }

}

object SkipList2 extends App {

  /**
    * skip list test cases
    */
  override def main(args: Array[String]) {
    var a: SkipList2[Int, String] = new SkipList2[Int, String]
    a.insert(1, "first")
    a.insert(2, "second")
    a.insert(3, "third")
    a.insert(4, "fourth")
    a.insert(-35, "low")
    a.insert(100, " high")
    a.insert(4, "again")
    println(a)

    println(a.lookup(-35))
    println(a.lookup(3))
    println(a.lookup(4))
    println(a.lookup(5))
    println(a.lookup(67))
    println(a.lookup(100))


  }

}