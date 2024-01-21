#!/usr/bin/make -f
PROJECT_NAME := 'Datacentric Platform'
DOCKER_COMPOSE_FILE := docker-compose.yml
WEB_SERVICE := web
DOCKER_PROJECT_NAME := dtcentric

define buildfn
    mvn clean package
endef
dev-start:
	$(call buildfn)
	docker-compose -p $(DOCKER_PROJECT_NAME) -f $(DOCKER_COMPOSE_FILE) up
