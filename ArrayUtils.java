package FirstSemestr.Java3.Lesson_1;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayUtils {

    public static <T> void swap(T[] array, int a, int b) {
        T objA = array[a];
        T objB = array[b];
        array[a] = objB;
        array[b] = objA;
    }

    public static <T> ArrayList<T> toList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }

    public static <T> void printArray(T[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
