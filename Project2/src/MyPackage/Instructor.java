package MyPackage;

import java.util.Date;

public class Instructor extends Person implements java.io.Serializable
{
	public String id;
	public String rank;
	public int salary;

	public Instructor()
	{
	}

	public Instructor(String id, String name, Date dob, String address, String rank, int salary)
	{
		super(id,name,dob,address);
		this.rank = rank;
		this.salary = salary;
	}
	
	public Instructor(String id, String rank, int salary)
	{
		this.id = id;
		this.rank = rank;
		this.salary = salary;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}	
}
