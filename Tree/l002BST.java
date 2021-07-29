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
}