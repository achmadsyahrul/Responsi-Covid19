package covid.view;

import covid.controller.DaerahController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistoryView extends JFrame implements ActionListener {
    private JTable table;
    private JButton btnBack;
    private String kode;
    public HistoryView(String[][] data, String kode){
        try{
            this.kode = kode;
            final String[] tableTitle = {"Positif", "PDP", "ODP", "Tanggal Update"};
            setTitle("Data Daerah");
            setSize(600,250);

            btnBack = new JButton(" Kembali ");
            table = new JTable(data,tableTitle);
            table.setBounds(30,40,400,600);
            JScrollPane sp=new JScrollPane(table);
            sp.setPreferredSize(new Dimension(500,80));
            this.getContentPane().add(BorderLayout.CENTER, sp);

            this.getContentPane().add(BorderLayout.SOUTH, btnBack);
            btnBack.addActionListener(this);
            btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocation(350,200);
            setVisible(true);
        }
        catch (Exception e){
            System.out.println("Error : " + e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnBack){
            DaerahController daerahController = new DaerahController();
            daerahController.readDaerah(kode);
            dispose();
        }
    }
}