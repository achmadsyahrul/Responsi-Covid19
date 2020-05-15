package covid.view;

import covid.controller.DaerahController;
import covid.controller.StatusController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpecificDaerahView extends JFrame implements ActionListener {
    private JLabel labelProvinsi, labelKota, labelZona, labelPositif,
            labelPDP, labelODP, labelTgl, isProvinsi, isKota, isZona,
            isPositif, isPDP, isODP, isTgl, labelRiwayat;
    private JButton btnUpdate, btnDelete, btnBack, btnShow;
    private String id;

    public void openDetail(String[] data){
        //isi data[] = {id, kode, provinsi, kota, zona, positif, pdp, odp, tgl_update}
        this.id = data[0];
        setTitle(data[3] + ", " + data[2]);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350,325);
        labelProvinsi = new JLabel(" Provinsi : ");
        labelKota = new JLabel(" Kota : ");
        labelZona = new JLabel(" Zona : ");
        labelPositif = new JLabel(" Jumlah Positif : ");
        labelPDP = new JLabel(" Jumlah PDP : ");
        labelODP = new JLabel(" Jumlah ODP : ");
        labelTgl = new JLabel(" Tanggal Update : ");
        labelRiwayat = new JLabel( " Riwayat : ");

        isProvinsi = new JLabel(data[2]);
        isKota = new JLabel(data[3]);
        isZona = new JLabel(data[4]);
        isPositif = new JLabel(data[5]);
        isPDP = new JLabel(data[6]);
        isODP = new JLabel(data[7]);
        isTgl = new JLabel(data[8]);
        btnUpdate = new JButton("Edit");
        btnDelete = new JButton("Hapus");
        btnBack = new JButton("Kembali");
        btnShow = new JButton("Lihat");

        setLayout(null);
        add(labelProvinsi);
        add(labelKota);
        add(labelZona);
        add(labelPositif);
        add(labelPDP);
        add(labelODP);
        add(labelTgl);
        add(isProvinsi);
        add(isKota);
        add(isZona);
        add(isPositif);
        add(isPDP);
        add(isODP);
        add(isTgl);
        add(labelRiwayat);
        add(btnUpdate);
        add(btnDelete);
        add(btnBack);
        add(btnShow);

        labelProvinsi.setBounds(10,10,120,20);
        isProvinsi.setBounds(130,10,190,20);
        labelKota.setBounds(10,35,120,20);
        isKota.setBounds(130,35,190,20);
        labelZona.setBounds(10,60,120,20);
        isZona.setBounds(130,60,190,20);
        labelPositif.setBounds(10,85,120,20);
        isPositif.setBounds(130,85,190,20);
        labelPDP.setBounds(10,110,120,20);
        isPDP.setBounds(130,110,190,20);
        labelODP.setBounds(10,135,120,20);
        isODP.setBounds(130,135,190,20);
        labelTgl.setBounds(10,160,120,20);
        isTgl.setBounds(130,160,190,20);
        labelRiwayat.setBounds(10,185,120,20);
        btnShow.setBounds(130,185,90,20);
        btnShow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnShow.addActionListener(this);
        btnBack.setBounds(10,260,90,20);
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(this);
        btnUpdate.setBounds(110,260,90,20);
        btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnUpdate.setBackground(Color.blue);
        btnUpdate.setForeground(Color.white);
        btnUpdate.addActionListener(this);
        btnDelete.setBounds(210,260,90,20);
        btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDelete.setBackground(Color.red);
        btnDelete.setForeground(Color.white);
        btnDelete.addActionListener(this);

        setResizable(false);
        setLocation(450,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnUpdate){
            DaerahController daerahController = new DaerahController();
            daerahController.updateDaerah(id);
            dispose();
        }
        else if(e.getSource()==btnDelete){
            DaerahController d = new DaerahController();
            d.delete(id);
            dispose();
        }
        else if(e.getSource()==btnShow){
            StatusController statusController = new StatusController();
            statusController.showHistory(id);
            dispose();
        }
        else{
            DaerahController d = new DaerahController();
            d.readDaerah();
            dispose();
        }
    }
}

