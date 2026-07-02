package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<T> implements Iterable<T> {
    T[] array;
    private int next_First;
    private int next_Last;
    private int size;
    private int array_Size = 8;

    public ArrayDeque() {
        size = 0;
        next_First = 0;
        next_Last = 1;
        array = (T[]) new Object[array_Size];
    }

    /* Occurs when the next_First == next_Last*/
    private void increase_Size() {
        int original_Size = array_Size;
        array_Size = array_Size * 2;
        T[] temp = array;
        array = (T[]) new Object[array_Size];

        System.arraycopy(temp, 0, array, 0, next_Last);
        int remainingSize = original_Size - next_First - 1;
        System.arraycopy(temp, next_First + 1, array, array_Size - remainingSize, remainingSize);
        next_First = array_Size - remainingSize - 1;
    }

    /* Occurs when size <= array_Size//4*/
    private void reduce_Size() {
        int original_Size = array_Size;
        array_Size = array_Size / 4;
        T[] temp = array;
        array = (T[]) new Object[array_Size];

        if (next_First < next_Last) {
            System.arraycopy(temp, next_First + 1, array, 0, size);
        } else {
            if (next_First == original_Size - 1) {
                System.arraycopy(temp, 0, array, 0, size);
            } else {
                System.arraycopy(temp, next_First + 1, array, 0, original_Size - next_First - 1);
                System.arraycopy(temp, 0, array, original_Size - next_First - 1, next_Last);
            }
        }
        next_First = array_Size - 1;
        next_Last = size;
    }

    private void move_nextFirst() {
        if (next_First == 0) {
            next_First = array_Size - 1;
        } else {
            next_First -= 1;
        }
    }

    private void inverse_nextFirst() {
        if (next_First == array_Size - 1) {
            next_First = 0;
        } else {
            next_First += 1;
        }
    }

    private void move_nextLast() {
        if (next_Last == array_Size - 1) {
            next_Last = 0;
        } else {
            next_Last += 1;
        }
    }

    private void inverse_nextLast() {
        if (next_Last == 0) {
            next_Last = array_Size - 1;
        } else {
            next_Last -= 1;
        }
    }

    /* Adds an item of type T to the front of the deque*/
    public void addFirst(T item) {
        array[next_First] = item;
        size += 1;
        move_nextFirst();

        if (next_First == next_Last) {
            increase_Size();
        }
    }

    /*Adds an item of tpe T to the back of the deque*/
    public void addLast(T item) {
        array[next_Last] = item;
        size += 1;
        move_nextLast();

        if (next_First == next_Last) {
            increase_Size();
        }
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (next_First < next_Last) {
            int temp = next_First + 1;
            System.out.print(array[temp]);
            temp += 1;
            while (temp < next_Last) {
                System.out.print(" " + array[temp]);
                temp += 1;
            }
        } else {
            int temp = next_First + 1;
            if (temp == array_Size) {
                temp = 0;
                System.out.print(array[temp]);
                temp += 1;
            } else {
                System.out.print(array[temp]);
                temp += 1;
                while (temp < array_Size) {
                    System.out.print(" " + array[temp]);
                    temp += 1;
                }
                temp = 0;
            }
            while (temp < next_Last) {
                System.out.print(" " + array[temp]);
                temp += 1;
            }
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        inverse_nextFirst();
        T temp = array[next_First];
        size -= 1;
        if (array_Size > 8 && size < (array_Size / 4)) {
            reduce_Size();
        }
        return temp;
    }

    public T removeLast() {
        if (size == 0){
            return null;
        }
        inverse_nextLast();
        T temp = array[next_Last];
        size -= 1;
        if (array_Size > 8 && size < (array_Size / 4)) {
            reduce_Size();
        }
        return temp;
    }

    public T get(int index) {
        int position;
        if (index >= size) {
            return null;
        }
        if (next_First < next_Last) {
            position = next_First + 1 + index;
            return array[position];
        } else {
            position = next_First + 1 + index;
            if (position < array_Size) {
                return array[position];
            }
            position -= array_Size;
            return array[position];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T>{
        private int index;
        private ArrayIterator(){
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
        else if (o instanceof java.util.ArrayDeque<?>){
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
