/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mul.app;

import com.mul.app.audio.MidiPlayer;
import com.mul.app.ui.MainView;
import java.util.HashMap;
import java.util.Hashtable;
import javax.swing.JFrame;

/**
 *
 * @author Aity
 */
public class MainApp  {

    public static HashMap<Integer, String> alphas;
    public static HashMap<String, String> alphas2;

    public MainApp() {
    }

    public static void main  (String argv[]) throws Exception {
        JFrame f = new MainView();
        f.setVisible(true);
        MidiPlayer.play("HeWillDoNewThings1.mid", true);
    }

    public static void doExit() {
        MidiPlayer.stop();
        System.exit(0);

    }

    public static void loadAlphabets() {
        alphas = new HashMap<Integer, String>();
       /* alphas.put(1, "Apple");
        alphas.put(2, "Letter B");
        alphas.put(3, "CAT");
        alphas.put(4, "Dog");
        alphas.put(5, "Elephant");
        alphas.put(6, "Fish");
        alphas.put(7, "Gun");
        alphas.put(8, "House");
        alphas.put(9, "Iron");
        alphas.put(10, "Jug");
        alphas.put(11, "Kettle");
        alphas.put(12, "Lion");
        alphas.put(13, "Mouse");
        alphas.put(14, "Nail");
        alphas.put(15, "Orange");
        alphas.put(16, "Pencil");
        alphas.put(17, "Queen");
        alphas.put(18, "Rat");
        alphas.put(19, "Snail");
        alphas.put(20, "Table");
        alphas.put(21, "Umbrella");
        alphas.put(22, "Van");
        alphas.put(23, "Watch");
        alphas.put(24, "Xylophone");
        alphas.put(25, "Yam");
        alphas.put(26, "Zebra"); */
        alphas.put(1, "Letter A stands for APPLE");
        alphas.put(2, "Letter B stands for BALL");
        alphas.put(3, "Letter C stands for CAT");
        alphas.put(4, "Letter D stands for DOG");
        alphas.put(5, "Letter E stands for EGG");
        alphas.put(6, "Letter F stands for FISH");
        alphas.put(7, "Letter G stands for GOAT");
        alphas.put(8, "Letter H stands for HOUSE");
        alphas.put(9, "Letter I stands for IRON");
        alphas.put(10, "Letter J stands for JUG");
        alphas.put(11, "Letter K stands for KEY");
        alphas.put(12, "Letter L stands for LION");
        alphas.put(13, "Letter M stands for MAT");
        alphas.put(14, "Letter N stands for NAIL");
        alphas.put(15, "Letter O stands for ORANGE");
        alphas.put(16, "Letter P stands for PENCIL");
        alphas.put(17, "Letter Q stands for QUEEN");
        alphas.put(18, "Letter R stands for RAT");
        alphas.put(19, "Letter S stands for SNAIL");
        alphas.put(20, "Letter T stands for TABLE");
        alphas.put(21, "Letter U stands for UMBRELLA");
        alphas.put(22, "Letter V stands for VAN");
        alphas.put(23, "Letter W stands for WATCH");
        alphas.put(24, "Letter X stands for XYLOPHONE");
        alphas.put(25, "Letter Y stands for YAM");
        alphas.put(26, "Letter Z stands for ZEBRA");
        //////////////////////
        alphas2 = new HashMap<String, String>();
        alphas2.put("A", "APPLE");
        alphas2.put("B", "BALL");
        alphas2.put("C", "CAT");
        alphas2.put("D", "DOG");
        alphas2.put("E", "EGG");
        alphas2.put("F", "FISH");
        alphas2.put("G", "GOAT");
        alphas2.put("H", "HOUSE");
        alphas2.put("I", "IRON");
        alphas2.put("J", "JUG");
        alphas2.put("K", "KEY");
        alphas2.put("L", "LION");
        alphas2.put("M", "MAT");
        alphas2.put("N", "NAIL");
        alphas2.put("O", "ORANGE");
        alphas2.put("P", "PENCIL");
        alphas2.put("Q", "QUEEN");
        alphas2.put("R", "RAT");
        alphas2.put("S", "SNAIL");
        alphas2.put("T", "TABLE");
        alphas2.put("U", "UMBRELLA");
        alphas2.put("V", "VAN");
        alphas2.put("W", "WATCH");
        alphas2.put("X", "XYLOPHONE");
        alphas2.put("Y", "YAM");
        alphas2.put("Z", "ZEBRA");
    }
}
