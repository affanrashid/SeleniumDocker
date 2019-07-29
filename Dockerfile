# This is how we make our docker image. We copied this file into our intellij Project directory
# We then Specify the the Image we are going to use, The working directory that we are going to be in once the image loads
# Copy any file from our intelliJ project directory to the working directory
# Copy our testNG .xml files to the working directory
# Give the command we want to run once everything above is completed.

FROM openjdk:8u191-jre-alpine3.8

#We Take the base image above and two utilities; curl, JQ

RUN apk add curl jq


# workspace
WORKDIR /usr/share/udemy

# Add .jar under target location from host
#int this image
ADD target/selenium-docker.jar          selenium-docker.jar
ADD target/selenium-docker-tests.jar    selenium-docker-tests.jar
ADD target/libs                         libs

#ADD suite files
ADD book-flight-module.xml              book-flight-module.xml
ADD search-module.xml                   search-module.xml

#ADD health check script
RUN wget https://s3.amazonaws.com/selenium-docker/healthcheck/healthcheck.sh


# BROWSER
# HUB_HOST
# MODULE
# These are environment variable that someone might be passing to the image
#ENTRYPOINT java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -DBROWSER=$BROWSER -DHUB_HOST=$HUB_HOST org.testng.TestNG $MODULE

#We execute the health check which basically checks to see if the selenium Hub has connections with the grid and then execute the test cases

ENTRYPOINT sh healthcheck.sh

#to build this image go to the where the docker source file is present "docker build -t=affanr/selenium-docker ."
