package _02;

/**
 * Created by yanhuche on 3/21/2017.
 */
public class Main0207RuntimeConstantPoolOOM {


    public static void main(String[] args) {
        String str1 = new StringBuilder("中国").append("钓鱼岛").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }

}
