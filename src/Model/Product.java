/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
/**
 *
 * @author mian
 */
public class Product {
    
    private static ArrayList<Part> associatedParts = new ArrayList<Part>();
    private final SimpleIntegerProperty ProductProductID=new SimpleIntegerProperty(0);
    private final SimpleStringProperty ProductName= new SimpleStringProperty("");
    private final SimpleDoubleProperty ProductPrice=new SimpleDoubleProperty(0.0);
    private final SimpleIntegerProperty ProductInStock=new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty ProductMin=new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty ProductMax=new SimpleIntegerProperty(0);
   
    
    public Product(){
        this(0,"",0.0,0,0,0);        
    }
    
    public Product(int productID, String productName, double price, int inStock, int min, int max){
        setProductID(productID);
        setName(productName);
        setPrice(price);
        setInStock(inStock);
        setMin(min);
        setMax(max);
    }
    //Creating return objects for the table within main so it does not throw an error about
    //int cannot be derefrenced 
    public IntegerProperty prodIDProp(){
        return ProductProductID;
    }
    public StringProperty prodNameProp(){
        return ProductName;
    }
    public DoubleProperty prodPriceProp(){
        return ProductPrice;
    }
    public IntegerProperty prodInStockProp(){
        return ProductInStock;
    }
    public IntegerProperty prodMinProp(){
        return ProductMin;
    }
    public IntegerProperty prodMaxProp(){
        return ProductMax;
    }
    // Getters
    public int getProductID(){
        return ProductProductID.get();
    }
    public String getName(){
        return ProductName.get();
    }
    public double getPrice(){
        return ProductPrice.get();
    }
    public int getInStock(){
        return ProductInStock.get();
    }
    public int getMin(){
        return ProductMin.get();
    }
    public int getMax(){
        return ProductMax.get();
    } 
    
    // Setters
    public void setProductID(int productID) {
        ProductProductID.set(productID);
    }
    public void setName(String partName){
        ProductName.set(partName);
    }
    public void setPrice(double price) {
        ProductPrice.set(price);
    }
    public void setInStock(int inStock) {
        ProductInStock.set(inStock);
    }
    public void setMin(int min) {
        ProductMin.set(min);
    }
    public void setMax(int max) {
        ProductMax.set(max);
    }
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }
    public boolean removeAssociatedPart(int remPart){
        associatedParts.remove(remPart);
        return true;
    }
    public Part lookupAssociatedPart(int lookPart){
       //lookup the part
        return associatedParts.get(lookPart);
    }
    //form input validation
    public static String prodCheck(String name, int inventory, double price, int max, int min, ObservableList<Part> bagOParts, String errMsg) {
        double partsSum = 0.00;
        //check to make sure the price of the product is greater than the sum of the parts
        for (int i = 0; i < bagOParts.size(); i++) {
            partsSum = partsSum + bagOParts.get(i).getPrice();
        }
                if (name == null){
            errMsg = errMsg + " Name cannot be blank. ";
        }
        if (inventory < 1){
            errMsg = errMsg + " Inventory cannot be less than 1. ";
        }
        if (price <= 0){
            errMsg = errMsg + " Parts aren't free. Put in a price greater than 0, cheapskate!";
        }
        if (max < min){
            errMsg = errMsg + " Max must be more than Min. ";
        }
        if (inventory < min){
            errMsg = errMsg + " Inventory can't be less than min. ";
        }
        if (inventory > max){
            errMsg = errMsg + " Inventory can't be more than max. ";
        }
        if (partsSum > price) {
            errMsg = errMsg + " The product price must be more than the sum of all the parts. ";
        }
        
        return errMsg; 
    }

 

}
   