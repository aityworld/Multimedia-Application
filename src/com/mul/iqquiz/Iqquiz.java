package com.mul.iqquiz;

//
// Source Programming by DaHazard (Bavo Bruylandt)
// Find It V1.4 15/05/2000
//
import com.mul.app.ui.EnterPanel;
import com.mul.app.ui.MainView;
import java.awt.*;
import java.awt.event.*;
import java.lang.String.*;
import java.awt.Image.*;
import javax.swing.*;

// Makes the frame
public class Iqquiz extends JPanel implements WindowListener, ActionListener {

    MenuItem newgame, exit, about, soff, help, keuze;
    public Button pauze, stop, previous, helpbt;
    public Button next;
    
    Button[] opt = new Button[5];
    JTextField tf1, tf2;
    private Screen sc;
    private Timeline tl;
    private Infocanvas info;
    boolean text = false;
    boolean sound = true;
    int red, green, blue;
    int question = 0;
    int bpressed = 0;
    int good = 0;
    String[] answer = new String[16];
    String[] answer2 = new String[16];
    boolean pause = false;
    boolean stoppressed = false;
    boolean start = true;
    boolean checked = false;
    boolean timeup = false;
    boolean cheated = false;
    String[] solution = {"0", "10", "4", "37", "3", "2", "SWALLOW", "4", "NOSE", "2", "O", "16", "4", "33", "20", "0", "0"};;
	String [] solution2 = {"0", "Y"};
    Color btcolor;

    public Iqquiz() {
        //super((iq.reg)? "IQ Quiz Registered to " + iq.companyname: "IQ Quiz Unregistered Version");

        MenuBar mbalk = new MenuBar();
        //setMenuBar(mbalk);
        Menu mGame = new Menu("Game");
        Menu mAbout = new Menu("Help");
        mbalk.add(mGame);
        mbalk.add(mAbout);
        newgame = new MenuItem("New Game");
        exit = new MenuItem("Exit Game");
        help = new MenuItem("Help...");
        about = new MenuItem("About..");
        soff = new MenuItem("Pause");
        mGame.add(newgame);
        mGame.add(soff);
        mGame.insertSeparator(1);
        mGame.add(exit);
        mAbout.add(help);
        mAbout.add(about);
        newgame.addActionListener(this);
        exit.addActionListener(this);
        soff.addActionListener(this);
        about.addActionListener(this);
        help.addActionListener(this);
        Dimension dimScreen = getToolkit().getScreenSize();
        int frSize1 = 700;
        int frSize2 = 550;
        setLocation(0, 0);
        setSize(frSize1, frSize2);
        //addWindowListener(this);
        setLayout(null);
        sc = new Screen(this); // Photoscreen Canvas
        tl = new Timeline(sc, this); // Timeline Canvas
        info = new Infocanvas(sc, this);
        tf1 = new JTextField("");
        tf2 = new JTextField("");
        tf1.setBackground(Color.white);
        tf2.setBackground(Color.white);
        pauze = new Button("Pause");
        next = new Button("Start");
        stop = new Button("Stop");
        helpbt = new Button("Solution");
        previous = new Button("Previous");
        btcolor = new Color(102, 102, 0);
        for (int a = 0; a < 5; a++) {
            opt[a] = new Button("Choice " + (a + 1));
            opt[a].setBounds(50 + a * 90, 400, 70, 30);
            opt[a].addActionListener(this);
            opt[a].setBackground(btcolor);
        }
        tf1.setBounds(50, 400, 150, 30);
        tf2.setBounds(250, 400, 150, 30);
        helpbt.setBounds(50, 450, 100, 40);
        pauze.setBounds(50, 450, 100, 40);
        next.setBounds(50, 450, 360, 40);
        helpbt.addActionListener(this);
        stop.setBounds(290, 450, 100, 40);
        previous.setBounds(410, 450, 100, 40);
        helpbt.setBounds(530, 450, 100, 40);
        stop.addActionListener(this);
        previous.addActionListener(this);
        next.addActionListener(this);
        pauze.addActionListener(this);
        stop.setBackground(btcolor);
        helpbt.setBackground(btcolor);
        next.setBackground(btcolor);
        pauze.setBackground(btcolor);
        previous.setBackground(btcolor);

        sc.setBounds(40, 80, frSize1 - 200, 300);
        add(sc);
        tl.setBounds(frSize1 - 120, 80, 100, 150);
        add(tl);
        info.setBounds(frSize1 - 120, 250, 100, 130);
        add(info);
        // Sets the backgroundcolor (param)
        red = 0;
        green = 0;
        blue = 0;
        red = 0;
        green = 0;
        blue = 122;
        Color kleur = new Color(red, green, blue);
        Color kleur2 = new Color(102, 102, 0);

        sc.setBackground(kleur2);
        info.setBackground(kleur2);
        tl.setBackground(kleur2);
        setBackground(kleur);
        for (int a = 0; a < 16; a++) {
            answer[a] = "";
        }
        add(next);
        validate();
        doLayout();
        next.validate();

    }

