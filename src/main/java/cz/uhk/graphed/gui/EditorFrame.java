package cz.uhk.graphed.gui;
import cz.uhk.graphed.model.Circle;
import cz.uhk.graphed.model.Rectangle;
import cz.uhk.graphed.model.Square;
import cz.uhk.graphed.model.Triangle;

import javax.swing.*;
import java.awt.*;

public class EditorFrame extends JFrame {
    private Canvas canvas = new Canvas();

    public EditorFrame() throws HeadlessException {
        super("FIM Graphic Editor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);//So the application closes on exit
        add(canvas,BorderLayout.CENTER); //Where the panel will be placed (center..)
        initSampleData();
        add(toolBar(),BorderLayout.NORTH);
        pack(); //instead off setSize...
    }
    private JToolBar toolBar(){
        JToolBar tb = new JToolBar(JToolBar.NORTH);
        JButton b1 = new JButton("button1");
        //JPanel p = new JPanel();
        tb.add(b1);
        tb.setSize(500,500);
        tb.setVisible(true);
        return tb;
    }
    private void initSampleData() {
        canvas.add(new Square(new Point (100,100),Color.black,50));
        canvas.add(new Circle(new Point (100,100),Color.black,50));
        canvas.add(new Square(new Point (50,100),Color.black,50));
        canvas.add(new Circle(new Point (50,100),Color.black,25));
        canvas.add(new Square(new Point (100,150),Color.black,50));
        canvas.add(new Square(new Point (150,100),Color.black,50));
        canvas.add(new Square(new Point (100,50),Color.black,50));
        canvas.add(new Rectangle(new Point (300,100),Color.green,50,100));
        canvas.add(new Circle(new Point (500,100),Color.RED,50));
        canvas.add(new Triangle(new Point (600,110),Color.RED,100));
    }

}
