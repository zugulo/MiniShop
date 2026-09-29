# Azubi-Probeaufgabe: Android-Grundlagen in 3 Wochen

## Ziel

In drei Wochen soll eine kleine Android-App entstehen, mit der die Grundlagen der
Android-Entwicklung verständlich und praktisch erlernbar werden. Der Fokus liegt auf:

- Jetpack Compose
- Room
- API-Integration
- ViewModel
- Softwarearchitektur
- Clean Code

Die Aufgabe ist für eine Person ohne Android-Vorerfahrung gedacht. Ziel ist nicht „Feature-Masse",
sondern sauberes Arbeiten, nachvollziehbare Entscheidungen und stabile Grundstrukturen.

## Rahmenbedingungen

- Dauer: 3 Wochen
- Zielgruppe: Azubi ohne Android-Vorerfahrung
- Git-Workflow: GitFlow
- Commits: Conventional Commits
- Code und Kommentare: Englisch
- Fokus auf Lernfortschritt und saubere Umsetzung

## Projektidee: Mini-Shop

Die Aufgabe wird als kleiner Shop umgesetzt:

- Produktliste
- Produktdetail
- Warenkorb
- Favoriten oder einfache Merkliste
- Lokale Speicherung mit Room
- Datenbezug über eine API

Optional kann in Woche 3 ein kleines Stretch Goal ergänzt werden, z. B. Barcode-Scanning.

## Wochenplan

### Woche 1: Android-Basics und erste UI

Ziel: Projekt verstehen, erste Screens bauen, Datenfluss sauber aufsetzen.

**Inhalte**

- Projekt starten und Struktur verstehen ✅
- Compose-Grundlagen ✅
- Navigation zwischen Screens ✅
- einfache UI-Komponenten✅
- erstes State-Handling✅
- Basis-Architektur mit ViewModel✅

**Teilaufgaben**

**Feature-Branch `feature/project-setup`**

- Projekt lokal starten✅
- Version Catalog und Grundkonfiguration verstehen✅
- Basis-Pakete anlegen✅
- App-Name, Icons und Grundstruktur prüfen✅
- GitFlow-Branching nutzen✅

**Feature-Branch `feature/home-screen`**

- Startscreen für den Mini-Shop bauen✅
- Produktliste als statische Daten darstellen✅
- einfache Karten-Komponenten anlegen✅
- leere und geladene UI-Zustände unterscheiden✅

**Feature-Branch `feature/navigation-and-state`**

- Navigation zwischen Liste und Detail umsetzen✅
- erstes ViewModel für UI-State verwenden✅
- UI-State von Logik trennen✅

**Definition of Done Woche 1**

- App läuft fehlerfrei
- Produktliste wird in Compose sauber dargestellt
- Navigation funktioniert
- ViewModel wird sinnvoll genutzt
- Code ist lesbar und in Englisch verfasst
- Commits folgen Conventional Commits

### Woche 2: API, Room und Datenfluss

Ziel: Daten nicht mehr nur statisch anzeigen, sondern aus API und lokaler Datenbank laden.

**Inhalte**

- API-Calls mit sauberer Trennung von Schichten
- Datenmodelle und Mapping
- Room-Datenbank
- Repository-Pattern
- Lade-, Fehler- und Empty-States

**Teilaufgaben**

**Feature-Branch `feature/api-integration`**

- API-Client aufsetzen✅
- Produktdaten von einer externen API laden✅
- DTOs und Domain-Modelle trennen✅
- Fehlerbehandlung für Ladefehler ergänzen

**Feature-Branch `feature/local-cache`**

- Room-Datenbank anlegen✅
- Produkte lokal speichern✅
- Daten aus dem Cache anzeigen✅
- Synchronisationsstrategie einfach halten✅

**Feature-Branch `feature/product-detail`**

- Detailseite mit echten Daten füllen✅
- Preis, Beschreibung und Bild anzeigen✅
- Detaildaten über ViewModel bereitstellen✅

**Definition of Done Woche 2**

- Produkte kommen aus API und/oder lokalem Cache✅
- Room ist eingebunden und nutzt sinnvolle Entities/Daos✅
- Repository trennt Datenquelle und UI✅
- Fehler- und Ladezustände werden angezeigt✅
- Architektur bleibt nachvollziehbar und nicht überkompliziert✅

### Woche 3: Warenkorb, Qualität und Abschluss

Ziel: Die App wird zu einem kleinen, abgeschlossenen Mini-Shop mit robustem Abschluss.

**Inhalte**

- Warenkorb-Funktion
- State-Management über mehrere Screens
- Code-Qualität
- kleine Refactorings
- Abschluss-Dokumentation

**Teilaufgaben**

**Feature-Branch `feature/cart`**

- Produkte in den Warenkorb legen✅
- Menge ändern und entfernen✅
- Warenkorb-Zustand über ViewModel halten✅

**Feature-Branch `feature/favorites-or-search`**

- Optional: Favoriten✅
- alternativ: einfache Suche oder Filter✅
- UI-Verhalten konsistent halten✅

**Feature-Branch `feature/cleanup-and-quality`**

- Code aufräumen✅
- Namensgebung prüfen✅
- unnötige Duplikate entfernen✅
- Fehlerfälle und leere Zustände nachbessern✅

**Feature-Branch `feature/final-review`**

- README oder kurze Projektdoku ergänzen✅
- Architektur erklären✅
- wichtigste Entscheidungen dokumentieren✅
- bekannte Grenzen und mögliche nächste Schritte benennen✅

**Definition of Done Woche 3**

- Warenkorb funktioniert stabil
- App ist in einem vorzeigbaren Zustand
- Code ist strukturiert, lesbar und konsistent
- wichtige Entscheidungen sind dokumentiert
- keine offenen kritischen Fehler bekannt

## Qualitätsstandards

- Klar benannte Klassen, Methoden und Variablen
- Keine unnötige Logik in Composables
- UI-State und Business-Logik getrennt
- Keine hardcodierten Sonderfälle ohne Begründung
- Kleine, nachvollziehbare Commits
- Code Review muss jede wichtige Zeile erklärbar machen

## Bewertungsmaßstab

Bewertet wird nicht nur das Endergebnis, sondern vor allem:

- saubere Struktur
- Lernfortschritt
- Umgang mit Feedback
- Verständlichkeit des Codes
- Qualität der Entscheidungen
- Fähigkeit, Fehler selbstständig einzugrenzen

## Optionales Stretch Goal

Wenn Zeit bleibt, kann in Woche 3 ein kleines AI- oder Computer-Vision-Thema ergänzt werden.
Empfehlenswert ist ein geräteunabhängiges Feature wie Barcode-Scanning.

Ein flaggschiff-gebundenes GenAI-Feature sollte nur dann gewählt werden, wenn ein geeignetes
Testgerät vorhanden ist und der Aufwand bewusst akzeptiert wird.

## Hinweis zum AI-Einsatz

AI-Tools dürfen zur Unterstützung genutzt werden, aber nicht als Ersatz für Verständnis. Als
Leitplanke gilt:

**Jede generierte Zeile muss im Review mündlich erklärbar sein.**

## Abschluss

Am Ende der drei Wochen soll eine kleine, stabile Android-App mit nachvollziehbarer Struktur
vorliegen, die die Grundlagen sauber vermittelt und als gute Basis für die weitere Ausbildung dient.