package com.peifeng.daily;

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

    @Test
    public void testString(){
        String s1 = "Hello World";
        System.out.println("s1 is \"" + s1 + "\"");
        String s2 = s1;
        System.out.println("s2 is another reference to s1.");
        String s3 = new String(s1);
        System.out.println("s3 is a copy of s1.");
        // compare using '=='
        System.out.println("Compared by '==':");
        // true since string is immutable and s1 is binded to "Hello World"
        System.out.println("s1 and \"Hello World\": " + (s1 == "Hello World"));
        // true since s1 and s2 is the reference of the same object
        System.out.println("s1 and s2: " + (s1 == s2));
        // false since s3 is refered to another new object
        System.out.println("s1 and s3: " + (s1 == s3));
        // compare using 'equals'
        System.out.println("Compared by 'equals':");
        System.out.println("s1 and \"Hello World\": " + s1.equals("Hello World"));
        System.out.println("s1 and s2: " + s1.equals(s2));
        System.out.println("s1 and s3: " + s1.equals(s3));
        // compare using 'compareTo'
        System.out.println("Compared by 'compareTo':");
        System.out.println("s1 and \"Hello World\": " + (s1.compareTo("Hello World") == 0));
        System.out.println("s1 and s2: " + (s1.compareTo(s2) == 0));
        System.out.println("s1 and s3: " + (s1.compareTo(s3) == 0));
    }

    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }
        if(strs.length == 1) return strs[0];

        StringBuilder builder = new StringBuilder();
        labelA:
        for (int i = 0; i < strs[0].length(); i++) {
            String c = strs[0].substring(i, i+1);
            int k = 0;
            for (int j = 1; j < strs.length; j++) {
                if(i+1 <= strs[j].length() && strs[j].substring(i, i+1).equals(c)){
                    k++;
                }else{
                    break labelA;
                }
                if(k == strs.length - 1){
                    builder.append(c);
                    k = 0;
                }
            }
        }
        return builder.toString();
    }

    @Test
    public void testStr(){
        String s = "example   good a";
        String s1 = reverseWords(s);
        System.out.println(s1);

    }

    public String reverseWords(String s) {
        if(s == null && s.trim().length() == 0) return s;
        String str = s.trim();
        char[] charsArray = str.toCharArray();
        int len = charsArray.length;
        List<String> words = new ArrayList<String>();
        int begin = 0;
        int wordLen = 0;
        for (int i = 0; i < len; i++) {
            if(charsArray[i] == ' '){
                if(wordLen > 0){
                    words.add(str.substring(begin, begin+wordLen));
                }
                begin=i+1;
                wordLen=0;
            }else if(i == len - 1){
                words.add(str.substring(begin));
            }else {
                wordLen++;
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i =  words.size() - 1; i >= 0; i--) {
            builder.append(words.get(i));
            builder.append(" ");
        }
        return builder.toString().trim();
    }
}
