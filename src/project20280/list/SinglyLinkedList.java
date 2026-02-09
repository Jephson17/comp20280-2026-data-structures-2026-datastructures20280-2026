package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {

        private final E element;            // reference to the element stored at this node

        /**
         * A reference to the subsequent node in the list
         */
        private Node<E> next;         // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e the element to be stored
         * @param n reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n) {
            // TODO
            element = e;
            next = n;
        }

        // Accessor methods

        /**
         * Returns the element stored at the node.
         *
         * @return the element stored at the node
         */
        public E getElement() {
            return element;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         *
         * @return the following node
         */
        public Node<E> getNext() {
            // TODO
            if (next == null) {
                return null;
            }
            else {
                return next;
            }

        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param n the node that should follow this one
         */
        public void setNext(Node<E> n) {
            // TODO
            next = n;
        }
    } //----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null;               // head node of the list (or null if empty)


    /**
     * Number of nodes in the list
     */
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() {
    }              // constructs an initially empty list

    //@Override
    public int size() {
        // TODO
        return size;
    }

    //@Override
    public boolean isEmpty() {
        // TODO
        if (head == null) {
            return true;
        }
        return false;
    }

    @Override
    public E get(int position) {
        // TODO
        Node<E> current = head;
        int count = 0;

        while (current != null) {

            if (count == position) {
                return current.getElement();
            }

            current = current.getNext();
            count++;
        }

        return null;
    }

    @Override
    public void add(int position, E e) {
        // TODO
        Node<E> current = head; // starts at the head
        // current is a pointer that only know how to point to Nodes
        Node<E> nodeToStoreItem =  new Node<>(e, null);
        for (int i = 0; i <= position; i++) {
            current = current.getNext();

            if (i == position) {
                nodeToStoreItem.setNext(current); // setNext is the function that
                // tells a Node to point to the next Node

            }

        }

    }


    @Override
    public void addFirst(E e) {
        // TODO
        Node<E> firstNode = new Node<>(e, null);
        firstNode.setNext(head);
        head = firstNode;
        size++;
    }

    @Override
    public void addLast(E e) {
        // TODO
        if (head == null) {
            Node<E> firstNode = new Node<E> (e, null);
            head = firstNode;
            size++;

        }

        else{
            Node<E> currentNode = head;

            while(currentNode.getNext() != null) {

                currentNode = currentNode.getNext();
                // The current node keeps going until get next is null,
                // which means I arrived at the last Node, so current is
                // at the last Node, THEN I can update the ex last node
                // to be the second last
            }

            Node<E> lastNode = new  Node<>(e, null);
            currentNode.setNext(lastNode);

            size++;

        }

    }

    @Override
    public E remove(int position) {
        // TODO
        Node<E>  current = head;
        E removedElement = null;

        for  (int i = 0; i < position - 1; i++) {
            current = current.getNext();
            removedElement = current.getElement();
        }

        current.setNext(current.getNext().getNext());

        size--;

        return removedElement;
    }

    @Override
    public E removeFirst() {
        // TODO
        E removedFirstElement = null;
        removedFirstElement = head.getElement();
        head = head.getNext();
        return removedFirstElement;
    }

    @Override
    public E removeLast() {
        // TODO

        Node<E>  current = head;
        E removedLastElement = null;

        for (int  i = 0; i <= size - 2; i++) {

            current = current.getNext();
        }
        removedLastElement = current.getNext().getElement(); // THAT is the last
        // item in the last Node

        current.setNext(null); // the second last element

        size--;
        return removedLastElement;
    }

    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        //LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        //ll.removeLast();
        //ll.removeFirst();
        //System.out.println("I accept your apology");
        //ll.add(3, 2);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);

    }
}
