import java.util.ArrayList;

/**
 * Node class
 *
 * @param <E> Type of element stored in a list
 * @author Isaac J. Nuno
 * @version 2.0, 06 Nov 2021
 * Added children ArrayList
 */


public class Node<E> {
    E data;
    Node<E> next;
    Node<E> prev;
    ArrayList<Node<E>> children;

    /**
     * Default Constructor
     */
    Node()
    {
        data=null;
        next=null;
        prev=null;
        children = new ArrayList<>();
    }
    /**
     * Constructor for node with no next or prev field
     * @param dataItem the data/element to be stored
     */
    Node(E dataItem)
    {
        data = dataItem;
        next=null;
        prev=null;
        children = new ArrayList<>();
    }

    /**
     * Creates a node with next field
     * @param dataItem element to be stored
     * @param nodeRef the node current node will point to
     */
    Node(E dataItem, Node<E> nodeRef)
    {
        data = dataItem;
        next = nodeRef;
    }

    /**
     * Creates a node with next and prev fields
     * @param dataItem element to be stored
     * @param next  next node
     * @param prev  previous node
     */
    Node(E dataItem, Node<E> next, Node<E> prev)
    {
        data = dataItem;
        this.next=next;
        this.prev=prev;
    }


    /**
     * Stores data in node
     * @param newData data to be stored
     */
    public void setData(E newData)
    {
        data = newData;
    }


    /**
     * Sets next pointer
     * @param next the next element
     */
    public void setNext(Node<E> next)
    {
        this.next=next;
    }


    /**
     * Sets reference to previous node
     * @param prev previous node
     */
    public void setPrev(Node<E> prev)
    {
        this.prev=prev;
    }

    /**
     * Retrieves data
     * @return the desired data
     */
    public E getData(){return data;}

    /**
     * Retrieves previous node
     * @return
     */
    public Node<E> getPrev(){return prev;}

    /**
     * Retrieves next element
     * @return
     */
    public Node<E> getNext(){return next;}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((data == null) ? 0 : data.hashCode());
        return result;
    }

    /**
     * Returns a string of data stored in node
     * @return
     */
    public String toString()
    {
        String temp=""+data;
        return temp;
    }
}
