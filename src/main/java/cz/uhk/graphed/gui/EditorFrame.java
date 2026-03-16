package cz.uhk.graphed.gui;
import cz.uhk.graphed.model.Circle;
import cz.uhk.graphed.model.Rectangle;
import cz.uhk.graphed.model.Square;

import javax.swing.*;
import java.awt.*;

public class EditorFrame extends JFrame {
    private Canvas canvas = new Canvas();

    public EditorFrame() throws HeadlessException {
        super("FIM Graphic Editor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);//So the application closes on exit
        add(canvas,BorderLayout.CENTER); //Where the panel will be placed (center..)
        initSampleData();

        pack(); //instead off setSize...
    }

    private void initSampleData() {
        canvas.add(new Square(new Point (100,100),Color.black,50));
        canvas.add(new Square(new Point (100,200),Color.blue,50));
        canvas.add(new Square(new Point (200,100),Color.green,50));
        canvas.add(new Rectangle(new Point (300,100),Color.green,50,100));
        canvas.add(new Circle(new Point (500,100),Color.black,50));
    }

}
