package domain.model;

import ui.controller.RequestHandler;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Person {
	private String userid;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private ArrayList<Reservation> reservations;
	private ArrayList<Contact> contacts;

	public Person(String userid, String email, String password, String firstName, String lastName) {
		setUserid(userid);
		setEmail(email);
		setPasswordHash(password);
		setFirstName(firstName);
		setLastName(lastName);
		this.reservations = null;
		this.contacts = null;
	}
	
	public Person() {
	}


	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		if(userid.isEmpty()){
			throw new IllegalArgumentException("No userid given");
		}
		this.userid = userid;
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
	
	public String getPassword() {
		return password;
	}
	
	public boolean isCorrectPassword(String password)  {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		try{
			return hashPassword(password).equals(this.password);
		}catch (Exception e){
			System.out.println(e.getMessage());
			return false;
		}

	}

	public void setPassword(String password)  {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		try {
			this.password = hashPassword(password);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public void setPasswordHash(String password){
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		this.password = password;
	}

	public String hashPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{

		MessageDigest crypt = MessageDigest.getInstance("SHA-512");
		crypt.reset();
		byte[] passwordBytes = password.getBytes("UTF-8");
		crypt.update(passwordBytes);
		byte[] digest = crypt.digest();
		return new BigInteger(1, digest).toString(16);
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

	public void addReservation(Reservation reservation){
		this.reservations.add(reservation);
	}

	public String getReservations(){
		String out = "";
		if(this.reservations == null ||this.reservations.isEmpty())
			out="no reservations";
		for(Reservation reservation: reservations){
			out += "on " + reservation.getTimestamp().toLocalDateTime().toLocalDate() + " at "+reservation.getTimestamp().toLocalDateTime().toLocalTime() +"\n";
		}
		return out;
	}

	public void addContact(Contact contact){ this.contacts.add(contact);}

	public ArrayList getContacts(){
		return this.contacts;
	}
	
	@Override
	public String toString(){
		return getFirstName() + " " + getLastName() + ": " + getUserid() + ", " + getEmail();
	}	
}
