import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class TestGUI extends JFrame {

    public TestGUI() {
        super("Closest pair of points");

        setMinimumSize(new Dimension(1024, 576));
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(new Dimension(screenDimension.width, screenDimension.width * 9 / 16));
        setResizable(false);
        setLocation(0, 0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pack();
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1.0");
        javax.swing.SwingUtilities.invokeLater(() -> {
            TestGUI testGUI = new TestGUI();
            testGUI.add(new SetupPanel(testGUI), BorderLayout.CENTER);
            testGUI.setVisible(true);
        });
    }

}
