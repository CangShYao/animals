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
                System.out.println("尝试登录");
                DBconnect dBconnect = new DBconnect();
                String pd = dBconnect.getPassword(textField1.getText());
                String pd2 = passwordField1.getText();
                if (pd.equals(pd2))
                {
                    System.out.println("登录成功");
                    UserGui userGui = new UserGui();
                    userGui.init();
                }
                else {
                    System.out.println("登录失败");
                    if (pd.equals(""))
                    {
                        ErrorMessage eM = new ErrorMessage();
                        eM.show_error("密码错误");
                    }
                }
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
