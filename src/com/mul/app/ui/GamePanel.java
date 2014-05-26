/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GamePanel.java
 *
 * Created on May 5, 2011, 11:48:43 PM
 */
package com.mul.app.ui;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Vector;
import javax.swing.JFrame;

/**
 *
 * @author Aity
 */
public class GamePanel extends javax.swing.JPanel implements Runnable {

    String imgs[];
    private static TexturePaint texture;

    /** Creates new form GamePanel */
    public GamePanel(String imgss[], int numToShow) {
        initComponents();
        setBackground(Color.black);
        this.imgs = imgss;
        // initializes to display 2 strings, 3 images and 8 shapes
        setStrings(2);
        setImages(numToShow);

    }

    static {
        int w = 10;
        int h = 10;
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D gi = bi.createGraphics();
        Color oc = Color.blue;
        Color ic = Color.green;
        gi.setPaint(new GradientPaint(0, 0, oc, w * .35f, h * .35f, ic));
        gi.fillRect(0, 0, w / 2, h / 2);
        gi.setPaint(new GradientPaint(w, 0, oc, w * .65f, h * .35f, ic));
        gi.fillRect(w / 2, 0, w / 2, h / 2);
        gi.setPaint(new GradientPaint(0, h, oc, w * .35f, h * .65f, ic));
        gi.fillRect(0, h / 2, w / 2, h / 2);
        gi.setPaint(new GradientPaint(w, h, oc, w * .65f, h * .65f, ic));
        gi.fillRect(w / 2, h / 2, w / 2, h / 2);
        texture = new TexturePaint(bi, new Rectangle(0, 0, w, h));
    }
    private static BasicStroke bs = new BasicStroke(6);
    // an array of Font objects
    private static Font fonts[] = {
        new Font("Times New Roman", Font.PLAIN, 64),
        new Font("serif", Font.BOLD + Font.ITALIC, 24),
        new Font("Courier", Font.BOLD, 36),
        new Font("Arial", Font.BOLD + Font.ITALIC, 48),
        new Font("Helvetica", Font.PLAIN, 52)};
    // an array of String objects
    private static String strings[] = {
        "Alpha", "Composite", "Src", "SrcOver",
        "SrcIn", "SrcOut", "Clear", "DstOver", "DstIn"};
    // an array of images
    ////////private static String imgs[] = {
    ///////"A.jpg", "B.jpg", "C.jpg"};
    // an array of colors
    private static Paint paints[] = {
        Color.red, Color.blue, Color.green, Color.magenta,
        Color.orange, Color.pink, Color.cyan, texture,
        Color.yellow, Color.lightGray, Color.white};
    private Vector vector = new Vector(13);
    public int numShapes, numStrings, numImages;
    private Thread thread;
    private BufferedImage bimg;

    public Image getImage(String name) {
        URL url = getClass().getResource("/com/mul/app/ui/resource/" + name );
        Image img = getToolkit().getImage(url);
        try {
            MediaTracker tracker = new MediaTracker(this);
            tracker.addImage(img, 0);
            tracker.waitForID(0);
        } catch (Exception e) {
        }
        return img;
    }

    public void setImages(int num) {

        if (num < numImages) {
            Vector v = new Vector(vector.size());
            for (int i = 0; i < vector.size(); i++) {
                if (((ObjectData) vector.get(i)).object instanceof Image) {
                    v.addElement(vector.get(i));
                }
            }
            vector.removeAll(v);
            v.setSize(num);
            vector.addAll(v);
        } else {
            Dimension d = getSize();
            for (int i = numImages; i < num; i++) {
                Object obj = getImage(imgs[i % imgs.length]);
                if (imgs[i % imgs.length].equals("jumptojavastrip.gif")) {
                    int iw = ((Image) obj).getWidth(null);
                    int ih = ((Image) obj).getHeight(null);
                    BufferedImage bimg = new BufferedImage(iw, ih, BufferedImage.TYPE_INT_RGB);
                    bimg.createGraphics().drawImage((Image) obj, 0, 0, null);
                    obj = bimg;
                }
                ObjectData od = new ObjectData(obj, Color.black);
                od.reset(d.width, d.height);
                vector.addElement(od);
            }
        }
        numImages = num;
    }

