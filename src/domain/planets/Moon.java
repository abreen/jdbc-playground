package domain.planets;

import domain.Model;

public class Moon implements Model {

  private String name;

  private double orbitalPeriodDays;

  private double diameterKm;

  private double distanceFromPlanetKm;

  public Moon(String name, double orbitalPeriod, double diameter, double distanceFromPlanet) {
    this.name = name;
    this.orbitalPeriodDays = orbitalPeriod;
    this.diameterKm = diameter;
    this.distanceFromPlanetKm = distanceFromPlanet;
  }

  public String getName() {
    return name;
  }

  public double getOrbitalPeriodDays() {
    return orbitalPeriodDays;
  }

  public double getDiameterKm() {
    return diameterKm;
  }

  public double getDistanceFromPlanetKm() {
    return distanceFromPlanetKm;
  }
}
