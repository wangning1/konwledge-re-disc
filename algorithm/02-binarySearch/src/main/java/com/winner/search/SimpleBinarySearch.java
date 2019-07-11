package com.winner.search;

/**
 * @Author: ningwang216580
 * @Date: 2019/7/11 8:01
 * <p>
 * 简单二分查找
 * 思想：
 */
public class SimpleBinarySearch {

    public static void main(String[] args) {
        int[] origin = {0, 1, 2, 3, 4, 5, 5, 5, 6, 7, 8, 9};
        int value = 9;
        int valueIndex = binarySearch01(origin, origin.length, value);
        System.out.println("普通查找：" + valueIndex);
        valueIndex = binarySearch02(origin, origin.length, value);
        System.out.println("递归查找：" + valueIndex);
    }

    /**
     * 非递归实现
     *
     * @param arr        数组
     * @param len        数组长度
     * @param compareVal 待比较的值
     * @return
     */
    public static int binarySearch01(int[] arr, int len, int compareVal) {
        int low = 0;
        int high = len;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] == compareVal) {
                return mid;
            } else if (arr[mid] < compareVal) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }


    /**
     * 递归实现
     *
     * @param arr        数组
     * @param len        数组长度
     * @param compareVal 查找元素
     * @return
     */
    public static int binarySearch02(int[] arr, int len, int compareVal) {
        return recursionBinarySearch(arr, 0, arr.length, compareVal);
    }

    //递归实现
    private static int recursionBinarySearch(int[] arr, int low, int high, int compareVal) {
        if (low > high) {
            return -1;
        }
        int mid = low + ((high - low) >> 1);
        if (arr[mid] == compareVal) {
            return mid;
        } else if (arr[mid] > compareVal) {
            return recursionBinarySearch(arr, low, mid - 1, compareVal);
        } else {
            return recursionBinarySearch(arr, mid + 1, high, compareVal);
        }
    }

}
