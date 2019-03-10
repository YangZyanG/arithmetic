package arithmetic.study.complexity;

import java.util.Arrays;

/***
 * 最好、最坏、平均、均摊时间复杂度
 */
public class day04 {

    /***
     * 先说一个常用知识点，for循环中不管是i++还是++i，效果都是一样的
     * 因为for循环的执行顺序先执行是括号里的前面两个表达式，然后执行循环体，最后执行括号里的最后一个表达式，所以for循环中i++和++i是一样的。
     * 但是for循环一定用++i来表示，因为i++在计算过程中会多申请一个内存空间，所以++i的效率比i++更高
     * 直接看下面代码，其中n为数组长度，整段代码的意思一目了然，就是找出指定参数x在数组array中的下标
     * 通过前面的分析法，不难看出整段代码中只有循环与未知大小的n有关系，所以整段代码的时间复杂度是O(n)
     * 但是这里有个问题，我们在数组中查找参数x，并不需要每次都把整个数组遍历一遍，因为有可能在中途找到就可以提前结束了，所以我们优化一下代码
     * @param array
     * @param n
     * @param x
     * @return
     */
    //n为数组长度
    public int method1(int[] array, int n, int x){

        int i = 0;
        int pos = -1;

        for(; i<n; ++i){
            if(array[i] == x)
                pos = i;
        }

        return pos;
    }

    /***
     * 这是上段代码优化之后的结果，可以看出多了一步break的操作，这样就不用每次都循环完这个数组了
     * 但是问题来了，经过我们优化之后，这段代码的时间复杂度还是O(n)吗？
     * 因为要查询的变量x可能出现在数组中的任何位置。
     * 如果数组中第一个元素正好就是x，那么就不需要遍历剩下的n-1个元素了，那时间复杂度就是O(1)。
     * 但如果数组中不存在或者x正好在数组最末尾，那我们就需要把整个数组都遍历一遍，时间复杂度就是O(n)。
     * 所以不同的情况下，这段代码的时间复杂度是不一样的。
     * @param array
     * @param n
     * @param x
     * @return
     */
    public int method1_1(int[] array, int n, int x){

        int i = 0;
        int pos = -1;

        for(; i<n; ++i){
            if(array[i] == x){
                pos = i;
                break;
            }
        }

        return pos;
    }

    /***
     * 概念引入
     * 最好情况时间复杂度就是，在最理想的情况下，执行这段代码的复杂度。就像上面的情况，变量x刚好在数组中第一位。
     * 最坏情况时间复杂度就是，在最糟糕的情况下，执行这段代码的复杂度。也拿上面的情况讲，变量x不存在或者在数组最末尾。
     */

    /***
     * 平均情况时间复杂度
     * 我们都知道，最好情况时间复杂度和最坏情况时间复杂度对应的都是极端情况下的时间复杂度，发生的概率其实并不大。
     * 为了更好地表示平均情况下的复杂度，我们需要引入另一个概念：平均情况时间复杂度，简称平均时间复杂度。
     * 还是用上面的例子：
     * 要查找变量x在数组中的位置，有n+1种情况(在数组0~n-1位置中和不在数组中)，我们把每种情况下查找需要遍历的次数累加起来再除以n+1，就可以得到遍历次数的平均值，即
     * 1+2+3+4+...+n+n/n+1 -> (n(n+1)/2 + n)/n+1 -> n(n+3)/2(n+1)
     * 那么最终的结果就是n(n+3)/2(n+1)，换成大O表示法，系数常数什么忽略，得到的平均时间复杂度就是O(n)
     * 这个结论虽然是正确的，但是计算过程稍微有点问题，究竟是什么问题呢？
     * 我们刚才说到变量x的位置一共有n+1种情况，但是这n+1种情况出现的概率并不是一样的，我们来分析分析。
     * 我们要找的变量x，要么在数组里，要么不在数组里，所以在或不在数组里的概率都是1/2。
     * 假如x在数组中，那么它出现在0~n-1这n个位置上的概率是一样的，都是1/n
     * 所以，x在数组中任意位置的概率是1/2n，不在数组中的概率是1/2，那么刚才那个公式应该改为：
     * 1 * 1/2n + 2 * 1/2n + 3 * 1/2n + 4 * 1/2n +...+ n * 1/2n + n * 1/2 -> (1+2+3+4+...+n) * 1/2n + n/2 -> n(n+1)/2 * 1/2n + n/2 -> (3n+1)/4
     * 最终的结果就是(3n+1)/4，用大O表示法表示还是O(n)，这个值就是概论中的加权平均值，也叫期望值。
     * 所以平均时间复杂度的全称叫加权平均时间复杂度或者期望时间复杂度。
     */


