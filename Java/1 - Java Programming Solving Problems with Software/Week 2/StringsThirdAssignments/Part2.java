
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import edu.duke.*;

public class Part2 {
    public Part2(){
    }
    
    public float cgRatio(String dna){
        float countC = 0;
        int countG = 0;
        
        for(char s : dna.toCharArray()){
            
            if(s == 'C'){
                countC += 1;
            }
            
            else if(s == 'G'){
                countG += 1;
            } 
        }
        
        if (countG == 0){
            return 0;
        }
        
        return (countC/countG);
    }
    
    public int countCTG(String dna){
        int countGene = 0;
        int searchIndex = 0;
        
        while (true){
         int geneIndex = dna.indexOf("CTG", searchIndex);
         
         if(geneIndex == -1){
             break;
         }
         
         countGene += 1;
         searchIndex = geneIndex+3;
         
        }
        
        return countGene;
    }
}
