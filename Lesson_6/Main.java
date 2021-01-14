package FirstSemestr.SixthLesson;

public class Main {
    public static void main(String[] args) {
        showHomework();
    }

    public static void showHomework() {
        Dog sharik = new Dog("Шарик");
        Cat matroskin = new Cat("Матроскин");

        sharik.run(100);
        sharik.run(99999999);
        sharik.run(-555);

        sharik.swim(5);
        sharik.swim(99999999);
        sharik.swim(-555);

        matroskin.run(100);
        matroskin.run(99999999);
        matroskin.run(-555);

        matroskin.swim(10);

        System.out.println("Количество животных: " + Animal.getAnimalCount());
        System.out.println("Количество котов: " + Cat.catCount);
        System.out.println("Количество собак: " + Dog.dogCount);
    }
}
