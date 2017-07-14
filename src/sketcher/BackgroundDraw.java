/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sketcher;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marvin
 */
public class BackgroundDraw extends Thread {

    private SketchPanel sp;

    public BackgroundDraw(SketchPanel sp) {
        this.sp = sp;
    }

    public void drawme() {
        Graphics2D g2d = (Graphics2D) sp.getGraphics();
        ArrayList <Drawable> local = new ArrayList<>(sp.getDrawables());
        local.forEach((_item) -> {
            //System.out.println("sketcher.BackgroundDraw.drawme()");
            _item.drawme(g2d);
        });
        g2d.fillOval(0, 0, 100, 100);
        //System.out.println("sketcher.BackgroundDraw.drawme()");
    }

    @Override
    public void run() {
        while (true) {
//            try {
//                sleep(1000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(BackgroundDraw.class.getName()).log(Level.SEVERE, null, ex);
//            }
            drawme();
        }
    }
}
