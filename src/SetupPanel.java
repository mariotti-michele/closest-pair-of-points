import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SetupPanel extends JPanel {

    private TestGUI testGUI;
    private JTextField xCoordTextField;
    private JTextField yCoordTextField;
    private ArrayList<Point> pList;

    public SetupPanel(TestGUI testGUI) {
        super();
        setBackground(Color.WHITE);
        setLayout(new GridLayout(0,1));
        setBorder(BorderFactory.createEmptyBorder(testGUI.getWidth()/10, testGUI.getWidth()/4,
                testGUI.getWidth()/10, testGUI.getWidth()/4));
        this.testGUI = testGUI;
        this.pList = new ArrayList<Point>();

        this.add(new JLabel("insert x coordinate of point"));
        xCoordTextField = new JTextField();
        this.add(xCoordTextField);
        this.add(new JLabel("insert y coordinate of point"));
        yCoordTextField = new JTextField();
        this.add(yCoordTextField);
        JButton insertPointButton = new JButton("insert point");
        this.add(insertPointButton);
        insertPointButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleInsertPointButton();
            }
        });
        JButton endButton = new JButton("end");
        this.add(endButton);
        endButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleEndButton();
            }
        });
    }

    private void handleInsertPointButton(){
        pList.add(new Point(Double.parseDouble(xCoordTextField.getText()), Double.parseDouble(yCoordTextField.getText())));
        yCoordTextField.setText("");
        xCoordTextField.setText("");
    }

    private void handleEndButton(){
        CPOPResult result = ClosestPairOfPoints.calculateClosestPairOfPoints(pList.toArray(new Point[pList.size()]));
        TestPanel testPanel = new TestPanel(pList.toArray(new Point[pList.size()]), result, testGUI.getWidth());
        testGUI.getContentPane().removeAll();
        testGUI.getContentPane().add(testPanel, BorderLayout.CENTER);
        testGUI.validate();
        testGUI.repaint();

        System.out.println("Points: " + pList.toString());
        System.out.println("Closest pair of points: ");
        System.out.println(result.getP1().toString());
        System.out.println(result.getP2().toString());
    }
}
