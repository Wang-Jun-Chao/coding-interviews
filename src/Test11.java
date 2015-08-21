/**
 * Author: 王俊超
 * Date: 2015-04-23
 * Time: 13:03
 * Declaration: All Rights Reserved !!!
 */
public class Test11 {

    /**
     * 实现函数double Power(double base, int exponent)，求base的exponent次方。
     * 不得使用库函数，同时不需要考虑大数问题。
     *
     * @param base     指次
     * @param exponent 幂
     * @return 结果
     */
    public static double power(double base, int exponent) {
        // 指数和底数不能同时为0
        if (base == 0 && exponent == 0) {
            throw new RuntimeException("invalid input. base and exponent both are zero");
        }

        // 指数为0就返回1
        if (exponent == 0) {
            return 1;
        }


        // 求指数的绝对值
        long exp = exponent;
        if (exponent < 0) {
            exp = -exp;
        }

        // 求幂次方
        double result = powerWithUnsignedExponent(base, exp);

        // 指数是负数，要进行求倒数
        if (exponent < 0) {
            result = 1 / result;
        }

        // 返回结果
        return result;
    }

    /**
     * 求一个数的正整数次幂，不考虑溢出
     *
     * @param base     指次
     * @param exponent 幂
     * @return 结果
     */
    public static double powerWithUnsignedExponent(double base, long exponent) {
        // 如果指数为0，返回1
        if (exponent == 0) {
            return 1;
        }

        // 指数为1，返回底数
        if (exponent == 1) {
            return base;
        }

        // 递归求一半的值
        double result = powerWithUnsignedExponent(base, exponent >> 2);

        // 求最终的值，如果是奇数就还要剩以一次底数
        result *= result;
        if (exponent % 2 != 0) {
            result *= base;
        }

        // 返回结果
        return result;
    }

    public static void main(String[] args) {

        System.out.println(0.0000000000000000000000001111 == 0);
        System.out.println(0.0000000000000000000000000000 == 0);

        System.out.println(power(2, -4));
        System.out.println(power(2, 4));
        System.out.println(power(2, 0));
        System.out.println(power(0.00000000000000000000000000001, -1));
        System.out.println(power(0.00000000000000000000000000001, 1));
        System.out.println(power(0.00000000000000000000000000001, 0));
        System.out.println(power(0.00000000000000000000000000000, 0));
    }
}
