/**
 * Author: 王俊超
 * Date: 2015-06-11
 * Time: 14:35
 * Declaration: All Rights Reserved !!!
 */
public class Test34 {
    /**
     * 判断一个数是否只有2，3，5因子（丑数）
     *
     * @param num 待判断的数，非负
     * @return true是丑数，false丑数
     */
    private static boolean isUgly(int num) {
        while (num % 2 == 0) {
            num /= 2;
        }

        while (num % 3 == 0) {
            num /= 3;
        }

        while (num % 5 == 0) {
            num /= 5;
        }

        return num == 1;
    }

    /**
     * 找第index个丑数，速度太慢
     *
     * @param index 第index个丑数
     * @return 对应的丑数值
     */
    public static int getUglyNumber(int index) {
        if (index <= 0) {
            return 0;
        }

        int num = 0;
        int uglyFound = 0;
        while (uglyFound < index) {
            num++;
            if (isUgly(num)) {
                ++uglyFound;
            }
        }

        return num;
    }

    /**
     * 找第index个丑数，【第二种方法】
     *
     * @param index 第index个丑数
     * @return 对应的丑数值
     */
    public static int getUglyNumber2(int index) {
        if (index <= 0) {
            return 0;
        }

        int[] pUglyNumbers = new int[index];
        pUglyNumbers[0] = 1;
        int nextUglyIndex = 1;

        int p2 = 0;
        int p3 = 0;
        int p5 = 0;

        while (nextUglyIndex < index) {
            int min = min(pUglyNumbers[p2] * 2, pUglyNumbers[p3] * 3, pUglyNumbers[p5] * 5);
            pUglyNumbers[nextUglyIndex] = min;

            while (pUglyNumbers[p2] * 2 <= pUglyNumbers[nextUglyIndex]) {
                p2++;
            }

            while (pUglyNumbers[p3] * 3 <= pUglyNumbers[nextUglyIndex]) {
                p3++;
            }

            while (pUglyNumbers[p5] * 5 <= pUglyNumbers[nextUglyIndex]) {
                p5++;
            }

            nextUglyIndex++;
        }

        return pUglyNumbers[nextUglyIndex - 1];
    }

    private static int min(int n1, int n2, int n3) {
        int min = n1 < n2 ? n1 : n2;
        return min < n3 ? min : n3;
    }


    public static void main(String[] args) {
        System.out.println("Solution 1:");
        test1();
        System.out.println();

        System.out.println("Solution 2:");
        test2();
    }

    private static void test1() {
        System.out.println(getUglyNumber(1)); // 1
        System.out.println(getUglyNumber(2)); // 2
        System.out.println(getUglyNumber(3)); // 3
        System.out.println(getUglyNumber(4)); // 4
        System.out.println(getUglyNumber(5)); // 5
        System.out.println(getUglyNumber(6)); // 6
        System.out.println(getUglyNumber(7)); // 8
        System.out.println(getUglyNumber(8)); // 9
        System.out.println(getUglyNumber(9)); // 10
        System.out.println(getUglyNumber(10)); // 12
        System.out.println(getUglyNumber(11)); // 15
        System.out.println(getUglyNumber(1500)); // 859963392
        System.out.println(getUglyNumber(0)); // 0
    }

    private static void test2() {
        System.out.println(getUglyNumber2(1)); // 1
        System.out.println(getUglyNumber2(2)); // 2
        System.out.println(getUglyNumber2(3)); // 3
        System.out.println(getUglyNumber2(4)); // 4
        System.out.println(getUglyNumber2(5)); // 5
        System.out.println(getUglyNumber2(6)); // 6
        System.out.println(getUglyNumber2(7)); // 8
        System.out.println(getUglyNumber2(8)); // 9
        System.out.println(getUglyNumber2(9)); // 10
        System.out.println(getUglyNumber2(10)); // 12
        System.out.println(getUglyNumber2(11)); // 15
        System.out.println(getUglyNumber2(1500)); // 859963392
        System.out.println(getUglyNumber2(0)); // 0
    }
}
