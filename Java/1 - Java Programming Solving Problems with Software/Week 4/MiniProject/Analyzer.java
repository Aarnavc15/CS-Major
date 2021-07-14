
/**
 * Write a description of Analyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.*;
import edu.duke.*;
import org.apache.commons.csv.*;

public class Analyzer {

    public Analyzer(){
        
    }
    
    public void totalBirths (FileResource fr){
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        
        int totalNames = 0;
        int boyNames = 0;
        int girlNames = 0;
        
        for (CSVRecord record : fr.getCSVParser(false)){
            if(record.get(1).equals("F")){
                girlNames ++;
                totalGirls += Integer.parseInt(record.get(2));
            }
            
            else if (record.get(1).equals("M")){
                boyNames ++;
                totalBoys += Integer.parseInt(record.get(2));
            }
        }
        
        totalBirths = totalBoys + totalGirls;
        totalNames = boyNames + girlNames;
        
        System.out.println("Total Births: " + totalBirths + " Male Births: " + totalBoys + 
        " Female Births: " + totalGirls + " Total Names: " + totalNames + " Male Names: " 
        + boyNames + " Female Names: " + girlNames);
    }
    
    public void testTotalBirths(){
        FileResource fr = new FileResource("us_babynames_test/example-small.csv");
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender){
        int rank = 0;
        
        FileResource fr = new FileResource("us_babynames_test/yob" + year +"short.csv");
        
        for(CSVRecord record : fr.getCSVParser(false)){
            
            if (record.get(1).equals(gender)){
                rank += 1;
                
                if(record.get(2).equals(name)){
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public String getName(int year, int rank, String gender){
        int count = 0;
        
        FileResource fr = new FileResource("us_babynames_test/yob" + year +"short.csv");
        
        for(CSVRecord record : fr.getCSVParser(false)){
            
            if (record.get(1).equals(gender)){
                count += 1;
                
                if(count == rank){
                    return record.get(0);
                }
            }
        }
        return "NO NAME";
    }
    
    public void whatisNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name,gender);
        
        if (rank == -1){
            System.out.println(name + " not found in " + year);
        }
        
        else{
            String newName = getName(newYear, rank, gender);
            
            if(newName.equals("NO NAME")){
                System.out.println("No name at Rank "+ rank + " in "+ newYear);
            }
            
            else{
                System.out.println(name + " born in " + year + " would be" + newName 
                + " if they were born in " + newYear);
            }
        }
    }
    
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int highestRank = 0;
        int yearLargest = -1;
        
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            String fName = f.getName();
            int count = 0;
            String curName = "";
            for(CSVRecord record : fr.getCSVParser(false)){
                 
                curName = record.get(0);
                if (record.get(1).equals(gender));{
                    count ++;
                    if(curName.equals(name)){
                        break;
                    }
                }
            }
            if (count > highestRank && curName.equals(name)){
                 highestRank = count;
                 yearLargest = Integer.parseInt(fName.substring(fName.indexOf("yob") + 3,
                    fName.indexOf("yob") + 7));
                }
        }
        
        return yearLargest;
    }
    public int getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int highestRank = 0;
        int totalRank = 0;
        int noRanks = 0;
        int yearLargest = -1;
        
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            int count = 0;
            String curName = "";
            
            for(CSVRecord record : fr.getCSVParser(false)){
                 
                curName = record.get(0);
                if (record.get(1).equals(gender));{
                    count ++;
                    if(curName.equals(name)){
                        break;
                    }
                }
            }
            
            if (curName.equals(name)){
                totalRank += count;
                noRanks ++;
            }
        }
            
        if (totalRank == 0){
            return -1;
        }
        
        return (totalRank/noRanks);
    }
    
}
