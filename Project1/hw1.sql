# Item 1 - Person Table 
create table Person (
Name char(20),
ID char(9) not null,
Address char(30),
DOB date) ;
# Item 2 - Instructor table 
create table Instructor (
InstructorID char(9) not null,
Rank char(12),
Salary int ) ;
# Item 3 - Student Table
create table Student (
StudentID char(9),
Classification varchar(10),
GPA double,
MentorID char(9),
CreditHours int ) ;
# Item 4 - Course Table 
create table Course (
CourseCode char(6) not null,
CourseName char(50),
PreReq char(6) ) ;
# Item 5 - Offering Table
create table Offering (
CourseCode char(6) not null,
SectionNo int not null,
InstructorID char(9) not null ) ;
# Item 6 - Enrollment Table 
create table Enrollment (
CourseCode char(6) not null,
SectionNo int not null,
StudentID char(9) not null references Student,
Grade char(4) not null,
primary key (CourseCode, StudentID)) ;
# Adding Primary and foreign keys to tables
alter table Person add primary key (ID) ;
alter table Instructor add primary key (InstructorID) ;
alter table Student add primary key (StudentID) ;
alter table Course add primary key (CourseCode, PreReq) ;
alter table Offering add primary key (CourseCode, SectionNo) ;
alter table Instructor add foreign key (InstructorID) references Person (ID) ;
alter table Student add foreign key (StudentID) references Person (ID) ; 
alter table Student add foreign key (MentorID) references Instructor (InstructorID) ;
alter table Offering add foreign key (InstructorID) references Instructor (InstructorID) ;
alter table Enrollment add foreign key (CourseCode) references Offering (CourseCode) ;
alter table Enrollment add foreign key (SectionNo) references Offering (SectionNo) ;
#Item 7 - Loading Person
load xml local infile 'Z:/ComS363//Person.xml'
into table Person
rows identified by '<Person>';
#Item 8 - Loading Instructor
load xml local infile 'Z:/ComS363//Instructor.xml'
into table Instructor
rows identified by '<Instructor>';
# Item 9 - Loading Student 
load xml local infile 'Z:/ComS363//Student.xml'
into table Student
rows identified by '<Student>';
#Item 10 - Loading Course 
load xml local infile 'Z:/ComS363//Course.xml'
into table Course
rows identified by '<Course>';
#Item 11 - Loading Offering 
load xml local infile 'Z:/ComS363//Offering.xml'
into table Offering
rows identified by '<Offering>';
#Item 12 - Loading Enrollment 
load xml local infile 'Z:/ComS363//Enrollment.xml'
into table Enrollment
rows identified by '<Enrollment>';
#Item 13 
select distinct StudentID, MentorID from Student s where  s.GPA > 3.8 and s.Classification = "junior" or s.Classification = "senior" ;
#Item 14
select distinct CourseCode, SectionNo from Enrollment e, Student s where e.StudentID = s.StudentID and s.Classification = "sophomore" ;
#Item 15
select distinct Name, Salary from Person p, Instructor i, Student s where p.ID = i.InstructorID and s.MentorID = i.InstructorID and s.Classification = "freshman" ;
#Item 16 
select distinct sum(Salary) from Instructor i where i.InstructorID not in (select o.InstructorID from Offering o) ;
#Item 17
select Name, DOB from Student s, Person p where year(p.DOB) = 1976 and s.StudentID = p.ID ;
#Item 18 
select Name, Rank from Person p, Instructor i where p.ID = i.InstructorID and i.InstructorID not in (select o.InstructorID from Offering o) and i.InstructorID not in (select s.MentorID from Student s) ;
#Item 19
select ID, Name, DOB from Person p, Student s where p.ID = s.StudentID and p.DOB = (select max(DOB) from Person);
#Item 20
select ID, DOB, Name from Person p where p.ID not in (select i.InstructorID from Instructor i) and p.ID not in (select s.StudentID from Student s) ;
#Item 21 
select InstructorDetails.Name, Count(*) totalCount from Instructor i inner join Person as InstructorDetails on InstructorDetails.ID = i.InstructorID inner join Student as Mentees on Mentees.MentorID = i.InstructorID group by InstructorDetails.Name ;
#Item 22 
select Count(StudentID), Avg(GPA), Classification from Student group by Classification ;
#Item 23
select CourseCode, Count(StudentID) from Enrollment group by CourseCode order by 1 limit 1 ; 
#Item 24
select s.StudentID, s.MentorID from Student s, Offering o, Enrollment e where e.StudentID = s.StudentID and o.InstructorID = s.MentorID and o.CourseCode = e.CourseCode ;
#Item 25 
select StudentID, Name, CreditHours from Student s, Person p where p.ID = s.StudentID and s.Classification = "Freshman" and Year(p.DOB) >= 1976 ; 
