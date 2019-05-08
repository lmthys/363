package MyPackage;

public class Enrollment implements java.io.Serializable
{
	public String coursecode;
	public String sectionno;
	public Student student;
	public String grade;
	
	public Enrollment()
	{
	}
	
	public Enrollment(String coursecode, String sectionno)
	{
		this.coursecode = coursecode;
		this.sectionno = sectionno;
	}
	
	public Enrollment(String coursecode, String sectionno, Student student, String grade)
	{
		this.coursecode = coursecode;
		this.sectionno = sectionno;
		this.student = student;
		this.grade = grade;
	}

	public String getCoursecode() {
		return coursecode;
	}

	public void setCoursecode(String coursecode) {
		this.coursecode = coursecode;
	}

	public String getSectionno() {
		return sectionno;
	}

	public void setSectionno(String sectionno) {
		this.sectionno = sectionno;
	}

	public Student getStudentid() {
		return student;
	}

	public void setStudentid(Student student) {
		this.student = student;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
}
