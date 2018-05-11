/**
 * 
 * 用两个栈实现队列
 * 
 * @author lancel0t
 * @date 2018年5月11日
 */
package lancel0t.other;

import java.util.Stack;

public class QueueWithTwoStacks<T> {

	private Stack<T> stack1 = new Stack<T>();
	private Stack<T> stack2 = new Stack<T>();

	// 入队列
	public void offer(T ele) {
		stack1.push(ele);
	}

	// 出队列
	public T poll() throws Exception {

		// 如果第2个栈没有数据，依次将第1个栈元素弹出，并压入第2个栈
		if (stack2.size() <= 0) {
			while (stack1.size() > 0) {
				T ele = stack1.pop();
				stack2.push(ele);
			}
		}

		// 如果栈1和栈2都没有元素，此时栈2为空
		if (stack2.size() == 0)
			throw new Exception("队列为空！");

		return stack2.pop();
	}

	public static void main(String[] args) throws Exception {
		QueueWithTwoStacks<Character> ins = new QueueWithTwoStacks<Character>();

		ins.offer('a');
		ins.offer('b');
		ins.offer('c');
		System.out.println("== 出队列：" + ins.poll());
		ins.offer('d');
		ins.offer('e');
		System.out.println("== 出队列：" + ins.poll());
		System.out.println("== 出队列：" + ins.poll());
		System.out.println("== 出队列：" + ins.poll());
		System.out.println("== 出队列：" + ins.poll());
	}

}
