package domain.db;

import domain.model.Contact;
import domain.model.CovidTest;
import domain.model.Reservation;
import domain.service.ContactTracingService;
import ui.controller.RequestHandler;
import util.DbConnectionService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CovidTestDB {
    private Connection connection;
    private String schema;

    public CovidTestDB() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
    }

    public void addTest(CovidTest test) {
        if (test == null)
            throw new DbException("Give valid test");

        String sqlString = String.format("INSERT INTO %s.covidtests (userid,date,positive) VALUES (?,?,?)", this.schema);

        try {
            PreparedStatement sqlStatement = connection.prepareStatement(sqlString);
            sqlStatement.setString(1, test.getUserid());
            sqlStatement.setDate(2, test.getDate());
            sqlStatement.setBoolean(3, test.isPositive());
            sqlStatement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public ArrayList<CovidTest> getAllTests(){
        ArrayList<CovidTest> tests = new ArrayList<>();
        String sqlString = String.format("SELECT * FROM %s.covidtests",this.schema);

        try {
            PreparedStatement sqlStatement = connection.prepareStatement(sqlString);
            ResultSet resultSet = sqlStatement.executeQuery();

            while (resultSet.next()){
                String userid = resultSet.getString("userid");
                Date date = resultSet.getDate("date");
                Boolean positive = resultSet.getBoolean("positive");
                CovidTest covidTest = new CovidTest(userid,date,positive);
                tests.add(covidTest);
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        return tests;
    }

    public CovidTest getTestByUserid(String userid){
        CovidTest test = new CovidTest();
        String sqlString = String.format("SELECT * FROM %s.covidtests WHERE userid = ?",this.schema);

        try {
            PreparedStatement sqlStatement = connection.prepareStatement(sqlString);
            sqlStatement.setString(1, userid);
            ResultSet resultSet = sqlStatement.executeQuery();

            while (resultSet.next()){
                Date date = resultSet.getDate("date");
                Boolean positive = resultSet.getBoolean("positive");
                test = new CovidTest(userid,date,positive);
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        return test;
    }

    public void removeTest(String userid){
        String sqlString = String.format("DELETE FROM %s.covidtests WHERE userid='"+userid+"'",this.schema);
        try {
            PreparedStatement sqlStatement = connection.prepareStatement(sqlString);
            sqlStatement.execute();
        }catch (SQLException exception){
            throw new DbException(exception.getMessage());
        }
    }


}
