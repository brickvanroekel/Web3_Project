package domain.service;

import domain.db.ContactDB;
import domain.db.PersonDB;
import domain.db.ReservationDB;
import domain.model.Contact;
import domain.model.Person;
import domain.model.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ContactTracingService {
    private PersonDB personDb = new PersonDB();
    private ReservationDB reservationDB = new ReservationDB();
    private ContactDB contactDB = new ContactDB();

    public ContactTracingService() {
    }



    public Person getPerson(String personId) {
        List<Person> personList = getPersons();
        for (Person p : personList) {
            if (p.getUserid().equals(personId)) return p;
        }
        return null;

    }

    public List<Person> getPersons() {
        return getPersonDb().getAllPersons();
    }

    public void addPerson(Person person) {
        getPersonDb().add(person);
    }

    public void updatePersons(Person person) {
        getPersonDb().updatePerson(person);
    }

    public void deletePerson(String id) {
        getPersonDb().removePerson(id);
    }

    public Reservation getReservation(String id){
        ArrayList<Reservation> reservations = getAllReservations();
        for(Reservation reservation:reservations){
            if(reservation.getId().equals(id))
                return reservation;
        }
        return null;
    }
    public List<Contact> getContacts(){
        return getAllContacts();
    }

    public void addContact(Contact contact){
        getContactDB().add(contact);
    }

    private PersonDB getPersonDb() {return personDb;}
    private ReservationDB getReservationDB() { return reservationDB;}
    private ContactDB getContactDB(){return contactDB;}

    public ArrayList<Reservation> getAllReservations(){ return getReservationDB().getAllReservations(this);}
    public void addReservation(Reservation reservation){getReservationDB().add(reservation);}
    public String getReservationsUser(String userid){
        Person person = getPerson(userid);
        return person.getReservations();
    }

    public ArrayList<Contact> getAllContacts(){ return getContactDB().getAllContacts(this);}

}
