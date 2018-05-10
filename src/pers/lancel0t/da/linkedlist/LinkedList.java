/**
 * 
 * 链表
 * 
 * @author lancel0t
 * @date 2018年5月10日
 */
package pers.lancel0t.da.linkedlist;

public class LinkedList {
	
	// 链表节点
    public class ListNode
    {
        public int val;
        public ListNode next;
        public ListNode(int x) { val = x; }
    }
    
    // 逆转单链表
    public ListNode ReverseList(ListNode head)
    {
        ListNode reverse = null;
        ListNode curr = head;
        while (curr != null)
        {
            //移动节点
            ListNode nextTemp = curr.next;
            //将当前节点加到头结点位置，节点next为之前反转的单链表
            curr.next = reverse;
            reverse = curr;
            curr = nextTemp;
        }
        return reverse;
    }
    
	public static void main(String[] args) {
		
	}

}
