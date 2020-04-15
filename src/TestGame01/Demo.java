package TestGame01;
import javax.swing.*;
import java.awt.*;

public class Demo extends JFrame{
    private JButton[] jb = new JButton[9];
    private JPanel p1,p2;
    private JTextField[] grid = new JTextField[100];

    public Demo(){
        setTitle("Kakuro");
        //f.setSize(1000,800);
        //f.setLocation(200,100);
        setBounds(200,100,1000,700);
        Container c = getContentPane();
        c.setBackground(Color.GRAY);
        //c.validate();//验证组件
        c.setLayout(null);

        p1 = new JPanel();
        p2 = new JPanel();
        p1.setLayout(new GridLayout(3,3));
        p1.setBounds(800,500,150,150);
        p2.setLayout(new GridLayout(10,10));
        p2.setBounds(50,10,650,650);

        jb[0] = new JButton("1");
        jb[1] = new JButton("2");
        jb[2] = new JButton("3");
        jb[3] = new JButton("4");
        jb[4] = new JButton("5");
        jb[5] = new JButton("6");
        jb[6] = new JButton("7");
        jb[7] = new JButton("8");
        jb[8] = new JButton("9");

        p1.add(jb[0]);
        p1.add(jb[1]);
        p1.add(jb[2]);
        p1.add(jb[3]);
        p1.add(jb[4]);
        p1.add(jb[5]);
        p1.add(jb[6]);
        p1.add(jb[7]);
        p1.add(jb[8]);

        int i;
        for(i=0; i<100; i++){
            grid[i] = new JTextField(1);
            p2.add(grid[i]);
        }

        this.add(p1);
        this.add(p2);

        JButton start = new JButton("Start");
        JButton exit = new JButton("Exit");
        start.setBounds(900,50,80,30);
        exit.setBounds(900,90,80,30);
        c.add(start);
        c.add(exit);

        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//hide the window and terminate program

    }
    public static void main(String[] args) {
        new Demo();

    }
}

