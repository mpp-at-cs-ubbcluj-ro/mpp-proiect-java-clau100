package org.example.mpp_ui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.mpp_ui.Domain.Concurs;
import org.example.mpp_ui.Domain.Participant;
import org.example.mpp_ui.Repository.ConcursRepo;
import org.example.mpp_ui.Repository.ParticipantRepo;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class UserView {
    @FXML
    private TableView<Concurs> concursuri;

    @FXML
    private TableView<Participant> participanti;

    @FXML
    private TableView<Participant> totiParticipanti;

    @FXML
    private TextField numeText;

    @FXML
    private TextField varstaText;
    private static final Logger logger = LogManager.getLogger();

    private ConcursRepo concursRepo;
    private ParticipantRepo participantRepo;

    public void initialize(){
        Properties props=new Properties();
        try {
            props.load(HelloApplication.class.getResourceAsStream("/BD.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }
        concursRepo = new ConcursRepo(props);
        participantRepo = new ParticipantRepo(props);

        concursuri.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        participanti.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        totiParticipanti.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        reloadListViews();

        concursuri.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if(newSelection == null){
                return;
            }
            System.out.println("Selected: " + newSelection.getId());
            Optional<Concurs> opt = concursRepo.find(newSelection.getId());
            if (opt.isEmpty()){
                return;
            }
            Concurs c = opt.get();
            List<Participant> participants = participantRepo.FindAllFromList(c.getParticipanti());

            ObservableList<Participant> lst = FXCollections.observableArrayList();

            lst.addAll(participants);
            lst.forEach(p ->{
                System.out.println(p.getNume());
            });
            participanti.setItems(lst);
        });


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

        TableColumn<Participant, Long> partId = new TableColumn<>("id");
        IdColumn.setCellValueFactory(f -> new SimpleObjectProperty<>(f.getValue().getId()));

        TableColumn<Participant, String> partNume = new TableColumn<>("Nume");
        partNume.setCellValueFactory(f -> new SimpleObjectProperty<>(f.getValue().getNume()));

        TableColumn<Participant, Integer> partVarsta = new TableColumn<>("Varsta");
        partVarsta.setCellValueFactory(f -> new SimpleObjectProperty<>(f.getValue().getVarsta()));
        participanti.getColumns().clear();
        participanti.getColumns().addAll(partId, partNume, partVarsta);

        TableColumn<Participant, Long> partId2 = new TableColumn<>("id");
        partId2.setCellValueFactory(f -> new SimpleObjectProperty<>(f.getValue().getId()));

        TableColumn<Participant, String> partNume2 = new TableColumn<>("Nume");
        partNume2.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getNume()));

        TableColumn<Participant, Integer> partVarsta2 = new TableColumn<>("Varsta");
        partVarsta2.setCellValueFactory(f -> new SimpleObjectProperty<>(f.getValue().getVarsta()));

        totiParticipanti.getColumns().clear();
        totiParticipanti.getColumns().addAll(partId2, partNume2, partVarsta2);

        List<Participant> part = participantRepo.getAll();
        ObservableList<Participant> parts = FXCollections.observableArrayList();
        parts.addAll(part);
        totiParticipanti.setItems(parts);

        logger.traceExit();
    }

    public void EnrollClicked(){
        Concurs c = concursuri.getSelectionModel().selectedItemProperty().get();
        Participant p = totiParticipanti.getSelectionModel().selectedItemProperty().get();

        concursRepo.Enroll(p.getId(), c.getId());
    }

    public void RegisterClicked(){
        String nume = numeText.getText();
        int varsta =Integer.parseInt(varstaText.getText());

        Participant p = new Participant(0L, varsta, nume, new LinkedList<>());
        participantRepo.add(p);

        reloadListViews();
    }
}
