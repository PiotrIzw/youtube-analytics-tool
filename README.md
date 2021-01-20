# Youtube Analytics Tool
Projekt oparty o architekturę REST wykonany w ramach zajęć z Inżynierii Internetu. Pozwala na sprawdzanie statystyk kanałów oraz filmów z serwisu YouTube.com poprzez podanie identyfikatora. Możliwość pobrania 10 ostatnich filmów danego kanału i ich statystyk, bądz pojedyńczego filmu. Możliwa aktualizacja filmów/kanałów bądź usunięcie. Użytkownik będący administratorem może dodatkowo wyświetlać informacje o zarejestrowanych użytkownikach oraz w razie potrzeby usunąć dane konto.
## Opis projektu
### Wykorzystane przy budowaniu projektu technologie
* Spring Boot + Security + Data + REST
* Relacyjna baza danych H2
* Zewnętrzne API YouTube (YouTube Data API V3)
* Maven jako system zarządzania projektem, dociągania zależności, budowania.
* Token JWT
* Postman w celu testowania oprogramowania.
### Możliwości
* Rejestracja użytkowników wraz z odpowiednią rolą.
* Autentyfikacja użytkownika poprzez podanie odpowiednich danych logowania.
* Dostęp do endpointów użytkownika danej grupy.
* 2 grupy użytkowników (ADMIN, USER).
* Wszystkie dane na temat użytkowników zapisywane w bazie.
## Uruchomienie
Projekt najlepiej uruchomić używając JDK 11 oraz IntelliJ Ultimate 2020. 
## Przykład
* Uruchomić program.
* Dodać użytkownika poprzez Postman lub inne oprogramowanie do testowania API.<br>
Wykonać żądanie POST na wkazanym URL:
```
localhost:8080/register
```
oraz w body wprowadzić.
```
{
    "username" : "admin",
    "password" : "pass",
    "role" : "ROLE_ADMIN"
}
```
* Dokonać autentyfikacji użytkownika pod adresem:
```
localhost:8080/authenticate
```
o treści:
```
{
    "username" : "admin",
    "password" : "pass"
}
```

* Skopiować zwrócony token i wprowadzić jako token autoryzacyjny do Postmana.
* Można wykorzystać przykładowe dane wykonując POST pod adresem:
```
localhost:8080/api/test
```
* Można także skorzystać z pozostałych endpointów, których implementacja znajduje się w poszczególnych klasach kontrolerów.
