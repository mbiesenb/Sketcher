/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sketcher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.ColorChooserUI;

/**
 *
 * @author Marvin
 */
public class Window_alt extends JFrame {

    private SketchPanel sp;

    public Window_alt() {
        setVisible(true);
        setSize(600, 600);
        sp = new SketchPanel();
        this.setLayout(new BorderLayout());
        this.add(sp, BorderLayout.CENTER);
        this.add(new OptionPanel(), BorderLayout.WEST);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                // do stuff 
                //sp.redraw();
                //System.out.println(".componentResized()");
            }

            
        });

    }

    private class OptionPanel extends JPanel {

        private JButton btn_size_1 = new JButton("1px"); // 1 px
        private JButton btn_size_2 = new JButton("3px"); // 3 px 
        private JButton btn_size_3 = new JButton("6px"); // 6 px
        private JButton btn_size_4 = new JButton("12px"); // 6 px

        private JButton btn_fkt_1 = new JButton("Clear");
        private JButton btn_fkt_2 = new JButton("Rubber");
        private JButton btn_fkt_3 = new JButton("Pen");

        public OptionPanel() {
            setBackground(Color.lightGray);
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            initListener();
            add(new JLabel("Thickness"));
            add(btn_size_1);
            add(btn_size_2);
            add(btn_size_3);
            add(btn_size_4);
            add(new JLabel(""));
            add(new JLabel("Funktionen"));
            add(btn_fkt_1);
            add(btn_fkt_2);
            add(btn_fkt_3);
        }

        private void initListener() {
            btn_size_1.addMouseListener(setSize(1));
            btn_size_2.addMouseListener(setSize(3));
            btn_size_3.addMouseListener(setSize(6));
            btn_size_4.addMouseListener(setSize(12));
            btn_fkt_1.addMouseListener(doFkt(1)); //clear
            btn_fkt_2.addMouseListener(doFkt(2));
            btn_fkt_3.addMouseListener(doFkt(3));
        }

        private MouseListener setSize(int thickness) {
            return new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    System.out.println("Thickness" + thickness);
                    sp.setCurrentThickness(thickness);
                }
            };
        }

        private MouseListener doFkt(int fkt) {
            return new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    switch (fkt) {
                        case 1:
                            sp.clearAll();
                            break;
                        case 2:
                            sp.setCurrentColor(Color.white);
                            break;
                        case 3:
                            sp.setCurrentColor(Color.black);
                            break;
                    }
                }
            };
        }

    }

}
