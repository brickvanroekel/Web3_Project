package domain.service;

import domain.db.ContactDB;
import domain.db.CovidTestDB;
import domain.db.PersonDB;
import domain.db.ReservationDB;
import domain.model.Contact;
import domain.model.CovidTest;
import domain.model.Person;
import domain.model.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ContactTracingService {
    private PersonDB personDb = new PersonDB();
    private ReservationDB reservationDB = new ReservationDB();
    private ContactDB contactDB = new ContactDB();
    private CovidTestDB covidTestDB = new CovidTestDB();

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



    private PersonDB getPersonDb() {return personDb;}
    private ReservationDB getReservationDB() { return reservationDB;}
    private ContactDB getContactDB(){return contactDB;}
    private CovidTestDB getCovidTestDB(){ return covidTestDB;}

    public ArrayList<Reservation> getAllReservations(){ return getReservationDB().getAllReservations();}
    public void addReservation(Reservation reservation){getReservationDB().add(reservation);}
    public ArrayList<Reservation> getReservationsUser(String userid){ return getReservationDB().getReservationsByUser(userid); }
    public ArrayList<String> getReservationIds(){return getReservationDB().getUsedIDs();}
    public void removeReservation(String id){ getReservationDB().removeReservations(id);}


    public ArrayList<Contact> getAllContacts(){ return getContactDB().getAllContacts();}
    public void addContact(Contact contact){
        getContactDB().add(contact);
    }
    public ArrayList<Contact> getContactsUser(String userid){
        return getContactDB().getContactsByUserId(userid);
    }

    public ArrayList<CovidTest> getAllTests(){ return getCovidTestDB().getAllTests();}
    public void addTest(CovidTest test){ getCovidTestDB().addTest(test);}
    public CovidTest getTestUser(String userid){ return getCovidTestDB().getTestByUserid(userid);}
    public void removeTest(String userid){getCovidTestDB().removeTest(userid);}
}
