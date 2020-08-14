package com.peifeng.sort;

public class BSearch {

    /**
     * 查找第一个值等于给定值的元素
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bsearch(int[] a, int n, int value){
        int low = 0;
        int high = n - 1;
        while(low <= high){
            int mid = low + ((high - low) >> 1);
            if(a[mid] >= value){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        if(low < n && a[low] == value) return low;
        else return -1;
    }

    public int bsearch2(int[] a, int n, int value){
        int low = 0;
        int high = n - 1;
        while(low <= high){
            int mid = low + ((high - low) >> 1);
            if(a[mid] > value){
                high = mid - 1;
            }else if(a[mid] < value){
                low = mid + 1;
            }else{
                if(mid == 0 || a[mid - 1] != value) return mid;
                else high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个值等于给定值的元素
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bsearchInlast(int[] a, int n, int value){
        int low = 0;
        int high = n - 1;
        while(low <= high){
            int mid = low + ((high - low)>>1);
            if(a[mid] > value){
                high = mid - 1;
            }else if(a[mid] < value){
                low = mid + 1;
            }else{
                if((mid - 1 == n) || a[mid + 1] != value) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找第一个大于等于给定值的元素
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bsearchLarge(int[] a, int n, int value){
        int low = 0;
        int high = n - 1;
        while(low <= high){
            int mid = low + ((high - low) >> 1);
            if(a[mid] >= value){
                if(mid == 0 || a[mid - 1] < value) return mid;
                else high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个小于等于给定值的元素
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bsearchLess(int[] a, int n, int value){
        int low = 0;
        int high = n - 1;
        while(low <= high){
            int mid = low + ((high - low) >> 1);
            if(a[mid] > value){
                high = mid - 1;
            }else{
                if(mid == n - 1 || a[mid + 1] > value) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }

}
