import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class EdgeGenerator {

    public static void main(String[]args){

        // function: write random pairs to txt file for testing purpose
        // usage: java EdgeGenerator.java  #MaxInt_of_nodes #of_pairs out.txt

        // write pairs of number to test2 file
        // random generating number of pairs
        // write each pair to file test2.txt
        // limit number of pairs created

        int maxInt = Integer.parseInt(args[0]);
        int numPairs = Integer.parseInt(args[1]);
        String fileName = args[2];
        ArrayList pairs = createPair(maxInt,numPairs);
        write2File(fileName, pairs);


    }

    public static ArrayList<String> createPair(int max, int pair){

        Random rn = new Random();
        ArrayList<String> as = new ArrayList<>();
        while(pair>0){
            int a = rn.nextInt(max);
            int b = rn.nextInt(max);

            // ensuring no duplicate edge created
            if (a!=b && !as.contains(a+", "+b) && !as.contains(b+", "+a)){
                as.add(a+", "+b);
                pair--;
            }
        }

        return as;
    }

    public static void write2File(String fileName, ArrayList<String>as) {
        File fout = new File(fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fout);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        try {
            for (String e : as) {
                bw.write(e);
                bw.newLine();
            }
            bw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
