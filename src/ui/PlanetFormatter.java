package ui;

import domain.planets.Planet;

import java.util.function.Function;

public class PlanetFormatter implements Function<Planet, String> {
  @Override
  public String apply(Planet planet) {
    return String.format("%s (%.2f AU)", planet.getName(), planet.getDistanceFromSun());
  }
}
