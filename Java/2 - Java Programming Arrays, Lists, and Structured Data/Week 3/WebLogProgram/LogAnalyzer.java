
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry> ();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for(String line: fr.lines()){
             LogEntry le = WebLogParser.parseEntry(line);
             records.add(le);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for(LogEntry le : records){
             String ipAdd = le.getIpAddress();
             if(! uniqueIPs.contains(ipAdd)){
                 uniqueIPs.add(ipAdd);
             }
         }
         
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num){
         for(LogEntry le : records){
             if(le.getStatusCode() > num){
                 System.out.println(le);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for(LogEntry le : records){
             String ipAdd = le.getIpAddress();
             String date = le.getAccessTime().toString();
             if(! uniqueIPs.contains(ipAdd) && date.indexOf(someday) != -1){
                 uniqueIPs.add(ipAdd);
             }
         }
         
         return uniqueIPs;
     }
     
     public int countUniqueIPsInRange (int start, int stop){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for(LogEntry le : records){
             String ipAdd = le.getIpAddress();
             int statCode = le.getStatusCode();
             if(! uniqueIPs.contains(ipAdd) && (statCode >= start && statCode <= stop)){
                 uniqueIPs.add(ipAdd);
             }
         }
         
         return uniqueIPs.size();
     }
     
     public HashMap<String, Integer> countVisitsPerIP(){
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         
         for(LogEntry le : records){
             String ipAdd = le.getIpAddress();
             if(counts.containsKey(ipAdd)){
                 counts.put(ipAdd, counts.get(ipAdd) + 1);
             }
             
             else{
                 counts.put(ipAdd, 1);
             }
         }
         
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
        int max = 0;
     
        for(String key : counts.keySet()){
            int visits = counts.get(key);
             if(visits > max){
             max = visits;
            }
        }
        return max;       
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts){
        ArrayList<String> iPs = new ArrayList<String>();
        int maxVis =  mostNumberVisitsByIP(counts);
        for(String key : counts.keySet()){
            int visits = counts.get(key);
            if(visits == maxVis){
                iPs.add(key);
            }
        }
        
        return iPs;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays(){
     
         HashMap<String, ArrayList<String>> yeets = new HashMap<String, ArrayList<String>>();
         
         for(LogEntry le : records){
             String ipAdd = le.getIpAddress();
             String day = le.getAccessTime().toString().substring(4,10);
             if(yeets.containsKey(day)){
                 ArrayList newArr = yeets.get(day);
                 newArr.add(ipAdd);
                 yeets.put(day, newArr);
             }
             
             else{
                 ArrayList<String> newArr = new ArrayList<String>();
                 newArr.add(ipAdd);
                 yeets.put(day, newArr);
             }
         }
         
         return yeets;
     }
     
     public int dayWithMostIPVisits(HashMap<String, ArrayList<String>> counts){
         int max = 0;
         
         for(String key : counts.keySet()){
            int visits = counts.get(key).size();
             if(visits > max){
             max = visits;
            }
        } 
         
        return max;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> counts, String day){
         ArrayList<String> visits = counts.get(day);
         HashMap<String, Integer> uniqueVis = new HashMap<String, Integer>();
         int max = 0;
     
        for(String ip : visits){
           if(uniqueVis.containsKey(ip)){
               uniqueVis.put(ip, uniqueVis.get(ip) +1);
           }
           
           else{
               uniqueVis.put(ip,1);
           }
        }
        return iPsMostVisits(uniqueVis);
     } 
     
}
