package domain.db;

import domain.model.Contact;
import domain.service.ContactTracingService;
import util.DbConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class ContactDB {
    private Connection connection;
    private String schema;

    public ContactDB(){
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
    }

    public void add(Contact contact){
        if(contact==null)
            throw new DbException("Give valid contact");
        String sqlString = String.format("INSERT INTO %s.contacts (contact,firstName,lastName,gsm,email,timestamp) VALUES (?,?,?,?,?,?)", this.schema);

        try {
            PreparedStatement sqlStatement = connection.prepareStatement(sqlString);
            sqlStatement.setString(1,contact.getContact());
            sqlStatement.setString(2,contact.getFirstName());
            sqlStatement.setString(3,contact.getLastName());
            sqlStatement.setString(4,contact.getGsm());
            sqlStatement.setString(5,contact.getEmail());
            sqlStatement.setTimestamp(6,contact.getTimestamp());
            sqlStatement.execute();
        }catch (SQLException exception){

            throw new DbException(exception.getMessage());
        }
    }

    public ArrayList<Contact> getAllContacts(){
        ArrayList<Contact> contacts = new ArrayList<>();
        String sqlString = String.format("SELECT * FROM %s.contacts",this.schema);

        try {
            PreparedStatement sqlStatement = connection.prepareStatement(sqlString);
            ResultSet resultSet = sqlStatement.executeQuery();
            while (resultSet.next()){
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                Timestamp timestamp = resultSet.getTimestamp("timestamp");
                String gsm = resultSet.getString("gsm");
                String email = resultSet.getString("email");
                String contact = resultSet.getString("contact");

                Contact c = new Contact(contact,firstName,lastName,gsm,email,timestamp);
                contacts.add(c);
            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
            throw new DbException(exception.getMessage());
        }
        contacts.sort(Comparator.comparing(o -> o.getTimestamp().toLocalDateTime()));
        return contacts;

    }

    public ArrayList<Contact> getContactsByUserId(String userid){
        ArrayList<Contact> contacts = new ArrayList<>();
        String sqlString = String.format("SELECT * FROM %s.contacts WHERE contact = ?",this.schema);

        try {
            PreparedStatement sqlStatement = connection.prepareStatement(sqlString);
            sqlStatement.setString(1,userid);
            ResultSet resultSet = sqlStatement.executeQuery();
            while (resultSet.next()){
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                Timestamp timestamp = resultSet.getTimestamp("timestamp");
                String gsm = resultSet.getString("gsm");
                String email = resultSet.getString("email");
                String contact = resultSet.getString("contact");

                Contact c = new Contact(contact,firstName,lastName,gsm,email,timestamp);
                contacts.add(c);
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        contacts.sort(Comparator.comparing(o -> o.getTimestamp().toLocalDateTime()));
        return contacts;
    }




}
