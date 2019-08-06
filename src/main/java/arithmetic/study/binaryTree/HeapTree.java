package arithmetic.study.binaryTree;

import org.junit.Test;

import java.util.ArrayList;

/***
 * 堆
 */
public class HeapTree {

    /***
     * 堆是一种特殊的树，只要满足这两点，它就是一个堆。
     * 1.堆是一个完全二叉树
     * 2.堆中每一个节点的值都必须大于等于或小于等于其子树中每个节点的值
     */

    /***
     * 堆是一个完全二叉树，而完全二叉树比较适合用数组来储存，因为用数组来储存是非常节省储存空间的，因为我们不需要储存左右子节点的指针。
     * 如果我们把新的元素放到堆的最后，有可能就不符合堆的特性了，所以我们需要在插入的时候作出调整，这个过程就是堆化的过程。
     * 堆化实际有两种，一种是从下往上，一种是从上往下。
     * 两种堆化的原理一样，都是顺着节点所在路径，向上或向下，对比，然后交换。
     * 下面我们就以从下往上的方式实现一个堆。
     */

    private int[] heap;
    private int capacity;
    private int count;

    public HeapTree(){

    }

    public HeapTree(int[] arrays){
        heap = new int[arrays.length + 1];
        capacity = arrays.length;
        for (int i=0,length=arrays.length; i<length; ++i){
            heap[i + 1] = arrays[i];
            ++count;
        }
        buildHeap(heap, capacity);
    }

    public HeapTree(int capacity){
        //下标从1开始
        heap = new int[capacity + 1];
        this.capacity = capacity;
        count = 0;
    }

    public void insert(int data){
        if (count >= capacity)
            return;

        heap[++count] = data;
        int i = count;
        //第一个i/2判断当前节点是否是根节点，第二个i/2判断当前插入元素是否比父节点值大，这里我把i/2改成了i>>1
        while (i>>1>0 && heap[i]>heap[i>>1]){
            swap(heap, i, i>>1);
            i = i>>1;
        }
    }

    /***
     * 删除堆顶元素
     * 根据堆的特性，我们知道堆顶存放的元素是这个堆的最大值或最小值。
     * 所以我们在删除堆顶元素的时候，为了保证堆的完全二叉树的特性，我们可以将最后一个数放至堆顶，然后再将这个元素从上往下去堆化。
     */
    public void delete(){
        if (count == 0)
            return;

        heap[1] = heap[count];
        heap[count] = 0;
        --count;
        heapify(heap, count, 1);
    }

    private void swap(int[] heap, int dataIndex, int parentIndex){
        int temp = heap[parentIndex];
        heap[parentIndex] = heap[dataIndex];
        heap[dataIndex] = temp;
    }

    /***
     * 先是当前节点和左子节点进行对比交换，接着左子节点和右子节点进行对比交换
     * @param heap
     * @param count
     * @param i
     */
    private void heapify(int[] heap, int count, int i){
        while (true){
            int index = i;
            if (i<<1 <= count && heap[i] < heap[i<<1])
                index = i<<1;
            if ((i<<1) + 1 <= count && heap[index] < heap[(i<<1) + 1])
                index = (i<<1) + 1;
            if (i == index)
                break;
            swap(heap, i, index);
            i = index;
        }
    }

    public int[] getHeap(){
        return heap;
    }

    /**
     * 如何基于堆实现排序？
     * 我们把堆排序的过程大致分为两个步骤，建堆和排序。
     */
    public void buildHeap(int[] arrays, int n){
        for (int i=n>>1; i>=1; --i)
            heapify(arrays, n, i);
    }

    public void sort(){
        int k = count;

        while (k > 1){
            swap(heap, 1, k);
            --k;
            heapify(heap, k, 1);
        }
    }
}
