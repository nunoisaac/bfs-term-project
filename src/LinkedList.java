import java.util.*;
import java.util.Map;

/**
 *A class of linked Nodes
 *
 * @param <E> Type of element stored in a list
 * @author Isaac J. Nuno
 * @version 2.0, 21 Oct 2020 modified 5 Nov 2020
 */

public class LinkedList<E> implements Iterable<E>{
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    /**
     * Adds element to end of list by default
     * If list is empty, adds as first element
     *
     * @param data data to be stored
     */
    public void add(E data)
    {
        if(this.head == null) {
            this.head = new Node<E>(data);              //add new node to front of list
            this.tail = this.head;                        //update tail
        }
        else {
            this.tail.next = new Node<E>(data);
            this.tail.next.prev = this.tail;              //set previous node of last node
            this.tail = tail.next;                        //Node added to end of list is the new tail of the list
        }
        size++;                                         //update size of list
    }

    /**
     * Adds element at specified index
     *
     * @param     index index of desired location
     * @param     data data to be stored in specified location
     * @exception IndexOutOfBoundsException
     *            if index is not in range 0 to size-1
     */
    public void add(int index, E data)
    {
        if(index < 0 || index >= size)                   //verify index is valid
        {
            throw new
                    IndexOutOfBoundsException(Integer.toString(index));
        }
        else {
            if(index == 0 || this.head == null)             //for index 0 or if head is null, add element to front of list
                addFirst(data);
            else {
                Node<E> temp = this.head;
                while (index - 1 > 0) {
                    temp = temp.next;                   //Search correct location for insertion of new node
                    index--;
                }
                temp.next = new Node<E>(data, temp.next, temp); //set new element in desired location, readjust pointers (element being added, next, prev)
                temp.next.next.prev = temp.next;          //set pointer of element to right of new node to point to new node
                size++;                                 //update size of list
            }
        }
    }

    /**
     * Adds element to front of list
     *
     * @param data data to be stored
     */
    public void addFirst(E data)
    {

        if(this.head == null) {
            this.head = new Node<E>(data);              //head points to newly added node
            this.tail = this.head;                      //update tail of LinkedList
        }
        else {
            this.head = new Node<E>(data, this.head);   //head points to newly added node
            this.head.next.prev = this.head;              //set next node .prev to point to newly added
        }
        size++;                                         //update size of list
    }


    /**
     * Adds element to end of list
     * if list is empty, adds as first element
     *
     * @param data data to be stored
     */
    public void addLast(E data)
    {
        if(this.head == null)
            this.head = new Node<E>(data);             //adds as first element
        else{
            this.tail.next = new Node<E>(data);        //end of list points to new element
            this.tail.next.prev = tail;                  //set previous of newly added node
            this.tail = tail.next;                       //Node added to end of list is the new tail of the list
        }
        size++;                                        //increment size of list
    }


    /**
     * Retrieves element at specified index
     *
     * @param     index index of desired element
     * @return    the desired element
     * @exception IndexOutOfBoundsException
     *            if index is not in range 0 to size-1
     */
    public E get(int index)
    {
        if(index < 0 || index >= size)                 //verify index is valid
        {
            throw new
                    IndexOutOfBoundsException(Integer.toString(index));
        }
        else {
            Node<E> temp = this.head;
            while (index > 0) {
                temp = temp.next;                     //Search correct location for desired element
                index--;
            }
            if (temp != null)
                return temp.data;                    //return element
        }
        return null;                                 //return null if not found
    }



    /**
     * Removes first element of the list
     *
     * @return the removed element
     *
     */
    public E remove()
    {
        Node<E> temp = this.head;                     //store current head for return statement
        if(size == 1)
        {
            head = null;

        }
        else if(this.head != null)
        {
            head = head.next;                         //set next node as first node
            head.prev = null;                         //set first node.prev to null
        }
        if(temp != null)
        {
            size--;                                 //update size of list
            return temp.data;                       //return removed element
        }
        return null;                                //return null if list is empty and no element was removed
    }

    /**
     * Removes element from desired location
     *
     * @param index index of element to be removed
     * @exception IndexOutOfBoundsException
     *            if index is not in range 0 to size-1
     */
    public void remove(int index)
    {
        if(index < 0 || index >= size)                //verify index is valid
        {
            throw new
                    IndexOutOfBoundsException(Integer.toString(index));
        }
        else {
            if(index == 0)                            //call remove() if index is 0, removing first element
                remove();
            else if(index == size - 1)
                removeLast();                         //call removeLast(), removing last element
            else {
                Node<E> temp = this.head;
                while (index - 1 > 0) {
                    temp = temp.next;                 //search for desired index
                    index--;
                }
                temp.next = temp.next.next;           //remove element at specified location
                temp.next.prev = temp;                //adjust pointers
                size--;                               //update size
            }
        }
    }


    /**
     * Removes element from end of list
     *
     * @return the removed element
     */
    public E removeLast() {
        if(size == 1)
        {
            Node<E> temp = this.tail;
            this.head = null;                            //If only one element is left, head=tail, set head to null
            this.size = 0;                               //List is empty
            return temp.data;
        }
        else if (tail != null) {
            Node<E> newTail = this.tail.prev;             //store tail.prev as newTail
            Node<E> temp = this.tail;                     //stores tail for return statement
            this.tail.prev.next = null;                   //remove last element
            this.tail = newTail;                          //sets new tail of LinkedList
            size--;                                       //update size of list
            return temp.data;                             //return removed element
        }
        return null;
    }


