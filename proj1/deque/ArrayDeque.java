package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.lang.Math.max;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] array;
    private int nextFirst;
    private int nextLast;
    private int size;
    private int arraySize = 8;

    public ArrayDeque() {
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        array = (T[]) new Object[arraySize];
    }

    /* Occurs when the nextFirst == nextLast*/
    private void increaseSize() {
        int originalSize = arraySize;
        arraySize = arraySize * 2;
        T[] temp = array;
        array = (T[]) new Object[arraySize];

        System.arraycopy(temp, 0, array, 0, nextLast);
        int remainingSize = originalSize - nextFirst - 1;
        System.arraycopy(temp, nextFirst + 1, array, arraySize - remainingSize, remainingSize);
        nextFirst = arraySize - remainingSize - 1;
    }

    /* Occurs when size <= arraySize//4*/
    private void reduceSize() {
        int originalSize = arraySize;
        arraySize = max(arraySize / 4, 8);
        T[] temp = array;
        array = (T[]) new Object[arraySize];

        if (nextFirst < nextLast) {
            System.arraycopy(temp, nextFirst + 1, array, 0, size);
        } else {
            if (nextFirst == originalSize - 1) {
                System.arraycopy(temp, 0, array, 0, size);
            }  else {
                System.arraycopy(temp, nextFirst + 1, array, 0, originalSize - nextFirst - 1);
                System.arraycopy(temp, 0, array, originalSize - nextFirst - 1, nextLast);
            }
        }
        nextFirst = arraySize - 1;
        nextLast = size;
    }

    private void moveNextFirst() {
        if (nextFirst == 0) {
            nextFirst = arraySize - 1;
        } else {
            nextFirst -= 1;
        }
    }

    private void inverseNextFirst() {
        if (nextFirst == arraySize - 1) {
            nextFirst = 0;
        } else {
            nextFirst += 1;
        }
    }

    private void moveNextLast() {
        if (nextLast == arraySize - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
    }

    private void inverseNextLast() {
        if (nextLast == 0) {
            nextLast = arraySize - 1;
        } else {
            nextLast -= 1;
        }
    }

    /* Adds an item of type T to the front of the deque*/
    public void addFirst(T item) {
        if (nextFirst == nextLast) {
            increaseSize();
        }
        array[nextFirst] = item;
        size += 1;
        moveNextFirst();
    }

    /*Adds an item of tpe T to the back of the deque*/
    public void addLast(T item) {
        if (nextFirst == nextLast) {
            increaseSize();
        }
        array[nextLast] = item;
        size += 1;
        moveNextLast();
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (nextFirst < nextLast) {
            int temp = nextFirst + 1;
            System.out.print(array[temp]);
            temp += 1;
            while (temp < nextLast) {
                System.out.print(" " + array[temp]);
                temp += 1;
            }
        }  else {
            int temp = nextFirst + 1;
            if (temp == arraySize) {
                temp = 0;
                System.out.print(array[temp]);
                temp += 1;
            } else {
                System.out.print(array[temp]);
                temp += 1;
                while (temp < arraySize) {
                    System.out.print(" " + array[temp]);
                    temp += 1;
                }
                temp = 0;
            }
            while (temp < nextLast) {
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
        inverseNextFirst();
        T temp = array[nextFirst];
        size -= 1;
        if (arraySize > 8 && size < (arraySize / 4)) {
            reduceSize();
        }
        return temp;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        inverseNextLast();
        T temp = array[nextLast];
        size -= 1;
        if (arraySize > 8 && size < (arraySize / 4)) {
            reduceSize();
        }
        return temp;
    }

    public T get(int index) {
        int position;
        if (index >= size) {
            return null;
        }
        if (nextFirst < nextLast) {
            position = nextFirst + 1 + index;
            return array[position];
        } else {
            position = nextFirst + 1 + index;
            if (position < arraySize) {
                return array[position];
            }
            position -= arraySize;
            return array[position];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {
        private int index;
        private ArrayIterator() {
            index = 0;
        }
        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                T item = get(index);
                index += 1;
                return item;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Deque<?>)) {
            return false;
        }

        Deque<?> temp = (Deque<?>) o;
        if (temp.size() != this.size()) {
            return false;
        } else {
            for (int i = 0; i < this.size; i++) {
                if (!(temp.get(i)).equals(this.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }
}
