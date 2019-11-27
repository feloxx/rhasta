package org.cdp.rhasta2.ds.SkipList.SkipList1;

import java.util.*;

/******************************************************************************
 * SkipListNode                                                                *
 *                                                                             *
 * View README file for information about this project.                        *
 * View LICENSE file for license information.                                  *
 ******************************************************************************/
public class SkipListNode<E> {
    private E value;
    public List<SkipListNode<E>> nextNodes;

    public E getValue() {
        return value;
    }

    public SkipListNode(E value) {
        this.value = value;
        nextNodes = new ArrayList<SkipListNode<E> >();
    }

    public int level() {
        return nextNodes.size()-1;
    }

    public String toString() {
        return "SLN: " + value;
    }


    String a = "";
}
