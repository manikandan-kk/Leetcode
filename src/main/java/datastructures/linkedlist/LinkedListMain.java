package datastructures.linkedlist;

import java.util.*;

class MyLinkedList {
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

    private Node head;

    //O(N)
    public void insertAtLast(int data) {
        if (head == null) {
            head = new Node(data, null);
            return;
        }
        Node traverse = head;
        while (traverse.next != null) {
            traverse = traverse.next;
        }
        traverse.next = new Node(data, null);
    }

    //O(1)
    public void insertAtHead(int data) {
        if (head == null) {
            head = new Node(data, null);
            return;
        }
        head = new Node(data, head);
    }

    //O(N)
    public void insertAfter(int data, int after) {
        if (!search(after)) {
            throw new IllegalArgumentException(String.format("Node %s not exists", after));
        }
        Node traverse = head;
        while (traverse != null) {
            if (traverse.data == after) {
                Node nextOfAfter = traverse.next;
                traverse.next = new Node(data, nextOfAfter);
                break;
            }
            traverse = traverse.next;
        }
    }

    //O(N)
    public void insertBefore(int data, int before) {
        if (!search(before)) {
            throw new IllegalArgumentException(String.format("Node %s not exists", before));
        }
        if (before == head.data) {
            head = new Node(data, head);
            return;
        }
        Node traverse = head;
        while (traverse.next != null) {
            if (traverse.next.data == before) {
                Node nextOfCurr = traverse.next;
                traverse.next = new Node(data, nextOfCurr);
                break;
            }
            traverse = traverse.next;
        }
    }

    //O(1)
    public int head() {
        if (head == null) throw new IllegalStateException("LinkedList is empty");
        return head.data;
    }

    //O(N)
    public int length() {
        Node traverse = head;
        int length = 0;
        while (traverse != null) {
            traverse = traverse.next;
            length++;
        }
        return length;
    }

    //O(1)
    public void clear() {
        head = null;
    }

    //O(1)
    public boolean isEmpty() {
        return head == null;
    }

    //O(1)
    public boolean isNotEmpty() {
        return head != null;
    }

    //O(N)
    public boolean search(int data) {
        Node traverse = head;
        while (traverse != null) {
            if (traverse.data == data) {
                return true;
            }
            traverse = traverse.next;
        }
        return false;
    }

    //O(N)
    public void reverse() {
        if (head == null) return;
        Node f = head, s = null;
        while (f != null) {
            Node temp = f.next;
            f.next = s;
            s = f;
            f = temp;
        }
        head = s;
    }

    //O(N)
    private void reverseRecurse(Node curr, Node prev) {
        if (curr == null) {
            head = prev;
            return;
        }
        reverseRecurse(curr.next, curr);
        curr.next = prev;
    }

    //O(N)
    public void reverseRecursively() {
        reverseRecurse(head, null);
    }

