package com.peifeng.linkedlist;

public class ReverseList {


    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    public static ListNode reverse(ListNode head){

        ListNode pre = null;
        ListNode cur = head;

        while(cur!=null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public static void main(String[] args) {

        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        ListNode temp = listNode1;
        while (temp != null){
            System.out.print(temp.val + ",");
            temp = temp.next;
        }
        System.out.println();
        temp = reverse(listNode1);
        while (temp != null){
            System.out.print(temp.val + ",");
            temp = temp.next;
        }
    }
}


