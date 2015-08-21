import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Author: 王俊超
 * Date: 2015-06-17
 * Time: 07:56
 * Declaration: All Rights Reserved !!!
 */
public class Test64 {
    private static class Heap<T> {
        // 堆中元素存放的集合
        private List<T> data;
        // 比较器
        private Comparator<T> cmp;

        /**
         * 构造函数
         *
         * @param cmp 比较器对象
         */
        public Heap(Comparator<T> cmp) {
            this.cmp = cmp;
            this.data = new ArrayList<>(64);
        }

        /**
         * 向上调整堆
         *
         * @param idx 被上移元素的起始位置
         */
        public void shiftUp(int idx) {
            // 检查是位置是否正确
            if (idx < 0 || idx >= data.size()) {
                throw new IllegalArgumentException(idx + "");
            }

            // 获取开始调整的元素对象
            T intent = data.get(idx);

            // 如果不是根元素，则需要上移
            while (idx > 0) {
                // 找父元素对象的位置
                int parentIdx = (idx - 1) / 2;
                // 获取父元素对象
                T parent = data.get(parentIdx);
                //上移的条件，子节点比父节点大，此处定义的大是以比较器返回值为准
                if (cmp.compare(intent, parent) > 0) {
                    // 将父节点向下放
                    data.set(idx, parent);
                    idx = parentIdx;
                    // 记录父节点下放的位置
                }
                // 子节点不比父节点大，说明父子路径已经按从大到小排好顺序了，不需要调整了
                else {
                    break;
                }
            }

            // index此时记录是的最后一个被下放的父节点的位置（也可能是自身），
            // 所以将最开始的调整的元素值放入index位置即可
            data.set(idx, intent);
        }

        /**
         * 向下调整堆
         *
         * @param idx 被下移的元素的起始位置
         */
        public void shiftDown(int idx) {
            // 检查是位置是否正确
            if (idx < 0 || idx >= data.size()) {
                throw new IllegalArgumentException(idx + "");
            }

            // 获取开始调整的元素对象
            T intent = data.get(idx);
            // 获取开始调整的元素对象的左子结点的元素位置
            int leftIdx = idx * 2 + 1;
            // 如果有左子结点
            while (leftIdx < data.size()) {
                // 取左子结点的元素对象，并且假定其为两个子结点中最大的
                T maxChild = data.get(leftIdx);
                // 两个子节点中最大节点元素的位置，假定开始时为左子结点的位置
                int maxIdx = leftIdx;

                // 获取右子结点的位置
                int rightIdx = leftIdx + 1;
                // 如果有右子结点
                if (rightIdx < data.size()) {
                    T rightChild = data.get(rightIdx);
                    // 找出两个子节点中的最大子结点
                    if (cmp.compare(rightChild, maxChild) > 0) {
                        maxChild = rightChild;
                        maxIdx = rightIdx;
                    }
                }

                // 如果最大子节点比父节点大，则需要向下调整
                if (cmp.compare(maxChild, intent) > 0) {
                    // 将较大的子节点向上移
                    data.set(idx, maxChild);
                    // 记录上移节点的位置
                    idx = maxIdx;
                    // 找到上移节点的左子节点的位置
                    leftIdx = 2 * idx + 1;
                }
                // 最大子节点不比父节点大，说明父子路径已经按从大到小排好顺序了，不需要调整了
                else {
                    break;
                }

            }
            // index此时记录是的最后一个被上移的子节点的位置（也可能是自身），
            // 所以将最开始的调整的元素值放入index位置即可
            data.set(idx, intent);
        }

        /**
         * 添加一个元素
         *
         * @param item 添加的元素
         */
        public void add(T item) {
            // 将元素添加到最后
            data.add(item);
            // 上移，以完成重构
            shiftUp(data.size() - 1);
        }

