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
        setId(reservation,errors);
        setTimestamp(request,reservation,errors);
        reservation.setShopper(person.getUserid());


        if(errors.size()==0){
            try {
                getDb().addReservation(reservation);
                request.getSession().setAttribute("succes","You have succesfully booked a reservation!");
                response.sendRedirect("Servlet?command=Reservation");
                return "reservation.jsp";
            }catch (Exception e){
                e.printStackTrace();
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors",errors);
        return "reservation.jsp";
    }
    private void setId(Reservation reservation, ArrayList<String> errors){
        ArrayList<String> ids = db.getReservationIds();
        String id = null;
        try {
            id = String.valueOf(Math.round(Math.random()*10000));
            if(ids.contains(id)){
                setId(reservation,errors);
            }
        }catch (Exception e){
            errors.add("Couldn't set reservation ID");
        }
        reservation.setId(id);

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
