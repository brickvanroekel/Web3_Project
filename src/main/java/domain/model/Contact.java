package domain.model;

import ui.controller.RequestHandler;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact implements Comparable<Contact>{
    private String email;
    private Timestamp timestamp;
    private String firstName;
    private String lastName;
    private String gsm;
    private String contact;

    public Contact(String contact, String firstName, String lastName, String gsm,String email, Timestamp timestamp) {
        setFirstName(firstName);
        setLastName(lastName);
        setTimestamp(timestamp);
        setEmail(email);
        setGsm(gsm);
        setContact(contact);
    }

    public Contact() {
    }

    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        if(gsm==null || gsm.trim().equals("")){
            throw new IllegalArgumentException("No phone number given");
        }
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public int compareTo(Contact contact){
        return getTimestamp().toLocalDateTime().compareTo(contact.getTimestamp().toLocalDateTime());
    }
}
