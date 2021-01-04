# Semantic Code Clone Detection for Enterprise Applications

This project detect semantic code clones in enterprise applications.

## Prerequisites

* Maven 3.6+
* Java 11+

## Reproducing results

Change directory in the console into the cloned project.

Run on Linux:

```shell script
./mvnw compile quarkus:dev -Dquarkus.args='/pat/to/app/under/test,/path/to/output' -Ddebug=true
```

Run on Windows:

```shell script
mvnw.cmd compile quarkus:dev -Dquarkus.args='/pat/to/app/under/test,/path/to/output' -Ddebug=true
```

Run on MacOS:

```shell script
sh ./mvnw compile quarkus:dev -Dquarkus.args='/pat/to/app/under/test,/path/to/output' -Ddebug=true
```

There are 2 arguments specified in `-dDquarkus.args`. 

First is path to the application that we want to analyze. We utilized project Train Ticket that can be accessed
here: [https://github.com/FudanSELab/train-ticket](https://github.com/FudanSELab/train-ticket)