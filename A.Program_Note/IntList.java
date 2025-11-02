public class IntList{
    int first;
    IntList rest;

    public IntList(int f, IntList r){
        this.first = f;
        this.rest = r;
    }

    /**Return the size of the IntList */
    public int size(){
        if(this.rest == null){
            return 1;
        }
        return 1 + this.rest.size();
        
    }

    /**Get the ith item in the IntList (Assume it exists) */
    public int get(int i){
        if (i == 0){
            return this.first;
        }
        return this.rest.get(i-1);
    }

    public static void main(String[] args){
        IntList L = new IntList(15, null);
        L = new IntList(10, L);
        L = new IntList(5,L);
        System.out.println(L.size());
        System.out.println(L.get(2));
        System.out.println(L.get(0));
    }
}