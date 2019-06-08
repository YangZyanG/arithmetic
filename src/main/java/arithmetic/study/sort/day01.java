package arithmetic.study.sort;

import org.junit.Test;

import java.util.Arrays;

/***
 * 为什么插入排序比冒泡排序更受欢迎？
 * 插入排序和冒泡排序的时间复杂度都相同，都是O(n²)，那为什么我们更倾向于用插入排序呢？
 */
public class day01 {

    /***
     * 如何分析一个排序算法？
     * 学习排序算法，我们除了学习它的算法原理、代码实现之外，更重要的是学会如何评价、分析一个排序算法。
     * 那分析一个排序算法，要从哪几方面入手呢？
     */

    /***
     * 第一点：排序算法的执行效率
     * 1.最好情况、最坏情况、平均情况时间复杂度
     * 2.时间复杂度的系数、常数、低阶
     * 3.比较次数和交换(移动)次数
     */

    /***
     * 第二点：排序算法的内存消耗
     * 我们前面讲过，算法的内存消耗可以通过空间复杂度来衡量，排序算法也不例外。
     * 不过针对排序算法的空间复杂度，我们还要引入一个新的概念，原地排序。
     * 原地排序算法，就是特指空间复杂度是O(1)的排序算法。
     */

    /***
     * 第三点：排序算法的稳定性
     * 仅仅通过执行效率和内存消耗来衡量排序算法的好坏是不够的。
     * 针对排序算法，我们还有一个更重要的指标，稳定性。
     * 这个概念是说，如果待排序的序列中存在值相等的元素，经过排序之后，相等元素之间原有的先后顺序不变。
     *
     * 我通过一个例子来解释一下。
     * 比如我们有一组数据 2，9，3，4，8，3，按照大小排序之后就是2，3，3，4，8，9。
     * 这组数据里有两个3，经过某种排序算法排序之后，如果两个3的前后顺序没变，那我们就把这种排序算法叫做稳定的排序算法。
     * 如果两个3的前后顺序变了，那对应的排序算法就叫做不稳定的排序算法。
     */

    /***
     * 冒泡排序
     * 冒泡排序只会操作相邻的两个数据，看是否满足大小关系要求，如果不满足就让它俩互换。
     * 一次冒泡排序至少让一个元素移动到它应该在的位置，重复n次，就完成了n个数据的排序工作。
     * 实际上，冒泡排序还可以进一步优化，当某次冒泡排序没有数据交换时，说明已经达到完全有序了，就可以直接退出循环了。
     *
     * 关于冒泡排序的几个问题
     * 1.冒泡排序是原地排序算法吗？
     * 冒泡排序的过程只涉及到相邻两个数据的交换，只需要常量级的空间，所以空间复杂度是O(1)，是一个原地排序算法。
     * 2.冒泡排序是稳定算法吗？
     * 冒泡排序只会交换符合大小要求的相邻两个元素，当两个元素相等时，冒泡排序不做交换，所以冒泡排序是稳定算法。
     * 3.冒泡排序的时间复杂度是多少？
     * 最好情况只执行一次冒泡，即O(n)，最坏情况会进行n次冒泡，每次冒泡时里面的循环又是n次，所以是O(n²)。
     *
     */
    @Test
    public void method1(){
        int[] arrays = {4, 12, 7, 1, 9, 3, 6};
        System.out.println(Arrays.toString(arrays));
        arrays = bubbleSort(arrays);
        System.out.println(Arrays.toString(arrays));
    }

    public int[] bubbleSort(int[] arrays){

        for (int i=0; i<arrays.length; ++i){
            //一共要进行length次冒泡，每次冒泡必定有数据交换，如果没有则说明已经有序，可以提前结束排序
            boolean isSort = false;
            System.out.println(i);
            for (int j=0; j<arrays.length - i -1; ++j){

                if(arrays[j] > arrays[j+1]){
                    int temp = arrays[j];
                    arrays[j] = arrays[j+1];
                    arrays[j+1] = temp;
                    isSort = true;
                }

            }

            if (!isSort)
                break;
        }
        return arrays;
    }

