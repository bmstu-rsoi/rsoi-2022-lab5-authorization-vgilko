#!/bin/bash

echo 'Start installing services'
# install services to local maven repo
mvn -f cars/pom.xml --batch-mode --update-snapshots clean install -DskipTests
mvn -f payment/pom.xml --batch-mode --update-snapshots clean install -DskipTests
mvn -f rental/pom.xml --batch-mode --update-snapshots clean install -DskipTests
mvn -f gateway/pom.xml --batch-mode --update-snapshots clean install -DskipTests

# build docker images
docker build ./rental/ -t vgilko/dockerhub:rental
docker build ./payment/ -t vgilko/dockerhub:payment
docker build ./cars/ -t vgilko/dockerhub:cars
docker build ./gateway/ -t vgilko/dockerhub:gateway

# push images to dockerhub
docker push vgilko/dockerhub:rental
docker push vgilko/dockerhub:payment
docker push vgilko/dockerhub:cars
docker push vgilko/dockerhub:gateway

echo 'Services was installed and pushed to dockerhub'