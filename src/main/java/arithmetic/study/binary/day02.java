package arithmetic.study.binary;

import org.junit.Test;

/***
 * 二分查找
 * 如何快速定位IP对应的省份？
 */
public class day02 {

    /***
     * 4种常见的二分查找变形问题
     * 1.查找第一个值等于给定值的元素
     * 2.查找最后一个值等于给定值的元素
     * 3.查找第一个大于等于给定值的元素
     * 4.查找最后一个小于等于给定值的元素
     */

    /***
     * 查找第一个值等于给定值的元素
     */
    public int method1(int[] arrays, int n, int value){
        int low = 0;
        int high = n - 1;
        int mid;

        while (low <= high){
            mid = low + ((high - low) >> 1);

            if (value > arrays[mid])
                low = mid + 1;
            else if (value < arrays[mid])
                high = mid - 1;
            else {
                if (mid==0 || value!=arrays[mid-1])
                    return mid;
                else
                    high = mid - 1;
            }
        }

        return -1;
    }

    @Test
    public void test1(){
        int[] arrays = {1, 2, 4, 6, 9, 12, 23, 23, 25, 30};
        System.out.println(method1(arrays, 10, 23));
    }

    /***
     * 查找最后一个值等于给定值的元素
     */
    public int method2(int[] arrays, int n, int value){
        int low = 0;
        int high = n - 1;
        int mid;

        while (low <= high){
            mid = low + ((high - low) >> 1);

            if (value > arrays[mid])
                low = mid + 1;
            else if (value < arrays[mid])
                high = mid - 1;
            else {
                if (mid==n-1 || value!=arrays[mid+1])
                    return mid;
                else
                    low = mid + 1;
            }
        }

        return -1;
    }

    @Test
    public void test2(){
        int[] arrays = {1, 2, 4, 6, 9, 12, 23, 23, 25, 30};
        System.out.println(method2(arrays, 10, 23));
    }

    /***
     * 查找第一个大于等于给定值的元素
     */
    public int method3(int[] arrays, int n, int value){
        int low = 0;
        int high = n - 1;
        int mid;

        while (low <= high){
            mid = low + ((high - low) >> 1);

            if (value > arrays[mid])
                low = mid + 1;
            else {
                if (mid==0 || value>arrays[mid-1])
                    return mid;
                else
                    high = mid - 1;
            }
        }

        return -1;
    }

    @Test
    public void test3(){
        int[] arrays = {1, 2, 4, 6, 9, 12, 23, 23, 25, 30};
        System.out.println(method3(arrays, 10, 10));
    }

    /***
     * 查找第一个小于等于给定值的元素
     */
    public int method4(int[] arrays, int n, int value){
        int low = 0;
        int high = n - 1;
        int mid;

        while (low <= high){
            mid = low + ((high - low) >> 1);

            if (value < arrays[mid])
                high = mid - 1;
            else {
                if (mid==n-1 || value<arrays[mid+1])
                    return mid;
                else
                    low = mid + 1;
            }
        }

        return -1;
    }

    @Test
    public void test4(){
        int[] arrays = {1, 2, 4, 6, 9, 12, 23, 23, 25, 30};
        System.out.println(method4(arrays, 10, 10));
    }
}
