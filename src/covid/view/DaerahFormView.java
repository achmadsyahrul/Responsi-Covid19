package covid.view;

import covid.controller.DaerahController;
import covid.controller.MenuController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DaerahFormView extends JFrame implements ActionListener {
    private JTextField fieldProvinsi, fieldKota, fieldKode;
    private JLabel labelJudul, labelProvinsi, labelKota, labelKode;

    private JButton btnSubmit, btnReset, btnBack;

    public void openForm(){
        setTitle("Input Daerah");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350,225);

        fieldProvinsi = new JTextField(10);
        fieldKota = new JTextField(10);
        fieldKode = new JTextField(10);
        labelJudul = new JLabel(" Input Daerah ");
        labelProvinsi = new JLabel(" Provinsi ");
        labelKota = new JLabel(" Kota ");
        labelKode = new JLabel(" Kode Daerah ");
        btnSubmit = new JButton("Submit");
        btnReset = new JButton("Reset");
        btnBack = new JButton("Kembali");

        setLayout(null);
        add(labelJudul);
        add(fieldProvinsi);
        add(labelProvinsi);
        add(labelKota);
        add(fieldKota);
        add(labelKode);
        add(fieldKode);
        add(btnSubmit);
        add(btnReset);
        add(btnBack);

        labelJudul.setBounds(10,10,120,20);
        labelProvinsi.setBounds(10,35,120,20);
        fieldProvinsi.setBounds(130,35,190,20);
        labelKota.setBounds(10,60,120,20);
        fieldKota.setBounds(130,60,190,20);
        labelKode.setBounds(10,85,120,20);
        fieldKode.setBounds(130,85,190,20);
        btnSubmit.setBounds(75,125,120,20);
        btnSubmit.setBackground(Color.blue);
        btnSubmit.setForeground(Color.white);
        btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSubmit.addActionListener(this);
        btnReset.setBounds(200,125,120,20);
        btnReset.setBackground(Color.red);
        btnReset.setForeground(Color.white);
        btnReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnReset.addActionListener(this);
        btnBack.setBounds(10,155,320,20);
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(this);

        setResizable(false);
        setLocation(450,200);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnSubmit) {
            if (fieldProvinsi.getText().equals("")) {
                setMessage("Provinsi harus diisi");
            }
            if (fieldKota.getText().equals("")) {
                setMessage("Kota harus diisi");
            }
            if (fieldKode.getText().equals("")) {
                setMessage("Kode daerah harus diisi");
            }
            else {
                String[] data = {
                        fieldKode.getText(), fieldProvinsi.getText(), fieldKota.getText()
                };
                DaerahController daerah = new DaerahController();
                daerah.createDaerah(data);
            }
        }
        else if(e.getSource()==btnReset){
            reset();
        }
        else if(e.getSource()==btnBack){
            dispose();
            MenuController m = new MenuController();
            m.openMenu();
        }
    }

    public void reset(){
        fieldProvinsi.setText("");
        fieldKota.setText("");
        fieldKode.setText("");
    }

    public void setMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }
}