    /***
     * 均摊时间复杂度
     * 听起来跟平均时间复杂度有点像，对于初学者来说，这两个概念确实非常容易混淆。
     * 大部分情况下，我们不需要区分最好、最坏、平均时间复杂度，平均时间复杂度只在某些特殊情况下才会用到，而均摊时间复杂度应用场景更特殊、更有限。
     */
    int[] array;
    int count;

    private day04(){}

    public day04(int n){
        array = new int[n];
        count = 0;
    }

    /***
     * 看如下代码，代码实现了一个数据插入数组的功能，如果数组满了，然后对数据所有值求和，并将和放在数组第一位，然后再将新的数据插入数组。如果没满，则直接将元素放入数组。
     * 我们用刚讲的三种时间复杂度的分析方法来分析一下
     * 最好的情况，数组中有空闲位置，则直接将元素放入数组，复杂度为O(1)
     * 最坏的情况，数组满了，最需要遍历数组，复杂度为O(n)
     * 平均的情况，数组长度是n，根据插入位置的不同，一共有n种情况，每种情况的复杂度都是O(1)。
     * 还有一种额外的情况，数组满了，那么这个时候的复杂度是O(n)，那么综上所述一共就有n+1种情况，且发生的概率都一样。
     * 所以我们得到平均时间复杂度的公式为：
     * 1 * 1/n+1 + 1 * 1/n+1 + ... + 1 * 1/n+1 + n * 1/n+1 -> 2n/n+1 -> O(1)
     *
     * @param val
     */
    public void method2(int val){

        if(count == array.length){
            int sum = 0;
            for(int i=0, size=array.length; i<size; ++i){
                sum = sum + array[i];
            }
            array[0] = sum;
            count = 1;
        }

        array[count] = val;
        ++count;
    }

    /***
     * 均摊时间复杂度和平均时间复杂度的区别
     * 以上面两种方法作为参考，大致有两点不同的地方
     * 1.method1只有在极端情况下复杂度才为O(1)，而method2大部分情况下复杂度都是O(1)，极端情况下才为O(n)
     * 2.对于method2来讲，O(1)时间复杂度的出现和O(n)时间复杂度的出现时非常有规律的，一般在O(n)出现后，会有n-1个O(1)
     * 所以针对这种特殊的场景，我们就用均摊时间复杂度分析法来分析
     */


    int[] arrays = new int[10];
    int len = 10;
    int i = 0;

    /***
     * 课后练习
     * 当i<len时，直接执行，所以最好情况时间复杂度是O(1)
     * 当i>len时，假如len为n，那么for循环一共会执行n次，所以最坏时间复杂度是O(n)
     * 分析代码可知，一次O(n)出现后，len为2n，后面就会出现2n-1次O(1)，当i=2n时，又会执行循环
     * 所以直接用均摊时间分析法，每种情况出现的概率1/n，除了最后一次，其他时候代码只执行一次，所以复杂度为1 * 1/n + 1 * 1/n +...+ n * 1/n -> O(1)
     * @param element
     */
    void add(int element){
        if(i >= len){
            int new_array[] = new int[len*2];

            for(int j=0; j<len; ++j){
                new_array[j] = arrays[j];
            }

            arrays = new_array;
            len = len * 2;
        }

        array[i] = element;
        ++i;
    }
}
