# ASE 2023 - Travel Planner

## Kapitel 1: Einführung

### 1.1. Übersicht über die Applikation

In der heutigen Welt, in der die Reiseplanung oft eine Vielzahl von Ressourcen und Plattformen erfordert - von der Suche nach dem perfekten Reiseziel, über die Buchung von Tickets und Unterkünften, bis hin zur Planung von Aktivitäten vor Ort - kann das Organisieren einer Reise eine komplexe und zeitaufwändige Aufgabe sein. Es ist genau dieses Problem, das unsere Applikation, "TravelPlaner", angeht und zu lösen versucht.

TravelPlaner ist mehr als nur eine Reiseplanungs-App. Es ist eine umfassende Plattform, die darauf abzielt, die Reiseerfahrung von Anfang bis Ende zu optimieren. Sie kombiniert alle Aspekte der Reiseplanung und -verwaltung in einer einzigen, benutzerfreundlichen Anwendung, die den Benutzern ermöglicht, ihre Ausflüge auf effiziente und intuitive Weise zu organisieren.

Das Herzstück der TravelPlaner-Anwendung ist die personalisierte Benutzererfahrung. Die Anwendung erkennt, dass jede Reise einzigartig ist und ermöglicht es den Benutzern daher, ihre eigenen spezifischen Reisepläne zu erstellen und zu verwalten. Benutzer können sich anmelden und eine Reihe von Funktionen nutzen, die entwickelt wurden, um den Reiseplanungsprozess zu rationalisieren und zu personalisieren:

1. **Get Travel Overview:** Mit dieser Funktion können Benutzer eine Übersicht über ihre geplanten Reisen erhalten. Dies umfasst Informationen wie das Reiseziel, geplante Aktivitäten, Transportmittel und möglicherweise auch Details zu Unterkünften und gebuchten Tickets. 
2. **Add Travel:**  Hier können Benutzer neue Reisen zu ihrem Plan hinzufügen. Sie können Details wie das Reiseziel, das Datum und die gewünschten Aktivitäten eingeben.
   Remove Travel: Diese Funktion ermöglicht es Benutzern, eine geplante Reise aus ihrem Plan zu entfernen. Dies könnte nützlich sein, wenn sich Pläne ändern oder ein Reiseziel aus irgendeinem Grund nicht mehr attraktiv ist.
3. **Remove Travel:** Diese Funktion ermöglicht es Benutzern, eine geplante Reise aus ihrem Plan zu entfernen. Dies könnte nützlich sein, wenn sich Pläne ändern oder ein Reiseziel aus irgendeinem Grund nicht mehr attraktiv ist. 
4. **Manage Travel Activities:** Hier können Benutzer die Aktivitäten für jede ihrer geplanten Reisen verwalten. Sie können neue Aktivitäten hinzufügen, bestehende Aktivitäten ändern oder löschen.
5. **Check Weather:** Diese Funktion ermöglicht es Benutzern, das aktuelle Wetter an ihren Reisezielen zu überprüfen. So können sie sich besser auf ihre Reise vorbereiten und gegebenenfalls ihre Pläne anpassen.
6. **Check Activities:** Mit dieser Funktion können Benutzer eine Liste aller geplanten Aktivitäten einsehen. Das könnte ihnen helfen, ihre Zeit besser zu planen und sicherzustellen, dass sie nichts verpassen.
7. **Logout:** Nachdem die Benutzer ihren Reiseplan abgeschlossen und alle benötigten Informationen erhalten haben, können sie sich sicher von der Plattform abmelden.

Die TravelPlaner-App nutzt die neuesten Technologien, einschließlich einer Reihe von APIs, um Benutzern aktuelle und genaue Informationen zu liefern. Dies umfasst alles von Wettervorhersagen bis hin zu Aktivitäten und Ereignissen an den Reisezielen. Mit TravelPlaner haben Reisende alle Informationen, die sie benötigen, zur Hand - was die Reiseplanung einfacher, stressfreier und insgesamt effizienter macht.