    public void actionPerformed(ActionEvent actionE) {
        // Button actions
        if (actionE.getSource() == pauze || actionE.getSource() == stop || actionE.getSource() == previous || actionE.getSource() == next || actionE.getSource() == opt[0] || actionE.getSource() == opt[1] || actionE.getSource() == opt[2] || actionE.getSource() == opt[3] || actionE.getSource() == opt[4] || actionE.getSource() == helpbt) {
            if (actionE.getSource() == pauze) {
                pauseCheck();
            }
            if (actionE.getSource() == helpbt) {
                 if (tl.messageThread != null) {
                        tl.messageThread.stop();
                        tl.messageThread = null;
                    }
                //this.dispose();
                MainView.switchView(new EnterPanel());
            }

            if (actionE.getSource() == stop) {
                stop.setLabel("Stopped");
                next.setLabel("Done");
                stoppressed = true;
                checkanswer();
                sc.repaint();
                removeAll();
                tl.messageThread.suspend();
            }


            if (actionE.getSource() == opt[0]) {
                bpressed = 1;
                saveanswer();
                nextquestion();
                sc.repaint();
            }
            if (actionE.getSource() == opt[1]) {
                bpressed = 2;
                saveanswer();
                nextquestion();
                sc.repaint();
            }
            if (actionE.getSource() == opt[2]) {
                bpressed = 3;
                saveanswer();
                nextquestion();
                sc.repaint();
            }
            if (actionE.getSource() == opt[3]) {
                bpressed = 4;
                saveanswer();
                nextquestion();
                sc.repaint();
            }
            if (actionE.getSource() == opt[4]) {
                bpressed = 5;
                saveanswer();
                nextquestion();
                sc.repaint();
            }
            if (actionE.getSource() == next) {
                if (next.getLabel() == "Done") {
                    restart();
                    removeAll();
                } else {
                    if (start) {
                        helpbt.setLabel("Home");
                        next.setBounds(170, 450, 100, 40);
                        add(pauze);
                        add(stop);
                        add(helpbt);
                        add(previous);
                        start = false;
                        next.setLabel("Next");
                        nextquestion();
                        sc.repaint();
                        pauze.requestFocus();
                        stop.requestFocus();
                        previous.requestFocus();
                        next.requestFocus();
                        pauze.setVisible(true);
                        previous.setVisible(true);
                        stop.setVisible(true);
                        next.setVisible(true);
                        try {
                            tl.messageThread.start();
                        } catch (Exception te) {
                            tl.messageThread.resume();
                        }

                    } else {
                        saveanswer();
                        nextquestion();

                        if (answer[question] != null && text) {
                            tf1.setText(answer[question]);
                            tf2.setText(answer2[question]);
                        }
                        sc.repaint();

                    }
                }
                if (next.getLabel().equals("Finish")) {
                    ;
                }
            }
            if (actionE.getSource() == previous) {
                if (question > 1) {
                    tf1.requestFocus();
                    question -= 2;
                    tf1.setText(answer[question + 1]);
                    tf2.setText(answer2[question + 1]);
                    nextquestion();
                    sc.repaint();
                    info.repaint();
                }
            }
        } // Menu actions
        else {
            keuze = (MenuItem) actionE.getSource();
            if (keuze != null) {
                String s = null;
                if (keuze == newgame) {
                    restart();
                } else if (keuze == exit) {
                    if (tl.messageThread != null) {
                        tl.messageThread.stop();
                        tl.messageThread = null;
                    }
                //this.dispose();	
                } else if (keuze == about) {
                    ;
                } else if (keuze == soff) {
                    pauseCheck();
                } else if (keuze == help) {
                    ;
                }
            }
        }
    }

