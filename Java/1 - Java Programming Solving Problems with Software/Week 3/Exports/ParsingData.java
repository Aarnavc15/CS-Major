
/**
 * Write a description of ParsingData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;

public class ParsingData {
    public ParsingData(){
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        System.out.println(countryInfo(parser, "Germany"));
        System.out.println();
        
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "gold", "diamonds");
        System.out.println();
        
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "gold"));
        System.out.println();
        
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999");
        
    }
    
    public String countryInfo(CSVParser parser, String country){
        for (CSVRecord record: parser){
            if(record.get("Country").contains(country)){
               return (record.get("Country") +  ": " +
               record.get("Exports") + ": " + 
               record.get("Value (dollars)"));
            }
        }
        
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String export1, String export2){
        
        for(CSVRecord record : parser){
         boolean cond1 = record.get("Exports").contains(export1);
         boolean cond2 = record.get("Exports").contains(export2); 
         
         if(cond1 && cond2){
             System.out.println(record.get("Country"));
         }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for(CSVRecord record : parser){
            if(record.get("Exports").contains(exportItem)){
                count += 1;
            }
        }
        
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record : parser){
            int lengthAmount = record.get("Value (dollars)").length();
            
            if(lengthAmount > amount.length()){
             System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));   
            }
        }
    }
}
