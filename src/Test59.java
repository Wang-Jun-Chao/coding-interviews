/**
 * Author: Íõ¿¡³¬
 * Date: 2015-06-16
 * Time: 11:08
 * Declaration: All Rights Reserved !!!
 */
public class Test59 {
    private static class BinaryTreeNode {
        private int val;
        private BinaryTreeNode left;
        private BinaryTreeNode right;

        public BinaryTreeNode() {
        }

        public BinaryTreeNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return val + "";
        }
    }

    public static boolean isSymmetrical(BinaryTreeNode root) {
        return isSymmetrical(root, root);
    }

    private static boolean isSymmetrical(BinaryTreeNode left, BinaryTreeNode right) {

        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null) {
            return false;
        }

        if (left.val != right.val ) {
            return false;
        }

        return isSymmetrical(left.left, right.right) && isSymmetrical(left.right, right.left);
    }

    public static void main(String[] args) {
        test01();
        test02();
    }

    private static void assemble(BinaryTreeNode node,
                                 BinaryTreeNode left,
                                 BinaryTreeNode right) {
        node.left = left;
        node.right = right;
    }

    //                            1
    //                  2                   2
    //             4         6          6          4
    //          8     9   10   11   11     10   9     8
    public static void test01() {
        BinaryTreeNode n1 = new BinaryTreeNode(1);
        BinaryTreeNode n2 = new BinaryTreeNode(2);
        BinaryTreeNode n3 = new BinaryTreeNode(2);
        BinaryTreeNode n4 = new BinaryTreeNode(4);
        BinaryTreeNode n5 = new BinaryTreeNode(6);
        BinaryTreeNode n6 = new BinaryTreeNode(6);
        BinaryTreeNode n7 = new BinaryTreeNode(4);
        BinaryTreeNode n8 = new BinaryTreeNode(8);
        BinaryTreeNode n9 = new BinaryTreeNode(9);
        BinaryTreeNode n10 = new BinaryTreeNode(10);
        BinaryTreeNode n11 = new BinaryTreeNode(11);
        BinaryTreeNode n12 = new BinaryTreeNode(11);
        BinaryTreeNode n13 = new BinaryTreeNode(10);
        BinaryTreeNode n14 = new BinaryTreeNode(9);
        BinaryTreeNode n15 = new BinaryTreeNode(8);

        assemble(n1, n2, n3);
        assemble(n2, n4, n5);
        assemble(n3, n6, n7);
        assemble(n4, n8, n9);
        assemble(n5, n10, n11);
        assemble(n6, n12, n13);
        assemble(n7, n14, n15);
        assemble(n8, null, null);
        assemble(n9, null, null);
        assemble(n10, null, null);
        assemble(n11, null, null);
        assemble(n12, null, null);
        assemble(n13, null, null);
        assemble(n14, null, null);
        assemble(n15, null, null);

        System.out.println(isSymmetrical(n1));

    }


    //                            1
    //                  2                   2
    //             4         5          6          4
    //          8     9   10   11   11     10   9     8
    public static void test02() {
        BinaryTreeNode n1 = new BinaryTreeNode(1);
        BinaryTreeNode n2 = new BinaryTreeNode(2);
        BinaryTreeNode n3 = new BinaryTreeNode(2);
        BinaryTreeNode n4 = new BinaryTreeNode(4);
        BinaryTreeNode n5 = new BinaryTreeNode(5);
        BinaryTreeNode n6 = new BinaryTreeNode(6);
        BinaryTreeNode n7 = new BinaryTreeNode(4);
        BinaryTreeNode n8 = new BinaryTreeNode(8);
        BinaryTreeNode n9 = new BinaryTreeNode(9);
        BinaryTreeNode n10 = new BinaryTreeNode(10);
        BinaryTreeNode n11 = new BinaryTreeNode(11);
        BinaryTreeNode n12 = new BinaryTreeNode(11);
        BinaryTreeNode n13 = new BinaryTreeNode(10);
        BinaryTreeNode n14 = new BinaryTreeNode(9);
        BinaryTreeNode n15 = new BinaryTreeNode(8);

        assemble(n1, n2, n3);
        assemble(n2, n4, n5);
        assemble(n3, n6, n7);
        assemble(n4, n8, n9);
        assemble(n5, n10, n11);
        assemble(n6, n12, n13);
        assemble(n7, n14, n15);
        assemble(n8, null, null);
        assemble(n9, null, null);
        assemble(n10, null, null);
        assemble(n11, null, null);
        assemble(n12, null, null);
        assemble(n13, null, null);
        assemble(n14, null, null);
        assemble(n15, null, null);

        System.out.println(isSymmetrical(n1));

    }
}
