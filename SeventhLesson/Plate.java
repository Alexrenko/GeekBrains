package FirstSemestr.SeventhLesson;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void decreaseFood(int n) {
        if (food - n < 0)
            food = 0;
        else
            food -= n;
    }

    public void info() {
        System.out.println("plate: " + food);
    }

    public void addFood(int quantity) {
        food += quantity;
    }
}
