package datastructures.trees;

import java.util.*;

class BinarySearchTree {
    private Node root;

    static class Node {
        int data;
        Node left;
        Node right;
        public Node(int data) {
            this.data = data;
        }
    }

    public void insert(int data) {
        if (root == null) {
            root = new Node(data);
            return;
        }
        Node traverse = root;
        while (traverse != null) {
            if (data <= traverse.data) {
                if (traverse.left == null) {
                    traverse.left = new Node(data);
                    traverse = null;
                    continue;
                }
                traverse = traverse.left;
            } else {
                if (traverse.right == null) {
                    traverse.right = new Node(data);
                    traverse = null;
                    continue;
                }
                traverse = traverse.right;
            }
        }
    }

    //level order traversal
    public void printBasic() {
        if (root == null) {
            return;
        }
        Map<Integer, Queue<Node>> map = new HashMap<>();
        Queue<Node> firstLevel = new LinkedList<>();
        firstLevel.add(root);
        map.put(1, firstLevel);
        int counter = 1;
        while (map.containsKey(counter)) {
            Queue<Node> queue = map.get(counter);
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                System.out.print(node.data + " ");
                if (node.left != null || node.right != null) {
                    Queue<Node> getQueue = map.getOrDefault(counter + 1, new LinkedList<>());
                    map.put(counter + 1, getQueue);
                    if (node.left != null) {
                        getQueue.add(node.left);
                    }
                    if (node.right != null) {
                        getQueue.add(node.right);
                    }
                }
            }
            System.out.println();
            counter++;
        }
    }
}

public class BinarySearchTreeMain {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(15);
        bst.insert(7);
        bst.insert(3);
        bst.insert(8);
        bst.insert(19);
        bst.insert(23);
        bst.insert(16);
        bst.insert(1);
        bst.printBasic();
        bst.insert(4);
        bst.printBasic();
    }
}