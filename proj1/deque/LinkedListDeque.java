package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T>{
    private static class Node<E>{
        private Node<E> before;
        private Node<E> after;
        private E value;

        private Node(E item){
            value = item;
            before = this; //For a single item, the item before it is itself
            after = this; //For a single item, the item after it is itself
        }

        private Node(){
            value = null;
            before = null;
            after = null;
        }
    }

    private Node<T> dl;
    private int size;

    public LinkedListDeque(){
        dl = new Node();
        size = 0;
    }

    /** Adds one item to the back of the deque*/
    public void addLast(T item){
        if (dl.value == null){
            dl = new Node(item);
        }
        else{
            Node add = new Node(item);
            Node originalLast = dl.before;
            Node originalFirst = dl;

            add.before = originalLast;
            add.after = originalFirst;

            originalFirst.before = add;
            originalLast.after = add;
        }
        size += 1;
    }


    /** Adds an item to the front of the deque*/
    public void addFirst(T item){
        /** Because of the characteristics of the list
         * addLast is same as addFirst. addFirst only need
         * one more step reset dl to the new first item
         */
        addLast(item);
        dl = dl.before;
    }

    /** Returns the size*/
    public int size(){
        return size;
    }

    /** Prints the items in the deque from first to last*/
    public void printDeque(){
        System.out.print(dl.value + " ");

        Node temp =  dl.after;
        while (temp != dl){
            System.out.print(temp.value + " ");
            temp = temp.after;
        }
        System.out.println();
    }

    /** Removes and returns the first item*/
    public T removeFirst(){
        if (size == 0){
            return null;
        }
        else if (size == 1){
            T value = dl.value;
            dl = new Node<>();
            size = 0;
            return value;
        }
        else{
            Node originalFirst = dl;
            Node secondFirst = dl.after;
            Node originalLast = dl.before;

            secondFirst.before = originalLast;
            originalLast.after = secondFirst;

            dl = secondFirst;
            size -= 1;
            return (T) originalFirst.value;
        }
    }

    /** Removes and returns the last item*/
    public T removeLast(){
        if (size == 0){
            return null;
        }
        else if (size == 1){
            T value = dl.value;
            dl = new Node<>();
            size = 0;
            return value;
        }
        else{
            Node originalFirst = dl;
            Node originalLast = dl.before;
            Node secondLast = originalLast.before;

            originalFirst.before = secondLast;
            secondLast.after = originalFirst;
            size -= 1;
            return (T) originalLast.value;
        }
    }

    /** Gets the item at the given index using iteration*/
    public T get(int index){
        if (index >= size){
            return null;
        }
        else{
            int i = 0;
            Node temp = dl;
            while (i != index){
                temp = temp.after;
                i += 1;
            }
            return (T) temp.value;
        }
    }

    /** Gets the item at the given index using recursion*/
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        } else {
            if (index == 0) {
                return dl.value;
            } else {
                LinkedListDeque next = new LinkedListDeque();
                next.dl = this.dl.after;
                next.size = this.size - 1;
                return (T) next.getRecursive(index - 1);
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T>{
        private int index;
        public LinkedListIterator(){
            index = 0;
        }
        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            else{
                T item = get(index);
                index += 1;
                return item;
            }
        }
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof LinkedListDeque<?>){
            LinkedListDeque<?> temp = (LinkedListDeque<?>) o;
            if(temp.size() != this.size()){
                return false;
            }
            else{
                for (int i = 0; i < temp.size(); i++){
                    if (temp.get(i) != this.get(i)){
                        return false;
                    }
                }
            }
        }
        else if (o instanceof ArrayDeque<?>){
            deque.ArrayDeque<?> temp = (deque.ArrayDeque<?>) o;
            if(temp.size() != this.size()){
                return false;
            }
            else{
                for (int i = 0; i < temp.size(); i++){
                    if (temp.get(i) != this.get(i)){
                        return false;
                    }
                }
            }
        }
        else{
            return false;
        }
        return true;
    }
}
