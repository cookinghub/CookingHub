#! /bin/bash -x

DOCKER_IMAGE_TAG=$1

# run docker login
$(aws ecr get-login --no-include-email --region us-east-1)

# use maven to deploy docker image
mvn dockerfile:push
mvn dockerfile:tag@tag-version -D dockerTag=${DOCKER_IMAGE_TAG}
mvn dockerfile:push@tag-version -D dockerTag=${DOCKER_IMAGE_TAG}
