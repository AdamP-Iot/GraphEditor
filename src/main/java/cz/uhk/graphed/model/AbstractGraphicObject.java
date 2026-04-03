package cz.uhk.graphed.model;
import java.awt.*;

public abstract class AbstractGraphicObject {
    protected Point position;
    protected Color color;

    AbstractGraphicObject(Point position, Color color) {
        this.position = position;
        this.color = color;
    }

    AbstractGraphicObject() {}

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setPosition(int x, int y) {
        setPosition(new Point(x, y));
    }

    public abstract void draw(Graphics g);

    public abstract boolean contains(Point p);

    public void move (int dx, int dy) { position.translate(dx,dy); }

    public boolean contains(int x, int y) {
        return contains(new Point(x, y));
    }
}