    /**
     * Replaces element at desired location
     *
     * @param     index  index of element to be set
     * @param     data   data to be stored at desired location
     * @exception IndexOutOfBoundsException
     *            if index is not in range 0 to size-1
     */
    public void set(int index, E data)
    {

        if(index < 0 || index >= size)               //verify index is valid
        {
            throw new
                    IndexOutOfBoundsException(Integer.toString(index));
        }
        else {
                Node<E> temp = this.head;
                while (index > 0) {
                    temp = temp.next;                 //search for desired location
                    index--;
                }
                temp.setData(data);                   //replace element
            }
    }


    /**
     * Returns first element from list
     * Does not remove element
     *
     * @return first element from list
     */
    public E peek()
    {
        if(this.head != null)
            return this.head.data;                   //return first element
        return null;                                 //return null if list is empty
    }


    /**
     * Removes first element from list
     *
     * @return the removed element
     */
    public E poll()
    {
        Node<E> temp = this.head;
        if(size == 1)
        {
            head = null;
        }
        else if(this.head != null)
        {
            head = head.next;                        //set next element as head
            head.prev = null;                        //adjust pointer of head node
        }
        if(temp != null)
        {
            size--;                                  //update size of list
            return temp.data;                        //returns removed element
        }
        return null;                                 //return null if list is empty
    }


    /**
     * Adds to end of list
     *
     * @param data the data to be stored
     * @return true if successful
     */
    public boolean offer(E data)
    {
        if(this.head == null) {
            this.head = new Node<E>(data);           //adds as first element if list is empty
        }
        else{
            this.tail.next = new Node<E>(data);
            this.tail.next.prev = tail;                //set previous node of last node
            this.tail = tail.next;                     //Node added to end of list is the new tail of the list
        }
        size++;                                        //update size of list
        return true;                                   //return true if successful
    }

    /**
     * Retrieves first element of list
     * @return the first element
     */
    public E getFirst(){return head.data;}

    /**
     * Retrieves element at end of list
     * @return the last element
     */
    public E getLast(){return tail.data;}

    /**
     * Retrieves but does not remove first element of list
     * @return the first element
     */
    public E element(){return head.data;}

    /**
     * Retrieves size of the list
     * @return the size
     */
    public int size()
    {
        return size;
    }

    /**
     * Retrieves index of specified element
     *
     * @param data data element to be searched
     * @return the index of desired data element
     */
    public int indexOf(E data)
    {
        int index = 0;
        Node<E> temp = this.head;
        while(temp != null){
            if(temp.data.equals(data))                  //travers list until node with matching element is found
                return index;                           //return index of element
            index++;
            temp = temp.getNext();
        }
        return -1;                                      //return -1 if element not found
    }

    /**
     * Checks if element is in list
     *
     * @param o object to be searched
     * @return  returns true if element is found
     */
    public boolean contains(Object o)
    {
        Node<E> temp = this.head;
        while(temp != null)
        {
            if(o.equals(temp.data))                         //search list until object found or end of list is reached
                return true;                                //return true if object is found
            temp = temp.next;
        }
        return false;                                       //return false if object not found
    }

    /**
     * Clears list of all elements
     *
     */
    public void clear()
    {
        this.head = null;
        this.size = 0;                                        //List is empty
    }

    /**
     * helper method to reverseNodes
     */
    public void reverseOrder()
    {
        if(this.head != null)
        reverseNodes(this.head);
    }

    /**
     * Recursive function to reverse order of all elements
     *
     * @param temp node to be reversed
     * @return  reversed node
     */
    public Node<E> reverseNodes(Node<E> temp)
    {
        if(temp.next == null) {
            this.head = temp;                                   //sets element at end of list as new head node
            this.head.prev = null;                              //head.prev points to null
            return head;                                        //return head element
        }
        temp.prev = reverseNodes(temp.next);                    //current node points to switched node
        temp.prev.next = temp;                                  //previous node points to current node
        temp.next = null;                                       //current node could be end of list, point to null
        return temp;
    }

    /**
     * Checks if list is empty
     * @return true if list is empty otherwise false
     */
    public boolean isEmpty()
    {
        return size == 0;
    }

    /**
     * Traverses the LinkList
     *
     * @return Iterator object
     */
    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }
    class LinkedListIterator implements Iterator<E> {

        Node<E> temp = null;

        @Override
        public boolean hasNext() {
            if (temp == null && head != null)                  //Checks if first element in list is null
                return true;                                   //Return true if the first element in list is not null
            else if (temp != null)                             //Checks if next element is null
                return temp.getNext() != null;                 //Returns true if next element is not null
            return false;
        }

        @Override
        public E next() {

            if (temp == null && head != null) {
                temp = head;
                return head.getData();                          //Returns first element in list
            } else if (temp != null) {
                temp = temp.getNext();                          //Point to next element
                return temp.getData();                          //Return data
            }
            throw new NoSuchElementException();
        }
    }

    /**
     * Creates a string of all elements in list
     *
     * @return string of elements
     */
    public String toString() {
        Node<E> temp = this.head;
        StringBuilder string = new StringBuilder();
        string.append("[");
        while (temp != null) {
            if (temp.next == null)
                string.append(temp.data);
            else
                string.append(temp.data).append(", ");
            temp=temp.next;
        }
        string.append("]");
        return string.toString();
    }
    public void removeDups(){
        if (this.head != null) {
            Set<E> seen = new HashSet<>();
            Node<E> temp = this.head;
            helpRemove(temp, seen);
        }
    }
    private void helpRemove(Node<E> temp, Set<E> seen) {
        if (temp == null)
            return;
        if (!seen.contains(temp.getData())){
            seen.add(temp.data);
        }
        else{
            if(temp.next != null) {
                temp.next.prev = temp.prev;
                temp.prev.next = temp.next;
            }
            else{
                temp.prev.next = null;
            }
        }
        helpRemove(temp.next, seen);
    }
}
