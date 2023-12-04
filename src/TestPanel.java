import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class TestPanel extends JPanel {

    private Point[] p;
    private CPOPResult result;
    private Ellipse2D circle;
    private double circleDim;

    public TestPanel(Point[] p, CPOPResult result, double frameWidth) {
        super();
        setBackground(Color.WHITE);
        setLayout(new GridLayout(0,1));
        this.p = p;
        this.result = result;
        this.circleDim = frameWidth / 100;
        this.circle = new Ellipse2D.Double(0, 0, circleDim, circleDim);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)(g);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        double xMin = p[0].getX();
        double xMax = p[0].getX();
        double yMin = p[0].getY();
        double yMax = p[0].getY();
        for(int i = 0; i < p.length; i++){
            if(p[i].getX() < xMin)
                xMin = p[i].getX();
            else if(p[i].getX() > xMax)
                xMax = p[i].getX();
            if(p[i].getY() < yMin)
                yMin = p[i].getY();
            else if(p[i].getY() > yMax)
                yMax = p[i].getY();
        }

        double bound = 5*circleDim;
        double udmX = (this.getWidth() - 2*bound) / (xMax - xMin);
        double udmY = (this.getHeight() - 2*bound) / (yMax - yMin);
        double udm = Math.min(udmX, udmY);

        for(Point pI : p){
            double x = 0;
            double y = 0;
            if(xMin == xMax){
                x = this.getWidth()/2.0 - circleDim/2;
            }
            else {
                x = pI.getX() * udm - circleDim/2 + bound - xMin * udm;
            }
            if(yMin == yMax){
                y = this.getHeight()/2.0 - circleDim/2;
            }
            else {
                y = this.getHeight() - (pI.getY() * udm - yMin * udm + bound) - circleDim/2;
            }

            if(pI.equals(result.getP1()) || pI.equals(result.getP2())){
                g2d.setColor(Color.GREEN);
            }
            else
                g2d.setColor(Color.RED);

            circle.setFrame(x, y, circleDim, circleDim);
            g2d.draw(circle);
            g2d.fill(circle);

            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.PLAIN, (int) circleDim));
            g2d.drawString(pI.toString(), (int) (x - circleDim), (int) (y + 2*circleDim));

            g2d.drawString("Distance: " + (float)result.getDistance(), (int) (this.getWidth()/2 - bound), (int) (this.getHeight() - bound/5));
        }
    }
}
