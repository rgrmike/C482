/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.javafx.collections.ObservableSequentialListWrapper;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author mian
 */
public class Product {
    
    private static ObservableList<Part> partList = observableArrayList();
    private final SimpleIntegerProperty ProductProductID=new SimpleIntegerProperty(0);
    private final SimpleStringProperty ProductName= new SimpleStringProperty("");
    private final SimpleDoubleProperty ProductPrice=new SimpleDoubleProperty(0.0);
    private final SimpleIntegerProperty ProductInStock=new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty ProductMin=new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty ProductMax=new SimpleIntegerProperty(0);
    //public Product(){
    //    this(partList,0,"",0.0,0,0,0);
    //}
    
}
