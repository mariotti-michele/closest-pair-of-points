public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    protected double getX(){
        return x;
    }

    protected double getY(){
        return y;
    }

    protected boolean equals(Point p){
        if(p.getX() == this.getX() && p.getY() == this.getY())
            return true;
        else
            return false;
    }

    public String toString(){
        String s = "(" + x + ", " + y + ")";
        return s;
    }
}
