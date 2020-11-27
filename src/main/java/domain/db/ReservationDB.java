package domain.db;

import domain.service.ContactTracingService;
import domain.model.Person;
import domain.model.Reservation;
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
            System.out.println(exception.getMessage());
            throw new DbException(exception.getMessage());
        }
    }

    public ArrayList<Reservation> getAllReservations(ContactTracingService contactTracingService){
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


}
