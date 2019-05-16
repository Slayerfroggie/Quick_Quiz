/**
 * **************************************************************************
 */
/*                                                                           */
/*                    Doubly-Linked List Manipulation                        */
/*                                                                           */
/*                     January 1998, Toshimi Minoura                         */
/*                                                                           */
/**
 * **************************************************************************
 */
// Filename: Doubly-LinkedList_ToshimiMinoura
// Source:   TBA
package quincy.squickquizbynetwork;

// A Node is a node in a doubly-linked list.
class LinkedListNode
{              // class for nodes in a doubly-linked list

    LinkedListNode prev;              // previous Node in a doubly-linked list
    LinkedListNode next;              // next LinkedListNode in a doubly-linked list
    
    String questionTopic;
    int questionQN;
    int questionFailures;
    
    //public char data;       // data stored in this Node

    LinkedListNode()
    {                // constructor for head Node 
        prev = this;           // of an empty doubly-linked list
        next = this;
        questionTopic = "QN Topic";
        questionQN = 0;
        questionFailures = 0;
//    myWords.Word1 = "Yellow";
//    myWords.Word2 = "No";
        // data = 'H';           // not used except for printing data in list head
    }

    LinkedListNode(String w1, int w2, int w3)
    {       // constructor for a Node with data
        prev = null;
        next = null;
        
        questionTopic = w1;
        questionQN = w2;
        questionFailures = w3;
        //this.data = data;     // set argument data to instance variable data
    }

    public void append(LinkedListNode newNode)
    {  // attach newNode after this Node
        newNode.prev = this;
        newNode.next = next;
        if (next != null)
        {
            next.prev = newNode;
        }
        next = newNode;
        //System.out.println("Node with data " + newNode.questionTopic
               // + " appended after Node with data " + questionTopic);
    }

    public void insert(LinkedListNode newNode)
    {  // attach newNode before this Node
        newNode.prev = prev;
        newNode.next = this;
        prev.next = newNode;;
        prev = newNode;
        //.out.println("Node with data " + newNode.questionTopic
                //+ " inserted before Node with data " + questionTopic);
    }

    public void remove()
    {              // remove this Node
        next.prev = prev;                 // bypass this Node
        prev.next = next;
        //System.out.println("Node with data " + questionTopic + " removed");
    }
    public String toString(){
        return this.questionTopic + " - " + this.questionQN;
    }
}

class DList
{

    LinkedListNode head;

    public DList(String s1, int s2, int s3)
    {
        head = new LinkedListNode(s1, s2, s3);
    }

    public LinkedListNode find(String wrd1)
    {          // find LinkedListNode containing x
        for (LinkedListNode current = head.next; current != head; current = current.next)
        {
            if (current.questionTopic.compareToIgnoreCase(wrd1) == 0)
            {        // is x contained in current Node?
                //System.out.println("Data " + wrd1 + " found");
                return current;               // return Node containing x
            }
        }
        //System.out.println("Data " + wrd1 + " not found");
        return null;
    }

    //This Get method Added by Matt C
    public LinkedListNode get(int i)
    {
        LinkedListNode current = this.head;
        if (i < 0 || current == null)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        while (i > 0)
        {
            i--;
            current = current.next;
            if (current == null)
            {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        return current;
    }

    public String toString()
    {
        String str = "";
        if (head.next == head)
        {             // list is empty, only header Node
            return "List Empty";
        }
        str = "list content = ";
        for (LinkedListNode current = head.next; current != head && current != null; current = current.next)
        {
            str = str + current.questionTopic;
        }
        return str;
    }

    public void print()
    {                  // print content of list
        if (head.next == head)
        {             // list is empty, only header Node
            //System.out.println("list empty");
            return;
        }
        //System.out.print("list content = ");
        for (LinkedListNode current = head.next; current != head.prev; current = current.next)
        {
            //System.out.print(" " + current.questionQN + " - " + current.questionTopic);
        }
        //System.out.println("");
    }

//  public static void printList(String[] args)
//  {
//    DList dList = new DList("", 0, 0);              // create an empty dList
//    dList.print();
//
//    dList.head.append(new LinkedListNode("1", 2, 3));       // add Node with data '1'
//    dList.print();
//    dList.head.append(new LinkedListNode("3", 4, 5));       // add Node with data '2'
//    dList.print();
//    dList.head.append(new LinkedListNode("5", 6, 7));       // add Node with data '3'
//    dList.print();
//    dList.head.insert(new LinkedListNode("A", 8, 9));       // add Node with data 'A'
//    dList.print();
//    dList.head.insert(new LinkedListNode("C", 10, 11));       // add Node with data 'B'
//    dList.print();
//    dList.head.insert(new LinkedListNode("E", 12, 13));       // add Node with data 'C'
//    dList.print();
//
//    LinkedListNode nodeA = dList.find("A");           // find Node containing 'A'
//    nodeA.remove();                         // remove that Node
//    dList.print();
//
//    LinkedListNode node2 = dList.find("3");           // find Node containing '2'
//    node2.remove();                           // remove that Node
//    dList.print();
//
//    LinkedListNode nodeB = dList.find("5");            // find Node containing 'B'
//    nodeB.append(new LinkedListNode("Linked", 1, 2));   // add Node with data X
//    dList.print();
//  }
}
