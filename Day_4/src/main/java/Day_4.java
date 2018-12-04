import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class Day_4 {

    //Key = Guard_ID , Value = counts for each minute, how often the guard has slept during it
    private Map<Integer, int[]> guards = new HashMap<>();

    private List<String> input;

    void readInput(String file) throws IOException {
        input = Files.readAllLines(new File(file).toPath());
        //sorts Strings -> chronological sort
        Collections.sort(input);
        handleInput();
    }

    int computePartOne() {
        /*
          Method 1:
          1) Evaluate the guard with the most slept minutes
          2) Evaluate the minute he slept the most often
         */

        //1)
        int maxSum = 0;
        int maxId = 0;
        for (int id : guards.keySet()) {
            int sum = 0;
            int[] minutes = guards.get(id);
            for (int i : minutes) {
                if (i >= 1) sum += i;
            }
            if (sum > maxSum) {
                maxSum = sum;
                maxId = id;
            }
        }
        //2)
        int maxMinute = 0;
        int maxIndex = 0;
        int[] maxMinutes = guards.get(maxId);
        for (int i = 0; i < maxMinutes.length; i++) {
            if (maxMinutes[i] > maxMinute) {
                maxMinute = maxMinutes[i];
                maxIndex = i;
            }
        }
        System.out.println("\nGuard-ID: " + maxId);
        System.out.println("Best Minute: " + maxIndex);
        System.out.println("Flag Part 1: " + maxId * maxIndex);
        return maxId*maxIndex;
    }

    int computePartTwo() {
        /*
          Method 2:
          1) Evaluate the guard who slept the most often during one minute and find that minute
         */
        int bestMinuteIndex = 0;
        int bestMinuteFrequency = 0;
        int bestMinuteId = 0;
        for (int id : guards.keySet()) {
            int[]minutes = guards.get(id);
            for(int i = 0; i<minutes.length; i++) {
                if(minutes[i] > bestMinuteFrequency) {
                    bestMinuteFrequency = minutes[i];
                    bestMinuteIndex = i;
                    bestMinuteId = id;
                }
            }
        }

        System.out.println("\nGuard ID: " + bestMinuteId);
        System.out.println("Best Minute: " + bestMinuteIndex);
        System.out.println("Flag Part 2: " + bestMinuteId*bestMinuteIndex);
        return bestMinuteId*bestMinuteIndex;
    }

    private void handleInput() {
        /*
          Because the values are ordered, we can process each day chronologically
          1) Guard checks in
          2) Guard falls asleep
          3) Guard wakes up
          4) 2/3) repeating variable times
        */
        int startSleeping = 0;
        int current_guard = 0;
        for (String in : input) {
            //Split input in 3 cases
            boolean guardShift = in.contains("Guard");
            boolean fallenAsleep = in.contains("falls");
            boolean wakenUp = in.contains("wakes");

            if (guardShift) {
                //get the ID, if it's the first time the guard is showing up, add him to the List
                int id = Integer.parseInt(in.substring(26).split(" ")[0]);
                current_guard = id;
                if (!guards.containsKey(id)) {
                    guards.put(id, new int[60]);
                }
            }
            //get minute Value
            int minuteValue = Integer.parseInt(in.substring(15, 17));

            //remember the starting minute of the sleeping time
            if (fallenAsleep) {
                startSleeping = minuteValue;
            }

            //increment the frequency in the sleeping interval by one
            if (wakenUp) {
                for (int i = startSleeping; i < minuteValue; i++) {
                    guards.get(current_guard)[i] += 1;
                }
            }
        }
    }
}