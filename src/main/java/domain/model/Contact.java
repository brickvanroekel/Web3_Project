package domain.model;

import ui.controller.RequestHandler;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    //private String contactId;
    private String email;
    private Timestamp timestamp;
    private String firstName;
    private String lastName;
    private String gsm;

    public Contact(String firstName, String lastName, Timestamp timestamp, String gsm,String email) {
        //setContactId(contactId);
        setFirstName(firstName);
        setLastName(lastName);
        setTimestamp(timestamp);
        setEmail(email);
        setGsm(gsm);

    }

    public Contact() {
    }

    /*public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        if (contactId != null || !contactId.trim().isEmpty())
            this.contactId = contactId;
        throw new IllegalArgumentException("No ID given");
    }*/

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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
