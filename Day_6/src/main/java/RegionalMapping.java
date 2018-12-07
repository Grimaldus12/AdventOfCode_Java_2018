import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RegionalMapping {

    /*
        Method to compute the regions:
        Fill a grid, the max x and y values are equivalent to the biggest values appearing
        in the points input (For the array obviously "+1").
        Then we compute our regions by mapping the the nearest input-Point for each
        point in the grid. In the regions map we got all of our input-points mapped with
        the amount of grid-points being closest.
        To eliminate the indefinite regions we just delete all of those, containing
        a boundary point
     */



    private Map<Integer, Point> initPoints(String file) throws IOException {
        List<String> input = Files.readAllLines(new File(file).toPath());
        Map<Integer, Point> points = new HashMap<>();
        int idx = 0;
        for (String s : input) {
            String[] data = s.trim().split(", ");
            points.put(idx, new Point(Integer.parseInt(data[0]), Integer.parseInt(data[1])));
            idx++;
        }
        return points;
    }

    private int[] getMaxCoords(Map<Integer, Point> points) {
        int maxX = 0;
        int maxY = 0;
        for (int key : points.keySet()) {
            if(points.get(key).x > maxX) maxX = points.get(key).x;
            if(points.get(key).y > maxY) maxY = points.get(key).y;
        }
        return new int[] {maxX, maxY};
    }

    void computeRegions (String file) throws IOException {
        Map<Integer, Point> points = initPoints(file);
        int[] maxCoords = getMaxCoords(points);
        Map<Integer, Integer> region = new HashMap<>();

        //init the grid and Mapping for regions
        int[][] grid = new int[maxCoords[0]+1][maxCoords[1]+1];
        int y = maxCoords[0];
        int x = maxCoords[1];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int nearestPoint = -1;
                int bestDistance = y + x;

                for (int pointIndex : points.keySet()) {
                    int dist = Math.abs(points.get(pointIndex).x - j) + Math.abs(points.get(pointIndex).y - i);
                    if(dist < bestDistance) {
                        nearestPoint = pointIndex;
                        bestDistance = dist;
                    } else if(dist == bestDistance) {
                        nearestPoint = -1;
                    }
                }
                grid[i][j] = nearestPoint;
                if (region.get(nearestPoint) != null) region.put(nearestPoint, region.get(nearestPoint) + 1);
                else region.put(nearestPoint, 1);
            }
        }

        //remove infinite regions left and right boundary
        for(int i = 0; i < y; i++) {
            int xBoundary = grid[i][0];
            region.remove(xBoundary);
            xBoundary = grid[i][x-1];
            region.remove(xBoundary);
        }

        //remove infinite regions upper and lower boundary
        for (int j = 0; j < x; j++) {
            int yBoundary = grid[0][j];
            region.remove(yBoundary);
            yBoundary = grid[y-1][j];
            region.remove(yBoundary);
        }

        //get the biggest region
        int biggestRegion = 0;
        int pointOfRegion = 0;
        for (int point : region.keySet()) {
            if (biggestRegion < region.get(point)) {
                biggestRegion = region.get(point);
                pointOfRegion = point;
            }
        }

        System.out.println("Biggest Region: " + (pointOfRegion+1) );
        System.out.println("Points in this region: " + region.get(pointOfRegion));

        //Part 2
        int pointsInRegion = 0;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                int totalDist = 0;

                for (int pointIndex : points.keySet()) {
                    Point p = points.get(pointIndex);
                    int dist = Math.abs(p.x - j) + Math.abs(p.y - i);
                    totalDist += dist;
                }
                if(totalDist < 10000) pointsInRegion++;
            }
        }
        System.out.println("\nPoints with Manhattan Distance less than 10000: " + pointsInRegion);
    }


}
