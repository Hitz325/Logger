# Outdoor Event Log(Logger) Project Outline
https://logger-heroku.herokuapp.com/

## Overview

For our LiftOff project we decided to create an outdoors activity event log. Users will be able to create and log into accounts, create, view, edit, rate, and delete events. Event details could include location, descrition, outdoor event type(e.g. fishing, hiking, camping, etc.), and date/time, etc.

This project could have applications for local, state, and federal parks departments, as well as retailers, that would give users a way of sharing their experiences and providing data. Location data, ratings, and description information could prompt park officials to potential problems at a location. Popular locations with "good" ratings, and description data(i.e. keywords) could be beneficial for marketing opportunities.

## Features
1. User login: Users will be able to create and log into accounts. When creating accounts we want to use a ReCaptcha to authenticate that the user is an actual person and not a "robot." To do anything users will need to be logged in.
2. Users can create, read, edit, and delete events: Once logged in, users can create, edit, and delete events associated with their account. Mixture required(event type, date/time) and optional fields (description, location, rating).
3. Users search: Users will be able to search for previously posted events based on different filters matching user input. Filters could include: all, description, event type.
4. Tag location: Add location data for events and display events on GoogleMap with markers.
5. Rate locations: Implement a rating system(e.g. 1-5 star system) where users can rate the quality of the event they are adding to the log.

## Technologies
- Java
- JavaScript
- Spring Boot
- ClearDB
- GoogleMaps API
- Bootstrap
- Heroku

## What we'll have to learn
For location data we will have to learn how to make API calls to GoogleMaps. We will also need to learn how to use the ReCaptcha API for user authentication.

## Project tracker
https://trello.com/b/tUy1OLVB/zac-o-group-a

## Updates
-11/21/21: Succsessfully deployed the app through Heroku and added a clearDB remote database to make the app persistent. URL: https://logger-heroku.herokuapp.com/
The project still contains its fair share of bugs, and the functionality isn't 100% there. I have not gotten around to implementing the ReCaptcha to authenticate users creating accounts.
While the GoogleMap API displays correctly, location data added to the Google API is also something else that needs doing. Search output still needs to be neatly formatted. 
Major bugs to work out: Deleting events, had this working before, but after reworking how the data is stored and how it relates to each other, this function broke. Also able to delete Event Categories, but not the original one in the database. Last major bug to work out is tying the events to the user. Which seems to work correctly, but there are still a few bugs to work out.
