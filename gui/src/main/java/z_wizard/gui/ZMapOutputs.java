package z_wizard.gui;

import z_wizard.containers.AbstractContainer;
import z_wizard.containers.ZMapOutputParams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.Format;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

public class ZMapOutputs implements ICrossFormable {
    private JPanel panel1;
    private JRadioButton addrBtn;
    private JRadioButton tcpBtn;
    private JRadioButton timestamps;
    private JRadioButton flagsBtn;
    private JRadioButton fullBtn;
    private JRadioButton ttlBtn;
    private JRadioButton classificBtn;
    private JButton saveBtn;
    JFrame zMapOutputs = new JFrame("Настройки вывода ZMap");

    private String addrParams = "saddr,saddr-raw,daddr,daddr-raw,ipid,";
    private String ttl = "ttl,";
    private String classific = "classification,";
    private String tcpParams = "sport,dport,seqnum,acknum,window,";
    private String flagsParams = "success,repeat,cooldown,";
    private String timestapmParams = "timestamp-str,timestamp-ts,timestamp-us,";
    private ZMapOutputParams params;

    ZMapOutputs(AbstractContainer container){
        zMapOutputs.setPreferredSize(new Dimension(350,300));
        zMapOutputs.add(panel1);
        zMapOutputs.setTitle("Настройки вывода ZMap");
        zMapOutputs.getContentPane();
        zMapOutputs.setLocationRelativeTo(null);
        zMapOutputs.pack();
        zMapOutputs.setVisible(true);

        params = (ZMapOutputParams) container;

        fullBtn.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                if ( itemEvent.getStateChange() == ItemEvent.SELECTED){
                    addrBtn.setSelected(false);
                    tcpBtn.setSelected(false);
                    timestamps.setSelected(false);
                    flagsBtn.setSelected(false);
                    classificBtn.setSelected(false);
                    ttlBtn.setSelected(false);
                    fullBtn.setSelected(true);
                }
            }
        });
        addrBtn.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                if ( itemEvent.getStateChange() == ItemEvent.SELECTED)
                    fullBtn.setSelected(false);
            }
        });
        tcpBtn.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                fullBtn.setSelected(false);
            }
        });
        timestamps.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                fullBtn.setSelected(false);
            }
        });
        flagsBtn.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                fullBtn.setSelected(false);
            }
        });
        ttlBtn.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                fullBtn.setSelected(false);
            }
        });
        classificBtn.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                fullBtn.setSelected(false);
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
        String value ="";
        if (fullBtn.isSelected())
            value += String.format("%s,%s,%s,%s,%s,%s",
                    addrParams, ttl, classific, tcpParams, flagsParams, timestapmParams);
            //            value += "=*";
//            result.put("--output-fields", value);
//            params.AddZmapOutputParam(result);
//            return;
//        }


        if (addrBtn.isSelected())
            value += addrParams;
        if (ttlBtn.isSelected())
            value += ttl;
        if (classificBtn.isSelected())
            value += classific;
        if (tcpBtn.isSelected())
            value += tcpParams;
        if (flagsBtn.isSelected())
            value += flagsParams;
        if (timestamps.isSelected())
            value += timestapmParams;
        result.put("-f", value);

        params.AddZmapOutputParam(result);
    }
}
