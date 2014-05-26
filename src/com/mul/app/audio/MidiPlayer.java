/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mul.app.audio;

////////////////////////////////////////////////////////////

import java.io.File;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;

////////////////////////////////////////////////////////////
// You are not (yet) responsible for the code below here!
//
// MidiPlayer
//
// Include this class if you want to play midi files,
// And include these imports:
//
// import java.io.*;
// import javax.sound.midi.*;
//
////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////
public class MidiPlayer {
        
    public static void play(String filename) {
        play(filename, false);
    }

    public static void play(String filename, boolean loop) {
        try {
            stop();
            sequencer = MidiSystem.getSequencer();
            File midiFile = new File(filename);
            sequencer.setSequence(MidiSystem.getSequence(midiFile));
            if (loop) {
                sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            }
            sequencer.open();
            sequencer.start();
        } catch (Exception e) {
            System.err.println("MidiPlayer: " + e);
            sequencer = null;
        }
    }

    public MidiPlayer() {
    }


    public static Sequencer getSequencer() {
        return sequencer;
    }

    public static void stop() {
        try {
            if ((sequencer == null) || (!sequencer.isRunning())) {
                return;
            }
            sequencer.stop();
            sequencer.close();
        } catch (Exception e) {
            System.err.println("MidiPlayer: " + e);
        }
        sequencer = null;
    }
    private static Sequencer sequencer = null;

    public static void main(String[] args){
      MidiPlayer.play("HeWillDoNewThings1.mid", true);
    }
}