package org.cdp.rhasta.ds.SkipList

import org.scalatest.FunSuite

class SkipList1Suite extends FunSuite {
  test("skipList1") {
    val skippy = SkipList1(List(1, 5, 36, 100, 2382, 9999, 123123123))
    skippy.insert((10000, 10000))

    println(skippy.get(11111))
  }
}
