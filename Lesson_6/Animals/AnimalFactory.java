package FirstSemestr.SixthLesson.Animals;

public class AnimalFactory {
    AnimalCounter counter = new AnimalCounter();

    public Cat createCat() {
        counter.addAnimalCount();
        counter.addCatCount();
        return new Cat();
    }

    public Dog createDog() {
        counter.addAnimalCount();
        counter.addDogCount();
        return new Dog();
    }

    public int getAnimalCount() {
        return counter.getAnimalCount();
    }

    public int getCatCount() {
        return counter.getCatCount();
    }

    public int getDogCount() {
        return counter.getDogCount();
    }
}
