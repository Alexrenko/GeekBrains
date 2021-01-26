package FirstSemestr.Calculator.Listners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class ResultButtonListner implements ActionListener {
    JTextField display;

    public ResultButtonListner(JTextField display) {
        this.display = display;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        resultOnlyForPlusAndMinus();
    }

    private void resultOnlyForPlusAndMinus() {
        String inputText = display.getText();

        //если последним был введен оператор, то убираем его
        char lastSign = inputText.charAt(inputText.length() - 1);
        if (lastSign == '+' || lastSign == '-') {
            inputText = inputText.substring(0, inputText.length() - 1);
        }

        //получаем массив чисел с дисплея
        long[] numbers = getNumbers(inputText);

        //получаем массив операторов с дисплея
        ArrayList<Character> operators = getOperators(inputText);

        //считаем результат
        if (numbers.length > 1) {
            long result = numbers[0];
            for (int i = 0; i < operators.size(); i++) {
                if (operators.get(i) == '+') {
                    result += numbers[i + 1];
                } else if (operators.get(i) == '-') {
                    result -= numbers[i + 1];
                }
            }
            display.setText(String.valueOf(result));
        }
    }

    //Метод возвращает массив аргументов исходного математического выражения
    private long[] getNumbers(String inputText) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, inputText.split("[-+]"));

        if (list.get(0).equals(""))
            list.remove(0);                 //удаляем пустую строку из списка, если она есть
        long[] numbers = new long[list.size()];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Long.parseLong(list.get(i));
        }

        char firstSimbol = inputText.charAt(0);
        if (firstSimbol == '-') {               //если первый символ в текстовом поле - минус
            numbers[0] = numbers[0] * (-1);     //то первое число должно быть отрицательным
        }                                       //а сам минус будет исключен из списка операторов
        return numbers;
    }

    //Метод возвращает список операторов математического выражения
    private ArrayList<Character> getOperators(String inputText) {

        ArrayList<Character> operators = new ArrayList<>();
        char[] charsOfInputText = inputText.toCharArray();

        for (int i = 0; i < charsOfInputText.length; i++) {
            if (charsOfInputText[i] == 43 || charsOfInputText[i] == 45) {
                operators.add((char)charsOfInputText[i]);
            }
        }

        char firstSimbol = inputText.charAt(0);
        if(firstSimbol == '-' || firstSimbol == '+') {    //если первый символ - минус или плюс
            operators.remove(0);                    //то этот символ не включается в список операторов
        }

        return operators;
    }
}
