package MyPackage;

import java.util.Date;

public class Person implements java.io.Serializable
{

	public String id;
	public String name;
	public Date dob;
	public String address;
	
	public Person()
	{
	}
	
	public Person(String id)
	{
		this.id = id;
	}

	public Person(String id, String name, Date dob, String address)
	{
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}