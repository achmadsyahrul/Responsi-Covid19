package covid.view;

import covid.controller.DaerahController;
import covid.controller.MenuController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DaerahView extends JFrame implements ActionListener {
    private JTable table;
    private JButton btnBack;
    private String kode;
    public DaerahView(String[][] data){
        try{
            kode = data[0][0];
            final String[] tableTitle = {"Kode", "Provinsi", "Kota", "Positif", "PDP", "ODP", "Status", "Tanggal Update"};
            setTitle("Data Daerah");
            setSize(900,375);

            btnBack = new JButton(" Kembali ");
            table = new JTable(data,tableTitle);
            table.setBounds(30,40,400,600);
            JScrollPane sp=new JScrollPane(table);
            sp.setPreferredSize(new Dimension(500,80));
            this.getContentPane().add(BorderLayout.CENTER, sp);

            this.getContentPane().add(BorderLayout.SOUTH, btnBack);
            btnBack.addActionListener(this);
            btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            table.setCursor((Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)));
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
                public void valueChanged(ListSelectionEvent event) {
                    dispose();
                    kode = table.getValueAt(table.getSelectedRow(), 0).toString();
                    DaerahController daerahController = new DaerahController();
                    daerahController.readDaerah(kode);
                }
            });
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocation(200,150);
            setVisible(true);
        }
        catch (Exception e){
            System.out.println("Error : " + e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnBack){
            MenuController menu = new MenuController();
            menu.openMenu();
            dispose();
        }
    }
}

