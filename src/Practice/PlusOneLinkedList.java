package Practice;

/**
 * Given a non-negative integer represented as non-empty a singly linked list
 * of digits, plus one to the integer.
 * You may assume the integer do not contain any leading zero, except the number
 * 0 itself.
 * The digits are stored such that the most significant digit is at the head of
 * the list.
 *
 * E.g. Input: 1->2->3
 * Output: 1->2->4
 *
 * Input: 0
 * Output: 1
 *
 * Input: 9->9
 * Output: 1->0->0
 *
 * Input: 1->9->8->9
 * Output: 1->9->9->0
 *
 * Input: 1->9->9->9
 * Output: 2->0->0->0
 * 这道题不可以用int parse string来做因为已知int过大的话是无法使用Int来实现的
 *
 */
public class PlusOneLinkedList {
    private static class ListNode {
        int val;
        ListNode next;
        //constructor
        ListNode(int x){
            val = x;
        }
    }

    private static ListNode plusOne(ListNode head){
        //Dummy node
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode i = dummy;
        ListNode j = dummy;

        while(j.next != null){
            j=j.next;
            if(j.val != 9){
                i=j;
            }
        }

        if(j.val != 9){//Case1 plus one directly
            j.val++;
        }else{//Case2 have a carry
            i.val++;
            i=i.next;
            while(i != null){
                i.val=0;
                i=i.next;
            }
        }

        if(dummy.val==0){
            return dummy.next;
        }
        return dummy;
    }


}
