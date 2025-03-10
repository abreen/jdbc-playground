package data.planets.postgres;

import data.planets.MoonDto;

public record PostgresMoonDto(
    String moonName,
    String planetName,
    Double orbitalPeriod,
    Double diameter,
    Double distanceFromPlanet
) implements MoonDto {
}
