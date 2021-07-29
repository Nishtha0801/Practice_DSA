import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
public class l003TraversalSet{
    public static class TreeNode{
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val){
            this.val = val;
        }
    }

    public static TreeNode getRightMostNode(TreeNode node, TreeNode curr){
        while(node.right != null && node.right != curr){
            node = node.right;
        }
        return node;
    }
    public static ArrayList<Integer> morrisInOrderTraversal(TreeNode root){
        ArrayList<Integer> ans = new ArrayList<>();
        TreeNode curr = root;
        while(curr != null){
            TreeNode left = curr.left;
            if(left == null){
                ans.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rightMostNode = getRightMostNode(left, curr);
                if(rightMostNode.right == null){
                    rightMostNode.right = curr;
                    curr = curr.left;
                } else {
                    rightMostNode.right = null;
                    ans.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        return;
    }
    public static ArrayList<Integer> morrisPreOrderTraversal(TreeNode root){
        ArrayList<Integer> ans = new ArrayList<>();
        TreeNode curr = root;
        while(curr != null){
            TreeNode left = curr.left;
            if(left == null){
                ans.add(curr.val);
                curr = curr.right;
            }
            else{
                TreeNode rightMostNode = getRightMostNode(left,curr);
                if(rightMostNode.right == null){
                    rightMostNode.right = curr;
                    ans.add(curr.val);
                    curr = curr.left;
                }
                else{
                    rightMostNode.right = null;
                    curr = curr.right;
                }
            }
        }
        return ans;
    }

    // Leetcode 98
    public boolean isValidBST(TreeNode root) {
        TreeNode curr = root;
        long prev = -(long) 1e13;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {

                if (prev >= curr.val)
                    return false;
                prev = curr.val;

                curr = curr.right;
            } else {
                TreeNode rightMostNode = getRightMostNode(left, curr);
                if (rightMostNode.right == null) { // thread creation block
                    rightMostNode.right = curr; // thread is created
                    curr = curr.left;
                } else { // thread destroy block
                    rightMostNode.right = null; // thread is cut down

                    if (prev >= curr.val)
                        return false;
                    prev = curr.val;

                    curr = curr.right;
                }
            }
        }

        return true;
    }

    // approach 2
    public void insertAllLeft(TreeNode node, LinkedList<TreeNode> st){
        while(node != null){
            st.addFirst(node);
            node = node.left;
        }
        return;
    }
    public boolean isValidBST(TreeNode root){
        LinkedList<TreeNode> st = new LinkedList<>();
        insertAllLeft(root, st);
        long prev = -(long) 1e13;
        while(st.size() != 0){
            TreeNode rNode = st.removeFirst();
            if(prev>=rNode.val){
                return false;
            }
            prev = rNode.val;
            insertAllLeft(rNode.right, st);
        }
        return true;
    }

    class BSTIterator{
        private LinkedList<TreeNode> st = new LinkedList<>();

        public BSTIterator(TreeNode root){
            insertAllLeft(root, st);
        }

        private void insertAllLeft(TreeNode node, LinkedList<TreeNode> st){
            while(node!=null){
                st.addFirst(node);
                node  = node.left;
            }
        }

        public int next(){
            TreeNode rn = st.removeFirst();
            insertAllLeft(rn.right, st);
            return rn.val;
        }
        public boolean hasNext(){
            return st.size()!=0;
        }
    }

    // approach 2
    class BSTIterator{
        private TreeNode curr = null;

        public BSTIterator(TreeNode root){
            curr = root;
        }
        private TreeNode getRightMostNode(TreeNode node, TreeNode curr){
            while(node.right != null && node.right != curr){
                node = node.right;
            }
            return node;
        }
        public int next(){
            int rv = -1;
            while(curr != null){
                TreeNode left = curr.left;
                if(left == null){
                    rv = curr.val;
                    curr = curr.right;
                    break;
                }
                else{
                    TreeNode rightMostNode = getRightMostNode(left, curr);
                    if(rightMostNode.right == null){
                        rightMostNode.right = curr;
                        curr = curr.left;
                    }
                    else{
                        rightMostNode.right = null;
                        rv = curr.val;
                        curr = curr.right;
                        break;
                    }
                }
            }
            return rv;
        }
        public boolean hasNext(){
            return curr != null;
        }
    }

    public int kthSmallest(TreeNode root, int k){
        int rv = -1;
        TreeNode curr = root;
        while(curr != null){
            TreeNode left = curr.left;
            if(left == null){
                if(--k == 0){
                    rv = curr.val;
                }
                curr = curr.right;
            } else {
                TreeNode rightMostNode = getRightMostNode(left, curr);
                if(rightMostNode.right == null){
                    rightMostNode.right = curr;
                    curr = curr.left;
                }
                else{
                    rightMostNode.right = null;
                    if(--k == 0){
                        rv = curr.val;
                    }
                    curr = curr.right;
                }
            }
        }
        return rv;
    }

    public int kthSmallest(TreeNode root, int k){
        LinkedList<TreeNode> st = new LinkedList<>();
        insertAllLeft(root, st);
        while(--k > 0){
            TreeNode rn = st.removeFirst();
            insertAllLeft(rn.right, st);
        }
        return st.removeFirst().val;
    }
}