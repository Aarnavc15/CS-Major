import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for(int i = whichSlice; i <message.length(); i+= totalSlices){
            sb.append(message.charAt(i));
            
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cCrack = new CaesarCracker(mostCommon);
        for(int i = 0; i < klength; i++){
            String slice = sliceString(encrypted, i, klength);
            int keyz = cCrack.getKey(slice);
            key[i] = keyz;
        }
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dictionary = new HashSet<String>();
        
        for(String line : fr.lines()){
            dictionary.add(line.toLowerCase());
        }
        return dictionary;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        int words = 0;
        for(String word : message.split("\\W")){
            if(dictionary.contains(word.toLowerCase())){
                words ++;
            }
        }
        return words;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
     String letters = "abcdefghijklmnopqrstuvwxyz";
     int[] letterCount = new int[26];
     for(String word: dictionary){
         for(int i = 0; i<word.length(); i++){
             int index = letters.indexOf(word.charAt(i));
             if(index != -1){
                 letterCount[index] ++;
             }
         }
     }
     
     int mostFreq = 0;
     for(int j = 1; j< letterCount.length; j++){
        if(letterCount[mostFreq] < letterCount[j]){
            mostFreq = j;
        }
     }
     
     return letters.charAt(mostFreq);
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
       int maxKeyLength = 100;
       int maxWordsFound = 0;
       int keyLength = 1;
       char mostCom = mostCommonCharIn(dictionary);
       
       for(int i = 1; i<= maxKeyLength; i++){
           int [] keys = tryKeyLength(encrypted, i, mostCom);
           VigenereCipher vC = new VigenereCipher(keys);
           String decryptMessage = vC.decrypt(encrypted);
           int noWords = countWords(decryptMessage, dictionary);
           if(noWords > maxWordsFound){
               maxWordsFound = noWords;
               keyLength = i;
           }
       }
       System.out.println(keyLength);
       VigenereCipher vC = new VigenereCipher(tryKeyLength(encrypted, keyLength, 'e'));
       String message = vC.decrypt(encrypted);
       return message;
    }
    
    public String breakForAllLangs(String encr, HashMap<String, HashSet<String>> languages){
        
        int mostWords = 0;
        String message = "";
        
        for(String lang: languages.keySet()){
            HashSet<String> dict = languages.get(lang);
            String decrypt = breakForLanguage(encr, dict);
            int words = countWords(decrypt, dict);
            if(words > mostWords){
                mostWords = words;
                message = decrypt;
            }
        }
        return message;
    }
    
    public void breakVigenere () {
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        String message = new FileResource("VigenereTestData/athens_keyflute.txt").asString();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            HashSet<String> dictionary = readDictionary(new FileResource(f));
            String name = f.getName();
            languages.put(name, dictionary);
        }
        
        String decrypted = breakForAllLangs(message, languages);
        System.out.println(decrypted);
    }
    
    
    
    public void tester(){
        
        breakVigenere();
    }
    
}
