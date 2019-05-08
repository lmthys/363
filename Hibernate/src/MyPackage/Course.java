package MyPackage;

public class Course implements java.io.Serializable
{
	public String coursecode;
	public String coursename;
	public String prereq;
	
	public Course()
	{		
	}
	
	public Course(String coursecode, String prereq)
	{
		this.coursecode = coursecode;
		this.prereq = prereq;
	}
	
	public Course(String coursecode, String coursename, String prereq)
	{
		this.coursecode = coursecode;
		this.coursename = coursename;
		this.prereq = prereq;
	}

	public String getCoursecode() {
		return coursecode;
	}

	public void setCoursecode(String coursecode) {
		this.coursecode = coursecode;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getPrereq() {
		return prereq;
	}

	public void setPrereq(String prereq) {
		this.prereq = prereq;
	}
}
