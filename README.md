# PROJEKT LAB 6_1B

RPG turn fight to symulacja turowego systemu walki znanego z wielu gier typu RPG osadzona w stylistyce medieval fantasy. Gracz ma możliwość sterowania od jednej do czterech postaci sojuszniczych, prowadząc walkę z maksymalnie czterema przeciwnikami. Liczba, rodzaje i statystyki postaci definiuje jeden z trzech poziomów walki. Oprócz zdefiniowanych walk, gracz ma możliwość konfiguracji własnej walki, która obejmuje zdefiniowanie własnych zespołów postaci i dostępnych w walce przedmiotów. Gra implementuje wiele typowych mechanik znanych z gier RPG, m. in. posiadane przez postaci umiejętności, przedmioty, zdolności analizowania przeciwnika, kolejność ruchów w oparciu o czas potrzebny na wykonanie akcji. Gracz steruje postaciami wybierając z graficznego menu żądane akcje, ma również dostęp do historii rozgrywki i aktualnego stanu kolejki tur. Przeciwnikami steruje silnik gry.

# Uruchomienie:

Wymagania: Zainstalowana wersja java >= 17

Pobierz to repozytorium.

Użyj tej komendy w folderze projektu:

```bash
javac -d ./src/main/resources ./src/main/java/game/*.java ./src/main/java/gui/*.java ./src/main/java/*.java && java -classpath ./src/main/resources Main
```