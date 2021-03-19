package homework;

import java.io.FileReader;
import java.util.*;

public class MyCollection<E extends Comparable>{
    public int size=0;
    public Node<E> first;
    public Node<E> last;
    private Node<E> iter=first;
    public MyCollection() {

    }



    //内部类用作构造节点对象
    private class Node<E>{
        E item;
        Node<E> next;
        Node<E> pre;

        Node (Node<E> pre,E element,Node<E> next){
            this.item=element;
            this.pre=pre;
            this.next=next;
        }
    }

    public void add(E element){
        Node<E> l =last;
        Node<E> newNode=new Node<>(l,element,null);
        if(last!=null)
            last.next=newNode;
        if(first==null)
            first=newNode;
        last=newNode;
        size++;
    }

    public void insert(int index,E element){
        if(index>=size)
            System.out.println("下标超过界限,插入失败！");
        if(index==0){
            Node<E> p=first;
            final Node<E> newNode=new Node<>(null,element,p);
            p.pre=newNode;
            first=newNode;
            size++;
        }

        if(index<size&&index>0){
            Node<E> p=first;
            for(int i=0;i<index;i++){
                p=p.next;
            }
            final Node<E> newNode=new Node<>(p.pre,element,p);
            p.pre.next=newNode;
            p.pre=newNode;
            size++;
        }
    }

    public E remove(int index){
        if(index<0||index>size-1) {
            System.out.println("下标超出界限,移除失败！");
            System.exit(0);
        }
        if(index==0){
            final Node<E> p=first;
            p.next.pre=null;
            first=p.next;
            size--;
            return p.item;
        }
        else{
            Node<E>p=first;
            for(int i=0;i<index;i++){
                p=p.next;
            }
            p.pre.next=p.next;
            p.next.pre=p.pre;
            size--;
            return p.item;
        }
    }

    public E get(int index){
        if(index>=size) {
            System.out.println("超出下标界限，查询失败！");
            System.exit(0);
        }
        Node<E> p=first;
        for(int i=0;i<index;i++){
            p=p.next;
        }
        return p.item;
    }

    public String toString(){
        String s="[ ";
        Node<E> p=first;
        for(int i=0;i<size;i++){
            s=s+p.item;
            p=p.next;
            if(i<size-1)
                s=s+",";
        }
        s+=" ]";
        return s;
    }
}
