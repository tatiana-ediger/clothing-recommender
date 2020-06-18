# Clothing Recommender
*Authors: Pablo Kvitca, Tatiana Ediger*

This project models a Clothing domain with different types of clothes, their attributes, and relationships on a Graph based model. We use the [Neo4j Database](https://neo4j.com/neo4j-graph-database/) for storage and analysis of this graph. The project allows us to add, list, see, describe, find, and most interestingly *recommend clothes* to purchase/wear to the user.  
Additionally, we use [Testcontainers](https://www.testcontainers.org/) to simplify out *Data-Access Layer Integration* testing process.

## Technologies Used
- [Neo4j Database](https://neo4j.com/neo4j-graph-database/) - Data storage & modelling
- [Neo4j-OGM](https://neo4j.com/docs/ogm-manual/current/) - Interfacing with DB from Java
- [Logback](http://logback.qos.ch/) - Logging
- [JUnit 5 - Jupiter](https://junit.org/junit5/docs/current/user-guide/) - Testing Framework
- [Testcontainers - Neo4j](https://www.testcontainers.org/modules/databases/neo4j/) - Integration Testing
- [Docker](https://www.docker.com/)

## Project Status
The project currently just has a demo Command Line based interface, intended to show the capabilities of the Graph model and database.
Currently some of the recommendation options are implemented (*recommend to a user*, *recommend to a user using related items*, and *recommend to a user using similar items*). To see this in action you can use the Main program as a command line interface, as follows:

### Requirements
- Java 11.0.6
- Neo4j Database - **IMPORTANT:** should have user called `neo4j` with password `demo` - and should be actively running with the *Bolt protocol* (see Neo4j documentaito) on port #`7687`. **Note that these are the default network configs**.
- For running tests: *Docker* should be running.

### Available Demo Commands
1. `filldb` - seed the database with demo data. **SHOULD BE RAN FIRST**
- `list [label]` -- lists all available of *label*
  - `clothings` -- lists all available of **clothings**
  - `bottoms` -- lists all available of **bottoms**
  - `tops` -- lists all available of **tops**
  - `footwears` -- lists all available of **footwears**
  - `users` -- lists all available of **users**
  - `descriptors` -- lists all available of **descriptors**
  - `colors` -- lists all available of **colors**
  - `brands` -- lists all available of **brands**
  - `fanciness` -- lists all available of **fanciness**
  - `subtypes` -- lists all available of **subtypes**
  - `groupings` -- lists all available of **groupings**
  - `collections` -- lists all available of **collections**
  - `sets` -- lists all available of **sets**
- `closet [username]` -- lists every *clothing* owned by the given user
  - `[username]` is the unique identifier for the user
- `describe-clothing [catalogID]` -- lists every descriptor and grouping the clothing is in
  - `[catalogID]` is the unique identifier for the clothing
- `described-by [descriptor type] [descriptor name]` -- lists every clothing related to the given **descriptor**
  - `[descriptor type]` can be: "color", "brand", "subtype", or "fanciness"
  - `[descriptor name]` should be a type of descriptor on the database. Example: `blue`, `Vans`, `jeans`, `fancy`
- `grouped-by [grouping type] ["][grouping name]["]` -- lists every clothing related to the given **grouping**
  - `[grouping type]` can be: "collection" or "set"
  - `[grouping name]` shoud be the name of the grouping (can be between quotes if the name has spaces)
- `add-clothing [clothing type] ["][clothing name]["] [catalogID] [[descriptor type] [descriptor name] [descriptor type] [descriptor name] ...]` -- adds the given clothing to the DB, with the given name, catalog id, type, and descriptors. Groupings need to be added on a separate command.
  - `[clothing type]` can be "bottom", "top", or "footwear"
  - `[clothing name]` can be any string for the name of the clothing (use quotes if it contains spaces)
  - `[catalogID]` should be a unique string identifier for the clothing
  - An unlimited number of `[descriptor type] [descriptor name]` pairs is then accepted. These can be existing or new to the DB
- `add-to-closet [username] [catalogID]` -- adds the given clothing as owned by the given user
  - `[username]` is the unique identifier for the user
  - `[catalogID]` is the unique identifier for the clothing
- `add-user [username] ["][readable name]["]` -- adds the given user to the DB
  - `[username]` is the unique identifier for the user
  - `[readable name]` is the printable name of the user (use quotes if it contains spaces)
- `add-to-grouping [catalogID] [grouping type] ["][grouping name]["]` -- adds the given clothing as being on the given grouping
  - `[catalogID]` is the unique identifier for the clothing
  - `[grouping type]` can be: "collection" or "set"
  - `[grouping name]` shoud be the name of the grouping (can be between quotes if the name has spaces). This can be existing or new to the DB
- `recommend-purchase-related [username] [catalogID]` -- recommends items related (by groupings) to the given clothing, that the user does NOT own already
  - `[username]` is the unique identifier for the user
  - `[catalogID]` is the unique identifier for the clothing
- `recommend-purchase-similar [username] [catalogID]` -- recommends items similar (by descriptors) to the given clothing, that the user does NOT own already
  - `[username]` is the unique identifier for the user
  - `[catalogID]` is the unique identifier for the clothing
- `recommend-purchase [username]` -- recommends items based on similar users to the given user, that the user does NOT own already
  - `[username]` is the unique identifier for the user
  
### Future commands

- `recommend-wear-related [username] [catalogID]` -- recommends items related (by groupings) to the given clothing, that the user DOES own already
  - `[username]` is the unique identifier for the user
  - `[catalogID]` is the unique identifier for the clothing
- `recommend-wear-similar [username] [catalogID]` -- recommends items similar (by descriptors) to the given clothing, that the user DOES own already
  - `[username]` is the unique identifier for the user
  - `[catalogID]` is the unique identifier for the clothing
- `recommend-wear [username]` -- recommends items based on similar users to the given user, that the user DOES own already
  - `[username]` is the unique identifier for the user
