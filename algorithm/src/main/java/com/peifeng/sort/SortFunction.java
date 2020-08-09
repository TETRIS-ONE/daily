package com.peifeng.sort;

/**
 * 排序函数
 */
public class SortFunction {

    /**
     * 冒泡排序
     * @param nums 表示数组
     * @param n 表示数组大小
     */
    public static void bubbleSort(int[] nums, int n){
        if (n <= 0) return;
        for (int i = 0; i < n; i++) {
            // 提前退出冒泡循环的标志位
            boolean flag = false;
            for (int j = 0; j < n - i - 1; j++) {
                if(nums[j] > nums[j+1]){
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                    flag = true; // 表示有数据交换
                }
            }
            if(!flag){ // 没有数据交换，提前退出
                break;
            }
        }
    }

    /**
     * 插入排序
     * @param nums 表示数组
     * @param n 表示数组大小
     */
    public static void insertionSort(int[] nums, int n){
        if(n <= 0) return;
        // 初始已排序区间，数组的第一个元素
        // 未排序区间，从数组的第二个元素开始
        // 取未排序区间的元素，在已排序区间中找到合适的插入位置将其插入，并保证已排序区间数据一直有序。
        // 重复这个过程，直到未排序区间元素为空，算法结束。
        for (int i = 1; i < n; i++) {
            int temp = nums[i]; // 第二个元素开始
            int j = i - 1; // 与相邻的排序区间的元素，依次比较
            for (; j >= 0; j--) {
                if(nums[j] > temp){ // 比较
                    nums[j+1] = nums[j]; // 移动数据
                }else{
                    break;
                }
            }
            nums[j+1] = temp; // 插入数据
        }
    }

    /**
     * 选择排序算法的实现思路有点类似插入排序，也分已排序区间和未排序区间。
     * 但是选择排序每次会从未排序区间中找到最小的元素，将其放到已排序区间的末尾
     * @param nums
     * @param n
     */
    public static void selectionSort(int[] nums, int n){
        if(n <= 0) return;
        for (int i = 0; i < n - 1; i++) {
            // 查找最小值
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if(nums[minIndex] > nums[j]){
                    minIndex = j;
                }
            }
            // 交换
            int temp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        int[] nums = {4,5,6,1,2,1};
        selectionSort(nums, 6);
        for (int i = 0; i < 6; i++) {
            System.out.print(nums[i] + ",");

        }
    }
}
