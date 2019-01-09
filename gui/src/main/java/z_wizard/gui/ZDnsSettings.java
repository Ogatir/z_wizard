package z_wizard.gui;

import z_wizard.containers.AbstractContainer;
import z_wizard.containers.ZDnsParams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ZDnsSettings implements ICrossFormable{

    JFrame zdnsSet = new JFrame("Настройки ZDns");
    private JPanel panel1;
    private JComboBox moduleField;
    private JTextField addrField;
    private JTextField fileNameField;
    private JButton saveFileBtn;
    private JButton saveBtn;

    private String moduleTypes[] = {"A", "AAAA", "ANY", "AXFR", "CAA",
            "CNAME", "DMARC", "MX", "NS", "PTR", "TXT", "SOA", "SPF", "mxlookup", "alookup"};

    private ZDnsParams params;

    public ZDnsSettings(AbstractContainer container){
        zdnsSet.setPreferredSize(new Dimension(400,300));
        zdnsSet.add(panel1);
        zdnsSet.setTitle("Настройки ZDns");
        zdnsSet.getContentPane();
        zdnsSet.setLocationRelativeTo(null);
        zdnsSet.pack();
        zdnsSet.setVisible(true);

        params = (ZDnsParams) container;

        for (String module : moduleTypes)
            moduleField.addItem(module);

        saveFileBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Выбрать файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    fileNameField.setText(file.getAbsolutePath());
                }
            }
        });
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                SaveParams(params);
            }
        });
    }

    public void SaveParams(AbstractContainer container) {
        Map<String, String> result = new HashMap<String, String>();

        if (!addrField.getText().equals(""))
            result.put("page", addrField.getText());

        result.put("module", (String) moduleField.getItemAt(moduleField.getSelectedIndex()));

        if (!fileNameField.getText().equals(""))
            result.put("--output-file", fileNameField.getText());

        params.AddZDnsOutputParam(result);
    }
}
