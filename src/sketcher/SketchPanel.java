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
    private ArrayList<Drawable> drawables = new ArrayList<>();

    public SketchPanel() {
        setBackground(Color.white);
        initListener();
        repaint();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for(Drawable d: drawables){
            d.drawme(g2d);
        }
    }

    private void initListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //System.out.println(".kommt an()");
                leftMouseClicked = true;
                currentLine.setFrom(e.getPoint());
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
        //System.out.println("get c thickness " + getCurrentThickness());
        Line addLine = new Line(currentLine.getFrom(), currentLine.getTo(), currentColor, getCurrentThickness());
        //addLine.setThickness(getCurrentThickness());
        drawables.add(addLine);
        repaint();
    }
    public void clearAll(){
        drawables.clear();
        repaint();
    }

}
