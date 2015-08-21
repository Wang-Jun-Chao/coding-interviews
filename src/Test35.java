import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Author: 王俊超
 * Date: 2015-06-11
 * Time: 16:46
 * Declaration: All Rights Reserved !!!
 */
public class Test35 {
    public static char firstNotRepeatingChar(String s) {
        if (s == null || s.length() < 1) {
            throw new IllegalArgumentException("Arg should not be null or empty");
        }

        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, -2);
            } else {
                map.put(c, i);
            }
        }

        Set<Map.Entry<Character, Integer>> entrySet = map.entrySet();
        // 记录只出现一次的字符的索引
        int idx = Integer.MAX_VALUE;
        // 记录只出现一次的字符
        char result = '\0';

        // 找最小索引对应的字符
        for (Map.Entry<Character, Integer> entry : entrySet) {
            if (entry.getValue() >= 0 && entry.getValue() < idx) {
                idx = entry.getValue();
                result = entry.getKey();
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(firstNotRepeatingChar("google")); // l
        System.out.println(firstNotRepeatingChar("aabccdbd")); // '\0'
        System.out.println(firstNotRepeatingChar("abcdefg")); // a
        System.out.println(firstNotRepeatingChar("gfedcba")); // g
        System.out.println(firstNotRepeatingChar("zgfedcba")); // g
    }
}
