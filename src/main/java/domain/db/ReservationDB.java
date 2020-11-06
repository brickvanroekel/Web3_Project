package domain.db;

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
        String sqlString = String.format("INSERT INTO %s.reservations (id,date,arrival,shopper) VALUES (?,?,?,?)", this.schema);

        try {
            PreparedStatement sqlStatement = connection.prepareStatement(sqlString);
            sqlStatement.setString(1,reservation.getId());
            sqlStatement.setDate(2,Date.valueOf(reservation.getDate()));
            sqlStatement.setTime(3,Time.valueOf(reservation.getArrival()));
            sqlStatement.setString(3,reservation.getShopperString());
            sqlStatement.execute();

        }catch (SQLException exception){
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
                String date = resultSet.getDate("date").toString();
                String arrival = resultSet.getTime("arrival").toString();
                String userid = resultSet.getString("shopper");
                Person shopper = contactTracingService.getPerson(userid);

                Reservation reservation = new Reservation(id,date,arrival,shopper);
                reservations.add(reservation);
            }
        }catch (SQLException exception){
            throw new DbException(exception.getMessage());
        }
        return reservations;

    }


}
