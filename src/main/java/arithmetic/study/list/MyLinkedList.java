package arithmetic.study.list;

/***
 * 模仿LinkedList，实现双向链表功能
 */
public class MyLinkedList<E> {

    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;

    private void linkFirst(E e){
        final Node<E> f = first;
        final Node<E> node = new Node<E>(null, e, f);
        first = node;
        if(null == f)
            last = node;
        else
            f.prev = node;
        size++;

    }

    private void linkLast(E e){
        final Node<E> l = last;
        final Node<E> node = new Node<E>(l, e, null);
        last = node;
        if(null == l)
            first = node;
        else
            l.next = node;
        size++;
    }

    private void linkBefore(E e, Node<E> next){
        Node<E> prev = next.prev;
        Node<E> node = new Node<E>(prev, e, next);
        next.prev = node;
        if(null == prev)
            first = node;
        else
            prev.next = node;
        size++;
    }

    public void add(E e){
        linkLast(e);
    }

    public void add(int index, E e){
        checkIndex(index);

        if(index == (size-1))
            linkLast(e);
        else
            linkBefore(e, node(index));
    }

    public void addFirst(E e){
        linkFirst(e);
    }

    public void addLast(E e){
        linkLast(e);
    }

    /***
     * 获取指定下标的node元素
     * 判断插入下标在整个list的前半部分还是后半部分，然后根据结果选择是从头遍历还是尾遍历
     * @param index
     * @return
     */
    Node<E> node(int index){
        Node<E> node;
        if(index < (size >> 1)){
            node = first;
            for(int i=0; i<index; ++i)
                node = node.next;
        }else{
            node = last;
            for(int i=size-1; i>index; --i)
                node = node.prev;
        }
        return node;
    }

    private void checkIndex(int index){
        if(index<0 || index>size)
            throw new RuntimeException("out of Bounds msg");
    }

    /***
     * 结点类
     * data 数据
     * prev 前驱结点
     * next 后继结点
     * @param <E>
     */
    private static class Node<E>{

        E data;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E data, Node<E> next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }
}
