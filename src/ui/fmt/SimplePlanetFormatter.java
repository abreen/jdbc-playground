package ui.fmt;

import domain.planets.Planet;

import java.util.function.Function;

public class SimplePlanetFormatter implements Function<Planet, String> {
  @Override
  public String apply(Planet planet) {
    return String.format("%s (%.2f AU)", planet.getName(), planet.getDistanceFromSunAu());
  }
}
