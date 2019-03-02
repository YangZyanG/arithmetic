package arithmetic.study.complexity;

/***
 * 几种常见时间复杂度实例分析
 * 对于复杂度量级，可以粗略的分为两类，多项式量级和非多项式量级
 */
public class day02 {


    /***
     * O(1)
     * 只要代码执行时间不随n增大而增长，这样代码的时间复杂度都是O(1)
     * 一般情况下，只要代码中不存在循环语句或者循环语句的循环次数无n无关（如下代码）、递归语句，
     * 即使有成千上万行代码，其时间复杂度也是O(1)
     * @return
     */
    public int method1(int n){

        int i = 0;
        int sum = 0;

        for(; i<100; i++){
            sum = sum + n;
        }

        return sum;
    }

    /***
     * O(logn)
     * 对数阶时间复杂度非常常见，同时也是最难分析的一种时间复杂度
     * 根据我们前面讲的复杂度分析方法，第三段代码是循环执行次数最多的一段，
     * 所以我们只要能计算出这段代码被执行了多少次，就能知道这段代码的时间复杂度了。
     * 观察后我们可以发现，i的值是等比增加的，2¹，2²,2³...2^x，我们只要知道x的值是多少，我们就可以知道代码的执行次数了
     * 我们可以得出等式2^x=n，通过对数我们可得x=log²n，所以这段代码的时间复杂度就是O(log²n)
     * @param n
     * @return
     */
    public int method2(int n){

        int i = 1;

        while (i < n){
            i = i * 2;
        }

        return i;
    }

    /***
     * O(logn)
     * 根据上面代码实践，我们可以看出这段代码中i值变化的规律，3¹,3²,3³...3^x
     * 得出等式3^x=n，x=log³n，所以这段代码的时间复杂度就是O(log³n)
     * 实际上，不管底数是多少，我们都把对数阶的时间复杂度记为O(logn)
     * 例如log³n=log³2*log²n，其中log³2为常数项，可以忽略，其次是用大O表示法时，我们可以忽略系数，所以log²n=log³n
     * 所以这段代码和上一段代码的时间复杂度都为O(logn)
     * @param n
     * @return
     */
    public int method3(int n){

        int i = 1;

        while (i < n){
            i = i * 3;
        }

        return i;
    }

    /***
     * O(nlogn)
     * 根据我们前面学习的复杂度分析法，嵌套代码的复杂度等于嵌套内外代码的乘积
     * 第一段代码循环的复杂度是O(n)
     * 第二段代码的复杂度是O(logn)
     * 所以整个第一段代码的复杂度就是O(n)*O(logn)=O(nlogn)
     * O(nlogn)也是一种非常常见的算法时间复杂度，比如归并排序、快速排序。
     * @param n
     * @return
     */
    public int method4(int n){

        int i = 1;
        int sum = 0;

        for(; i<n; i++){
            sum = sum + method4_1(n);
        }
        return sum;
    }

    public int method4_1(int n){

        int i = 1;

        while (i < n){
            i = i * 2;
        }

        return i;
    }

    /***
     * O(m+n)、O(m*n)
     * 这种代码的时间复杂度不能用前面的方法进行分析，因为你不能判断m和n谁的量级大
     * 这种时候只需要将不确定数据规模的时间复杂度相加或相乘就行
     * @param n
     * @param m
     * @return
     */
    public int method5(int n, int m){

        int i = 0;
        int sum_n = 0;

        for(; i<n; i++){
            sum_n = sum_n + i;
        }

        int j = 0;
        int sum_m = 0;

        for(; j<m; j++){
            sum_m = sum_m + j;
        }

        return sum_n + sum_m;
    }
}
