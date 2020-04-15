package Practice;

public class Queue {
    private static class Node{
        private int val;
        private Node next;

        private Node(int x){
            this.val = x;
        }
    }

    private Node head;
    private Node tail;

    public boolean isEmpty(){
        return head == null;
    }

    public int peek(){
        return head.val;
    }

    public void add(int val){
        Node node = new Node(val);
        if(tail != null){
            tail.next = node;
        }
        tail = node;
        if(head == null){
            head = node;
        }
    }

    public int remove(){
        int value = head.val;
        head = head.next;
        if(head == null){//if there is only one node, after remove,
            tail = null;// it's empty, so both head and tail is null.
        }
        return value;
    }
}
