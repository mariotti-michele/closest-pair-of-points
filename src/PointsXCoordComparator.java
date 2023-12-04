import java.util.Comparator;

public class PointsXCoordComparator implements Comparator<Point> {

    public int compare(Point p1, Point p2) {
        if(p1.getX() == p2.getX()){
            return Double.compare(p1.getY(), p2.getY());
        }
        else {
            return Double.compare(p1.getX(), p2.getX());
        }
    }

}
