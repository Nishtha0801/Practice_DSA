import java.util.ArrayList;
import java.util.LinkedList;

public class l004Construction{
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
    
        TreeNode(int val){
            this.val = val;
        }
    }

    public static TreeNode constructFromInorder(int[] inorder, int si, int ei){
        if(si>ei){
            return null;
        }
        int mid = (si+ei)/2;
        TreeNode root = new TreeNode(inorder[mid]);

        root.left = constructFromInorder(inorder, si, mid-1);
        root.right = constructFromInorder(inorder, mid+1, ei);

        return root;
    }

    public static TreeNode constructFromInorder(int[] inorder){
        return constructFromInorder(inorder, 0, inorder.length - 1);
    }

    public class Codec {
        public void serialize(TreeNode root, StringBuilder sb){
            if(root == null){
                return;
            }
            sb.append(root.val + " ");
            serialize(root.left, sb);
            serialize(root.right, sb);
        }

        public String serialize(TreeNode root){
            StringBuilder sb = new StringBuilder();
            serialize(root, sb);

            return sb.toString();
        }

        public TreeNode bstFromPreorder(int[] preorder, int lr, int rr, int[] idx){
            int i = idx[0];
            if(i >= preorder.length || preorder[i] < lr || preorder[i] > rr){
                return null;
            }
            TreeNode root = new TreeNode(preorder[i]);
            idx[0]++;

            root.left = bstFromPreorder(preorder, lr, root.val, idx);
            root.right = bstFromPreorder(preorder, root.val, rr, idx);

            return root;
        }

        public TreeNode deserialize(String data){
            if(data.equals("")){
                return null;
            }
            String[] arr = data.split(" ");
            int[] preorder = new int[arr.length];
            for(int i=0; i<arr.length; i++){
                preorder[i] = Integer.parseInt(arr[i]);
            }
            int[] idx = new int[1];
            return bstFromPreorder(preorder, -(int)1e9, (int)1e9, idx);
        }
    }

    TreeNode getMidNode(TreeNode head){
        if(head == null || head.right == null){
            return head;
        }
        TreeNode slow = head;
        TreeNode fast = head;
        while(fast.right != null && fast.right.right != null){
            fast = fast.right.right;
            slow = slow.right;
        }
        return slow;
    }

    TreeNode dllToBST(TreeNode head){
        if(head == null || head.right == null){
            return head;
        }

        TreeNode midNode = getMidNode(head);
        TreeNode prev = midNode.left;
        TreeNode forw = midNode.right;

        midNode.left = null;
        midNode.right = null;
        forw.left = null;

        if(prev != nullptr){
            prev.right = null;
        }
        TreeNode root = midNode;
        TreeNode leftHead = (prev != nullptr ? head : nullptr);
        TreeNode rightHead = forw;

        root.left = dllToBST(leftHead);
        root.right = dllToBST(rightHead);

        return root;
    }

    TreeNode mergeTwoSortedDLL(TreeNode l1, TreeNode l2){
        if(l1 == null || l2 == null){
            return l1 != null ? l1 : l2;
        }

        TreeNode dummy = new TreeNode(-1);
        TreeNode prev = dummy;
        TreeNode c1 = l1;
        TreeNode c2 = l2;

        while(c1 != null && c2 != null){
            if(c1.val <= c2.val){
                prev.right = c1;
                c1.right = prev;

                c1 = c1.right;
            }
            else {
                prev.right = c2;
                c2.right = prev;

                c2 = c2.right;
            }
            prev = prev.right;
        }
        if(c1 != null){
            prev.right = c1;
            c1.left = prev;
        }
        else {
            prev.right = c2;
            c2.left = prev;
        }

        TreeNode head = dummy.right;
        dummy.right = head.left = null;

        return head;
    }

    TreeNode mergeSort(TreeNode head)
    {
        if (head == null || head.right == null)
            return head;

        TreeNode midNode = getMidNode(head);
        TreeNode forwHead = midNode.right;
        forwHead.left = midNode.right = null;

        return mergeTwoSortedDLL(mergeSort(head), mergeSort(forwHead));
    }
    void display(TreeNode *node)
    {
        if (node == nullptr)
            return;

        string str = "";
        str += ((node->left != nullptr ? to_string(node->left->val) : "."));
        str += (" -> " + to_string(node->val) + " <- ");
        str += ((node->right != nullptr ? to_string(node->right->val) : "."));

        cout << str << endl;

        display(node->left);
        display(node->right);
    }

    TreeNode BTToBST(TreeNode root)
    {
        if (root == null)
            return null;

        TreeNode head = dll(root); // Binary Tree to Doubly Lihnked List
        head = mergeSort(head);     // doubly Linked List to Sorted Doubly Linked List
        root = dllToBST(head);

        return root;
    }

    TreeNode bstFromPostorder(int[] postorder, int lr, int rr, int[] idx)
    {
        int i = idx[0];
        if (i <= -1 || postorder[i] < lr || postorder[i] > rr)
            return null;

        TreeNode root = new TreeNode(postorder[i]);
        idx[0]--;

        root.right = bstFromPostorder(postorder, root.val, rr, idx);
        root.left = bstFromPostorder(postorder, lr, root.val, idx);

        return root;
    }

    public static TreeNode buildTreeFromPreIn(int[] preorder, int psi, int pei, int[] inorder, int isi, int iei){
        if(psi > pei){
            return null;
        }

        int idx = isi;
        while(inorder[idx] != preorder[psi]){
            idx++;
        }
        int tnel = idx - isi;
        TreeNode root = new TreeNode(preorder[psi]);

        root.left = buildTree(preorder, psi+1, psi+tnel, inorder, isi, idx - 1);
        root.right = buildTree(preorder, psi+tnel+1, pei, inorder, idx+1, iei);

        return root;
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        return buildTree(preorder, 0, n - 1, inorder, 0, n - 1);
    }

    public static TreeNode buildTreeFromPostAndIn(int[] postOrder, int psi, pei, int[] inorder, int isi, int iei){
        if(psi > pei){
            return null;
        }
        int idx = isi;
        while(inorder[idx] != postOrder[pei]){
            idx++;
        }
        int tnel = idx - isi;
        TreeNode root = new TreeNode(postOrder[pei]);

        root.left = buildTreeFromPostAndIn(postOrder, psi, psi+tnel-1, inorder, isi, idx-1);
        root.right = buildTreeFromPostAndIn(postOrder, psi + tnel, pei-1, inorder, idx+1, iei);

        return root;
    }
    public static TreeNode buildTreeFromPostAndIn(int[] inorder, int[] postorder) {
        int n = inorder.length;
        return buildTreeFromPostAndIn(postorder, 0, n - 1, inorder, 0, n - 1);
    }

    public static TreeNode constructFromPrePost(int[] postOrder, int ppsi, int ppei, int[] preOrder, int psi, int pei) {
        if (psi > pei)
            return null;

        TreeNode root = new TreeNode(preOrder[psi]);
        if (psi == pei)
            return root;

        int idx = ppsi;
        while (postOrder[idx] != preOrder[psi + 1])
            idx++;

        int tnel = idx - ppsi + 1;
        root.left = constructFromPrePost(postOrder, ppsi, ppsi + tnel - 1, preOrder, psi + 1, psi + tnel);
        root.right = constructFromPrePost(postOrder, ppsi + tnel, pei - 1, preOrder, psi + tnel + 1, pei);

        return root;
    }

    public static TreeNode constructFromPrePost(int[] preOrder, int[] postorder) {
        int n = postorder.length;
        return constructFromPrePost(postorder, 0, n - 1, preOrder, 0, n - 1);
    }

    public class CodecBTree {

        public void serialize(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append("# ");
                return;
            }

            sb.append(root.val + " ");
            serialize(root.left, sb);
            serialize(root.right, sb);
        }

        public String serialize(TreeNode root) {
            if (root == null)
                return "";
            StringBuilder sb = new StringBuilder();
            serialize(root, sb);

            return sb.toString();
        }

        int idx = 0;

        public TreeNode deserialize(String[] arr) {
            if (idx >= arr.length || arr[idx].equals("#")) {
                idx++;
                return null;
            }

            TreeNode root = new TreeNode(Integer.parseInt(arr[idx++]));
            root.left = deserialize(arr);
            root.right = deserialize(arr);

            return root;
        }

        public TreeNode deserialize(String data) {
            if (data.length() == 0)
                return null;

            idx = 0;
            String[] arr = data.split(" ");
            return deserialize(arr);
        }
    }
}
