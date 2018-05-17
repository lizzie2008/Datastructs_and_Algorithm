/**
 * 
 * Trie字典树（前缀树）
 * 
 * @author lancel0t
 * @date 2018年5月16日
 */
package lancel0t.tree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TrieTree {

	private TrieNode root;

	public TrieTree() {
		root = new TrieNode();
	}

	/*
	 * Trie树节点
	 */
	public class TrieNode {
		// 26个字符，也就是26叉树
		public TrieNode[] childNodes;

		// 词频统计
		public int freq;

		// 记录该节点的字符
		public char nodeChar;

		public TrieNode() {
			childNodes = new TrieNode[26];
			freq = 0;
		}
	}

	/*
	 * 插入操作
	 */
	public void AddTrieNode(String word) {
		AddTrieNode(this.root, word);
	}

	private void AddTrieNode(TrieNode root, String word) {
		if (word.length() == 0)
			return;

		// 求字符地址，方便将该字符放入到26叉树中的哪一叉中
		int k = word.charAt(0) - 'a';

		// 如果该叉树为空，则初始化
		if (root.childNodes[k] == null) {
			root.childNodes[k] = new TrieNode();

			// 记录下字符
			root.childNodes[k].nodeChar = word.charAt(0);
		}

		String nextWord = word.substring(1);

		// 说明是最后一个字符，统计该词出现的次数
		if (nextWord.length() == 0)
			root.childNodes[k].freq++;

		AddTrieNode(root.childNodes[k], nextWord);
	}

	/*
	 * 删除操作
	 */
	public void DeleteTrieNode(String word) {
		DeleteTrieNode(this.root, word);
	}

	private void DeleteTrieNode(TrieNode root, String word) {
		if (word.length() == 0)
			return;

		// 求字符地址，方便将该字符放入到26叉树种的哪一颗树中
		int k = word.charAt(0) - 'a';

		// 如果该叉树为空,则说明没有找到要删除的点
		if (root.childNodes[k] == null)
			return;

		String nextWord = word.substring(1);

		// 如果是最后一个单词，则减去词频
		if (word.length() == 0 && root.childNodes[k].freq > 0)
			root.childNodes[k].freq--;

		DeleteTrieNode(root.childNodes[k], nextWord);
	}

	/*
	 * 查找操作
	 */
	public boolean SearchTrie(String word) {
		char[] chs = word.toLowerCase().toCharArray();
		for (int i = 0, length = chs.length; i < length; i++) {
			int index = chs[i] - 'a';
			if (root.childNodes[index] == null) {
				/// 如果不存在，则查找失败
				return false;
			}
			root = root.childNodes[index];
		}
		return true;
	}

	public static void main(String[] args) throws IOException {

		// 加载常用英文词汇表
		InputStreamReader isr = new InputStreamReader(new FileInputStream("data/words.txt"), "UTF-16");
		BufferedReader read = new BufferedReader(isr);

		TrieTree trie = new TrieTree();
		String str;
		while ((str = read.readLine()) != null) {

			String[] ss = str.split(" ");
			String node = ss[1].toLowerCase().trim();
			trie.AddTrieNode(node);
		}
		read.close();

		System.out.println("== 字典树中查找again结果：" + trie.SearchTrie("again"));
		System.out.println("== 字典树中查找ttttt结果：" + trie.SearchTrie("ttttt"));
	}
}
