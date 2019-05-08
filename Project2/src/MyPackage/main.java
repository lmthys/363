package MyPackage;

import java.awt.Dimension;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import MyPackage.Person;
import MyPackage.Instructor;
import MyPackage.Student;
import MyPackage.HibernateUtil;

public class main
{
	public static void main(String[] args)
	{
		System.out.println("Program Started");	
		
		String instruction = "Enter 1: See all Persons\n";
		instruction += "Enter 2: See all Students\n";
		instruction += "Enter 3: See all Instructors\n";
		instruction += "Enter 4: See all Courses\n";
		instruction += "Enter 5: See all Offering\n";
		instruction += "Enter 6: See all Enrollment\n";
		instruction += "Enter 7: See information of a specific Person\n";
		instruction += "Enter 8: Insert new Instructor\n";
		instruction += "Enter 9: Delete existing Instructor\n";
		instruction += "Enter 10: Exit";
		String option = JOptionPane.showInputDialog(instruction);
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		while(true)
		{
			session.beginTransaction();
			String hql = "";
			
			if(option.equals("1"))
			{
				
				hql = "select p from Person p";
				Query query = session.createQuery(hql);
				List result = query.list();
								
				String toShow = "";
				Object[][] tableRow = new Object[result.size()][4];
				for(int i=0;i<result.size();i++)
				{
					Person thePerson = (Person)result.get(i);
					tableRow[i][0] = thePerson.getId();
					tableRow[i][1] = thePerson.name;
					tableRow[i][2] = thePerson.dob;
					tableRow[i][3] = thePerson.address;
				}
				Object[] tableCol = {"PID","Name","DOB", "Address"};
				JTable theTable = new JTable(tableRow,tableCol);
				JOptionPane.showMessageDialog(null, new JScrollPane(theTable));
				session.getTransaction().commit();
			}
			
			else if(option.equals("2"))
			{
				
				hql = "select s from Student s";
				Query query = session.createQuery(hql);
				List result = query.list();
								
				Object[][] tableRow = new Object[result.size()][8];
				for(int i=0;i<result.size();i++)
				{
					Student theStudent = (Student)result.get(i);
					tableRow[i][0] = theStudent.getId();
					tableRow[i][1] = theStudent.name;
					tableRow[i][2] = theStudent.dob;
					tableRow[i][3] = theStudent.address;
					tableRow[i][4] = theStudent.classification;
					tableRow[i][5] = theStudent.gpa;
					tableRow[i][6] = theStudent.credithours;
					tableRow[i][7] = theStudent.mentorid.id;
					
				}
				Object[] tableCol = {"Student ID","Name","DOB", "Address","Classification","GPA","CreditHours","MentorID"};
				JTable theTable = new JTable(tableRow,tableCol);
				JOptionPane.showMessageDialog(null, new JScrollPane(theTable));
				session.getTransaction().commit();
			}
			else if(option.equals("3"))
			{
				hql = "select i from Instructor i";
				List result = session.createQuery(hql).list();
				Object[][] tableRow = new Object[result.size()][6];
				for(int i=0;i<result.size();i++)
				{
					Instructor theInstructor = (Instructor)result.get(i);
					tableRow[i][0] = theInstructor.getId();
					tableRow[i][1] = theInstructor.name;					
					tableRow[i][2] = theInstructor.dob;
					tableRow[i][3] = theInstructor.address;
					tableRow[i][4] = theInstructor.rank;
					tableRow[i][5] = theInstructor.salary;
				}
				Object[] tableCol = {"Instructor ID","Name","DOB","Address","Rank","Salary"};
				JTable theTable = new JTable(tableRow,tableCol);
				JOptionPane.showMessageDialog(null, new JScrollPane(theTable));
				session.getTransaction().commit();
			}
			else if(option.equals("4"))
			{
				hql = "select c from Course c";
				List result = session.createQuery(hql).list();
				String toShow = "";
				for(int i=0;i<result.size();i++)
				{
					Course theCourse = (Course)result.get(i);
					toShow += "CourseCode:  " + theCourse.getCoursecode() + ",   CourseName:  " + theCourse.coursename + ",   PreReq:  " + theCourse.prereq + "\n";
				}
				JOptionPane.showMessageDialog(null, toShow);
				session.getTransaction().commit();
			}
			else if(option.equals("5"))
			{
				hql = "select o from Offering o";
				List result = session.createQuery(hql).list();
				String toShow = "";
				for(int i=0;i<result.size();i++)
				{
					Offering theOffering = (Offering)result.get(i);
					toShow += "Course Code:  " + theOffering.getCoursecode() + "   , Section No.: " + theOffering.sectionno + "   , Instructor Id: " + theOffering.instructor.id + "\n";
				}
				JOptionPane.showMessageDialog(null, toShow);
				session.getTransaction().commit();
			}
			else if(option.equals("6"))
			{
				hql = "select e from Enrollment e";
				List result = session.createQuery(hql).list();
				String toShow = "";
				for(int i=0;i<result.size();i++)
				{
					Enrollment theEnrollment = (Enrollment)result.get(i);
					toShow += "Course Code:  " + theEnrollment.coursecode + "   , Section No.: " + theEnrollment.sectionno + "   , Student Id: " + theEnrollment.student.id + "   , Grade : " + theEnrollment.grade + "\n";
				}
				JOptionPane.showMessageDialog(null, toShow);
				session.getTransaction().commit();
			}
			else if(option.equals("7"))
			{
				String personID = JOptionPane.showInputDialog("Enter The Person Id:");
				hql = "from Instructor i where i.id = :pid";
				List result = session.createQuery(hql).setParameter("pid", personID).list();
				String toShow = "";
				for(int i=0;i<result.size();i++)
				{
					Object obj = (Object)result.get(i);
					Instructor theinstructor = (Instructor)obj;
					toShow += "PersonID: " + theinstructor.getId() + "\n";
					toShow += "Name: " + theinstructor.getName() + "\n";
					toShow += "DOB: " + theinstructor.getDob() + "\n";
					toShow += "Address: " + theinstructor.getAddress() + "\n";
					toShow += "Rank: " + theinstructor.getRank() + "\n";
					toShow += "Salary: " + theinstructor.getSalary() + "\n";
				}
				if(result.size() == 0)
				{
					hql = "from Student s where s.id = :pid";
					result = session.createQuery(hql).setParameter("pid", personID).list();
					for(int i=0;i<result.size();i++)
					{
						Object obj = (Object)result.get(i);
						Student theStudent = (Student)obj;
						toShow += "Person ID: " + theStudent.getId() + "\n";
						toShow += "Name: " + theStudent.getName() + "\n";
						toShow += "DOB: " + theStudent.getDob() + "\n";
						toShow += "Address: " + theStudent.getAddress() + "\n";
						toShow += "Classification: " + theStudent.getClassification() + "\n";
						toShow += "Credit Hours: " + theStudent.getCredithours() + "\n";
						toShow += "GPA: " + theStudent.getGpa() + "\n";
						toShow += "Mentor Id: " + theStudent.getMentorid().id;
					}
					if(result.size() == 0)
					{
						hql = "from Person p where p.id = :pid";
						result = session.createQuery(hql).setParameter("pid", personID).list();
						for(int i=0;i<result.size();i++)
						{
							Object obj = (Object)result.get(i);
							Person thePerson = (Person)obj;
							toShow += "Person ID: " + thePerson.getId() + "\n";
							toShow += "Name: " + thePerson.getName() + "\n";
							toShow += "DOB: " + thePerson.getDob() + "\n";
							toShow += "Address: " + thePerson.getAddress();
						}
						if(result.size() == 0)
						{
							toShow = "The person with ID: " + personID + " does not exist in the database.";
						}
					}
				}
				JOptionPane.showMessageDialog(null, toShow);
				session.getTransaction().commit();
			}
			else if(option.equals("8"))
			{
				String InstructorID = JOptionPane.showInputDialog("Enter Instructor ID");
				String toShow = "";
				List result = session.createQuery("from Instructor i where i.id = :pid").setParameter("pid", InstructorID).list();
				if(result.size() > 0)
				{
					toShow += "Instructor with Id: " + InstructorID + " has already existed in the database.";
				}
				else
				{
					result = session.createQuery("from Person p where p.id = :pid").setParameter("pid", InstructorID).list();
					if(result.size() > 0)
					{
						try
						{							
							String iRank = JOptionPane.showInputDialog("Enter Instructor Rank: ");
							int iSalary = Integer.parseInt(JOptionPane.showInputDialog("Enter Instructor Salary: "));
							Instructor newInstructor = new Instructor(InstructorID,iRank,iSalary);
							newInstructor.setId(InstructorID);
							session.save(newInstructor);
							toShow += "Instructor with Instructor ID: " + InstructorID + " has been added to Instroctor Table.";
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}						
					}
					else
					{					
						try
						{
							String iName = JOptionPane.showInputDialog("Enter Instructor Name: ");
							String iDOB = JOptionPane.showInputDialog("Enter Instructor DOB: ");
							String iAddress = JOptionPane.showInputDialog("Enter Instructor Address: ");
							String iRank = JOptionPane.showInputDialog("Enter Instructor Rank: ");
							int iSalary = Integer.parseInt(JOptionPane.showInputDialog("Enter Instructor Salary: "));
							DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
							Instructor newInstructor = new Instructor(InstructorID,iName,df.parse(iDOB),iAddress,iRank,iSalary);
							newInstructor.setId(InstructorID);
							session.save(newInstructor);
							toShow += "Instructor with Instructor ID: " + InstructorID + " has been added to Person Table and Instroctor Table.";
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				}
				JOptionPane.showMessageDialog(null, toShow);
				session.getTransaction().commit();
			}
			else if(option.equals("9"))
			{	
				String InstructorID = JOptionPane.showInputDialog("Enter Id of Instructor to be deleted: ");
				String toShow = "";
				String temp="";
				List result = session.createQuery("select o from Offering o").list();
				for(int i=0;i<result.size();i++)
				{
					Offering theOffering = (Offering)result.get(i);
					temp = theOffering.instructor.id;
					if (temp == InstructorID)
					{
						break;
					}
				}
				if(temp == InstructorID)
				{
					toShow += "The Instructor with ID " + InstructorID + " is teaching many courses in Offering table.";
				}
				else
				{
					result = session.createQuery("from Instructor i where i.id = :pid").setParameter("pid", InstructorID).list();
					if(result.size() == 1)
					{
						Instructor theInstructor = (Instructor)result.get(0);
						session.delete(theInstructor);
						toShow += "Instructor with ID: " + InstructorID + " has been deleted from Instructor Table.";
					}
					else
					{
						toShow += "Instructor with Id: " + InstructorID + " does not exist in the database.";
					}
				}
				JOptionPane.showMessageDialog(null, toShow);
				session.getTransaction().commit();
			}
			else				
			{
				break;
			}
			option = JOptionPane.showInputDialog(instruction);			
		}		
		session.close();
		System.out.println("Program halted.");		
	}
}