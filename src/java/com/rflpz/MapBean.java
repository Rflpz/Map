/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rflpz;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import java.sql.*;
/**
 *
 * @author Rflpz
 */
@ManagedBean
@RequestScoped
public class MapBean {

    private String centerCoords = "6.920833, 103.578611";
    private final MapModel mapModel;
    public MapBean() {
        this.mapModel = new DefaultMapModel();
        
        LatLng coord1 = new LatLng(3.1357,101.6880);
        this.mapModel.addOverlay(new Marker(coord1, "Aeropuerto ",null,"http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));
        LatLng coord2 = new LatLng(19.277788, -103.727642);
        this.mapModel.addOverlay(new Marker(coord2, "Aeropuerto ",null,"http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));
    }
    public MapModel getMapModel(){
        return mapModel;
    }
    public String getCenterCoords(){
        return centerCoords;
    }
}
