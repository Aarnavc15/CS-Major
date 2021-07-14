
/**
 * Write a description of Quiz1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import edu.duke.*;

public class Quiz1 {
    public Quiz1(){
    }
    
    public void findAbc(String input) {
    int index = input.indexOf("abc");
    while (true) {
        if (index == -1 || index >= input.length() - 3){
            break;
        }
        
        System.out.println((index+1) + ", " + (index+4));
        String found = input.substring(index+1, index+4);
        System.out.println(found);
        System.out.println("index " + index);
        
        index = input.indexOf("abc", index+3);
        
        System.out.println("index after updating " + index);
    }
}
   public void test() {
       //findAbc("abcd");
       //findAbc("abcdabc");
       //findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
       findAbc("abcabcabcabca");
}

public static void main (String [] args){
    Quiz1 q1 = new Quiz1();
    q1.test();
}
}
