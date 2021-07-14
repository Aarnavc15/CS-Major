
/**
 * Write a description of WordsLength here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class WordsLength {
    public WordsLength(){
    }
    
    public void countWordLengths(FileResource resource, int [] counts){
        for(String word : resource.words()){
            int count = 0;
            for(int i = 0; i< word.length(); i++){
                if(Character.isLetter(word.charAt(i)) || (i != 0 && i != word.length() - 1)){
                    count ++;
                }   
            }
            
            if(count > 30){
                count = 30;
            }
            counts[count] ++;
        }
    }
    
    public int indexOfMax(int [] values){
     
        int maxValue = values[0];
        int ixMax = 0;
        for(int i = 1; i<values.length; i++){
            if(values[i] > maxValue){
                ixMax = i;
                maxValue = values[i];
            }
        }
       
        return ixMax;
    }
    
    
    public void tests(){
        FileResource resource = new FileResource();
        int [] counts = new int[30];
        
        countWordLengths(resource, counts);
        int i = 0;
        for (int count : counts){
            if(count > 0){
                System.out.println(count + " words of length " + i) ;
            }
            i++;
        }
        
        System.out.println("Index of most frequent " + indexOfMax(counts));
    }
}
