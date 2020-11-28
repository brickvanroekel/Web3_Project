package domain.db;

import domain.model.Contact;
import domain.service.ContactTracingService;
import util.DbConnectionService;

import java.sql.*;
import java.util.ArrayList;


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
        String sqlString = String.format("INSERT INTO %s.contacts (firstName,lastName,gsm,email,timestamp) VALUES (?,?,?,?,?)", this.schema);

        try {
            PreparedStatement sqlStatement = connection.prepareStatement(sqlString);
            sqlStatement.setString(1,contact.getFirstName());
            sqlStatement.setString(2,contact.getLastName());
            sqlStatement.setString(3,contact.getGsm());
            sqlStatement.setString(4,contact.getEmail());
            sqlStatement.setTimestamp(5,contact.getTimestamp());
            sqlStatement.execute();

        }catch (SQLException exception){
            System.out.println(exception.getMessage());
            throw new DbException(exception.getMessage());
        }
    }

    public ArrayList<Contact> getAllContacts(ContactTracingService contactTracingService){
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

                Contact contact = new Contact(firstName,lastName,gsm,email,timestamp);
                contacts.add(contact);
            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
            throw new DbException(exception.getMessage());
        }
        return contacts;

    }


}
