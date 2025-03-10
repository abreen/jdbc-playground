# Planets data layer

## Abstractions

- A `PlanetDao` is any object that can interact with a data source to handle planet DTOs
- A `PlanetDto` is any object representing a planet
- A `MoonDao` is any object that can interact with a data source to handle moon DTOs
- A `MoonDto` is any object representing a planet's moon

## Implementations

- The `PostgresPlanetDao` uses JDBC to access a Postgres database for planet data
- The `PostgresPlanetDto` is a planet DTO used with Postgres
- The `PostgresMoonDao` uses JDBC to access a Postgres database for moon data
- The `PostgresMoonDto` is a moon DTO used with Postgres

## Extensibility

### Changing data sources

Suppose we now have a web-based source of planetary data and we want to start using it. Write a new `PlanetDao`:

```java
public class WebPlanetDao implements PlanetDao {
  private HttpClient client;

  private URI serviceUri;

  public PlanetDao(HttpClient client, URI serviceUri) {
    this.client = client;
    this.serviceUri = serviceUri;
  }

  public Optional<PlanetDto> fetchOne(String key) {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(serviceUri)
        .POST(HttpRequest.BodyPublishers.noBody())
        .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    // ...build a PlanetDto...
  }
}
```

* Any code that depends on the `PlanetDao` and `PlanetDto` abstractions doesn't need to change
* If an HTTP error is encountered, `DataAccessException` can be thrown
* `WebPlanetDao` could still leverage the legacy `PostgresPlanetDao` if the web API doesn't provide some fields

### Adding a new field

To add a new field:

1. Add a new public method to the DTO
2. Add new code to the DAO to fetch & persist the new field
3. In the domain layer, update the mapper and model with the new field
