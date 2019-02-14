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
     * 
     */
}
