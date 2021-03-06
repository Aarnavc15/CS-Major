
/**
 * Write a description of GladLibJava here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import edu.duke.*;
import java.util.*;
public class GladLibJava {

    private HashMap<String, ArrayList<String>> tagList;
    private ArrayList<String> usedList;
    private ArrayList<String> usedTags;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibJava(){
        tagList = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibJava(String source){
        tagList = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String [] categories = {"adjective", "noun", "color", "country",
                                 "name", "animal", "timeframe", "verb","fruit"};
        for(String element: categories){
            ArrayList <String> hula = readIt(source + "/" + element + ".txt");
            tagList.put(element,hula);
        }
        usedList = new ArrayList<String>();
        usedTags = new ArrayList<String>();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        
        if (tagList.containsKey(label)){
            if(usedTags.indexOf(label) == -1){
                usedTags.add(label);
            }
            return randomFrom(tagList.get(label));
        }
      
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        int index = usedList.indexOf(sub);
        while (index != -1){
            sub = getSubstitute(w.substring(first+1,last));
            index = usedList.indexOf(sub);
        }
        
        usedList.add(sub);
        
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        usedList.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("The Words Considered were " + totalWordsInMap());
    }
    
    public int totalWordsInMap(){
        int totalWords = 0;
        
        for(String key: tagList.keySet()){
            totalWords += tagList.get(key).size();
        }
        
        return totalWords;
    }
    public int totalWordsConsidered(){
        int totalWords = 0;
        
        for(String key: usedList){
            totalWords += tagList.get(key).size();
        }
        
        return totalWords;
    }
}
