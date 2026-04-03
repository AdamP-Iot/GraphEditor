package cz.uhk.graphed.model;

import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;

public class Group extends AbstractGraphicObject{
    private ArrayList<AbstractGraphicObject> objects;

    public Group (Point position, Color color){
        super(position,color);
        objects = new ArrayList<>();
    };
    public Group(){};

    public void addObject(AbstractGraphicObject object) {
        objects.add(object);
    }

    @Override
    public void draw(Graphics g) {
        for(AbstractGraphicObject object : objects){
            object.draw(g);
        }
    }

    @Override
    public void move(int dx, int dy) {
        for(AbstractGraphicObject object : objects){
            object.move(dx,dy);
        }
    }

    @Override
    public boolean contains(Point p) {
        for(AbstractGraphicObject object : objects) {
            if(object.contains(p)){
                return true;
            }
        }
        return false;
    }
}
