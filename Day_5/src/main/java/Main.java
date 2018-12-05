import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String file = "resources/Day_5";
        Polymers p = new Polymers();
        System.out.println("Part 1:");
        System.out.println("Length of the Polymer chain: " + p.deletePolarities(p.readInput(file)));

        System.out.println("Part 2:");
        p.improvedDeletion(p.readInput(file));
    }
}
