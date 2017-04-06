Simple Spark Streaming application
====

## Requirement

 * Apache Maven >= 3

## Build
```
$ git clone https://github.com/wtakase/spark-hellostreaming
$ cd spark-hellostreaming
$ mvn clean package
```

## Run
Open terminal and execute as follows:
```
$ nc -lk 9999
```
Then, open another terminal and run the application.
If you type some words on the first terminal, the application counts up them every 10 seconds.
```
$ spark-submit --master local[*] --class jp.kek.spark.hellostreaming.HelloStreaming target/hellostreaming-0.0.1-jar-with-dependencies.jar localhost 9999
-------------------------------------------
Time: 1491450480000 ms
-------------------------------------------

-------------------------------------------
Time: 1491450490000 ms
-------------------------------------------
(hoge,2)
```
