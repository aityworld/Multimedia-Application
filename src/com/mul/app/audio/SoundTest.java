/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mul.app.audio;
import java.util.*;
import javax.sound.midi.*;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;


class SoundTest
{
   public static void main(String[] args) {
       try {
           Synthesizer synth = MidiSystem.getSynthesizer();
           synth.open();

           final MidiChannel[] mc    = synth.getChannels();
           Instrument[]        instr = synth.getDefaultSoundbank().getInstruments();
           synth.loadInstrument(instr[76]);  // Bottle Blow
           for (int i = 0; i < 120; ++i) {
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {}
               mc[4].noteOn(i,200);
           }
       } catch (MidiUnavailableException e) {}
   }
}

