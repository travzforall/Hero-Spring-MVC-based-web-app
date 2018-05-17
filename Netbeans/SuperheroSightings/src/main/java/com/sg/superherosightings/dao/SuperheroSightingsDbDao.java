/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Characters;
import com.sg.superherosightings.dto.Location;
import com.sg.superherosightings.dto.Organization;
import com.sg.superherosightings.dto.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @sighting josesosa
 */
public class SuperheroSightingsDbDao implements SuperheroSightingsDao {
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    // Prepared statements for MySql, basically the query that will be run in MySql throught the Spring, JDBC hocus pocus
    
    // Sighting prepared statements
    private static final String SQL_INSERT_SIGHTING
    = "insert into Sightings ( LocationID, CharacterID, SightingDate" +  "values (?, ?, ?)";

    private static final String SQL_DELETE_SIGHTING
        = "delete from Sightings where SightingID = ?";

    private static final String SQL_UPDATE_SIGHTING
        = "update Sightings set LocationId = ?, CharacterID = ? " + "where SightingID =  ?";

    private static final String SQL_SELECT_SIGHTING
        = "select * from Sightings where SightingID = ?";

    private static final String SQL_SELECT_SIGHTING_BY_SIGHTINGDATE =
            "select * from Sightings where SightingDate = ?"; 

    private static final String SQL_SELECT_ALL_SIGHTINGS
        = "select * from Sightings";
    
    private static final String SQL_SELECT_LAST_TEN_SIGHTINGS
        = "select * FROM Sighting ORDER BY SightingID DESC LIMIT 10 ";
    
    // Character prepared statements
    private static final String SQL_INSERT_CHARACTER
    = "insert into Characters (Name, Description, IsSuperHero " + " values (?, ?, ?)";

    private static final String SQL_DELETE_CHARACTER
        = "delete from Characters where CharacterID = ?";

    private static final String SQL_UPDATE_CHARACTER
        = "update Characters set Name = ?, Description = ?, IsSuperHero = ?, "+ " where CharacterID =  ?";

    private static final String SQL_SELECT_CHARACTER
        = "select * from Characters where CharacterId = ?";

    private static final String SQL_SELECT_CHARACTERS_BY_ORGANIZATION
        = ""; // revisit after checking out join statements;

    private static final String SQL_SELECT_ALL_CHARACTERS
        = "select * from Characters";
    
    //Organizations prepared statements
    private static final String SQL_INSERT_ORGANIZATION
    = "insert into Organization (OrganizationName, LocationID " + " values (?, ?)";

    private static final String SQL_DELETE_ORGANIZATION
        = "delete from Organization where OrganizationID = ?";

    private static final String SQL_UPDATE_ORGANIZATION
        = "update Organization set OrganizationName = ?, LocationID = ?" + "where OrganizationID = ?";

    private static final String SQL_SELECT_ORGANIZATION
        = "select * from Organiztion where OrganizationId = ?";

    private static final String SQL_SELECT_ORGANIZATIONS_BY_CHARACTER
        = ""; // revisit after checking out join statements;

    private static final String SQL_SELECT_ALL_ORGANIZATIONS
        = "select * from Organization";
    
    //Locations prepared statements
    private static final String SQL_INSERT_LOCATION
    = "insert into Location (Name, Description, Latitude, Longitude, StreetNumber, StreetName, City, State, ZipCode " + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_LOCATION
        = "delete from Location where LocationID = ?";

    private static final String SQL_UPDATE_LOCATION
        = "update Location set LocationName = ?, Description = ?, Latitude = ?, Longitude = ?, StreetNumber = ?, StreetName = ?, City = ?, State = ?, ZipCode = ?  " + "where LocationID = ?";

    private static final String SQL_SELECT_LOCATION
        = "select * from Location where LocationId = ?";

    private static final String SQL_SELECT_LOCATION_BY_CHARACTER
        = ""; // revisit after checking out join statements;

    private static final String SQL_SELECT_ALL_LOCATIONS
        = "select * from Location";

    
//    public List<Sighting> getLastTen(){
//        temp = code that gets last ten sightings;
//        for(Sighting sight: temp ){
//            charTemp = getCharacter(temp.CharacterID);
//            temp.setCharacter(charTemp);
//        }
//    }

