package org.example.mpp_ui;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.mpp_ui.Domain.Concurs;
import org.example.mpp_ui.Repository.ConcursRepo;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class UserView {
    @FXML
    private TableView<Concurs> concursuri;

    private static final Logger logger = LogManager.getLogger();

    private ConcursRepo concursRepo;

    public void initialize(){
        Properties props=new Properties();
        try {
            props.load(HelloApplication.class.getResourceAsStream("/BD.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }
        concursRepo = new ConcursRepo(props);
        reloadListViews();
    }
    private void reloadListViews(){
        logger.traceEntry();
        TableColumn<Concurs, Long> IdColumn = new TableColumn<>("id");
        IdColumn.setCellValueFactory(f -> new SimpleObjectProperty<>(f.getValue().getId()));
        TableColumn<Concurs, String> ProbaColumn = new TableColumn<>("Proba");
        ProbaColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getProba()));
        TableColumn<Concurs, Integer> VarstaMinColumn = new TableColumn<>("Varsta Minima");
        VarstaMinColumn.setCellValueFactory(f -> new SimpleObjectProperty<>(f.getValue().getVarstaMin()));
        TableColumn<Concurs, Integer> VarstaMaxColumn = new TableColumn<>("Varsta Maxima");
        VarstaMaxColumn.setCellValueFactory(f -> new SimpleObjectProperty<>(f.getValue().getVarstaMax()));
        concursuri.getColumns().clear();
        concursuri.getColumns().addAll(IdColumn, ProbaColumn, VarstaMinColumn, VarstaMaxColumn);
        List<Concurs> conc = concursRepo.getAll();
        conc.forEach((c) -> System.out.println(c.getId() + "|" + c.getProba()));
        ObservableList<Concurs> lst = FXCollections.observableArrayList();
        lst.addAll(conc);
        concursuri.setItems(lst);
        logger.traceExit();
    }
}
