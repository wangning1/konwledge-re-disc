package com.winner.sort;

import java.util.Arrays;

/**
 * @Author: ningwang216580
 * @Date: 2019/7/10 17:30
 * <p>
 * 插入排序 思想：
 * 将数组分为两个区间，已排序空间和未排序空间。初始的已排序空间只有一个元素就是数组的第一个元素。插入排序的核心思想就是取未排序空间中的元素，
 * 在已排序空间中找到合适的位置插入，保证已排序空间一直有序。重复这个过程，直到未排序空间为空，算法结束。
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] originArr = {7, 5, 8, 4, 2, 1, 3, 0, 9, 2};
        System.out.println("----待排序数组：" + Arrays.toString(originArr));
        insertionSort01(originArr, originArr.length);
        System.out.println("----排序后数组：" + Arrays.toString(originArr));

    }

    /**
     * @param arr 待排序数组
     * @param len 数组长度
     */
    public static void insertionSort01(int[] arr, int len) {
        int targetIndex = 0;
        for (int i = 1; i < len; i++) {
            targetIndex = i;
            for (int j = i; j >= 1; j--) {
                if (arr[i] < arr[j - 1]) {
                    targetIndex = j - 1;
                }
            }
            if (targetIndex != i) {
                for (int p = i; p > targetIndex; p--) {
                    int temp = arr[p];
                    arr[p] = arr[p - 1];
                    arr[p - 1] = temp;
                }
            }
        }
    }
}
