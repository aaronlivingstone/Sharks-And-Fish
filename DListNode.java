/* DListNode.java */

/**
 *  A DListNode2 is a node in a DList2 (doubly-linked list).
 */

public class DListNode {

  /**
   *  item references the item stored in the current node.
   *  prev references the previous node in the DList.
   *  next references the next node in the DList.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

    public int item;
    public int sTime; 
    public int numElements;
    public DListNode prev;
    public DListNode next;

  /**
   *  DListNode2() constructor.
   */
  DListNode() {
    item = 0;
    sTime = 0;
    numElements = 1;
    prev = null;
    next = null;
  }

    DListNode(int i, int j, int k) {
    item = i;
    sTime = j;
    numElements = k;
    prev = null;
    next = null;
  }

}
