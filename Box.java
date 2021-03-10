package FirstSemestr.Java3.Lesson_1;

import FirstSemestr.Java3.Lesson_1.Fruits.Fruit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Box<T extends Fruit> {
    private final ArrayList<T> fruits = new ArrayList<>();

    public Box() {
    }

    public Box(T... fruits) {
        add(fruits);
    }

    public void add(T... fruits) {
        this.fruits.addAll(Arrays.asList(fruits));
    }

    public void remove(T fruit) {
        fruits.remove(fruit);
    }

    public void show() {
        System.out.println(this);
    }

    public Float getWeight() {
        Float sum = 0.0f;
        for (T fruit : fruits) {
            sum += fruit.getWeight();
        }
        return sum;
    }

    public boolean compare(Box box) {
        return this.getWeight().equals(box.getWeight());
    }

    public void pourOver(Box<T> box) {
        for (T fruit : fruits) {
            box.add(fruit);
        }
        fruits.clear();
    }

    @Override
    public String toString() {
        return fruits.toString();
    }
}
