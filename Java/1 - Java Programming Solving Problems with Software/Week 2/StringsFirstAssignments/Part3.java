
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public Part3(){
    }
    
    public boolean twoOccurrences (String stringa, String stringb){
       int index1 = stringb.indexOf(stringa);
       if (index1 == -1){
           return false;
        }
       
       int index2 = stringb.indexOf(stringa, index1+1);
       if (index2 == -1){
           return false;
        }
        
       return true;
    }
    
    public String lastPart(String stringa, String stringb){
        int index = stringb.indexOf(stringa);
        if(index == -1){
            return stringb;
        }
        
        int index2 = stringa.length();
        String output = stringb.substring(index2);
        return output;
    }
    
    public void testing(){
        System.out.println(lastPart("by", "by bye"));
        System.out.println(lastPart("be", "by bye"));
        System.out.println(lastPart("anda", "panda"));
        System.out.println(lastPart("a", "aaranv"));
        System.out.println(twoOccurrences("by", "by bye"));
        System.out.println(twoOccurrences("bye", "by bye"));
        System.out.println(twoOccurrences("anda", "panda"));
        System.out.println(twoOccurrences("a", "aaranv"));
    }
    
    public static void main(String[] args){
        Part3 p3 = new Part3();
        p3.testing();
    }
}
