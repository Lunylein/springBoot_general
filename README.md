# Getting Started

### Reference Documentation
Setting up demo webstore with postgresql and spring-boot

### Guides

Running Postgres in Docker Container

`docker run --name postgres-spring -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres:alpine`

username: postgres // default postgres username

password: password 

default postgres port: 5432 

minimal verison: alpine 

`docker ps` get running docker
`docker exec -it {Container-ID}`  get inside container
`psql -U postgres` "enter"  DB-Server
`create database springshop;`  create DB from our config; dont miss the semicolon; all lowercase