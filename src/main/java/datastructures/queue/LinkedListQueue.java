package datastructures.queue;

public class LinkedListQueue {

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

    private static final Integer DEFAULT_QUEUE_SIZE = 100000;
    private Node head;
    private Node rear;
    private int counter;
    private int queueSize;

    public LinkedListQueue() {
        this(DEFAULT_QUEUE_SIZE);
    }

    public LinkedListQueue(int size) {
        this.counter = 0;
        this.queueSize = size;
    }

    //TC = O(1)
    public int head() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty!");
        }
        return head.data;
    }

    //TC = O(1)
    public boolean isEmpty() {
        return head == null;
    }

    //TC = O(1)
    public void clear() {
        this.head = null;
    }

    //TC = O(1)
    public void add(int data) {
        if (counter == queueSize) {
            throw new IllegalStateException("Queue is full!");
        }
        counter++;
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            rear = head;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    //TC = O(1)
    public int poll() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty!");
        }
        int pollData = this.head.data;
        this.head = head.next;
        counter--;
        return pollData;
    }

    //TC = O(N) or currentQueueSize
    public void printQueue() {
        Node traverse = this.head;
        while (traverse != null) {
            System.out.print(traverse.data);
            if (traverse.next != null) {
                System.out.print("->");
            }
            traverse = traverse.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedListQueue linkedListQueue = new LinkedListQueue(5);
        System.out.println("is queue empty?: " + linkedListQueue.isEmpty());
        //System.out.println("Head: " + linkedListQueue.head());
        linkedListQueue.add(3);
        linkedListQueue.add(2);
        linkedListQueue.add(1);
        linkedListQueue.add(7);
        linkedListQueue.add(13);
        linkedListQueue.printQueue();
        System.out.println("Head: " + linkedListQueue.head());
        linkedListQueue.poll();
        linkedListQueue.poll();
        linkedListQueue.poll();
        linkedListQueue.poll();
        linkedListQueue.printQueue();
        System.out.println("Head: " + linkedListQueue.head());
    }
}
