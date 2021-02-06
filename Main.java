package FirstSemestr.Java2.Lesson_2;

public class Main {
    public static void main(String[] args) {

        String[][] myArray = getStringMatrix(4, 3);
        //myArray[2][3] = "пять";
        printMatrix(myArray);

        try {
            int sum = getSumArray(myArray);
            System.out.println("Сумма всех ячеек в массиве: " + sum);
        } catch (MyArraySizeException sizeEx) {
            System.out.println("Ошибка! Размер массива должен быть 4 х 4.");
            sizeEx.printStackTrace();
        } catch (MyArrayDataException dataEx) {
            System.out.println("Ошибка! Неподходящие данные в массиве.");
            dataEx.printStackTrace();
        }
    }

    private static String[][] getStringMatrix(int m, int n) {
        String[][] array = new String[m][n];
        for (int i = 0; i < m; i++) {
           for (int j = 0; j < n; j++) {
               array[i][j] = j + (i * 4) + "";
           }
        }
        return array;
    }

    public static int getSumArray(String[][] inputArray) throws MyArrayDataException, MyArraySizeException {
        checkSize(inputArray);
        int sum = 0;
        for (int m = 0; m < inputArray.length; m++) {
            for (int n = 0; n < inputArray[0].length; n++) {
                try {
                    sum += Integer.parseInt(inputArray[m][n]);
                } catch (Exception ex) {
                    throw new MyArrayDataException(String.format(
                            "Ошибка преобразования в число. Значение в ячейке [%d][%d] - \"%s\"",
                            m, n, inputArray[m][n]), ex);
                }
            }
        }
        return sum;
    }

    private static void checkSize(String[][] inputArray) throws MyArraySizeException {
        int suitableSizeM = 4;
        int suitableSizeN = 4;

        if (inputArray.length == suitableSizeM) {
            for (String[] strings : inputArray) {
                if (strings.length != suitableSizeN) {
                    throw new MyArraySizeException(String.format(
                            "Неподходящий размер массива. Столбцов в массиве - %d", strings.length));
                }
            }
        } else {
            throw new MyArraySizeException(String.format(
                    "Неподходящий размер массива. В массиве %d строки.", inputArray.length));
        }
    }

    private static void printMatrix(String[][] inputArray) {
        for (int m = 0; m < inputArray.length; m++) {
            for (int n = 0; n < inputArray[0].length; n++) {
                System.out.print("\t" + inputArray[m][n]);
            }
            System.out.println();
        }
        System.out.println();
    }

}
