
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.util.*;

public class CharactersInPlay {
    private ArrayList<String> charNames;
    private ArrayList<Integer> nameCounts;
    
    public CharactersInPlay(){
        charNames = new ArrayList<String>();
        nameCounts = new ArrayList<Integer>();
    }
    
    
    public void update(String person){
        int index = charNames.indexOf(person);
        if(index == -1){
            charNames.add(person);
            nameCounts.add(1);
        }
        
        else{
            int value = nameCounts.get(index);
            nameCounts.set(index, value + 1);
        }
    }
    
    public void findAllCharacters(){
        charNames.clear();
        nameCounts.clear();
        FileResource fr = new FileResource();
        for(String line : fr.lines()){
            int index = line.indexOf(".");
            if(index != -1){
                String name = line.substring(0,index);
                update(name);   
            }
        }
    }
    
    public void CharacterWithNumParts(int num1, int num2){
        for(int i = 0; i<nameCounts.size(); i++){
            int freq = nameCounts.get(i);
            if(freq>= num1 && freq<= num2){
                System.out.println(charNames.get(i) + " " + freq);
            }
        }
    }

    public void tester(){
        findAllCharacters();
        for(int i = 0; i<nameCounts.size(); i++){
            int freq = nameCounts.get(i);
            System.out.println(charNames.get(i) + " " + freq);
        }
        
        CharacterWithNumParts(2,4);
    }
    
}
