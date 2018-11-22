import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogonGui {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton registerButton;
    private JButton logonButton;
    private JPanel jpanel;

    public LogonGui() {
        logonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserGui userGui = new UserGui();
                userGui.init();
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void init() {
        JFrame frame = new JFrame("System");
        frame.setContentPane(new LogonGui().jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width) / 2) - 475,
                ((Toolkit.getDefaultToolkit().getScreenSize().height) / 2) - 300,
                950, 600);
        frame.setMinimumSize(new Dimension(950, 500));
        frame.setVisible(true);
    }
}
