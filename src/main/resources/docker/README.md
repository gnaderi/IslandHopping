Following instructions explain how to publish IslandHoping tutorial APIs via a portable Docker container.

### Pre-requisites
You need to have:
- a Docker installation available locally. See [Docker website](https://docs.docker.com/install/) on installation instructions for Windows and Linux users.
- at least 100MB of local storage.


### Build image
`./gradlew build buildDocker`


### Start container
`docker run -d -p <SERVER_PORT>:8080 {appName}`
```docker run -p 8080:8080 com.gnaderi/island-hopping:0.0.1-SNAPSHOT```
Now if you issue a `docker ps` command you should see a new running container listed.