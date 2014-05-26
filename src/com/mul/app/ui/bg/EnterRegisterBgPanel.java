/*
 * EnterRegisterBgPanel.java
 *
 * Created on September 12, 2000, 11:02 AM
 */
package com.mul.app.ui.bg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author  Aity
 */
public class EnterRegisterBgPanel extends javax.swing.JPanel {

    final static int maxCharHeight = 15;
    final static int minFontSize = 6;
    final static Color bg = Color.white;
    final static Color fg = Color.black;
    final static Color red = Color.red;
    final static Color white = Color.white;
    final static BasicStroke stroke = new BasicStroke(2.0f);
    final static BasicStroke wideStroke = new BasicStroke(8.0f);
    final static float dash1[] = {10.0f};
    final static BasicStroke dashed = new BasicStroke(1.0f,
            BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER,
            10.0f, dash1, 0.0f);
    Dimension totalSize;
    FontMetrics fontMetrics;
    private Image image = null;

    /** Creates new form EnterRegisterBgPanel */
    public EnterRegisterBgPanel() {
        initComponents();
        try {
            //java.net.URL imgURL = getClass().getResource("icons/win_icon.gif");
            image = new ImageIcon(getClass().getResource("/com/mul/app/ui/bg/imgs/login-register1.png")).getImage();// ImageIO.read(new File("login-register1.png"));
            //image = ImageIO.read(new File(".\\src\\test\\jjjjj.GIF"))
            int d = 3;
        } catch (Exception e) {
            e.toString();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(this.image!=null)
        {

        g.draw3DRect(0, 0, this.getWidth(),this.getHeight(),true);
        g.drawImage(this.image,0, 0, this.getWidth(),this.getHeight(),this);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 795, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 562, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Testing");
                frame.setContentPane(new EnterRegisterBgPanel());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}