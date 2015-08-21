import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: 王俊超
 * Date: 2015-06-10
 * Time: 13:32
 * Declaration: All Rights Reserved !!!
 */
public class Test30 {
    /**
     * 大顶堆
     *
     * @param <T> 参数化类型
     */
    private final static class MaxHeap<T extends Comparable<T>> {
        // 堆中元素存放的集合
        private List<T> items;
        // 用于计数
        private int cursor;

        /**
         * 构造一个椎，始大小是32
         */
        public MaxHeap() {
            this(32);
        }

        /**
         * 造诣一个指定初始大小的堆
         *
         * @param size 初始大小
         */
        public MaxHeap(int size) {
            items = new ArrayList<>(size);
            cursor = -1;
        }

        /**
         * 向上调整堆
         *
         * @param index 被上移元素的起始位置
         */
        public void siftUp(int index) {
            T intent = items.get(index); // 获取开始调整的元素对象

            while (index > 0) { // 如果不是根元素
                int parentIndex = (index - 1) / 2; // 找父元素对象的位置
                T parent = items.get(parentIndex);  // 获取父元素对象
                if (intent.compareTo(parent) > 0) { //上移的条件，子节点比父节点大
                    items.set(index, parent); // 将父节点向下放
                    index = parentIndex; // 记录父节点下放的位置
                } else { // 子节点不比父节点大，说明父子路径已经按从大到小排好顺序了，不需要调整了
                    break;
                }
            }

            // index此时记录是的最后一个被下放的父节点的位置（也可能是自身），所以将最开始的调整的元素值放入index位置即可
            items.set(index, intent);
        }

        /**
         * 向下调整堆
         *
         * @param index 被下移的元素的起始位置
         */
        public void siftDown(int index) {
            T intent = items.get(index);  // 获取开始调整的元素对象
            int leftIndex = 2 * index + 1; // // 获取开始调整的元素对象的左子结点的元素位置

            while (leftIndex < items.size()) { // 如果有左子结点
                T maxChild = items.get(leftIndex); // 取左子结点的元素对象，并且假定其为两个子结点中最大的
                int maxIndex = leftIndex; // 两个子节点中最大节点元素的位置，假定开始时为左子结点的位置

                int rightIndex = leftIndex + 1;  // 获取右子结点的位置
                if (rightIndex < items.size()) {  // 如果有右子结点
                    T rightChild = items.get(rightIndex);  // 获取右子结点的元素对象
                    if (rightChild.compareTo(maxChild) > 0) {  // 找出两个子节点中的最大子结点
                        maxChild = rightChild;
                        maxIndex = rightIndex;
                    }
                }

                // 如果最大子节点比父节点大，则需要向下调整
                if (maxChild.compareTo(intent) > 0) {
                    items.set(index, maxChild); // 将子节点向上移
                    index = maxIndex; // 记录上移节点的位置
                    leftIndex = index * 2 + 1; // 找到上移节点的左子节点的位置
                } else { // 最大子节点不比父节点大，说明父子路径已经按从大到小排好顺序了，不需要调整了
                    break;
                }
            }

            // index此时记录是的最后一个被上移的子节点的位置（也可能是自身），所以将最开始的调整的元素值放入index位置即可
            items.set(index, intent);
        }

        /**
         * 向堆中添加一个元素
         *
         * @param item 等待添加的元素
         */
        public void add(T item) {
            items.add(item); // 将元素添加到最后
            siftUp(items.size() - 1); // 循环上移，以完成重构
        }

        /**
         * 删除堆顶元素
         *
         * @return 堆顶部的元素
         */
        public T deleteTop() {
            if (items.isEmpty()) { // 如果堆已经为空，就报出异常
                throw new RuntimeException("The heap is empty.");
            }

            T maxItem = items.get(0); // 获取堆顶元素
            T lastItem = items.remove(items.size() - 1); // 删除最后一个元素
            if (items.isEmpty()) { // 删除元素后，如果堆为空的情况，说明删除的元素也是堆顶元素
                return lastItem;
            }

            items.set(0, lastItem); // 将删除的元素放入堆顶
            siftDown(0); // 自上向下调整堆
            return maxItem; // 返回堆顶元素
        }

        /**
         * 获取下一个元素
         *
         * @return 下一个元素对象
         */
        public T next() {

            if (cursor >= items.size()) {
                throw new RuntimeException("No more element");
            }
            return items.get(cursor);

        }

