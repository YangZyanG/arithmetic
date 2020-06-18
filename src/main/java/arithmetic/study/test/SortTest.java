package arithmetic.study.test;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yangziyang
 * @since 2020-04-23
 */
public class SortTest {

    public int[] bubbleSort(int[] arrays) {
        if (ArrayUtils.isNotEmpty(arrays)) {
            for (int i = 0; i < arrays.length; ++i) {
                boolean isCompleted = true;
                for (int j = 0; j < arrays.length - i - 1; ++j) {
                    if (arrays[j] > arrays[j + 1]) {
                        int temp = arrays[j];
                        arrays[j] = arrays[j + 1];
                        arrays[j + 1] = temp;
                        isCompleted = false;
                    }
                }
                if (isCompleted) {
                    break;
                }
            }
        }
        return arrays;
    }

    @Test
    public void bubbleSortTest() {
        int[] arrays = new int[]{9, 6, 3, 7, 8, 5, 1, 2, 4, 0};
        System.out.println(Arrays.toString(arrays));
        arrays = bubbleSort(arrays);
        System.out.println(Arrays.toString(arrays));
    }

    public int[] insertionSort(int[] arrays) {
        if (ArrayUtils.isNotEmpty(arrays)) {
            if (arrays.length <= 1)
                return arrays;

            for (int i = 1; i < arrays.length; ++i) {
                int value = arrays[i];
                int j = i - 1;
                for (; j >= 0; --j) {
                    if (arrays[j] > value) {
                        arrays[j + 1] = arrays[j];
                    } else {
                        //如果arrays[j] <= value，循环可以直接结束，因为array[j]前面的区间已经是有序的了，array[j]是当前已排序区间中最大的值了。
                        break;
                    }
                }
                arrays[j + 1] = value;
            }
        }
        return arrays;
    }

    @Test
    public void insertionSortTest() {
        int[] arrays = new int[]{9, 6, 3, 7, 8, 5, 1, 2, 4, 0};
        System.out.println(Arrays.toString(arrays));
        arrays = insertionSort(arrays);
        System.out.println(Arrays.toString(arrays));
    }

    public int[] selectionSort(int[] arrays) {
        if (ArrayUtils.isNotEmpty(arrays)) {
            if (arrays.length <= 1)
                return arrays;

            for (int i = 0; i < arrays.length; ++i) {
                int j = i + 1;
                int min = i;
                int value = arrays[i];

                for (; j < arrays.length; ++j) {
                    if (arrays[min] > arrays[j]) {
                        min = j;
                    }
                }

                if (min != i) {
                    arrays[i] = arrays[min];
                    arrays[min] = value;
                }
            }
        }
        return arrays;
    }

    @Test
    public void selectionSortTest() {
        int[] arrays = new int[]{9, 6, 3, 7, 8, 5, 1, 2, 4, 0};
        System.out.println(Arrays.toString(arrays));
        arrays = selectionSort(arrays);
        System.out.println(Arrays.toString(arrays));
    }

    public int[] mergeSort(){
        List list = new ArrayList();
        return null;
    }
}
