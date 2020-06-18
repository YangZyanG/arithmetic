package arithmetic.study.test;

import org.junit.Test;

/**
 * @author yangziyang
 * @since 2020-06-18
 */
public class BinaryTest {

    /***
     * 简单二分查找的正常实现
     * 该方法只适用于不喊重复元素的数组，未找到返回-1
     * @param array 数组
     * @param value 要查找的值
     * @return
     */
    public int binarySearch(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        int mid;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (value == array[mid])
                return mid;
            else if (value > mid)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }

    @Test
    public void test_1() {
        int[] array = new int[10000];
        for (int i = 0; i < 10000; ++i) {
            array[i] = i;
        }
        int result = binarySearch(array, 12);
        System.out.println(result);
    }

    /***
     * 简单二分查找的递归实现
     * @param array
     * @param value
     * @return
     */
    public int binarySearchRecursion(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        return binarySearchRecursion(array, value, low, high);
    }

    public int binarySearchRecursion(int[] array, int value, int low, int high) {
        if (low > high)
            return -1;

        int mid = low + ((high - low) >> 1);
        if (value == array[mid])
            return mid;
        else if (value > array[mid])
            return binarySearchRecursion(array, value, mid + 1, high);
        else
            return binarySearchRecursion(array, value, low, mid - 1);
    }

    @Test
    public void test_2() {
        int[] array = new int[10000];
        for (int i = 0; i < 10000; ++i) {
            array[i] = i;
        }
        int result = binarySearchRecursion(array, 1000);
        System.out.println(result);
    }

    /***
     * 查找第一个值等于给定值的元素
     * 数组中存在重复元素，那么从这些重复元素中找到第一个元素的坐标
     * @param array
     * @param value
     * @return
     */
    public int binarySearch_1(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        int mid;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (value > array[mid])
                low = mid + 1;
            else if (value < array[mid])
                high = mid - 1;
            else {
                if (mid == 0 || value != array[mid - 1])
                    return mid;
                else
                    high = mid - 1;
            }
        }
        return -1;
    }

    /***
     * 查找最后一个值等于给定值的元素
     * 数组中存在重复元素，那么从这些重复元素中找到最后一个元素的坐标
     * @param array
     * @param value
     * @return
     */
    public int binarySearch_2(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        int mid;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (value > array[mid])
                low = mid + 1;
            else if (value < array[mid])
                high = mid - 1;
            else {
                if (mid == array.length - 1 || value != array[mid + 1])
                    return mid;
                else
                    low = mid + 1;
            }
        }
        return -1;
    }

    /***
     * 查找出第一个大于等于给定值的元素
     * @param array
     * @param value
     * @return
     */
    public int binarySearch_3(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        int mid;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (value > array[mid])
                low = mid + 1;
            else {
                if (mid == 0 || value > array[mid - 1])
                    return mid;
                else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /***
     * 查找最后一个小于等于给定值的元素
     * @param array
     * @param value
     * @return
     */
    public int binarySearch_4(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        int mid;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (value < array[mid])
                high = mid - 1;
            else {
                if (mid == array.length - 1 || value < array[mid + 1])
                    return mid;
                else
                    low = mid + 1;
            }
        }
        return -1;
    }

    /***
     * 注意
     * 其中有多处判断的判断条件为mid==0或mid==array.length-1
     * 这种判断条件是表明给定值比整个数组的所有值都大或都小
     */
}
