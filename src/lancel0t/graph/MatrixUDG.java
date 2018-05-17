/**
 * 
 * 无向图(List Undirected Graph)
 * 通过邻接矩阵实现
 * 
 * @author lancel0t
 * @date 2018年5月17日
 */
package lancel0t.graph;

import java.util.Scanner;

public class MatrixUDG {

	private char[] mVexs; // 顶点集合
	private int[][] mMatrix; // 邻接矩阵

	/*
	 * 创建图(自己输入数据)
	 */
	public MatrixUDG() {

		// 输入"顶点数"和"边数"
		try (Scanner scan = new Scanner(System.in)) {

			System.out.printf("input vertex number: ");
			int vlen = scan.nextInt();
			System.out.printf("input edge number: ");
			int elen = scan.nextInt();
			if (vlen < 1 || elen < 1 || (elen > (vlen * (vlen - 1)))) {
				System.out.printf("input error: invalid parameters!\n");
				return;
			}

			// 初始化"顶点"
			mVexs = new char[vlen];
			for (int i = 0; i < mVexs.length; i++) {
				System.out.printf("vertex(%d): ", i);
				mVexs[i] = scan.next().charAt(0);
			}

			// 初始化"边"
			mMatrix = new int[vlen][vlen];
			for (int i = 0; i < elen; i++) {
				// 读取边的起始顶点和结束顶点
				System.out.printf("edge(%d):", i);
				char c1 = scan.next().charAt(0);
				char c2 = scan.next().charAt(0);
				int p1 = getPosition(c1);
				int p2 = getPosition(c2);

				if (p1 == -1 || p2 == -1) {
					System.out.printf("input error: invalid edge!\n");
					return;
				}

				mMatrix[p1][p2] = 1;
				mMatrix[p2][p1] = 1;
			}
		}
	}

	/*
	 * 创建图(用已提供的矩阵)
	 *
	 * 参数说明： vexs -- 顶点数组 edges -- 边数组
	 */
	public MatrixUDG(char[] vexs, char[][] edges) {

		// 初始化"顶点数"和"边数"
		int vlen = vexs.length;
		int elen = edges.length;

		// 初始化"顶点"
		mVexs = new char[vlen];
		for (int i = 0; i < mVexs.length; i++)
			mVexs[i] = vexs[i];

		// 初始化"边"
		mMatrix = new int[vlen][vlen];
		for (int i = 0; i < elen; i++) {
			// 读取边的起始顶点和结束顶点
			int p1 = getPosition(edges[i][0]);
			int p2 = getPosition(edges[i][1]);

			mMatrix[p1][p2] = 1;
			mMatrix[p2][p1] = 1;
		}
	}

	/*
	 * 返回ch位置
	 */
	private int getPosition(char ch) {
		for (int i = 0; i < mVexs.length; i++)
			if (mVexs[i] == ch)
				return i;
		return -1;
	}

	/*
	 * 打印矩阵队列图
	 */
	public void print() {
		System.out.printf("== Martix Graph:\n");

		System.out.print("  ");
		for (int i = 0; i < mVexs.length; i++) {
			System.out.printf("%c ", mVexs[i]);
		}
		System.out.printf("\n");

		for (int i = 0; i < mVexs.length; i++) {
			System.out.printf("%c ", mVexs[i]);
			for (int j = 0; j < mVexs.length; j++) {
				System.out.printf("%d ", mMatrix[i][j]);
			}
			System.out.printf("\n");
		}
	}

	public static void main(String[] args) {
		char[] vexs = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		char[][] edges = new char[][] { { 'A', 'C' }, { 'A', 'D' }, { 'A', 'F' }, { 'B', 'C' }, { 'C', 'D' },
				{ 'E', 'G' }, { 'F', 'G' } };

		MatrixUDG pG = new MatrixUDG(vexs, edges);
		pG.print(); // 打印图
	}

}