Im Großen und Ganzen zielt TravelPlaner darauf ab, das oft chaotische und zeitaufwändige Unterfangen der Reiseplanung zu revolutionieren. Indem es eine One-Stop-Lösung für alle Reisebedürfnisse bietet, ermöglicht es den Reisenden, weniger Zeit mit der Planung zu verbringen und mehr Zeit damit, ihre Reisen zu genießen.


### 1.2. Wie startet man die Applikation?

TravelPlaner ist eine CLI-Anwendung, die in Java 19 geschrieben wurde. Um die Anwendung auszuführen, ist ein Computer mit Java 19 oder höher erforderlich. Die Anwendung ist bereits vollständig konfiguriert, einschließlich aller notwendigen API-Schlüssel, sodass nur der Start der Hauptanwendung erforderlich ist.

Zunächst muss das GitHub-Repository auf den lokalen Rechner geklont werden. Dazu öffnen Sie ein Terminalfenster und geben folgenden Befehl ein: 

```bash
git clone https://github.com/paul910/ASE.git
```

Die Anwendung kann dann im nächsten Schritt über ein Konsolenfenster mit dem folgenden Befehl gestartet werden:

```bash
cd ASE/src/main/java/org/example
java -jar Main.java
```

Im Anschluss kann mit der gestarteten Anwendung interagiert werden.


### 1.3. Wie testet man die Applikation?

Um die Funktionalität von Travel Planner zu testen, wird die Anwendung über ein Konsolenfenster gestartet. Travel Planner bietet eine klare und intuitive Benutzeroberfläche, die dem Benutzer numerische Optionen zur Interaktion präsentiert.

Nach dem Start wird der Nutzer von einer Einführung in die Anwendung begrüßt, in der er die Möglichkeit hat, sich zu registrieren, sich anzumelden oder die Anwendung zu beenden. Bei der Erstnutzung wird empfohlen, einen neuen Benutzer zu registrieren. Die dafür erforderlichen Schritte werden im Dialogprozess klar kommuniziert und sind unkompliziert zu befolgen.

Sobald der Nutzer angemeldet ist, wird er mit einer Reihe von Optionen begrüßt, die den Kern der Travel Planner-Funktionalität darstellen. Er hat die Möglichkeit, eine Übersicht seiner geplanten Reisen einzusehen, neue Reisen hinzuzufügen und bestehende zu entfernen. Darüber hinaus kann er Reiseaktivitäten verwalten, das Wetter an seinem Reiseziel prüfen und vorgeschlagene Aktivitäten überprüfen. Die jeweiligen Optionen werden durch numerische Eingaben ausgewählt.

Es ist zu beachten, dass bei der ersten Anmeldung keine Reisen in der Übersicht vorhanden sind. Daher wird empfohlen, als ersten Schritt eine neue Reise hinzuzufügen, um alle Funktionen vollständig nutzen zu können.

Travel Planner speichert persistent alle Daten, die während der Nutzung anfallen, einschließlich der Reisedetails und geplanten Aktivitäten, in einem lokalen Speicher. Stellen Sie sicher, dass Sie die entsprechenden Rechte zum Erstellen und Schreiben von Dateien und Ordnern im aktuellen Arbeitsverzeichnis haben. Andernfalls kann es zu Fehlern bei der Nutzung der Anwendung kommen.

Im Großen und Ganzen lässt sich Travel Planner leicht testen und bedienen, wodurch das Organisieren Ihrer zukünftigen Reisen vereinfacht und angenehmer gestaltet wird


## 2. Clean Architecture

### 2.1. Was ist Clean Architecture?

Clean Architecture ist ein Softwarearchitektur-Konzept, das von Robert C. Martin, auch bekannt als "Uncle Bob", entwickelt wurde. Dieser Ansatz zielt darauf ab, die Entwicklung von Softwareanwendungen mit hoher Wartbarkeit, Testbarkeit und Flexibilität zu fördern, indem bestimmte Prinzipien und Designmuster eingehalten werden.

Im Kern der Clean Architecture steht die Idee der Trennung von Anliegen durch Schichten. Diese Architektur besteht in der Regel aus mindestens vier konzentrischen Schichten, von innen nach außen: Entitäten, Anwendungsfall, Schnittstellenadapter und Frameworks & Treiber. Jede dieser Schichten hat eine bestimmte Rolle und Verantwortung und sollte voneinander unabhängig sein. Dies wird als Regel der Abhängigkeiten bezeichnet, was bedeutet, dass die Abhängigkeiten immer von den äußeren Schichten zu den inneren Schichten gerichtet sind.

