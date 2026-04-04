package cz.uhk.graphed.gui;

import cz.uhk.graphed.model.*;
import cz.uhk.graphed.model.Rectangle;
import cz.uhk.graphed.model.Circle;
import cz.uhk.graphed.model.Square;
import cz.uhk.graphed.model.Triangle;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Canvas extends JPanel {
    private List<AbstractGraphicObject> graphicObjects = new ArrayList<>();
    private AbstractGraphicObject selectedObject;
    private String selectedTool = "";
    private Group group;

    //secret
    private boolean gifBg = false;
    Image bgImage;
    Clip clip;
    boolean clipPlayed;

    private int dx;
    private int dy;

    public Canvas() {
        setPreferredSize(new Dimension(800, 600)); //Canvas size
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) { //selectedObject = null; <- so that I can't drag spawned objects while spawning objects from toolBar
                if (selectedTool.equals("Square")) {
                    add(new Square(new Point(e.getX() - 20, e.getY() - 20), Color.RED, 50));
                    selectedObject = null;
                    repaint();
                } else if (selectedTool.equals("Circle")) {
                    add(new Circle(new Point(e.getX() - 20, e.getY() - 20), Color.RED, 30));
                    selectedObject = null;
                    repaint();
                } else if (selectedTool.equals("Triangle")) {
                    add(new Triangle(new Point(e.getX() - 20, e.getY() + 20), Color.RED, 50));
                    selectedObject = null;
                    repaint();
                } else if (selectedTool.equals("Rectangle")) {
                    add(new Rectangle(new Point(e.getX() - 20, e.getY() - 20), Color.RED, 40, 60));
                    selectedObject = null;
                    repaint();
                } else if (selectedTool.equals("Drag") && group != null && group.contains(e.getPoint())) {
                    selectedObject = group;
                    dx = e.getX();
                    dy = e.getY();
                } else if (selectedTool.equals("Drag")) {
                    selectedObject = findObjectContaining(e.getPoint());
                    if (selectedObject != null) {
                        dx = e.getX();
                        dy = e.getY();
                    }
                }
            }
        });

        //image for bg from URL
        try {
            bgImage = new ImageIcon(new java.net.URL("https://media1.tenor.com/m/1E6HMze0mQEAAAAd/dancing-cockroach-rainbow.gif")).getImage();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //for pressed "Q" key
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("Q"), "Q");
        getActionMap().put("Q", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gifBg = true;
                repaint();

                try {
                    AudioInputStream audio = AudioSystem.getAudioInputStream(new File("song.wav"));
                    if (clip != null && clip.isRunning()) {
                        return;
                    }
                    clip = AudioSystem.getClip();
                    clip.open(audio);
                    clip.start();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            }

        });
        addMouseMotionListener(new MouseAdapter() {
            //selectedObject.setPosition(e.getX()-dx,e.getY()-dy); -> replaced by dx,dy and move
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedObject != null) {
                    int moveX = e.getX() - dx;
                    int moveY = e.getY() - dy;
                    selectedObject.move(moveX, moveY);
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

        if (gifBg) {
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        }
        for (var o : graphicObjects) { //Draw everything in list
            o.draw(g);
        }


    }

    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * For actionPerformed function
     *
     * @param selectedTool
     */
    public void setSelectedTool(String selectedTool) {
        this.selectedTool = selectedTool;
    }
}
