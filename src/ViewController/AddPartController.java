/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import Model.Part;
import Model.InhousePart;
import Model.Inventory;
import static Model.Inventory.getPartInv;
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
    private int modifyPartIndex;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // If addmodpart = 1 then set the form to add, if it = 2 then modify
        if(MainScreenController.addmodpart==2){
            //set the label to modify
            AddPartMainLabel.setText("Modify Parts");
        }
        if(MainScreenController.addmodpart==1){
            //set the screen label to Add Parts
            AddPartMainLabel.setText("Add Parts");
            //grab the next part ID from the inventory counter
            addModPartID = Inventory.getPartCoutner();
            //convert to string and set the PartID in the form
            AddPartIDField.setText(Integer.toString(addModPartID));
        }    
    }    
        
    @FXML
    private void AddPartInhouseRadioHandler(ActionEvent event) {
        //Set the radio buttons and labels to Machine ID
        inOrOut = false;
        AddPartMachineIDLabel.setText("Machine ID");
        AddPartMachineIDField.setPromptText("Machine ID");
        AddPartOutsourceRadio.setSelected(false);
    }

    @FXML
    private void AddPartOutsourceRadioHandler(ActionEvent event) {
        //Set the radio buttons and labels to Company Name
        inOrOut = true;
        AddPartMachineIDLabel.setText("Company Name");
        AddPartMachineIDField.setPromptText("Company Name");
        AddPartInhouseRadio.setSelected(false);
    }

    @FXML
    private void AddPartSaveButtonHandler(ActionEvent event) throws IOException{
        //grab the values of all the text boxes
        String textPartID=AddPartIDField.getText();
        String name=AddPartNameField.getText();
        String textInStock=AddPartInvField.getText();
        String textPrice=AddPartPriceField.getText();
        String textMax=AddPartMaxField.getText();
        String textMin=AddPartMinField.getText();
        String inout=AddPartMachineIDField.getText();
        
        //try catch to make sure that all the parseInt methods work - if not they throw a number format exception
        try {
            //set variables to proper data types
            int partID=Integer.parseInt(textPartID);
            int inStock=Integer.parseInt(textInStock);
            double price=Double.parseDouble(textPrice);
            int max=Integer.parseInt(textMax);
            int min=Integer.parseInt(textMin);
            //check the parts to see if the data matches the field
            errMsg = Part.partCheck(name, price, inStock, min, max, errMsg);
            if (errMsg.length() > 0){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Bad Data Entry");
                alert.setHeaderText("Error Adding Part");
                alert.setContentText(errMsg);
                alert.showAndWait();
                //reset the errMsg variable for the next iteration
                errMsg = "";
                //close the warning and stay on the form we are at
                return;
            }
            //check for inhousepart
            if (inOrOut == false) {
                //debug message to show that we picked the inhouse part
                System.out.println("Inhouse Part " + name);
                InhousePart inhousePart = new InhousePart();
                //load values to inhouse part
                inhousePart.setPartID(partID);
                inhousePart.setName(name);
                inhousePart.setInStock(inStock);
                inhousePart.setPrice(price);
                inhousePart.setMax(max);
                inhousePart.setMin(min);
                inhousePart.setMachineID(Integer.parseInt(inout));
                //check to see if we are adding a new part or modyfing
                if (MainScreenController.addmodpart==1){
                    Inventory.addPart(inhousePart);    
                }
                if (MainScreenController.addmodpart==2){
                    Inventory.updatePart(modifyPartIndex, inhousePart);
                }
            }
            //check for outsourced part
            if (inOrOut == true){
                //debug message to show that we properly picked outsource part
                System.out.println("Outsourced Part: " + name);
                OutsourcedPart outPart = new OutsourcedPart();
                //load values to outsource part
                outPart.setPartID(partID);
                outPart.setName(name);
                outPart.setInStock(inStock);
                outPart.setPrice(price);
                outPart.setMax(max);
                outPart.setMin(min);
                outPart.setCompanyName(inout);
                //check to see if we are adding a new part or modifying
                if (MainScreenController.addmodpart==1){
                    Inventory.addPart(outPart);
                }
                if (MainScreenController.addmodpart==2){
                    Inventory.updatePart(modifyPartIndex, outPart);
                }
            }
        //catch a number format error and pop up an error message
        } catch(NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter a valid number.");
            alert.showAndWait();
            //stay on the form we are at if an error gets thrown
            return;
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
    
    public void setPart(Part part){
            //based on the selected part from the main screen load the part
            modifyPartIndex = getPartInv().indexOf(part);
            //grab the parts part ID
            addModPartID = part.getPartID();
            //set the name, Inventory, Price, Max, and Min fields to the value of the part
            AddPartNameField.setText(part.getName());
            AddPartInvField.setText(Integer.toString(part.getInStock()));
            AddPartPriceField.setText(Double.toString(part.getPrice()));
            AddPartMaxField.setText(Integer.toString(part.getMax()));
            AddPartMinField.setText(Integer.toString(part.getMin()));
            //check to see if the part is inhouse or outsource and set the buttons
            //if the part is an instance of an Inhouse Part then set the form to inhouse and populate the machine ID
            if (part instanceof InhousePart) {
                inOrOut = false;
                AddPartMachineIDLabel.setText("Machine ID");
                AddPartMachineIDField.setText(Integer.toString(((InhousePart) part).getMachineID()));
                AddPartInhouseRadio.setSelected(true);
                AddPartOutsourceRadio.setSelected(false);
            }
            //if the part is an instance of an outsource part then set the form to oursource and populate the Company Name
            if (part instanceof OutsourcedPart) {
                inOrOut = true;
                AddPartMachineIDLabel.setText("Company Name");
                AddPartMachineIDField.setText(((OutsourcedPart) part).getCompanyName());
                AddPartInhouseRadio.setSelected(false);
                AddPartOutsourceRadio.setSelected(true);
            }
            //convert to string and set the PartID in the form
            AddPartIDField.setText(Integer.toString(addModPartID));
    }
}
