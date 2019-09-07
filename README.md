# SaaS Starter

[![CircleCI](https://circleci.com/gh/sdudziak/saas_starter.svg?style=svg&circle-token=3c7bb2271ea15ea0a7c46e31abace819746fd469)](https://circleci.com/gh/sdudziak/saas_starter)
[![codecov](https://codecov.io/gh/sdudziak/saas_starter/branch/master/graph/badge.svg?token=ueZ5elU993)](https://codecov.io/gh/sdudziak/saas_starter)

Simple starter for SaaS projects. Just download, 

Contains:
* Role Based User Management
    * Managing roles
    * Managing accounts
    * Managing organizations
* Registration process
    * Create user account manually
    * Create user account using OAuth2 
    * Account Verification email
    * Reset password via email
    * Creating organizations by users
    * Administrating organization members 
        * Create accounts for them
        * Inviting existing users
        * removing members from organization
* Payment gateway implementation with recurring plan
    * Trial access
    * Recurring payment request
    * Membership supression
    * Free access to selected features (Role Based)
    * Payment history
* Integration for invoice services
    * for companies
    * for personal users
* jQuery + Bootstrap 4+ for landing page
* Application part API based
* Angular for application part
* StatsD for statistics 
* Internal event driven design + DDD ready
* Clear separation of modules
* Tested and stable
* Docker & k8s ready
* Managing DB migrations
