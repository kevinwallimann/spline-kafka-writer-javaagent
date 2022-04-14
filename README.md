# Spline Kafka Writer Java Agent

## Overview
This Java agent can be used to extract the record metadata after a message has been written to a Kafka topic.
To that end, the agent hooks into the `onCompletion` method of the `org.apache.kafka.clients.producer.Callback` class.

Note that the `Callback` interface is implemented as an anonymous class in the `org.apache.spark.sql.kafka010.KafkaRowWriter` class
and also in the `org.apache.kafka.clients.producer.KafkaProducer$InterceptorCallback` class.

In the current implementation, the agent is invoked for both implementations, but the output is only generated for the `KafkaRowWriter`

The agent simply writes out the record metadata to stdout, like so

```
RecordMetadata:topic-0@1046
RecordMetadata:topic-0@1047
RecordMetadata:topic-0@1048
```

## Usage
Compile the agent with
```
mvn clean package
``` 

Then, use it with a Spark job by adding the following extra java options

```
--conf spark.driver.extraJavaOptions="-javaagent:<project-dir>/target/spline-kafka-writer-java-agent-<version>.jar"
--conf spark.executor.extraJavaOptions="-javaagent:<project-dir>/target/spline-kafka-writer-java-agent-<version>.jar"
```
