package com.mul.iqquiz;

import java.awt.*;
import java.awt.event.*;
import java.lang.String.*;
import java.util.EventListener;
import java.awt.Image.*;
import java.net.*;

// Photoscreen
class Screen extends Canvas implements MouseListener {

    Toolkit toolkit;
    int[] xpos = new int[5];
    int[] ypos = new int[5];
    boolean succes = false;
    URL url;
    int iqptint;
    boolean load;
    private Iqquiz frame;
    int xpo, ypo;
    Image img1, img2, img3, img4, img5, img6, img7;
    Font questions;
    Font answers;
    Font title;
    Font letters;
    double iqpt;

    public Screen(Iqquiz frame) {
        toolkit = Toolkit.getDefaultToolkit();
        load = true;
        repaint();
        this.frame = frame;
        addMouseListener(this);
        //url = iq.getDocumentBase();
        fetchimages();
        load = false;
        questions = new Font("Verdana", Font.BOLD, 16);
        answers = new Font("Verdana", Font.PLAIN, 16);
        title = new Font("Verdana", Font.BOLD, 40);
        letters = new Font("Verdana", Font.PLAIN, 20);

    }

    public void fetchimages() {

        img1 = toolkit.getImage("images/img1.gif"); // X - gif voor fout
        img2 = toolkit.getImage("images/img2.gif"); // X - gif voor fout
        img3 = toolkit.getImage("images/img3.gif"); // X - gif voor fout
        img4 = toolkit.getImage("images/img4.gif"); // X - gif voor fout
        img5 = toolkit.getImage("images/img5.gif"); // X - gif voor fout
        img6 = toolkit.getImage("images/img6.gif"); // X - gif voor fout
        img7 = toolkit.getImage("images/img7.gif"); // X - gif voor fout


        MediaTracker mt = new MediaTracker(this);
        mt.addImage(img1, 0);
        mt.addImage(img2, 0);
        mt.addImage(img3, 0);
        mt.addImage(img4, 0);
        mt.addImage(img5, 0);
        mt.addImage(img6, 0);
        mt.addImage(img7, 0);

        try {
            mt.waitForAll(0);
        } catch (InterruptedException e) {
        }
    }

    // Mousevents
    public void mouseClicked(MouseEvent event) {
    }

