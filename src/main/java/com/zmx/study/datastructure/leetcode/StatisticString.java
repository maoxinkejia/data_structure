package com.zmx.study.datastructure.leetcode;

import java.util.HashMap;
import java.util.Map;

public class StatisticString {
    public static void main(String[] args) {
        String s = "AABCABBB";
        int k = 2;
        int i = characterReplacement(s, k);
        System.out.println(i);
//        String s = "abcabcdadefjkl";
//        String s = "abcabcbb";
//        System.out.println(lengthOfLongestSubstring(s));
    }

    /**
     * 最长不含重复字符的字符串
     * https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>(s.length());

        int left = 0,right = 0;
        int res = 0;
        while (right < s.length()) {
            if (map.containsKey(s.charAt(right))) {
                // 若当前right的字符已经存在，则将left指针右移，说明当前这个字符已经在后面存在了
                // 此时map对应当前字符的index还没有更新，是上一个当前字符的index，left指针偏移到index+1的位置重新计数
                left = Math.max(left, map.get(s.charAt(right)) + 1);
            }

            // 不论前面有没有重复的字符，都要放进去，更新字符最新出现的index
            map.put(s.charAt(right), right);

            // res为最大不重复的字符长度，若前面已经达到最大值，后面出现字符重复时，则取res和最新值的最大值
            // 若后面的字符为都不重复，则最新值会大于res，Math.max则会取最大值
            res = Math.max(res, right - left + 1);

            right++;
        }

        return res;
    }

    /**
     * 替换后的最长重复字符
     * https://leetcode-cn.com/problems/longest-repeating-character-replacement/
     * 输入：s = "AABABBA", k = 1
     * 输出：4
     * 解释：
     * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
     */
    public static int characterReplacement(String s, int k) {
        // 因为只有大写英文字母，所以数组长度为26，初始默认数字为0
        int[] num = new int[26];
        char[] charArray = s.toCharArray();

        int max = 0;
        int left = 0, right = 0;
        while (right < s.length()) {
            // A-A=0  B-A=1，此处拿到字母对应数组的index，++表示当前index的数字自增，用来记录相同字母的个数
            num[charArray[right] - 'A']++;
            // 当前字母有几个，跟max相比
            max = Math.max(max, num[charArray[right] - 'A']);

            // 窗口向右滑动后包含的字母个数超过了相同字母个数(max) + 可以替换字母个数(k)时
            // 左边界要向右移动，此时右边界再次向右移动才有意义
            // 若左边界不向右移时右边界再次右移，此时小串已经不满足条件，大串更不会满足条件
            if (right - left + 1 > max + k) {
                // 因为左边界即将右移，将左边界对应的数字的个数减一
                num[charArray[left] - 'A']--;
                // 左边界右移，此处需要先将原始左边界对应的数字减一，再做右移操作
                left++;
            }

            // 右边界右移
            right++;
        }


        return right - left;
    }
}


