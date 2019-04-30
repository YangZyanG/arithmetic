package arithmetic.study.stack;

/***
 * 数组实现的栈
 */
public class ArrayStack {

    private String[] items;

    //栈的大小，即初始化数组的大小
    private int n;

    //栈中元素的个数
    private int count;

    private ArrayStack(){

    }

    public ArrayStack(int n){
        items = new String[n];
        this.n = n;
        this.count = 0;
    }

    /***
     * 入栈
     * @param item
     * @return
     */
    public boolean push(String item){
        //栈满了直接返回false
        if(count == n)
            return false;

        items[count] = item;
        ++count;
        return true;
    }

    /***
     * 出栈
     * @return
     */
    public String pop(){
        if(count == 0)
            return null;

        String item = items[count - 1];
        --count;
        return item;
    }
}
