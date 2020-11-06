package ui.controller;

import domain.model.Person;
import domain.model.Reservation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class BookHandler extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        ArrayList<String> errors = new ArrayList<>();

        Person person = (Person) request.getSession().getAttribute("person");

        if(person==null){
            request.setAttribute("errors","Log in");
            return "reservation.jsp";
        }
        Reservation reservation = new Reservation();
        setDate(reservation,request,errors);
        setArrival(reservation,request,errors);
        reservation.setShopper(person);

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

    private void setDate(Reservation reservation, HttpServletRequest request, ArrayList<String> errors) {
        String date = request.getParameter("date");
        try{
            reservation.setDate(date);
            request.setAttribute("dateClass", "has-succes");
            request.setAttribute("datePreviousValue", date);
        }
        catch (Exception exc){
            errors.add(exc.getMessage());
            request.setAttribute("dateClass", "has-error");
        }
    }

    private void setArrival(Reservation reservation, HttpServletRequest request, ArrayList<String> errors) {
        String arrival = request.getParameter("arrival");
        try{
            reservation.setArrival(arrival);
            request.setAttribute("arrivalClass", "has-succes");
            request.setAttribute("arrivalPreviousValue", arrival);
        }
        catch (Exception exc){
            errors.add(exc.getMessage());
            request.setAttribute("arrivalClass", "has-error");
        }
    }


}
