//Amanda Esposito
//class holds the commands for the linked lists

public class LinkedList
{
    // variables
    private int length;
    private Node head;
    private Node last;

    /**
     * @post length = 0
     * @post head = null
     * @post last = null
     * constructor
     */
    public LinkedList()
    {
        length = 0;
        head = null;
        last = null;
    }
    // Linked list Node
    static class Node
    {
        String data;
        Node next;
        Node prev;

        Node(String str)
        {
            data = str;
            next = null;
        }
    }

    /**
     * @param list of values
     * @param element is the value to add to the list
     * @post length = #length + 1
     * @return list with new value included
     * Adds a value to a list
     */
    public LinkedList enqueue(LinkedList list, String element) //add element
    {
        Node item = new Node(element);
        item.next = null;
        if(list.head == null) //if head null then add here
        {
            list.head = item;
            list.last = list.head;
        }
        else //else find  end of list and then add the element
        {
            while(last.next != null)
            {
                last = last.next;
            }
            last.next = item;
            last = last.next;
        }
        length ++;
        return list;
    }
    /**
     * @param list of values
     * @param index is which value will be deleted
     * @pre length > 0
     * @post length = #length - 1
     * @return list without value at the index
     * Takes the values at index out of the list
     */
    public LinkedList dequeue(LinkedList list, int index) //remove item at index
    {
        Node currentNode = list.head, prevLoc = null;
        //remove head
        if(currentNode != null && index == 0)
        {
            list.head = currentNode.next;
            length = length - 1;
            return list;
        }
        int counter = 0;
        //removing a non-head
        while(currentNode != null)
        {
            if(counter == index)
            {
                prevLoc.next = currentNode.next;
                break;
            }
            else
            {
                prevLoc = currentNode;
                currentNode = currentNode.next;
                counter++;
            }
        }
        length = length -1;
        return list;
    }
    /**
     * @param list of values
     * @pre length > 0
     * @post output of the list to the command line
     * prints out the list to the command line
     */
    public static void printList(LinkedList list)
    {
        Node currNode = list.head;
        // start from begining/head and traverse the rest of the list
        while (currNode != null) {
            // Print the data at current node
            System.out.println(currNode.data + " ");
            // Then go to the next node
            currNode = currNode.next;
        }
        System.out.println();
    }

    /**
     * @param list of values
     * @pre length > 0
     * @return the first value in the list
     */
    public static String getFirst(LinkedList list)
    {   Node front = list.head;
        if(list.head != null) //return the user the first value in the list
            return front.data;
        else
            return "null";
    }
    /**
     * @return the length of the list
     */
    public int length()
    {
        return length; //return the list length
    }
    /**
     * @param list of values
     * @pre length > 0
     * @return the last value in the list
     */
    public String getLast(LinkedList list)
    {
        return last.data;
    }
    /**
     * @param list of values
     * @pre length > 0
     * @post list = reverse(#list)
     */
    public static void reverse(LinkedList list)
    {
        Node pointer = list.head;
        Node previous = null, current;

        while (pointer != null) //keep going through list while not null
        {
            current = pointer;
            pointer = pointer.next;
            // reverse
            current.next = previous;
            previous = current;
            list.head = current;
        }
    }
}