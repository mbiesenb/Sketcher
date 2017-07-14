/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sketcher;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
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

    public SketchPanel() {
        setIgnoreRepaint(true);
        setBackground(Color.white);

        initListener();
        repaint();
        initBackgroundThread();

    }

    public ArrayList<Drawable> getDrawables() {
        return drawables;
    }

    @Override
    public void paint(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g;
//        int performaceCounter = 0;
//        Graphics2D g2d2 = (Graphics2D) g;
//        for (Drawable d : drawables) {
//            if (performaceCounter > 100) {
//                return;
//            }
//            d.drawme(g2d);
//            performaceCounter++;
//        }
        //super.paintComponent(g);
        //g.setColor(Color.black);
        //g.drawRect(0, 0, 100, 100);
//        BackgroundDraw d = new BackgroundDraw(this);
//        d.start();

    }
//    public void redraw(){
//        Graphics2D  g2d = (Graphics2D) getGraphics();
//        drawables.stream().map((d) -> {
//            d.drawme(g2d);
//            return d;
//        }).forEachOrdered((_item) -> {
//            ;
//        });
//    }
    public void selfPaint(){
        Graphics2D  g2d = (Graphics2D) getGraphics();
        int performaceCounter = 0;
        for (Drawable d : drawables) {
            if (performaceCounter > 2) {
                break;
            }
            d.drawme(g2d);
            performaceCounter++;
        }
        
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
    }

    private void appendLine() {
        Line addLine = new Line(currentLine.getFrom(), currentLine.getTo(), currentColor, getCurrentThickness());
        addLine.setSp(this);
        drawables.add(0, addLine);
        selfPaint();

    }

    public void clearAll() {
        drawables.clear();
        repaint();
    }

    private void initBackgroundThread() {
        BackgroundDraw backgroundDraw = new BackgroundDraw(this);
        backgroundDraw.start();
    }

}
