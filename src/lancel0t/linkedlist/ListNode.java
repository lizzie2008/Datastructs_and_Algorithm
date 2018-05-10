package lancel0t.linkedlist;

// 链表节点
public class ListNode<T> {
	public T val;
	public ListNode<T> next;

	public ListNode() {}
		
	public ListNode(T x) {
		val = x;
	}
}
