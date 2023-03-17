# How to run

1. copy file mail-server.env.example with name (mail-server.env)
2. replace placeholders with your values
3. Before running docker-compose you need a .jar file to create app image.
   To do that you need running Elasticsearch and Kafka instances.
   **If you don't use containers from docker-compose replace URLs with your own.**

   Then run:`mvn clean package -Dspring.kafka.bootstrap-servers=localhost:29092 -Delasticsearch.address=localhost:9200`
   to build .jar file
4. run in your terminal docker-compose up -d (-d to run detached) to deploy the application with kafka and Elasticsearch