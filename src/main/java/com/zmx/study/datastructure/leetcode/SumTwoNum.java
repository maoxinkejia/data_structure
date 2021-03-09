package com.zmx.study.datastructure.leetcode;

import java.util.HashMap;
import java.util.Map;

public class SumTwoNum {
    public static void main(String[] args) {

    }

    /**
     * 两数之和
     * https://leetcode-cn.com/problems/two-sum/
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums.length < 2) {
            return null;
        }

        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }

            map.put(nums[i], i);
        }

        return null;
    }

    /**
     * 两个链表求和
     * https://leetcode-cn.com/problems/sum-lists-lcci/
     */
    public ListNode addTwoSum(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode head = new ListNode();
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
