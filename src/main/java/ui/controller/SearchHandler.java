package ui.controller;

import domain.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class SearchHandler extends RequestHandler {
    private static final int quarantine = 14;
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Role[] roles = {Role.administrator,Role.customer};
        Utility.checkrole(request, roles);

        Person person = (Person) request.getSession().getAttribute("person");
        ArrayList<String> errors = new ArrayList<>();
        CovidTest test = db.getTestUser(person.getUserid());

        if(test.getUserid()==null){
            errors.add("You have not registered a test yet. You can register one at the 'Register test result' page.");
            request.setAttribute("errors",errors);
            return "search.jsp";
        }

        Date testDate = test.getDate();
        ArrayList<Contact> userContacts = new ArrayList<>();
        userContacts = db.getContactsUser(person.getUserid());
        ArrayList<Contact> riskContacts = new ArrayList<>();
        for (Contact c:userContacts) {
            Date date = Date.valueOf(c.getTimestamp().toLocalDateTime().toLocalDate());
            if(date.equals(testDate) || date.after(testDate)){
                riskContacts.add(c);
            }
        }
        ArrayList<Reservation> userReservations = new ArrayList<>();
        userReservations = db.getReservationsUser(person.getUserid());
        ArrayList<Reservation> cancelableReservations = new ArrayList<>();
        for (Reservation r: userReservations){
            Date date = Date.valueOf(r.getTimestamp().toLocalDateTime().toLocalDate());
            Date endQuarantineDate = addDays(testDate,quarantine);
            if(date.equals(testDate) || (date.after(testDate) && date.before(endQuarantineDate))){
                cancelableReservations.add(r);
            }
        }

        if("POST".equalsIgnoreCase(request.getMethod())){
            for (Reservation r:cancelableReservations) {
                db.removeReservation(r.getId());
            }
            request.getSession().setAttribute("succes","You have succesfully canceled you reservation(s)");
            return "reservation.jsp";
        }

        request.setAttribute("contacts",riskContacts);
        request.setAttribute("reservations",cancelableReservations);
        request.getSession().setAttribute("cancelableReservations",cancelableReservations);
        return "search.jsp";

    }

    //found on stackoverflow: adds days to date
    public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
    }
}
