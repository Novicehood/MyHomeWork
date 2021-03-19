package homework;

public class Test {
    public static void main(String[] args) {
        MyCollection<String> col=new MyCollection<>();
        col.add("123");
        col.add("456");
        col.add("wu");
        col.insert(0,"99");
        col.remove(1);
        System.out.println(col);
    }
}
