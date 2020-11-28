package ui.controller;

import domain.model.Person;
import domain.model.Reservation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BookHandler extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        ArrayList<String> errors = new ArrayList<>();

        Person person = (Person) request.getSession().getAttribute("person");

        if(person==null){
            request.setAttribute("errors","Log in");
            return "index.jsp";
        }
        Reservation reservation = new Reservation();
        setTimestamp(request,reservation,errors);
        reservation.setShopper(person.getUserid());
        reservation.setId();


        if(errors.size()==0){
            try {
                person.addReservation(reservation);
                db.addReservation(reservation);
                request.setAttribute("reservation",reservation);
                return "confirmation.jsp";
            }catch (Exception e){
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors",errors);
        return "reservation.jsp";
    }

    private void setTimestamp(HttpServletRequest request,Reservation reservation,ArrayList<String> errors){
        String date = request.getParameter("date");
        String hour=request.getParameter("hour");
        String timestampPattern="yyyy-M-d HH:mm";
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern(timestampPattern);
        LocalDateTime dateTime=null;
        Timestamp timestamp=null;
        try{
            request.setAttribute("arrivalPreviousValue",hour);
            request.setAttribute("datePreviousValue",date);
            dateTime=LocalDateTime.from(formatter.parse(date+" "+hour));
            timestamp=Timestamp.valueOf(dateTime);
        }catch(DateTimeException e){
            errors.add("date or hour invalid");
        }
        reservation.setTimestamp(timestamp);
    }


}
