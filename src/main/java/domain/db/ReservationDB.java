package domain.db;

import domain.model.Contact;
import domain.service.ContactTracingService;
import domain.model.Person;
import domain.model.Reservation;
import org.postgresql.core.SqlCommand;
import util.DbConnectionService;

import java.sql.*;
import java.util.ArrayList;


public class ReservationDB {
    private Connection connection;
    private String schema;

    public ReservationDB(){
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
    }

    public void add(Reservation reservation){
        if(reservation==null)
            throw new DbException("Give valid reservation");
        String sqlString = String.format("INSERT INTO %s.reservations (id,timestamp,shopper) VALUES (?,?,?)", this.schema);

        try {
            PreparedStatement sqlStatement = connection.prepareStatement(sqlString);
            sqlStatement.setString(1,reservation.getId());
            sqlStatement.setTimestamp(2,reservation.getTimestamp());
            sqlStatement.setString(3,reservation.getShopper());
            sqlStatement.execute();

        }catch (SQLException exception){
            exception.printStackTrace();
            throw new DbException(exception.getMessage());
        }
    }

    public ArrayList<Reservation> getAllReservations(){
        ArrayList<Reservation> reservations = new ArrayList<>();
        String sqlString = String.format("SELECT * FROM %s.reservations",this.schema);

        try {
            PreparedStatement sqlStatement = connection.prepareStatement(sqlString);
            ResultSet resultSet = sqlStatement.executeQuery();
            while (resultSet.next()){
                String id = resultSet.getString("id");
                Timestamp timestamp = resultSet.getTimestamp("timestamp");
                String shopper = resultSet.getString("shopper");

                Reservation reservation = new Reservation(id,timestamp,shopper);
                reservations.add(reservation);
            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
            throw new DbException(exception.getMessage());
        }
        return reservations;
    }

    public void removeReservations(String id){
        String sqlString = String.format("DELETE FROM %s.reservations WHERE id='"+id+"'",this.schema);
        try {
            PreparedStatement sqlStatement = connection.prepareStatement(sqlString);
            sqlStatement.execute();
        }catch (SQLException exception){
            throw new DbException(exception.getMessage());
        }
    }

    public ArrayList<Reservation> getReservationsByUser(String userid){
        ArrayList<Reservation> reservations = new ArrayList<>();
        String sqlString = String.format("SELECT * FROM %s.reservations WHERE shopper = ?",this.schema);

        try {
            PreparedStatement sqlStatement = connection.prepareStatement(sqlString);
            sqlStatement.setString(1,userid);
            ResultSet resultSet = sqlStatement.executeQuery();
            while (resultSet.next()){
                String id = resultSet.getString("id");
                Timestamp timestamp = resultSet.getTimestamp("timestamp");
                String shopper = resultSet.getString("shopper");

                Reservation reservation = new Reservation(id,timestamp,shopper);
                reservations.add(reservation);
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        return reservations;
    }

    public ArrayList<String> getUsedIDs(){
        ArrayList<String> ids = new ArrayList<>();
        String sqlString = String.format("SELECT id FROM %s.reservations",this.schema);

        try {
            PreparedStatement sqlStatement = connection.prepareStatement(sqlString);
            ResultSet resultSet = sqlStatement.executeQuery();
            while (resultSet.next()) {
                ids.add(resultSet.getString("id"));
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        return ids;
    }



}
