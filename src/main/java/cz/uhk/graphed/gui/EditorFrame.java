package cz.uhk.graphed.gui;
import cz.uhk.graphed.model.Circle;
import cz.uhk.graphed.model.Rectangle;
import cz.uhk.graphed.model.Square;
import cz.uhk.graphed.model.Triangle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EditorFrame extends JFrame implements ActionListener {
    private Canvas canvas = new Canvas();

    JToggleButton squareTb = new JToggleButton("Square");
    JToggleButton circleTb = new JToggleButton("Circle");
    JToggleButton triangleTb = new JToggleButton("Triangle");
    JToggleButton rectangleTb = new JToggleButton("Rectangle");
    JToggleButton dragB = new JToggleButton("");

    public EditorFrame() throws HeadlessException {
        super("FIM Graphic Editor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);//So the application closes on exit
        add(canvas,BorderLayout.CENTER); //Where the panel will be placed (center...)
        initSampleData();
        add(toolBar(),BorderLayout.NORTH);
        pack(); //instead off setSize...
    }
    private JToolBar toolBar(){
        JToolBar tb = new JToolBar();

        ButtonGroup bGroup = new ButtonGroup(); //better to have buttons in a group
        bGroup.add(squareTb);
        bGroup.add(circleTb);
        bGroup.add(triangleTb);
        bGroup.add(rectangleTb);
        bGroup.add(dragB);

        //add listenerToObjects
        squareTb.addActionListener(this);
        circleTb.addActionListener(this);
        triangleTb.addActionListener(this);
        rectangleTb.addActionListener(this);
        dragB.addActionListener(this);

        //all this code for an icon...
        try {
            dragB.setIcon(new ImageIcon(ImageIO.read(new java.net.URL("https://www.rw-designer.com/icon-image/7483-256x256x32.png")).getScaledInstance(16, 16, Image.SCALE_SMOOTH)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        tb.add(squareTb);
        tb.add(circleTb);
        tb.add(triangleTb);
        tb.add(rectangleTb);
        tb.add(dragB);

        tb.setSize(500,500);
        tb.setVisible(true);
        return tb;
    }
    @Override
    public void actionPerformed(ActionEvent e){ //setNameOfObject
        Object source = e.getSource();

        if (source == squareTb) {
            canvas.setSelectedTool("Square");
        } else if (source == circleTb) {
            canvas.setSelectedTool("Circle");
        } else if (source == triangleTb) {
            canvas.setSelectedTool("Triangle");
        } else if (source == rectangleTb) {
            canvas.setSelectedTool("Rectangle");
        } else if (source == dragB){
            canvas.setSelectedTool("Drag");
        }
    }
    private void initSampleData() {
        /**
          canvas.add(new Square(new Point (100,100),Color.black,50));
        canvas.add(new Circle(new Point (100,100),Color.black,50));
        canvas.add(new Rectangle(new Point (100,100),Color.black,50,80));
        canvas.add(new Triangle(new Point (100,100),Color.black,100));
         **/
    }

}
