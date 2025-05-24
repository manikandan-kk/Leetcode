package datastructures.queue;

public class ArrayQueue {

    private static final Integer DEFAULT_QUEUE_SIZE = 100000;
    private int[] queue;
    private int headPos;
    private int rearPos;
    private int queueSize;

    public ArrayQueue() {
        this(DEFAULT_QUEUE_SIZE);
    }

    public ArrayQueue(int size) {
        this.queue = new int[size];
        this.headPos = -1;
        this.rearPos = -1;
        this.queueSize = size;
    }

    //TC = O(1)
    public int head() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty, no head!");
        }
        return queue[headPos];
    }

    //TC = O(1)
    public boolean isEmpty() {
        return headPos == -1;
    }

    //TC = O(1)
    public void clear() {
        headPos = -1;
        rearPos = -1;
    }

    //TC = O(1)
    public void add(int data) {
        if (size() < queueSize) {
            if (rearPos == -1) {
                headPos = 0;
            }
            if (rearPos == queueSize - 1) {
                rearPos = 0;
            } else {
                rearPos++;
            }
            queue[rearPos] = data;
            return;
        }
        throw new IllegalStateException("Queue is full");
    }

    //TC = O(1)
    public int poll() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        int pollable = queue[headPos];
        if (rearPos >= headPos) {
            headPos++;
            if (headPos > rearPos) { //all are polled
                clear();
            }
        } else {
            headPos++;
            if (headPos == queueSize) {
                headPos = 0;
            }
        }
        return pollable;
    }

    //TC = O(N) or currentQueueSize
    public void printQueue() {
        if (!isEmpty()) {
            if (headPos <= rearPos) {
                for (int i = headPos; i <= rearPos; i++) {
                    System.out.print(queue[i]);
                    if (i != rearPos)
                        System.out.print("<-");
                }
            } else {
                for (int i = headPos; i < queueSize; i++) {
                    System.out.print(queue[i]);
                    System.out.print("<-");
                }
                for (int i = 0; i <= rearPos; i++) {
                    System.out.print(queue[i]);
                    if (i != rearPos) {
                        System.out.print("<-");
                    }
                }
            }
            System.out.println();
        }
    }

    public int size() {
        if (!isEmpty()) {
            return rearPos >= headPos ? (rearPos - headPos + 1) : ((queueSize - headPos) + (rearPos + 1));
        }
        return 0;
    }

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(5);
        System.out.println("is queue empty?: " + arrayQueue.isEmpty());
        //System.out.println("Head: " + arrayQueue.head());
        arrayQueue.add(3);
        arrayQueue.add(2);
        arrayQueue.add(1);
        arrayQueue.add(7);
        arrayQueue.add(13);
        arrayQueue.printQueue();
        System.out.println("Head: " + arrayQueue.head());
        arrayQueue.poll();
        arrayQueue.poll();
        arrayQueue.poll();
        arrayQueue.printQueue();
        System.out.println("Head: " + arrayQueue.head());
        arrayQueue.add(99);
        arrayQueue.add(100);
        arrayQueue.add(101);
        arrayQueue.printQueue();
        System.out.println("Head: " + arrayQueue.head());
        arrayQueue.poll();
        arrayQueue.poll();
        arrayQueue.printQueue();
        System.out.println("Head: " + arrayQueue.head());
        arrayQueue.add(335);
        arrayQueue.add(336);
        arrayQueue.printQueue();
        System.out.println("Head: " + arrayQueue.head());
        arrayQueue.poll();
        arrayQueue.printQueue();
        System.out.println("Head: " + arrayQueue.head());
    }
}