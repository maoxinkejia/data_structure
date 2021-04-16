package com.zmx.study.datastructure.leetcode;

public class CommonQuestion {
    public static void main(String[] args) {
//        int[] array = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
//        int[] array = new int[]{4,3,2,1,4};
//        System.out.println(maxArea(array));

//        String s = "IV";
//        System.out.println(romanToInt_2(s));

//        int[] nums = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
//        System.out.println(orderlyArraySearch(nums, 4));

//        int x = 2147395599;
//        System.out.println(mySqrt(x));
    }

    /**
     * 旋转排序数组查找（二分查找法）
     * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
     * 输入：nums = [4,5,6,7,0,1,2], target = 0
     * 输出：4
     * <p>
     * 旋转数组其本质是将有序数组根据指定索引进行旋转，旋转后的数组为部分有序
     * 在将数组二分后，其一定是有一半为有序，另一半是无须的
     * 若目标值在有序的一侧，则按照普通的二分查找法即可
     * 若目标值在无须的一侧，则需要单独进行查找
     */
    public static int disorderlyArraySearch(int[] nums, int target) {
        if (nums == null) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            // 数组的第一个元素比中位数小，说明左侧是有序数组
            if (nums[0] <= nums[mid]) {
                // 数组第一位比目标值小 且 目标值比中位数小，左侧数组为有序数组，则右指针左移，抛弃右侧无序数组
                if (nums[0] <= target && target < nums[mid]) {
                    right = mid - 1;
                }
                // 数组第一位比目标值大 或 目标值比比中位数大，左侧数组为有序数组，则左指针右移，抛弃左侧有序数组
                else {
                    left = mid + 1;
                }
            }
            // 数组的第一个元素比中位数大，说明右侧是有序数组
            else {
                // 中位数比目标值小 且 目标值比末尾数小，右侧为有序数组，则左指针右移，抛弃左侧无序数组
                if (nums[mid] < target && target <= nums[nums.length - 1]) {
                    left = mid + 1;
                }
                // 中位数比目标值大 或 目标值比末尾数大，右侧为有序数组，则右指针左移，抛弃右侧有序数组
                else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    /**
     * 猜数字大小
     * https://leetcode-cn.com/problems/guess-number-higher-or-lower/
     */
    public static int guessNumber(int n) {
        int left = 1;
        int right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int result = guess(mid);
            if (result == 0) {
                return mid;
            }

            if (result == -1) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    private static int guess(int num) {
        return 1;
    }

    /**
     * 求平方根
     * https://leetcode-cn.com/problems/sqrtx/
     */
    public static int mySqrt(int x) {
        int left = 0;
        int right = x;
        int res = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if ((long) mid * mid <= x) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return res;
    }

    /**
     * 二分查找
     * https://leetcode-cn.com/problems/binary-search/
     */
    public static int orderlyArraySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            System.out.println("中位数：" + nums[mid]);
            if (nums[mid] == target) {
                return mid;
            }

            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    /**
     * 盛最多水的容器
     * https://leetcode-cn.com/problems/container-with-most-water/
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     */
    public static int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        int res = 0;
        while (left < right) {
            int minHeight = Math.min(height[left], height[right]);
            res = Math.max(res, minHeight * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return res;
    }

    /**
     * 罗马数字转整数
     * https://leetcode-cn.com/problems/roman-to-integer/
     * 输入: "LVIII"
     * 输出: 58
     * 解释: L = 50, V= 5, III = 3.
     * 输入: "MCMXCIV"
     * 输出: 1994
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     * <p>
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     */
    public static int romanToInt_1(String s) {
        s = s.replace("IV", "a");
        s = s.replace("IX", "b");
        s = s.replace("XL", "c");
        s = s.replace("XC", "d");
        s = s.replace("CD", "e");
        s = s.replace("CM", "f");

        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum += getValue(s.charAt(i));
        }

        return sum;
    }

    /**
     * 此解法核心思想是先获取一个字符，
     * 若后一个字符比前一个字符大，则前一个字符做减法操作
     * 若前一个字符比后一个字符大，则做加法操作
     */
    public static int romanToInt_2(String s) {
        int preNum = getValue(s.charAt(0));
        int sum = 0;

        for (int i = 1; i < s.length(); i++) {
            int currNum = getValue(s.charAt(i));
            if (preNum < currNum) {
                sum -= preNum;
            } else {
                sum += preNum;
            }

            preNum = currNum;
        }

        sum += preNum;
        return sum;
    }

    private static int getValue(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            case 'a':
                return 4;
            case 'b':
                return 9;
            case 'c':
                return 40;
            case 'd':
                return 90;
            case 'e':
                return 400;
            case 'f':
                return 900;
            default:
                return 0;
        }
    }
}
