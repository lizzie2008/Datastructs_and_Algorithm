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

public class LinkedList {

	// 链表节点
	public class ListNode<T> {
		public T val;
		public ListNode<T> next;

		public ListNode() {
		}

		public ListNode(T x) {
			val = x;
		}
	}

	// 尾部添加新节点
	public <T> void addToTail(ListNode<T> head, T value) {
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
	public <T> void print(ListNode<T> head) {
		ListNode<T> currNode = head.next;
		List<String> values = new ArrayList<String>();
		while (currNode != null) {
			values.add(currNode.val.toString());
			currNode = currNode.next;
		}
		System.out.println(String.join("->", values));
	}

	// 逆转链表
	public <T> ListNode<T> ReverseList(ListNode<T> head) {
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
		return reverse;
	}

	public static void main(String[] args) {

		LinkedList linkedList = new LinkedList();
		// 声明头节点
		ListNode<Integer> head = linkedList.new ListNode<Integer>();

		linkedList.addToTail(head, 1);
		linkedList.addToTail(head, 2);
		linkedList.addToTail(head, 3);
		linkedList.addToTail(head, 4);
		linkedList.addToTail(head, 5);

		System.out.println("== 原链表：");
		linkedList.print(head);

		ListNode<Integer> reverse = linkedList.ReverseList(head);
		System.out.println("== 逆转链表：");
		linkedList.print(reverse);
	}

}
