/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.*;

/**
 *
 * @author mian
 */
public abstract class Part {
    private final SimpleIntegerProperty PartID=new SimpleIntegerProperty(0);
    private final SimpleStringProperty PartName=new SimpleStringProperty("");
    private final SimpleDoubleProperty PartPrice=new SimpleDoubleProperty(0.0);
    private final SimpleIntegerProperty PartInStock=new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty PartMin=new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty PartMax=new SimpleIntegerProperty(0);
    public Part(){
        this(0,"",0.0,0,0,0);
    }
    public Part(int partID, String partName, double price, int inStock, int min, int max){
        setPartID(partID);
        setName(partName);
        setPrice(price);
        setInStock(inStock);
        setMin(min);
        setMax(max);
    }
    public int getPartID(){
        return PartID.get();
    }
    public String getName(){
        return PartName.get();
    }
    public double getPrice(){ 
        return PartPrice.get();
    }
    public int getInStock(){
        return PartInStock.get();
    }
    public int getMin(){
        return PartMin.get();
    }
    public int getMax(){
        return PartMax.get();
    }
    public void setPartID(int partID){
        PartID.set(partID);
    }
    public void setName(String partName){
        PartName.set(partName);
    }
    public void setInStock(int inStock){
        PartInStock.set(inStock);
    } 
    public void setPrice(double price){
        PartPrice.set(price);
    }
    public void setMin(int min){
        PartMin.set(min);
    }
    public void setMax(int max){
        PartMax.set(max);
    }
    
    //Check the part input and return what needs to be fixed
    public static String partCheck(String name, double price, int inStock, int min, int max, String errMsg){
        
        if (name == null){
            errMsg = errMsg + " Name cannot be blank. ";
        }
        if (inStock < 1){
            errMsg = errMsg + " Inventory cannot be less than 1. ";
        }
        if (price <= 0){
            errMsg = errMsg + " Parts aren't free. Put in a price greater than 0, cheapskate!";
        }
        if (max < min){
            errMsg = errMsg + " Max must be more than Min. ";
        }
        if (inStock < min){
            errMsg = errMsg + " Inventory can't be less than min. ";
        }
        if (inStock > max){
            errMsg = errMsg + " Inventory can't be more than max. ";
        }
        return errMsg;
        
    }
}
