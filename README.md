# Hadoop MapReduce for number addition

### Creating executable jar
- Clone this repository
- run ```mvn clean install```

### Running in AWS EMR as a Step
- Create S3 bucket
- Add built `.jar` file to s3
- create `in\` directory and upload sample number list
- Go to *Add Step* in EMR console
- Select `.jar` and provide input and output using args
![2021-04-09_18-54](https://user-images.githubusercontent.com/25344622/114186912-0637d100-9965-11eb-9d83-d95ae13643e4.png)
- Click *Add step*

### Running using EMR master node
- Upload sample files and `.jar` file to master node
- Create input directory in *DFS* as `in`  
  ```hdfs dfs -mkdir in```
- Copy sample files to *DFS*  
  ```hdfs dfs -put sample1.txt in/```
- Run `.jar` with args  
  ```hadoop jar numberaverage-1.0-SNAPSHOT.jar in/ out/```
- View output  
  ```hdfs dfs -cat out/part-r-0000```
