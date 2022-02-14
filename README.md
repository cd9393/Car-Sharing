# Car-Sharing
## Description
One of the features of relational databases is, quite obviously, the relations between the tables. Let's create a table CAR that relates to a particular company. The data in this table should be linked to the COMPANY table with a foreign key.

Since one company can have more than one car but one car can only belong to one company, the appropriate table relation model is One to Many.

## Objectives
Create another table named CAR with the following columns:

ID column should be PRIMARY KEY and AUTO_INCREMENT with the type INT.
NAME column should be UNIQUE and NOT NULL with the type VARCHAR.
COMPANY_ID column should be NOT NULL with the type INT. This column should be a FOREIGN KEY referring to the ID column of the table COMPANY.
COMPANY table should be the same as in the previous stage.
Update the option Company list in the manager menu. Now, after showing the list of companies the program should prompt the user to choose one of them:

Choose a company:
1. First company name
2. Second company name
3. Third company name
Once the user has chosen a company, print the company menu:

'Company name' company:
1. Car list
2. Create a car
0. Back
If the user chose the option Car list, the program should print the list of cars that belong to the chosen company. If the car list is empty, print the message The car list is empty!. Otherwise, print the cars ordered by their ID column. Their indexes should start from 11, for example:

'Company name' cars:
1. First car name
2. Second car name
3. Third car name
After printing the car list, print the company menu again. If the option Create a car was chosen, the program should prompt the user for the car name and save it in the database. The COMPANY_ID column of that car should refer to the company where it was created.

If the Back option was chosen, go back and print the manager menu.

Note that a list numeration should always start with 1.
