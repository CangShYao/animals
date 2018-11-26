import javax.swing.*;

/**
 * software:   IntelliJ IDEA
 * author：    jd.wq@qq.com
 * file:       ErrorMessage.java
 * date：      2018/11/26 21:35
 * describe：
 **/
public class ErrorMessage {
    public void show_error(String error_message)
    {
        JOptionPane.showMessageDialog(null, error_message, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}
