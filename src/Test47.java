/**
 * Author: 王俊超
 * Date: 2015-06-14
 * Time: 16:07
 * Declaration: All Rights Reserved !!!
 */
public class Test47 {
    public static int add(int x, int y) {
        int sum;
        int carry;

        do {
            sum = x ^ y;
            // x&y的某一位是1说明，它是它的前一位的进位，所以向左移动一位
            carry = (x & y) << 1;

            x = sum;
            y = carry;
        } while (y != 0);

        return x;
    }


    public static void main(String[] args) {
        System.out.println(add(1, 2) + ", " + (1 + 2));
        System.out.println(add(13, 34)+ ", " + (13 + 34));
        System.out.println(add(19, 85)+ ", " + (19 + 95));
        System.out.println(add(865, 245)+ ", " + (865 + 245));
    }
}
