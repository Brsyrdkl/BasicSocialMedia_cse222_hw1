package homework1;

public class BasicArray<T>{
    private int size;
    private int capacity;

    private T[] array;

    public BasicArray(T[] array){
        this.size = array.length;
        this.capacity = array.length;
        this.array = array;
    }

    public BasicArray(int capacity){
        this.capacity = capacity;
        this.size = 0;
        this.array = (T[]) new Object[capacity];
    }

    public BasicArray(){
        this.capacity = 10;
        this.size = 0;
        this.array = (T[]) new Object[capacity];
    }

    public int getSize(){
        return this.size;
    }

    public void add(T element){
        if(this.size == this.capacity){
            fullSize();
        }
        this.array[this.size] = element;
        this.size++;
    }
    private void fullSize(){
        @SuppressWarnings("unchecked")
        T[] newArray = (T[]) new Object[capacity * 2];

        for(int i = 0; i < this.size; i++){
            newArray[i] = this.array[i];
        }

        capacity=capacity*2;
        this.array = newArray;
    }

    public boolean remove(T element){

        @SuppressWarnings("unchecked")
        T[] newArray = (T[]) new Object[capacity];
        boolean flag=false;
        int j=0;
        for(int i = 0; i < this.size; i++){
            if(this.array[i].equals(element)){
                System.out.println("array");
                flag = true;
                continue;
            }
            newArray[j++] = this.array[i];
        }
        if (flag) {
            this.array = newArray;
            this.size--;
        }
        return flag;
    }
    public boolean contains(T element){
        for(int i = 0; i < this.size; i++){
            if(this.array[i].equals(element)){
                return true;
            }
        }
        return false;
    }

    public T get(int index){
        return this.array[index];
    }

    public T get(String index){
        Integer i = Integer.valueOf(index);
        if(i == 0){
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.array[i-1];
    }

    public String toString(){
        String str = "";
        for(int i = 0; i < this.size; i++){
            str += this.array[i] + " ";
        }
        return str;
    }


}