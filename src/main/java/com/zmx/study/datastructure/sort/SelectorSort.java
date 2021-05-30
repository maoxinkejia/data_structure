package com.zmx.study.datastructure.sort;

import java.util.Arrays;

public class SelectorSort {
    public static void main(String[] args) {
        int[] arr = new int[]{15, 3, 9, -1, 10, 12, 5};
        selectorSort(arr);
    }

    private static void selectorSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
