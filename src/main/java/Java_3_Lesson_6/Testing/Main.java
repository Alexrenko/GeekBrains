package Java_3_Lesson_6.Testing;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] array = {1, 3, 4, 1, 4, 0, -3, 8};
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(Four.getAllAfterFour(array)));
        boolean checkOneOrFour = Four.checkOneAndFour(array);
        System.out.println("В массиве есть хоть одна четверка или единица? " + checkOneOrFour);
    }


}
