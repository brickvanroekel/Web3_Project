package domain.model;

import ui.controller.RequestHandler;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    private LocalDate date;
    private String email;
    private LocalTime hour;
    private String firstName;
    private String lastName;
    private String gsm;


    DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter tFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public Contact(String firstName, String lastName, String date, String hour, String gsm,String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setGsm(gsm);
        setDate(date);
        setHour(hour);
    }

    public Contact() {
    }

    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    public void setEmail(String email) {
        if(email.isEmpty()){
            throw new IllegalArgumentException("No email given");
        }
        String USERID_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(USERID_PATTERN);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            throw new IllegalArgumentException("Email not valid");
        }
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName.isEmpty()){
            throw new IllegalArgumentException("No firstname given");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName.isEmpty()){
            throw new IllegalArgumentException("No last name given");
        }
        this.lastName = lastName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(String date) {
        if(date == null  || date.trim().isEmpty())
            throw new IllegalArgumentException("Give correct date");
        this.date = LocalDate.parse(date, dFormatter);
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(String arrival) {
        if(arrival == null || arrival.trim().isEmpty())
            throw new IllegalArgumentException("Give correct arrival time");
        this.hour = LocalTime.parse(arrival,tFormatter);
    }

}
