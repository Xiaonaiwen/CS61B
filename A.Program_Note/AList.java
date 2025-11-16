/** Array based list */

public class AList {
    private int[] items;
    private int size;

    public AList() {
        items = new int[100];
        size = 0;
    }    

    public void addLast(int x){
        if (size != items.length){
            items[size] = x;
            size += 1;
        }
        else{
            int[] newArray = new int[size + 1];
            System.arraycopy(items, 0, newArray, 0, size);
            newArray[size] = x;
            items = newArray;
            size += 1;
        }
    }

    public int getLast(){
        return items[size - 1];
    }

    public int get(int i){
        return items[i];
    }

    public int size(){
        return size;
    }

    /** Delete the last item, returns the deleted item */
    public int removeLast(){
        int x = getLast();
        size -= 1;
        return x;
    }
}

