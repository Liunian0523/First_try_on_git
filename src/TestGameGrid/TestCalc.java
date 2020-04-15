package TestGameGrid;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestCalc {
    public static void main(String[] args) {
        new Calculator();//new Calculator().loadFrame()
    }
}

//calculator class
class Calculator extends Frame {
    public Calculator(){//public void loadFrame() 优化
        //3 textfield
        TextField num1 = new TextField(10);
        TextField num2 = new TextField(10);
        TextField num3 = new TextField(20);
        //1 label
        Label label = new Label("+");
        //1 button
        Button button = new Button("=");
        button.addActionListener(new MyCalculatorListener(num1,num2,num3));
        //button.addActionListener(new MyCalculatorListener(this)) 优化
        //set layout
        setLayout(new FlowLayout());

        add(num1);
        add(label);
        add(num2);
        add(button);
        add(num3);
        pack();
        setVisible(true);

    }
}

//Listener
class MyCalculatorListener implements ActionListener {
    //get input numbers
    private TextField num1,num2,num3;

    public MyCalculatorListener(TextField num1, TextField num2, TextField num3){
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //get num1 and num2
        int n1 = Integer.parseInt(num1.getText());
        int n2 = Integer.parseInt(num2.getText());
        //sum 1 & 2 --> num3
        num3.setText(""+(n1+n2));
        //clear 1 & 2 cells to empty
        num1.setText("");
        num2.setText("");
    }
}
