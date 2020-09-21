    /*
       Zemberek pom.xml nlp kütüphanesini kullanarak cumlelerdeki ünlü ünsüz harf hatalarýný deðiþtirdik.

 */
package com.darendeliozgur.zemberek.nlp;

import com.google.common.base.Splitter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import zemberek.morphology.TurkishMorphology;
import zemberek.normalization.TurkishSentenceNormalizer;
import zemberek.normalization.TurkishSpellChecker;
import zemberek.normalization.deasciifier.Deasciifier;


public class SimpleClassifacation {

  

  public static void main(String[] args) throws IOException {
   BufferedReader inputStream = null;
    Deasciifier d = new Deasciifier();
     inputStream = new BufferedReader(new FileReader("D:/Ozgur/Desktop/a.txt"));
     String filename="D:/Ozgur/Desktop/pozitif.txt";
     PrintWriter outputStream=new PrintWriter(filename);
            String data;
            
            while ((data = inputStream.readLine()) != null) {
                                d.setAsciiString(data);
				outputStream.println(d.convertToTurkish());  
                                
           }
            outputStream.close();
 }
} 
    



