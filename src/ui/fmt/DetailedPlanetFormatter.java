package ui.fmt;

import domain.planets.Moon;
import domain.planets.Planet;

import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DetailedPlanetFormatter implements Function<Planet, String> {

  @Override
  public String apply(Planet planet) {
    var sb = new StringBuilder();
    sb.append(String.format("Planet name: %s%n", planet.getName()));
    sb.append(String.format("\tDistance from the Sun: %,.2f km%n", planet.getDistanceFromSunKm()));
    sb.append(String.format("\tOrbital period: %,.2f days%n", planet.getOrbitalPeriodDays()));
    sb.append(String.format("\tLength of day: %,.2f hours%n", planet.getDayLengthHours()));

    if (planet.hasMoons()) {
      sb.append("\tMoons:\n");
      sb.append(planet.getMoons().stream()
          .sorted(Comparator.comparing(Moon::getDistanceFromPlanetKm))
          .map(moon -> String.format("\t\tMoon name: %s%n", moon.getName()) +
                       String.format("\t\tDistance from planet: %,.2f km%n", moon.getDistanceFromPlanetKm()) +
                       String.format("\t\tOrbital period: %,.2f days%n", moon.getOrbitalPeriodDays()) +
                       String.format("\t\tDiameter: %,.2f km", moon.getDiameterKm()))
          .collect(Collectors.joining("\n\n"))
      );
    }

    return sb.toString();
  }
}
