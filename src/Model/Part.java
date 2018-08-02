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
abstract class Part {
    private final SimpleIntegerProperty PartID= new SimpleIntegerProperty(0);
    private final SimpleStringProperty PartName=new SimpleStringProperty("");
    private final SimpleIntegerProperty PartInv= new SimpleIntegerProperty(0);
    private final SimpleDoubleProperty Cost=new SimpleDoubleProperty(0.0);
    public Part(){
        this(0,"",0,0.0);
    }
    public Part(int partID, String partName, int partInv,double cost){
        setPartID(partID);
        setPartName(partName);
        setPartInv(partInv);
        setCost(cost);
    }
    public int getPartID(){
        return PartID.get();
    }
    public String getPartName(){
        return PartName.get();
    }
    public String getPartInv(){
        return PartName.get();
    }
    public double getCost(){ 
        return Cost.get();
    }
    public void setPartID(int partID){
        PartID.set(partID);
    }
    public void setPartName(String partName){
        PartName.set(partName);
    }
    public void setPartInv(int partInv){
        PartInv.set(partInv);
    } 
    public void setCost(double cost){
        Cost.set(cost);
    }
    
}
