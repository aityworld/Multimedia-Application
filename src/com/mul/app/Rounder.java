/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mul.app;

/**
 *
 * @author Aity
 */
public class Rounder {

    public static void main(String[] args) {
        float num = 2.455765f;
        float round = round(num, 3);
        System.out.println("Rounded data: " + round);
    }

    public static float round(float Rval, int Rpl) {
        float p = (float) Math.pow(10, Rpl);
        Rval = Rval * p;
        float tmp = Math.round(Rval);
        return (float) tmp / p;
    }

    public static double round(double Rval, int Rpl) {
        float p = (float) Math.pow(10, Rpl);
        Rval = Rval * p;
        float tmp = Math.round(Rval);
        return (float) tmp / p;
    }
}
