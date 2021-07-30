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
}
