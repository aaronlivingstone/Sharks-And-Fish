/* DList.java */

/**
 *  A DList is a mutable doubly-linked list.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 */

public class DList {

  /**
   *  head references the sentinel node.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

    protected DListNode head;
    protected long size;
    protected int fixCounter = 0;

  /* DList2 invariants:
   *  1)  head != null.
   *  2)  For any DListNode2 x in a DList2, x.next != null.
   *  3)  For any DListNode2 x in a DList2, x.prev != null.
   *  4)  For any DListNode2 x in a DList2, if x.next == y, then y.prev == x.
   *  5)  For any DListNode2 x in a DList2, if x.prev == y, then y.next == x.
   *  6)  size is the number of DListNode2s, NOT COUNTING the sentinel
   *      (denoted by "head"), that can be accessed from the sentinel by
   *      a sequence of "next" references.
   */

  /**
   *  DList2() constructor for an empty DList2.
   */
  public DList() {
    head = new DListNode();
    head.item = Integer.MIN_VALUE;
    head.next = head;
    head.prev = head;
    size = 0;
  }

  /**
   *  DList2() constructor for a one-node DList2.
   */
    public DList(int a, int b, int c) {
        head = new DListNode();
        head.item = Integer.MIN_VALUE;
        head.next = new DListNode();
        head.next.item = a;
        head.prev = head.next;
        head.next.prev = head;
        head.prev.next = head;
        size = 1;
    }

  /**
   *  DList2() constructor for a two-node DList2.
   */
  public DList(int a, int b) {
    head = new DListNode();
    head.item = Integer.MIN_VALUE;
    head.next = new DListNode();
    head.next.item = a;
    head.prev = new DListNode();
    head.prev.item = b;
    head.next.prev = head;
    head.next.next = head.prev;
    head.prev.next = head;
    head.prev.prev = head.next;
    size = 2;
  }

  /**
   *  insertFront() inserts an item at the front of a DList2.
   */
    public void insertFront(int i) {
    // Your solution here.
      DListNode current = new DListNode();
      current.item = i;
      current.next = head.next;
      head.next.prev = current;
      head.next = current;
      current.prev = head;
      size++;
  }

 /**
   *  insertFront() inserts an item at the front of a DList2.
   */
    public void insertFront(int i, int j, int k) {
    // Your solution here.
      DListNode current = new DListNode();
      current.item = i;
      current.sTime = j;
      current.numElements = k;
      current.next = head.next;
      head.next.prev = current;
      head.next = current;
      current.prev = head;
      size++;
  }

  /**
   *  removeFront() removes the first item (and first non-sentinel node) from
   *  a DList2.  If the list is empty, do nothing.
   */
  public void removeFront() {
    // Your solution here.
      head.next = head.next.next;
      head.next.prev = head;
      if (size > 0) {
          size--;
      }
  }

 /**
   *  insertFront() inserts an item at the front of a DList2.
   */
    public void insertBack(int i, int j, int k) {
    // Your solution here.
      DListNode current = new DListNode();
      current.item = i;
      current.sTime = j;
      current.numElements = k;
      current.prev = head.prev;
      current.next = head;
      head.prev.next = current;
      head.prev = current;
      size++;
  }

    public void removeBack() {
        if (head.prev != head) {
            head.prev = head.prev.prev;
            head.prev.next = head;
            size--;
        }
    }
    
    public int getSize() {
        return (int)size;
    }

    public int getItem(int i) {
        assert i <= size : "Bad link indexing";
        DListNode current = head;
        while (i > 0){
            current = current.next;
            i--;
        }
        return current.item;
            
    }

    public int getSTime(int i) {
        assert i <= size : "Bad link indexing";
        DListNode current = head;
        while (i > 0){
            current = current.next;
            i--;
        }
        return current.sTime;
    }

    public int getNumElements(int i) {
        assert i <= size : "Bad link indexing";
        DListNode current = head;
        while (i > 0){
            current = current.next;
            i--;
        }
        return current.numElements;
    }

 /**
   *  squish() takes this list and, wherever two or more consecutive items are
   *  equals(), it removes duplicate nodes so that only one consecutive copy
   *  remains.  Hence, no two consecutive items in this list are equals() upon
   *  completion of the procedure.
   *
   *  After squish() executes, the list may well be shorter than when squish()
   *  began.  No extra items are added to make up for those removed.
   *
   *  For example, if the input list is [ 0 0 0 0 1 1 0 0 0 3 3 3 1 1 0 ], the
   *  output list is [ 0 1 0 3 1 0 ].
   *
   *  IMPORTANT:  Be sure you use the equals() method, and not the "=="
   *  operator, to compare items.
   **/

  public void squish() {
    // Fill in your solution here.  (Ours is eleven lines long.)
      DListNode currentNode = head;
      DListNode currentNode2 = head.next;
      int aItem, bItem, aStarveTime, bStarveTime;
      while (currentNode.next != head) {         
          aItem = currentNode.item;
          bItem = currentNode.next.item;
          aStarveTime = currentNode.sTime;
          bStarveTime = currentNode.next.sTime;
          if ((aItem == bItem) && (aStarveTime == bStarveTime)) {
              currentNode.numElements += currentNode.next.numElements;
              currentNode.next = currentNode.next.next;
              size--;
          } else {
              currentNode = currentNode.next;
          }
      }
  }

