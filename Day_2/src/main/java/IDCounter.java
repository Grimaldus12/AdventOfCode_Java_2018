import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IDCounter {
    private static File file = new File("resources/input.txt");
    private static List<ID> idList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        insertID();

        int twoRepeats = 0;
        int threeRepeats = 0;

        for (ID id : idList) {
            char[] s = id.getId().toCharArray();
            Map<Character, Integer> frequency = new HashMap<>();
            boolean twoRep = false;
            boolean threeRep = false;
            //Count frequency of char through mapping them with their frequency
            for (char c : s) {
                if (!frequency.containsKey(c)) frequency.put(c, 1);
                else frequency.put(c, frequency.get(c) + 1);
            }
            //Count the frequencies and elaborate if there are chars with two or three repetitions
            for(char c : frequency.keySet()) {
                if(frequency.get(c) == 2) twoRep = true;
                else if(frequency.get(c) == 3) threeRep = true;
            }
            //Increment 2/3 Repeats Counter if the ID counts for the Repeats
            if(twoRep)twoRepeats+=1;
            if(threeRep)threeRepeats+=1;
        }
        System.out.println("Falg Part 1: " + threeRepeats*twoRepeats);

        //Part 2
        /*
          Method:
          Compare each id to all other ID and look, if they got at maximum one differing char
          Add those ID's to a new List
         */
        List<ID> similarId = new ArrayList<>();
        for (ID id : idList) {
            for (ID secondId : idList) {
                if(id.equals(secondId) && !(similarId.contains(id))) continue;
                int differ = id.calculateDifference(secondId);
                if(differ <= 1) {
                    similarId.add(id);
                    similarId.add(secondId);
                }
            }
        }

        //in this task, all ID's in the resulting list are differing by 1 char from all others
        //get the differing index and cut it out
        int differingIndex = similarId.get(0).calculateDifferingIndex(similarId.get(1));
        String id = similarId.get(0).getId().substring(0, differingIndex) + similarId.get(0).getId().substring(differingIndex+1);

        System.out.println("Flag Part 2: " + id);
    }

    //read input from file
    private static void insertID() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        while((s = br.readLine()) != null) {
            ID id = new ID(s);
            idList.add(id);
        }
    }
}
