# subscription-management-system
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Oracle](https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white)
Gestor de suscripciones y pagos desarrollado en Java 17, SpringBoot y SQL


SQL:
The sql structure is going to be this one:
![Database Structure JamesMartin](./assets/DATABASE%20STRUCTURE.png)

The dates fields in the Subscription table are going to be automatically calculated with a trigger.

Security:
-Users password are stored with BCrypt with a length of 255 to ensure privacy

Integrity:
-The fields email and DNI have constrainsts to prevent duplicate acounts
-In the price and duration field there is a check constraint to prevent negative data (values must be greater than 0)

Automatizacion:
-A trigger that calculates the end date of a subscription on any new subscription created

Performance:
-Implemented indexes for the foreigns keys in the subscription table to ensure fast performance in the searches