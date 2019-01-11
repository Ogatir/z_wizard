package z_wizard.gui;

import z_wizard.containers.AbstractContainer;
import z_wizard.containers.CommonSettingsParams;
import z_wizard.containers.ZDnsParams;
import z_wizard.executors.ZGrabExecutor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CommonSettings implements ICrossFormable {
    private JPanel panel1;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextField zmapPathField;
    private JTextField ZGrabPathField;
    private JTextField ZDnsPathField;
    private JTextField ZtagPathField;
    private JTextField ZAnnotPathField;
    JFrame comm_settings= new JFrame("Настройки");

    private CommonSettingsParams params;

    public CommonSettings(){
        comm_settings.setPreferredSize(new Dimension(400,300));
        comm_settings.add(panel1);
        comm_settings.setTitle("Настройки");
        comm_settings.getContentPane();
        comm_settings.setLocationRelativeTo(null);
        comm_settings.pack();

        InitPath();


        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                SaveParams(params);
            }
        });
    }

    public void SaveParams(AbstractContainer container) {
        Map<String, String> result = new HashMap<String, String>();

        if (!zmapPathField.getText().equals(""))
            result.put("zMapPath", zmapPathField.getText());
        if (!ZGrabPathField.getText().equals(""))
            result.put("zGrabPath", ZGrabPathField.getText());
        if (!ZDnsPathField.getText().equals(""))
            result.put("zDnsPath", ZDnsPathField.getText());
        if (!ZtagPathField.getText().equals(""))
            result.put("zTagPath", ZtagPathField.getText());
        if (!ZAnnotPathField.getText().equals(""))
            result.put("zAnnotatePath", ZAnnotPathField.getText());
        params.AddSettingsParam(result);
    }

    public Map <String, String> GetCurrentParams(){
        Map <String, String> result = new HashMap<String, String>();
        result.put("zMapPath", zmapPathField.getText());
        result.put("zGrabPath", ZGrabPathField.getText());
        result.put("zDnsPath", ZDnsPathField.getText());
        result.put("zTagPath", ZtagPathField.getText());
        result.put("zAnnotatePath", ZAnnotPathField.getText());
        return result;
    }

    public void Show(AbstractContainer container){
        params = (CommonSettingsParams) container;
        comm_settings.setVisible(true);
    }

    private void InitPath(){
        zmapPathField.setText("zmap");
        ZGrabPathField.setText("/home/skorodub/go/src/github.com/zmap/zgrab/zgrab");
        ZDnsPathField.setText("/home/skorodub/go/src/github.com/zmap/zdns/zdns/zdns");
        ZtagPathField.setText("ztag");
        ZAnnotPathField.setText("zannotate");
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
