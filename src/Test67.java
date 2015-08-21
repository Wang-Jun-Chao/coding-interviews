/**
 * Author: 王俊超
 * Date: 2015-06-17
 * Time: 19:19
 * Declaration: All Rights Reserved !!!
 */
public class Test67 {
    /**
     * 题目：地上有个m行n列的方格。一个机器人从坐标(0,0)的格子开始移动，
     * 它每一次可以向左、右、上、下移动一格，但不能进入行坐标和列坐标的数
     * 位之和大于k的格子。例如，当k为18时，机器人能够进入方格(35,37)，
     * 因为3+5+3+7=18.但它不能进入方格(35,38)，因为3+5+3+8=19.
     * 请问该机器人能够达到多少格子？
     *
     * @param threshold 约束值
     * @param rows      方格的行数
     * @param cols      方格的列数
     * @return 最多可走的方格
     */
    public static int movingCount(int threshold, int rows, int cols) {
        // 参数校验
        if (threshold < 0 || rows < 1 || cols < 1) {
            return 0;
        }

        // 变量初始化
        boolean[] visited = new boolean[rows * cols];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }

        return movingCountCore(threshold, rows, cols, 0, 0, visited);
    }

    /**
     * 递归回溯方法
     *
     * @param threshold 约束值
     * @param rows      方格的行数
     * @param cols      方格的列数
     * @param row       当前处理的行号
     * @param col       当前处理的列号
     * @param visited   访问标记数组
     * @return 最多可走的方格
     */
    private static int movingCountCore(int threshold, int rows, int cols,
                                       int row, int col, boolean[] visited) {

        int count = 0;

        if (check(threshold, rows, cols, row, col, visited)) {
            visited[row * cols + col] = true;
            count = 1
                    + movingCountCore(threshold, rows, cols, row - 1, col, visited)
                    + movingCountCore(threshold, rows, cols, row, col - 1, visited)
                    + movingCountCore(threshold, rows, cols, row + 1, col, visited)
                    + movingCountCore(threshold, rows, cols, row, col + 1, visited);
        }

        return count;
    }

    /**
     * 断机器人能否进入坐标为(row, col)的方格
     *
     * @param threshold 约束值
     * @param rows      方格的行数
     * @param cols      方格的列数
     * @param row       当前处理的行号
     * @param col       当前处理的列号
     * @param visited   访问标记数组
     * @return 是否可以进入，true是，false否
     */
    private static boolean check(int threshold, int rows, int cols,
                                 int row, int col, boolean[] visited) {
        return col >= 0 && col < cols
                && row >= 0 && row < rows
                && !visited[row * cols + col]
                && (getDigitSum(col) + getDigitSum(row) <= threshold);
    }

    /**
     * 一个数字的数位之和
     *
     * @param number 数字
     * @return 数字的数位之和
     */
    private static int getDigitSum(int number) {
        int result = 0;
        while (number > 0) {
            result += (number % 10);
            number /= 10;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(movingCount(5, 10, 10) + "[21]");
        System.out.println(movingCount(15, 20, 20) + "[359]");
        System.out.println(movingCount(10, 1, 100) + "[29]");
        System.out.println(movingCount(10, 1, 10) + "[10]");
        System.out.println(movingCount(15, 100, 1) + "[79]");
        System.out.println(movingCount(15, 10, 1) + "[10]");
        System.out.println(movingCount(5, 10, 10) + "[21]");
        System.out.println(movingCount(12, 1, 1) + "[1]");
        System.out.println(movingCount(-10, 10, 10) + "[0]");
    }
}
