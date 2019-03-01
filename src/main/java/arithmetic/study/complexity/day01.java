package arithmetic.study.complexity;

/***
 * 复杂度分析入门
 * 大O复杂度表示法
 */
public class day01 {

    /***
     * 下面代码中假设每运行一行代码需要消耗的时间是t
     * 那么整个方法需要消耗的时间就是 (2+2n)*t
     * 我们用T(n)来表示整个方法需要运行的时间
     * 尽管t的具体值我们不知道，可以看出消耗的总时间T(n)是跟n的大小成正比的
     * 那么用大O复杂度表示法就可以表示为 T(n)=O(f(n))，即T(n)=O(2+2n)
     * T(n)我们讲过了表示总时间，n表示数据规模，f(n)表示代码执行总次数，公式中的O就表示代码的执行时间T(n)与f(n)表达是成正比
     *
     * 当n很大时，公式中的低阶、常量、系数三部分并不能左右增长趋势，所以都可以忽略，我们只需要记录一个最大量就可以了。
     * 所以这个方法中最终时间复杂度记为 T(n)=O(n)
     * @param n
     * @return
     */
    public int method1(int n){

        int sum = 0;
        int i = 0;

        for(; i<n; i++){
            sum = sum + i;
        }

        return sum;
    }


    /***
     * T(n)=O(f(n)) ==> T(n)=O(3+2n+2n²)
     * 最终为 T(n)=O(n²)
     * @param n
     * @return
     */
    public int method2(int n){

        int sum = 0;
        int i = 0;
        int j = 0;

        for(; i<n; i++){
            j = 0;
            for(; j<n; j++){
                sum = j + sum;
            }
        }
        return sum;
    }


    /***
     * 复杂度分析实用方法三则
     * 大O这种复杂度表示方法只是表示一种变化趋势
     * 我们通常会忽略掉公式中的常数、低阶、系数，只需要记录一个最大阶的量级就可以了
     */
    /***
     * 复杂度分析方法一：只关注循环执行次数最多的一段代码
     * 即下面代码只需要关注j循环即可，复杂度为O(n)
     * @param n
     * @return
     */
    public int method3(int n){

        int sum = 0;
        int i = 0;
        int j = 0;

        for(; i<100; i++){
            sum = sum + i;
        }

        for(; j<n; j++){
            sum = sum + j;
        }

        return sum;
    }


    /***
     * 复杂度分析方法二：总复杂度等于量级最大的那段代码的复杂度
     * 下面代码只需关注量级最大的那段代码，第一个循环复杂度是O(n)，第二段代码复杂度是O(n²)
     * 所以这段代码复杂度就等于量级最大的那段代码复杂度，即O(n²)
     * @param n
     * @return
     */
    public int method4(int n){

        int sum = 0;
        int i = 0;
        int j = 0;

        for(; i<n; i++){
            sum = sum + i;
        }

        for(; i<n; i++){
            for(; j<n; j++){
                sum = sum + j;
            }
        }

        return sum;
    }


    /***
     * 嵌套代码的复杂度等于嵌套内外代码复杂度的乘积
     * 下面代码中method5代码的复杂度是O(n)
     * method5循环中又调用了method5_1，而method5_1的复杂度是O(n)
     * 所以method5整段代码的复杂度是O(n²)
     * @param n
     * @return
     */
    public int method5(int n){

        int ret = 0;
        int i = 0;
        for(; i<n; i++){
            ret = method5_1(n) + ret;
        }
        return ret;
    }

    public int method5_1(int m){

        int sum = 0;
        int i = 0;
        for(; i<m; i++){
            sum = i + sum;
        }
        return sum;
    }
}
