# CSV Email Service

This service was written to provide an easy way to send emails to all participants given by a csv file

## Getting Started

The following instructions will help you to get it running on your machine. So you can test it locally

### Prerequisites

You need the following on you computer installed:

- Java 1.8 SDK
- Maven

### How to get started

First step: Check out the sources:

```
$ git clone https://github.com/KasimirBln/csv-email-service.git
```

Second step create the package and run the service:

```
$ mvn package && java -jar target/csv-email-service-0.1.jar
```

### Start using the service

Go to your favourite webbrowser or create a curl request to access the landing page.

```
$ curl http://localhost:9000
```

You will receive the available endpoints for this service, that you can use. With the following command you can start
the service in mocking mode.

```
$ curl -d "csvFile=LOCAL_CSV_FILE" -X POST http://localhost:9000/start
```

If you don't specify any file a standard file will be used to demo the functionality.

For the easier start there is a small generator included creating a little bigger testfile. This file will also used in
the next run.

```
$ curl http://localhost:9000/generateCsv
$ curl http://localhost:9000/start
```

In order to not accidentally send emails around the service will by default start in a mocking mode. To  exit the
mocking mode and really sending the mail you have two options:

(1) change the appropriate settings in the application.properties file
(2) send the needed settings on startup as spring properties

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
