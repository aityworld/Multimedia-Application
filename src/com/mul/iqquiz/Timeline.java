package com.mul.iqquiz;

import java.awt.*;
import java.awt.event.*;
import java.lang.String.*;
import java.awt.Image.*;
import java.util.GregorianCalendar;
// De Tijdlijn, commentaar en X-V indicator	

public class Timeline extends Canvas implements Runnable {

    Toolkit toolkit;
    static Thread messageThread;
    Screen sc;
    Iqquiz frame;
    GregorianCalendar calendar;
    boolean running = true; // stops thread
    Color fontkleur;
    Color timekleur;
    int red, green, blue;
    int uurend;
    int minend;
    int secend;
    int uurstarted;
    int minstarted;
    int secstarted;
    int uur1 = 0;
    int min1 = 0;
    int sec1 = 0;
    String stopstring = "Ready to start";

    public Timeline(Screen sc, Iqquiz frame) {
        toolkit = Toolkit.getDefaultToolkit();
        this.sc = sc;
        this.frame = frame;
        this.setBackground(fontkleur);
        
        // sets fontcolors (param)
        calendar = new GregorianCalendar();
        red = 255;
        green = 255;
        blue = 0;
        fontkleur = new Color(102, 102, 0);
        // sets timeline colors (params)
        red = 255;
        green = 255;
        blue = 0;
        timekleur = new Color(102, 102, 0);

        if (messageThread == null) {
            messageThread = new Thread(this);
        }
    }
// Thread action

    public void run() {
        while (true) {
            toolkit.beep();
            stopstring = "Running";
            sec1++;
            if (sec1 > 59) {
                min1++;
                sec1 = 0;
            }
            if (min1 == 5) {
                stopstring = "Time Up";
                frame.timeup = true;
                repaint();
                frame.stoppressed = true;
                frame.checkanswer();
                //frame.question = 11;
                sc.repaint();
                toolkit.beep();
                messageThread.suspend();
            }
            //	messageThread.suspend();
            repaint();
            try {
                messageThread.sleep(1000);
            } catch (InterruptedException ire) {
            }
        }
    }

    public void volgende() {
        if (sc.succes) { // true if all spots found
            sc.repaint(); // new pictures
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.drawString("MINUTE: " + (4 - min1), 20, 20);
        g.drawString("SECOND: " + (60 - sec1), 20, 40);
        g.drawString(stopstring, 20, 60);
    }

// resets timeline variables
    public void Clear() {
        running = true;
        messageThread.resume();	// Herstart de slapende Thread

        sc.succes = false;
    }
    // pauze method
    public void stopff() {
    }
// New game actions
    public void opnieuw() {


        frame.next.setLabel("Next");
        running = true;
    }
}