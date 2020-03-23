package arithmetic.study.graph;

import java.util.Arrays;

/**
 * @author yangziyang
 * @since 2020-01-08
 */
public class MatrixArray {

    private int[][] array;
    private int vertex;

    private MatrixArray(){

    }

    /**
     * 根据顶点数初始化二维数组
     * @param vertex
     */
    public MatrixArray(int vertex){
        this.vertex = vertex;
        this.array = new int[vertex][vertex];
    }

    /***
     * 模仿加好友，即两个顶点建度
     * @param v1
     * @param v2
     */
    public void add(int v1, int v2){
        if (check(v1, v2))
            return;

        array[v1][v2] = array[v2][v1] = 1;
    }

    public void delete(int v1, int v2){
        if (check(v1, v2))
            return;

        array[v1][v2] = array[v2][v1] = 0;
    }

    public boolean check(int v1, int v2){
        if (v1 > vertex || v2 > vertex)
            return true;
        return false;
    }

    public void print(){
        for (int i=0; i<vertex; ++i){
            System.out.println(Arrays.toString(array[i]));
        }
    }
}
