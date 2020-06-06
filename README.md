## My First Scala Spark Setup
This is docker setup using scala and apache spark, can be run locally to test and learn

how to setup apache spark and scala using docker, very good article [here](https://towardsdatascience.com/a-journey-into-big-data-with-apache-spark-part-1-5dfcc2bccdd2)

Steps to run in local:


1. Run spark cluster first
```sh
docker-compose up
```

2. RUN the Scala SBT
```sh
docker run -it --rm -v `pwd`:/project yasharma6121991/scala-sbt:latest
```
Once the scala sbt run use command `package` to create the jar file of application

3. Run the spark shell to submit jar file
```sh
docker run --rm -it -e SPARK_MASTER="spark://spark-master:7077" \
  -v `pwd`:/project -v `pwd`/../docker-spark:/local \
  --network docker-spark_spark-network -w /project \
  yasharma6121991/docker-spark:latest /bin/bash
```

4. Submit the application ussin `spark-submit`
```sh
/spark/bin/spark-submit --master $SPARK_MASTER \
  --class com.example.MyFirstScalaSpark \
  /project/target/scala-2.12/myfirstscalaspark_2.12-0.1.0.jar \
  /local/UKSA_Oct_18_-_Transparency_Data.csv
```