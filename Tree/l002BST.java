public class l002BST{

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static int size(TreeNode root){
        return root == null ? 0 : size(root.left) + size(root.right) + 1;
    }

    public static int height(TreeNode root){
        return root == null ? -1 : Math.max(height(root.left), height(root.right)) + 1;
    }

    public static TreeNode getLeftMost(TreeNode root){
        if(root == null){
            return null;
        }
        TreeNode curr = root;
        while(curr.left != null){
            curr = curr.left;
        }
        return curr;
    }

    public static TreeNode getRightMost(TreeNode root){
        if(root == null){
            return null;
        }
        TreeNode curr = root;
        while(curr.right != null){
            curr = curr.right;
        }
        return curr;
    }

    public static int maximum(TreeNode root){
        TreeNode curr = root;
        while(curr.right != null){
            curr = curr.right;
        }
        return curr.val;
    }

    public static int minimum(TreeNode root){
        TreeNode curr = root;
        while(curr.left != null){
            curr = curr.left;
        }
        return curr.val;
    }

    public static bool findData(TreeNode root, int data){
        if(root == null){
            return false;
        }
        TreeNode curr = root;
        while(curr != null){
            if(curr.val == data){
                return true;
            }
            else if(curr.val < data){
                curr = curr.right;
            }
            else {
                curr = curr.left;
            }
        }
        return false;
    }

   public static ArrayList<TreeNode> nodeToRootPathBST(TreeNode root, int data){
        if(root == null){
            return new ArrayList<>();
        }
        ArrayList<TreeNode> ans = new ArrayList<>();
        TreeNode curr = root;
        while(curr != null){
            ans.add(curr);
            if(curr.val == data){
                break;
            }
            else if(curr.val < data){
                curr = curr.right;
            }
            else{
                curr = curr.right;
            }
        }
        return ans;
    }

    // p and q may or may not exist
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        TreeNode LCA = null;
        TreeNode curr = root;
        while(curr != null){
            if(curr.val < p.val && curr.val < q.val){
                curr = curr.right;
            }
            else if(curr.val > p.val && curr.val > q.val){
                curr = curr.left;
            }
            else{
                LCA = curr;
                break;
            }
        }
        return (LCA != null && findData(LCA, p.val) && findData(LCA, q.val)) ? LCA : null;
    }

    public static TreeNode getLeftMost(TreeNode root){
        if(root == null){
            return null;
        }
        while(root.left != null){
            root = root.left;
        }
        return root;
    }

    public static TreeNode getRightMost(TreeNode root){
        if(root == null){
            return null;
        }
        while(root.right != null){
            root = root.right;
        }
        return root;
    }

    public static void predSucc(TreeNode root, int data){
        TreeNode curr = root;
        TreeNode succ = null;
        TreeNode pred = null;
        while(curr != null){
            if(curr.val == data){
                TreeNode leftMost = getLeftMost(curr.right);
                succ = leftMost != null ? leftMost : succ;

                TreeNode rightMost = getRightMost(curr.left);
                pred = rightMost != null ? rightMost : pred;
                break;
            } else if(curr.val < data) {
                pred = curr;
                curr = curr.right;
            }
            else{
                succ = curr;
                curr = curr.left;
            }
        }
    }

    public TreeNode insertIntoBST(TreeNode root, int val){
        if(root == null){
            return new TreeNode(val);
        }
        if(root.val < val){
            root.right = insertIntoBST(root.right, val);
        }
        else{
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }

    public int getMin(TreeNode root) {

        while (root.left != null)
            root = root.left;
        return root.val;
    }


    public TreeNode deleteNode(TreeNode root, int key){
        if(root == null){
            return null;
        }

        if(root.val < key){
            root.right = deleteNode(root.right, key);
        }
        else if(root.val > key){
            root.left = deleteNode(root.left, key);
        }
        else {
            if(root.left == null || root.right == null){
                TreeNode rNode = root.left != null ? root.left : root.right;
                root.left = root.right = null;
                // delete root;
                return rNode;
            }
            int minEle = getMin(root.right);
            root.val = minEle;

            root.right = deleteNode(root.right, minEle);

        }
        return root;
    }
}