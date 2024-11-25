# Gruppuppgift: Refaktorering
# Ali, Johan, Kalid, Angelica, Erik

Vi började med att kolla igenom Main-koden som vi fått och försökte identifiera problemen.
- Oläsbar
- Nästlade villkor
- Single responsibility principle (SRP)
- Environment Configuration

Koden var otydlig och svår att läsa samt att det var mycket överflödig kod. Detta gör det svårare att underhålla och felsöka.
Det första vi gjorde var att separera ansvar och skapa olika klasser. Vi skapade också en env.fil.
Databaskonfigurationen var hårdkodad i Main så vi skapade DatabaseConfig som används för att hämta databasinställningar från
våran env.fil (dotenv). Detta ökar säkerheten genom att hålla känslig information utanför källkoden. 

Sedan skapade vi DatabaseConnection som används för att hantera databasanslutningen. Vi använde oss av hibernate (ORM) istället
för jdbc. Med hibernate kan man arbeta med databaser genom att använda Java-objekt istället för att skriva SQL-kod samt att
det finns möjligheter att arbeta med andra typer av databaser i framtiden, det är enklare att underhålla. Denna klassen
skapar också en SessionFactory för att öppna sessioner(anslutningar till databasen).

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

//