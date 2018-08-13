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
 
    
    public static ObservableList<Part> getPartInv() {
        return allParts;
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
