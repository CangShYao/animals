import javax.swing.*;
import java.awt.*;

public class UserGui {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JButton button1;
    private JButton button2;

    public void init()
    {
        JFrame frame = new JFrame("Animals");
        frame.setContentPane(new UserGui().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width) / 2) - 475,
                ((Toolkit.getDefaultToolkit().getScreenSize().height) / 2) - 300,
                950, 600);
        frame.setMinimumSize(new Dimension(950, 500));
        frame.setVisible(true);
    }
}
