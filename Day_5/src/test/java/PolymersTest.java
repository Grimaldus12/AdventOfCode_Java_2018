
import org.junit.Test;
import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class PolymersTest {
    Polymers p = new Polymers();



    @Test
    public void checkUnitsTestFile() throws IOException {
        String file = "resources/Day_5_test";
        assertEquals(1, p.deletePolarities(p.readInput(file)));
    }

    @Test
    public void checkUnitsRealFile() throws IOException {
        String file = "resources/Day_5";
        assertEquals(9390, p.deletePolarities(p.readInput(file)));
    }

    @Test
    public void partTwoTestFile() throws IOException {
        String file = "resources/Day_5_test";
        assertEquals(0, p.improvedDeletion(p.readInput(file)));
    }

    @Test
    public void partTwoRealFile() throws IOException {
        String file = "resources/Day_5";
        assertEquals(5898, p.improvedDeletion(p.readInput(file)));
    }
}

