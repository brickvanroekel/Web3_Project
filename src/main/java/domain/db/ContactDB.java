package domain.db;

import domain.model.Contact;
import domain.model.ContactTracingService;
import domain.model.Person;
import domain.model.Reservation;
import util.DbConnectionService;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
        String sqlString = String.format("INSERT INTO %s.contacts (firstName,lastName,date,hour,gsm,email) VALUES (?,?,?,?,?,?)", this.schema);

        try {
            PreparedStatement sqlStatement = connection.prepareStatement(sqlString);
            sqlStatement.setString(1,contact.getFirstName());
            sqlStatement.setString(2,contact.getLastName());
            sqlStatement.setDate(3,Date.valueOf(contact.getDate()));
            sqlStatement.setTime(4,Time.valueOf(contact.getHour()));
            sqlStatement.setString(5,contact.getGsm());
            sqlStatement.setString(6,contact.getEmail());
            sqlStatement.execute();

        }catch (SQLException exception){
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
                String date = resultSet.getDate("date").toString();
                String hour = resultSet.getTime("hour").toString();
                String gsm = resultSet.getString("gsm");
                String email = resultSet.getString("email");

                Contact contact = new Contact(firstName,lastName,date,hour,gsm,email);
                contacts.add(contact);
            }
        }catch (SQLException exception){
            throw new DbException(exception.getMessage());
        }
        return contacts;

    }


}