    /***
     * 插入排序
     * 我们先来看一个问题。
     * 一个有序的数组，我们往里面添加一个新的数据后，如何继续保持数据的有序性呢？
     * 很简单，我们只要遍历数组，找到数据应该插入的位置将其插入即可。
     * 插入排序就是借鉴上面的思想来进行排序的。
     * 首先，我们将数组中的数据分为两个区间，已排序区和未排序区。初始已排序区的元素就只有一个，就是数组的第一个元素。
     * 插入算法的核心思想是取未排序区中的元素，在已排序区中找到合适的位置将其插入，并保证已排序区数据一直有序。重复这个过程，知道未排序区元素为空，算法结束。
     *
     * 关于插入排序的几个问题
     * 1.插入排序是原地排序算法吗？
     * 插入排序并不需要额外的储存空间，所以空间复杂度是O(1)，是原地排序算法。
     * 2.插入排序是稳定的排序算法吗？
     * 插入排序的时候，两个元素值相同时不会移动，所以是稳定的排序算法。
     * 3.插入排序的时间复杂度是多少？
     * 最好是O(n)，最坏是O(n²)。
     */

    @Test
    public void method2(){
        int[] arrays = {4, 12, 7, 1, 9, 3, 6};
        System.out.println(Arrays.toString(arrays));
        arrays = insertSort(arrays, arrays.length);
        System.out.println(Arrays.toString(arrays));
    }
    /***
     * n表示数组大小
     * i相当于排序区和未排序区的边界，j表示从边界的前一个元素开始往数组头遍历
     * @param arrays
     * @param n
     * @return
     */
    public int[] insertSort(int[] arrays, int n){
        if (n <= 1)
            return arrays;

        for (int i=1; i<n; ++i){
            int value = arrays[i];
            int j = i - 1;

            for (; j>=0; --j){
                if (arrays[j] > value){
                    arrays[j+1] = arrays[j];
                }else
                    break;
            }

            arrays[j+1] = value;
        }
        return arrays;
    }

    /***
     * 选择排序
     * 选择排序算法的思路有点类似插入排序，也分为已排序区和未排序区。
     * 但选择排序每次从未排序区间中找到最小的元素，将其放在已排序区的末尾。
     *
     * 关于选择排序的三个问题
     * 1.选择排序是原地排序算法吗？
     * 选择排序不额外申请空间，所以空间复杂度是O(1)，是原地排序算法。
     * 2.选择排序是稳定的算法吗？
     * 如下例子，第一次循环，第一个3就和1互换，所以两个3的前后顺序变了，不是稳定的算法。
     * 3.选择排序的时间复杂度是多少？
     * 最好是O(n²)，最坏是O(n²)。
     */
    @Test
    public void method3(){
        int[] arrays = {3, 4, 12, 3, 7, 1, 9, 6};
        System.out.println(Arrays.toString(arrays));
        arrays = choiceSort(arrays, arrays.length);
        System.out.println(Arrays.toString(arrays));
    }

    /***
     * 从0开始排序，直到倒数第二个数，最后一个数不用排序，排完倒数第二个数之后，倒数第二个数和最后一个数的大小位置已经确定。
     * j从第二个数开始，直到最后一个数。
     * @param arrays
     * @param n
     * @return
     */
    public int[] choiceSort(int[] arrays, int n){

        for (int i=0; i<n-1; ++i){

            int j = i + 1;
            int value = arrays[i];
            int minIndex = i;

            for (; j<n; ++j){
                if (arrays[minIndex] > arrays[j]){
                    minIndex = j;
                }
            }

            if (minIndex != i){
                arrays[i] = arrays[minIndex];
                arrays[minIndex] = value;
            }
        }

        return arrays;
    }

    /***
     * 希尔排序
     */
    @Test
    public void method4(){
        int[] arrays = {3, 4, 12, 3, 7, 1, 9, 6};
        System.out.println(Arrays.toString(arrays));
        arrays = shellSort(arrays);
        System.out.println(Arrays.toString(arrays));
    }

    public int[] shellSort(int[] arrays){

        for (int gap=arrays.length/2; gap>0; gap/=2){

            for (int i = gap; i < arrays.length; i++) {

                int j = i;
                int temp = arrays[j];

                // j - step 就是代表与它同组隔壁的元素
                while (j - gap >= 0 && arrays[j - gap] > temp) {
                    arrays[j] = arrays[j - gap];
                    j = j - gap;
                }
                arrays[j] = temp;
            }
        }

        return arrays;
    }
}
