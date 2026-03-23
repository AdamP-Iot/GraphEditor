package cz.uhk.graphed.model;

import java.awt.*;

public class Circle extends AbstractGraphicObject {
    protected int a;


    public Circle(Point position, Color color, int a) {
        super(position, color);
        this.a = a;
    }
    public Circle(){}

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g; //control over geometry
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.drawOval(position.x,position.y,2*a,2*a);
    }
    //(xp-x-r)na2 + (yr-y-r)na2 <=r na2
    @Override
    public boolean contains(Point p) {
        return Math.pow(p.x - position.x-a,2)+ Math.pow(p.y - position.y-a,2)<= Math.pow(a,2);

    }
}
