/** An SLList is a list of integers, which hides the terrible truth
 *  of the nakedness within.
 * 
 * IntList Example:
 * IntList L = new IntList(15, null);
 * L = new IntList(10, L);
 * L = new IntList(5, L);
 * 
 * It is the same as:
 * SLList L = new SLList(15);
 * L.addFirst(10);
 * L.addFirst(5);
 * */

public class SLList {
    private IntList list;
    private IntList sentinel;
    private static int mysteryNumber = 19; 
    private int size;

    public SLList(){
        size = 0;
        /* we find that there is a problem when we say list = null. The problem is when you create a new empty list, addLast Function will not work because it will ask for null.rest!
        What we do is we will create a mystery number ??, that be the first of the list, but this value does not have any meaning instead of creating the list!*/  
        sentinel = new IntList(mysteryNumber, null); 
    }

    public SLList(int value){
        // People don't need to know for a IntList, A IntList needs to have a IntList behind the first value. They don't need to care anything about how IntList works!
        
        // list = new IntList(value, null); 
        sentinel = new IntList(mysteryNumber, null);
        sentinel.rest = new IntList(value, null);
        size = 1;
    }

    /** Adds x to the front of the list */
    public void addFirst(int x){
        // this.list = new IntList(x, this.list);
        this.sentinel.rest = new IntList(x, this.sentinel.rest);
        size ++;
    }

    /** Returns the first item in the list */
    public int getFirst(){
        // return this.list.first;
        return this.sentinel.rest.first;
    }

    /** Adds x to the last of the list */
    public void addLast(int x){
        // IntList p = this.list;
        IntList p = this.sentinel;
        while (p.rest != null){
            p = p.rest;
        }
        p.rest = new IntList(x, null);
        size++;
    }

    /** Get the size of the list 
     * However, it is really slow!
    */
    public int oldSize(){
        // does not change this for the sentinel stuffs, use the newSize function
        IntList p = this.list;
        if (p.rest == null){
            return 1;
        }
        else{
            return 1 + p.rest.size();
        }
    }

    /** Really Fast Method by creating an instant variable */
    public int newSize(){
        return size;
    }

    public static void main(String[] args) {
        SLList L = new SLList(10);
        L.addFirst(5);
        L.addLast(15);
        L.getFirst();
        System.out.println(L.newSize());
    }
}