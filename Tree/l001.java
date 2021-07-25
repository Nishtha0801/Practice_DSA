import java.util.ArrayList;
public class l001{
    public static class TreeNode{
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val){
            this.val = val;
        }
    }

    public static int size(TreeNode root){
        return root == null? 0 : size(root.left) + size(root.right)+1;
    }

    public static int height(TreeNode root){
        return root == null? -1 : Math.max(height(root.left), height(root.right))+1;
    }

    public static int maximum(TreeNode root){
        return root == null? -(int)1e9 : Math.max(root.val, Math.max(maximum(root.left), maximum(root.right)));
    }

    public static int minimum(TreeNode root){
        return root == null? (int)1e9 : Math.min(root.val, Math.min(maximum(root.left), maximum(root.right)));
    }
   
    public static boolean find(TreeNode root, int data){
        if(root == null){
            return false;
        }
        if(root.val == data){
            return true;
        }
        return find(root.left, data) || find(root.right, data);
    }

    public static boolean nodeToRootPath_(TreeNode root, int data, ArrayList<TreeNode>ans){
        if(root == null){
            return false;
        }
        if(root.val == data){
            ans.add(root);
            return true;
        }
        boolean res = nodeToRootPath_(root.left, data, ans) || nodeToRootPath_(root.right, data, ans);
        if(res){
            ans.add(root);
        }
        return res;
    }

    public static ArrayList<TreeNode> nodeToRootPath_(TreeNode root, int data){
        if(root == null){
            return new ArrayList<>();
        }
        if(root.val == data){
            ArrayList<TreeNode>base = new ArrayList<>();
            base.add(root);
            return base;
        }
        ArrayList<TreeNode>left = nodeToRootPath_(root.left, data);
        if(left.size()!=0){
            left.add(root);
            return left;
        }
        ArrayList<TreeNode>right = nodeToRootPath_(root.right, data);
        if(right.size()!=0){
            right.add(root);
            return right;
        }
        return new ArrayList<>();
    }

    public static void rootToAllLeafPath(TreeNode root, ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> smallAns){
        if(root == null){
            return;
        }
        if(root.left == null && root.right == null){
            ArrayList<Integer> base = new ArrayList<>(smallAns);
            base.add(root);
            ans.add(base);
            return;
        }
        smallAns.add(root.val);
        rootToAllLeafPath(root.left, ans, smallAns);
        rootToAllLeafPath(root.right, ans, smallAns);
        smallAns.remove(smallAns.size()-1);
    }

    public static ArrayList<ArrayList<Integer>> rootToAllLeafPath(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> smallAns = new ArrayList<>();

        rootToAllLeafPath(root, ans, smallAns);
        return ans;
    }

    public static void exactlyOneChild(TreeNode root, ArrayList<Integer> ans){
        if(root == null || (root.left == null && root.right == null)){
            return;
        }
        if(root.left == null || root.right == null){
            ans.add(root.val);
        }
        exactlyOneChild(root.left, ans);
        exactlyOneChild(root.right, ans);
    }

    public static ArrayList<Integer> exactlyOneChild(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        exactlyOneChild(root);
        return ans;
    }

    public static int countExactlyOneChild(TreeNode root){
        if(root == null || (root.left == null && root.right == null)){
            return 0;
        }
        int left = countExactlyOneChild(root.left);
        int right = countExactlyOneChild(root.right);

        int ans = left + right;
        if(node.left == null || node.right == null){
            ans++;
        }
        return ans;
    }

    public static void kdown(TreeNode root, TreeNode block, int k, ArrayList<Integer> ans){
        if(root == null || k<0 || root == block){
            return;
        }
        if(k==0){
            ans.add(root.val);
            return;
        }
        kdown(root.left, block, k-1, ans);
        kdown(root.right, block, k-1, ans);
    }

    public static ArrayList<Integer> distanceK(TreeNode root, TreeNode target, int k){
        ArrayList<TreeNode> path = new ArrayList<>();
        nodeToRootPath_(root, target.val, path);

        ArrayList<Integer> ans = new ArrayList<>();
        TreeNode block = null;
        for(int i=0; i<path.size(); i++){
            kdown(path.get(i), block, k-i, ans);
            block = path.get(i);
        }
    }

    public int distanceK(TreeNode root, TreeNode target, int k, List<Integer> ans) {
        if (root == null)
            return -1;

        if (root == target) {
            kdown(root, k, null, ans);
            return 1;
        }

        int ld = distanceK(root.left, target, k, ans);
        if (ld != -1) {
            kdown(root, k - ld, root.left, ans);
            return ld + 1;
        }

        int rd = distanceK(root.right, target, k, ans);
        if (rd != -1) {
            kdown(root, k - rd, root.right, ans);
            return rd + 1;
        }

        return -1;
    }


    public static void main(String[] args){

    }
}