    public void insertSpecial(int target, int species) {
        fixCounter++;
        int currTotal = 0;
        int preTotal = 0;
        int elem = 0;
        DListNode current = head;
        DListNode current2 = head.next;
        DListNode tempFront = new DListNode();
        DListNode tempBack = new DListNode();
        while (currTotal < target) {
           
            current = current.next;
            preTotal = currTotal;
            currTotal += current.numElements;
        }
       
        if (current.item == 0) { 

            if (target > (preTotal + 1)) {
                size++;
                current.prev.next = tempFront;
                tempFront.prev = current.prev;
                tempFront.next = current;
                tempFront.item = 0;
                current.prev = tempFront;
                tempFront.numElements = target - preTotal - 1;
            }
            
            if (currTotal > target) {
                size++;
                current.next.prev = tempBack;
                tempBack.next = current.next;
                tempBack.prev = current;
                current.next = tempBack;
                tempBack.numElements = currTotal - target;               
            }
            current.item = species; 
            current.numElements = 1;          
        }
    }

  /**
   *  toString() returns a String representation of this DList.
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of this DList.
   */
  public String toString() {
    String result = "[  ";
    DListNode current = head.next;
    while (current != head) {
      result = result + current.item + ":" + current.numElements + "  ";
      current = current.next;
    }
    return result + "]";
  }

  public static void main(String[] args) {
    // DO NOT CHANGE THE FOLLOWING CODE.

    DList l = new DList();
    System.out.println("### TESTING insertFront ###\nEmpty list is " + l);

    l.insertFront(9);
    System.out.println("\nInserting 9 at front.\nList with 9 is " + l);
    if (l.head.next.item != 9) {
      System.out.println("head.next.item is wrong.");
    }
    if (l.head.next.prev != l.head) {
      System.out.println("head.next.prev is wrong.");
    }
    if (l.head.prev.item != 9) {
      System.out.println("head.prev.item is wrong.");
    }
    if (l.head.prev.next != l.head) {
      System.out.println("head.prev.next is wrong.");
    }
    if (l.size != 1) {
      System.out.println("size is wrong.");
    }

    l.insertFront(8, 0, 0);
    System.out.println("\nInserting 8 at front.\nList with 8 and 9 is " + l);
    if (l.head.next.item != 8) {
      System.out.println("head.next.item is wrong.");
    }
    if (l.head.next.prev != l.head) {
      System.out.println("head.next.prev is wrong.");
    }
    if (l.head.prev.item != 9) {
      System.out.println("head.prev.item is wrong.");
    }
    if (l.head.prev.next != l.head) {
      System.out.println("head.prev.next is wrong.");
    }
    if (l.head.next.next != l.head.prev) {
      System.out.println("l.head.next.next != l.head.prev.");
    }
    if (l.head.prev.prev != l.head.next) {
      System.out.println("l.head.prev.prev != l.head.next.");
    }
    if (l.size != 2) {
      System.out.println("size is wrong.");
    }



    l = new DList(1, 2);
    System.out.println("\n\n### TESTING removeFront ###\nList with 1 and 2 is "
                       + l);

    l.removeFront();
    System.out.println("\nList with 2 is " + l);
    if (l.head.next.item != 2) {
      System.out.println("head.next.item is wrong.");
    }
    if (l.head.next.prev != l.head) {
      System.out.println("head.next.prev is wrong.");
    }
    if (l.head.prev.item != 2) {
      System.out.println("head.prev.item is wrong.");
    }
    if (l.head.prev.next != l.head) {
      System.out.println("head.prev.next is wrong.");
    }
    if (l.size != 1) {
      System.out.println("size is wrong.");
    }

    l.removeFront();
    System.out.println("\nEmpty list is " + l);
    if (l.head.next != l.head) {
      System.out.println("head.next is wrong.");
    }
    if (l.head.prev != l.head) {
      System.out.println("head.prev is wrong.");
    }
    if (l.size != 0) {
      System.out.println("size is wrong.");
    }

    l.removeFront();
    System.out.println("\nEmpty list is " + l);
    if (l.head.next != l.head) {
      System.out.println("head.next is wrong.");
    }
    if (l.head.prev != l.head) {
      System.out.println("head.prev is wrong.");
    }
    if (l.size != 0) {
      System.out.println("size is wrong.");
    }

    l.insertBack(99, 1, 1);
    System.out.println("inserting back: " + l);
    l.insertBack(100, 1, 1);
    System.out.println("inserting back: " + l);
    l.insertBack(101, 1, 1);
    System.out.println("inserting back: " + l);
    System.out.println("head.prev: " + l.head.prev.item);
    System.out.println("head.next: " + l.head.next.item);

    System.out.println("head.next.item: " + l.getItem(1));
    System.out.println("head.next.next.item: " + l.getItem(2));
    System.out.println("head.next.next.numElements: " + l.getNumElements(2));
    
  }

}
