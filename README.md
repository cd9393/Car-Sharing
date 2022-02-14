# Car-Sharing
## Description
Great, we have a database! Now, let's implement the ability to actually work with it from your program. In this stage, you will create a user-friendly menu that allows you to log in as a manager, create companies, and save them in the database.

Here is the Data Access Object Pattern that allows you to get the data from the database as a Java object. This can make working with the database easier.
## Objectives
Update your database and add constraints to the COMPANY table columns:

ID column should be PRIMARY KEY and AUTO_INCREMENT.
NAME column should be UNIQUE and NOT NULL.
Column types should be the same as in the previous stage.
Implement the menu with the following options:

1. Log in as a manager
0. Exit
If the option Exit was chosen, the program should stop. In case the user chose Log in as a manager, the following menu should be printed:

1. Company list
2. Create a company
0. Back
Now, if the user chose to go Back, the program should print the main menu. Company list should print the list of companies sorted by their IDs. Their indexes start from 11, for example:

Company list:
1. First company name
2. Second company name
3. Third company name
If there are no companies, print The company list is empty!.

If the option Create a company was chosen, the program should prompt the user to enter a company name with the message Enter the company name:, read the company name, and save it to the database.

Note that a list numeration should always start with 1.