Die **Entitäten** repräsentieren die Geschäftsregeln und -logik der Anwendung. Sie sind am zentralsten und sind völlig unabhängig von anderen Schichten. Der **Anwendungsfall** beschreibt, was die Software tun soll, ohne sich um die Details der Implementierung zu kümmern. Die **Schnittstellenadapter**-Schicht umfasst Adapter, die die Anwendungsfälle und Entitäten mit den externen Agenten wie Datenbanken, Webdiensten oder Benutzerschnittstellen verbinden. Die äußerste Schicht, **Frameworks & Treiber**, umfasst Tools wie Datenbanken und Web-Frameworks, die die Infrastruktur der Anwendung bereitstellen. Die Hauptvorteile der Clean Architecture liegen in ihrer Flexibilität und Testbarkeit. Da jede Schicht von den anderen entkoppelt ist, kann sie unabhängig getestet und modifiziert werden, ohne die anderen Schichten zu beeinflussen. Dies erleichtert die Wartung der Software und ermöglicht eine einfache Integration von Änderungen und neuen Technologien.

Zusammenfassend kann man sagen, dass die Clean Architecture ein wirkungsvoller Ansatz zur Schaffung robuster, nachhaltiger und wartbarer Softwarelösungen ist, die auch auf lange Sicht noch effizient sind und sich gut erweitern lassen.

### 2.2 Analyse der Dependency Rule

Die Dependency Rule ist ein wichtiger Grundsatz der Clean Architecture und besagt, dass Codeabhängigkeiten immer von außen nach innen gerichtet sein sollten, wobei die inneren Schichten keinerlei Wissen über die äußeren Schichten haben sollten. Dies bedeutet, dass höhere Schichten (wie Anwendungsfälle und Entitäten) nicht von niedrigeren Schichten (wie Frameworks und Interfaces) abhängig sein sollten.

Um ein besseres Verständnis von dieser Regel zu erlangen, betrachten wir zwei Klassen unserer Anwendung, eine, die die Dependency Rule einhält (Positivbeispiel), und eine, die sie verletzt (Negativbeispiel).

**Positiv-Beispiel:** WeatherAPI

Die WeatherAPI-Klasse ist ein guter Fall für die Einhaltung der Dependency Rule in der Clean Architecture. Diese Klasse ist in einer niedrigeren Schicht unserer Anwendung platziert und definiert nur, wie eine Wetter-API-Anfrage ausgeführt wird. Das bedeutet, sie ist für die Kommunikation mit der externen Wetter-API verantwortlich und kümmert sich nicht um Details der oberen Schichten oder um die Art der Daten, die von der API zurückgegeben werden.

Abhängigkeiten von WeatherAPI: Da sie sich in der äußersten Schicht der Anwendung befindet, hat sie keine Abhängigkeiten von anderen internen Klassen der Anwendung. Ihre einzige Abhängigkeit könnte eine externe Bibliothek oder ein Framework sein, das für die Kommunikation mit der Wetter-API verwendet wird. Diese Art von Abhängigkeit ist jedoch in Ordnung, da sie nicht die Trennung zwischen den Schichten der Anwendung beeinträchtigt.
Abhängigkeiten zu WeatherAPI: Die WeatherService-Klasse in einer höheren Schicht hängt von WeatherAPI ab. Sie nutzt WeatherAPI, um Wetterdaten zu holen und diese Daten dann für die spezifischen Bedürfnisse der Anwendung zu verarbeiten. Die Abhängigkeit ist hier von der höheren zur niedrigeren Schicht gerichtet, was der Dependency Rule entspricht.

**Negativ-Beispiel:** Travel

Die Travel-Klasse stellt ein Domainobjekt dar und sollte daher keine direkte Abhängigkeit von TravelRepository haben, einer Klasse, die für die Datenpersistenz zuständig ist. Die Kopplung dieser beiden Klassen verringert die Flexibilität und Testbarkeit der Anwendung.

