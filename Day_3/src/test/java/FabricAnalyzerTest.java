import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FabricAnalyzerTest {

    FabricAnalyzer f;

    @Test
    public void computeOverlapTestInput() throws IOException {
        f = new FabricAnalyzer("resources/test_Day_3");
        f.fillGrid();
        int overlappedCells = f.computeOverlap();
        assertEquals(4, overlappedCells);
    }

    @Test
    public void nonOverlappedCellsTestInput() throws IOException {
        f = new FabricAnalyzer("resources/test_Day_3");
        f.fillGrid();
        String id = f.getNonOverlapId();
        assertEquals("#3", id);
    }

    @Test
    public void computeOverlapPuzzleInput() throws IOException {
        f = new FabricAnalyzer("resources/Day_3");
        f.fillGrid();
        int overlappedCells = f.computeOverlap();
        assertEquals(119551, overlappedCells);
    }

    @Test
    public void nonOverlappedCellsPuzzleInput() throws IOException {
        f = new FabricAnalyzer("resources/Day_3");
        f.fillGrid();
        String id = f.getNonOverlapId();
        assertEquals("#1124", id);
    }
}
