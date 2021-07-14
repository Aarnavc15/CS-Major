
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.util.*;

public class CodonCount {
    private HashMap<String, Integer> dnaMap;
    
    public CodonCount(){
        dnaMap = new HashMap<String, Integer>();
        
    }
    
    public void buildCodonMap(int start, String dna){
        dnaMap.clear();
        for(int search = start; search<dna.length()-3; search +=3){
            String codon = dna.substring(search, search+3);
            if(dnaMap.containsKey(codon)){
                dnaMap.put(codon, dnaMap.get(codon) + 1);
            }
            
            else{
                dnaMap.put(codon, 1);
            }
        }
    }
    
    public String getMostCommonCodon(){
        String mostCommon = "";
        
        for(String key : dnaMap.keySet()){
            if(mostCommon.equals("")){
                mostCommon = key;
            }
            
            if(dnaMap.get(key) > dnaMap.get(mostCommon)){
                mostCommon = key;
            }
        }
        
        return mostCommon;
    }
    
    public void printCodonCounts(int start, int stop){
     
        for(String codon:dnaMap.keySet()){
         int freq = dnaMap.get(codon);
         if(freq >= start && freq<= stop){
             System.out.println(codon + " " + freq);
         }
        }
    }
    public void tester(){
        String dna = (new FileResource()).asString().toUpperCase();
        for(int frame = 0 ; frame<=2; frame++){
            buildCodonMap(frame, dna);
            String mostFreq = getMostCommonCodon();
            int size = dnaMap.size();
            
            System.out.println("Reading frame starting with " + frame + 
                                " results in " + size + " unique codons");
            System.out.println(" and the most common codon is " + mostFreq +" with count " +
                                dnaMap.get(mostFreq));
            System.out.println("Counts of codons between 1 and 5 inclusive are:");
            
            printCodonCounts(1,5);
        }
    }
}
