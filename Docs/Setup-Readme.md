#User Documentation
Welcome to the Group 11 Employee Management System (GiiMMS).
This short guide is intended to give an overview of the functions and possible uses.

#Terminal (login page)  
Upon entering the application, you are greeted with the terminal panel. Its purpose is logging in employees, as well as admins.

#OneTimePassword feature
When a new Employee is registered, he is given a one-time password from the admin. Upon first login the login panel is expanded, and the Employee must enter his new password twice. If they match, he is greeted with his corresponding page.

#Basic Usage as Employee (User)
After logging in as an employee, you get to the main panel of the application. At the top left you can see all the information saved for this person - username, given and family names, their position at company and availability. 
Under the data are the controls - an Employee can change their availability or add data - what he did today, or general information about the day. They can also export all their data in a format of choice - JSON or CSV. Here the Employee can also log out and he is given a message, if the login was successful. There is a functionality to change the username. 
On the right side of the profile panel the Employee can see their last saved location, or if available the current one.

#Basic Usage as Admin
After a successful login through the terminal as an admin you are greeted with a similar layout. On the left side there is all the personal data for the admin - given and family name, username and position in the company.
Under them there are three buttons - 'Add a new Employee', 'Official Terminals' and 'Logout'. The third one is and does the same as in the Employee page. The other two are explained in detail in the next two sections.
On the right side is a table with all Employees in the company and their core data - username, names, position and availability. In the last column for every person there is a button to see their profile that takes you to the desired Employee.

#Add new Employee
A new employee can be added on this page. It consists of five fields - username, password, first name, 
last name and position. All of them are mandatory and if at least one is not filled the Employee cannot be created. The password has special requirements - more than 6 and less than 20 characters, one of which must be a special one. Under the fields are three buttons for adding the Employee, clearing all fields and going back to the admin page. The created Employee then appears in the list of Employees immediately at the bottom of the table.

#Official Terminals
Here lie all official terminals of the company. It is a panel with similar layout:
On the left side are two buttons - 'Back to Admin Panel' and 'Add official Terminal'. The former is self-explanatory, and the latter takes the admin to a new panel with the same name. 
On the right side there is a table with all official terminals of the company, ordered by date. It shows the id of the terminal, the branch name and precise location.

#Add official Terminal
In this small panel there are three fields and three buttons. The branch name, latitude and longitude are required to be entered here. The buttons add the new terminal, clear the fields, or take you back to the official terminals page respectively.

#HowToRun
If you want to run the Application either:
    - use the war file and execute it by
    changing directory where GiiMMS-0.0.1-SNAPSHOT.war is located and run it by 
    using java: **java -jar GiiMMS-0.0.1-SNAPSHOT.war**
    - use maven:spring-boot:run with this project openened in a supported 
    IDE    
    

