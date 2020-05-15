package covid.model;

import covid.database.DBConnect;

import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class StatusModel extends CovidModel{
    private Connection connection;
    private Statement statement;

    public StatusModel(){
        DBConnect dbConnect = new DBConnect();
        connection = dbConnect.getConnection();
    }

    public void createStatus(String kodeDaerah){
        String id = getData("id", "daerah", "kode", kodeDaerah);

        Timestamp date = new Timestamp(new Date().getTime()+7*60*60*1000); //+7jam konversi ke millisecond
        try{
            String query = " insert into status_orang (id_daerah, positif, pdp, odp, tgl_update) values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, id);
            preparedStmt.setInt (2, 0);
            preparedStmt.setInt (3, 0);
            preparedStmt.setInt (4, 0);
            preparedStmt.setTimestamp (5, date);
            preparedStmt.execute();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void createStatus(String[] data){
        String id = getData("id", "daerah", "kode", data[0]);

        Timestamp date = new Timestamp(new Date().getTime()+7*60*60*1000); //+7jam konversi ke millisecond
        try{
            String query = " insert into status_orang (id_daerah, positif, pdp, odp, tgl_update) values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, id);
            preparedStmt.setString (2, data[1]);
            preparedStmt.setString (3, data[2]);
            preparedStmt.setString (4, data[3]);
            preparedStmt.setTimestamp (5, date);
            preparedStmt.execute();
            updateStatus(data);
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void updateStatus(String[] data){
        try{
            String zona;
            int positif=0;
            try {
                positif = Integer.parseInt(data[1]);
            } catch (NumberFormatException num) {
                JOptionPane.showMessageDialog(null, num.getMessage());
            }
            statement = connection.createStatement();
            String query = "update daerah set zona = ? where kode = ?";
            if(positif>0){
                zona = "Merah";
            }
            else{
                zona = "Hijau";
            }
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, zona);
            preparedStmt.setString (2, data[0]);
            preparedStmt.execute();
            statement.close();
            JOptionPane.showMessageDialog(null, "Update Berhasil");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public String[][] readStatus(String idDaerah){
        try{
            int row = 0;
            String[][] data = new String[numRows("status_orang", "id_daerah", idDaerah)][4];
            statement = connection.createStatement();
            String query = "select * from status_orang where id_daerah = '" + idDaerah + "'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data[row][0] = resultSet.getString("positif");
                data[row][1] = resultSet.getString("pdp");
                data[row][2] = resultSet.getString("odp");
                data[row][3] = resultSet.getString("tgl_update");
                row++;
            }
            return data;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    @Override
    public String[] getData(String column, String table) {
        return super.getData(column, table);
    }
}
