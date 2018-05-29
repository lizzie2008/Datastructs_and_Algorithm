/**
 * 
 * 顺时针打印矩阵
 * 
 * @author lancel0t
 * @date 2018年5月14日
 */
package lancel0t.classic;

public class PrintMatrixClockwisly {

	private int[][] matrix;

	public PrintMatrixClockwisly(int[][] matrix) {
		this.matrix = matrix;
	}

	public void print() {

		int rows = matrix.length;
		if (rows <= 0)
			return;
		int cols = matrix[0].length;
		if (cols <= 0)
			return;

		int start = 0;
		System.out.println("== 顺时针打印矩阵： ");
		while (rows > start * 2 && cols > start * 2) {
			PrintMatrixInCircle(matrix, rows, cols, start);
			++start;
		}
	}

	private void PrintMatrixInCircle(int[][] matrix, int rows, int cols, int start) {

		int endX = cols - 1 - start;
		int endY = rows - 1 - start;

		// 从左向右打印一行
		for (int i = start; i <= endX; i++) {
			System.out.print(matrix[start][i] + " ");
		}

		// 从上到下打印一列
		if (start < endY) {
			for (int i = start + 1; i <= endY; i++) {
				System.out.print(matrix[i][endX] + " ");
			}
		}

		// 从右到左打印一行
		if (start < endX && start < endY) {
			for (int i = endX - 1; i >= start; i--) {
				System.out.print(matrix[endY][i] + " ");
			}
		}

		// 从下到上打印一列
		if (start < endX && start < endY - 1) {
			for (int i = endY - 1; i >= start + 1; i--) {
				System.out.print(matrix[i][start] + " ");
			}
		}

	}

	public static void main(String[] args) {

		int matrix[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		PrintMatrixClockwisly pmc = new PrintMatrixClockwisly(matrix);
		pmc.print();
	}

}
