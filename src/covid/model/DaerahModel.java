package covid.model;

import covid.database.DBConnect;

import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DaerahModel extends CovidModel{
    private Connection connection;
    private Statement statement;

    public DaerahModel(){
        DBConnect dbConnect = new DBConnect();
        connection = dbConnect.getConnection();
    }

    public void createDaerah(String[] data){
        try{
            String query = " insert into daerah (kode, provinsi, kota, zona) values (?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, data[0]);
            preparedStmt.setString (2, data[1]);
            preparedStmt.setString (3, data[2]);
            preparedStmt.setString (4, "Hijau");
            preparedStmt.execute();
            connection.close();
            StatusModel statusModel = new StatusModel();
            statusModel.createStatus(data[0]);
            JOptionPane.showMessageDialog(null, "Input Berhasil");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public String[][] readDaerah(){
        try{
            int row = 0;
            int numRows = numRows("daerah");
            if(numRows==0){
                return null;
            }
            String[][] data = new String[numRows][8];
            statement = connection.createStatement();
            String query = "select d.kode, d.provinsi, d.kota, d.zona, s.positif, s.pdp, s.odp, s.tgl_update " +
                    "from daerah d left join status_orang s on d.id = s.id_daerah where s.tgl_update in " +
                    "(select max(tgl_update) from daerah d left join status_orang s on d.id = s.id_daerah group by d.kode) " +
                    "order by zona desc, provinsi asc, kota asc";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data[row][0] = resultSet.getString("kode");
                data[row][1] = resultSet.getString("provinsi");
                data[row][2] = resultSet.getString("kota");
                data[row][3] = resultSet.getString("positif");
                data[row][4] = resultSet.getString("pdp");
                data[row][5] = resultSet.getString("odp");
                data[row][6] = resultSet.getString("zona");
                data[row][7] = resultSet.getString("tgl_update");
                row++;
            }
            return data;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    public String[] readDaerah(String kode){
        try{
            String[] data = new String[9];
            statement = connection.createStatement();
            String query = "select * from daerah d left join status_orang s on d.id = s.id_daerah " +
                    "where kode = '"+kode+"' order by tgl_update desc limit 1";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data[0] = resultSet.getString("d.id");
                data[1] = resultSet.getString("kode");
                data[2] = resultSet.getString("provinsi");
                data[3] = resultSet.getString("kota");
                data[4] = resultSet.getString("zona");
                data[5] = resultSet.getString("positif");
                data[6] = resultSet.getString("pdp");
                data[7] = resultSet.getString("odp");
                data[8] = resultSet.getString("tgl_update");
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar tgl = Calendar.getInstance();
            try{
                tgl.setTime(format.parse(data[8]));
            }catch(ParseException e){
                e.printStackTrace();
            }
            format.applyPattern("dd-MMM-yyyy HH:mm:ss");
            data[8] = format.format(tgl.getTime());
            return data;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    @Override
    public void updateData(String[] data) {
        super.updateData(data);
    }

    @Override
    public void delete(String id) {
        super.delete(id);
    }
}
