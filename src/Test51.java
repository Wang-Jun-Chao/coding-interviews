/**
 * Author: 王俊超
 * Date: 2015-06-15
 * Time: 08:36
 * Declaration: All Rights Reserved !!!
 */
public class Test51 {
    /**
     * 题目：在一个长度为n的数组里的所有数字都在0到n-1的范围内。数组中某些数字是重复的，
     * 但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
     * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是重复的数字2或者。
     *
     * @param number
     * @return
     */
    public static int duplicate(int[] number) {
        if (number == null || number.length < 1) {
            return -1;
        }

        // 判断输入的是否在[0, number.length-1]之间
        for (int i : number) {
            if (i < 0 || i >= number.length) {
                return -1;
            }
        }

        for (int i = 0; i < number.length; i++) {
            // 当number[i]与i不相同的时候一直交换
            while (number[i] != i) {
                // 如果i位置与number[i]位置的数字相同，说明有重复数字
                if (number[i] == number[number[i]]) {
                    return number[i];
                }
                // 如果不同就交换
                else {
                    swap(number, i, number[i]);
                }
            }
        }
        return -1;
    }

    private static void swap(int[] data, int x, int y) {
        int tmp = data[x];
        data[x] = data[y];
        data[y] = tmp;
    }

    public static void main(String[] args) {
        int[] numbers1 = {2, 1, 3, 1, 4};
        System.out.println(duplicate(numbers1));

        int[] numbers2 = {2, 4, 3, 1, 4};
        System.out.println(duplicate(numbers2));

        int[] numbers3 = {2, 4, 2, 1, 4};
        System.out.println(duplicate(numbers3));

        int[] numbers4 = {2, 1, 3, 0, 4};
        System.out.println(duplicate(numbers4));

        int[] numbers5 = {2, 1, 3, 5, 4};
        System.out.println(duplicate(numbers5));
    }
}
