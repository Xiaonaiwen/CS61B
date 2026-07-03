package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> compare;
    public MaxArrayDeque(Comparator<T> c) {
        super();
        compare = c;
    }
    public T max() {
        return getT((Comparator<T>) compare);
    }
    public T max(Comparator<T> c) {
        return getT((Comparator<T>) c);
    }

    private T getT(Comparator<T> c) {
        if (this.size() == 0) {
            return null;
        }
        T max = this.get(0);
        for (int i = 0; i < this.size(); i++) {
            if (c.compare(max, this.get(i)) < 0) {
                max = this.get(i);
            }
        }
        return max;
    }
}
