package cz.uhk.graphed.model;

import java.awt.*;

public class Square extends AbstractGraphicObject {
    protected int a;
    public Square(Point position, Color color, int a) {
        super(position, color);
        this.a = a;
    }

    public Square() {}

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g; //control over geometry
        g2.setColor(color);
        g2.drawRect(position.x,position.y,a,a); // square drawn
    }

    @Override
    public boolean contains(Point p) {
        return false;
    }
}
