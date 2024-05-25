public class Stack {
    private Stack next;
    private Node node;

    public void init(){
        this.next = null;
        this.node = null;
    }
    public void push(Node node){
        Stack newStack = new Stack();
        newStack.node = node;
        newStack.next = this.next;
        this.next = newStack;
    }

    public Node pop(){
        Node node = this.next.node;
        this.next = this.next.next;
        return node;
    }

    public boolean isEmpty(){
        return this.next == null;
    }

    public Node peek(){
        return this.next.node;
    }

    public void print(){
        Stack current = this.next;
        while(current != null){
            System.out.println(current.node.getWord());
            current = current.next;
        }
    }
}
