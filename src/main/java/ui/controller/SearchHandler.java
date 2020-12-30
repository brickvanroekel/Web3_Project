package ui.controller;

import domain.model.Contact;
import domain.model.CovidTest;
import domain.model.Person;
import domain.model.Reservation;

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
        Person person = (Person) request.getSession().getAttribute("person");
        ArrayList<String> errors = new ArrayList<>();
        CovidTest test = db.getTestUser(person.getUserid());

        if(test.getUserid()==null){
            errors.add("You have not registered a test yet. You can register one at the 'Register test' page.");
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
