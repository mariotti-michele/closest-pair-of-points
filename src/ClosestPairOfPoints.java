import java.util.Arrays;
import java.util.ArrayList;

public class ClosestPairOfPoints {

    private static double calculateDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    protected static CPOPResult bruteForce(Point[] p) {
        CPOPResult result = new CPOPResult();
        for (int i = 0; i < p.length; i++) {
            for (int j = i + 1; j < p.length; j++) {
                double dK = calculateDistance(p[i], p[j]);
                if (dK < result.getDistance()) {
                    result.setDistance(dK);
                    result.setPoints(p[i], p[j]);
                }
            }
        }
        return result;
    }

    protected static CPOPResult calculateClosestPairOfPoints(Point[] q) {
        Point[] pX = Arrays.copyOf(q, q.length);
        Arrays.sort(pX, new PointsXCoordComparator());
        Point[] pY = Arrays.copyOf(q, q.length);
        Arrays.sort(pY, (p1, p2) -> (int) (p1.getY() - p2.getY()));
        CPOPResult result = new CPOPResult();
        return calculateClosestPairOfPointsRic(pX, pY, result);
    }

    private static CPOPResult calculateClosestPairOfPointsRic(Point[] pX, Point[] pY, CPOPResult result){
        if (pX.length <= 3) {
            return bruteForce(pX);
        }

        int m = (int) Math.ceil(pX.length / 2.0) - 1;   // -1 because array index starts from 0

        if(pX[m+1].equals(pX[m])){
            result.setPoints(pX[m+1], pX[m]);
            result.setDistance(0);
            return result;
        }

        Point[] pL = Arrays.copyOfRange(pX, 0, m + 1); // "from" included and "to" excluded
        Point[] pR = Arrays.copyOfRange(pX, m + 1, pX.length);
        Point[] yL = new Point[pL.length];
        Point[] yR = new Point[pR.length];

        int indexL = 0;
        int indexR = 0;
        for(int i = 0; i < pY.length; i++){
            if(pY[i].getX() < pL[m].getX()){
                yL[indexL] = pY[i];
                indexL++;
            }
            else if(pY[i].getX() == pL[m].getX() && pY[i].getY() <= pL[m].getY()){
                yL[indexL] = pY[i];
                indexL++;
            }
            else {
                yR[indexR] = pY[i];
                indexR++;
            }
        }

        CPOPResult resL = calculateClosestPairOfPointsRic(pL, yL, result);
        CPOPResult resR = calculateClosestPairOfPointsRic(pR, yR, result);
        if(resL.getDistance() <= resR.getDistance()){
            result.setResult(resL);
        }
        else {
            result.setResult(resR);
        }

        ArrayList<Point> straddlingPoints = new ArrayList<Point>();
        for (Point p : pY) {
            if (Math.abs(p.getX() - pX[m].getX()) < result.getDistance()) {
                straddlingPoints.add(p);
            }
        }

        for (int i = 0; i < straddlingPoints.size(); i++) {
            for (int j = i + 1; j < straddlingPoints.size()
                    && (straddlingPoints.get(j).getY() - straddlingPoints.get(i).getY()) < result.getDistance(); j++) {
                double dS = calculateDistance(straddlingPoints.get(i), straddlingPoints.get(j));
                if (dS < result.getDistance()) {
                    result.setDistance(dS);
                    result.setPoints(straddlingPoints.get(i), straddlingPoints.get(j));
                }
            }
        }

        return result;
    }
}