package arithmetic.study.binary;

import org.junit.Test;

import java.util.ArrayList;

/***
 * 二分查找
 */
public class day01 {

    /***
     * 二分查找原理
     * 二分查找针对的是一个有序的数据集合，查找的思想有点类似分治。
     * 每次都通过跟区间的中间元素做对比，然后将待查找区间缩小为原来的一半，直到找到要查找的元素，或者区间缩小为0。
     *
     * O(logn)惊人的查找速度
     * 二分查找是我们目前为止遇到的第一个时间复杂度为 O(logn)的算法。
     */

    /***
     * 非递归实现二分
     * @param arrays
     * @param n  数组元素个数
     * @param value  待查找的元素
     */
    public int method1(int[] arrays, int n, int value){
        int low = 0;
        int high = n - 1;
        int mid;

        while (low <= high){
            mid = (low + high) / 2;

            if (value == arrays[mid])
                return mid;
            else if (value > arrays[mid])
                low = mid + 1;
            else
                high = mid - 1;
        }

        return -1;
    }

    /***
     * 递归实现
     * 在计算中值mid的时候，mid=(low+high)/2这种写法是有问题的，如果low和high的值比较大的话，两者之和是有可能溢出的。
     * 改进的方法是low+(high-low)/2，如果想优化到极致的话，可以直接用位运算，low+(high-low)>>1
     * @param arrays
     * @param n
     * @param value
     * @return
     */
    public int method2(int[] arrays, int n, int value){
        return method2_recursion(arrays, 0, n - 1, value);
    }

    public int method2_recursion(int[] arrays, int low, int high, int value){
        if (low > high)
            return -1;

        int mid = low + ((high - low) >> 1);
        if (value == arrays[mid])
            return mid;
        else if (value > arrays[mid])
            return method2_recursion(arrays, mid + 1, high, value);
        else
            return method2_recursion(arrays, low, mid - 1, value);
    }

    @Test
    public void test1(){
        int[] arrays = new int[100000];
        for (int i=0; i<100000; ++i){
            arrays[i] = i;
        }

//        System.out.println(method1(arrays, 100000, 85434));
        System.out.println(method2(arrays, 100000, 79822));
    }

    /***
     * 二分查找应用的局限性
     * 1.首先，二分查找依赖的是顺序表结构，并且能友好的支持随便访问的数据结构，说白了就是数组，arrayList按道理讲也是可行的。
     * 2.二分查找只针对有序数据。
     * 3.数据量大小不适合二分查找，直接遍历就足够了。
     * 4.数据量太大不适合二分查找，因为存储大数组已经占用了很大一部分内存了，这时候也就不建议用二分查找了。
     */
}
