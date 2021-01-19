package FirstSemestr.SixthLesson;

import FirstSemestr.SixthLesson.Animals.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        showHomework();
    }

    public static void showHomework() {
        AnimalFactory factoryOne = new AnimalFactory();
        AnimalFactory factoryTwo = new AnimalFactory();
        for (int i = 0; i < 10; i++) {
            factoryOne.createCat();
            factoryOne.createDog();
        }
        ArrayList<Animal> animals = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
               animals.add(factoryTwo.createCat());
               animals.add(factoryTwo.createDog());
        }

        System.out.println("На первой фабрике, всего животных: " + factoryOne.getAnimalCount());
        System.out.println("На первой фабрике, котов: " + factoryOne.getCatCount());
        System.out.println("На первой фабрике, собак: " + factoryOne.getDogCount());

        System.out.println("На второй фабрике, всего животных: " + factoryTwo.getAnimalCount());
        System.out.println("На второй фабрике, котов: " + factoryTwo.getCatCount());
        System.out.println("На второй фабрике, собак: " + factoryTwo.getDogCount());

        animals.get(0).run(1000);
        animals.get(1).swim(50);
    }
}
