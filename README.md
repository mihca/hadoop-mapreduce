# hadoop-mapreduce
Einfaches Beispiel eines MapReduce-Jobs f�r Hadoop auf Basis der American Community Survey 2013.

Datenfiles k�nnen heruntergeladen werden von:
https://www.kaggle.com/c/2013-american-community-survey

Es gibt 2 Arten von Datens�tzen, H�user und Bev�lkerung. Dieses Beispiel basiert auf den H�userdaten:

```
../input/pums/ss13husa.csv (housing, a)
../input/pums/ss13husb.csv (housing, b)
```

"a" enth�lt die Staaten 1 bis 25 und "b" die Staaten 26 bis 50. Um eine noch gr��ere Datei zu erhalten, k�nnen beide Dateien in eine Datei gemergt werden. Zu Testzwecken ist eine kleine Datei `ss13husa-test.csv` unter `src/test/resources` im Projekt enthalten.

Jede Zeile stellt ein Haus dar mit den Eigenschaften Region, Staat, Alter, Miete oder Eigentum, etc.

```
H,154,6,02500,3,01,1000000,1007549,00051,04,1,1,1,,1,03,02,2,2,2,,2,2,350,1,2,0002,003,2,3,0350,1,,2,,,,3,2,1,09,,,1,9,2,1,,1,1,2,1,,0025000,3,0480,2,1,1,,1,000151000,4,0,,,1,1,000151000,0,4,4,4,1,1,1,6,00,04,0,0,00,003,0,1,0,0,0,0,2,00426,,0,0,1,03,2,01,01,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,00045,00052,00053,00050,00100,00079,00078,00050,00019,00057,00089,00046,00067,00109,00051,00018,00017,00018,00047,00087,00049,00050,00049,00064,00013,00016,00016,00055,00086,00048,00014,00049,00050,00011,00056,00083,00071,00108,00054,00081,00043,00053,00050,00063,00017,00015,00015,00047,00088,00047,00014,00052,00051,00020,00053,00084,00078,00074,00053,00015,00054,00055,00058,00065,00110,00082,00089,00050,00012,00055,00086,00053,00059,00084,00049,00015,00015,00020,00050,00016
```

Der MapReduce-Job z�hlt einfach die Anzahl der H�user pro Bundesstaat. Die Information �ber den Bundesstaat steht in Spalte 6 als zweistelliger Zahlenwert. Das Ergebnis sieht beispielhaft so aus:
```
01      24216
02      3493
04      32025
05      14809
06      153589
08      24580
09      17137
10      4449
11      3662
12      98530
13      45698
15      6055
16      7218
17      58206
18      31465
...
```

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

* Job �ber SSH starten (Eingabedatei `/user/achim/ss13hus.csv`, Ausgabeverzeichnis `/user/achim/output`)
```
hadoop jar hadoop-0.0.1-SNAPSHOT-job.jar com.msg.xt.hadoop.acs.StateJob /user/achim/ss13hus.csv /user/achim/output
```

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
