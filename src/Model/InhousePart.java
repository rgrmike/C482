/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author mian
 */
public class InhousePart extends Part {
    
    private final SimpleIntegerProperty PartMachineID= new SimpleIntegerProperty(0);
    public InhousePart(){
        this(0);
    }

    public InhousePart(int machineID) {
        setMachineID(machineID);
    }
    
    public int getMachineID(){
        return PartMachineID.get();
    }
    
    public void setMachineID(int machineID) {
        PartMachineID.set(machineID);
    } 
}
