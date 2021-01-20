package FirstSemestr.SeventhLesson;

public class Cat {
    private String name;
    private int appetite;
    private boolean satiety;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAppetite() {
        return appetite;
    }

    public void setAppetite(int appetite) {
        this.appetite = appetite;
    }

    public boolean isSatiety() {
        return satiety;
    }

    public void eat(Plate plate) {
        if (isEnoughFood(plate)) {
            plate.decreaseFood(appetite);
            satiety = true;
            System.out.printf("%s доволен %n", name);
        } else
            System.out.printf("%s остался голодным %n", name);

    }

    public boolean isEnoughFood(Plate plate) {
        return plate.getFood() >= appetite;
    }
}
