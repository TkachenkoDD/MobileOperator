# JavaSchoolDT

This is an application that simulates the operation of the information system of a mobile operator.

The app provides the following functionality:
• For clients of the company:
o Viewing the contract in your personal account;
o View all possible tariffs for the transition, change the tariff;
o Viewing possible options for the tariff, connecting new options, disabling existing ones;
o Blocking / unblocking the number (if the number is blocked, then it is impossible to change the tariff and options; if the number was blocked not by the user, then he cannot unblock it himself);

• For company employees:
o Concluding a contract with a new client: choosing a new phone number with connecting the tariff and options. The phone number must be unique.
o View all users and contracts;
o User blocking / user unblocking;
o Search for a user by number;
o Change of tariff, connection and disconnection of options to the user;
o Adding new tariffs, deleting old ones;
o Add / remove options for the tariff;
o Option management: some options may be incompatible with each other or may be added only with certain options, employee adding and removing these rules.
When performing actions with contracts on each page, before saving changes, a basket should be displayed, in which the selected positions of the user are displayed.

Technology stack:
• Tomcat
• DB - PostgreSQL
• Maven
• JPA
• Spring MVC
• JSP
