
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.*;
import edu.duke.*;

public class Part3 {
    public Part3(){
    }
    
    public int findStopCodon (String dna, int startIndex, String stopCodon){
        int stopIndex = dna.toUpperCase().indexOf(stopCodon, startIndex);
        if (stopIndex != -1 && ((stopIndex - startIndex)%3 == 0)){
            return stopIndex;
        }
        else {
            return dna.length();
        }
    }
    
    public void testStopCodon(){
        String dna1 = "xyzATGxyzTAA";
        int index1 = findStopCodon(dna1, 3, "TAA");
        System.out.println(index1);
    }
    
    
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG", where) ;
        int stopTAA = findStopCodon(dna, startIndex+3, "TAA");
        int stopTAG = findStopCodon(dna, startIndex+3, "TAG");
        int stopTGA = findStopCodon(dna, startIndex+3, "TGA");
        
        int tempIndex = Math.min(stopTAA,stopTAG);
        int geneIndex = Math.min(stopTGA, tempIndex);
        
        if (geneIndex == dna.length()){
            return "";
        }
        
        return dna.substring(startIndex,geneIndex+3);
      
    }
        
    public void testFindGene(){
        String str1 = "xxxxxxTGA";
        String str2 = "ATGxxxTAA";
        String str3 = "xxATGxxxTAAxxxTAG";
        String str4 = "xxxATGxxxTAT";
        
        System.out.println(str1);
        System.out.println(findGene(str1,0));
        System.out.println(str2);
        System.out.println(findGene(str2,0));
        System.out.println(str3);
        System.out.println(findGene(str3,0));
        System.out.println(str4);
        System.out.println(findGene(str4,0));
    }
    
    public void printAllGenes(String dna){
        int startIndex = 0;
        
        while (true){
            String gene = findGene(dna, startIndex);
            
            if (gene.isEmpty()){
                break;
            }
            
            System.out.println(gene);
            
            startIndex = startIndex + gene.length();
        }
    }
    
    public int countGenes(String dna){
        int startIndex = 0;
        int count = 0;
        
        while (true){
            String gene = findGene(dna, startIndex);
            
            if (gene.isEmpty()){
                break;
            }
            
            count += 1;
            
            startIndex = startIndex + gene.length();
        }
        
        return count;
        
    }
    
    public void testCountGenes(){
        String str1 = "ATGxxxTAAxATGxxxTAG";
        int int1 = countGenes(str1);
        System.out.println(str1);
        System.out.println(int1);
        
    }

    public int howMany(String stringa, String stringb){
        int startIndex = 0;
        int count = 0;
        
        while (true){
            startIndex = stringb.indexOf(stringa, startIndex);
            
            if (startIndex == -1){
                break;
            }
            
            count += 1;
            startIndex += stringa.length();
            
        }
        
        return count;
    }
    
    public static void main (String [] args){
        Part3 P3 = new Part3();
        String str1 = P3.findGene("AATGCTAACTAGCTGACTAAT",0);
        System.out.println(str1);
        
        int count1 = P3.howMany("TG", "CTGCCTGCATGATCGTA");
        System.out.println(count1);
    }
}
