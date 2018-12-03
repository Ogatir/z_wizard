import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private JPanel mainPanel;
    private JTextField textField1;
    private JTextArea textArea1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JPanel toolBarPanel;
    private JPanel outputPanel;
    private JPanel bottomPanel;
    private JList list1;
    private JPanel listPanel;
    private JTextArea TextArea;


    private class MyButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent action_event){
            textField1.setText("Z_Wizard");
        }
    }

    public MainWindow() {
        this.getContentPane().add(mainPanel);

    }

}
