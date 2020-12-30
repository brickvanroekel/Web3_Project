package domain.db;

import domain.model.Person;
import util.DbConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDB {
    private Connection connection;
    private String schema;

    public PersonDB(){
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
    }


    public void add(Person person){
        if(person==null){
            throw new DbException("Give a valid person");
        }
        String sqlString= String.format("INSERT INTO %s.users (userid,firstName, lastName, email, password, role) VALUES (?,?,?,?,?,?)",this.schema);

        try {
            PreparedStatement sqlstatement = connection.prepareStatement(sqlString);
            sqlstatement.setString(1,person.getUserid());
            sqlstatement.setString(2,person.getFirstName());
            sqlstatement.setString(3,person.getLastName());
            sqlstatement.setString(4,person.getEmail());
            sqlstatement.setString(5,person.getPassword());
            sqlstatement.setString(6, person.getRoleString());
            sqlstatement.execute();
        }catch (SQLException exception){
            throw new DbException(exception.getMessage());
        }
    }

    public ArrayList<Person> getAllPersons(){
        ArrayList<Person> people = new ArrayList<>();
        String sqlString = String.format("SELECT * FROM %s.users",this.schema);

        try {
            PreparedStatement sqlStatement = connection.prepareStatement(sqlString);
            ResultSet result = sqlStatement.executeQuery();
            while (result.next()){
                String userid = result.getString("userid");
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                String email = result.getString("email");
                String password = result.getString("password");
                String role = result.getString("role");
                Person person = new Person(userid,email,password,firstName,lastName, role);
                people.add(person);
            }
        }catch (SQLException exception){
            throw new DbException(exception.getMessage());
        }
        return people;
    }

    public void updatePerson(Person person){
        String sqlString = String.format("UPDATE %s.users SET email ='"+person.getEmail()+"', password ='"+person.getPassword()+"',firstName ='"+person.getFirstName()+"',lastName ='"+person.getLastName()+"' WHERE userid='"+person.getUserid()+"'",this.schema);
        try {
            PreparedStatement sqlStatement = connection.prepareStatement(sqlString);
            sqlStatement.execute();
        }catch (SQLException exception){
            throw new DbException(exception.getMessage());
        }
    }

    public void removePerson(String userid){
        String sqlString = String.format("DELETE FROM %s.users WHERE userid='"+userid+"'",this.schema);
        try {
            PreparedStatement sqlStatement = connection.prepareStatement(sqlString);
            sqlStatement.execute();
        }catch (SQLException exception){
            throw new DbException(exception.getMessage());
        }
    }

}
