/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import Model.Inventory;
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
import Model.Product;
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
    private int addModProdID;
    private String errMsg = new String();
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
    @FXML
    private TextField AddProdIDField;
    @FXML
    private TextField AddProdNameField;
    @FXML
    private TextField AddProdInvField;
    @FXML
    private TextField AddProdPriceField;
    @FXML
    private TextField AddProdMaxFieeld;
    @FXML
    private TextField AddProdMinField;

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
        prodAddPartID.setCellValueFactory(cellData -> cellData.getValue().pIDProp().asObject());
        prodAddName.setCellValueFactory(cellData -> cellData.getValue().partNameProp());
        prodAddInv.setCellValueFactory(cellData -> cellData.getValue().partInStockProp().asObject());
        prodAddPrice.setCellValueFactory(cellData -> cellData.getValue().partPriceProp().asObject());
        prodDelPartID.setCellValueFactory(cellData -> cellData.getValue().pIDProp().asObject());
        prodDelName.setCellValueFactory(cellData -> cellData.getValue().partNameProp());
        prodDelInv.setCellValueFactory(cellData -> cellData.getValue().partInStockProp().asObject());
        prodDelPrice.setCellValueFactory(cellData -> cellData.getValue().partPriceProp().asObject());
        updatePartsTbl();
        updateProdTbl();
        addModProdID = Inventory.getProductCoutner();
        AddProdIDField.setText(Integer.toString(addModProdID));
    }    

    public void updatePartsTbl(){
        prodAddTbl.setItems(getPartInv());
    }
    //grab products from inventory
    public void updateProdTbl() {
        prodDelTable.setItems(ourParts);
    } 
    
    @FXML
    private void AddProdSearchButtonHandler(ActionEvent event) {
    }

    @FXML
    private void AddProdAddButtonHandler(ActionEvent event) {
        //record the selected item
        Part part = prodAddTbl.getSelectionModel().getSelectedItem();
        //add the part to the local list of parts
        ourParts.add(part);
        //update the bottom table with the parts
        updateProdTbl();
    }

    @FXML
    private void AddProdDeleteButtonHandler(ActionEvent event) {
        //get the selected item from the table
        Part part = prodDelTable.getSelectionModel().getSelectedItem();
        //put up an alert pop up window asking for confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to delete the part");
        //record which button was clicked
        Optional<ButtonType> x = alert.showAndWait();
        //if the OK button is clicked then go ahead and remove the part
        if (x.get() == ButtonType.OK){
            ourParts.remove(part);
            
        }
    }

    @FXML
    private void AddProdSaveButtonHandler(ActionEvent event) throws IOException{
        //map the contents of text boxes to variables
        String prodName = AddProdNameField.getText();
        String prodInventory = AddProdInvField.getText();
        String prodPrice = AddProdPriceField.getText();
        String prodMin = AddProdMinField.getText();
        String prodMax = AddProdMaxFieeld.getText();
        //try catch block to check for errors
        try {
            int prodInv=Integer.parseInt(prodInventory);
            double price=Double.parseDouble(prodPrice);
            int max=Integer.parseInt(prodMax);
            int min=Integer.parseInt(prodMin);
        
            errMsg = Product.prodCheck(prodName, prodInv, price, min, max, ourParts, errMsg);
            if (errMsg.length() > 0){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Bad Data Entry");
                alert.setHeaderText("Error Adding Product");
                alert.setContentText(errMsg);
                alert.showAndWait();
                //reset the errMsg variable for the next iteration
                errMsg = "";
            }
            else{
                Product newPrd = new Product();
                newPrd.setProductID(addModProdID);
                newPrd.setName(prodName);
                newPrd.setPrice(price);
                newPrd.setMin(min);
                newPrd.setMax(max);
                newPrd.addAssociatedPart((Part) ourParts);
                Inventory.addProduct(newPrd);
            }
            
        } catch(NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Number Error");
            alert.setContentText("Please enter a valid number.");
            alert.showAndWait();
        } 
        
        //put us back to the main screen
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
        //confirm that the user wants to exit the form
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to cancel?");
        Optional<ButtonType> x = alert.showAndWait();
        //if the user clicks ok then go ahead and load the main screen
        if (x.get() == ButtonType.OK) {
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
    
}
