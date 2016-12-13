# SpringBootRest

A spring boot REST interface the builds as a docker container leveraging Gradle.
Prerequisites : 
Install Eclipse on your local machine.

From eclipse market place
Install Spring plugin
Install “eclipse docker tooling”
Install gradle(build ship) plugin to build the project

Creating project:
New Spring Starter Project -> choose type= gradle(buildship) and give name, package, group and description for the project -> next ->
choose spring boot version 1.4.2 (stable version) and choose dependencies we want to include in the project.
In this example I have chosen web, jersey, Rest repositories.

Coding:
Write necessary services in src/main/java package. Use annotations @SpringBootApplication to make a java class as a Spring Boot application.
Use @RestController, @RequestMapping, @RequestParam, .. Annotations to create a Restful service.

Build a docker image with gradle:
By simply modifying the build.gradle file and including docker image properties in docker file.
buildscript {
	ext {
		springBootVersion = '1.4.2.RELEASE'
	}
	repositories {
		mavenCentral()
	}
	dependencies {
		classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
		classpath('se.transmode.gradle:gradle-docker:1.0') // adding docker to classpath
	}
}

apply plugin: 'java'
apply plugin: 'eclipse'
apply plugin: 'org.springframework.boot'
apply plugin: 'docker' //docker plugin

jar {
	baseName = 'demo'
	version = '0.0.1-SNAPSHOT'
}

//docker image builder
task buildDocker(type: Docker, dependsOn: build) {
  push = true
  applicationName = jar.baseName
  dockerfile = file('src/main/docker/Dockerfile')
  doFirst {
    copy {
      from jar
      into stageDir
    }
  }

sourceCompatibility = 1.8
targetCompatibility = 1.8

repositories {
	mavenCentral()
}


dependencies {
	compile('org.springframework.boot:spring-boot-starter-data-rest')
	compile('org.springframework.boot:spring-boot-starter-web')
	testCompile('org.springframework.boot:spring-boot-starter-test')
}


Execution:
open the browser send a request to the server using the given @RequestMapping url in the REST service.
In this example it is localhost:8080/?name=kumar
