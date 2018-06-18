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
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author miken
 */
public class MainScreenController implements Initializable {

    @FXML
    private TableColumn<?, ?> PartsPartIDCol;
    @FXML
    private TableColumn<?, ?> PartsPartNameCol;
    @FXML
    private TableColumn<?, ?> PartsInventoryCol;
    @FXML
    private TableColumn<?, ?> PartsPriceCol;
    @FXML
    private Button PartSearchButton;
    @FXML
    private Button PartModifyButton;
    @FXML
    private Button PartDeleteButton;
    @FXML
    private Button PartsAddButton;
    @FXML
    private TableColumn<?, ?> ProdProdIDCol;
    @FXML
    private TableColumn<?, ?> ProdNameCol;
    @FXML
    private TableColumn<?, ?> ProdInventoryCol;
    @FXML
    private TableColumn<?, ?> ProdPriceCol;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void PartSearchButtonHandler(ActionEvent event) {
    }

    @FXML
    private void PartModifyButtonHandler(ActionEvent event) {
    }

    @FXML
    private void PartDeleteButtonHandler(ActionEvent event) {
    }

    @FXML
    private void PartsAddButtonHandler(ActionEvent event) throws IOException{
        Stage stage; 
        Parent root;
        //get reference to the button's stage         
        stage=(Stage) PartsAddButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    

    @FXML
    private void MainExitHandler(ActionEvent event) {
    }

    @FXML
    private void ProdSearchButtonHandler(ActionEvent event) {
    }

    @FXML
    private void ProdModifyButtonHandler(ActionEvent event) {
    }

    @FXML
    private void ProdDeleteButtonHandler(ActionEvent event) {
    }

    @FXML
    private void ProdAddButtonHandler(ActionEvent event) {
    }
    
}
