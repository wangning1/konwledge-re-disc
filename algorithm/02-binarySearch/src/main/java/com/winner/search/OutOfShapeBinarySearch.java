package com.winner.search;

/**
 * @Author: ningwang216580
 * @Date: 2019/7/11 8:02
 * <p>
 * 变形的二分查找：
 * 四种变形
 * - 01 查找第一个等于给定值元素
 * - 02 查找最后一个等于给定值元素
 * - 03 查找第一个大于等于给定值元素
 * - 04 查找最后一个小于等于给定值元素
 */
public class OutOfShapeBinarySearch {
    public static void main(String[] args) {
        int[] originArr = {1, 3, 4, 5, 6, 8, 8, 8, 11, 18};
        int compareVal = 8;
        int valIndex = binarySearchFirstEqual(originArr, originArr.length, compareVal);
        System.out.println("查找第一个等于给定值元素下标:" + valIndex);

        valIndex = binarySearchLastEqual(originArr, originArr.length, compareVal);
        System.out.println("查找最后一个等于给定值元素下标:" + valIndex);

        valIndex = binarySearchFirstBiggerOrEqual(originArr, originArr.length, compareVal);
        System.out.println("查找第一个大于等于给定值元素下标:" + valIndex + ",元素值:" + originArr[valIndex]);

        valIndex = binarySearchFirstSmallerOrEqual(originArr, originArr.length, compareVal);
        System.out.println("查找第一个小于于等于给定值元素下标:" + valIndex + ",元素值:" + originArr[valIndex]);
    }

    /**
     * 01 查找第一个等于给定值元素
     *
     * @param arr
     * @param len
     * @param compareVal
     */
    public static int binarySearchFirstEqual(int[] arr, int len, int compareVal) {
        int low = 0;
        int high = len;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] < compareVal) {
                low = mid + 1;
            } else if (arr[mid] > compareVal) {
                high = mid - 1;
            } else {
                if (mid == 0 || arr[mid - 1] < compareVal) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 02 查找最后一个等于给定值元素
     *
     * @param arr
     * @param len
     * @param compareVal
     * @return
     */
    public static int binarySearchLastEqual(int[] arr, int len, int compareVal) {
        int low = 0;
        int high = len;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] < compareVal) {
                low = mid + 1;
            } else if (arr[mid] > compareVal) {
                high = mid - 1;
            } else {
                if (mid == len || arr[mid + 1] > compareVal) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 03 查找第一个大于等于给定值元素
     *
     * @param arr
     * @param len
     * @param compareVal
     * @return
     */
    public static int binarySearchFirstBiggerOrEqual(int[] arr, int len, int compareVal) {
        int low = 0;
        int high = len;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] > compareVal) {
                if (mid == len || arr[mid - 1] == compareVal) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else if (arr[mid] <= compareVal) {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 04 查找最后一个小于等于给定值元素
     *
     * @param arr
     * @param len
     * @param compareVal
     * @return
     */
    public static int binarySearchFirstSmallerOrEqual(int[] arr, int len, int compareVal) {
        int low = 0;
        int high = len;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] >= compareVal) {
                high = mid - 1;
            } else {
                if (mid == 0 || arr[mid + 1] == compareVal) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }


}
