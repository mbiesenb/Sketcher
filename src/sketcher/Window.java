/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sketcher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.ColorChooserUI;

/**
 *
 * @author Marvin
 */
public class Window extends JFrame {

    private SketchPanel sp;

    public Window() {
        setVisible(true);
        setSize(600, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        sp = new SketchPanel(this);
        OptionPanel op = new OptionPanel();
        this.setLayout(new BorderLayout());
        this.add(op, BorderLayout.WEST);
        this.add(sp, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        repaint();
        validate();
        revalidate();

    }

    private class OptionPanel extends JPanel {

        private JButton btn_size_1 = new JButton("1px"); // 1 px
        private JButton btn_size_2 = new JButton("3px"); // 3 px 
        private JButton btn_size_3 = new JButton("6px"); // 6 px
        private JButton btn_size_4 = new JButton("12px"); // 6 px

        private JButton btn_fkt_1 = new JButton("Clear");
        private JButton btn_fkt_2 = new JButton("Rubber");
        private JButton btn_fkt_3 = new JButton("Pen");

        private JButton btn_file_load = new JButton("Laden");
        private JButton btn_file_show = new JButton("Show");
        private JButton btn_file_hide = new JButton("Hide");
        private String imagePath = this.getClass().getResource("").getPath() + "/../../../Bilder/";
        private JFileChooser filechooser = new JFileChooser(imagePath);

        public OptionPanel() {
            setBackground(Color.lightGray);
            setLayout(new GridLayout(20, 1));
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
            setVisible(true);
            add(new JLabel(""));
            add(new JLabel("Hintergrund"));
            add(btn_file_load);
            add(btn_file_show);
            add(btn_file_hide);
            repaint();
        }

        private void initListener() {
            btn_size_1.addMouseListener(setSize(1));
            btn_size_2.addMouseListener(setSize(3));
            btn_size_3.addMouseListener(setSize(6));
            btn_size_4.addMouseListener(setSize(12));
            btn_fkt_1.addMouseListener(doFkt(1)); //clear
            btn_fkt_2.addMouseListener(doFkt(2));
            btn_fkt_3.addMouseListener(doFkt(3));
            btn_file_load.addMouseListener(bgOption(1));
            btn_file_show.addMouseListener(bgOption(2));
            btn_file_hide.addMouseListener(bgOption(3));
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

        private MouseListener bgOption(int option) {
            return new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    switch (option) {
                        case 1:
                            int ret = filechooser.showOpenDialog(null);
                            if (ret == JFileChooser.APPROVE_OPTION) {

                                String path = filechooser.getSelectedFile().getAbsolutePath();
                                sp.setBackgroundImage(path);
                            }
                            break;
                        case 2:
                            sp.showImage();
                            break;
                        case 3:
                            sp.hideImage();
                            break;
                    }
                }

            };
        }

    }

}
