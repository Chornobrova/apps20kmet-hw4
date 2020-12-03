package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {
    private ImmutableLinkedList data;
    public Queue() {
        data = new ImmutableLinkedList();
    }

    public void enqueue(Object obj) {
        data = data.addLast(obj);
    }

    public Object peek() {
        return data.getFirst();
    }

    public Object dequeue() {
        Object obj = peek();
        data = data.removeFirst();
        return obj;
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public Object[] getData() {
        return data.toArray();
    }
}
