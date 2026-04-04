package cz.uhk.graphed.gui;
import cz.uhk.graphed.model.*;
import cz.uhk.graphed.model.Rectangle;
import cz.uhk.graphed.model.Circle;
import cz.uhk.graphed.model.Square;
import cz.uhk.graphed.model.Triangle;
import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Canvas extends JPanel {
    private List<AbstractGraphicObject> graphicObjects = new ArrayList<>();
    private AbstractGraphicObject selectedObject;
    private String selectedTool = "";
    private Group group;
    private int dx;
    private int dy;
    public Canvas() {
        setPreferredSize(new Dimension(800, 600)); //Canvas size
       addMouseListener(new MouseAdapter() {
           @Override
           public void mousePressed(MouseEvent e) { //selectedObject = null; <- so that I can't drag spawned objects while spawning objects from toolBar
               if(selectedTool.equals("Square") ){
                   add(new Square(new Point (e.getX(),e.getY()),Color.RED,50));
                   selectedObject = null;
                   repaint();
               } else if(selectedTool.equals("Circle")){
                   add(new Circle(new Point (e.getX(),e.getY()),Color.RED,30));
                   selectedObject = null;
                   repaint();
               }else if(selectedTool.equals("Triangle")){
                   add(new Triangle(new Point (e.getX(),e.getY()),Color.RED,50));
                   selectedObject = null;
                   repaint();
               }else if(selectedTool.equals("Rectangle")){
                   add(new Rectangle(new Point (e.getX(),e.getY()),Color.RED,40,60));
                   selectedObject = null;
                   repaint();
               } else if(selectedTool.equals("Drag") && group!= null && group.contains(e.getPoint()) ) {
                   selectedObject = group;
                   dx = e.getX();
                   dy = e.getY();
                } else if(selectedTool.equals("Drag")){
                   selectedObject = findObjectContaining(e.getPoint());
                   if (selectedObject != null) {
                       dx = e.getX();
                       dy = e.getY();
                   }
               }
           }
       });
        addMouseMotionListener(new MouseAdapter() {

            //selectedObject.setPosition(e.getX()-dx,e.getY()-dy); -> replaced by dx,dy and move
           @Override
           public void mouseDragged(MouseEvent e) {
               if(selectedObject != null){
                   int moveX = e.getX()-dx;
                   int moveY = e.getY()-dy;
                   selectedObject.move(moveX,moveY);
                   dx = e.getX();
                   dy = e.getY();
                   repaint();
               }
           }
       });
    }

    private AbstractGraphicObject findObjectContaining(Point point) {
        AbstractGraphicObject result = null;
        for (var object : graphicObjects) {
            if (object.contains(point)) {
               result = object;
            }
        }
        return result;
    }

    public void add(AbstractGraphicObject object) {
        graphicObjects.add(object);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (var o: graphicObjects){ //Draw everything in list
            o.draw(g);
        }
    }
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     *  For actionPerformed function
     * @param selectedTool
     */
    public void setSelectedTool(String selectedTool) {
        this.selectedTool = selectedTool;
    }
}
