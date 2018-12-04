import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

public class Day_4_test {

    Day_4 day4 = new Day_4();

    @Test
    public void testFileMethodOne() throws IOException {
        day4.readInput("resources/Day_4_test");
        int result = day4.computePartOne();

        assertEquals(34116, result);
    }

    @Test
    public void testFileMethodTwo() throws IOException {
        day4.readInput("resources/Day_4_test");
        int result = day4.computePartTwo();

        assertEquals(34116, result);
    }

    @Test
    public void puzzleFileMethodOne() throws IOException {
        day4.readInput("resources/Day_4");
        int result = day4.computePartOne();

        assertEquals(30630, result);
    }

    @Test
    public void puzzleFileMethodTwo() throws IOException {
        day4.readInput("resources/Day_4");
        int result = day4.computePartTwo();

        assertEquals(136571, result);
    }
}
