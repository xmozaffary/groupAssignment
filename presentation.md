# Gruppuppgift: Refaktorering
# Johan, Kalid, Angelica, Erik, Ali

## Erik
Vi började med att kolla igenom Main-koden som vi fått och försökte identifiera problemen.
- Oläsbar
- Nästlade villkor
- Single responsibility principle (SRP)
- Environment Configuration


Koden var otydlig och svår att läsa samt att det var mycket överflödig kod. Detta gör det svårare att underhålla och felsöka.
Det första vi gjorde var att separera ansvar och skapa olika klasser. Vi skapade också en env.fil.


## Angelica
Databaskonfigurationen var hårdkodad i Main så vi skapade DatabaseConfig som används för att hämta databasinställningar från
våran env.fil (dotenv). Detta ökar säkerheten genom att hålla känslig information utanför källkoden.
Sedan skapade vi DatabaseConnection som används för att hantera databasanslutningen. Vi använde oss av hibernate (ORM) istället
för jdbc. Med hibernate kan man arbeta med databaser genom att använda Java-objekt istället för att skriva SQL-kod samt att
det finns möjligheter att arbeta med andra typer av databaser i framtiden, det är enklare att underhålla. Denna klassen
skapar också en SessionFactory för att öppna sessioner(anslutningar till databasen).


## Kalid
Sedan skapade vi Todo-klassen. Hibernate använder denna klass för att hantera databastabellen todo och
möjliggör CRUD-operationer. 

Vi skapade ett DAO-interface. (TodoDAO). Sedan TodoDAOImpl för att undvika duplicerad kod för databasoperationer. Alla
CRUD-operationer är hanterade i DAO-klassen.

Sedan skapade vi TodoService som fungerar som ett mellanlager mellan DAO och användargränssnittet, vilket centraliserar
affärslogik. 

Vi implementerade vi MenuSystem som använder metoder som addTodo(), viewAllTodo(), updateTodo() och deleteTodo().



I slutet så rensade vi Main och initialiserade databaseconnection, TodoDAO, TodoService och MenuSystem. 

Vi testkörde sedan koden för att se att allt fungerade som det skulle, vilket det gjorde. 

Under grupparbetets gång så använde vi oss av CodeWithMe-funktionen i IntelliJ. 


Koden innehöll många nästlade if-satser som gör att logiken blir svår att följa och underhålla. För att förbättra detta använde vi oss
av en switch och metoder istället.


## Johan
# Uppdaterade krav - Val av databas vid start  H2/MySql

Vi skapade ett interface(DatabaseConnection) med en metod getSessionFactory. Vi skapade sedan H2Connection som fungerar på samma sätt som MySqlConnection.
Vi skapade sedan DatabaseConnectionFactory med en metod som heter createDatabaseConnection med en int parameter(int choice) och
om den får ettan så returnerar den en instans av MySqlConnection och om den får tvåan returnerar den instans H2Connection. 



## Ali
Vi gjorde ett nytt menysystem i Main för att man ska kunna välja databas vid start av applikationen.
Vi la till H2 dependency i pom-filen.
Vi utökade env.filen med H2-variabler.


# Problem

Att todo skulle få  false som defualt värde från början

displayMenu visades även om man hade en databaseConnection

SKRIV INTE VANLIGA // KOMMENTARER I ENV.FILEN!!!

Glömde att lägga till dependency för H2

Felstavning jättemycket

Att komma på en lösning

Vi kunde inte lösa detta problem som ibland printade INFO efter tabellen där man vill göra sina val:
Nov 27, 2024 11:36:44 AM org.hibernate.resource.transaction.backend.jdbc.internal.DdlTransactionIsolatorNonJtaImpl getIsolatedConnection
INFO: HHH10001501: Connection obtained from JdbcConnectionAccess [org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess@5b5dce5c] for (non-JTA) DDL execution was not in auto-commit mode; the Connection 'local transaction' will be committed and the Connection will be set into auto-commit mode.
Nov 27, 2024 11:36:44 AM org.hibernate.resource.transaction.backend.jdbc.internal.DdlTransactionIsolatorNonJtaImpl getIsolatedConnection
INFO: HHH10001501: Connection obtained from JdbcConnectionAccess [org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess@3d20e575] for (non-JTA) DDL execution was not in auto-commit mode; the Connection 'local transaction' will be committed and the Connection will be set into auto-commit mode.
