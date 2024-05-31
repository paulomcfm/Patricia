public class Stack {
    private Stack next;
    private Node node;

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
}
