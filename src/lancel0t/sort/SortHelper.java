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
	 * 冒泡排序
	 * 
	 * 平均时间复杂度：O(n^2)
	 * 
	 * 稳定性：稳定
	 */
	public static void bubbleSort(int[] data) {

		boolean flag;

		for (int i = data.length - 1; i > 0; i--) {
			// 初始化标记为没有交换
			flag = false;

			// 将a[0...i]中最大的数据放在末尾
			for (int j = 0; j < i; j++) {
				if (data[j] > data[j + 1]) {
					// 交换a[j]和a[j+1]
					int tmp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = tmp;

					flag = true; // 发生交换
				}
			}
			// 若没发生交换，则说明数列已有序
			if (!flag)
				break;
		}
	}

	/*
	 * 快速排序
	 * 
	 * 平均时间复杂度：O(nlogn)
	 * 
	 * 稳定性：不稳定
	 */
	public static void quickSort(int[] data) {
		quickSort(data, 0, data.length - 1);
	}

	private static void quickSort(int[] data, int beg, int end) {

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
					data[lo++] = data[hi];

				// 从左向右找第一个大于x的数
				while (lo < hi && data[lo] < x)
					lo++;
				if (lo < hi)
					data[hi--] = data[lo];
			}

			data[lo] = x;
			quickSort(data, beg, lo - 1); /* 递归调用 */
			quickSort(data, lo + 1, end); /* 递归调用 */
		}
	}

	/*
	 * 插入排序
	 * 
	 * 平均时间复杂度：O(n^2)
	 * 
	 * 稳定性：稳定
	 */
	public static void insertSort(int[] data) {
		int i, j, k;

		for (i = 1; i < data.length; i++) {

			// 为a[i]在前面的a[0...i-1]有序区间中找一个合适的位置
			for (j = i - 1; j >= 0; j--)
				if (data[j] < data[i])
					break;

			// 如找到了一个合适的位置
			if (j != i - 1) {
				// 将比a[i]大的数据向后移
				int temp = data[i];
				for (k = i - 1; k > j; k--)
					data[k + 1] = data[k];
				// 将a[i]放到正确位置上
				data[k + 1] = temp;
			}
		}
	}

	/*
	 * 希尔排序
	 * 
	 * 平均时间复杂度：O(n^2)
	 * 
	 * 稳定性：不稳定
	 */
	public static void shellSort(int[] data) {
		int i, j, gap;

		// gap为步长，每次减为原来的一半。
		for (gap = data.length / 2; gap > 0; gap /= 2) {
			// 共gap个组，对每一组都执行直接插入排序
			for (i = 0; i < gap; i++) {
				for (j = i + gap; j < data.length; j += gap) {

					// 如果a[j] < a[j-gap]，则寻找a[j]位置，并将后面数据的位置都后移。
					if (data[j] < data[j - gap]) {
						int tmp = data[j];
						int k = j - gap;

						while (k >= 0 && data[k] > tmp) {
							data[k + gap] = data[k];
							k -= gap;
						}
						data[k + gap] = tmp;
					}
				}
			}
		}
	}

	/*
	 * 选择排序
	 * 
	 * 平均时间复杂度：O(n^2)
	 * 
	 * 稳定性：稳定
	 */
	public static void selectSort(int data[]) {

		for (int i = 0; i < data.length; i++) {
			int min = i;

			// 找出"a[i+1] ... a[n]"之间的最小元素，并赋值给min。
			for (int j = i + 1; j < data.length; j++) {
				if (data[j] < data[min])
					min = j;
			}

			// 若min!=i，则交换 a[i] 和 a[min]。
			if (min != i)
				swap(data, i, min);
		}
	}

	/*
	 * 堆排序
	 * 
	 * 平均时间复杂度：O(nlogn)
	 * 
	 * 稳定性：不稳定
	 */
	public static void heapSort(int[] data) {
		int len = data.length;

		/*
		 * 从最后一个非叶子节点开始到根节点，进行向下调整。
		 * 
		 * 遍历之后，得到的数组实际上是一个(最大)二叉堆。
		 */
		for (int i = len / 2 - 1; i >= 0; i--)
			maxHeapDown(data, i, len - 1);

		for (int i = len - 1; i > 0; i--) {
			// 交换a[0]和a[i]。交换后，a[i]是a[0...i]中最大的。
			int tmp = data[0];
			data[0] = data[i];
			data[i] = tmp;
			// 调整a[0...i-1]，使得a[0...i-1]仍然是一个最大堆。
			// 即，保证a[i-1]是a[0...i-1]中的最大值。
			maxHeapDown(data, 0, i - 1);
		}
	}

	// (最大)堆的向下调整算法
	public static void maxHeapDown(int[] data, int start, int end) {
		int cpos = start; // 当前节点的位置
		int lpos = 2 * cpos + 1; // 左孩子的位置
		int tmp = data[cpos]; // 当前节点值

		for (; lpos <= end; cpos = lpos, lpos = 2 * lpos + 1) {
			// "l"是左孩子，"l+1"是右孩子
			if (lpos < end && data[lpos] < data[lpos + 1])
				lpos++; // 左右两孩子中选择较大者
			if (tmp >= data[lpos])
				break; // 调整结束
			else { // 交换值
				data[cpos] = data[lpos];
				data[lpos] = tmp;
			}
		}
	}

	/*
	 * 合并排序
	 * 
	 * 平均时间复杂度：O(nlogn)
	 * 
	 * 稳定性：稳定
	 */
	public static void mergeSort(int[] data) {
		mergeSort(data, 0, data.length - 1);
	}

	private static void mergeSort(int[] a, int start, int end) {
		if (a == null || start >= end)
			return;

		int mid = (end + start) / 2;
		mergeSort(a, start, mid); // 递归排序a[start...mid]
		mergeSort(a, mid + 1, end); // 递归排序a[mid+1...end]

		// a[start...mid] 和 a[mid...end]是两个有序空间，
		// 将它们排序成一个有序空间a[start...end]
		merge(a, start, mid, end);
	}

	// 将一个数组中的两个相邻有序区间合并成一个
	private static void merge(int[] a, int start, int mid, int end) {
		int[] tmp = new int[end - start + 1]; // tmp是汇总2个有序区的临时区域
		int i = start; // 第1个有序区的索引
		int j = mid + 1; // 第2个有序区的索引
		int k = 0; // 临时区域的索引

		while (i <= mid && j <= end) {
			if (a[i] <= a[j])
				tmp[k++] = a[i++];
			else
				tmp[k++] = a[j++];
		}

		while (i <= mid)
			tmp[k++] = a[i++];

		while (j <= end)
			tmp[k++] = a[j++];

		// 将排序后的元素，全部都整合到数组a中。
		for (i = 0; i < k; i++)
			a[start + i] = tmp[i];

		tmp = null;
	}

	// 交换数组i下标元素和j下标元素
	private static void swap(int[] data, int i, int j) {
		if (i < data.length || j < data.length)
			return;
		int tmp = data[i];
		data[j] = data[i];
		data[i] = tmp;
	}

	public static void main(String[] args) {
		int i;
		int data[] = { 6, 2, 8, 5, 324, 23423, 56, 2, 87, 3, 42, 436 };
		int tmp[];

		System.out.println("== 原始顺序:");
		for (i = 0; i < data.length; i++)
			System.out.printf("%d ", data[i]);
		System.out.printf("\n");

		System.out.println("== 冒泡排序:");
		tmp=data;
		bubbleSort(tmp);
		for (i = 0; i < tmp.length; i++)
			System.out.printf("%d ", tmp[i]);
		System.out.printf("\n");

		System.out.println("== 快速排序:");
		tmp=data;
		quickSort(tmp);
		for (i = 0; i < tmp.length; i++)
			System.out.printf("%d ", tmp[i]);
		System.out.printf("\n");

		System.out.println("== 插入排序:");
		tmp=data;
		insertSort(tmp);
		for (i = 0; i < tmp.length; i++)
			System.out.printf("%d ", tmp[i]);
		System.out.printf("\n");

		System.out.println("== 希尔排序:");
		tmp=data;
		shellSort(tmp);
		for (i = 0; i < tmp.length; i++)
			System.out.printf("%d ", tmp[i]);
		System.out.printf("\n");

		System.out.println("== 选择排序:");
		tmp=data;
		selectSort(tmp);
		for (i = 0; i < tmp.length; i++)
			System.out.printf("%d ", tmp[i]);
		System.out.printf("\n");

		System.out.println("== 堆排序:");
		tmp=data;
		heapSort(tmp);
		for (i = 0; i < tmp.length; i++)
			System.out.printf("%d ", tmp[i]);
		System.out.printf("\n");

		System.out.println("== 合并排序:");
		tmp=data;
		mergeSort(tmp);
		for (i = 0; i < tmp.length; i++)
			System.out.printf("%d ", tmp[i]);
		System.out.printf("\n");
	}

}
