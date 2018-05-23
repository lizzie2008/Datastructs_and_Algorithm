/**
 * 
 * 【剑指Offer】面试题3 ：二维数组中的查找
 * 【  题目描述 】在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * 
 * @author lancel0t
 * @date 2018年5月22日
 */
package lancel0t.codinginterviews;

public class Example03 {

	/*
	 * 定义数组下标i,j始终指向矩阵右上角的位置
	 * 如果matrix[i][j]大于目标值，往前面的列 查找
	 * 如果matrix[i][j]小于目标值，往后面的行查找
	 */
	public static boolean find(int[][] matrix, int number) {
		// 有效性检查
		if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0)
			return false;

		int rows = matrix.length;
		int cols = matrix[0].length;

		// i,j始终指向矩阵右上角的位置
		for (int i = 0, j = cols - 1; i <= rows - 1 && j >= 0;) {
			if (matrix[i][j] > number) {
				j--;
			} else if (matrix[i][j] < number) {
				i++;
			} else {
				return true;
			}
		}

		return false;
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 8, 9 }, { 2, 4, 9, 12 }, { 4, 7, 10, 13 }, { 6, 8, 11, 15 } };
		System.out.println(find(matrix, 7)); // 要查找的数在数组中
		System.out.println(find(matrix, 5)); // 要查找的数不在数组中
		System.out.println(find(matrix, 1)); // 要查找的数是数组中最小的数字
		System.out.println(find(matrix, 15)); // 要查找的数是数组中最大的数字
		System.out.println(find(matrix, 0)); // 要查找的数比数组中最小的数字还小
		System.out.println(find(matrix, 16)); // 要查找的数比数组中最大的数字还大
		System.out.println(find(null, 16)); // 健壮性测试，输入空指针
	}

}
