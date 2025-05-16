# Projekt Bonussystem (TDD)
## Klassendiagramm
Das diesem Projekt zugrunde liegende [Klassendiagramm kann hier gefunden werden](Klassendiagramm.png).

## Bonussystem
Die genauen Werte des Bonussytems werden im Weiteren beschrieben

### Grundbonus
Der Grundbonus für jeden Mitarbeiter beträgt 300 Euro.

### Performance Bonus
#### Seniority Bonus
Ein Interval, also eine Höherstufung des Seniority Levels erfolgt nach 3 Jahren.
Dabei wird jede 5 Jahre ein 150 € Euro bonus kalkuliert.

#### Project Completion Bonus
Hierbei wird mit einem flachen Bonus pro Projekt gerechnet.
Dieser Bonus beträgt 50 € pro kompletten Projekt.

#### Teamlead Bonus
Teamleader erhalten einen Bonus von 200 €.

#### Geringe Fehlzeiten Bonus
Die erste Abstufung der geringen Fehlzeiten wird bei 3 angesetzt.
Das heißt bei 3 oder weniger Fehltagen gibt es einen 200 € Bonus.
Die zweite Abstufung wird bei 10 angesetzt, wobei es einen 50 € Bonus gibt.

#### Hohe Fehlzeiten Abzüge
Bei hohen Fehlzeiten gibt es Abzüge.
Die erste Abstufung sind hier 15 Fehltage, bei den es einen Abzug von 50 € gibt.
Die zweite Abstufung, ab 25 Fehltage, bei den es einen Abzug von 100 € gibt.

#### Performance Bonus
Der Performance Bonus enthält drei Faktoren, mit dem der Bonus multipliziert wird. 
Dabei ist der niedrige Faktor 7%, der mittlere Faktor 12% und der hohe Faktor 17%.
Der Performance Score kann dabei maximal 100 betragen.
Bei einem Performance-Score von weniger als 33 wird der niedrige Faktor verwendet.
Bei einem Performance-Score von mehr als 66 wird der hohe Faktor verwendet.
Dazwischen wird der mittlere Faktor verwendet.

#### Höchst- und Mindestbonus
Der Höchstbonus beträgt 2000 € und der Mindestbonus 500 €.

### Beispielrechnung
#### Mitarbeiter A:
- Grundbonus: 300 €
- Betriebszugehörigkeit: 6 Jahre -> 1 vollendete 5-Jahres-Periode -> 1 * 150 € = 150 €
- Abgeschlossene Projekte: 2 -> 2 * 50 € = 100 €
- Position: Kein Teamlead -> 0 €
- Fehltage: 2 -> +200 € Bonus (da <= 3 Tage)
- Performance Score Annahme: 33-66 -> mittlerer Faktor (12%)
- **Berechnungsbasis (Summe):** 300 € (Grundbonus) + 150 € (Seniority) + 100 € (Projekte) + 0 € (Teamlead) + 200 € (Fehlzeiten) = 750 €
- **Anwendung Performance Faktor:** 750 € * 1,12 = 840 €
- **Prüfung Höchst-/Mindestbonus:** 500 € <= 840 € <= 2000 € -> OK
- **Finaler Bonus:** 840 €

#### Mitarbeiter B:
- Grundbonus: 300 €
- Betriebszugehörigkeit: 12 Jahre -> 2 vollendete 5-Jahres-Perioden -> 2 * 150 € = 300 €
- Abgeschlossene Projekte: 5 -> 5 * 50 € = 250 €
- Position: Teamlead -> +200 €
- Fehltage: 16 -> -50 € Abzug (da 15-24 Tage)
- Performance Score Annahme: > 66 -> hoher Faktor (17%)
- **Berechnungsbasis (Summe):** 300 € (Grundbonus) + 300 € (Seniority) + 250 € (Projekte) + 200 € (Teamlead) - 50 € (Fehlzeiten) = 1000 €
- **Anwendung Performance Faktor:** 1000 € * 1,17 = 1170 €
- **Prüfung Höchst-/Mindestbonus:** 500 € <= 1170 € <= 2000 € -> OK
- **Finaler Bonus:** 1170 €