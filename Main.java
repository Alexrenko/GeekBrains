package FirstSemestr.Java3.Lesson_1;

import FirstSemestr.Java3.Lesson_1.Fruits.Apple;
import FirstSemestr.Java3.Lesson_1.Fruits.Fruit;
import FirstSemestr.Java3.Lesson_1.Fruits.Orange;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
 * b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта, поэтому в
 *    одну коробку нельзя сложить и яблоки, и апельсины;
 * c. Для хранения фруктов внутри коробки можете использовать ArrayList;
 * d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта
 *    (вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
 * e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую
 *    подадут в compare в качестве параметра, true - если их веса равны, false в противном случае(коробки с
 *    яблоками мы можем сравнивать с коробками с апельсинами);
 * f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку(помним про
 *    сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами), соответственно в текущей коробке
 *    фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
 * g. Не забываем про метод добавления фрукта в коробку.
 */

public class Main {
    public static void main(String[] args) {

        //task1();
        //task2();
        task3();

    }

    private static void task1() {
        Integer[] arrayIntegers = new Integer[] {1, 2, 3, 4, 5, 6};
        String[] arrayStrings = new String[] {"first", "second", "third", "fourth"};

        Integer[] arr = new Integer[10];
        ArrayUtils.swap(arr, 0, 5);

        ArrayUtils.swap(arrayIntegers, 0, 5);
        ArrayUtils.swap(arrayStrings, 0, 1);

        ArrayUtils.printArray(arrayIntegers);
        ArrayUtils.printArray(arrayStrings);
    }

    private static void task2() {
        Integer[] arrayIntegers = new Integer[] {1, 2, 3, 4, 5, 6};
        String[] arrayStrings = new String[] {"first", "second", "third", "fourth"};

        ArrayList listIntegers = ArrayUtils.toList(arrayIntegers);
        ArrayList listStrings = ArrayUtils.toList(arrayStrings);

        System.out.println(listIntegers.toString());
        System.out.println(listStrings.toString());
    }

    private static void task3() {
        Box<Orange> orangeBox = new Box<>(new Orange(), new Orange());  //2 апельсина, 3.0f
        Box<Apple> appleBox1 = new Box<>(new Apple(), new Apple());   //2 яблока, 2.0f
        Box<Apple> appleBox2 = new Box<>(new Apple(), new Apple(), new Apple());   //3 яблока, 3.0f

        System.out.println(new ArrayList<>(Arrays.asList(orangeBox, appleBox1, appleBox2)));

        System.out.println("Вес orangeBox равен весу appleBox1? " + orangeBox.compare(appleBox1));
        System.out.println("Вес orangeBox равен весу appleBox2? " + orangeBox.compare(appleBox2));

        appleBox1.pourOver(appleBox2);

        System.out.println(new ArrayList<>(Arrays.asList(orangeBox, appleBox1, appleBox2)));

    }
}
