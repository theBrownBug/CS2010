package Practice.Queue;

public class PriorityQueueLinkedList {

    static class Node{
        int data , priority ;
        Node next ;
    }
    static Node node = new Node() ;
    static Node newNode(int d , int p){
        Node node = new Node();
        node.data = d ;
        node.priority= p ;
        node.next = null ;
        return  node  ;

    }
/*
    // return the value at the head ;
    public static int peek(Node head){
        return head.data;
    }

    //
    public static Node push(Node head){

    }
    public static Node pop(Node head){
        if
    }
    */
}
