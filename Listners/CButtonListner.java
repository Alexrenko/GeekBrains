package FirstSemestr.Calculator.Listners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CButtonListner implements ActionListener {
    JTextField display;

    public CButtonListner(JTextField display) {
        this.display = display;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        display.setText("");
    }
}
