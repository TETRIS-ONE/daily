package com.peifeng.daily;

public class movesToMakeZigzag {

    public static int movesToMakeZigzag(int[] nums) {

        if (null == nums || nums.length == 0) return -1;
        if (nums.length == 1) return 0;

        // gap 表示当前元素与相邻元素的差值
        //      差值含义: 为正表示需要减少的操作次数
        //               为负则不用理会(已经符合条件)
        // evenSum 表示偶数索引符合条件需要的操作次数之和
        // oddSum 表示奇数索引符合条件需要的操作次数之和
        // n 表示数组长度
        int gap = 0, evenSum = 0, oddSum = 0, n = nums.length;
        // 计算偶数索引需要减少多少次数才能符合条件
        for (int i = 0; i < n; i = i+2) {
            gap = 0;
            if (i != 0) gap = Math.max(gap, nums[i] - nums[i-1] + 1);
            if (i+1 < n) gap = Math.max(gap, nums[i] - nums[i+1] + 1);
            evenSum += gap;
        }
        // 计算奇数索引需要减少多少次数才能符合条件
        for (int i = 1; i < n; i = i+2) {
            gap = 0;
            if (i != 0) gap = Math.max(gap, nums[i] - nums[i-1] + 1);
            if (i+1 < n) gap = Math.max(gap, nums[i] - nums[i+1] + 1);
            oddSum += gap;
        }

        return Math.min(evenSum, oddSum);

    }

    public static void main(String[] args) {

//        int[] nums = {7,4,8,9,7,7,5};
//        int[] nums = {9,6,1,6,2};
//        int[] nums = {1,2,3};
        int[] nums = {2,7,10,9,8,9};
        int i = movesToMakeZigzag.movesToMakeZigzag(nums);
        System.out.println(i);
    }
}
