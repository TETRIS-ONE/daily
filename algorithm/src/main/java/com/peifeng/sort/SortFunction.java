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

    /**
     * 归并排序算法
     * @param nums 数组
     * @param n 数组大小
     */
    public static void mergeSort(int[] nums, int n){
        mergeSortC(nums,0, n-1);
    }

    /**
     * 递归调用函数
     * @param nums
     * @param p
     * @param r
     */
    public static void mergeSortC(int[] nums, int p, int r){
        // 递归终止条件
        if(p >= r){
            return;
        }
        //取 p 到 r 之间的中间位置 q
        int q = (p + r)/2;
        // 分治递归
        mergeSortC(nums, p, q);
        mergeSortC(nums, q+1, r);
        merge(nums, p, r);
    }

    public static void merge(int[] nums, int p, int r){
        // 初始化变量 i, j, k
        int i = p, q = (p + r)/2, j = (p + r)/2 + 1, k = 0;
        // 申请一个临时数组 tmp
        int[] tmp = new int[nums.length];
        while(i <= q && j <= r){
            if(nums[i] > nums[j]){
                tmp[k++] = nums[j++];
            }else{
                tmp[k++] = nums[i++];
            }
        }

        // 判断哪个子数组中有剩余的数据
        int start = i, end = q;
        if(j <= r){
            start = j;
            end = r;
        }

        // 将剩余的数据拷贝到临时数组 tmp
        while(start <= end){
            tmp[k++] = nums[start++];
        }

        // 将 tmp 中的数组拷贝回 nums
        for (int l = 0; l <= r-p; l++) {
            nums[p+l] = tmp[l];
        }
    }

    /**
     * 快速排序
     * @param nums 数组
     * @param n 数组大小
     */
    public static void quickSort(int[] nums, int n){
        quickSortC(nums, 0, n-1);
    }

    /**
     * 快速排序递归函数
     * @param nums
     * @param p
     * @param r
     */
    public static void quickSortC(int[] nums, int p, int r){
        if(p >= r) return;
        // 获取分区点
        int q = partition(nums, p, r);

        quickSortC(nums, p, q-1);
        quickSortC(nums, q+1, r);
    }

    public static int partition(int[] nums, int p, int r){
        // 一般情况下选择 p 到 r 区间的最后一个元素，作为 pivot
        int pivot = nums[r];
        int i = p;
        for (int j = p; j < r; j++) {
            if(nums[j] < pivot){
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                i++;
            }
        }
        int tmp = nums[i];
        nums[i] = nums[r];
        nums[r] = tmp;

        return i;
    }

    /**
     * 计数排序
     * @param nums
     * @param n
     */
    public static void countingSort(int[] nums, int n){
        if(n <= 1) return;
        // 查找数组中数据的范围
        int max = nums[0];
        for (int i = 0; i < n; i++) {
            if(max < nums[i]){
                max = nums[i];
            }
        }

        int[] c = new int[max + 1]; // 申请一个计数数组c，下标大小 [0, max]
        for (int i = 0; i <= max; i++) {
            c[i] = 0;
        }

        // 计算每个元素个数，放入 c 中
        for (int i = 0; i < n; i++) {
            c[nums[i]]++;
        }

        // 依次累加
        for (int i = 1; i <= max; i++) {
            c[i] = c[i] + c[i-1];
        }

        // 临时数组 r，存储排序之后的结果
        int[] r = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int index = c[nums[i]] - 1;
            r[index] = nums[i];
            c[nums[i]]--;
        }
        // 拷贝结果
        for (int i = 0; i < n; i++) {
            nums[i] = r[i];
        }
    }


    public static void main(String[] args) {
        int[] nums = {4,5,6,3,2,1};
        countingSort(nums, 6);
        for (int i = 0; i < 6; i++) {
            System.out.print(nums[i] + ",");
        }
    }
}
