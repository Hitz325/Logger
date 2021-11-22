# Outdoor Event Log Project Outline

## Overview

For our LiftOff project we decided to create an outdoors activity event log. Users will be able to create and log into accounts, create, view, edit, rate, and delete events. Event details could include location, descrition, outdoor event type(e.g. fishing, hiking, camping, etc.), and date/time, etc.

This project could have applications for local, state, and federal parks departments, as well as retailers, that would give users a way of sharing their experiences and providing data. Location data, ratings, and description information could prompt park officials to potential problems at a location. Popular locations with "good" ratings, and description data(i.e. keywords) could be beneficial for marketing opportunities.

## Features
1. User login: Users will be able to create and log into accounts. When creating accounts we want to use a ReCaptcha to authenticate that the user is an actual person and not a "robot." To do anything other than read previously posted events, users will need to be logged in.
2. Users can create, read, edit, and delete events: Once logged in, users can create, edit, and delete events associated with their account. Mixture required(event type, date/time) and optional fields (description, location, rating).
3. Users search: Users will be able to search for previously posted events based on different filters matching user input. Filters could include: all, description, event type.
4. Tag location: Add location data for events and display events on GoogleMap with markers.
5. Rate locations: Implement a rating system(e.g. 1-5 star system) where users can rate the quality of the event they are adding to the log.

## Technologies
- Java
- Spring Boot
- MySQL
- Hibernate
- Thymeleaf templates
- GoogleMaps API
- Bootstrap
- ReCaptcha
- JavaScript

## What we'll have to learn
For location data we will have to learn how to make API calls to GoogleMaps. We will also need to learn how to use the ReCaptcha API for user authentication.

## Project tracker
https://trello.com/b/tUy1OLVB/zac-o-group-a

##Updates
-11/21/21: Succsessfully deployed the app through Heroku and added a clearDB remote database to make the app persistent. URL: https://logger-heroku.herokuapp.com/
