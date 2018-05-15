/**
 * 
 * 二叉搜索树
 * 
 * @author lancel0t
 * @date 2018年5月14日
 */
package lancel0t.tree;

public class BSTree<T extends Comparable<T>> {

	private BSTNode root; // 根结点

	/*
	 * 二叉搜索树节点定义
	 */
	public class BSTNode {
		private T value; // 关键字(键值)
		private BSTNode lchild; // 左孩子
		private BSTNode rchild; // 右孩子
		private BSTNode parent; // 父结点

		public BSTNode(T value, BSTNode parent, BSTNode lchild, BSTNode rchild) {
			this.value = value;
			this.parent = parent;
			this.lchild = lchild;
			this.rchild = rchild;
		}
	}

	/*
	 * (递归实现)查找"二叉树x"中键值为key的节点
	 */
	private BSTNode search(BSTNode x, T value) {
		if (x == null)
			return x;

		int cmp = value.compareTo(x.value);
		if (cmp < 0)
			return search(x.lchild, value);
		else if (cmp > 0)
			return search(x.rchild, value);
		else
			return x;
	}

	public BSTNode search(T value) {
		return search(root, value);
	}

	/*
	 * 查找最小结点：返回tree为根结点的二叉树的最小结点。
	 */
	private BSTNode minimum(BSTNode tree) {
		if (tree == null)
			return null;

		while (tree.lchild != null)
			tree = tree.lchild;
		return tree;
	}

	public T minimum() {
		BSTNode p = minimum(root);
		if (p != null)
			return p.value;

		return null;
	}

	/*
	 * 查找最大结点：返回tree为根结点的二叉树的最大结点。
	 */
	private BSTNode maximum(BSTNode tree) {
		if (tree == null)
			return null;

		while (tree.rchild != null)
			tree = tree.rchild;
		return tree;
	}

	public T maximum() {
		BSTNode p = maximum(root);
		if (p != null)
			return p.value;

		return null;
	}

	/*
	 * 找结点(x)的前驱结点。即，查找"二叉树中数据值小于该结点"的"最大结点"。
	 */
	public BSTNode precursor(BSTNode node) {
		// 如果x存在左孩子，则"x的前驱结点"为 "以其左孩子为根的子树的最大结点"。
		if (node.lchild != null)
			return maximum(node.lchild);

		// 如果x没有左孩子。则x有以下两种可能：
		// (01) x是"一个右孩子"，则"x的前驱结点"为 "它的父结点"。
		// (01) x是"一个左孩子"，则往上查找父节点，且父节点的左孩子不为x，找到的这个"最低的父结点"就是"x的前驱结点"。
		BSTNode y = node.parent;
		while ((y != null) && (node == y.lchild)) {
			node = y;
			y = y.parent;
		}

		return y;
	}

	/*
	 * 找结点(x)的后继结点。即，查找"二叉树中数据值大于该结点"的"最小结点"。
	 */
	public BSTNode successor(BSTNode node) {
		// 如果x存在右孩子，则"x的后继结点"为 "以其右孩子为根的子树的最小结点"。
		if (node.rchild != null)
			return minimum(node.rchild);

		// 如果x没有右孩子。则x有以下两种可能：
		// (01) x是"一个左孩子"，则"x的后继结点"为 "它的父结点"。
		// (02) x是"一个右孩子"，则往上查找父节点，且父节点的右孩子不为x，找到的这个"最低的父结点"就是"x的后继结点"。
		BSTNode y = node.parent;
		while ((y != null) && (node == y.rchild)) {
			node = y;
			y = y.parent;
		}

		return y;
	}

	/*
	 * 将结点插入到二叉树中
	 *
	 * 参数说明： tree 二叉树的 node 插入的结点
	 */
	public void insert(BSTree<T> bst, BSTNode node) {
		int cmp;
		BSTNode y = null;
		BSTNode x = bst.root;

		// 查找z的插入位置
		while (x != null) {
			y = x;
			cmp = node.value.compareTo(x.value);
			if (cmp < 0)
				x = x.lchild;
			else
				x = x.rchild;
		}

		node.parent = y;
		if (y == null)
			bst.root = node;
		else {
			cmp = node.value.compareTo(y.value);
			if (cmp < 0)
				y.lchild = node;
			else
				y.rchild = node;
		}
	}

	public void insert(T value) {
		BSTNode node = new BSTNode(value, null, null, null);

		// 如果新建结点失败，则返回。
		if (node != null)
			insert(this, node);
	}

	/*
	 * 删除结点(node)，并返回被删除的结点
	 *
	 * 参数说明： bst 二叉树 node 删除的结点
	 */
	private BSTNode remove(BSTree<T> bst, BSTNode node) {
		BSTNode x = null;
		BSTNode y = null;

		if ((node.lchild == null) || (node.rchild == null))
			y = node;
		else
			y = successor(node);

		if (y.lchild != null)
			x = y.lchild;
		else
			x = y.rchild;

		if (x != null)
			x.parent = y.parent;

		if (y.parent == null)
			bst.root = x;
		else if (y == y.parent.lchild)
			y.parent.lchild = x;
		else
			y.parent.rchild = x;

		if (y != node)
			node.value = y.value;

		return y;
	}

	/*
	 * 删除结点(node)，并返回被删除的结点
	 *
	 * 参数说明： tree 二叉树的根结点 node 删除的结点
	 */
	public void remove(T value) {
		BSTNode node = search(root, value);
		if (node != null)
			remove(this, node);
	}

	/*
	 * 销毁二叉树
	 */
	private void destroy(BSTNode tree) {
		if (tree == null)
			return;

		if (tree.lchild != null)
			destroy(tree.lchild);
		if (tree.rchild != null)
			destroy(tree.rchild);

		tree = null;
	}

	public void destroy() {
		destroy(root);
		root = null;
	}

	/*
	 * 打印"二叉查找树"
	 *
	 * value -- 节点的键值 
	 * 
	 * direction -- 0，表示该节点是根节点; -1，表示该节点是它的父结点的左孩子;
	 * 1，表示该节点是它的父结点的右孩子。
	 */
	private void print(BSTNode tree, T key, int direction) {

		if (tree != null) {

			if (direction == 0) // tree是根节点
				System.out.printf("%2d is root\n", tree.value);
			else // tree是分支节点
				System.out.printf("%2d is %2d's %6s child\n", tree.value, key, direction == 1 ? "right" : "left");

			print(tree.lchild, tree.value, -1);
			print(tree.rchild, tree.value, 1);
		}
	}

	public void print() {
		if (root != null) {
			System.out.println("== 打印树结构: ");
			print(root, root.value, 0);
		}
	}

	public static void main(String[] args) {
		final int arr[] = { 1, 5, 4, 3, 2, 6 };
		BSTree<Integer> tree = new BSTree<Integer>();

		System.out.println("== 依次添加: ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
			tree.insert(arr[i]);
		}
		System.out.println();
		tree.print();

		System.out.println("== 最小值: " + tree.minimum());
		System.out.println("== 最大值: " + tree.maximum());

		System.out.println("== 删除节点: " + arr[1]);
		tree.remove(arr[1]);

		tree.print();
	}
}
