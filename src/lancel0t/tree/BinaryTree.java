/**
 * 
 * 二叉树
 * 
 * @author lancel0t
 * @date 2018年5月10日
 */
package lancel0t.tree;

import java.util.Arrays;
import java.util.List;

public class BinaryTree {

	// 二叉树节点定义
	public class BinaryTreeNode<T> {
		// 节点值
		private T value;
		// 左孩子
		private BinaryTreeNode<T> lChild;
		// 右孩子
		private BinaryTreeNode<T> rChild;
	}

	// 根据前序遍历和后续遍历，构造二叉树
	public <T> BinaryTreeNode<T> constuct(List<T> preOrderList, List<T> inOrderList) {

		if (preOrderList == null || inOrderList == null || preOrderList.size() != inOrderList.size())
			return null;

		int length = preOrderList.size();

		if (length > 0) {
			// 前序遍历第1个节点为根节点
			T root = preOrderList.get(0);
			BinaryTreeNode<T> node = new BinaryTreeNode<T>();
			node.value = root;
			// 获取根节点在中序遍历的索引位置
			int index = inOrderList.indexOf(root);
			// 构建左子树
			List<T> preOrderLeft = preOrderList.subList(1, 1 + index);
			List<T> inOrderLeft = inOrderList.subList(0, index);
			node.lChild = constuct(preOrderLeft, inOrderLeft);

			List<T> preOrderRight = preOrderList.subList(1 + index, length);
			List<T> inOrderRight = inOrderList.subList(index + 1, length);
			node.rChild = constuct(preOrderRight, inOrderRight);

			return node;
		}
		return null;
	}

	// 前序遍历
	public <T> void preOrder(BinaryTreeNode<T> root) {
		if (root != null) {
			System.out.print(root.value.toString() + " ");
			preOrder(root.lChild);
			preOrder(root.rChild);
		}
	}

	// 中序遍历
	public <T> void inOrder(BinaryTreeNode<T> root) {
		if (root != null) {
			inOrder(root.lChild);
			System.out.print(root.value.toString() + " ");
			inOrder(root.rChild);
		}
	}

	// 后序遍历
	public <T> void postOrder(BinaryTreeNode<T> root) {
		if (root != null) {
			postOrder(root.lChild);
			postOrder(root.rChild);
			System.out.print(root.value.toString() + " ");
		}
	}

	public static void main(String[] args) {

		BinaryTree tree = new BinaryTree();

		// 由前序和中序遍历来构造树
		List<Integer> preOrderList = Arrays.asList(1, 2, 4, 7, 3, 5, 6, 8);
		List<Integer> inOrderList = Arrays.asList(4, 7, 2, 1, 5, 3, 8, 6);
		BinaryTreeNode<Integer> root = tree.constuct(preOrderList, inOrderList);

		System.out.println("== 前序遍历：");
		tree.preOrder(root);
		System.out.println();
		System.out.println("== 中序遍历：");
		tree.inOrder(root);
		System.out.println();
		System.out.println("== 后序遍历：");
		tree.postOrder(root);
		System.out.println();
	}

}
