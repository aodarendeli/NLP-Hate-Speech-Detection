/*
  Bu yazdığımız kod ile nefret içerikli kelimelerizi csv de dosyasından okuyup, tweetlerimizin olduğu cümlelerden kontrol ederek
  Cümlede kaç tane nefret içerikli kelime varsa yanına sayısını yazmaktadır.
*/
package filter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 *
 * @author Özgür
 */
public class Filter {
static Map<String, String[]> words = new HashMap<>();
static int largestWordLength = 0;

 public static void loadConfigs() throws Exception {
        try {
            FileReader file=new FileReader("D:/Ozgur/Desktop/BadWords.csv");
            BufferedReader reader = new BufferedReader(file);
            String line = "";
            int counter = 0;
            while((line = reader.readLine()) != null) {
                counter++;
                String[] content = null;
                try {
                    content = line.split(",");
                    if(content.length == 0) {
                        continue;
                    }
                    String word = content[0];
                    String[] ignore_in_combination_with_words = new String[]{};
                    if(content.length > 1) {
                        ignore_in_combination_with_words = content[1].split("_");
                    }

                    if(word.length() > largestWordLength) {
                        largestWordLength = word.length();
                    }
                    words.put(word.replaceAll(" ", ""), ignore_in_combination_with_words);

                } catch(Exception e) {
                   // e.printStackTrace();
                }

            }
          //  System.out.println("Loaded " + counter + " words to filter out");
        } catch (IOException e) {
            //e.printStackTrace();
        }
  
    }
 public static ArrayList<String> badWordsFound(String input) {
        if(input == null) {
            return new ArrayList<>();
        }

      
        
        input = input.replaceAll("1","i");
        input = input.replaceAll("!","i");
        input = input.replaceAll("3","e");
        input = input.replaceAll("4","a");
        input = input.replaceAll("@","a");
        input = input.replaceAll("5","s");
        input = input.replaceAll("7","t");
        input = input.replaceAll("0","o");
        input = input.replaceAll("9","g");
        

        ArrayList<String> badWords = new ArrayList<>();
        input = input.toLowerCase().replaceAll("[^a-zA-Z]", "");

        
        for(int start = 0; start < input.length(); start++) {
          
            for(int offset = 1; offset < (input.length()+1 - start) && offset < largestWordLength; offset++)  {
                String wordToCheck = input.substring(start, start + offset);
                if(words.containsKey(wordToCheck)) {
                   
                    String[] ignoreCheck = words.get(wordToCheck);
                    boolean ignore = false;
                    for(int s = 0; s < ignoreCheck.length; s++ ) {
                        if(input.contains(ignoreCheck[s])) {
                            ignore = true;
                            break;
                        }
                    }
                    if(!ignore) {
                        badWords.add(wordToCheck);
                    }
                }
            }
        }


        badWords.stream().forEach((s) -> {
           // System.out.println(s + " qualified as a bad word in a username");
    });
        return badWords;

    }

    public static String filterText(String input) {
        ArrayList<String> badWords = badWordsFound(input);
        if(badWords.size() >= 0) {
            System.out.println(badWords.size());
        }
        return input;
    }
    /*public static void loadExcel() throws Exception {
        String fileName="data.csv";
        File file = new File(fileName);
        try{
            Scanner inputStream=new Scanner(file);
            while(inputStream.hasNext()){
                String data=inputStream.next();
                System.out.println(data+"***");
            }
            inputStream.close();
           } catch(FileNotFoundException e){
               e.printStackTrace();
           }
    }*/
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
       BufferedReader inputStream = null;
        try {
            inputStream = new BufferedReader(new FileReader("D:/Ozgur/Desktop/pozitif.csv"));
            String data;
            while ((data = inputStream.readLine()) != null) {
		        loadConfigs();		
                        System.out.print(data);
                        filterText(data);
                       
                        
           }
            
            
            }catch (Exception e) {
            // TODO Auto-generated catch block
           // e.printStackTrace();
        }
        
    }
}

