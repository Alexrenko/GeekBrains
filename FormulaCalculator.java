package FirstSemestr.Java2.Lesson_5;

public class FormulaCalculator implements Runnable{
    float[] array;

    public FormulaCalculator(float[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length; i++) {
            array[i] = (float)(array[i] * Math.sin(0.2f + i / 5f)
                    * Math.cos(0.2f + i / 5f) * Math.cos(0.4f + i / 2f));
        }
    }
}