        /**
         * 删除堆顶结点
         *
         * @return 堆顶结点
         */
        public T deleteTop() {
            // 如果堆已经为空，就抛出异常
            if (data.isEmpty()) {
                throw new RuntimeException("The heap is empty.");
            }

            // 获取堆顶元素
            T first = data.get(0);
            // 删除最后一个元素
            T last = data.remove(data.size() - 1);

            // 删除元素后，如果堆为空的情况，说明删除的元素也是堆顶元素
            if (data.size() == 0) {
                return last;
            } else {
                // 将删除的元素放入堆顶
                data.set(0, last);
                // 自上向下调整堆
                shiftDown(0);
                // 返回堆顶元素
                return first;
            }
        }

        /**
         * 获取堆顶元素，但不删除
         *
         * @return 堆顶元素
         */
        public T getTop() {
            // 如果堆已经为空，就抛出异常
            if (data.isEmpty()) {
                throw new RuntimeException("The heap is empty.");
            }

            return data.get(0);
        }

        /**
         * 获取堆的大小
         *
         * @return 堆的大小
         */
        public int size() {
            return data.size();
        }

        /**
         * 判断堆是否为空
         *
         * @return 堆是否为空
         */
        public boolean isEmpty() {
            return data.isEmpty();
        }

        /**
         * 清空堆
         */
        public void clear() {
            data.clear();
        }

        /**
         * 获取堆中所有的数据
         *
         * @return 堆中所在的数据
         */
        public List<T> getData() {
            return data;
        }
    }

    /**
     * 升序比较器
     */
    private static class IncComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    /**
     * 降序比较器
     */
    private static class DescComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    private static class DynamicArray {
        private Heap<Integer> max;
        private Heap<Integer> min;

        public DynamicArray() {
            max = new Heap<>(new IncComparator());
            min = new Heap<>(new DescComparator());
        }

        /**
         * 插入数据
         *
         * @param num 待插入的数据
         */
        public void insert(Integer num) {
            // 已经有偶数个数据了（可能没有数据）
            // 数据总数是偶数个时把新数据插入到小堆中
            if ((min.size() + max.size()) % 2 == 0) {
                // 大堆中有数据，并且插入的元素比大堆中的元素小
                if (max.size() > 0 && num < max.getTop()) {
                    // 将num加入的大堆中去
                    max.add(num);
                    // 删除堆顶元素，大堆中的最大元素
                    num = max.deleteTop();
                }

                // num插入到小堆中，当num小于大堆中的最大值进，
                // num就会变成大堆中的最大值，见上面的if操作
                // 如果num不小于大堆中的最大值，num就是自身
                min.add(num);
            }
            // 数据总数是奇数个时把新数据插入到大堆中
            else {
                // 小堆中有数据，并且插入的元素比小堆中的元素大
                if (min.size() > 0 && num > min.size()) {
                    // 将num加入的小堆中去
                    min.add(num);
                    // 删除堆顶元素，小堆中的最小元素
                    num = min.deleteTop();
                }
                // num插入到大堆中，当num大于小堆中的最小值进，
                // num就会变成小堆中的最小值，见上面的if操作
                // 如果num不大于大堆中的最小值，num就是自身
                max.add(num);
            }
        }

        public double getMedian() {
            int size = max.size() + min.size();

            if (size == 0) {
                throw new RuntimeException("No numbers are available");
            }

            if ((size & 1) == 1) {
                return min.getTop();
            } else {
                return (max.getTop() + min.getTop()) / 2.0;
            }
        }
    }

    public static void main(String[] args) {
        DynamicArray array = new DynamicArray();
        array.insert(5);
        System.out.println(array.getMedian()); // 5

        array.insert(2);
        System.out.println(array.getMedian()); // 3.5

        array.insert(3);
        System.out.println(array.getMedian()); // 3

        array.insert(4);
        System.out.println(array.getMedian()); // 3.5

        array.insert(1);
        System.out.println(array.getMedian()); // 3

        array.insert(6);
        System.out.println(array.getMedian()); // 3.5

        array.insert(7);
        System.out.println(array.getMedian()); // 4

        array.insert(0);
        System.out.println(array.getMedian()); // 3.5

        array.insert(8);
        System.out.println(array.getMedian()); // 4

    }
}
