package domain.planets;

import domain.Model;

public class Moon implements Model {

  private String name;

  private double orbitalPeriod;

  private double diameter;

  private double distanceFromPlanet;

  public Moon(String name, double orbitalPeriod, double diameter, double distanceFromPlanet) {
    this.name = name;
    this.orbitalPeriod = orbitalPeriod;
    this.diameter = diameter;
    this.distanceFromPlanet = distanceFromPlanet;
  }
}
