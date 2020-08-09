package com.peifeng.daily;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }

    public boolean isPalindrome(String s) {

        if("".equals(s) || s == null){
            return true;
        }
        s = s.toUpperCase();
        char[] array = s.toCharArray();
        String target = "";
        for(int i = 0; i< array.length; i++){
            if(validChar(array[i])){
                target = target + array[i];
            }
        }
        array = target.toCharArray();
        boolean flag = true;
        for(int i = 0; i< array.length; i++){
            if(i == array.length-1-i || i == array.length/2 + 1){
                break;
            }
            if(array[i] != array[array.length-1-i]){
                flag = false;
            }
        }
        return flag;
    }

    public boolean validChar(char a){
        int num = Integer.valueOf(a);
        if((num>=48&&num<=57)||(num>=65&&num<=90)){
            return true;
        }
        return false;
    }

    static int abc = 0;
    public static void main(String[] args) {
        Thread th1 = new Thread(()->{
            abc = 3;
            System.out.println("th1:" + abc);
        });

        Thread th2 = new Thread(()->{
            System.out.println("th2:" + abc);
        });

        while(true){
            th1.run();
            th2.run();
        }

    }


    @Test
    public void testPriority(){
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(5);
        priorityQueue.add(4);
        priorityQueue.add(10);
        priorityQueue.add(2);

        System.out.println(priorityQueue.peek());
    }

    @Test
    public void testDeque(){
        int[] ints = this.maxSlidingWindow(new int[]{1, 3, 1, 2, 0, 5}, 3);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + ",");
        }
    }


    public static int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0){
            return new int[0];
        }
        Deque<Integer> deque = new LinkedList<Integer>();
        int[] data = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            if(i >= k && deque.peek() <= i-k){
                deque.remove();
            }

            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]){
                deque.removeLast();
            }
            deque.add(i);
            if(i >= k-1){
                data[i-k+1] = nums[deque.peek()];
            }
        }
        return data;
    }



}
