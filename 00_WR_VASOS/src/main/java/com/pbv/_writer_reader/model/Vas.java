/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pbv._writer_reader.model;

import java.util.ArrayList;


/**
 *
 * @author pbv
 */
public class Vas {
    String ID;
    int maxItems;
    ArrayList<Dau> contingut;
    
    public Vas(String ID, int maxItems) {
        this.ID = ID;
        this.maxItems = maxItems;
        contingut = new ArrayList<>();
        if (maxItems>1){
            maxItems = 1;
        }
    }

    public String getID() {
        return ID;
    }

    public int getMaxItems() {
        return maxItems;
    }

    public ArrayList<Dau> getContingut() {
        return contingut;
    }
    
    
    
    public boolean ficarProducte(Dau p){
        if (contingut.size() >= maxItems){
            return false;
        } else {
            contingut.add(p);
            return true;
        }
    }  
}
