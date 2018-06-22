package cn.los;

public class StringTest {

    public static void main(String[] args) {

        String dateStr = "20180621";

        StringBuilder sb = new StringBuilder(dateStr);// 构造一个StringBuilder对象

        sb.insert(4, "-");// 在指定的位置1，插入指定的字符串
        sb.insert(7, "-");

        dateStr = sb.toString();

        System.out.println(dateStr);

        String format = "%0" + (5 < 1 ? 1 : 5) + "d";
        System.out.println(String.format(format, 300));

    }

}
