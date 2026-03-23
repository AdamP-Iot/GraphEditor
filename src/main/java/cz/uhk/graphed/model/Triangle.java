package cz.uhk.graphed.model;

import java.awt.*;

public class Triangle extends AbstractGraphicObject{
    protected int a;
    protected int cx,cy; //pomocne souřadnice
    //Vc = a * odmocnina ze 3 /2
    //y - Vc
    //x + a/2
    public Triangle(Point position, Color color, int a) {
        super(position, color);
        this.a = a;
        computeC();
    }

    private void computeC() {
        cx = position.x + (int)Math.round(a/2.0);
        cy = position.y - (int)Math.round(a * Math.sin(Math.PI / 3)); //nebo Math.toRadiands(60)
    }
    public Triangle() {}

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    @Override
    public void draw(Graphics g) {
        var g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON); //vyhlazení objektu
        g2.drawLine(position.x, position.y, position.x + a, position.y);
        g2.drawLine(position.x, position.y, cx, cy);
        g2.drawLine(cx, cy,position.x+a, position.y);
    }

    @Override
    public boolean contains(Point p) {
    //domácí ukol rozpohyhobat dx=dy/tg60stupnu
        //int dx = (int)Math.Round((p.y - position.y)*Math.tan(Math.PI / 6));
        return false;
    }
}
