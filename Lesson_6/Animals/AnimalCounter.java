package FirstSemestr.SixthLesson.Animals;

public class AnimalCounter {
    private int count;
    private int catCount;
    private int dogCount;

    public int getAnimalCount() {
        return count;
    }

    public int getCatCount() {
        return catCount;
    }

    public int getDogCount() {
        return dogCount;
    }

    public void addAnimalCount() {
        this.count++;
    }

    public void addCatCount() {
        this.catCount++;
    }

    public void addDogCount() {
        this.dogCount++;
    }
}
