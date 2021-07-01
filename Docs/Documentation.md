#User Documentation
Welcome to the Group 11 Employee Management System (GiiMMS).
This short guide is intended to give an overview of the functions and possible uses.

#Terminal (login page)
Upon entering the application you are greeted with the therminal panel. Its purpose is logging in employees, as well as admins. 

#Basic Usage as Employee (User)
After logging in as an employee, you get to the main panel of the application. At the top left you can see all the information saved for this person - username, given and family names, their position at company and availability. 
Under the data are the controls - an Employee can change their availability or add data - what he did today, or general information about the day. They can also export all their data in a format of choice - JSON or CSV. Here the Employee can also log out and he is given a messsage, if the login was successful. 
On the right side of the profile panel the Employee cah see their last saved location, or if available the current one.

#OneTimePassword feature
When a new Employee is registered he is given a one time password from the admin. Upon first login the Employee is greeted with the 'changePassword' panel that prompts the Employee to change the OTP to a password that he defined.

#Basic Usage as Admin
After a successful login through the terminal as an admin you are greeted with a similar layout. On the left side there is all the personal data for the admin - given and family name, username and position in the company.
Under them there are two buttons - 'Add Employee' and 'Logout'. The latter is and does the same as in the Employee page. The former takes you to the panel, where and admin can add a new Employee in the company.
On the right side is a table with all Employees in the company and their core data - username, names, position and availability. In the last column for every person there is a button to see their profile that takes you to the desired Employee.

#Add new Employee
A new employee can be added on this page. It consists of five fields - username, password, first name, 
last name and position. All of them are mandatory and if at least one is not filled the Employee cannot be created. Under the fields are three buttons for adding the Employee, clearing all fields and going back to the admin page. The created Employee then appears in the list of Employees immediately at the bottom of the table.