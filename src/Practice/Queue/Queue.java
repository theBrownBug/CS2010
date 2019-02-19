package Practice.Queue;

public class Queue {
    public int front, rear , size ;
    int capacity ;
    public int[] array ;

    public Queue(int capacity){
        this.capacity = capacity ;
        this.front = this.size = 0 ;
        this.rear = this.capacity  - 1;
        array = new int[this.capacity] ;
    }

    public boolean isFull(Queue queue){
        return (queue.capacity == queue.size) ;
    }

    public boolean isEmpty(Queue queue){
        return (queue.size==0) ;
    }
    public void enqueue(int item){
        if(isFull(this)){
            System.out.println("The queue is full .. overflow condition");
            return ;
        }
        else{
            this.rear= (this.rear+1) % this.capacity ;
            array[this.rear] = item ;
            this.size += 1 ;
        }
    }
    public int dequeue(){
        if(isEmpty(this)){
            System.out.println("Underflow condition");
            System.exit(-1);
        }
        int removed = this.array[this.front] ;
        this.front = (this.front+1)% this.capacity ;
        this.size-= 1;
        return removed ;
    }
    public int front(){
        if(isEmpty(this)){
            return Integer.MIN_VALUE  ;
        }
        else{
            return(this.array[this.front]) ;
        }

    }
    public int rear(){
        if(isEmpty(this)){
            return Integer.MIN_VALUE ;
        }
        else{
            return(this.array[this.rear]) ;
        }
    }
}
