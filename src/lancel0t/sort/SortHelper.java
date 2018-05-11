/**
 * 
 * 排序帮助类
 * 
 * @author lancel0t
 * @date 2018年5月11日
 */
package lancel0t.sort;

public class SortHelper {

	/*
	 * 快速排序
	 * 时间复杂度：O(nlogn)
	 * 稳定性：不稳定
	 */
	public static void quickSort(int data[], int beg, int end) {

		if (beg < end) {

			int lo, hi, x;
			lo = beg;
			hi = end;
			x = data[lo];

			while (lo < hi) {

				// 从右向左找第一个小于x的数
				while (lo < hi && data[hi] > x)
					hi--;
				if (lo < hi)
					data[lo] = data[hi];

				// 从左向右找第一个大于x的数
				while (lo < hi && data[lo] < x)
					lo++;
				if (lo < hi)
					data[hi] = data[lo];
			}

			data[lo] = x;
			quickSort(data, beg, lo - 1); /* 递归调用 */
			quickSort(data, lo + 1, end); /* 递归调用 */
		}
	}

	public static void main(String[] args) {
		int i;
		int a[] = { 30, 40, 60, 10, 20, 50 };

		System.out.println("== 原始顺序:");
		for (i = 0; i < a.length; i++)
			System.out.printf("%d ", a[i]);
		System.out.printf("\n");

		System.out.println("== 快速排序:");
		quickSort(a, 0, a.length - 1);
		for (i = 0; i < a.length; i++)
			System.out.printf("%d ", a[i]);
		System.out.printf("\n");

	}

}
