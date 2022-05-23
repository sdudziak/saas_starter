# SaaS Starter

[![CircleCI](https://circleci.com/gh/sdudziak/saas_starter.svg?style=svg)](https://circleci.com/gh/sdudziak/saas_starter)
<!-- Disabled until  -->
<!-- [![codecov](https://codecov.io/gh/sdudziak/saas_starter/branch/master/graph/badge.svg?token=ueZ5elU993)](https://codecov.io/gh/sdudziak/saas_starter) -->

---
The purpose of this project is to save your time while getting started with a new Spring Boot + any frontend modern 
framework (It is Angular for now, but you can easily switch to React, Vue, it's up to you). The further plan is to
create a basic setup of the application, that allows you to rapid test your SaaS idea (with payment gateways integration,
RBAC & user management).

It's all open source solution so you can use it as you want. However I'm developing this project in the very limited 
free time - so if you find that my work is useful for you and saved you some time (especially for commercial project) 
please consider donation to the Author. That will also help me to develop this project more actively.
---

Contains:
* Spring Boot 2.6.+ 
* Angular 13.+
* Kotlin (+ detekt)
* docker-compose 
* Gradle (build tool)
* FlyWay (database migrations)
* karate.io (integration & API contract tests)

--- 

## Project structure

Project is divided to separated gradle subprojects, so you can use & configure them independently. You can change frameworks,
programming languages they're based on (as much as gradle supports it), tooling. It's up to you.

### app
   This is where the main application is being developed (Spring Boot bundled), and it should contain only code/resources
   which is necessary to run properly. That implicits that if you need to prepare anything before the build will start 
   (like translations, DB migrations, any other resources preprocessing) - you should move it to the separate subproject.
   The reason behind is that this approach will help you make your application as small, and also as tidy as possible.

### frontend 
   Your frontend application. The artifacts of this build is being deployed to the `app/src/main/resources/static`,
   so all of it's content is available in your main application on runtime

### integration tests
   This is where the integration tests are stored; The concept is that you can launch your application separately, and 
   then test it using tooling that reside inside this module. You can test API only, whole e2e including GUI & DB  

### migrations 
   The purpose of this subproject is to store database migrations separately and evaluate them using migration tool
   (FlyWay bundled). Database migration files are not suited to be a part of application resources and making them
   separated is not just a good idea.