    public void mouseEntered(MouseEvent me) {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void mousePressed(MouseEvent me) {

        xpo = me.getX();
        ypo = me.getY();


        repaint();
    }

    public void mouseReleased(MouseEvent me) {
    }

    public void mouseExited(MouseEvent me) {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

// redraws canvas			
    public void paint(Graphics p) {
        if (load) {
            p.setColor(Color.red);
            p.drawString("Loading Pictures", 50, 50);
        }

        if (frame.stoppressed) {
            p.setColor(Color.white);
            p.setFont(title);
            p.drawString("Good answers: " + frame.good, 50, 50);
            iqpt = (double) frame.good / 1.4 * 2 * 10;
            iqptint = (int) iqpt;
            p.drawString("Your IQ: " + iqptint, 50, 100);
            p.setFont(letters);
            p.drawString("Remember that this is not a real IQ test!", 50, 150);
            p.drawString("The result is just for fun.", 50, 200);

        }

        if (!frame.stoppressed) { // Time not up
            if (!frame.pause) {
                if (frame.question == 0) {
                    p.setFont(title);
                    p.setColor(Color.white);
                    p.drawString("IQ Quiz / Game", 100, 100);
                    p.setFont(letters);
                    p.drawString("Designed to test your IQ", 80, 150);
                    p.drawString("Created for Multimedia Learning", 80, 200);
                    if (true)//iq.reg
                    {
                        p.drawString("Just for your IQ Testing", 80, 250);
                    } else {
                        p.drawString("Just for your IQ Testing", 80, 250);
                    }
                }
                if (frame.question == 1) {
                    p.setFont(questions);
                    p.setColor(Color.white);
                    p.drawString("What is the next number?", 50, 40);
                    p.setFont(answers);
                    p.drawString("2, 4, 6, 8, .", 50, 90);
                }
                if (frame.question == 2) {
                    p.setFont(questions);
                    p.setColor(Color.white);
                    p.drawString("What word doesn't fit?", 50, 40);
                    p.setFont(answers);
                    p.drawString("1. Diamond", 50, 90);
                    p.drawString("2. Exotic", 50, 130);
                    p.drawString("3. Friend", 50, 170);
                    p.drawString("4. Natural", 50, 210);
                    p.drawString("5. Generous", 50, 250);

                }
                if (frame.question == 3) {
                    p.setFont(questions);
                    p.setColor(Color.white);
                    p.drawString("What is the next number?", 50, 40);
                    p.setFont(answers);
                    p.drawString("2, 5, 10, 17, 26, .", 50, 90);
                }

                if (frame.question == 4) {
                    p.setFont(questions);
                    p.setColor(Color.white);
                    p.drawString("What figure matches these?", 50, 40);
                    p.setFont(answers);
                    p.drawImage(img1, 50, 60, this);
                }
                if (frame.question == 5) {
                    p.setFont(questions);
                    p.setColor(Color.white);
                    p.drawString("What animal doesn't match?", 50, 40);
                    p.setFont(answers);
                    p.drawString("1. Shark", 50, 90);
                    p.drawString("2. Whale", 50, 130);
                    p.drawString("3. Cod", 50, 170);
                    p.drawString("4. Tunny", 50, 210);
                    p.drawString("5. Goldfish", 50, 250);

                }
                if (frame.question == 6) {
                    p.setFont(questions);
                    p.setColor(Color.white);
                    p.drawString("What is the word?", 50, 40);
                    p.setFont(answers);
                    p.drawString("IO(FASHION)SA", 50, 90);
                    p.drawString("LO(S..L..W)AW", 50, 130);
                }
                if (frame.question == 7) {
                    p.setFont(questions);
                    p.setColor(Color.white);
                    p.drawString("What word is not a componist?", 50, 40);
                    p.setFont(answers);
                    p.drawString("1. ZOTRAM", 50, 90);
                    p.drawString("2. SATSURS", 50, 130);
                    p.drawString("3. DILVIAV", 50, 170);
                    p.drawString("4. MALOSO", 50, 210);

                }
                if (frame.question == 8) {
                    p.setFont(questions);
                    p.setColor(Color.white);
                    p.drawString("What is the word?", 50, 40);
                    p.setFont(answers);
                    p.drawString("SURPRISE(RULE)ELEPHANT", 50, 90);
                    p.drawString("CONFIRM(....)DESTINY", 50, 130);

                }
                if (frame.question == 9) {
                    p.setFont(questions);
                    p.setColor(Color.white);
                    p.drawString("Image1 relates to image2 as image3 relates to", 50, 40);
                    p.setFont(answers);
                    p.drawImage(img2, 50, 60, this);

                }
                if (frame.question == 10) {
                    p.setFont(questions);
                    p.setColor(Color.white);
                    p.drawString("What are the next letters?", 50, 40);
                    p.setFont(answers);
                    p.drawString("G  E  K  O  .", 50, 90);
                    p.drawString("A  I  J  M  .", 50, 130);

                }
                if (frame.question == 11) {
                    p.setFont(questions);
                    p.setColor(Color.white);
                    p.drawString("What is the missing number?", 50, 40);
                    p.setFont(answers);
                    p.drawImage(img4, 50, 60, this);

                }
                if (frame.question == 12) {
                    p.setFont(questions);
                    p.setColor(Color.white);
                    p.drawString("What is the missing figure?", 50, 40);
                    p.setFont(answers);
                    p.drawImage(img5, 50, 60, this);

                }
                if (frame.question == 13) {
                    p.setFont(questions);
                    p.setColor(Color.white);
                    p.drawString("What number should be next?", 50, 40);
                    p.setFont(answers);
                    p.drawImage(img6, 50, 60, this);

                }
                if (frame.question == 14) {
                    p.setFont(questions);
                    p.setColor(Color.white);
                    p.drawString("What is the missing number?", 50, 40);
                    p.setFont(answers);
                    p.drawImage(img7, 50, 60, this);

                }

                // Draws pictures
                if (frame.question > 0 && !frame.answer[frame.question].equals("")) {
                    p.setColor(Color.white);
                    p.drawString("You answered " + frame.answer[frame.question], 50, 292);
                }
            }

            Font p1 = new Font("Impact", Font.PLAIN, 10);
            p.setFont(p1);
            p.setColor(Color.red);



        }
        if (frame.pause) {
            p.setColor(Color.white);
            p.setFont(title);
            p.drawString("Paused", 50, 50);


        }




        if (succes) {
        }
    }
}





	
		