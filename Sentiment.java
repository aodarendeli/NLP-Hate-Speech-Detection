/*
     Stanford nlp kütüphanesini kullanarak sentimental analysis yapmayı sağlayan programımız.
 */
package com.darendeliozgur.mavenproject2;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author Özgür
 */
public class Sentiment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        StanfordCoreNLP stanford=Pipeline.getPipeline();
        BufferedReader inputStream = null;
        inputStream = new BufferedReader(new FileReader("D:/Ozgur/Desktop/ekstra.txt"));
        String text;
        String filename="D:/Ozgur/Desktop/a5.txt";
        PrintWriter outputStream=new PrintWriter(filename);
        while ((text = inputStream.readLine()) != null) {
            
        
        CoreDocument coreDocument=new CoreDocument(text);
        stanford.annotate(coreDocument);
        List<CoreSentence> sentences=coreDocument.sentences();
        for(CoreSentence sentence : sentences){
            String sentiment=sentence.sentiment();
            System.out.println(sentiment+"\t"+sentence);
            outputStream.println(sentiment+"\t"+sentence);
        }
          
        } 
         outputStream.close();        
        
    }
}
    

