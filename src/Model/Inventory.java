/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author mian
 */
public class Inventory {
    
    private static ObservableList<Product> products = FXCollections.observableArrayList();
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    //initialize part and product counters
    private static int partCounter = 0;
    private static int productCounter = 0;
 
    //returns the entire parts list so that we can populate the tables
    public static ObservableList<Part> getPartInv() {
        return allParts;
    }
    //returns the entire product list so the tables can be populated
    public static ObservableList<Product> getProdInv() {
        return products;
    }
    //automatically assign an index to each part - like a db index
    public static int getPartCoutner() {
        partCounter++;
        return partCounter;
    }
    //automaticall assign an index number to each product
    public static int getProductCoutner() {
        productCounter++;
        return productCounter;
    }
    
    public static void addProduct(Product product){
        products.add(product);
    }
    
    public static boolean removeProduct(int product){
        products.remove(product);
        //add if statement for product not found
        return true;
    }
    
    public static int lookupProduct(int product){
        //stearch later - the uml calls this a product???
        return product;
    }
    
    public static void updateProduct(int prodID, Product product){
        products.set(prodID, product);
    }
    
    public static void addPart(Part part){
        allParts.add(part);
        //debugging information to make sure parts were added when called
        System.out.println("Added Part: " + part.getName() + " to index");
        System.out.println(part);
    }
    
    public static boolean deletePart(Part part){
        allParts.remove(part);
        //add if then statement if the part can't be found 
        return true;
    }
    
    public static int lookupPart(int part){
        //search
        return part;
    }
    
    public static void updatePart(int partID, Part part){
        allParts.set(partID, part);
    }
    

    
    
    
    
    
    

}
