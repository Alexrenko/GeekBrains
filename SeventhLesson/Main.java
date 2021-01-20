package FirstSemestr.SeventhLesson;

public class Main {
    public static void main(String[] args) {
        StringBuilder  name = new StringBuilder(8);
        Plate plate = new Plate(100);
        Cat[] cats = new Cat[6];
        for (int i = 0; i < cats.length; i++) {
            name.append("Кот ").append(i);
            cats[i] = new Cat(name.toString(), 20);
            cats[i].eat(plate);
            name.setLength(0);
        }
        plate.addFood(100);
        plate.info();
        cats[5].eat(plate);
    }
}
