/*
 * NewJPanel.java
 *
 * Created on September 12, 2000, 11:02 AM
 */
package com.mul.app.ui;

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
 * @author  Administrator
 */
public class NewJPanel extends javax.swing.JPanel {

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

    /** Creates new form NewJPanel */
    public NewJPanel() {
        initComponents();
        try {
            image = new ImageIcon(getClass().getResource("/com/mul/app/ui/bg/imgs/mainbg.jpg")).getImage();
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
        g.drawImage(this.image, 0, 0, this.getWidth(),this.getHeight(),this);
        //g.setPaint(red);
        
        }
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Dimension d = getSize();
        int gridWidth = d.width / 6;

        fontMetrics = pickFont(g2, "Filled and Stroked GeneralPath",
                gridWidth);

        Color fg3D = Color.lightGray;

        g2.setPaint(fg3D);
        g2.draw3DRect(0, 0, d.width - 1, d.height - 1, true);
        g2.draw3DRect(3, 3, d.width - 7, d.height - 7, false);
        g2.setPaint(Color.black);

        int x = 5;
        int y = 7;
        int rectWidth = gridWidth - 2 * x;


        GradientPaint redtowhite = new GradientPaint(400, this.getWidth(), red, x + rectWidth, y, Color.BLUE);
        g2.setPaint(redtowhite);
        //g2.fill(new RoundRectangle2D.Double(x, y, this.getWidth(),
               // this.getHeight(), 100, 10));

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
    FontMetrics pickFont(Graphics2D g2,
            String longString,
            int xSpace) {
        boolean fontFits = false;
        Font font = g2.getFont();
        FontMetrics fontMetrics = g2.getFontMetrics();
        int size = font.getSize();
        String name = font.getName();
        int style = font.getStyle();

        while (!fontFits) {
            if ((fontMetrics.getHeight() <= maxCharHeight) && (fontMetrics.stringWidth(longString) <= xSpace)) {
                fontFits = true;
            } else {
                if (size <= minFontSize) {
                    fontFits = true;
                } else {
                    g2.setFont(font = new Font(name,
                            style,
                            --size));
                    fontMetrics = g2.getFontMetrics();
                }
            }
        }

        return fontMetrics;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("AHP - Patient Biodata Form");
                frame.setContentPane(new NewJPanel());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
