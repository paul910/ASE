# ASE 2023 - Travel Planner
## Setup
- Add API Key as ```WEATHER_API_KEY``` to System Environment Variables (DemoKey: ```83295de4f821683d81829044bf82d3eb```)
- Add API Key as ```YELP_API_KEY``` to System Environment Variables (DemoKey: ```e7Ecbfhs5HnXLq8qCBQw7O_ZVXTNoE5f_u7Cg7jR-1QSNO0_ERR-U6zXEdWcfizyDocn_WiV39d1RtAavsMFbxXWzILZEVeQWLnU2gvEAg_GcStUFcaUAUchKRwKZHYx```)

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

Die Entitäten repräsentieren die Geschäftsregeln und -logik der Anwendung. Sie sind am zentralsten und sind völlig unabhängig von anderen Schichten.

Der Anwendungsfall beschreibt, was die Software tun soll, ohne sich um die Details der Implementierung zu kümmern.

Die Schnittstellenadapter-Schicht umfasst Adapter, die die Anwendungsfälle und Entitäten mit den externen Agenten wie Datenbanken, Webdiensten oder Benutzerschnittstellen verbinden.

Die äußerste Schicht, Frameworks & Treiber, umfasst Tools wie Datenbanken und Web-Frameworks, die die Infrastruktur der Anwendung bereitstellen.

Die Hauptvorteile der Clean Architecture liegen in ihrer Flexibilität und Testbarkeit. Da jede Schicht von den anderen entkoppelt ist, kann sie unabhängig getestet und modifiziert werden, ohne die anderen Schichten zu beeinflussen. Dies erleichtert die Wartung der Software und ermöglicht eine einfache Integration von Änderungen und neuen Technologien.

Zusammenfassend kann man sagen, dass die Clean Architecture ein wirkungsvoller Ansatz zur Schaffung robuster, nachhaltiger und wartbarer Softwarelösungen ist, die auch auf lange Sicht noch effizient sind und sich gut erweitern lassen.