package cn.los;

public class MainTest {

    public static void main(String[] args) {

        int[] ids = new int[] { 136, 137 };
        int[] arr = new int[ids.length];
        int i = 0;

        for (int id : ids) {

            arr[i] = 7;
            i++;

        }
        System.out.println(arr.toString());

    }

}
