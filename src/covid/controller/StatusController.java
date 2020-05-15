package covid.controller;

import covid.error.CovidException;
import covid.model.CovidModel;
import covid.model.StatusModel;
import covid.view.HistoryView;
import covid.view.StatusFormView;

public class StatusController {
    public void addStatus(){
        CovidModel model = new StatusModel(); //implementasi polymorphism
        StatusFormView view = new StatusFormView();
        view.openForm(model.getData("kode", "daerah"));
    }

    public void addStatus(String[] data){
        StatusModel model = new StatusModel();
        model.createStatus(data);
    }

    public void showHistory(String idDaerah){
        StatusModel model = new StatusModel();
        String kode = model.getData("kode","daerah","id", idDaerah);
        new HistoryView(model.readStatus(idDaerah), kode);
    }

    public void cekNegatif(int value) throws CovidException {
        if(value < 0)
            throw new CovidException("Bilangan harus positif");
    }
}
