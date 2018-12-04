import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class balance {
    public static void main(String[] args) throws IOException {
        File file = new File("resources/input.txt");
        List<Integer> frequency= new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(file));

        String s;
        while((s = br.readLine()) != null) {
            frequency.add(Integer.parseInt(s));
        }

        //Part 1
        /*
          Method:
          Iterate over the input once and add each change to the frequency to the current frequency
         */
        int current_freq = 0;
        for (int f : frequency ) {
            current_freq += f;
        }
        System.out.println("Flag Part 1: " + current_freq);

        //Part 2
        /*
          Method:
          Save all reached Frequencies while iterating endlessly over the input data until we
          reach a duplicate
          (might take a few seconds :D )
         */
        int balance = 0;
        List<Integer> reachedFreq = new ArrayList<>();
        while(true) {
            for (int i : frequency) {
                balance += i;
                if (reachedFreq.contains(balance)) {
                    System.out.println("Flag Part 2: " + balance);
                    System.exit(0);
                }
                reachedFreq.add(balance);
            }
        }
    }
}