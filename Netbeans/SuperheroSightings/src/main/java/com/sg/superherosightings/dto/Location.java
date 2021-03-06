/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dto;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author josesosa
 */
public class Location {
    
    int locationID;
    
    @NotBlank(message = "Damn Daniel you forgot the Name?")
    @Length(max = 100, message = "No less than 1 and no more than 50 characters in length.")
    String locationName;
            
    @NotEmpty(message = "Damn Daniel you forgot the Description?")
    @Length(min = 1, max = 100, message = "No less than 1 and no more than 50 characters in length.")
    String description;
            
    Double longitude;
    
    Double latitude;
    
    String streetNumber;
    @NotBlank(message = "Damn Daniel you forgot the Name?")
    @Length(max = 100, message = "No less than 1 and no more than 50 characters in length.")
            
    String streetName;
    @NotBlank(message = "Damn Daniel you forgot the Street Name?")
    @Length(max = 50, message = "No less than 1 and no more than 50 characters in length..")
            
    String city;
    @NotBlank(message = "Damn Daniel you forgot the City?")
    @Length(max = 100, message = "No less than 1 and no more than 20 characters in length..")
            
    String state;
    @NotBlank(message = "Damn Daniel you forgot the State?")
    @Length(max = 100, message = "First Name must be no more than 50 characters in length.")
            
    String zip;
    @NotBlank(message = "Damn Daniel you forgot the Zip?")
    @Length(min = 1, max = 100, message = "Zip code must be no more than 50 characters in length.")

    public Location() {
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + this.locationID;
        hash = 43 * hash + Objects.hashCode(this.locationName);
        hash = 43 * hash + Objects.hashCode(this.description);
        hash = 43 * hash + Objects.hashCode(this.longitude);
        hash = 43 * hash + Objects.hashCode(this.latitude);
        hash = 43 * hash + Objects.hashCode(this.streetNumber);
        hash = 43 * hash + Objects.hashCode(this.streetName);
        hash = 43 * hash + Objects.hashCode(this.city);
        hash = 43 * hash + Objects.hashCode(this.state);
        hash = 43 * hash + Objects.hashCode(this.zip);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        if (this.locationID != other.locationID) {
            return false;
        }
        if (!Objects.equals(this.locationName, other.locationName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.streetNumber, other.streetNumber)) {
            return false;
        }
        if (!Objects.equals(this.streetName, other.streetName)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.zip, other.zip)) {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }
        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
