/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import Model.InhousePart;
import Model.OutsourcedPart;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author miken
 */
public class AddPartController implements Initializable {

    @FXML
    private Label AddPartMainLabel;
    @FXML
    private RadioButton AddPartInhouseRadio;
    @FXML
    private RadioButton AddPartOutsourceRadio;
    @FXML
    private Button AddPartSaveButton;
    @FXML
    private Button AddPartCancelButton;
    @FXML
    private Label AddPartMachineIDLabel;
    @FXML
    private TextField AddPartIDField;
    @FXML
    private TextField AddPartNameField;
    @FXML
    private TextField AddPartInvField;
    @FXML
    private TextField AddPartPriceField;
    @FXML
    private TextField AddPartMaxField;
    @FXML
    private TextField AddPartMinField;
    @FXML
    private TextField AddPartMachineIDField;
    //variable to populate partID field
    private int addModPartID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // If addmodpart = 1 then add - if it = 2 then modify
        if(MainScreenController.addmodpart==2){
            AddPartMainLabel.setText("Modify Parts");
        }
        if(MainScreenController.addmodpart==1){
            AddPartMainLabel.setText("Add Parts");
        }
        //addModPartID = Inventory.getPIDCt();
        //AddPartIDField.setText(addModPartID);
    }    
    
    @FXML
    private void AddPartInhouseRadioHandler(ActionEvent event) {
        
        AddPartMachineIDLabel.setText("Machine ID");
        AddPartMachineIDField.setPromptText("Machine ID");
        AddPartOutsourceRadio.setSelected(false);
    }

    @FXML
    private void AddPartOutsourceRadioHandler(ActionEvent event) {
        
        AddPartMachineIDLabel.setText("Company Name");
        AddPartMachineIDField.setPromptText("Company Name");
        AddPartInhouseRadio.setSelected(false);
    }

    @FXML
    private void AddPartSaveButtonHandler(ActionEvent event) throws IOException{
        //remove partID when we finish inventory
        String partID=AddPartIDField.getText();
        String name=AddPartNameField.getText();
        String inv=AddPartInvField.getText();
        String price=AddPartPriceField.getText();
        String max=AddPartMaxField.getText();
        String min=AddPartMinField.getText();
        String inout=AddPartMachineIDField.getText();
        
        
        //add try to validate input for part
        if (inout == "Machine ID") {
            System.out.println("Outsourced Part name: " + name);
            InhousePart inhousePart = new InhousePart();
            //when we finish inventory set this to addModPartID
            inhousePart.setPartID(Integer.parseInt(partID));
            inhousePart.setName(name);
            inhousePart.setInStock(Integer.parseInt(inv));
            inhousePart.setPrice(Double.parseDouble(price));
            inhousePart.setMax(Integer.parseInt(max));
            inhousePart.setMin(Integer.parseInt(min));
            inhousePart.setMachineID(Integer.parseInt(inout));
            //Inventory.addPart(inhousePart);    
        }
        if (inout == "Company Name"){
            System.out.println("In House Part name: " + name);
            OutsourcedPart outPart = new OutsourcedPart();
            //when we finish inventory set this to addModPartID
            outPart.setPartID(Integer.parseInt(partID));
            outPart.setName(name);
            outPart.setInStock(Integer.parseInt(inv));
            outPart.setPrice(Double.parseDouble(price));
            outPart.setMax(Integer.parseInt(max));
            outPart.setMin(Integer.parseInt(min));
            outPart.setCompanyName(inout);
            //Inventory.addPart(outPart); 
        }
        //add catch 
        
        
        Stage stage; 
        Parent root;
        //get reference to the button's stage         
        stage=(Stage) AddPartSaveButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void AddPartCancelButtonHandler(ActionEvent event) throws IOException{
        
        Stage stage; 
        Parent root;
        //get reference to the button's stage         
        stage=(Stage) AddPartCancelButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
