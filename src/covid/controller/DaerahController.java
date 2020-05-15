package covid.controller;

import covid.model.CovidModel;
import covid.model.DaerahModel;
import covid.view.*;

public class DaerahController {
    public void createDaerah(){
        DaerahFormView view = new DaerahFormView();
        view.openForm();
    }

    public void createDaerah(String[] data){
        DaerahModel daerahModel = new DaerahModel();
        daerahModel.createDaerah(data);
    }

    public void readDaerah(){
        DaerahModel model = new DaerahModel();
        String[][] data = model.readDaerah();
        if(data == null)
            new MenuView();
        else
            new DaerahView(data);
    }

    public void readDaerah(String kode){
        DaerahModel model = new DaerahModel();
        SpecificDaerahView view = new SpecificDaerahView();
        view.openDetail(model.readDaerah(kode));
    }

    public void updateDaerah(String id){
        DaerahModel m = new DaerahModel();
        String kode = m.getData("kode", "daerah", "id", id);
        UpdateDaerahFormView update = new UpdateDaerahFormView();
        update.openForm(m.readDaerah(kode));
    }

    public void updateDaerah(String[] data){
        CovidModel covidModel = new DaerahModel(); //implementasi polymorphism
        covidModel.updateData(data);
    }

    public void delete(String id){
        CovidModel model = new DaerahModel(); //implementasi polymorphism
        model.delete(id);
    }
}
