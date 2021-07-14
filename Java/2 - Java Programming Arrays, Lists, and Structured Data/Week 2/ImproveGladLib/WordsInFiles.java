
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.util.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> fileNames;
    
    public WordsInFiles(){
        fileNames = new HashMap<String, ArrayList<String>> ();
    }
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        
        for(String word : fr.words()){
            if (fileNames.containsKey(word)){
                ArrayList <String> list = fileNames.get(word);
                list.add(f.getName());
                fileNames.put(word,list);
            }
            else{
                ArrayList <String> list = new ArrayList<String>();
                list.add(f.getName());
                fileNames.put(word,list);
            }
        }
    }
    
    public void buildWordFileMap(){
        fileNames.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        int maxNum = 0;
        
        for(String key: fileNames.keySet()){
            int size = fileNames.get(key).size();
            if(size>maxNum){
                maxNum = size;
            }
        }
        
        return maxNum;
    }
    
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> words = new ArrayList<String>();
        
        for(String key: fileNames.keySet()){
            int size = fileNames.get(key).size();
            if(size==number){
                words.add(key);
            }
        }
        
        return words;
        
    }
    
    public void printFilesIn(String word){
        ArrayList<String> files = fileNames.get(word);
        for(String file: files){
            System.out.println(file);
        }
    }
    
    public void tester(){
        buildWordFileMap();
        int maxNum = maxNumber();
        System.out.println("Maximum Number " + maxNum);
        ArrayList<String> words = wordsInNumFiles(maxNum);
        for(String word: words){
            System.out.println(word);
            printFilesIn(word);
        }
    }
}
