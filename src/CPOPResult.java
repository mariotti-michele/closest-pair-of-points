public class CPOPResult {
    private double distance;
    protected Point p1;
    protected Point p2;

    public CPOPResult() {
        this.distance = Double.MAX_VALUE;
        this.p1 = null;
        this.p2 = null;
    }

    protected void setDistance(double distance){
        this.distance = distance;
    }

    protected void setPoints(Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    protected void setResult(CPOPResult result){
        this.distance = result.distance;
        this.p1 = result.p1;
        this.p2 = result.p2;
    }

    protected double getDistance(){
        return distance;
    }

    protected Point getP1(){
        return p1;
    }

    protected Point getP2(){
        return p2;
    }
}
