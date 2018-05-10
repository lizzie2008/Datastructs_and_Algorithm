/**
 * 
 * 无回路有向图(Directed Acyclic Graph)的拓扑排序
 * 通过邻接表实现的
 * 
 * @author lancel0t
 * @date 2018年5月10日
 */
package lancel0t.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 邻接表
public class ListDG {

	// 邻接表中表顶点的定义
	private class VNode {
		char data; // 顶点信息
		ENode firstEdge; // 指向第一条依附该顶点的弧
	};

	// 邻接表中表边的定义
	private class ENode {
		int vex; // 该边所指向的顶点的位置
		ENode nextEdge; // 指向下一条弧的指针
	}

	// 顶点数组
	private List<VNode> vertexs;

	// 初始化邻接表
	public ListDG(char[] vexs, char[][] edges) {

		// 初始化"顶点数"和"边数"
		int vlen = vexs.length;
		int elen = edges.length;

		// 初始化"顶点"
		this.vertexs = new ArrayList<VNode>();

		for (int i = 0; i < vlen; i++) {
			// 新建VNode
			VNode vnode = new VNode();
			vnode.data = vexs[i];
			vnode.firstEdge = null;
			// 将vnode添加到数组vexs中
			this.vertexs.add(vnode);
		}

		// 初始化"边"
		for (int i = 0; i < elen; i++) {
			// 读取边的起始顶点和结束顶点
			char c1 = edges[i][0];
			char c2 = edges[i][1];
			// 读取边的起始顶点和结束顶点
			int p1 = getPosition(c1);
			int p2 = getPosition(c2);

			// 初始化node1
			ENode node1 = new ENode();
			node1.vex = p2;
			// 将node1链接到"p1所在链表的末尾"
			if (this.vertexs.get(p1).firstEdge == null) {
				this.vertexs.get(p1).firstEdge = node1;
			} else {
				ENode p = this.vertexs.get(p1).firstEdge;
				while (p.nextEdge != null)
					p = p.nextEdge;
				p.nextEdge = node1;
			}
		}
	}

	// 返回ch位置
	private int getPosition(char ch) {
		for (int i = 0; i < vertexs.size(); i++)
			if (vertexs.get(i).data == ch)
				return i;
		return -1;
	}

	// 深度优先搜索遍历图的递归实现
	private void DFS(int i, boolean[] visited) {
		ENode node;

		visited[i] = true;
		System.out.printf("%c ", vertexs.get(i).data);
		node = vertexs.get(i).firstEdge;
		while (node != null) {
			if (!visited[node.vex])
				DFS(node.vex, visited);
			node = node.nextEdge;
		}
	}

	// 深度优先搜索遍历图
	public void DFS() {
		boolean[] visited = new boolean[vertexs.size()]; // 顶点访问标记

		System.out.printf("== DFS: ");
		for (int i = 0; i < vertexs.size(); i++) {
			if (!visited[i])
				DFS(i, visited);
		}
		System.out.println();
	}

	// 广度优先搜索（类似于树的层次遍历）
	public void BFS() {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[vertexs.size()]; // 顶点访问标记

		System.out.printf("== BFS: ");
		for (int i = 0; i < vertexs.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				System.out.printf("%c ", vertexs.get(i).data);
				queue.offer(i); // 入队列
			}

			while (queue.size() > 0) {
				int j = queue.poll(); // 出队列
				ENode node = vertexs.get(j).firstEdge;
				while (node != null) {
					int k = node.vex;
					if (!visited[k]) {
						visited[k] = true;
						System.out.printf("%c ", vertexs.get(k).data);
						queue.offer(k);
					}
					node = node.nextEdge;
				}
			}
		}
		System.out.println();
	}

	// 打印邻接表
	public void print() {
		System.out.printf("== 邻接表:\n");
		for (int i = 0; i < vertexs.size(); i++) {
			System.out.printf("%d(%c): ", i, vertexs.get(i).data);
			ENode node = vertexs.get(i).firstEdge;
			while (node != null) {
				System.out.printf("%d(%c) ", node.vex, vertexs.get(node.vex).data);
				node = node.nextEdge;
			}
			System.out.println();
		}
	}

	/*
	 * 拓扑排序
	 *
	 * 返回值： -1 -- 失败(由于内存不足等原因导致) 0 -- 成功排序，并输入结果 1 -- 失败(该有向图是有环的)
	 */
	public int topologicalSort() {
		int index = 0;
		int num = vertexs.size();
		int[] ins; // 入度数组
		char[] tops; // 拓扑排序结果数组，记录每个节点的排序后的序号。
		Queue<Integer> queue; // 辅组队列

		ins = new int[num];
		tops = new char[num];
		queue = new LinkedList<Integer>();

		// 统计每个顶点的入度数
		for (int i = 0; i < num; i++) {

			ENode node = vertexs.get(i).firstEdge;
			while (node != null) {
				ins[node.vex]++;
				node = node.nextEdge;
			}
		}

		// 将所有入度为0的顶点入队列
		for (int i = 0; i < num; i++)
			if (ins[i] == 0)
				queue.offer(i); // 入队列

		while (!queue.isEmpty()) { // 队列非空
			int j = queue.poll().intValue(); // 出队列。j是顶点的序号
			tops[index++] = vertexs.get(j).data; // 将该顶点添加到tops中，tops是排序结果
			ENode node = vertexs.get(j).firstEdge;// 获取以该顶点为起点的出边队列

			// 将与"node"关联的节点的入度减1；
			// 若减1之后，该节点的入度为0；则将该节点添加到队列中。
			while (node != null) {
				// 将节点(序号为node.ivex)的入度减1。
				ins[node.vex]--;
				// 若节点的入度为0，则将其"入队列"
				if (ins[node.vex] == 0)
					queue.offer(node.vex); // 入队列

				node = node.nextEdge;
			}
		}

		if (index != num) {
			System.out.println("有向图是有环的");
			return 1;
		}

		// 打印拓扑排序结果
		System.out.printf("== 拓扑排序: ");
		for (int i = 0; i < num; i++)
			System.out.printf("%c ", tops[i]);
		System.out.println();

		return 0;
	}

	public static void main(String[] args) {

		char[] vexs = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		char[][] edges = new char[][] { { 'A', 'G' }, { 'B', 'A' }, { 'B', 'D' }, { 'C', 'F' }, { 'C', 'G' },
				{ 'D', 'E' }, { 'D', 'F' } };
		ListDG graph;

		// 根据顶点和边构造有向图
		graph = new ListDG(vexs, edges);
		graph.print();

		graph.DFS(); // 深度优先遍历
		graph.BFS(); // 广度优先遍历

		graph.topologicalSort();
	}
}
