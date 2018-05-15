/**
 * 
 * 二叉堆(最小堆)
 * 
 * @author lancel0t
 * @date 2018年5月15日
 */
package lancel0t.heap;

import java.util.ArrayList;
import java.util.List;

public class MinHeap<T extends Comparable<T>> {

    private List<T> heapList;        // 存放堆的数组

    public MinHeap() {
        this.heapList = new ArrayList<T>();
    }

    /*
     * 最小堆的向上调整算法
     */
    protected void filterup(int start) {
        int cpos = start;            // 当前节点的位置
        int ppos = (cpos-1)/2;        // 父结点的位置 
        T tmp = heapList.get(cpos);        // 当前节点值

        while(cpos > 0) {
            int cmp = heapList.get(ppos).compareTo(tmp);
            if(cmp <= 0)
                break;
            else {
                heapList.set(cpos, heapList.get(ppos));
                cpos = ppos;
                ppos = (ppos-1)/2;   
            }       
        }
        heapList.set(cpos, tmp);
    }
 
    /* 
     * 插入元素
     */
    public void insert(T data) {
        int size = heapList.size();

        heapList.add(data);    // 将"数组"插在表尾
        filterup(size);        // 向上调整堆
    }
    
    /* 
     * 最小堆的向下调整算法
     */
    protected void filterdown(int start, int end) {
        int cpos = start;          // 当前节点的位置
        int lpos = 2*cpos + 1;     // 左孩子的位置
        T tmp = heapList.get(cpos);    // 当前节点值

        while(lpos <= end) {
            int cmp = heapList.get(lpos).compareTo(heapList.get(lpos+1));
            // "l"是左孩子，"l+1"是右孩子
            if(lpos < end && cmp>0)
                lpos++;        // 左右两孩子中选择较小者

            cmp = tmp.compareTo(heapList.get(lpos));
            if(cmp <= 0)
                break;        //调整结束
            else {
                heapList.set(cpos, heapList.get(lpos));
                cpos = lpos;
                lpos = 2*lpos + 1;   
            }       
        }   
        heapList.set(cpos, tmp);
    }
     
    /*
     * 删除元素
     */
    public int remove(T data) {
        // 如果"堆"已空，则返回-1
        if(heapList.isEmpty() == true)
            return -1;

        // 获取data在数组中的索引
        int index = heapList.indexOf(data);
        if (index==-1)
            return -1;

        int size = heapList.size();
        heapList.set(index, heapList.get(size-1));// 用最后元素填补
        heapList.remove(size - 1);                // 删除最后的元素

        if (heapList.size() > 1)
            filterdown(index, heapList.size()-1);    // 从index号位置开始自上向下调整为最小堆

        return 0;
    }
    
    @Override 
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<heapList.size(); i++)
            sb.append(heapList.get(i) +" ");

        return sb.toString();
    }

    public static void main(String[] args) {
        int i;
        int a[] = {80, 40, 30, 60, 90, 70, 10, 50, 20};
        MinHeap<Integer> tree=new MinHeap<Integer>();

        System.out.printf("== 依次添加: ");
        for(i=0; i<a.length; i++) {
            System.out.printf("%d ", a[i]);
            tree.insert(a[i]);
        }

        System.out.printf("\n== 最小堆     : %s", tree);

        i=15;
        tree.insert(i);
        System.out.printf("\n== 添加元素: %d", i);
        System.out.printf("\n== 最小堆     : %s", tree);

        i=10;
        tree.remove(i);
        System.out.printf("\n== 删除元素: %d", i);
        System.out.printf("\n== 最小堆     : %s", tree);
        System.out.printf("\n");
    }
}
