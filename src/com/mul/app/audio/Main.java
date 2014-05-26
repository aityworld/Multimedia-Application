/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mul.app.audio;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
  public static void main(String[] args) throws Exception{
     String properties = "java.io.tmpdir";
        

        String tempDirt = System.getProperty(properties);
        System.out.println("Windows xp current temporary directory is " + tempDirt);

    String destinationname = "c:\\ccc";
    byte[] buf = new byte[1024];
    ZipInputStream zipinputstream = null;
    ZipEntry zipentry;
    zipinputstream = new ZipInputStream(new FileInputStream("fileName"));
    zipentry = zipinputstream.getNextEntry();
    while (zipentry != null) {
      String entryName = zipentry.getName();
      FileOutputStream fileoutputstream;
      File newFile = new File(entryName);
      String directory = newFile.getParent();

      if (directory == null) {
        if (newFile.isDirectory())
          break;
      }
      fileoutputstream = new FileOutputStream(destinationname + entryName);
      int n;
      while ((n = zipinputstream.read(buf, 0, 1024)) > -1){
        fileoutputstream.write(buf, 0, n);
      }
      fileoutputstream.close();
      zipinputstream.closeEntry();
      zipentry = zipinputstream.getNextEntry();
    }
    zipinputstream.close();
  }
}