    // adds Strings to a Vector
    public void setStrings(int num) {

        if (num < numStrings) {
            Vector v = new Vector(vector.size());
            for (int i = 0; i < vector.size(); i++) {
                if (((ObjectData) vector.get(i)).object instanceof TextData) {
                    v.addElement(vector.get(i));
                }
            }
            vector.removeAll(v);
            v.setSize(num);
            vector.addAll(v);
        } else {
            Dimension d = getSize();
            for (int i = numStrings; i < num; i++) {
                int j = i % fonts.length;
                int k = i % strings.length;
                Object obj = new TextData(strings[k], fonts[j], this);
                ObjectData od = new ObjectData(obj, paints[i % paints.length]);
                od.reset(d.width, d.height);
                vector.addElement(od);
            }
        }
        numStrings = num;
    }

    // adds shapes to a Vector
    // calls ObjectData.reset for each item in vector
    public void reset(int w, int h) {
        for (int i = 0; i < vector.size(); i++) {
            ((ObjectData) vector.get(i)).reset(w, h);
        }
    }

    // calls ObjectData.step for each item in vector
    public void step(int w, int h) {
        for (int i = 0; i < vector.size(); i++) {
            ((ObjectData) vector.get(i)).step(w, h);
        }
    }

    public void drawDemo(int w, int h, Graphics2D g2) {

        for (int i = 0; i < vector.size(); i++) {
            ObjectData od = (ObjectData) vector.get(i);
            AlphaComposite ac = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, od.alpha);
            g2.setComposite(ac);
            g2.setPaint(od.paint);
            g2.translate(od.x, od.y);

            if (od.object instanceof Image) {
                g2.drawImage((Image) od.object, 0, 0, this);
            } else if (od.object instanceof TextData) {
                g2.setFont(((TextData) od.object).font);
                g2.drawString(((TextData) od.object).string, 0, 0);
            } else if (od.object instanceof QuadCurve2D
                    || od.object instanceof CubicCurve2D) {
                g2.setStroke(bs);
                g2.draw((Shape) od.object);
            } else if (od.object instanceof Shape) {
                g2.fill((Shape) od.object);
            }
            g2.translate(-od.x, -od.y);
        }
    }

    public Graphics2D createGraphics2D(int w, int h) {
        Graphics2D g2 = null;
        if (bimg == null || bimg.getWidth() != w || bimg.getHeight() != h) {
            bimg = (BufferedImage) createImage(w, h);
            reset(w, h);
        }
        g2 = bimg.createGraphics();
        g2.setBackground(getBackground());
        g2.clearRect(0, 0, w, h);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        return g2;
    }

    public void paint(Graphics g) {
        Dimension d = getSize();
        step(d.width, d.height);
        Graphics2D g2 = createGraphics2D(d.width, d.height);
        drawDemo(d.width, d.height, g2);
        g2.dispose();
        g.drawImage(bimg, 0, 0, this);
    }

    public void start() {
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

    public synchronized void stop() {
        thread = null;
    }

    public void run() {
        Thread me = Thread.currentThread();
        while (thread == me) {
            repaint();
            try {
                thread.sleep(10);
            } catch (InterruptedException e) {
                break;
            }
        }
        thread = null;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(471, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(479, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

// adds images to a Vector
    /**
     * The TextData class creates an Object from the specified
     * String and Font and creates a FontMetrics object to store
     * width and height information.
     */
    static class TextData extends Object {

        public String string;
        public Font font;
        public int width, height;

        public TextData(String str, Font font, Component cmp) {
            string = str;
            this.font = font;
            FontMetrics fm = cmp.getFontMetrics(font);
            width = fm.stringWidth(str);
            height = fm.getHeight();
        }
    }

    /**
     * The ObjectData class increments and decrements the alpha
     * value used for the fading effect and generates random
     * coordinates for all objects and random sizes for shapes.
     */
    static class ObjectData extends Object {

        final int UP = 0;
        final int DOWN = 1;
        Object object;
        BufferedImage bimg;
        Paint paint;
        double x, y;
        float alpha;
        int alphaDirection;
        int imgX;

        public ObjectData(Object object, Paint paint) {
            this.object = object;
            this.paint = paint;
            if (object instanceof BufferedImage) {
                bimg = (BufferedImage) object;
                this.object = bimg.getSubimage(0, 0, 80, 80);
            }
            getRandomXY(300, 250);
            alpha = (float) Math.random();
            alphaDirection = Math.random() > 0.5 ? UP : DOWN;
        }

        // gets random coordinate values for object
        private void getRandomXY(int w, int h) {
            if (object instanceof TextData) {
                x = Math.random() * (w - ((TextData) object).width);
                y = Math.random() * h;
                y = y < ((TextData) object).height ? ((TextData) object).height : y;
            } else if (object instanceof Image) {
                x = Math.random() * (w - ((Image) object).getWidth(null));
                y = Math.random() * (h - ((Image) object).getHeight(null));
            } else if (object instanceof Shape) {
                Rectangle bounds = ((Shape) object).getBounds();
                x = Math.random() * (w - bounds.width);
                y = Math.random() * (h - bounds.height);
            }
        }

        // generates random sizes for object if object is a shape
        public void reset(int w, int h) {
            getRandomXY(w, h);
            double ww = 20 + Math.random() * ((w == 0 ? 400 : w) / 4);
            double hh = 20 + Math.random() * ((h == 0 ? 300 : h) / 4);
            if (object instanceof Ellipse2D) {
                ((Ellipse2D) object).setFrame(0, 0, ww, hh);
            } else if (object instanceof Rectangle2D) {
                ((Rectangle2D) object).setRect(0, 0, ww, ww);
            } else if (object instanceof RoundRectangle2D) {
                ((RoundRectangle2D) object).setRoundRect(0, 0, hh, hh, 20, 20);
            } else if (object instanceof Arc2D) {
                ((Arc2D) object).setArc(0, 0, hh, hh, 45, 270, Arc2D.PIE);
            } else if (object instanceof QuadCurve2D) {
                ((QuadCurve2D) object).setCurve(0, 0, w * .2, h * .4, w * .4, 0);
            } else if (object instanceof CubicCurve2D) {
                ((CubicCurve2D) object).setCurve(0, 0, 30, -60, 60, 60, 90, 0);
            } else if (object instanceof GeneralPath) {
                GeneralPath p = new GeneralPath();
                float size = (float) ww;
                p.moveTo(-size / 2.0f, -size / 8.0f);
                p.lineTo(+size / 2.0f, -size / 8.0f);
                p.lineTo(-size / 4.0f, +size / 2.0f);
                p.lineTo(+0.0f, -size / 2.0f);
                p.lineTo(+size / 4.0f, +size / 2.0f);
                p.closePath();
                object = p;
            }
        }

        // increments the alpha value and the BufferedImage subimage
        public void step(int w, int h) {
            if (object instanceof BufferedImage) {
                if ((imgX += 80) == 800) {
                    imgX = 0;
                }
                object = bimg.getSubimage(imgX, 0, 80, 80);
            }
            if (alphaDirection == UP) {
                if ((alpha += 0.05) > .99) {
                    alphaDirection = DOWN;
                    alpha = 1.0f;
                }
            } else if (alphaDirection == DOWN) {
                if ((alpha -= .05) < 0.01) {
                    alphaDirection = UP;
                    alpha = 0;
                    getRandomXY(w, h);
                }
            }
        }
    }  // End ObjectData class
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    public static void main(String argv[]) {
        final GamePanel demo = new GamePanel(new String[26], 9);
        JFrame f = new JFrame("me");
        f.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            public void windowDeiconified(WindowEvent e) {
                demo.start();
            }

            public void windowIconified(WindowEvent e) {
                demo.stop();
            }
        });
        f.getContentPane().add("Center", demo);
        f.pack();
        f.setSize(demo.getSize());
        f.show();
        demo.start();
    }
}
