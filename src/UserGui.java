import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserGui {
    private JPanel panel1;
    private JTextField textField1;
    private JButton FindButton;
    private JButton DeleteButton;
    private JTextField textField2;
    private JButton CFindButton;
    private JButton CDeleteButton;
    private JTable table1;
    private JTable table2;
    private JTextField textField3;
    private JButton VFindButton;
    private JButton VDeleteButton;
    private JTable table3;
    private JPanel ANIMAL;
    private JPanel CENTER;
    private JPanel VACCINE;
    private JTabbedPane tablePane1;

    public UserGui() {
        FindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String animal_id = textField1.getText();
            }
        });
        DeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String animal_id = textField1.getText();
                String table = "ANIMAL";
                DBconnect dBconnect = new DBconnect();
                dBconnect.DeleteData(animal_id, table);
            }
        });
        CFindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        CDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        VFindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        VDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void init()
    {
        String[] animal = {"ANIMALID", "ANIMALNUMBER", "ANIMALNAME","ANIMALSPECIES","CENTERID"};
        String[] center = {"CENTERID", "CENTERNAME", "CENTERADDRESS", "ROOM", "ROOMLEFT"};
        String[] vaccine = {"ID", "AINMAL", "USERID", "NAME"};
        JFrame frame = new JFrame("Animals");
        frame.setContentPane(new UserGui().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width) / 2) - 475,
                ((Toolkit.getDefaultToolkit().getScreenSize().height) / 2) - 300,
                950, 600);
        frame.setMinimumSize(new Dimension(950, 500));
        table1.addColumn(new TableColumn(5, 50));
        table1.setVisible(true);
        frame.setVisible(true);
    }
}
