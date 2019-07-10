package com.winner.sort;

import java.util.Arrays;

/**
 * @Author: ningwang216580
 * @Date: 2019/7/10 17:29
 * <p>
 * 冒泡排序 思想：
 * 冒泡排序只会操作相邻的元素，相邻元素进行比较，如果不满足大小关系，就进行交换，一次冒泡排序能让至少一个元素移动到应有的位置，重复n次，即可完成排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] originArr = {7, 5, 8, 4, 2, 1, 3, 0, 9, 2};
        System.out.println("----待排序数组：" + Arrays.toString(originArr));
        bubbleSort01(originArr, originArr.length);
        bubbleSort02(originArr, originArr.length);
        System.out.println("----排序后数组：" + Arrays.toString(originArr));
    }

    /**
     * 基本的冒泡排序，循环n次
     *
     * @param arr 待排数组
     * @param len 数组长度
     */
    public static void bubbleSort01(int[] arr, int len) {
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 冒泡排序优化, 如果某次比较没有进行交换，说明已经有序，可以提前结束
     *
     * @param arr
     * @param len
     */
    public static void bubbleSort02(int[] arr, int len) {
        for (int i = 0; i < len - 1; i++) {
            boolean isSwap = false;
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSwap = true;
                }
            }
            //没有发生交换，说明有序
            if (!isSwap) {
                System.out.println("本次没有交换数据，i = " + i);
                break;
            }
        }
    }
}
