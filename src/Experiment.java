import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Experiment {

    private static void writeFile(String fileName, String txt){
        try {
            PrintWriter out = new PrintWriter (new FileWriter(fileName));
            out.println(txt);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Point[][] generateRandomPoints(){
        Point[][] pointsSets = new Point[100][];
        for(int i = 0; i < pointsSets.length; i++){
            pointsSets[i] = new Point[(i/10 + 1) * 1000];
            for(int j = 0; j < pointsSets[i].length; j++){
                Random r = new Random();
                double randomX = 9999 * r.nextDouble();
                double randomY = 9999 * r.nextDouble();
                pointsSets[i][j] = new Point(randomX, randomY);
            }
        }
        return pointsSets;
    }

    private static String run(Point[][] pointsSets){
        String s = "\t" + "BruteForce" + "\t" + "DivideEtImpera" + "\n";

        for(int i = 0; i < pointsSets.length; i++){
            long t0, t1;

            s += "" + pointsSets[i].length;

            t0 = System.nanoTime();
            ClosestPairOfPoints.bruteForce(pointsSets[i]);
            t1 = System.nanoTime();
            s += "\t" + (t1 - t0) / 1E6;

            t0 = System.nanoTime();
            ClosestPairOfPoints.calculateClosestPairOfPoints(pointsSets[i]);
            t1 = System.nanoTime();
            s += "\t" + (t1 - t0) / 1E6;

            s += "\n";
        }

        return s;
    }

    public static void main(String[] args) {
        String fs = File.separator;
        String outputDir = "output" + fs;
        Point[][] pointsSets = generateRandomPoints();

        // warm up
        String s = run(pointsSets);

        s = "";
        s = run(pointsSets);

        writeFile(outputDir + "exp.txt", s);
    }

}
