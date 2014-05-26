/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mul.app.audio;
/*
 * Copyright (C) 2005 Caio Filipini, Sergio Umlauf
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * Created on 14/09/2005
 *
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Reads GZIP, Zip, and Jar files
 *
 * @author Sergio Umlauf
 *
 */
public class Zip {

    /**
     * Reads a GZIP file and dumps the contents to the console.
     */
    public static void readGZIPFile(String fileName) {
        // use BufferedReader to get one line at a time
        BufferedReader gzipReader = null;
        try {
            // simple loop to dump the contents to the console
            gzipReader = new BufferedReader(
                    new InputStreamReader(
                    new GZIPInputStream(
                    new FileInputStream(fileName))));
            while (gzipReader.ready()) {
                System.out.println(gzipReader.readLine());
            }
            gzipReader.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("The file was not found: " + fnfe.getMessage());
        } catch (IOException ioe) {
            System.out.println("An IOException occurred: " + ioe.getMessage());
        } finally {
            if (gzipReader != null) {
                try {
                    gzipReader.close();
                } catch (IOException ioe) {
                }
            }
        }
    }

    /**
     * Reads a Zip file, iterating through each entry and dumping the contents
     * to the console.
     */
    public static void readZipFile(String fileName) {
        ZipFile zipFile = null;
        try {
            // ZipFile offers an Enumeration of all the files in the Zip file
            zipFile = new ZipFile(fileName);
            for (Enumeration e = zipFile.entries(); e.hasMoreElements();) {
                ZipEntry zipEntry = (ZipEntry) e.nextElement();
                System.out.println(zipEntry.getName() + " contains:");
                // use BufferedReader to get one line at a time
                BufferedReader zipReader = new BufferedReader(
                        new InputStreamReader(zipFile.getInputStream(zipEntry)));
                while (zipReader.ready()) {
                    System.out.println(zipReader.readLine());
                }
                zipReader.close();
            }
        } catch (IOException ioe) {
            System.out.println("An IOException occurred: " + ioe.getMessage());
        } finally {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException ioe) {
                }
            }
        }
    }

    /**
     * Reads a Jar file, displaying the attributes in its manifest and dumping
     * the contents of each file contained to the console.
     */
    public static void readJarFile(String fileName) {
        JarFile jarFile = null;
        try {
            // JarFile extends ZipFile and adds manifest information
            jarFile = new JarFile(fileName);
            if (jarFile.getManifest() != null) {
                System.out.println("Manifest Main Attributes:");
                Iterator iter =
                        jarFile.getManifest().getMainAttributes().keySet().iterator();
                while (iter.hasNext()) {
                    Attributes.Name attribute = (Attributes.Name) iter.next();
                    //System.out.println(attribute + " : "
                           // + jarFile.getManifest().getMainAttributes().getValue(attribute));
                }
                System.out.println();
            }
            // use the Enumeration to dump the contents of each file to the console
            System.out.println("Jar file entries:");
            for (Enumeration e = jarFile.entries(); e.hasMoreElements();) {
                JarEntry jarEntry = (JarEntry) e.nextElement();
                if (!jarEntry.isDirectory()) {

                    File newFile = new File(jarEntry.getName());
                    String directory = newFile.getParent();

                    if (directory == null) {
                        if (newFile.isDirectory()) {
                            System.out.println(newFile.getAbsolutePath());
                        }
                    }


                    System.out.println(jarEntry.getName() + " contains:");
                    BufferedReader jarReader = new BufferedReader(
                            new InputStreamReader(jarFile.getInputStream(jarEntry)));
                    while (jarReader.ready()) {
                        //System.out.println(jarReader.readLine());
                    }
                    jarReader.close();
                }
            }
        } catch (IOException ioe) {
            System.out.println("An IOException occurred: " + ioe.getMessage());
        } finally {
            if (jarFile != null) {
                try {
                    jarFile.close();
                } catch (IOException ioe) {
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        readJarFile("dist\\MultimediaApp.jar");

    }
}
