
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class Part2 {
    public Part2(){
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
    
    public void testHowMany(){
        System.out.println(howMany("TAA", "TAAxxxTAA"));
    }
    
    public static void main (String [] args){
        Part2 P2 = new Part2();
        P2.testHowMany();
    }
}
