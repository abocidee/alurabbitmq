FROM openjdk
RUN mkdir /app
COPY target/alu-rabbitmq-0.0.1-SNAPSHOT.jar /app
CMD java -jar /app/alu-rabbitmq-0.0.1-SNAPSHOT.jar 
EXPOSE 8783 
