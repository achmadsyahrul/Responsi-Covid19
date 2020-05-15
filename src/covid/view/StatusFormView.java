package covid.view;

import covid.controller.MenuController;
import covid.controller.StatusController;
import covid.error.CovidException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatusFormView extends JFrame implements ActionListener {
    private JLabel labelKode, labelPositif, labelPDP, labelODP;
    private JComboBox cmbKode;
    private JSpinner positif,pdp,odp;
    private JButton btnSubmit, btnReset, btnBack;

    public void openForm(String[] kode){
        setTitle("Update");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350,275);

        labelKode = new JLabel(" Kode Daerah ");
        labelPositif = new JLabel(" Jumlah Positif Covid ");
        labelPDP = new JLabel(" Jumlah PDP ");
        labelODP = new JLabel(" Jumlah ODP ");
        cmbKode = new JComboBox(kode);
        positif = new JSpinner(new SpinnerNumberModel());
        pdp = new JSpinner(new SpinnerNumberModel());
        odp = new JSpinner(new SpinnerNumberModel());
        btnSubmit = new JButton("Submit");
        btnReset = new JButton("Reset");
        btnBack = new JButton("Kembali");

        setLayout(null);
        add(labelKode);
        add(cmbKode);
        add(labelPositif);
        add(positif);
        add(labelPDP);
        add(pdp);
        add(labelODP);
        add(odp);
        add(btnSubmit);
        add(btnReset);
        add(btnBack);

        labelKode.setBounds(10,10,150,20);
        cmbKode.setBounds(180,10,150,20);
        labelPositif.setBounds(10,35,150,20);
        positif.setBounds(180,35,150,20);
        labelPDP.setBounds(10,60,150,20);
        pdp.setBounds(180,60,150,20);
        labelODP.setBounds(10, 85, 150, 20);
        odp.setBounds(180,85,150,20);

        btnSubmit.setBounds(75,175,120,20);
        btnSubmit.setBackground(Color.blue);
        btnSubmit.setForeground(Color.white);
        btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSubmit.addActionListener(this);
        btnReset.setBounds(200,175,120,20);
        btnReset.setBackground(Color.red);
        btnReset.setForeground(Color.white);
        btnReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnReset.addActionListener(this);
        btnBack.setBounds(10,210,320,20);
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(this);

        setResizable(false);
        setLocation(450,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnSubmit) {

            if(positif.getValue().equals("")){
                setMessage("Positif harus diisi");
            }
            if(pdp.getValue().equals("")){
                setMessage("PDP harus diisi");
            }
            if(odp.getValue() == null){
                setMessage("ODP harus diisi");
            }
            if(cmbKode.getSelectedIndex()==0){
                setMessage("Kode harus diisi");
            }
            else{
                try {
                    int cek1 = Integer.parseInt(positif.getValue().toString());
                    int cek2 = Integer.parseInt(pdp.getValue().toString());
                    int cek3 = Integer.parseInt(odp.getValue().toString());
                    StatusController c = new StatusController();
                    c.cekNegatif(cek1);
                    c.cekNegatif(cek2);
                    c.cekNegatif(cek3);
                    String[] data = {
                            cmbKode.getSelectedItem().toString(),
                            positif.getValue().toString(),
                            pdp.getValue().toString(),
                            odp.getValue().toString()
                    };
                    c.addStatus(data);
                } catch (NumberFormatException num) {
                    setMessage("Jumlah positif covid, pdp, odp harus bilangan");
                } catch (CovidException covidEx){
                    setMessage(covidEx.getMessage());
                }
            }
        }
        else if(e.getSource()==btnReset){
            reset();
        }
        else if(e.getSource()==btnBack){
            MenuController menu = new MenuController();
            menu.openMenu();
            dispose();
        }
    }

    public void reset(){
        cmbKode.setSelectedIndex(0);
        positif.setValue(0); pdp.setValue(0); odp.setValue(0);
    }

    public void setMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }
}

