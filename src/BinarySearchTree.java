import java.util.ArrayList;
import java.util.List;

/**
 * Binary Search Tree implementation in Java.
 *
 * @author [Your Name]
 * @date [Current Date]
 */
public class BinarySearchTree {

    private TreeNode root;

    /**
     * Constructor - initializes an empty BST.
     */
    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Insert a value into the BST.
     * If the value already exists, do not insert it (no duplicates allowed).
     *
     * @param value The value to insert
     */
    public void insert(int value) {
        root = insertHelper(root, value);
    }

    /**
     * Search for a value in the BST.
     *
     * @param value The value to search for
     * @return true if the value exists in the tree, false otherwise
     */
    public boolean search(int value) {
        return searchHelper(root, value);
    }

    /**
     * Delete a value from the BST.
     * If the value doesn't exist, do nothing.
     *
     * @param value The value to delete
     */
    public void delete(int value) {
        root = deleteHelper(root, value);
    }

    /**
     * Find the minimum value in the BST.
     *
     * @return The minimum value
     * @throws IllegalStateException if the tree is empty
     */
    public int findMin() {
        if (root == null) {
            throw new IllegalArgumentException("Cannot find min");
        }

        TreeNode current = root;

        while (current.left != null) {
            current = current.left;
        }
        return current.data;
    }

    /**
     * Find the maximum value in the BST.
     *
     * @return The maximum value
     * @throws IllegalStateException if the tree is empty
     */
    public int findMax() {
        if (root == null) {
            throw new IllegalArgumentException("Cannot find max");
        }
        TreeNode current = root;

        while (current.right != null) {
            current = current.right;
        }
        return current.data;
    }

    /**
     * Perform an inorder traversal of the BST.
     *
     * @return A list of values in inorder sequence
     */
    public List inorderTraversal() {
        List<Integer> result = new ArrayList<>();

        inorderHelper(root, result);
        return result;
    }

    /**
     * Perform a preorder traversal of the BST.
     *
     * @return A list of values in preorder sequence
     */
    public List preorderTraversal() {
        List<Integer> result = new ArrayList<>();

        preorderHelper(root, result);
        return result;
    }

    /**
     * Perform a postorder traversal of the BST.
     *
     * @return A list of values in postorder sequence
     */
    public List postorderTraversal() {
        List<Integer> result = new ArrayList<>();

        postorderHelper(root, result);
        return result;
    }

    /**
     * Calculate the height of the BST.
     * Height is defined as the number of edges on the longest path from root to leaf.
     * An empty tree has height -1, a tree with one node has height 0.
     *
     * @return The height of the tree
     */
    public int height() {
        return heightHelper(root);
    }

    /**
     * Count the number of nodes in the BST.
     *
     * @return The number of nodes
     */
    public int size() {
        return sizeHelper(root);
    }

    /**
     * Check if the BST is empty.
     *
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Get the root of the tree (for testing purposes).
     *
     * @return The root node
     */
    public TreeNode getRoot() {
        return this.root;
    }

    // ========================================
    // HELPER METHODS
    // You may add private helper methods below
    // ========================================

    private int sizeHelper(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return sizeHelper(node.left) + sizeHelper(node.right) + 1;
    }

    private boolean searchHelper(TreeNode node, int val) {
        if (node == null) {
            return false;
        }
        if (val == node.data) {
            return true;
        }

        if (val < node.data) {
            return searchHelper(node.left, val);
        }else {
            return searchHelper(node.right, val);
        }
    }

    private TreeNode insertHelper(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val);
        }

        if (val < node.data) {
            node.left = insertHelper(node.left, val);
        }else if (val > node.data) {
            node.right = insertHelper(node.right, val);
        }

        return node;
    }

    private void inorderHelper(TreeNode node, List<Integer> out) {
        if (node == null) {
            return;
        }

        inorderHelper(node.left, out);
        out.add(node.data);
        inorderHelper(node.right, out);
    }

    private void preorderHelper(TreeNode node, List<Integer> out) {
        if (node == null) {
            return;
        }

        out.add(node.data);
        preorderHelper(node.left, out);
        preorderHelper(node.right, out);
    }

    private void postorderHelper(TreeNode node, List<Integer> out) {
        if (node == null) {
            return;
        }

        postorderHelper(node.left, out);
        postorderHelper(node.right, out);
        out.add(node.data);
    }

    private int heightHelper(TreeNode node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(heightHelper(node.left), heightHelper(node.right));
    }

    private TreeNode deleteHelper(TreeNode node, int val) {
        if (node == null) {
            return null;
        }

        if (val < node.data) {
            node.left = deleteHelper(node.left, val);
        }else if (val > node.data) {
            node.right = deleteHelper(node.right, val);
        }else {

            if (node.left == null && node.right == null) {
                return null;                                          // CASE 1
            }else if (node.left == null) {
                return node.right;                                    // CASE 2 Right
            }else if (node.right == null) {
                return node.left;                                     // CASE 2 Left
            }else {
                TreeNode successor = node.right;
                while (successor.left != null) {
                    successor = successor.left;
                }
                node.data = successor.data;
                 node.right = deleteHelper(node.right, successor.data);
            }
        }
        return node;
    }

}