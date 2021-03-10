package com.zmx.study.datastructure.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 */
public class SumTwoNum {
    public static void main(String[] args) {

    }

    /**
     * 两数之和
     * https://leetcode-cn.com/problems/two-sum/
     */
    public static int[] twoSum(int[] nums, int target) {
        if (nums.length < 2) {
            return null;
        }

        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            // 当target减去当前数能得到数组内其他数时，说明符合条件
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }

            // 将每一个数和对应的索引放到map中
            map.put(nums[i], i);
        }

        return null;
    }

    /**
     * 两个链表求和
     * https://leetcode-cn.com/problems/sum-lists-lcci/
     * 输入：(7 -> 1 -> 6) + (5 -> 9 -> 2)，即617 + 295
     * 输出：2 -> 1 -> 9，即912
     */
    public static ListNode addTwoSum(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode head = new ListNode();
        // first节点不能动，最后要返回first节点
        // 移动的是head节点，将head节点指针往后挪进行计算
        ListNode first = head;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            head.next = new ListNode(sum % 10);

            head = head.next;
            // 除以10若为1则说明需要进位，下一个数要加1
            carry = sum / 10;
        }

        return first.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {

    }

    ListNode(int val) {
        this.val = val;
    }
}
