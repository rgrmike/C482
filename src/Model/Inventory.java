/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;


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
    
    public static boolean removeProduct(Product product){
        products.remove(product);
        //add if statement for product not found
        return true;
    }
    
    public static int lookupProduct(String product){
        //create a boolean to hold if we found the item
        boolean found = false;
        //initialize idx to hold our return value
        int idx = 0;
        //use try catch to determine if a value is numeric or string
        //if it is string then parsing as an int will fail and throw an exceptiong
        try{
            //check to see if we can convert an integer
            int itemNumber=Integer.parseInt(product);
            //if we can convert then loop through all the parts in product
            for(int i = 0; i < products.size(); i++){
                //if we find the product ID we want then set found to true and record the index
                if(products.get(i).getProductID() == itemNumber){
                    //debug message to show how the product was found
                    System.out.println("Inventory lookupProduct found: "+ itemNumber + " using Integer search");
                    found=true;
                    idx = i;
                }
            }
        }
        //if parsing the string to integer fails then catch the exception
        catch(NumberFormatException e){
            //loop through all of the parts
            for(int i = 0; i < products.size(); i++){
                //check to see if the part in all upper case matches the recorded part 
                //which is also converted to upper case
                product = product.toUpperCase();
                if(product.equals(products.get(i).getName().toUpperCase())){
                    //print debug output to show how we found a match
                    System.out.println("Inventory lookupProduct found: "+ product + " using Integer search");
                    //set the found to true
                    found=true;
                    //record the index location
                    idx = i;
                }
            }
        }
        //if we found the value return it
        if (found==true){    
            return idx;
        }
        else {
            //debug information that we did not find the answer
            System.out.println("Searched but Did not find any parts using Inventory lookup.");
            //return that this is not the secret to the universe
            return -42;
        }
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
    
    public static int lookupPart(String part){
        //create a boolean to hold if we found the item
        boolean found = false;
        //initialize idx to hold our return value
        int idx = 0;
        //use try catch to determine if a value is numeric or string
        //if it is string then parsing as an int will fail and throw an exceptiong
        try{
            //check to see if we can convert an integer
            int itemPartNumber=Integer.parseInt(part);
            //if we can convert then loop through all the parts in product
            for(int i = 0; i < allParts.size(); i++){
                //if we find the product ID we want then set found to true and record the index
                if(allParts.get(i).getPartID() == itemPartNumber){
                    //debug message to show how the product was found
                    System.out.println("Inventory lookupPart found: "+ itemPartNumber + " using Integer search");
                    found=true;
                    idx = i;
                }
            }
        }
        //if parsing the string to integer fails then catch the exception
        catch(NumberFormatException e){
            //loop through all of the parts
            for(int i = 0; i < allParts.size(); i++){
                //check to see if the part in all upper case matches the recorded part 
                //which is also converted to upper case
                part = part.toUpperCase();
                if(part.equals(allParts.get(i).getName().toUpperCase())){
                    //print debug output to show how we found a match
                    System.out.println("Inventory lookupPart found: "+ part + " using Integer search");
                    //set the found to true
                    found=true;
                    //record the index location
                    idx = i;
                }
            }
        }
        //if we found the value return it
        if (found==true){    
            return idx;
        }
        else {
            //debug information that we did not find the answer
            System.out.println("Searched but Did not find any parts using Part lookup.");
            //return that this is not the secret to the universe
            return -42;
        }
    }
    
    public static void updatePart(int partID, Part part){
        allParts.set(partID, part);
    }
    
    public static boolean isPartDelOK(Part thePart) {
        //initialize a variable to hold our result
        boolean partFinder = false;
        //iterate through the part list
        for (int i = 0; i < products.size(); i++){
            if (products.get(i).getProdParts().contains(thePart)){
                partFinder = true;
            }
        }
        return partFinder;
    }
    
    public static boolean isProdDelOK(Product theProduct) {
        //check to see if a product has at least one part - if it does don't delete it
        //initialize a var to hold the result of the check
        boolean prodFinder = false;
        int prodID = theProduct.getProductID();
        //iterate through the products in a 
        for (int i=0; i < products.size(); i++) {
            if (products.get(i).getProductID() == prodID) {
                if (products.get(i).getProdParts() != null) {
                    prodFinder = true;
                }
            }
        }
        return prodFinder;
    }
    
    
    
    
    
    

}
