/**
 * 
 * 用两个队列实现栈
 * 
 * @author lancel0t
 * @date 2018年5月11日
 */
package lancel0t.other;

import java.util.LinkedList;
import java.util.Queue;

public class StackWithTwoQueues<T> {

	private Queue<T> queue1 = new LinkedList<T>();
	private Queue<T> queue2 = new LinkedList<T>();

	// 入栈
	public void push(T ele) {

		if (queue2.size() > 0) {
			queue2.offer(ele);
		} else {
			queue1.offer(ele);
		}
	}

	// 出栈
	public T pop() throws Exception {

		if (queue1.size() > 0) {
			while (queue1.size() > 1) {
				T ele = queue1.poll();
				queue2.offer(ele);
			}
			return queue1.poll();

		} else if (queue2.size() > 0) {
			while (queue2.size() > 1) {
				T ele = queue2.poll();
				queue1.offer(ele);
			}
			return queue2.poll();
		} else {
			throw new Exception("栈为空！");
		}
	}

	public static void main(String[] args) throws Exception {
		StackWithTwoQueues<Character> ins = new StackWithTwoQueues<Character>();

		ins.push('a');
		ins.push('b');
		ins.push('c');
		System.out.println("== 出栈：" + ins.pop());
		ins.push('d');
		System.out.println("== 出栈：" + ins.pop());
		ins.push('e');
		System.out.println("== 出栈：" + ins.pop());
		System.out.println("== 出栈：" + ins.pop());
		System.out.println("== 出栈：" + ins.pop());
	}

}