Abhängigkeiten von Travel: Die Travel-Klasse hat eine direkte Abhängigkeit von TravelRepository, was bedeutet, dass sie sich um Details der Datenpersistenz kümmert. Das widerspricht der Dependency Rule, da Domainobjekte unabhängig von solchen Details bleiben sollten. Ihre Hauptaufgabe ist es, Geschäftsregeln zu definieren und durchzusetzen, und sie sollten durch Services oder andere Mechanismen auf persistente Daten zugreifen können.
Abhängigkeiten zu Travel: Services oder andere Domain-Objekte könnten von Travel abhängen, um Geschäftsaktionen auszuführen oder um Daten zu verarbeiten, die sie benötigen. In einer gut strukturierten Anwendung wäre diese Art von Abhängigkeit von der oberen zur unteren Schicht gerichtet, was der Dependency Rule entspricht.

### 2.3. Analyse der Schichten

#### Schicht: Entities

**Klasse: Travel**

Die Travel-Klasse repräsentiert eine Reise, die von einem Benutzer erstellt wurde. Jede Reise hat eine eindeutige ID, einen Ersteller, ein Erstellungsdatum, eine Stadt, ein Budget, ein Start- und Enddatum sowie ein letztes Änderungsdatum.

Die Travel-Klasse enthält Methoden zur Verwaltung dieser Eigenschaften, einschließlich Setzen und Abrufen von Werten, sowie zur Konvertierung der Reise in eine CSV-Zeichenkette. Sie holt auch eine neue eindeutige ID vom TravelRepository ab.

**Einordnung in die Clean-Architecture:**

Die Travel-Klasse gehört zur Schicht der "Entities" in der Clean Architecture. Die Entities repräsentieren die Geschäftsobjekte der Anwendung und enthalten die Geschäftsregeln, die für die Anwendung relevant sind.

Die Travel-Klasse repräsentiert ein Geschäftsobjekt, nämlich eine Reise. Sie enthält Geschäftsregeln wie das Format der Reisedaten und das Format des Datums. Sie enthält jedoch auch eine direkte Abhängigkeit zum TravelRepository, was gegen die Dependency Rule in der Clean Architecture verstößt, da die Entities nicht von den äußeren Schichten wie der Datenzugriffsschicht abhängig sein sollten. Dies sollte durch Verwendung eines Interfaces oder einer abstrakten Klasse korrigiert werden, um die direkte Abhängigkeit von Travel zu TravelRepository zu entfernen.

#### Schicht: Interface Adapters

**Klasse: WeatherAPI**

Die WeatherAPI-Klasse ist ein Adapter, der es der Anwendung ermöglicht, mit der OpenWeatherMap API zu interagieren. Sie enthält Methoden zur Erstellung einer API-Anfrage und zur Rückgabe der Antwort als String.

Die WeatherAPI-Klasse hat eine request-Methode, die einen Städtenamen als Eingabe nimmt und eine HTTP-GET-Anfrage an die OpenWeatherMap API sendet. Sie verarbeitet die Antwort, prüft den Antwortcode und gibt den Antwort-String zurück.

**Einordnung in die Clean-Architecture:**

Die WeatherAPI-Klasse gehört zur Schicht der "Interface Adapters" in der Clean Architecture. Diese Schicht beinhaltet Adapter, die das Außen- und Innensystem miteinander verbinden, wobei das Innensystem die Business-Logik und die Anwendungsfall-Schicht darstellt, während das Außensystem Dinge wie externe APIs, Datenbanken und Web-Frameworks darstellt.

In diesem Fall dient die WeatherAPI-Klasse als Adapter zur OpenWeatherMap API. Sie konvertiert die Details der HTTP-Kommunikation (ein Detail, das für die innere Anwendungslogik irrelevant ist) in eine einfache Methode, die von der Anwendung verwendet werden kann, um Wetterdaten zu erhalten. Daher kann diese Klasse als "Interface Adapter" klassifiziert werden, da sie das Interface (die API) zur Außenwelt bereitstellt und anpasst, um es den inneren Schichten leichter zu machen, damit zu interagieren.