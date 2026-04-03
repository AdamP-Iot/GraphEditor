package cz.uhk.graphed.gui;
import cz.uhk.graphed.model.Circle;
import cz.uhk.graphed.model.Rectangle;
import cz.uhk.graphed.model.Square;
import cz.uhk.graphed.model.Triangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditorFrame extends JFrame implements ActionListener {
    private Canvas canvas = new Canvas();

    JToggleButton squareTb = new JToggleButton("Square");
    JToggleButton circleTb = new JToggleButton("Circle");
    JToggleButton triangleTb = new JToggleButton("Triangle");
    JToggleButton rectangleTb = new JToggleButton("Rectangle");

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

        squareTb.addActionListener(this);
        circleTb.addActionListener(this);
        triangleTb.addActionListener(this);
        rectangleTb.addActionListener(this);

        tb.add(squareTb);
        tb.add(circleTb);
        tb.add(triangleTb);
        tb.add(rectangleTb);

        tb.setSize(500,500);
        tb.setVisible(true);
        return tb;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();

        if (source == squareTb) {
            canvas.setSelectedTool("Square");
        } else if (source == circleTb) {
            canvas.setSelectedTool("Circle");
        } else if (source == triangleTb) {
            canvas.setSelectedTool("Triangle");
        } else if (source == rectangleTb) {
            canvas.setSelectedTool("Rectangle");
        }
    }
    private void initSampleData() {
        canvas.add(new Square(new Point (100,100),Color.black,50));
        canvas.add(new Circle(new Point (100,100),Color.black,50));
        canvas.add(new Rectangle(new Point (100,100),Color.black,50,80));
        canvas.add(new Triangle(new Point (100,100),Color.black,100));
    }

}
