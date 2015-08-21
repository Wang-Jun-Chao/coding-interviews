/**
 * Author: 王俊超
 * Date: 2015-04-23
 * Time: 16:50
 * Declaration: All Rights Reserved !!!
 */
public class Test16 {
    public static class ListNode {
        int value;
        ListNode next;
    }

    /**
     * 定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。
     *
     * @param head 链表的头结点
     * @return 反转后的链表的头结点
     */
    public static ListNode reverseList(ListNode head) {
        // 创建一个临时结点，当作尾插法的逻辑头结点
        ListNode root = new ListNode();
        // 逻辑头结点点的下一个结点为空
        root.next = null;

        // 用于记录要处理的下一个结点
        ListNode next;
        // 当前处理的结点不为空
        while (head != null) {
            // 记录要处理的下一个结点
            next = head.next;
            // 当前结点的下一个结点指向逻辑头结点的下一个结点
            head.next = root.next;
            // 逻辑头结点的下一个结点指向当前处理的结点
            root.next = head;
            // 上面操作完成了一个结点的头插

            // 当前结点指向下一个要处理的结点
            head = next;
        }

        // 逻辑头结点的下一个结点就是返回后的头结点
        return root.next;
    }

    /**
     * 定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。
     * 【书本上的方法，不使用逻辑头结点】
     *
     * @param head 链表的头结点
     * @return 反转后的链表的头结点
     */
    public static ListNode reverseList2(ListNode head) {
        // 用于记录反转后的链表的头结点
        ListNode reverseHead = null;
        // 用于记录当前处理的结点的
        ListNode curr = head;
        // 用于记录当前结点的前驱结点
        // 前驱结点开始为null，因为了是反转后的最后一个结点的下一个结点，即null
        ListNode prev = null;
        // 当前结点的下一个结点
        ListNode next;

        // 对链表进行尾插法操作
        while (curr != null) {
            // 记录当前处理的结点，最后一个记录的结点就是反转后的头结点
            // 【注意：与书上的不同，因为curr.next=null时，curr此时就最后一个处理的结点，
            // 对应到反转后的链表就是第一个结点，书上那样做更精确，只是多了一些判断，可以不要if】
            reverseHead = curr;
            // 记录当然前下一个结点
            next = curr.next;
            // 当前结点的下一个结点指向前驱结点，这样当前结点就插入到了反转链表的头部
            curr.next = prev;
            // 记录当前结点为前驱结点
            prev = curr;
            // 当前结点点移动到下一个结点
            curr = next;
        }

        // 返回转后的头结点
        return reverseHead;
    }


    /**
     * 输出链表的元素值
     *
     * @param head 链表的头结点
     */
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.value + "->");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        ListNode head = new ListNode();
        head.value = 1;

        head.next = new ListNode();
        head.next.value = 2;

        head.next.next = new ListNode();
        head.next.next.value = 3;

        head.next.next.next = new ListNode();
        head.next.next.next.value = 4;

        head.next.next.next.next = new ListNode();
        head.next.next.next.next.value = 5;

        head.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.value = 6;

        head.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.value = 7;

        head.next.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.next.value = 8;

        head.next.next.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.next.next.value = 9;

        printList(head);
        head = reverseList(head);
        printList(head);
        head = reverseList2(head);
        printList(head);

    }
}
