/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mul.app;

public class Shuffle {
    private static String[] ss = {"vhfgh","nbmghm","hbnmmhjn","zsddesd","lkhgf"};
    // swaps array elements i and j
    public static void exch(String[] a, int i, int j) {
        String swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // take as input an array of strings and rearrange them in random order
    public static void shuffle(String[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int r = i + (int) (Math.random() * (N - i));   // between i and N-1
            exch(a, i, r);
        }
    }

    // take as input an array of strings and print them out to standard output
    public static void show(String[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    // shuffle the command line arguments
    public static void main(String[] args) {
        shuffle(ss);
        show(ss);

    }
}
