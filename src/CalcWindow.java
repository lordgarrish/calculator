import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public final class CalcWindow {
    JFrame mainFrame;
    JPanel buttonPanel;
    JLabel numDisplay;
    JButton[] buttons;
    Font buttonsFont;
    CalcLogic cl;
    boolean newClick;


    private CalcWindow() {
        mainFrame = new JFrame("Calculator");
        buttonPanel = new JPanel();
        numDisplay = new JLabel(" ");
        numDisplay.setOpaque(true);
        numDisplay.setHorizontalAlignment(JLabel.RIGHT);
        numDisplay.setFont(new Font("Arial", Font.BOLD, 60));
        buttonPanel.setLayout(new GridLayout(4, 4));
        buttons = new JButton[16];
        buttonsFont = new Font("Arial", Font.PLAIN, 40);
        cl = new CalcLogic();

        for (int i = 0; i < 16; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(buttonsFont);
            buttons[i].addActionListener(new ButtonListener());
            buttonPanel.add(buttons[i]);
        }

        buttons[0].setText("7");
        buttons[1].setText("8");
        buttons[2].setText("9");
        buttons[3].setText("÷");
        buttons[4].setText("4");
        buttons[5].setText("5");
        buttons[6].setText("6");
        buttons[7].setText("×");
        buttons[8].setText("1");
        buttons[9].setText("2");
        buttons[10].setText("3");
        buttons[11].setText("-");
        buttons[12].setText("0");
        buttons[13].setText("C");
        buttons[14].setText("=");
        buttons[15].setText("+");

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Menu");
        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(actionEvent -> JOptionPane.showMessageDialog(mainFrame,
                "Calculator by lord_garrish, (c) 2021"));
        fileMenu.add(aboutMenuItem);
        menuBar.add(fileMenu);

        mainFrame.setJMenuBar(menuBar);
        mainFrame.add(numDisplay, BorderLayout.NORTH);
        mainFrame.add(buttonPanel, BorderLayout.CENTER);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(400,400);
        mainFrame.setVisible(true);
    }

    private double getNumber() {
        String convert = numDisplay.getText().trim();
        return Double.parseDouble(convert);
    }

    private void performOperation(double d) {
        double res = cl.calculate(d);
        displayResult(res);
    }

    private void displayResult(double d) {
        String s = String.valueOf(d);
        if(s.endsWith("0")) {
            numDisplay.setText(s.substring(0, s.length() - 2));
        } else {
            numDisplay.setText(s);
        }
    }

    private void setNumber(String s) {
        if(!newClick) {
            numDisplay.setText(numDisplay.getText() + s);
        } else {
            numDisplay.setText(s);
            newClick = false;
        }
    }

    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource() == buttons[0]) {
                setNumber("7");
            } else if (actionEvent.getSource() == buttons[1]) {
                setNumber("8");
            } else if (actionEvent.getSource() == buttons[2]) {
                setNumber("9");
            } else if (actionEvent.getSource() == buttons[3]) { //division
                performOperation(getNumber());
                cl.setOperation(CalcLogic.Operation.DIVISION);
                newClick = true;
            } else if (actionEvent.getSource() == buttons[4]) {
                setNumber("4");
            } else if (actionEvent.getSource() == buttons[5]) {
                setNumber("5");
            } else if (actionEvent.getSource() == buttons[6]) {
                setNumber("6");
            } else if (actionEvent.getSource() == buttons[7]) { //multiplying
                performOperation(getNumber());
                cl.setOperation(CalcLogic.Operation.MULTIPLYING);
                newClick = true;
            } else if (actionEvent.getSource() == buttons[8]) {
                setNumber("1");
            } else if (actionEvent.getSource() == buttons[9]) {
                setNumber("2");
            } else if (actionEvent.getSource() == buttons[10]) {
                setNumber("3");
            } else if (actionEvent.getSource() == buttons[11]) { //subtraction
                performOperation(getNumber());
                cl.setOperation(CalcLogic.Operation.SUBTRACTION);
                newClick = true;
            } else if (actionEvent.getSource() == buttons[12]) {
                setNumber("0");
            } else if (actionEvent.getSource() == buttons[13]) {
                cl.cancel();
                numDisplay.setText(" ");
            } else if (actionEvent.getSource() == buttons[14]) {
                performOperation(getNumber());
                cl.cancel();
            } else if (actionEvent.getSource() == buttons[15]) { //addition
                performOperation(getNumber());
                cl.setOperation(CalcLogic.Operation.ADDITION);
                newClick = true;
            }
        }
    }

    public static void main(String[] args) {
        new CalcWindow();
    }
}
