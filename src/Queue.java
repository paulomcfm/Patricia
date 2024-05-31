public class Queue {
    private Queue head;
    private Queue tail;
    private Queue next;
    private Node node;

    public void enqueue(Node node){
        Queue queue = new Queue();
        queue.node = node;
        if(this.head == null){
            this.head = queue;
            this.tail = queue;
        } else {
            this.tail.next = queue;
            this.tail = queue;
        }
    }

    public Node dequeue(){
        Node node = this.head.node;
        this.head = this.head.next;
        return node;
    }

    public boolean isEmpty() {
        return this.head == null;
    }
}
