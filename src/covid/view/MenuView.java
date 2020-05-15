package covid.view;

import covid.controller.DaerahController;
import covid.controller.StatusController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JFrame implements ActionListener {
    private JLabel labelMenu;
    private JButton btnCreate, btnUpdate, btnRead;

    public MenuView(){
        setTitle("Menu");
        labelMenu = new JLabel("Main Menu");
        labelMenu.setHorizontalAlignment(SwingConstants.CENTER);
        btnCreate = new JButton(" Tambah Daerah");
        btnUpdate = new JButton(" Update Daerah ");
        btnRead = new JButton(" Lihat Daerah ");
        setLayout(new GridLayout(4,1));
        add(labelMenu);
        add(btnCreate);
        add(btnUpdate);
        add(btnRead);
        pack();
        setResizable(false);
        setLocation(500,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRead.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCreate.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnRead.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== btnCreate){
            DaerahController daerah = new DaerahController();
            daerah.createDaerah();
            dispose();
        }
        else if(e.getSource()== btnUpdate){
            StatusController s = new StatusController();
            s.addStatus();
            dispose();
        }
        else{
            DaerahController d = new DaerahController();
            d.readDaerah();
            dispose();
        }
    }
}
