
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public Part2 (){
    }
    public String findSimpleGene(String dna, String startCodon,
    String stopCodon ){
            String result = dna.toUpperCase();
            int start = result.indexOf("ATG");
            if (start == -1){
                return "";
            }
            int stop = result.indexOf("TAA");
            
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
            String dna4 = "atgctataa";
            String dna5 = "ATGATAA";
            String start = "ATG";
            String stop = "TAA";
            
            System.out.println(dna1);
            System.out.println("The String is " + findSimpleGene(dna1,
            start, stop));
            System.out.println(dna2);
            System.out.println("The String is " + findSimpleGene(dna2,
            start, stop));
            System.out.println(dna3);
            System.out.println("The String is " + findSimpleGene(dna3, 
            start, stop));
            System.out.println(dna4);
            System.out.println("The String is " + findSimpleGene(dna4,
            start, stop));
            System.out.println(dna5);
            System.out.println("The String is " + findSimpleGene(dna5,
            start, stop));
        }  
        
        public static void main (String [] args){
            Part2 p2 = new Part2();
            p2.testSimpleGene();
        }
}
