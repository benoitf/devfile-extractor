# Extract devfiles from a che database

# pre-requisites
Java11+, Maven and podman/docker

# grab dump of the file

Create folder `/tmp/database`

Copy database dump file to `/tmp/database/db` file

# Run postgresql
```
$ docker run --rm -it -p 5432:5432 --name postgres  -v /tmp/database:/database -e POSTGRES_PASSWORD=postgres postgres:13
```

List all containers with like `docker ps` command

In a separate terminal, connect to the postgres container identified by SHA_OF_THE_CONTAINER

$ docker exec -ti ${SHA_OF_THE_CONTAINER} bash

Once inside the container, switch to postgres user and execute actions by using the following commands

```bash
# su - postgres
$ psql postgres < /database/db
$ create sequence hibernate_sequence start 1 increment 1;
```

# Run the tool

run `mvn clean install`


Create folder `/tmp/devfiles`

And then run

```
mvn compile exec:java -Dexec.mainClass="com.github.benoitf.devfile.extractor.Main"
```
All devfiles are extracted into `/tmp/devfiles` folder