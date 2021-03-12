package com.zmx.study.datastructure.leetcode;

public class PalindromeNumber {
    public static void main(String[] args) {
        int i = -123;
        System.out.println(String.valueOf(i).length());
    }

    /**
     * 回文数
     * https://leetcode-cn.com/problems/palindrome-number/
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
     */
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            // 反转后的数字要将原反转后数字往前进移一位，个位空出来给新取模得出来的数
            // 第一次0*10还是0，只添加了各位数
            // 第二次将原个位数移动到十位数，再加上新取模的个位数构成新的反转数字
            // 第三次以此类推
            revertedNumber = revertedNumber * 10 + x % 10;

            // 每反转一次数字，反转数字多一位，那原有数字要减一位，除以10即可
            x /= 10;
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }
}
