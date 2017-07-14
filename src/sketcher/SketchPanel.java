/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sketcher;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Marvin
 */
public class SketchPanel extends JPanel {

    private ArrayList<Drawable> drawables = new ArrayList<>();

    private boolean rightMouseClicked = false;
    private boolean leftMouseClicked = false;
    private Line currentLine = new Line();
    private int currentThickness = 5;
    private Color currentColor = Color.BLACK;
    private BackgroundDraw backgroundDraw = null;
    private Window window;
    private BufferedImage bimage;

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public int getCurrentThickness() {
        return currentThickness;
    }

    public void setCurrentThickness(int currentThickness) {
        this.currentThickness = currentThickness;
    }

    public SketchPanel(Window window) {
        this.window = window;
        initListener();
        initImage(2000,2000);
        repaint();

    }

    public void fillImage() {
        Graphics2D img2d = (Graphics2D) bimage.getGraphics();
        for (Drawable d : drawables) {
            d.drawme(img2d);
        }
    }

    public ArrayList<Drawable> getDrawables() {
        return drawables;
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        fillImage();
        g.drawImage(bimage, 0, 0, this);
    }

    private void initListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //System.out.println(".kommt an()");
                leftMouseClicked = true;
                currentLine.setFrom(e.getPoint());
                currentLine.setTo(e.getPoint());
                appendLine();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                leftMouseClicked = false;
                currentLine.setTo(e.getPoint());
                appendLine();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (leftMouseClicked) {
                    currentLine.setTo(e.getPoint());
                    appendLine();
                }
                currentLine.setFrom(e.getPoint());
            }
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                System.out.println(getWidth());
                System.out.println(getHeight());
                initImage(getWidth(), getHeight());
            }
        });
    }

    private void appendLine() {
        Line addLine = new Line(currentLine.getFrom(), currentLine.getTo(), currentColor, getCurrentThickness());
        addLine.setSp(this);
        drawables.add(addLine);
        //selfPaint();
        repaint();

    }

    public void clearAll() {
        drawables.clear();
        initImage(getWidth(), getHeight());
        repaint();

    }

    private void initImage(int width, int height) {
        bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D img2d = (Graphics2D) bimage.getGraphics();
        img2d.setColor(Color.white);
        img2d.fillRect(0, 0, bimage.getWidth(), bimage.getHeight());
        repaint();
    }
//    private void initBackgroundThread() {
//        if (backgroundDraw != null) {
//            backgroundDraw.stop();
//        }
//        backgroundDraw = new BackgroundDraw(this);
//        backgroundDraw.start();
//
//    }
}
