package datastructures.stack;

public class LinkedListStack {

    static class Node {
        private int data;
        private Node next;
        public int getData() {
            return data;
        }
        public Node getNext() {
            return next;
        }
        public Node(int data, Node next) {
            this(data);
            this.next = next;
        }
        public Node(int data) {
            this.data = data;
        }
    }

    private static final Integer DEFAULT_STACK_SIZE = 100000;
    private Node head;
    private int size;
    private int count;

    public LinkedListStack() {
        this(DEFAULT_STACK_SIZE);
    }

    public LinkedListStack(int size) {
        this.size = size;
    }

    public int top() {
        if (head == null) {
            throw new IllegalStateException("Stack is empty, no top!");
        }
        return head.data;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int pop() {
        if (head == null) {
            throw new IllegalStateException("Stack is empty, cannot pop!");
        }
        int topData = top();
        head = head.next;
        --count;
        return topData;
    }

    public void push(int data) {
        if (count >= size) {
            throw new IllegalStateException("Stack is full, cannot add more elements!!");
        }
        count++;
        head = new Node(data, head);
    }

    public void displayStack() {
        Node traverse = head;
        while (traverse != null) {
            System.out.print(traverse.data);
            if (traverse.next != null) {
                System.out.print("->");
            }
            traverse = traverse.next;
        }
        System.out.println();
    }

    public int size() {
        return count;
    }

    public static void main(String[] args) {
        LinkedListStack linkedListStack = new LinkedListStack(5);
        linkedListStack.displayStack();
        System.out.println("Stack is empty? " + linkedListStack.isEmpty());
        //System.out.println("Stack head " + arrayStack.top());
        linkedListStack.push(2);
        linkedListStack.displayStack();
        System.out.println("Stack size? " + linkedListStack.size());
        System.out.println("Stack is empty? " + linkedListStack.isEmpty());
        System.out.println("Stack head " + linkedListStack.top());
        linkedListStack.push(3);
        linkedListStack.push(11);
        linkedListStack.push(17);
        linkedListStack.push(1);
        //linkedListStack.push(33);
        linkedListStack.displayStack();
        System.out.println("Stack size? " + linkedListStack.size());
        System.out.println("Stack is empty? " + linkedListStack.isEmpty());
        System.out.println("Stack head " + linkedListStack.top());
        linkedListStack.pop();
        linkedListStack.pop();
        linkedListStack.pop();
        linkedListStack.pop();
        linkedListStack.displayStack();
        linkedListStack.pop();
        linkedListStack.displayStack();
        //linkedListStack.pop();
    }
}