    //O(N/2)
    public int findMiddle() {
        Node fast = head;
        Node slow = head;
        while(fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast.next != null) {
                fast = fast.next;
            }
        }
        return slow.data;
    }

    //O(N)
    public void createFullLoop() {
        if (head == null) {
            throw new IllegalStateException("LinkedList is empty");
        }
        Node traverse = head;
        while(traverse.next != null) {
            traverse = traverse.next;
        }
        traverse.next = head;
    }

    //O(N)
    public void createLoopTo(int data) {
        if (head == null) {
            throw new IllegalStateException("LinkedList is empty");
        }
        Node last = head;
        while(last.next != null) {
            last = last.next;
        }

        Node traverse = head;
        while(traverse != null) {
            if (traverse.data == data) {
                last.next = traverse;
                return;
            }
            traverse = traverse.next;
        }
    }

    //O(N)
    public boolean detectLoop() {
        if (head == null) {
            throw new IllegalStateException("LinkedList is empty");
        }
        Node fast = head;
        Node slow = head;
        while(fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast.next != null) {
                fast = fast.next;
            }
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    //O(N)
    public int lengthOfLoop() {
        if (head == null) {
            throw new IllegalStateException("LinkedList is empty");
        }
        boolean hasLoop = detectLoop();
        Node intersection = null;
        if (hasLoop) {
            //find loop intersection
            Node fast = head;
            Node slow = head;
            while(fast.next != null) {
                slow = slow.next;
                fast = fast.next;
                if (fast.next != null) {
                    fast = fast.next;
                }
                if (slow == fast) {
                    intersection = slow;
                    break;
                }
            }
            fast = intersection; slow = intersection;
            int count = 0;
            do {
                slow = slow.next;
                fast = fast.next.next;
                count++;
            } while(slow != fast);
            return count;
        }
        return -1;
    }

    //O(N)
    public void destroyLoop() {
        if (head == null) {
            throw new IllegalStateException("LinkedList is empty");
        }
        boolean hasLoop = detectLoop();
        if (hasLoop) {
            Set<Node> visited = new HashSet<>();
            Node traverse = head;

            while (traverse != null) {
                visited.add(traverse);
                if (visited.contains(traverse.next)) {
                    traverse.next = null;
                }
                traverse = traverse.next;
            }
            return;
        }
        throw new IllegalArgumentException("No loop detected!");
    }

    //O(N)
    public void removeDuplicates() {
        if (head == null) {
            return;
        }
        Node traverse = head;
        HashSet<Integer> visited = new HashSet<>();
        while (traverse.next != null) {
            visited.add(traverse.data);
            if (visited.contains(traverse.next.data)) {
                traverse.next = traverse.next.next;
            } else {
                traverse = traverse.next;
            }
        }
    }

    //O(N)
    public void removeAllEvenNumbers() {
        if (head == null) {
            return;
        }
        while(head != null && head.data % 2 == 0) {
            head = head.next;
        }
        Node traverse = head;
        if (traverse != null) {
            while (traverse.next != null) {
                if (traverse.next.data % 2 == 0) {
                    traverse.next = traverse.next.next;
                } else {
                    traverse = traverse.next;
                }
            }
        }
    }

    //O(N)
    public void print() {
        int i = 0;
        Node traverse = head;
        while (traverse != null) {
            System.out.print(traverse.data);
            if (traverse.next != null) {
                System.out.print("->");
            }
            traverse = traverse.next;
            i++;
            if (i > 50) break;
        }
        System.out.println();
    }

    public void printOnlyOdd() {
        Node traverse = head;
        boolean firstFound = false;
        while(traverse != null) {
            if (traverse.data % 2 != 0) {
                if (firstFound)
                    System.out.print("->");
                if (!firstFound) firstFound = true;
                System.out.print(traverse.data);
            }
            traverse = traverse.next;
        }
        System.out.println();
    }

    public void printOnlyEven() {
        Node traverse = head;
        boolean firstFound = false;
        while(traverse != null) {
            if (traverse.data % 2 == 0) {
                if (firstFound)
                    System.out.print("->");
                if (!firstFound) firstFound = true;
                System.out.print(traverse.data);
            }
            traverse = traverse.next;
        }
        System.out.println();
    }

    static class NthNode {
        int x;
        Node node;
        public NthNode(int x) { this.x = x; }
    }

    public NthNode findNthNode(Node head, int N) {
        if (head == null) {
            return new NthNode(0);
        }
        NthNode nthNode = findNthNode(head.next, N);
        nthNode.x++;
        if (nthNode.node == null) {
            if (nthNode.x == N) {
                nthNode.node = head;
            }
        }
        return nthNode;
    }

    public NthNode getNthNode(int N) {
        return findNthNode(this.head, N);
    }
}

public class LinkedListMain {
    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.insertAtLast(2);
        myLinkedList.insertAtLast(7);
        myLinkedList.insertAtLast(1);
        myLinkedList.insertAtLast(3);
        myLinkedList.insertAtLast(5);
        myLinkedList.insertAtLast(11);
        myLinkedList.insertAtLast(12);
        myLinkedList.print();
        System.out.println(myLinkedList.head());
        myLinkedList.insertAtHead(21);
        myLinkedList.print();
        System.out.println(myLinkedList.head());
        System.out.println(myLinkedList.length());
        System.out.println("Search result of 11: " + myLinkedList.search(11));
        System.out.println("Search result of 96: " + myLinkedList.search(96));
        myLinkedList.insertAfter(73, 3);
        myLinkedList.print();
        myLinkedList.insertBefore(17, 1);
        myLinkedList.print();
        myLinkedList.reverseRecursively();
        myLinkedList.print();
        System.out.println("Middle: " + myLinkedList.findMiddle());
        myLinkedList.insertAtLast(36);
        myLinkedList.insertAtLast(77);
        myLinkedList.insertAtLast(69);
        myLinkedList.print();
        System.out.println("Middle: " + myLinkedList.findMiddle());
        System.out.println("Has Loop: " + myLinkedList.detectLoop());
        //myLinkedList.createFullLoop();
        myLinkedList.createLoopTo(73);
        System.out.println("Has Loop: " + myLinkedList.detectLoop());
        System.out.println("Length of Loop: " + myLinkedList.lengthOfLoop());
        myLinkedList.print();
        myLinkedList.destroyLoop();
        System.out.println("Has Loop: " + myLinkedList.detectLoop());
        System.out.println("Length of Loop: " + myLinkedList.lengthOfLoop());
        myLinkedList.print();
        myLinkedList.insertAtHead(4);
        myLinkedList.insertAtHead(4);
        myLinkedList.insertAtLast(4);
        myLinkedList.insertAtLast(4);
        myLinkedList.insertAfter(4, 73);
        myLinkedList.insertAfter(4, 7);
        myLinkedList.insertAfter(17, 36);
        myLinkedList.insertAtLast(33);
        myLinkedList.print();
        //myLinkedList.removeDuplicates();
        //myLinkedList.print();
        //myLinkedList.removeAllEvenNumbers();;
        myLinkedList.print();
        myLinkedList.printOnlyOdd();
        myLinkedList.printOnlyEven();
        System.out.println(myLinkedList.getNthNode(20).node.getData());
    }
}