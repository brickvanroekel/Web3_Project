package domain.model;



import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Reservation implements Comparable<Reservation>{
    private String id;
    private LocalDate date;
    private LocalTime arrival;
    private Person shopper;

    DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter tFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public Reservation(){

    }

    public Reservation(String id,String date, String arrival, Person shopper){
        this.id = id;
        setDate(date);
        setArrival(arrival);
        setShopper(shopper);
    }

    public Reservation(String date, String arrival, Person shopper){
        setId();
        setDate(date);
        setArrival(arrival);
        setShopper(shopper);
    }
    public Reservation(String date, String arrival){
        setId();
        setDate(date);
        setArrival(arrival);
    }
    public void setId(){
        this.id =  String.valueOf(Math.random()*10000);
    }

    public String getId(){return id;}

    public LocalDate getDate() {
        return date;
    }

    public void setDate(String date) {
        if(date == null  || date.trim().isEmpty())
            throw new IllegalArgumentException("Give correct date");
        this.date = LocalDate.parse(date, dFormatter);
    }

    public LocalTime getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        if(arrival == null || arrival.trim().isEmpty())
            throw new IllegalArgumentException("Give correct arrival time");
        this.arrival = LocalTime.parse(arrival,tFormatter);
    }

    public Person getShopper(){return shopper;}

    public void setShopper(Person person){
        if(person==null)
            throw new IllegalArgumentException("Give valid person");
        this.shopper = person;
    }

    public String getShopperString(){
        String output = this.shopper.getUserid();
        return output;
    }

    public boolean isDuring(LocalTime time){
        if(time.isAfter(this.arrival)&& time.isBefore(this.arrival.plusHours(1)))
            return true;
        return false;
    }

    @Override
    public int compareTo(Reservation res){

        int difference = this.date.getMonthValue()-res.date.getMonthValue();
        if(difference == 0){
            difference = this.date.getDayOfMonth()-res.date.getDayOfMonth();
            if(difference == 0){
                difference = this.arrival.getHour()-res.arrival.getHour();
            }
        }
        return difference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return date.equals(that.date) &&
                arrival.equals(that.arrival);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, arrival);
    }
}
