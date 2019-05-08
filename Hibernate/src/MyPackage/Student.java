package MyPackage;

import java.util.Date;

public class Student extends Person implements java.io.Serializable
{
	public String id;
	public String classification;
	public double gpa;
	public int credithours;
	public Instructor mentorid;
	
	public Student()
	{		
	}
	
	public Student(String id, String name, Date dob, String address, String classification, double gpa, int credithours, Instructor mentoreid)
	{
		super(id,name,dob,address);
		this.classification=classification;
		this.gpa=gpa;
		this.credithours=credithours;
		this.mentorid=mentoreid;
	}
	
	// if the student already in person
	public Student(String id, String classification, double gpa, int credithours, Instructor mentoreid)
	{
		this.id = id;
		this.classification=classification;
		this.gpa=gpa;
		this.credithours=credithours;
		this.mentorid=mentoreid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public int getCredithours() {
		return credithours;
	}

	public void setCredithours(int credithours) {
		this.credithours = credithours;
	}

	public Instructor getMentorid() {
		return mentorid;
	}

	public void setMentorid(Instructor mentorid) {
		this.mentorid = mentorid;
	}
}