    // WindowEvents	
    public void windowClosing(WindowEvent e) {
        if (tl.messageThread != null) {
            tl.messageThread.stop();
            tl.messageThread = null;
        }
        //this.dispose ();
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowOpened(WindowEvent e) {
    }

    public void nextquestion() {
        tf1.requestFocus();
        question++;
        info.repaint();
        if (question == 14) {
            next.setLabel("Finish");
            text = true;
        } else {
            next.setLabel("Next");
            if (question == 1) {
                text = true;
            }
            if (question == 2) {
                text = false;
            }
            if (question == 3) {
                text = true;
            }
            if (question == 4) {
                text = false;
            }
            if (question == 5) {
                text = false;
            }
            if (question == 6) {
                text = true;
            }
            if (question == 7) {
                text = false;
            }
            if (question == 8) {
                text = true;
            }
            if (question == 9) {
                text = false;
            }
            if (question == 10) {
                text = true;
            }
            if (question == 11) {
                text = true;
            }
            if (question == 12) {
                text = false;
            }
            if (question == 13) {
                text = true;
            }
            if (question == 14) {
                text = true;
            }
            pauze.requestFocus();
            stop.requestFocus();
            previous.requestFocus();
            next.requestFocus();
            pauze.setVisible(true);
            next.setVisible(true);
            stop.setVisible(true);
            previous.setVisible(true);
            if (question == 15) {
                stoppressed = true;
                checkanswer();
                next.setLabel("Done");
                tl.stopstring = "Finished";
                refresh();
                removeAll();
                tl.messageThread.suspend();
            }
        }
        buttons();
    }

    public void checkanswer() {
        if (!checked) {
            question = 0;
            for (int a = 1; a < 16; a++) {
                question++;
                if (question == 1) {
                    if (answer[1].toUpperCase().equals(solution[1])) {
                        good++;
                    }
                }
                if (question == 2) {
                    if (answer[2].toUpperCase().equals(solution[2])) {
                        good++;
                    }
                }
                if (question == 3) {
                    if (answer[3].toUpperCase().equals(solution[3])) {
                        good++;
                    }
                }
                if (question == 4) {
                    if (answer[4].toUpperCase().equals(solution[4])) {
                        good++;
                    }
                }
                if (question == 5) {
                    if (answer[5].toUpperCase().equals(solution[5])) {
                        good++;
                    }
                }
                if (question == 6) {
                    if (answer[6].toUpperCase().equals(solution[6])) {
                        good++;
                    }
                }
                if (question == 7) {
                    if (answer[7].toUpperCase().equals(solution[7])) {
                        good++;
                    }
                }
                if (question == 8) {
                    if (answer[8].toUpperCase().equals(solution[8])) {
                        good++;
                    }
                }
                if (question == 9) {
                    if (answer[9].toUpperCase().equals(solution[9])) {
                        good++;
                    }
                }
                if (question == 10) {
                    if (answer[10].toUpperCase().equals(solution[10]) && answer2[10].toUpperCase().equals(solution2[1])) {
                        good++;
                    }
                }
                if (question == 11) {
                    if (answer[11].toUpperCase().equals(solution[11])) {
                        good++;
                    }
                }
                if (question == 12) {
                    if (answer[12].toUpperCase().equals(solution[12])) {
                        good++;
                    }
                }
                if (question == 13) {
                    if (answer[13].toUpperCase().equals(solution[13])) {
                        good++;
                    }
                }
                if (question == 14) {
                    if (answer[14].toUpperCase().equals(solution[14])) {
                        good++;
                    }
                }

                checked = true;
            }
        }
    }

    public void saveanswer() {
        if (text) {
            if (!tf1.getText().equals("")) {
                answer[question] = tf1.getText();
                answer2[question] = tf2.getText();
            }
        } else {
            if (bpressed != 0) {
                answer[question] = "" + bpressed;
            }
        }
    }

    public void paint(Graphics d) {
        d.setColor(Color.white);
        d.draw3DRect(575, 75, 110, 158, false);
        d.draw3DRect(575, 240, 110, 145, false);
        d.draw3DRect(35, 75, 510, 310, false);
    }

    public void buttons() {
        if (question != 15) {
            if (text) {
                for (int a = 0; a < 5; a++) {
                    remove(opt[a]);
                }
                add(tf1);
                if (question == 10) {
                    add(tf2);
                    tf2.setVisible(true);
                }
                repaint();
                pauze.requestFocus();
                stop.requestFocus();
                previous.requestFocus();
                next.requestFocus();
                validate();
                tf1.requestFocus();
                tf1.setText("focussing");
                tf2.setText("focussing");
                tf1.setText("");
                tf2.setText("");
                tf1.setVisible(true);

            } else {
                for (int a = 0; a < 5; a++) {
                    add(opt[a]);
                    opt[a].requestFocus();
                    opt[a].setVisible(true);
                    bpressed = 0;
                }
                remove(tf1);
                remove(tf2);
                tf1.setVisible(false);
            }
        }
    }

    public void restart() {

        text = false;
        sound = true;
        question = 0;
        bpressed = 0;
        good = 0;
        String[] answer = new String[16];
        String[] answer2 = new String[16];
        pause = false;
        stoppressed = false;
        start = true;
        checked = false;
        timeup = false;
        next.setLabel("Start");
        for (int a = 0; a < 16; a++) {
            answer[a] = "null";
        }
        tl.uur1 = 0;
        tl.min1 = 0;
        tl.sec1 = 0;
        sc.iqpt = 0;
        tl.stopstring = "Ready to start";
        info.answered = 0;
        refresh();
        removeAll();
        checked = false;

    }

    public void refresh() {
        sc.repaint();
        tl.repaint();
        info.repaint();
    }

    public void removeAll() {
        for (int f = 0; f < 5; f++) {
            remove(opt[f]);
        }
        remove(tf1);
        remove(tf2);
        remove(previous);
        remove(pauze);
        remove(stop);
        helpbt.setLabel("Home");

    }

    public void pauseCheck() {
        if (!pause) {
            pause = true;
            tl.stopstring = "Paused";
            tl.messageThread.suspend();
            pauze.setLabel("Resume");
            sc.repaint();
            tl.repaint();
        } else {
            pause = false;
            tl.messageThread.resume();
            pauze.setLabel("Pause");
            sc.repaint();
        }
    }
}