        /**
         * 判断堆中是否还有下一个元素
         *
         * @return true堆中还有下一个元素，false堆中无下五元素
         */
        public boolean hasNext() {
            cursor++;
            return cursor < items.size();
        }

        /**
         * 获取堆中的第一个元素
         *
         * @return 堆中的第一个元素
         */
        public T first() {
            if (items.size() == 0) {
                throw new RuntimeException("The heap is empty.");
            }
            return items.get(0);
        }

        /**
         * 判断堆是否为空
         *
         * @return true是，false否
         */
        public boolean isEmpty() {
            return items.isEmpty();
        }

        /**
         * 获取堆的大小
         *
         * @return 堆的大小
         */
        public int size() {
            return items.size();
        }

        /**
         * 清空堆
         */
        public void clear() {
            items.clear();
        }

        @Override
        public String toString() {
            return items.toString();
        }
    }

    /**
     * 题目： 输入n个整数，找出其中最小的k个数。
     * 【第二种解法】
     * @param input  输入数组
     * @param output 输出数组
     */
    public static void getLeastNumbers2(int[] input, int[] output) {
        if (input == null || output == null || output.length <= 0 || input.length < output.length) {
            throw new IllegalArgumentException("Invalid args");
        }

        MaxHeap<Integer> maxHeap = new MaxHeap<>(output.length);
        for (int i : input) {
            if (maxHeap.size() < output.length) {
                maxHeap.add(i);
            } else {
                int max = maxHeap.first();
                if (max > i) {
                    maxHeap.deleteTop();
                    maxHeap.add(i);
                }
            }
        }

        for (int i = 0; maxHeap.hasNext(); i++) {
            output[i] = maxHeap.next();
        }
    }


    /**
     * 题目： 输入n个整数，找出其中最小的k个数。
     * 【第一种解法】
     * @param input  输入数组
     * @param output 输出数组
     */
    public static void getLeastNumbers(int[] input, int[] output) {

        if (input == null || output == null || output.length <= 0 || input.length < output.length) {
            throw new IllegalArgumentException("Invalid args");
        }

        int start = 0;
        int end = input.length - 1;
        int index = partition(input, start, end);
        int target = output.length - 1;

        while (index != target) {
            if (index < target) {
                start = index + 1;
            } else {
                end = index - 1;
            }
            index = partition(input, start, end);
        }

        System.arraycopy(input, 0, output, 0, output.length);
    }

    /**
     * 分区算法
     *
     * @param input 输入数组
     * @param start 开始下标
     * @param end   结束下标
     * @return 分区位置
     */
    private static int partition(int[] input, int start, int end) {
        int tmp = input[start];

        while (start < end) {
            while (start < end && input[end] >= tmp) {
                end--;
            }
            input[start] = input[end];

            while (start < end && input[start] <= tmp) {
                start++;
            }
            input[end] = input[start];
        }

        input[start] = tmp;
        return start;
    }


    public static void main(String[] args) {
        System.out.println("第一种解法：");
        test1();
        System.out.println();
        System.out.println("第二种解法：");
        test2();

    }

    private static void test1() {
        int[] data = {4, 5, 1, 6, 2, 7, 3, 8};

        int[] output = new int[4];
        getLeastNumbers(data, output);
        for (int i : output) {
            System.out.print(i + " ");
        }
        System.out.println();

        int[] output2 = new int[8];
        getLeastNumbers(data, output2);
        for (int i : output2) {
            System.out.print(i + " ");
        }
        System.out.println();


        int[] output3 = new int[1];
        getLeastNumbers(data, output3);
        for (int i : output3) {
            System.out.print(i + " ");
        }
        System.out.println();


        int[] data2 = {4, 5, 1, 6, 2, 7, 2, 8};
        int[] output4 = new int[2];
        getLeastNumbers(data2, output4);
        for (int i : output4) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void test2() {
        int[] data = {4, 5, 1, 6, 2, 7, 3, 8};

        int[] output = new int[4];
        getLeastNumbers2(data, output);
        for (int i : output) {
            System.out.print(i + " ");
        }
        System.out.println();

        int[] output2 = new int[8];
        getLeastNumbers2(data, output2);
        for (int i : output2) {
            System.out.print(i + " ");
        }
        System.out.println();


        int[] output3 = new int[1];
        getLeastNumbers2(data, output3);
        for (int i : output3) {
            System.out.print(i + " ");
        }
        System.out.println();


        int[] data2 = {4, 5, 1, 6, 2, 7, 2, 8};
        int[] output4 = new int[2];
        getLeastNumbers2(data2, output4);
        for (int i : output4) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}


