package CalculatorStack;

//defines a generic class
public class DynamicStack<T> {
    //the capacity of the stack
    private int size;
    //an integer indicating the number of elements the stack has stored so far
    private int pointer = 0;
    //declare a generic array to store elements
    private T[] dynamicArray;

    //non-argument constructor, stack with default size - 10
    public DynamicStack (){
        dynamicArray = (T[])(new Object[10]);
    }

    //argument constructor, receives an integer as the size of the stack
    public DynamicStack(int size) throws IllegalArgumentException{
        if(size < 0)
            throw new IllegalArgumentException("Illegal Capacity: " + size);
        this.size = size;
        dynamicArray = (T[])(new Object[size]);
    }
    //method that returns the top element on the stack
    public T top() {
        if(isEmpty())
            return null;
        return dynamicArray[this.size()-1];
    }

    //method that adds the passing element to the stack
    public void push(T t) {
        //check the size before adding elements
        checkSize();
        dynamicArray[pointer] = t;
        pointer++;
    }
    //method that deletes and returns the top element form the stack,
    //or returns null if it is empty
    public T pop() {
        if(isEmpty())
            return null;
        else {
            pointer--;
            T delete = dynamicArray[pointer];
            dynamicArray[pointer] = null;
            return delete;
        }
    }

    //method that check whether or not the stack is full
    //if does, extends the size by 5
    public void checkSize() {
        //extends the size if the pointer is equal to it
        if(pointer == size) {
            //creates a new array whose size is 5 more than the old one
            T[] temp = (T[])(new Object[size+5]);
            //assigns the elements to the new array
            for(int i =0; i < size; i++) {
                temp[i] = dynamicArray[i];
            }
            //assigns the reference of new array to the old array
            dynamicArray = temp;
            size += 5;
        }

    }

    //return the size of the stack
    public int size() {
        return pointer;
    }

    //method that indicates whether or not the stack is emptu
    public boolean isEmpty() {
        if(pointer == 0) {
            return true;
        }
        else
            return false;
    }
}
