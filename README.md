# hadoop-mapreduce
Einfaches Beispiel eines MapReduce-Jobs f�r Hadoop auf Basis der Amercian Community Survey 2013.

Datenfiles k�nnen heruntergeladen werden von:
https://www.kaggle.com/c/2013-american-community-survey

Es gibt 2 Arten von Datens�tzen, H�user und Bev�lkerung. Dieses Beispiel basiert auf den H�userdaten:

```
../input/pums/ss13husa.csv (housing, a)
../input/pums/ss13husb.csv (housing, b)
```

"a" enth�lt die Staaten 1 bis 25 und "b" die Staaten 26 bis 50. Um eine noch gr��ere Datei zu erhalten, k�nnen beide Dateien in eine Datei gemergt werden. Zu Testzwecken ist eine kleine Datei `ss13husa-test.csv` unter `src/test/resources` im Projekt enthalten.

Jede Zeile stellt ein Haus dar mit den Eigenschaften Region, Staat, Alter, Miete oder Eigentum, etc.

Der MapReduce-Job z�hlt einfach die Anzahl der H�user pro Bundesstaat. Die Information �ber den Bundesstaat steht in Spalte 6.

# Installation Eclipse 
* Git Repository lokal klonen
```
git clone https://github.com/mihca/hadoop-mapreduce
```
* Eclipse starten und File/Import und dann "Existing Maven Projects" anklicken und das pom.xml ausw�hlen.

# Beispiel ausf�hren
* Build auf Kommandozeile starten mit
```
mvn clean package
```
* Buildergebnis `target/hadoop-0.0.1-SNAPSHOT-job.jar` auf den Hadoop-Masterknoten kopieren 

* Datendatei auf den Hadoop-Masterknoten ins HDFS kopieren, z.B. �ber den FileBrowser in HUE:
http://192.168.149.130:8000/filebrowser

* Job �ber SSH starten 
```
hadoop jar hadoop-0.0.1-SNAPSHOT-job.jar com.msg.xt.hadoop.acs.StateJob /user/achim/ss13hus.csv /user/achim/output
```
`/user/achim/ss13hus.csv` stellt dabei die Eingabedatei und `/user/achim/output` das Ausgabeverzeichnis dar

* Ergebnis anzeigen
 Im Erfolgsfall finden sich im Ausgabeverzeichnis folgende Artefakte. Die Datei `_SUCCESS` symbolisiert die erfolgreiche Ausf�hrung des Jobs. Die Datei(en) `part-r-nnnnn` werden pro Reducer erzeugt, d.h. in diesem Fall gab es nur einen Reducer-Prozess.
```
-rw-r--r--   1 root hdfs          0 2015-09-02 13:59 /user/achim/output/_SUCCESS
-rw-r--r--   1 root hdfs        450 2015-09-02 13:59 /user/achim/output/part-r-00000
```
Die Ergebnisse lassen sich �ber folgenden Befehl zu einer lesbaren Datei zusammenfassen:
```
hadoop fs -getmerge /user/achim/output/ /home/achim/output.txt
```
