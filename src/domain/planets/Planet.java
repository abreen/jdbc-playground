package domain.planets;

import domain.Model;

import java.util.Collection;

public class Planet implements Model {

  private String name;

  private double orbitalPeriod;

  private double dayLength;

  private double distanceFromSun;

  private Collection<Moon> moons;

  public Planet(String name, double orbitalPeriod, double dayLength, double distanceFromSun) {
    this.name = name;
    this.orbitalPeriod = orbitalPeriod;
    this.dayLength = dayLength;
    this.distanceFromSun = distanceFromSun;
  }

  public String getName() {
    return name;
  }

  public double getDistanceFromSun() {
    return distanceFromSun;
  }

  public void setMoons(Collection<Moon> moons) {
    this.moons = moons;
  }

  @Override
  public boolean equals(Object obj) {
    return switch (obj) {
      case Planet other -> name.equals(other.getName());
      case null, default -> false;
    };
  }
}
