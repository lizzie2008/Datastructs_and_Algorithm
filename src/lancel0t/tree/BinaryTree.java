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
import java.util.Stack;

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

	// 前序遍历（递归实现）
	public <T> void preOrderRecursive(BinaryTreeNode<T> root) {
		if (root != null) {
			System.out.print(root.value + " ");
			preOrderRecursive(root.lChild);
			preOrderRecursive(root.rChild);
		}
	}

	// 前序遍历（优化实现）
	public <T> void preOrder(BinaryTreeNode<T> root) {
		Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
		while (root != null || !stack.isEmpty()) {
			if (root != null) {
				// 访问节点并入栈
				System.out.print(root.value + " ");
				stack.push(root);
				// 访问左孩子
				root = root.lChild;
			} else {
				// 回退根节点，访问右孩子
				root = stack.pop();
				root = root.rChild;
			}
		}
	}

	// 中序遍历（递归实现）
	public <T> void inOrderRecursive(BinaryTreeNode<T> root) {
		if (root != null) {
			inOrderRecursive(root.lChild);
			System.out.print(root.value + " ");
			inOrderRecursive(root.rChild);
		}
	}

	// 中序遍历（优化实现）
	public <T> void inOrder(BinaryTreeNode<T> root) {
		Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
		while (root != null || !stack.isEmpty()) {
			if (root != null) {
				// 节点入栈，访问左孩子
				stack.push(root);
				root = root.lChild;
			} else {
				// 回退根节点，访问右孩子
				root = stack.pop();
				System.out.print(root.value + " ");
				root = root.rChild;
			}
		}
	}

	// 后序遍历（递归实现）
	public <T> void postOrderRecursive(BinaryTreeNode<T> root) {
		if (root != null) {
			postOrderRecursive(root.lChild);
			postOrderRecursive(root.rChild);
			System.out.print(root.value + " ");
		}
	}

	// 后序遍历（优化实现）
	public <T> void postOrder(BinaryTreeNode<T> root) {
		if (root == null)
			return;
		Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
		Stack<BinaryTreeNode<T>> output = new Stack<BinaryTreeNode<T>>();
		stack.push(root);
		while (!stack.isEmpty()) {
			BinaryTreeNode<T> curr = stack.pop();
			output.push(curr);
			if (curr.lChild != null)
				stack.push(curr.lChild);
			if (curr.rChild != null)
				stack.push(curr.rChild);
		}
		while (!output.isEmpty()) {
			System.out.print(output.pop().value + " ");
		}
	}

	public static void main(String[] args) {

		BinaryTree tree = new BinaryTree();

		// 由前序和中序遍历来构造树
		List<Integer> preOrderList = Arrays.asList(1, 2, 4, 7, 3, 5, 6, 8);
		List<Integer> inOrderList = Arrays.asList(4, 7, 2, 1, 5, 3, 8, 6);
		BinaryTreeNode<Integer> root = tree.constuct(preOrderList, inOrderList);

		System.out.println("== 前序遍历：");
		tree.preOrderRecursive(root);
		System.out.println();
		tree.preOrder(root);
		System.out.println();
		System.out.println("== 中序遍历：");
		tree.inOrderRecursive(root);
		System.out.println();
		tree.inOrder(root);
		System.out.println();
		System.out.println("== 后序遍历：");
		tree.postOrderRecursive(root);
		System.out.println();
		tree.postOrder(root);
		System.out.println();
	}

}
