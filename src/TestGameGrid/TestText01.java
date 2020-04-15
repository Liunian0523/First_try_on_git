package TestGameGrid;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestText01 {
    public static void main(String[] args) {
        new MyFrame();
    }
}

class MyFrame extends Frame{
    public MyFrame(){
        TextField textField = new TextField();
        add(textField);

        //listen the content which will be fill in this textfield
        MyActionListener2 myActionListener2 = new MyActionListener2();
        textField.addActionListener(myActionListener2);

        setVisible(true);
        pack();
    }
}

class MyActionListener2 implements ActionListener{
    public void actionPerformed(ActionEvent e){
        TextField field = (TextField) e.getSource();//return a content
        System.out.println(field.getText());//get input text
        field.setText("");//after pressing enter, textfield will be set empty
    }
}
//生成文本框，显示输入