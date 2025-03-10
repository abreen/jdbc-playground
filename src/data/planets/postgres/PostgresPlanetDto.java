package data.planets.postgres;

import data.planets.PlanetDto;

public record PostgresPlanetDto(
    String planetName,
    Double orbitalPeriod,
    Double dayLength,
    Double distanceFromSun
) implements PlanetDto {
}
