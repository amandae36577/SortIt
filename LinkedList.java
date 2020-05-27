//Programming Assignment 3
//Amanda Esposito
import java.io.*;//insertion of the needed java librarys

//class that holds the commands for the linked lists
public class LinkedList
{
    // instance variables
    private int length;
    private Node head;
    private Node last; 
    
    public LinkedList() //costructor
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
    /*public static void printListND(LinkedList list) 
     //attempted a no duplicates function, first of a few attempts
    { 
        Node currNode = list.head; 
        Node nextNode = currNode.next;
        // Traverse through the LinkedList 
        while (currNode != null && nextNode != null) { 
            // print the data if not a duplicate
            if(currNode.data != nextNode.data)
                System.out.print(currNode.data + " "); 
            // next node 
            currNode = currNode.next;
            nextNode = nextNode.next;
        } 
        System.out.print(currNode.data + " ");
        System.out.println();
    } */
    public static String getFirst(LinkedList list)
    {   Node front = list.head;
        if(list.head != null) //return the user the first value in the list
            return front.data;
        else
            return "null";
    }
    public int length()
    {
        return length; //return the list length
    }
    public String getLast(LinkedList list)
    {
        return last.data;
    }
    public static void reverse(LinkedList list)
    {
        Node pointer = list.head;
        Node previous = null, current = null;
    
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
    /*public void removeDup()  // attempt at removing duplicates
    { 
        //reference to head
        Node curr = head; 
  
        //Traverse list till the last node 
        while (curr != null) { 
             Node temp = curr; 
            //compare and delete until a match
            while(temp!=null && temp.data==curr.data) { 
                temp = temp.next; 
            } 
            
            curr.next = temp; 
            curr = curr.next; 
        } 
    } */
}