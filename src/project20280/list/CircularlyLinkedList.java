package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T e, Node<T> n) {
            data = e;
            next = n;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node<T> n) {
            next = n;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        if (size == 0) return null;

        int pos = 0;
        Node<E> curNode = tail.getNext();
        while (pos < i % size) {
            curNode = curNode.getNext();
            pos++;
        }

        return curNode.getData();
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */
    @Override
    public void add(int i, E e) {
        if (i < 0 || i > size) throw new IndexOutOfBoundsException("Index " + i + " is out of bounds");

        if (size == 0) {
            tail = new Node<E>(e, null);
            tail.setNext(tail);
            size++;
            return;
        }

        Node<E> prevNode = tail;
        for (int pos = 0; pos < i; pos++) {
            prevNode = prevNode.getNext();
        }

        Node<E> newNode = new Node<E>(e, prevNode.next);
        prevNode.setNext(newNode);

        size++;
    }

    @Override
    public E remove(int i) {
        if (isEmpty() || i >= size) throw new IndexOutOfBoundsException("Index " + i + " is out of bounds");

        if (size == 1){
            E data = tail.getData();
            tail = null;
            size = 0;
            return data;
        }

        Node<E> prevNode = tail;
        for (int pos = 0; pos < i; pos++) {
            prevNode = prevNode.getNext();
        }

        Node<E> targetNode = prevNode.getNext();
        prevNode.next = prevNode.getNext().getNext();

        if (targetNode == tail) {
            tail = prevNode;
        }

        size--;
        return targetNode.getData();
    }

    public void rotate() {
        if (tail != null) tail = tail.getNext();
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        private Node<E> curr = (Node<E>) tail; // Start at tail so first next() gets head
        private int itemsRemaining = size;

        @Override
        public boolean hasNext() {
            return itemsRemaining > 0;
        }

        @Override
        public E next() {
            curr = curr.next; // Move to the actual node
            itemsRemaining--;
            return curr.data;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) return null;
        return remove(0);
    }

    @Override
    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public void addFirst(E e) {
        if (size == 0) {
            tail = new Node<>(e, null);
            tail.setNext(tail);
            size++;
        } else {
            add(0, e);
        }
    }

    @Override
    public void addLast(E e) {
        addFirst(e);
        tail = tail.getNext();
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail;
        do {
            curr = curr.next;
            sb.append(curr.data);
            if (curr != tail) {
                sb.append(", ");
            }
        } while (curr != tail);
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for (int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }
}
