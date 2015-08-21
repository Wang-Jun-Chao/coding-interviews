/**
 * Author: 王俊超
 * Date: 2015-06-11
 * Time: 09:22
 * Declaration: All Rights Reserved !!!
 */
public class Test32 {

    /**
     * 题目：输入一个整数n求从1 到n这n个整数的十进制表示中1 出现的次数。
     * @param n 最大的数字
     * @return 1-n中，各个数位1出现的次数
     */
    public static int numberOf1Between1AndN(int n) {
        if (n <= 0) {
            return 0;
        }

        String value = n + "";
        int[] numbers = new int[value.length()];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = value.charAt(i) - '0';
        }

        return numberOf1(numbers, 0);
    }

    /**
     * 求0-numbers表的数字中的1的个数
     *
     * @param numbers 数字，如{1, 2, 3, 4, 5}表示数字12345
     * @param curIdx  当前处理的位置
     * @return 1的个数
     */
    private static int numberOf1(int[] numbers, int curIdx) {

        if (numbers == null || curIdx >= numbers.length || curIdx < 0) {
            return 0;
        }
        // 待处理的第一个数字
        int first = numbers[curIdx];

        // 要处理的数字的位数
        int length = numbers.length - curIdx;

        // 如果只有一位且这一位是0返回0
        if (length == 1 && first == 0) {
            return 0;
        }

        // 如果只有一位且这一位不是0返回1
        if (length == 1 && first > 0) {
            return 1;
        }

        // 假设numbers是21345
        // numFirstDigit是数字10000-19999的第一个位中的数目
        int numFirstDigit = 0;
        // 如果最高位不是1，如21345，在[1236, 21345]中，最高位1出现的只在[10000, 19999]中，出现1的次数是10^4方个
        if (first > 1) {
            numFirstDigit = powerBase10(length - 1);
        }
        // 如果最高位是1，如12345，在[2346, 12345]中，最高位1出现的只在[10000, 12345]中，总计2345+1个
        else if (first == 1) {
            numFirstDigit = atoi(numbers, curIdx + 1) + 1;
        }

        // numOtherDigits，是[1346, 21345]中，除了第一位之外（不看21345中的第一位2）的数位中的1的数目
        int numOtherDigits = first * (length - 1) * powerBase10(length - 2);
        // numRecursive是1-1234中1的的数目
        int numRecursive = numberOf1(numbers, curIdx + 1);

        return numFirstDigit + numOtherDigits + numRecursive;
    }

    /**
     * 将数字数组转换成数值，如{1, 2, 3, 4, 5}，i = 2，结果是345
     * @param numbers 数组
     * @param i 开始黑气的位置
     * @return 转换结果
     */
    private static int atoi(int[] numbers, int i) {
        int result = 0;
        for (int j = i; j < numbers.length; j++) {
            result = (result * 10 + numbers[j]);
        }
        return result;
    }

    /**
     * 求10的n次方，假定n不为负数
     * @param n 幂，非负数
     * @return 10的n次方
     */
    private static int powerBase10(int n) {
        int result = 1;
        for (int i = 0; i < n; i++) {
            result *= 10;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(numberOf1Between1AndN(1)); // 1
        System.out.println(numberOf1Between1AndN(5)); // 1
        System.out.println(numberOf1Between1AndN(10)); // 2
        System.out.println(numberOf1Between1AndN(55)); // 16
        System.out.println(numberOf1Between1AndN(99)); // 20
        System.out.println(numberOf1Between1AndN(10000)); // 4001
        System.out.println(numberOf1Between1AndN(21345)); // 18821
        System.out.println(numberOf1Between1AndN(0)); // 0
    }
}
