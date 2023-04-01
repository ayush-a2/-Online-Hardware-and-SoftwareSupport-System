# fallacious-cover-2527
Online Hardware and Software Support System
The Online Hardware and Software Support System is a web-based platform that allows employees to register hardware and software complaints, which can be attended to by engineers from the System Engineers Department. The system is accessible 24x7 and helps to keep the IT staff productive with fast, accurate, remote technical support for the organization's system environment.

Users
There are three types of users for this system:

Head of the Department (HOD)
Engineer
Employee
Roles and Features
Head of the Department (HOD)
Can log in to the system using their credentials
Can register a new engineer with a username (email), password, and category (hardware/software)
Can view a list of all registered engineers
Can delete any engineer from the system
Can view all raised problems
Can assign any problem to any engineer
Engineer
Can log in to the system using their credentials
Can view the problems assigned to them by the HOD
Can update the status of the problem addressed by them (solved or ongoing)
Can view a list of all problems attended by them
Can change their password
Employee
Can register themselves with their username and password
Can log in to the system using their credentials
Can register any complaint (hardware/software) through the system, after which a complaint ID is generated
Can view the status of their problem by using the complaint ID. Status means they can check who (engineer) is assigned to their problem
Can view all complaint history raised by them
Can change their password
Database
The following tables are used in this system:

employee: Contains information about registered employees such as employee ID, username, and password.
engineer: Contains information about registered engineers such as engineer ID, username, password, and category (hardware/software).
hod: Contains information about the HOD such as username and password.
problem: Contains information about the problems such as problem ID, complain ID, problem description, status, engineer ID, and engineer username.
employee_problem: A many-to-many relationship table between employees and problems to keep track of which employee has raised which problem, and which problems have been assigned to which engineers.
Technologies Used
Java
MySQL
Servlets
JSP
HTML
CSS
JavaScript
Getting Started
To get started with the Online Hardware and Software Support System, you will need to have a Java development environment set up on your machine, along with MySQL. Clone the project to your local machine and import it into your Java IDE. Run the SQL script to create the necessary database and tables in MySQL.

You can then run the project on a server, and access it through a web browser at http://localhost:<port>/.

Contributing
If you would like to contribute to the Online Hardware and Software Support System, feel free to fork the repository and submit a pull request with your changes.

