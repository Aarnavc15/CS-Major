import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int count = 0;
        for (Point currPt : s.getPoints()){
            count = count + 1;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
         double avg = getPerimeter(s)/getNumPoints(s);
         return avg;
    }
    
    public double getLargestSide(Shape s) {
        Point prevPt = s.getLastPoint();
        double largestDist = 0;
        for (Point currPt : s.getPoints()){
            double currDist = prevPt.distance(currPt);
            if (largestDist < currDist){
                largestDist = currDist;
            }
            prevPt = currPt;
        }
        return largestDist;
    }

    public double getLargestX(Shape s) {
        double BigX = 0;
        for (Point currPt : s.getPoints()){
            if (BigX < currPt.getX()){
                BigX = currPt.getX();
            }
        }
        return BigX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double largePerimeter = 0; 
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            if (perimeter > largePerimeter){
                largePerimeter = perimeter;
            }
        }
        return largePerimeter;
    }
    
    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        double largePerimeter = 0;
        File largePFile = new File("");
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            if (perimeter > largePerimeter){
                largePerimeter = perimeter;
                largePFile = f;
            }
        }
        return largePFile.getName();
    }
    
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int numPoints = getNumPoints(s);
        double avgLen = getAverageLength(s);
        double largeSide = getLargestSide(s);
        double largeX =getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("Number of Points = " + numPoints);
        System.out.println("Average Length = " + avgLen);
        System.out.println("Largest Side = " + largeSide);
        System.out.println("Largest x = " + largeX);
    }
    
    public void testPerimeterMultipleFiles() {
        double Perim = getLargestPerimeterMultipleFiles();
        System.out.println("Largest Perimeter = " + Perim);
    }

    public void testFileWithLargestPerimeter() {
        String fLarge = getFileWithLargestPerimeter();
        System.out.println("Largest File = " + fLarge);
    }
    
    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
       Shape triangle = new Shape();
       triangle.addPoint(new Point(0,0));
       triangle.addPoint(new Point(6,0));
       triangle.addPoint(new Point(3,6));
       for (Point p : triangle.getPoints()){
           System.out.println(p);
       }
       double peri = getPerimeter(triangle);
       System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
