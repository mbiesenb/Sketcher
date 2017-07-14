/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sketcher;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Marvin
 */
public class Line implements Drawable {
    private SketchPanel sp;

    public void setSp(SketchPanel sp) {
        this.sp = sp;
    }
    private Point from = null;
    private Point to = null;
    private Color color = Color.black;
    private int thickness = 1;

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public Point getFrom() {
        return from;
    }

    public void setFrom(Point from) {
        this.from = from;
    }

    public Point getTo() {
        return to;
    }

    public void setTo(Point to) {
        this.to = to;
    }

    public Line(Point from, Point to) {
        this.from = from;
        this.to = to;
    }

    public Line(Point from, Point to, int thickness) {
        this.from = from;
        this.to = to;
        this.thickness = thickness;
    }
    public Line(Point from, Point to, Color color, int thickness) {
        this.from = from;
        this.to = to;
        this.thickness = thickness;
        this.color = color;
    }
    
    public Line() {
    }

    @Override
    public void drawme(Graphics2D g2d) {
        Color temp = g2d.getColor();
        g2d.setColor(color);
        //g2d.setStroke(new BasicStroke(thickness));
        g2d.setStroke(new BasicStroke(thickness,                     // Line width
                            BasicStroke.CAP_ROUND,    // End-cap style
                            BasicStroke.JOIN_ROUND));
        g2d.drawLine(from.x, from.y, to.x, to.y);
        g2d.setColor(temp);
    }

}
