# OpenWeatherMap API client project

The OpenWeatherMap API client library provides an access to weather information's for Java EE applications.
It uses 2.5 version of API. Library is using REST client specified in JAX-RS 2.1 specification.
OpenWeatherMap client to properly function requires the client with registered JSON unmarshaller.
Unmarshaller need to be compatible with Jaxb specification.

## User Guide

### Installation & Compilation
Library installation require:
- Java 8+
- JAVA_PATH set to jdk location
- Maven 3+
- PATH need to contain maven location

Installation steps:
```sh
git clone https://github.com/Mielek/openweathermap-api-client.git
cd openweathermap-api-client
mvn clean install -DskipTests
```
Project does not contain OpenWeatherMap api key that's why invoking maven require `-DskipTests`.
How to prepare project for running JUnit tests is described in [this](#invoking-project-tests) section.

### Usage

Add maven dependency to your project.

```xml
<dependency>
    <groupId>com.github.mielek</groupId>
    <artifactId>openweathermap-api-client</artifactId>
    <version>0.0.1</version>
</dependency>
```

The `OpenWeatherMapClient` is available under `com.github.mielek.api.client.openweathermap` package.

Simple main example:
```java
class Main {
    public static void main() {
        Client webClient = ClientBuilder.newClient().register(JacksonJaxbJsonProvider.class);
        OpenWeatherMapClient weatherClient = new OpenWeatherMapClient(webClient, "<<YOUR_API_KEY>>");
        try {
            CityWeather cityWeather = weatherClient.getCurrentWeatherByCity("New York");
            System.out.println(cityWeather);
        } catch (OpenWeatherMapApiException e) {
            System.err.println(e.getMessage());
        }
    }
}
```
Remember that you need to provide implementation of `javax.ws.rs.client.Client` in your project.
You can use for example apache cxf JAX-RS client.
Additionally you need to remember to register a JSON unmarshaller in the client to properly deserialize data received from the API.
In the example above Jackson implementation of Jaxb JSON unmarshaller is used.

### Invoking project tests

To invoke tests shipped with the project you need to acquire OpenWeatherApi key.

Next you need to create `openweathermap-api-key` file in the project root directory and fill it with your api key. For example:

```sh
echo "<<YOUR_KEY>>" > openweathermap-api-key
```

Following command will compile project and invoke some simple tests.
They will check if client with your api key is working correctly.

```sh
mvn test
```

## Credits

Created by Rafal Mielowski
