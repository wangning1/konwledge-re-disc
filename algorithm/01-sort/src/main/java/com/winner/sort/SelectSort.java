package com.winner.sort;

import java.util.Arrays;

/**
 * @Author: ningwang216580
 * @Date: 2019/7/10 17:28
 * 选择排序 思想：
 * 将元素分为已排序区间和待排序区间，每次会从未排序区间中找到最小的元素，将其放在已排序区间的末尾。
 */
public class SelectSort {

    public static void main(String[] args) {

        int[] originArr = {7, 5, 8, 4, 2, 1, 3, 0, 9, 2};
        System.out.println("----待排序数组：" + Arrays.toString(originArr));
        selectSort01(originArr, originArr.length);
        System.out.println("----排序后数组：" + Arrays.toString(originArr));

    }

    /**
     * @param arr 待排序数组
     * @param len 数组长度
     */
    public static void selectSort01(int[] arr, int len) {
        for (int i = 0; i < len - 1; i++) {
            int minIndex = i + 1;
            //遍历待排序空间，找到最小的元素
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            //如果未排序中的最小元素小于已排序尾元素，进行交换
            if (arr[i] > arr[minIndex]) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

}


