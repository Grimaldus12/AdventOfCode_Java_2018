import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

class Polymers {

    //read input and return a List of all units
    List<Character> readInput(String file) throws IOException {
        List<String> input = Files.readAllLines(new File(file).toPath());
        List<Character> units = new ArrayList<>();
        for (String i : input) {
            for (char c : i.toCharArray()) {
                units.add(c);
            }
        }
        return units;
    }

    //Part 1
    int deletePolarities(List<Character> units){
        int index = 0;
        //Iterate once over all units
        while (index < units.size()-1) {
            char c = units.get(index);
            /*
              If the unit matches the next one in the list remove them both
              "or" specifies the ascii value of the match, doesn't matter which one is in
              Upper-/Lower-Case, just checks if they match the same letter in the Alphabet,
              with different cases
            */
            if((c == units.get(index+1)+32) || (c == units.get(index+1)-32)) {
                units.remove(index+1);
                units.remove(index);
                //modify the index so that it matches the last not removed entry
                if(index >= 1) index = index-1;
                else index = 0;
            } else {
                index++;
            }
        }
        return  units.size();
    }

    //Part 2
    int improvedDeletion(List<Character> units) {
        List<Character> withDeletedUnit;
        //Init an alphabet
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        char bestChar = 'a';
        //worst length is the input itself
        int bestLength = units.size();
        //delete each char in the alphabet once
        //get the length of the Sequence with the algorithm from part one
        for (char c : alphabet) {
            //init the List newly each iteration, else all chars will be deleted
            withDeletedUnit = new ArrayList<>(units);
            List<Character> toBeRemoved = new ArrayList<>();
            toBeRemoved.add(c);
            //also delete the opposing case (here all chars in alphabet are lowerCase)
            toBeRemoved.add((char) (c-32));
            withDeletedUnit.removeAll(toBeRemoved);
            int length;
            if((length = deletePolarities(withDeletedUnit)) < bestLength) {
                bestLength = length;
                bestChar = c;
            }
        }
        System.out.println("Best Length: " + bestLength);
        System.out.println("Character deleted : " + bestChar + "/" + (char) (bestChar-32));
        return bestLength;
    }
}
