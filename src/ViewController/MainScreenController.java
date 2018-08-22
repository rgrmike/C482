/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import Model.Part;
import Model.Product;
import Model.InhousePart;
import Model.OutsourcedPart;
import Model.Inventory;
import static Model.Inventory.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Optional;

/**
 * FXML Controller class
 *
 * @author miken
 */
public class MainScreenController implements Initializable {
    public static int addmodpart;
    public static int addmodprod;
    @FXML
    private TableView<Part> partTblView;
    @FXML
    private TableColumn<Part, Integer> PartsPartIDCol;
    @FXML
    private TableColumn<Part, String> PartsPartNameCol;
    @FXML
    private TableColumn<Part, Integer> PartsInventoryCol;
    @FXML
    private TableColumn<Part, Double> PartsPriceCol;
    @FXML
    private Button PartSearchButton;
    @FXML
    private Button PartModifyButton;
    @FXML
    private Button PartDeleteButton;
    @FXML
    private Button PartsAddButton;
    @FXML
    private TableView<Product> prodTblView;
    @FXML
    private TableColumn<Product, Integer> ProdProdIDCol;
    @FXML
    private TableColumn<Product, String> ProdNameCol;
    @FXML
    private TableColumn<Product, Integer> ProdInventoryCol;
    @FXML
    private TableColumn<Product, Double> ProdPriceCol;
    @FXML
    private Button MainExit;
    @FXML
    private Button ProdSearchButton;
    @FXML
    private Button ProdModifyButton;
    @FXML
    private Button ProdDeleteButton;
    @FXML
    private Button ProdAddButton;
    
    public void refreshPartsTbl(){
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //using makery style connectors
        PartsPartIDCol.setCellValueFactory(cellData -> cellData.getValue().pIDProp().asObject());
        PartsPartNameCol.setCellValueFactory(cellData -> cellData.getValue().partNameProp());
        PartsInventoryCol.setCellValueFactory(cellData -> cellData.getValue().partInStockProp().asObject());
        PartsPriceCol.setCellValueFactory(cellData -> cellData.getValue().partPriceProp().asObject());
        ProdProdIDCol.setCellValueFactory(cellData -> cellData.getValue().prodIDProp().asObject());
        ProdNameCol.setCellValueFactory(cellData -> cellData.getValue().prodNameProp());
        ProdInventoryCol.setCellValueFactory(cellData -> cellData.getValue().prodInStockProp().asObject());
        ProdPriceCol.setCellValueFactory(cellData -> cellData.getValue().prodPriceProp().asObject());
        //update the parts table when the form loads
        updatePartsTbl();
        //update the products table when form loads
        updateProdTbl();
    }    

    //grab all the parts that are stored in inventory so we can populate the table
    public void updatePartsTbl(){
        partTblView.setItems(getPartInv());
    }
    //grab products from inventory
    public void updateProdTbl() {
        prodTblView.setItems(getProdInv());
    }
    
    @FXML
    private void PartSearchButtonHandler(ActionEvent event) {
    }

    @FXML
    private void PartModifyButtonHandler(ActionEvent event) throws IOException{
        //addmodpart 2 sets the form to modify
        addmodpart=2;
        Stage stage; 
        Parent root;
        //get reference to the button's stage         
        stage=(Stage) PartsAddButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void PartDeleteButtonHandler(ActionEvent event) {
        //grab the selected part
        Part removePart = partTblView.getSelectionModel().getSelectedItem();
        if(isPartDelOK(removePart) == true) {
            //set the alert type as informational and tell the user why - then wait to be clicked
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Part cannot be deleted!");
            alert.setContentText("This part is part of at least one product. Remove from products before deleting.");
            alert.showAndWait();
        }
        //if the part isn't used then ask for confirmation and delete the part
        else {
            //put up an alert pop up window asking for confirmation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to delete the selected part?");
            //record which button was clicked
            Optional<ButtonType> x = alert.showAndWait();
            //if the OK button is clicked then go ahead and remove the part
            if (x.get() == ButtonType.OK){
                //remove the product from Products
                deletePart(removePart);
                //update the local table to reflect the change
                updatePartsTbl();
            }
        }
    }
    
    @FXML
    private void ProdDeleteButtonHandler(ActionEvent event) {
        //grab the selected part
        Product removeProd = prodTblView.getSelectionModel().getSelectedItem();
        //check to see if the product contains at least one part - if it does we won't delete it 
        if (isProdDelOK(removeProd) == true) {
            //set the alert type as informational and tell the user why - then wait to be clicked
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Product cannot be deleted!");
            alert.setContentText("Product has at least one part - remove all parts before deleting.");
            alert.showAndWait();
        }
        //if the product does not have any parts than confirm that it will be deleted
        else{
            //put up an alert pop up window asking for confirmation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to delete the selected product?");
            //record which button was clicked
            Optional<ButtonType> x = alert.showAndWait();
            //if the OK button is clicked then go ahead and remove the part
            if (x.get() == ButtonType.OK){
                //remove the product from Products
                removeProduct(removeProd);
                //update the local table to reflect the change
                updateProdTbl();
            }
        }
    }

    @FXML
    private void PartsAddButtonHandler(ActionEvent event) throws IOException{
        //addmodpart 1 sets the form to add
        addmodpart=1;
        Stage stage; 
        Parent root;
        //get reference to the button's stage         
        stage=(Stage) PartsAddButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    

    @FXML
    private void MainExitHandler(ActionEvent event) {
        //put up an alert pop up window asking for confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to exit?");
        //record which button was clicked
        Optional<ButtonType> x = alert.showAndWait();
        //if the OK button is clicked then go ahead and remove the part
        if (x.get() == ButtonType.OK){
            System.exit(0);
            
        }
        
    }

    @FXML
    private void ProdSearchButtonHandler(ActionEvent event) {
    }

    @FXML
    private void ProdModifyButtonHandler(ActionEvent event) throws IOException{
        //addmodpart 2 sets the form to modify
        addmodprod=2;
        Stage stage; 
        Parent root;
        //get reference to the button's stage         
        stage=(Stage) PartsAddButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ProdAddButtonHandler(ActionEvent event) throws IOException{
        //addmodpart 1 sets the form to add
        addmodprod=1;
        Stage stage; 
        Parent root;
        //get reference to the button's stage         
        stage=(Stage) PartsAddButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
