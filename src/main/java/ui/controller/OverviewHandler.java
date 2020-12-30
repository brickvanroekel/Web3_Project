package ui.controller;

import domain.model.Contact;
import domain.model.Person;
import domain.model.Reservation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class OverviewHandler extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        Person person = (Person) request.getSession().getAttribute("person");
        ArrayList<String> errors = new ArrayList<>();
        if(person==null){
            errors.add("Log in");
            request.setAttribute("errors",errors);
            return "index.jsp";
        }
        ArrayList<Reservation> reservationsUser = getDb().getReservationsUser(person.getUserid());
        ArrayList<String> reservationsString = new ArrayList<>();
        System.out.println(reservationsUser);
        try {
            if (person.getRole().equals("administrator")){
                request.setAttribute("db",db.getPersons());
                request.setAttribute("reservations",getDb().getAllReservations());
            }
            else{
                request.setAttribute("db",db.getPerson(person.getUserid()));
                for (Reservation reservation:reservationsUser) {
                    String string = reservation.toString() + "\n";
                    reservationsString.add(string);
                }
                request.setAttribute("reservations",reservationsString);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "personoverview.jsp";
    }
}
