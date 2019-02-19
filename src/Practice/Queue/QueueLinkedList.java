package Practice.Queue;

public class QueueLinkedList {

    QNode front , rear ;

    public class QNode{
        int key ;
        QNode next  ;

        QNode(int key){
            this.key = key ;
            this.next= null ;
        }
        QNode(int key ,QNode next){
            this.key = key ;
            this.next = next ;

        }
    }


    // construct an empty linked list
    public QueueLinkedList(){
        this.front = this.rear = null ;
    }


    public void enQueue(int key){
        // the linked list is empty
        if(this.rear ==null){
            this.rear = this.front = new QNode(key) ;
        }
        else{

            this.rear.next = new QNode(key) ;
            this.rear = this.rear.next;
        }
    }

    public QNode deQueue(){
        // the QueueLinkedList is Empty
        if(this.front==null){
            return null ;
        }
        QNode temp = this.front ;
        this.front = this.front.next ;

        // if the linked list becomes empty after moving the pointer towards the right, then:
        if(this.front==null){
            this.rear=null ;
        }
        return temp ;


    }
    public int front(){
        if(this.front==null)
            return Integer.MIN_VALUE ;

        return this.front.key ;
    }




}
