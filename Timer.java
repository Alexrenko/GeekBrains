package FirstSemestr.Java2.Lesson_5;

public class Timer {

    public static long getTimefirstCalcMethod(int size) {
        float[] array = new float[size];
        fillArray(array);
        long a = System.currentTimeMillis();
        calculateArray(array);
        return System.currentTimeMillis() - a;
    }

    public static long getTimesSecondCalcMethod(int size) throws InterruptedException {
        int h = size/2;
        float[] array = new float[size];
        float[] arrayOne = new float[h];
        float[] arrayTwo = new float[h];
        fillArray(array);

        long a = System.currentTimeMillis();
        System.arraycopy(array, 0, arrayOne, 0, h);
        System.arraycopy(array, h, arrayTwo, 0, h);
        Thread T1 = new Thread(new FormulaCalculator(arrayOne));
        Thread T2 = new Thread(new FormulaCalculator(arrayTwo));
        T1.start();
        T2.start();
        T1.join();
        T2.join();
        System.arraycopy(arrayOne, 0, array, 0, h);
        System.arraycopy(arrayTwo, 0, array, h, h);
        long b = System.currentTimeMillis();

        return b - a;
    }

    private static void fillArray(float[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = 1;
        }
    }

    private static void calculateArray(float[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (float)(array[i] * Math.sin(0.2f + i / 5f)
                    * Math.cos(0.2f + i / 5f) * Math.cos(0.4f + i / 2f));
        }
    }
}
