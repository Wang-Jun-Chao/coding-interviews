import java.util.ArrayList;
import java.util.List;

/**
 * Author: 王俊超
 * Date: 2015-04-24
 * Time: 13:45
 * Declaration: All Rights Reserved !!!
 */
public class Test25 {
    /**
     * 二叉树的树结点
     */
    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }

    /**
     * 输入一棵二叉树和一个整数， 打印出二叉树中结点值的和为输入整数的所有路径。
     * 从树的根结点开始往下一直到叶销点所经过的结点形成一条路径。
     *
     * @param root        树的根结点
     * @param expectedSum 要求的路径和
     */
    public static void findPath(BinaryTreeNode root, int expectedSum) {
        // 创建一个链表，用于存放根结点到当前处理结点的所经过的结点
        List<Integer> list = new ArrayList<>();

        // 如果根结点不为空，就调用辅助处理方法
        if (root != null) {
            findPath(root, 0, expectedSum, list);
        }
    }

    /**
     * @param root        当前要处理的结点
     * @param curSum      当前记录的和（还未加上当前结点的值）
     * @param expectedSum 要求的路径和
     * @param result      根结点到当前处理结点的所经过的结点，（还未包括当前结点）
     */
    public static void findPath(BinaryTreeNode root, int curSum, int expectedSum, List<Integer> result) {

        // 如果结点不为空就进行处理
        if (root != null) {
            // 加上当前结点的值
            curSum += root.value;
            // 将当前结点入队
            result.add(root.value);
            // 如果当前结点的值小于期望的和
            if (curSum < expectedSum) {
                // 递归处理左子树
                findPath(root.left, curSum, expectedSum, result);
                // 递归处理右子树
                findPath(root.right, curSum, expectedSum, result);
            }
            // 如果当前和与期望的和相等
            else if (curSum == expectedSum) {
                // 当前结点是叶结点，则输出结果
                if (root.left == null && root.right == null) {
                    System.out.println(result);
                }
            }
            // 移除当前结点
            result.remove(result.size() - 1);
        }
    }

    public static void main(String[] args) {
        //            10
        //         /      \
        //        5        12
        //       /\
        //      4  7
        BinaryTreeNode root = new BinaryTreeNode();
        root.value = 10;
        root.left = new BinaryTreeNode();
        root.left.value = 5;
        root.left.left = new BinaryTreeNode();
        root.left.left.value = 4;
        root.left.right = new BinaryTreeNode();
        root.left.right.value = 7;
        root.right = new BinaryTreeNode();
        root.right.value = 12;

        // 有两条路径上的结点和为22
        System.out.println("findPath(root, 22);");
        findPath(root, 22);

        // 没有路径上的结点和为15
        System.out.println("findPath(root, 15);");
        findPath(root, 15);

        // 有一条路径上的结点和为19
        System.out.println("findPath(root, 19);");
        findPath(root, 19);


        //               5
        //              /
        //             4
        //            /
        //           3
        //          /
        //         2
        //        /
        //       1
        BinaryTreeNode root2 = new BinaryTreeNode();
        root2.value = 5;
        root2.left = new BinaryTreeNode();
        root2.left.value = 4;
        root2.left.left = new BinaryTreeNode();
        root2.left.left.value = 3;
        root2.left.left.left = new BinaryTreeNode();
        root2.left.left.left.value = 2;
        root2.left.left.left.left = new BinaryTreeNode();
        root2.left.left.left.left.value = 1;

        // 有一条路径上面的结点和为15
        System.out.println("findPath(root2, 15);");
        findPath(root2, 15);

        // 没有路径上面的结点和为16
        System.out.println("findPath(root2, 16);");
        findPath(root2, 16);

        // 1
        //  \
        //   2
        //    \
        //     3
        //      \
        //       4
        //        \
        //         5
        BinaryTreeNode root3 = new BinaryTreeNode();
        root3.value = 1;
        root3.right = new BinaryTreeNode();
        root3.right.value = 2;
        root3.right.right = new BinaryTreeNode();
        root3.right.right.value = 3;
        root3.right.right.right = new BinaryTreeNode();
        root3.right.right.right.value = 4;
        root3.right.right.right.right = new BinaryTreeNode();
        root3.right.right.right.right.value = 5;

        // 有一条路径上面的结点和为15
        System.out.println("findPath(root3, 15);");
        findPath(root3, 15);

        // 没有路径上面的结点和为16
        System.out.println("findPath(root3, 16);");
        findPath(root3, 16);

        // 树中只有1个结点
        BinaryTreeNode root4 = new BinaryTreeNode();

        root4.value = 1;
        // 有一条路径上面的结点和为1
        System.out.println("findPath(root4, 1);");
        findPath(root4, 1);

        // 没有路径上面的结点和为2
        System.out.println("findPath(root4, 2);");
        findPath(root4, 2);

        // 树中没有结点
        System.out.println("findPath(null, 0);");
        findPath(null, 0);
    }
}
