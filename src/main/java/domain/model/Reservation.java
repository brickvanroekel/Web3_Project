package domain.model;



import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Reservation implements Comparable<Reservation>{
    private String id;
    private Timestamp timestamp;
    private String shopper;



    public Reservation(){

    }

    public Reservation(String id,Timestamp timestamp, String shopper){
        this.id = id;
        setTimestamp(timestamp);
        setShopper(shopper);
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){return id;}

    public Timestamp getTimestamp(){ return this.timestamp;}

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp=timestamp;
    }

    public String getShopper(){return shopper;}

    public void setShopper(String person){
        if(person==null || person.trim().isEmpty())
            throw new IllegalArgumentException("Give valid person");
        this.shopper = person;
    }

    public boolean isDuring(LocalTime time){
        if(time.isAfter(this.timestamp.toLocalDateTime().toLocalTime())&& time.isBefore(this.timestamp.toLocalDateTime().toLocalTime().plusHours(1)))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "On " + timestamp.toLocalDateTime().toLocalDate() + " at "+timestamp.toLocalDateTime().toLocalTime();
    }

    @Override
    public int compareTo(Reservation res){

        int difference = this.timestamp.toLocalDateTime().getMonthValue()-res.timestamp.toLocalDateTime().getMonthValue();
        if(difference == 0){
            difference = this.timestamp.toLocalDateTime().getDayOfMonth()-res.timestamp.toLocalDateTime().getDayOfMonth();
            if(difference == 0){
                difference = this.timestamp.toLocalDateTime().getHour()-res.timestamp.toLocalDateTime().getHour();
            }
        }
        return difference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return timestamp.toLocalDateTime().toLocalDate().equals(that.timestamp.toLocalDateTime().toLocalDate()) &&
                timestamp.toLocalDateTime().toLocalTime().equals(that.timestamp.toLocalDateTime().toLocalTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, shopper);
    }
}
