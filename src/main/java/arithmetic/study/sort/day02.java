package arithmetic.study.sort;

import org.junit.Test;

import java.util.Arrays;

/***
 * 上一节讲了冒泡排序、插入排序和选择排序这三种算法，它们的时间复杂度都是O(n²)，比较高，适合小规模数据的排序。
 * 今天，我们将两个时间复杂度为O(nlogn)的排序算法，归并排序和快速排序。这两种算法适合大规模的数据排序，比上一节讲的三种算法更常用。
 */
public class day02 {

    /***
     * 归并排序的原理
     * 归并排序的核心思想还蛮简单的，如果要排序一个数组，我们先把数组从中间分成前后两个部分，然后对前后两个部分分别排序。
     * 再将排好序的两部分合并在一起，这样整个数组就有序了。
     * 归并排序使用的时分治思想。分治，顾名思义，就是分而治之，就是把大问题分解成小的子问题，小的子问题解决了，大问题也就解决了。
     * 分治算法一般都是用递归来实现的，分治是一种解决问题的处理思想，递归是一种编程技巧。
     */

    /***
     * 如何用递归来实现归并排序？
     * 写递归的技巧就是，分析得出递推公式，然后找到终止条件，最后将递推公式换成递归代码。
     * 下面是归并排序的递推公式：
     * merge_sort(p...r) = merge(merge_sort(p...q), merge_sort(q+1...r));
     * merge_sort(p...r)表示给从下标p到r之间的数组排序，我们将这个排序问题转化为两个子问题merge_sort(p...q)、merge_sort(q+1...r)。
     * 其中下标q等于p和r的中间位置，也就是(p+r)/2。
     */
    @Test
    public void method1(){
        int[] arrays = {3, 4, 12, 3, 7, 1, 9, 6};
        System.out.println(Arrays.toString(arrays));
        mergeSort(arrays, arrays.length);
        System.out.println(Arrays.toString(arrays));
    }

    public void mergeSort(int[] arrays, int n){
        merge(arrays, 0, n-1);
    }

    /***
     * 递归，让原数组分成许多个小区间
     * @param arrays
     * @param p
     * @param r
     */
    public void merge(int[] arrays, int p, int r){
        if (p < r){
            int q = (p+r)/2;
            merge(arrays, p, q);
            merge(arrays, q+1, r);
            merge(arrays, p, q, r);
        }
    }

    /***
     * 对区间进行排序，排序后的值赋给原数组
     * @param arrays
     * @param p
     * @param q
     * @param r
     */
    public void merge(int[] arrays, int p, int q, int r){
        int i = p ,k = 0;
        int j = q + 1;
        int[] temp = new int[r - p + 1];

        while (i<=q && j<=r){
            if (arrays[i] <= arrays[j])
                temp[k++] = arrays[i++];
            else
                temp[k++] = arrays[j++];
        }

        while (i <= q)
            temp[k++] = arrays[i++];
        while (j <= r)
            temp[k++] = arrays[j++];

        for (int n=0; n<temp.length; ++n){
            arrays[n + p] = temp[n];
        }
    }

    /***
     * 快速排序的原理
     * 快排也是利用分治思想，乍看之下，它有点像归并排序，但是思路完全不一样。
     * 核心思想：
     * 在数组中找到一个pivot(分区点)，然后我们遍历这个数组，将小于pivot放左边，大于pivot放右边，这样就分成了两个区间。
     * 然后两个区间在重复上面的操作，找pivot，然后将小于pivot放左边，大于pivot放右边，知道区间缩小为1。
     */
    @Test
    public void method2(){
        int[] arrays = {3, 4, 12, 3, 7, 1, 9, 6};
        System.out.println(Arrays.toString(arrays));
        quickSort(arrays, arrays.length);
        System.out.println(Arrays.toString(arrays));
    }

    public void quickSort(int[] arrays, int n){
        quick(arrays, 0, n - 1);
    }

    public void quick(int[] arrays, int p, int r){
        if (p >= r)
            return;

        int q = partition(arrays, p, r);
        quick(arrays, p, q-1);
        quick(arrays, q+1, r);
    }

    public int partition(int[] arrays, int p, int r){
        int q = arrays[r];
        int i = p, j = p;
        int temp = arrays[i];

        for (; j<r; ++j){
            if (arrays[j] < q){
                arrays[i] = arrays[j];
                arrays[j] = temp;
                ++i;
                temp = arrays[i];
            }
        }

        arrays[i] = q;
        arrays[r] = temp;
        return i;
    }

    /***
     * 快速排序和归并排序都是分治思想，递推公式和递归代码也非常的相似，那它们的区别究竟在哪呢？
     * 归并排序的处理过程是由下到上，先处理子问题，然后再合并。
     * 快速排序正好相反，处理过程是由上到下，先分区处理，然后再对子问题分区处理。
     * 归并排序算法是一种在任何情况下时间复杂度都比较稳定的算法，这也是它存在的致命的缺点，且归并排序不是原地排序，空间复杂度较高，这也是它没有快速排序应用广泛的原因。
     */
}
