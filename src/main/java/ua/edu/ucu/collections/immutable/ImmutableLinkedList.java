package ua.edu.ucu.collections.immutable;

import lombok.Getter;
import lombok.Setter;


public class ImmutableLinkedList implements ImmutableList {
    static class Node {
        @Getter @Setter
        private Node next;

        @Getter @Setter
        private Object value;

    }

    private final Node head;
    private final int size;

    public ImmutableLinkedList() {
        head = new Node();
        size = 0;
    }

    private ImmutableLinkedList(Node arrHead, int arrSize) {
        head = arrHead;
        size = arrSize;
    }

    public ImmutableLinkedList add(Object e) {
        return add(size, e);
    }

    public ImmutableLinkedList add(int index, Object e) {
        return addAll(index, new Object[] {e});
    }

    public ImmutableLinkedList addAll(Object[] c) {
        return addAll(size, c);
    }

    private static Node[] copy(Node node, int length) {
        Node copyHead = new Node();
        Node copyCurrent = copyHead;
        Node current = node;
        for (int i = 0; i < length; i++) {
            copyCurrent.setValue(current.getValue());
            copyCurrent.setNext(new Node());
            copyCurrent = copyCurrent.getNext();
            current = current.getNext();
        }
        return new Node[] {copyHead, copyCurrent, current};
    }

    public ImmutableLinkedList addAll(int index, Object[] c) {

        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node[] nodeCopy = copy(head, index);

        Node copyHead = nodeCopy[0];
        Node copyCurrent = nodeCopy[1];
        Node current = nodeCopy[2];

        for (Object obj : c) {
            copyCurrent.setValue(obj);
            copyCurrent.setNext(new Node());
            copyCurrent = copyCurrent.getNext();
        }

        nodeCopy = copy(current, size - index);

        copyCurrent.setValue(nodeCopy[0].getValue());
        copyCurrent.setNext(nodeCopy[0].getNext());

        return  new ImmutableLinkedList(copyHead, size + c.length);

    }

    public Object get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getValue();
    }

    public ImmutableLinkedList remove(int index) {
        checkIndex(index);

        Node[] nodeCopy = copy(head, index);

        Node copyHead = nodeCopy[0];
        Node copyCurrent = nodeCopy[1];
        Node current = nodeCopy[2];

        current = current.getNext();

        nodeCopy = copy(current, size - index - 1);

        copyCurrent.setValue(nodeCopy[0].getValue());
        copyCurrent.setNext(nodeCopy[0].getNext());

        return  new ImmutableLinkedList(copyHead, size - 1);

    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    public ImmutableLinkedList set(int index, Object e) {
        checkIndex(index);
        Node[] nodeCopy = copy(head, index);
        Node copyHead = nodeCopy[0];
        Node copyCurrent = nodeCopy[1];
        Node current = nodeCopy[2];

        copyCurrent.setValue(e);
        copyCurrent.setNext(new Node());
        copyCurrent = copyCurrent.getNext();
        current = current.getNext();

        nodeCopy = copy(current, size - index - 1);

        copyCurrent.setValue(nodeCopy[0].getValue());
        copyCurrent.setNext(nodeCopy[0].getNext());

        return  new ImmutableLinkedList(copyHead, size);

    }

    public int indexOf(Object e) {
        Node current = head;
        for (int i = 0; i < size; i++) {
            if (current.getValue().equals(e)) {
                return i;
            }
            current = current.getNext();
        }
        return -1;
    }

    public int size() {
        return size;
    }

    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        Node current = head;
        for (int i = 0; i < size; i++) {
            array[i] = current.getValue();
            current = current.getNext();
        }
        return array;
    }

    @Override
    public String toString() {
        StringBuilder arrayString = new StringBuilder();
        Node current = head;
        arrayString.append("[");
        for (int i = 0; i < size; i++) {
            arrayString.append(current.getValue());
            arrayString.append(", ");
            current = current.getNext();
        }
        if (size >= 1) {
            arrayString.delete(arrayString.length() - 2,
                    arrayString.length());
        }
        arrayString.append("]");
        return arrayString.toString();
    }

    public ImmutableLinkedList addFirst(Object e) {
        return add(0, e);
    }
    public ImmutableLinkedList addLast(Object e) {
        return add(e);
    }
    public Object getFirst() {
        return get(0);

    }
    public Object getLast() {
        return get(size - 1);

    }
    public ImmutableLinkedList removeFirst() {
        return remove(0);

    }
    public ImmutableLinkedList removeLast() {
        return remove(size - 1);
    }

}