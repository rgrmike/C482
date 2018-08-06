/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author mian
 */
public class OutsourcedPart extends Part {
    
    private final SimpleStringProperty PartCompanyName= new SimpleStringProperty("");
    public OutsourcedPart(){
        this("");
    }

    public OutsourcedPart(String companyName) {
        setCompanyName(companyName);
    }
    
    public String getCompanyName(){
        return PartCompanyName.get();
    }
    
    public void setCompanyName(String companyName) {
        PartCompanyName.set(companyName);
    } 
}
