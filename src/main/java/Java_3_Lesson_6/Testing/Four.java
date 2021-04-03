package Java_3_Lesson_6.Testing;

public class Four {

    public static int[] getAllAfterFour(int[] inputArray) {
        boolean isFour = false;
        int numberLastFour = 0;

        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] == 4) {
                numberLastFour = i;
                isFour = true;
            }
        }
        if (isFour) {
            int amountAfterFour = inputArray.length - numberLastFour - 1;
            int[] newArray = new int[amountAfterFour];
            System.arraycopy(inputArray, numberLastFour + 1, newArray, 0, amountAfterFour);
            return newArray;
        } else {
            throw new RuntimeException("входной массив не содержит цифру 4");
        }
    }

    public static boolean checkOneAndFour(int[] inputArray) {
        for (int i = 0; i < inputArray.length; i++) {
            if ((inputArray[i] == 1) || inputArray[i] == 4) {
                return true;
            }
        }
        return false;
    }
}
