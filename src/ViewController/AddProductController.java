/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static Model.Inventory.*;
import Model.Part;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author miken
 */
public class AddProductController implements Initializable {
    private ObservableList<Part> ourParts = FXCollections.observableArrayList();
    @FXML
    private Button AddProdSearchButton;
    @FXML
    private Button AddProdAddButton;
    @FXML
    private Button AddProdDeleteButton;
    @FXML
    private Button AddProdSaveButton;
    @FXML
    private Button AddProdCancelButton;
    @FXML
    private Label AddProdMainLabel;
    @FXML
    private TextField AddProdSearchTextField;
    @FXML
    private TableView<Part> prodAddTbl;
    @FXML
    private TableColumn<Part, Integer> prodAddPartID;
    @FXML
    private TableColumn<Part, String> prodAddName;
    @FXML
    private TableColumn<Part, Integer> prodAddInv;
    @FXML
    private TableColumn<Part, Double> prodAddPrice;
    @FXML
    private TableView<Part> prodDelTable;
    @FXML
    private TableColumn<Part, Integer> prodDelPartID;
    @FXML
    private TableColumn<Part, String> prodDelName;
    @FXML
    private TableColumn<Part, Integer> prodDelInv;
    @FXML
    private TableColumn<Part, Double> prodDelPrice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // If addmodprod = 1 then add - if it = 2 then modify
        if(MainScreenController.addmodprod==2){
            AddProdMainLabel.setText("Modify Product");
        }
        if(MainScreenController.addmodprod==1){
            AddProdMainLabel.setText("Add Product");
        }
    }    

    @FXML
    private void AddProdSearchButtonHandler(ActionEvent event) {
    }

    @FXML
    private void AddProdAddButtonHandler(ActionEvent event) {
        Part part = prodAddTbl.getSelectionModel().getSelectedItem();
        ourParts.add(part);
        //call update prodDelTable();
    }

    @FXML
    private void AddProdDeleteButtonHandler(ActionEvent event) {
        Part part = prodDelTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to delete the part");
        Optional<ButtonType> x = alert.showAndWait();
        if (x.get() == ButtonType.OK){
            ourParts.remove(part);
            
        }
    }

    @FXML
    private void AddProdSaveButtonHandler(ActionEvent event) throws IOException{
        
        Stage stage; 
        Parent root;
        //get reference to the button's stage         
        stage=(Stage) AddProdSaveButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void AddProdCancelButtonHandler(ActionEvent event) throws IOException{
        Stage stage; 
        Parent root;
        //get reference to the button's stage         
        stage=(Stage) AddProdCancelButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
