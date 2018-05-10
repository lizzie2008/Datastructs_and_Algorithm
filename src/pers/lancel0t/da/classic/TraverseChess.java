/**
 * 
 * 马踏棋盘（递归+回溯）
 * 
 * @author lancel0t
 * @date 2018年5月10日
 */
package pers.lancel0t.da.classic;
import java.util.Scanner;

//马踏棋盘
public class TraverseChess {

	// 记录棋盘走位
	public int board[][] = new int[8][8];
	// fx、fy记录所有下一步可能的走法
	private int[] fx = new int[] { 1, 2, 2, 1, -1, -2, -2, -1 };
	private int[] fy = new int[] { 2, 1, -1, -2, -2, -1, 1, 2 };

	// 检查下一步走法是否有效
	private boolean check(int xx, int yy) {
		if (xx >= 0 && yy >= 0 && xx < 8 && yy < 8 && board[xx][yy] == 0)
			return true;
		return false;
	}

	// 打印结果
	private void printResult() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.format("%2d\t", board[i][j]);
			}
			System.out.println("\n");
		}
	}

	// 马踏棋盘递归+回溯查找算法
	private boolean find(int x, int y, int dept) {
		int xx, yy;
		for (int i = 0; i < 8; i++) {
			//移动到下一步
			xx = x + fx[i];
			yy = y + fy[i];

			// 如果下一步有效
			if (check(xx, yy)) {
				board[xx][yy] = dept;
				
				// 如果遍历完成，打印结果
				if (dept == 64) {
					printResult();
					return true;
				}

				// 如果遍历未完成，继续走位
				if (find(xx, yy, dept + 1)) {
					return true;
				} else{
					board[xx][yy]=0;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);		
		int row = 0, col = 0;
		System.out.println("请输入棋盘第几行：");
		row=scan.nextInt();
		System.out.println("请输入棋盘第几列：");
		col=scan.nextInt();
		scan.close();

		TraverseChess tc = new TraverseChess();

		// 给定第一个点，从下个点继续遍历
		tc.board[row - 1][col - 1] = 1;
		long beg = System.currentTimeMillis();
		System.out.println();
		if (tc.find(row - 1, col - 1, 2)) {
			long end = System.currentTimeMillis();
			System.out.format("成功！总共耗时%d秒\n", end - beg);
		} else {
			System.out.println("失败！");
		}
	}
}
