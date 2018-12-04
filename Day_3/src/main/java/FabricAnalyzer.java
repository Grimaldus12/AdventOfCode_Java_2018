import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class FabricAnalyzer {

    private File file;
    //Fabric-Grid
    private int[][] squareInch = new int[1000][1000];
    private List<String[]> idCollection = new ArrayList<>();

    FabricAnalyzer(String file)  {
        this.file = new File(file);
    }

    private String[] processLine(String s) {
        //Regex Processing of the input String, additionally cut out whitespaces
        String []data = s.split("[@,:x]",0);
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].replace(" ", "");
        }
        return data;
    }

    //read all the input
    private List<String[]> processFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        while ((s = br.readLine()) != null) idCollection.add(processLine(s));
        return idCollection;
    }

    //Method: for each claim increment each square inch by one
    void fillGrid() throws IOException {
        for (String[] s : processFile()) {
            for (int i = Integer.parseInt(s[2]); i < (Integer.parseInt(s[4]) + Integer.parseInt(s[2])); i++) {
                for (int j = Integer.parseInt(s[1]); j < (Integer.parseInt(s[3]) + Integer.parseInt(s[1])); j++) {
                    squareInch[i][j] += 1;
                }
            }
        }
    }

    //get all square Inches contained in more or equal 2 claims
    int computeOverlap() {
        int countOverlap = 0;
        for (int[] squareInch1 : squareInch) {
            for (int j = 0; j < squareInch[0].length; j++) {
                if (squareInch1[j] > 1) countOverlap++;
            }
        }
        return countOverlap;
    }

    /*
      Method:
        Analyze each claim again, after all claims are initialized through the fillGrid() Method
        If all entries got the value of 1 in the grid, then the claim doesn't overlap
     */
    String getNonOverlapId() {
        for (String[] s : idCollection) {
            boolean same = true;
            for (int i = Integer.parseInt(s[2]); i < (Integer.parseInt(s[4]) + Integer.parseInt(s[2])); i++) {
                for (int j = Integer.parseInt(s[1]); j < (Integer.parseInt(s[3]) + Integer.parseInt(s[1])); j++) {
                    if(squareInch[i][j] != 1) same = false;
                }
            }
            if(same) return s[0];
        } return "";
    }
}
