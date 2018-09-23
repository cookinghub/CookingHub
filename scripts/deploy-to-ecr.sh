#! /bin/bash

# run docker login
$(aws ecr get-login --no-include-email --region us-east-1)

# use maven to deploy docker image
mvn dockerfile:push
