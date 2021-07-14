
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class Part4 {
    public Part4(){
    }
    
    public void ytfinder(){
        URLResource ur = new URLResource
        ("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        
        for (String word : ur.words()){
            int index = word.toLowerCase().indexOf("youtube.com");
            if (index != -1){
                int start = word.indexOf("\"");
                int stop = word.indexOf("\"", start +1);
                System.out.println(word.substring(start+1,stop));
              
            }
        }
    }
    
    public static void main (String [] args){
        Part4 p4 = new Part4();
        p4.ytfinder();
    }
}
