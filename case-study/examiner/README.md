# Examiner project

Extracts manually detected inconsistencies and code clones from the application.

## Prerequisites

Set Java home directory, for instance:

```shell script
export JAVA_HOME=/home/jan/.jdks/adopt-openjdk-15.0.2
```

## Running the application in dev mode

Arguments:
1. path to System Under Test
2. full path to output file
3. -i for inconsistencies, -c for code clones

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev -Dquarkus.args='<sut>,<output>,<flag>'
```

```shell script
./mvnw compile quarkus:dev -Dquarkus.args='/home/jan/Development/train-ticket,/home/jan/Development/data/train-ticket/codeclone4.csv'
```

## Output format

-i for inconsistencies

```csv
package.class.field, package.class.field
```

-c for code clones

```csv
package.class.method, package.class.method
```


## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `examiner-1.0-SNAPSHOT-runner.jar` file in the `/target` directory. Be aware that it’s not an _über-jar_
as the dependencies are copied into the `target/lib` directory.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar -Dquarkus.args='<sut>,<output>,<flag>'
```

The application is now runnable using `java -jar target/examiner-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/examiner-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html
.
