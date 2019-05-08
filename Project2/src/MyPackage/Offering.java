package MyPackage;

public class Offering implements java.io.Serializable
{
	public String coursecode;
	public int sectionno;
	public Instructor instructor;
	
	public Offering()
	{
	}
	
	public Offering(String coursecode, int sectionno)
	{
		this.coursecode = coursecode;
		this.sectionno = sectionno;
	}
	
	public Offering(String coursecode, int sectionno, Instructor instructor)
	{
		this.coursecode = coursecode;
		this.sectionno = sectionno;
		this.instructor = instructor;
	}

	public String getCoursecode() {
		return coursecode;
	}

	public void setCoursecode(String coursecode) {
		this.coursecode = coursecode;
	}

	public int getSectionno() {
		return sectionno;
	}

	public void setSectionno(int sectionno) {
		this.sectionno = sectionno;
	}

	public Instructor getInstructorid() {
		return instructor;
	}

	public void setInstructorid(Instructor instructor) {
		this.instructor = instructor;
	}
}
