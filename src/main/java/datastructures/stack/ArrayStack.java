package datastructures.stack;

public class ArrayStack {

    private static final Integer DEFAULT_STACK_SIZE = 100000;
    private int head;
    private int[] stack;
    private int size;

    public ArrayStack() {
        this(DEFAULT_STACK_SIZE);
    }

    public ArrayStack(int size) {
        this.stack = new int[size];
        this.head = -1;
        this.size = size;
    }

    public int top() {
        if (head == -1) {
            throw new IllegalStateException("Stack is empty, no top!");
        }
        return stack[head];
    }

    public boolean isEmpty() {
        return head == -1;
    }

    public int pop() {
        if (head == -1) {
            throw new IllegalStateException("Stack is empty, cannot pop!");
        }
        int topData = top();
        head--;
        return topData;
    }

    public void push(int data) {
        if (head + 1 >= size) {
            throw new IllegalStateException("Stack is full, cannot add more elements!!");
        }
        head++;
        stack[head] = data;
    }

    public void displayStack() {
        for (int i = head; i >= 0; i--) {
            System.out.print(stack[i]);
            if (i > 0) System.out.print("->");
        }
        System.out.println();
    }

    public int size() {
        return head + 1;
    }

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);
        arrayStack.displayStack();
        System.out.println("Stack is empty? " + arrayStack.isEmpty());
        //System.out.println("Stack head " + arrayStack.top());
        arrayStack.push(2);
        arrayStack.displayStack();
        System.out.println("Stack size? " + arrayStack.size());
        System.out.println("Stack is empty? " + arrayStack.isEmpty());
        System.out.println("Stack head " + arrayStack.top());
        arrayStack.push(3);
        arrayStack.push(11);
        arrayStack.push(17);
        arrayStack.push(1);
        arrayStack.displayStack();
        System.out.println("Stack size? " + arrayStack.size());
        System.out.println("Stack is empty? " + arrayStack.isEmpty());
        System.out.println("Stack head " + arrayStack.top());
        arrayStack.pop();
        arrayStack.pop();
        arrayStack.pop();
        arrayStack.pop();
        arrayStack.displayStack();
        arrayStack.pop();
        arrayStack.displayStack();
    }
}
