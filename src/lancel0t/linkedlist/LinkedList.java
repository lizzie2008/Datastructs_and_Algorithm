/**
 * 
 * 链表
 * 
 * @author lancel0t
 * @date 2018年5月10日
 */
package lancel0t.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class LinkedList<T> {

	// 链表头指针
	private ListNode<T> head = new ListNode<T>();

	// 尾部添加新节点
	public void addToTail(T value) {
		ListNode<T> newNode = new ListNode<T>(value);
		if (head.next == null) {
			head.next = newNode;
		} else {
			ListNode<T> curNode = head;
			// 找到链表最后一个
			while (curNode.next != null)
				curNode = curNode.next;
			curNode.next = newNode;
		}
	}

	// 打印链表
	public void print() {
		ListNode<T> currNode = head.next;
		List<String> values = new ArrayList<String>();
		while (currNode != null) {
			values.add(currNode.val.toString());
			currNode = currNode.next;
		}
		System.out.println(String.join("->", values));
	}

	// 逆转链表
	public void ReverseList() {
		ListNode<T> reverse = new ListNode<T>();
		ListNode<T> curr = head.next;
		while (curr != null) {
			// 保留当前下个节点引用
			ListNode<T> nextTemp = curr.next;
			// 将当前节点加到头结点位置，节点next为之前反转的单链表
			curr.next = reverse.next;
			reverse.next = curr;
			curr = nextTemp;
		}
		head.next = reverse.next;
	}

	public static void main(String[] args) {

		LinkedList<Integer> linkedList = new LinkedList<Integer>();
		
		linkedList.addToTail(1);
		linkedList.addToTail(2);
		linkedList.addToTail(3);
		linkedList.addToTail(4);
		linkedList.addToTail(5);

		System.out.println("== 原链表：");
		linkedList.print();

		linkedList.ReverseList();
		System.out.println("== 逆转链表：");
		linkedList.print();
	}

}
