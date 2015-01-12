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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Rflpz
 */
@ManagedBean
@RequestScoped
public class MapBean {

    private String centerCoords = "47.605424, -122.333994";
    private final MapModel mapModel;
    private List<Place> myListPlaces = new ArrayList<Place>();
    
    public MapBean() throws ClassNotFoundException {
        this.mapModel = new DefaultMapModel();
        String type = "";
        getData();
        for (int i = 0; i < this.myListPlaces.size(); i++) {
            Place placeMarker = this.myListPlaces.get(i);
            LatLng coord = new LatLng(placeMarker.getLat(), placeMarker.getLon());
            if("bar".equals(placeMarker.getType())){
                type = "http://maps.google.com/mapfiles/ms/micons/red-dot.png";
            }
            else{
                type = "http://maps.google.com/mapfiles/ms/micons/blue-dot.png";
            }
            this.mapModel.addOverlay(new Marker(coord, placeMarker.getAddress(), null, type));
        }

    }
    private void getData() throws ClassNotFoundException {
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/map", "root", "");
            Statement st = con.createStatement();
            String sql = ("SELECT * FROM `markers`");
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Place place = new Place();
                place.setName(rs.getString("name"));
                place.setAddress(rs.getString("address"));
                place.setLat(rs.getFloat("lat"));
                place.setLon(rs.getFloat("lng"));
                place.setType(rs.getString("type"));
                this.myListPlaces.add(place);
            }
            System.out.println(this.myListPlaces.size());
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        } catch (InstantiationException ex) {
            Logger.getLogger(MapBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MapBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public MapModel getMapModel(){
        return mapModel;
    }
    public String getCenterCoords(){
        return centerCoords;
    }
}
