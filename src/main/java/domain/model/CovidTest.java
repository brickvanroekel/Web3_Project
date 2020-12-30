package domain.model;

import domain.db.DbException;
import java.sql.Date;

public class CovidTest {
    String userid;
    Date date;
    boolean positive;


    public CovidTest(String userid, Date date, boolean positive){
        setUserid(userid);
        setDate(date);
        setPositive(positive);
    }

    public CovidTest() { }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        if(userid == null || userid.trim().isEmpty())
            throw new DomainException("Give valid userid");
        this.userid = userid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        if(date == null)
            throw new DomainException("Give valid date");
        this.date = date;
    }


    public void setPositive(boolean positive) {
        this.positive = positive;
    }

    public boolean isPositive() {
        return positive;
    }
}
