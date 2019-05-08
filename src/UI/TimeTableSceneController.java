/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Model.TimeTable;
import UseCases.ScheduleFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Haneen
 */
public class TimeTableSceneController implements Initializable {

    @FXML
    private TextField startTxtBox;
    @FXML
    private TextField endTextBox;
    @FXML
    private ScrollPane daysScrollPane;
    @FXML
    private ScrollPane TimeTableScrollPane;
    
    private FlowPane daysPane;
    public HashSet<String> checkedDays;
    private ArrayList<CheckBox> daysCheckBoxes;
    private TimeTable generatedTimeTable;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        checkedDays = new HashSet<>();
        daysCheckBoxes = new ArrayList<>();
        daysPane = new FlowPane();
        for(TimeTablePresenter.Day d: TimeTablePresenter.Day.values()){
            CheckBox c = new CheckBox(d.toString());
            c.selectedProperty().getValue();
            daysCheckBoxes.add(c);
            daysPane.getChildren().add(c);
        }
        daysScrollPane.setContent(daysPane);
    } 
    
    public void makeSchedule(){
        if(checkedDays.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING, "No days have been checked!");
            alert.show();
            return;
        }
        int start=0, end=0;
        try{
            start = Integer.valueOf(startTxtBox.getText());
            end = Integer.valueOf(endTextBox.getText());
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Numbers only!");
            alert.show();
            return;
        }
        generatedTimeTable = ScheduleFactory.generateSchedule((String [])checkedDays.toArray(), start, end);
        TimeTablePresenter t = new TimeTablePresenter(generatedTimeTable);
        TimeTableScrollPane.setContent(t);
    }
    
}
