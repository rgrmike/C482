/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import Model.Part;
import Model.InhousePart;
import Model.Inventory;
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
import javafx.scene.control.Alert;
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
    
    private String errMsg = new String();
    private boolean inOrOut;
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
        inOrOut = false;
        AddPartMachineIDLabel.setText("Machine ID");
        AddPartMachineIDField.setPromptText("Machine ID");
        AddPartOutsourceRadio.setSelected(false);
    }

    @FXML
    private void AddPartOutsourceRadioHandler(ActionEvent event) {
        inOrOut = true;
        AddPartMachineIDLabel.setText("Company Name");
        AddPartMachineIDField.setPromptText("Company Name");
        AddPartInhouseRadio.setSelected(false);
    }

    @FXML
    private void AddPartSaveButtonHandler(ActionEvent event) throws IOException{
        //remove partID when we finish inventory
        String textPartID=AddPartIDField.getText();
        String name=AddPartNameField.getText();
        String textInStock=AddPartInvField.getText();
        String textPrice=AddPartPriceField.getText();
        String textMax=AddPartMaxField.getText();
        String textMin=AddPartMinField.getText();
        String inout=AddPartMachineIDField.getText();
        
        
        try {
            int partID=Integer.parseInt(textPartID);
            int inStock=Integer.parseInt(textInStock);
            double price=Double.parseDouble(textPrice);
            int max=Integer.parseInt(textMax);
            int min=Integer.parseInt(textMin);
        
            errMsg = Part.partCheck(name, price, inStock, min, max, errMsg);
            if (errMsg.length() > 0){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Bad Data Entry");
                alert.setHeaderText("Error Adding Part");
                alert.setContentText(errMsg);
                alert.showAndWait();
                //reset the errMsg variable for the next iteration
                errMsg = "";
            }
            
            if (inOrOut == false) {
                System.out.println("Inhouse Part " + name);
                InhousePart inhousePart = new InhousePart();
                //when we finish inventory set this to addModPartID
                inhousePart.setPartID(partID);
                inhousePart.setName(name);
                inhousePart.setInStock(inStock);
                inhousePart.setPrice(price);
                inhousePart.setMax(max);
                inhousePart.setMin(min);
                inhousePart.setMachineID(Integer.parseInt(inout));
                Inventory.addPart(inhousePart);    
            }
            if (inOrOut == true){
                System.out.println("Outsourced Part: " + name);
                OutsourcedPart outPart = new OutsourcedPart();
                //when we finish inventory set this to addModPartID
                outPart.setPartID(partID);
                outPart.setName(name);
                outPart.setInStock(inStock);
                outPart.setPrice(price);
                outPart.setMax(max);
                outPart.setMin(min);
                outPart.setCompanyName(inout);
                Inventory.addPart(outPart); 
            }
        } catch(NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Number Error");
            alert.setContentText("Please enter a valid number.");
            alert.showAndWait();
        }        
        
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
