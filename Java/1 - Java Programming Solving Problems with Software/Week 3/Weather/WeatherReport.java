
/**
 * Write a description of WeatherReport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.*;
import edu.duke.*;
import org.apache.commons.csv.*;

public class WeatherReport {

    public WeatherReport(){
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser){
        
        CSVRecord currentColdest = null;
        
        for(CSVRecord record : parser){
            
            if (currentColdest == null){
                currentColdest = record;
            }
            
            
            else{
                double tempColdest = Double.parseDouble(currentColdest.get("TemperatureF"));
                double tempRecord = Double.parseDouble(record.get("TemperatureF"));
                
                if (tempRecord < tempColdest || tempColdest == -9999.0){
                    currentColdest = record;
                }
            }  
        }
        
        return currentColdest;
    }
    
    public void testColdestHourInFile(){
     
        FileResource fr = new FileResource();
        
        CSVRecord coldDay = coldestHourInFile(fr.getCSVParser());
        System.out.println("The Coldest Temperature on " +coldDay.get("DateUTC") + " was " + coldDay.get("TemperatureF") + " at time " + coldDay.get("TimeEST"));
        
    }
    
    public String fileWithColdestTemperature(){
        
        DirectoryResource dr = new DirectoryResource();
        File coldestDay = null;
        CSVRecord coldestRecord = null;
        
        for(File file : dr.selectedFiles()){
            FileResource record = new FileResource(file);
            if (coldestDay == null){
                coldestDay = file;
                coldestRecord = coldestHourInFile(record.getCSVParser());
            }
            
            double lowestTemp = Double.parseDouble(coldestRecord.get("TemperatureF"));
            double recordTemp = Double.parseDouble(coldestHourInFile(record.getCSVParser()).get("TemperatureF"));
            
            if (recordTemp<lowestTemp){
                coldestDay= file;
                coldestRecord = coldestHourInFile(record.getCSVParser());
            }
        }
        
        return (coldestDay.getName());
    }
    
    public void testFileWithColdestTemperature(){
        String fileName = fileWithColdestTemperature();
        
        System.out.println("The Coldest Day was: " + fileName);
        FileResource fr = new FileResource();
        CSVRecord coldestRecord = coldestHourInFile(fr.getCSVParser());
        
        System.out.println("The Coldest Temperature on the day was: "+ coldestRecord.get("TemperatureF"));
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        
        CSVRecord currentColdest = null;
        
        for(CSVRecord record : parser){
            
            if (currentColdest == null){
                currentColdest = record;
            }
            
            
            else if (currentColdest.get("Humidity") != "N/A" && record.get("Humidity") != "N/A"){
                double tempColdest = Double.parseDouble(currentColdest.get("Humidity"));
                double tempRecord = Double.parseDouble(record.get("Humidity"));
                
                if (tempRecord < tempColdest){
                    currentColdest = record;
                }
            }  
        }
        
        return currentColdest;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("TimeEST"));
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        
        DirectoryResource dr = new DirectoryResource();
        File coldestDay = null;
        CSVRecord coldestRecord = null;
        
        for(File file : dr.selectedFiles()){
            FileResource record = new FileResource(file);
            if (coldestDay == null){
                coldestDay = file;
                coldestRecord = lowestHumidityInFile(record.getCSVParser());
            }
            
            double lowestTemp = Double.parseDouble(coldestRecord.get("Humidity"));
            double recordTemp = Double.parseDouble(lowestHumidityInFile(record.getCSVParser()).get("Humidity"));
            
            if (recordTemp<lowestTemp){
                coldestDay= file;
                coldestRecord = coldestHourInFile(record.getCSVParser());
            }
        }
        
        return (coldestRecord);
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord humidRecord =  lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+ humidRecord.get("Humidity") + " at " + humidRecord.get("DateUTC") + humidRecord.get("TimeEST"));
    }
    
    public double averageTemperatureInFile (CSVParser parser){
        double sum = 0.0;
        int count = 0;
        
        for(CSVRecord record : parser){
            double temp = Double.parseDouble(record.get("TemperatureF"));
            if (temp != 9999.0){
            sum += Double.parseDouble(record.get("TemperatureF"));
            count ++;   
            }
        }
        
        return (sum/count);
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        double avgTemp = averageTemperatureInFile(fr.getCSVParser());
        
        System.out.println("The Average Temperature is " + avgTemp);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double tempSum = 0.0;
        int count = 0;
        
        for(CSVRecord record : parser){
         
            if (record.get("Humidity") != "N/A"){
                double temp = Double.parseDouble(record.get("TemperatureF"));
                if(Integer.parseInt(record.get("Humidity")) >= value && temp != 9999.0){
                    tempSum += temp;
                    count ++;
                }
            }
        }
        
        if (count == 0){
            return tempSum;
        }
        
        else{
            return (tempSum/count);
        }
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        int value = 80;
        
        double Average = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), value);
        
        if (Average == 0.0){
            System.out.println("No temperatures with that humidity");
        }
        
        else {
            System.out.println("Average Temp when high Humidity is " + Average);
        }
    }
}