    /**********************SIGHTING***************************/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Sighting addSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sighting.getCharacterId(),
                sighting.getLocationId(),
                sighting.getSightingDate(),
                sighting.getSightingId());
        
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        
        sighting.setSightingId(newId);
        return sighting;
        
    }

    @Override
    public void deleteSighting(int sightingId) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingId);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                sighting.getCharacterId(),
                sighting.getLocationId(),
                sighting.getSightingDate(),
                sighting.getSightingId());
    }

    @Override
    public Sighting getSightingById(int sightingId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING,
                    new SightingMapper(), sightingId);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given contact id - we just 
            // want to return null in this case
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightings() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS, 
                                  new SightingMapper());  
    }
    
    public List<Sighting> getLastTenSightings() {
        return jdbcTemplate.query(SQL_SELECT_LAST_TEN_SIGHTINGS, 
                                  new SightingMapper());  
    }

    /**********************CHARACTERS***************************/

    
    @Override
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false)
    public Characters addCharacter(Characters character) {
        jdbcTemplate.update (SQL_INSERT_CHARACTER,
                character.getName(),
                character.getDescription(),
                character.getIsSuperHero());
        
        /*
        The above creates the character
        
        The below queries the db for the id that was just assigned
        to the new row
        */
        
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        
        //This sets the new id value on the Characters object and returns it
        character.setCharacterId(newId);
        return character;
    }

    @Override
    public void deleteCharacter(int characterId) {
        jdbcTemplate.update (SQL_DELETE_CHARACTER, characterId);
    }

    @Override
    public void updateCharacter(Characters character) {
        jdbcTemplate.update (SQL_UPDATE_CHARACTER,
                character.getName(),
                character.getDescription(),
                character.getIsSuperHero(),
                character.getCharacterId());
    }

    @Override
    public Characters getCharacterById(int characterId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_CHARACTER,
                    new CharactersMapper(), characterId);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given contact id - we just 
            // want to return null in this case
            return null;
        }
    }

    @Override
    public List<Characters> getCharactersBySightingId(int sightingId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Characters> getCharactersByOrganizationId(int organizationId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Characters> getAllCharacters() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CHARACTERS, 
                                  new CharactersMapper()); 
    }
    
    /**********************ORGANIZATION***************************/


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization addOrganization(Organization organization) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
        organization.getOrganizationName(),
        organization.getLocation());
        
        /*
        Query the database for the ID that was just assigned to the new row in the DB
        */
        
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        
        //Set the new id value on the Organization object and return it
        organization.setOrganizationId(newId);
        return organization;
    }
    
    @Override
    public void deleteOrganization(int organizationId) {
        jdbcTemplate.update (SQL_DELETE_ORGANIZATION, organizationId);
    }
    
    @Override
    public void updateOrganization(Organization organization) {
        jdbcTemplate.update (SQL_UPDATE_ORGANIZATION,
                organization.getOrganizationName(),
                organization.getLocation(),
                organization.getOrganizationId());
    }

    @Override
    public Organization getOrganizationById(int organizationId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION, new OrganizationMapper(), organizationId);
        } catch (EmptyResultDataAccessException ex) {
            
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());
    }
    
    /**********************LOCATION***************************/

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)

    public Location addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION, 
                location.getCity(),
                location.getDescription(),
                location.getLatitude(),
                location.getLocationID(),
                location.getLocationName(),
                location.getLongitude(),
                location.getState(),
                location.getStreetName(),
                location.getStreetNumber(),
                location.getZip());
        
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID", Integer.class);
        
        location.setLocationID(newId);
        
        return location;
        
    }

    @Override
    public void deleteLocation(int locationId) {
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationId);
    }

    @Override
    public void updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION, 
                location.getCity(),
                location.getDescription(),
                location.getLatitude(),
                location.getLocationID(),
                location.getLocationName(),
                location.getLongitude(),
                location.getState(),
                location.getStreetName(),
                location.getStreetNumber());
    }

    @Override
    public Location getLocationById(int locationId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION,
                    new LocationMapper(), locationId);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given contact id - we just 
            // want to return null in this case
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS, 
                                  new LocationMapper()); 
    }
    
    // HELPER METHODS???
    
    public List<Location> getAssociatedLocations(List<Sighting> temp){
        List<Location> helperLocationList = new ArrayList<Location>();
        
        for(Sighting sight: temp){
            Location local = getLocationById(sight.getLocationId());
            helperLocationList.add(local);
        }
        return helperLocationList;
    }
    
    public List<Characters> getAssociatedCharacters(List<Sighting> temp){
        List<Characters> helperCharacterList = new ArrayList<Characters>();
        
        for(Sighting sight: temp){
            Characters charact = getCharacterById(sight.getCharacterId());
            helperCharacterList.add(charact);
        }
        return helperCharacterList;
    }
    
    
        /**********************Mapper Below***************************/

    
    private static final class OrganizationMapper implements RowMapper<Organization>{
        public Organization mapRow (ResultSet rs, int rowNum) throws SQLException{
            Organization organization = new Organization();
            organization.setOrganizationName(rs.getString ("OrganizationName"));
            organization.setOrganizationId(rs.getInt("OrganizationId"));
            
            
            /*
            Mo - How do i set values for composition - lookups to other objects also Lists?
            
            Location location;
            List<Characters> memberList;
            */
            
        return organization;

            
        }
    }
    
    
    
    private static final class SightingMapper implements RowMapper<Sighting> {

        public Sighting mapRow(ResultSet rs, int rowNum) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingId(rs.getInt("SightingID"));
            sighting.setLocationId(rs.getInt("LocationID"));
            sighting.setCharacterId(rs.getInt("CharacterID"));
            sighting.setSightingDate((rs.getTimestamp("SightingDate")).toLocalDateTime().toLocalDate());
            
            return sighting;
        }
    }
    
    private static final class CharactersMapper implements RowMapper<Characters> {

        public Characters mapRow(ResultSet rs, int rowNum) throws SQLException {
            Characters charact = new Characters();
            charact.setCharacterId(rs.getInt("CharacterID"));
            charact.setName(rs.getString("CharacterName"));
            charact.setDescription(rs.getString("Description"));
            charact.setIsSuperHero(rs.getBoolean("IsSuperHero"));
            
            return charact;
            
   
        }
    }
    
    private static final class LocationMapper implements RowMapper<Location> {

        public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
            Location local = new Location();
            local.setLocationID(rs.getInt("LocationID"));
            local.setLocationName(rs.getString("LocationName"));
            local.setDescription(rs.getString("Description"));
            local.setLatitude(rs.getDouble("Latitude"));
            local.setLongitude(rs.getDouble("Longitude"));
            local.setStreetNumber(rs.getString("StreetNumber"));
            local.setStreetName(rs.getString("StreetName"));
            local.setCity(rs.getString("City"));
            local.setState(rs.getString("State"));
            local.setZip(rs.getString("ZipCode"));
           
            return local;
        
        }
    }

}
