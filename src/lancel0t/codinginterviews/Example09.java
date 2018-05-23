/**
 * 
 * 【剑指Offer】面试题9 ： 斐波那契数列
 * 【  题目描述 】大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package lancel0t.codinginterviews;

public class Example09 {

	// 迭代非递归
	public int Fibonacci(int n) {

		if (n < 2)
			return n;

		int fibn = 0;
		for (int fib0 = 0, fib1 = 1, i = 2; i <= n; i++) {
			fibn = fib0 + fib1;
			fib0 = fib1;
			fib1 = fibn;
		}
		return fibn;
	}

	public static void main(String[] args) {

		Example09 exam = new Example09();

		for (int i = 0; i < 20; i++) {
			System.out.print(exam.Fibonacci(i) + " ");
		}
	}
}
