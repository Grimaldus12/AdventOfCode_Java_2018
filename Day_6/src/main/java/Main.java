import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        RegionalMapping r = new RegionalMapping();
        System.out.println("Results Day_5:");
        r.computeRegions("resource/Day_5");

        System.out.println("\nResults Day_5_test");
        r.computeRegions("resource/Day_5_test");
    }
}
