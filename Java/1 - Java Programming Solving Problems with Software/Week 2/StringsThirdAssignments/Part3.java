
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
    
    
    public StorageResource getAllGenes(String dna){
        int startIndex = 0;
        
        StorageResource geneList = new StorageResource();
        
        while (true){
            String gene = findGene(dna, startIndex);
            
            if (gene.isEmpty()){
                break;
            }
            
            geneList.add(gene);
            
            startIndex = startIndex + gene.length();
        }
        
        return geneList;
    }
    
    public float cgRatio(String dna){
        float countC = 0;
        
        for(char s : dna.toCharArray()){
            
            if(s == 'C' || s == 'G'){
                countC += 1;
            }
        }
        
        return (countC/dna.length());
    }
    
    public void processGenes(StorageResource sr){
        int lenCount = 0;
        int ratioCount = 0;
        String longestStr = "";
        
        for(String dna : sr.data()){
            if(dna.length() > 9){
                System.out.println(dna + " is longer than 9 characters");
                lenCount += 0;
            }
            
            if(cgRatio(dna) > 0.35){
                System.out.println(dna + " has a cgRatio greater than 0.36");
                ratioCount+= 1;
            }
            
            if(dna.length() > longestStr.length()){
                longestStr = dna;
            }
        }
        
        System.out.println(lenCount + "dna strands are longer than 9");
        System.out.println(ratioCount + "dna strands have a cg Ratio greater than 0.36");
        System.out.println("Longest dna strand is " + longestStr);
    }
}
