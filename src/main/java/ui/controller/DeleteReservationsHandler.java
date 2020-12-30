package ui.controller;

import domain.model.Person;
import domain.model.Reservation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class DeleteReservationsHandler extends RequestHandler{
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        ArrayList<Reservation> reservations = (ArrayList<Reservation>) request.getSession().getAttribute("cancelableReservations");
        for (Reservation r:reservations) {
            db.removeReservation(r.getId());
        }
        request.getSession().setAttribute("succes","You have succesfully canceled you reservation(s)");
        return "reservation.jsp";
    }
}
