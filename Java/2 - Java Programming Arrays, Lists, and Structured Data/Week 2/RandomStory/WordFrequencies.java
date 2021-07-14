
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.util.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
        
    }
    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        
        FileResource fr = new FileResource();
        for(String word : fr.words()){
            int index = myWords.indexOf(word.toLowerCase());
            if(index == -1){
                myWords.add(word.toLowerCase());
                myFreqs.add(1);
            }
            
            else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value+1);
            }
            
        }
    }
    
    public int indexOfMax(){
        int maxIx = 0;
        int maxFreq = myFreqs.get(0);
        
        for(int i = 1; i<myFreqs.size(); i++){
            if(myFreqs.get(i) > maxFreq){
                maxFreq = myFreqs.get(i);
                maxIx = i;
            }
        }
        
        return maxIx;
    }
    
    public void tester(){
        findUnique();
        int Index = indexOfMax();
        
        System.out.println("Number of Unique Words: " + myFreqs.size());
        for(int i = 0; i<myFreqs.size(); i++){
            System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        }
        
        System.out.println("The word that occurs most often and its count are: " +
                            myWords.get(Index) + " " + myFreqs.get(Index));
    }
}
