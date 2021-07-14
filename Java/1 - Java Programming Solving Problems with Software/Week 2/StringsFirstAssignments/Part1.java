
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
        public Part1(){
        }
        public String findSimpleGene(String dna){
            String result = "";
            int start = dna.indexOf("ATG");
            if (start == -1){
                return "";
            }
            int stop = dna.indexOf("TAA");
            
            if ((stop - start)%3 == 0){
                return dna.substring(start,stop+3);
            }
            
            else{
                return "";
            }
        }
        
        public void testSimpleGene (){
            String dna1 = "ATCTGATAA";
            String dna2 = "ATGCTATCA";
            String dna3 = "ATCGTATCA";
            String dna4 = "AAATGCCCTAACTAGATTAAGAAACC";
            String dna5 = "ATGATAA";
            
            System.out.println(dna1);
            System.out.println("The String is " + findSimpleGene(dna1));
            System.out.println(dna2);
            System.out.println("The String is " + findSimpleGene(dna2));
            System.out.println(dna3);
            System.out.println("The String is " + findSimpleGene(dna3));
            System.out.println(dna4);
            System.out.println("The String is " + findSimpleGene(dna4));
            System.out.println(dna5);
            System.out.println("The String is " + findSimpleGene(dna5));
        }   
        
        public static void main (String [] args){
            Part1 p1 = new Part1();
            p1.testSimpleGene();
        }
}
