package FirstSemestr.Calculator;

import FirstSemestr.Calculator.Listners.CButtonListner;
import FirstSemestr.Calculator.Listners.DigitButtonsListener;
import FirstSemestr.Calculator.Listners.OperationButtonsListner;
import FirstSemestr.Calculator.Listners.ResultButtonListner;

import javax.swing.*;
import java.awt.*;

public class CalculatorFrame extends JFrame {
    public CalculatorFrame() {
        setTitle("Calculator");
        setBounds(500, 100, 300, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Создаем требуемые элементы
        JTextField display = createAndConfigureDisplay();   //дисплей
        JPanel[] panels = getPanels();                      //панели
        JButton[] buttons = createAndConfigureButtons();    //кнопки

        //Привязываем события к кнопкам
        createEventsOfDigitButtons(display, buttons);
        createEventOfCButton(display, buttons);
        createEventsOfOperationButtons(display, buttons);
        createEventOfResultButton(display, buttons);

        //Расставляем элементы
        buttons = orderButtons(buttons);
        placePanels(panels);
        panels[0].add(display, BorderLayout.CENTER);
        placeButtonsOnPanels(buttons, panels);

        setVisible(true);
    }

    private JTextField createAndConfigureDisplay() {
        JTextField display = new JTextField();
        display.setBackground(new Color(230, 230, 230));
        return display;
    }

    private JPanel[] getPanels() {
        JPanel[] panels = new JPanel[5];
        for (int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel();
        }
        return panels;
    }

    private JButton[] createAndConfigureButtons() {
        JButton[] buttons = new JButton[16];
        //создаем и настраиваем все 16 кнопок
        for (int i = 0; i < 16; i++) {
            buttons[i] = new JButton(String.valueOf(i));
            buttons[i].setBackground(new Color(204, 204, 204));
            Font font = new Font("TimesRoman", Font.BOLD, 20);
            buttons[i].setFont(font);
        }
        //меняем цвет кнопок с математическими операциями
        for (int i = 10; i < 15; i++) {
            buttons[i].setBackground(new Color(185, 185, 185));
        }
        //меняем цвет кнопки "="
        buttons[15].setBackground(new Color(115, 155, 165));
        //присваиваем текст кнопкам с операциями
        buttons[10].setText(String.valueOf("+"));
        buttons[11].setText(String.valueOf("-"));
        buttons[12].setText(String.valueOf("*"));
        buttons[13].setText(String.valueOf("/"));
        buttons[14].setText(String.valueOf("C"));
        buttons[15].setText(String.valueOf("="));
        //возвращаем массив готорых кнопок
        return buttons;
    }

    private void createEventsOfDigitButtons(JTextField display, JButton[] buttons) {
        DigitButtonsListener listener = new DigitButtonsListener(display);
        for (int i = 0; i < 10; i++) {
            buttons[i].addActionListener(listener);
        }
    }

    private void createEventOfCButton(JTextField display, JButton[] buttons) {
        CButtonListner listner = new CButtonListner(display);
        buttons[14].addActionListener(listner);
    }

    private void createEventsOfOperationButtons(JTextField display, JButton[] buttons) {
        OperationButtonsListner listner = new OperationButtonsListner(display);
        for (int i = 10; i < 12; i++) {
            buttons[i].addActionListener(listner);
        }
        buttons[14].addActionListener(listner);
    }

    private void createEventOfResultButton(JTextField display, JButton[] buttons) {
        ResultButtonListner listner = new ResultButtonListner(display);
        buttons[15].addActionListener(listner);
    }

    private JButton[] orderButtons(JButton[] buttons) {
        //меняем порядок кнопок в массиве для удобства расстановки на панелях
        JButton[] orderedButtons
                = new JButton[] {   buttons[1],  buttons[2],  buttons[3],  buttons[10],
                                    buttons[4],  buttons[5],  buttons[6],  buttons[11],
                                    buttons[7],  buttons[8],  buttons[9],  buttons[12],
                                    buttons[14], buttons[0],  buttons[15], buttons[13]};
        return orderedButtons;
    }

    private void placePanels(JPanel[] panels) {
        setLayout(new GridLayout(5,1));
        for (int i = 0; i < panels.length; i++) {
            add(panels[i]);
        }
        panels[0].setLayout(new BorderLayout());    //верхняя панель для дисплея
        for (int i = 1; i < panels.length; i++) {   //остальные четыре панели для кнопок
            add(panels[i]);
            panels[i].setLayout(new GridLayout(1,4));
        }
    }

    private void placeButtonsOnPanels(JButton[] buttons, JPanel[] panels) {
        for (int i = 1; i < panels.length; i++) {
            for (int j = 0; j < 4; j++) {
                panels[i].add(buttons[j + (4 * (i - 1))]);
            }
        }
    }

}
