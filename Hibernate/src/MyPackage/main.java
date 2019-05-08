package MyPackage;

import java.awt.Dimension;
import java.text.DateFormat;
import java.text.ParseException;
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
/***
 * 
 * @author Larisa Andrews
 *
 */
public class main
{
	public static void main(String[] args)
	{
		System.out.println("Program Started");	
		
		String instruction = "Enter 1: to see Item 1\n";
		instruction += "Enter 2: to see Item 2\n";
		instruction += "Enter 3: to see Item 3\n";
		instruction += "Enter 4: to see Item 4\n";
		instruction += "Enter 5: to see Item 5\n";
		instruction += "Enter 6: to see Item 6\n";
		instruction += "Enter 7: to see Item 9\n";
		instruction += "Enter 8: to see Item 10\n";
		instruction += "Enter 9: to see Item 11\n";
		instruction += "Enter 10: Exit";
		String option = JOptionPane.showInputDialog(instruction);
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		while(true)
		{
			session.beginTransaction();
			String hql = "";
			
			/**
			 * Item 1: Print names and address of all person
			 */
			if(option.equals("1"))
			{
				
				hql = "select p from Person p";
				Query query = session.createQuery(hql);
				List result = query.list();
								
				String toShow = "";
				Object[][] tableRow = new Object[result.size()][2];
				for(int i=0;i<result.size();i++)
				{
					Person thePerson = (Person)result.get(i);
					tableRow[i][0] = thePerson.name;
					tableRow[i][1] = thePerson.address;
				}
				Object[] tableCol = {"Name","Address"};
				JTable theTable = new JTable(tableRow,tableCol);
				JOptionPane.showMessageDialog(null, new JScrollPane(theTable));
				session.getTransaction().commit();
			}
			/**
			 * Item 2: List names of students. 
			 */
			else if(option.equals("2"))
			{
				
				hql = "select s from Student s";
				Query query = session.createQuery(hql);
				List result = query.list();
								
				Object[][] tableRow = new Object[result.size()][1];
				for(int i=0;i<result.size();i++)
				{
					Student theStudent = (Student)result.get(i);
					tableRow[i][0] = theStudent.name;
					
				}
				Object[] tableCol = {"Name"};
				JTable theTable = new JTable(tableRow,tableCol);
				JOptionPane.showMessageDialog(null, new JScrollPane(theTable));
				session.getTransaction().commit();
			}
			/**
			 * Item 3: Print names of students who are mentored by Min Tuyet
			 */
			else if(option.equals("3"))
			{
				hql = "select s from Student s";
				List result = session.createQuery(hql).list();
				Object[][] tableRow = new Object[result.size()][1];
				int j = 0;
				for(int i=0;i<result.size();i++)
				{
					Student theStudent = (Student)result.get(i);
					Instructor is = theStudent.getMentorid();
					if(is.getName().equals("Min Tuyet")) {
						tableRow[j][0] = theStudent.getName();
						j++;
					}
					
									
				}
				Object[] tableCol = {"Student Name"};
				JTable theTable = new JTable(tableRow,tableCol);
				JOptionPane.showMessageDialog(null, new JScrollPane(theTable));
				session.getTransaction().commit();
			}
			/**
			 * Item 4: Print the average GPA of students mentored by Min Tuyet
			 */
			else if(option.equals("4"))
			{
				hql = "select s from Student s";
				List result = session.createQuery(hql).list();
				Object[][] tableRow = new Object[1][1];
				double sum = 0;
				int j = 0;
				for(int i=0;i<result.size();i++)
				{
					Student theStudent = (Student)result.get(i);
					Instructor is = theStudent.getMentorid();
					if(is.getName().equals("Min Tuyet")) {
						j++;
						sum = theStudent.getGpa() + sum;
					}
					
									
				}
				tableRow[0][0] = sum/j;
				Object[] tableCol = {"Avg GPA"};
				JTable theTable = new JTable(tableRow,tableCol);
				JOptionPane.showMessageDialog(null, new JScrollPane(theTable));
				session.getTransaction().commit();
			}
			/**
			 * Item 5. List the Name, and Rank of faculty who have a salary of 100K or higher.
			 */
			else if(option.equals("5"))
			{
				hql = "select i from Instructor i";
				Query query = session.createQuery(hql);
				List result = query.list();
								
				String toShow = "";
				Object[][] tableRow = new Object[result.size()][2];
				int j = 0;
				for(int i=0;i<result.size();i++)
				{
					Instructor theI = (Instructor)result.get(i);
					if(theI.salary >= 100000) {
						tableRow[j][0] = theI.name;
						tableRow[j][1] = theI.getRank();
						j++;
					}
				}
				Object[] tableCol = {"Name","Rank"};
				JTable theTable = new JTable(tableRow,tableCol);
				JOptionPane.showMessageDialog(null, new JScrollPane(theTable));
				session.getTransaction().commit();
			}
			/**
			 * Item 6. List Names of students and their mentors.
			 */
			else if(option.equals("6"))
			{
				hql = "select s from Student s";
				List result = session.createQuery(hql).list();
				Object[][] tableRow = new Object[result.size()][2];
				int j = 0;
				for(int i=0;i<result.size();i++)
				{
					Student theStudent = (Student)result.get(i);
					Instructor is = theStudent.getMentorid();
					tableRow[i][0] = theStudent.getName();
					tableRow[i][1] = is.getName();
									
				}
				Object[] tableCol = {"Student Name", "Mentor Name"};
				JTable theTable = new JTable(tableRow,tableCol);
				JOptionPane.showMessageDialog(null, new JScrollPane(theTable));
				session.getTransaction().commit();
			}
			/**
			 * Item 9: Insert a Person in the database: Name: Briggs Jason; ID: 480293439; Address: 215, North Hyland Avenue; date of birth: 15th January 1975.
			 */
			else if(option.equals("7"))
			{
				 Date date = null;
					try {
						date = new SimpleDateFormat("yyyy-MM-dd").parse("1975-01-15");
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Person p = new Person("480293439", "Briggs Jason",date,"215, North Hyland Avenue");
					session.save(p);
					String toShow = "Person" + p.name + "Has been saved";
					JOptionPane.showMessageDialog(null, toShow);
					session.getTransaction().commit();
				
			}
			/**
			 * Item 10: Increase faculty Min Tuyet's salary by 10%.
			 */
			else if(option.equals("8"))
			{
				List result = session.createQuery("from Instructor i where i.id =201586985").list();
				Instructor theInstructor = (Instructor)result.get(0);
				int newSalary = (int) (theInstructor.salary * 1.1);
				theInstructor.setSalary(newSalary);
				String toShow = "Instructor " + theInstructor.name + "'s salary has been updated";
				JOptionPane.showMessageDialog(null, toShow);
				session.getTransaction().commit();
				
			}
			/**
			 * Item 11: Delete student with ID 118784412.
			 */
			else if(option.equals("9"))
			{	
				String toShow = "";
				String StudentID = "118784412";
				List result = session.createQuery("from Student s where s.id = :pid").setParameter("pid", StudentID).list();
				if(result.size() == 1)
				{
					Student theStudent = (Student)result.get(0);
					session.delete(theStudent);
					toShow += "Student with ID: " + StudentID + " has been deleted from Student Table.";
				}
				else
				{
					toShow += "Student with Id: " + StudentID + " does not exist in the database.";
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