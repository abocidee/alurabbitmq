#!/bin/bash
docker-compose down 
docker rmi alu-rabbitmq
 mvn clean 
 mvn install -Dmaven.test.skip=true

docker-compose up -d  
