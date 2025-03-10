package domain.planets;

import domain.Model;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Stream;

public class Planet implements Model {

  private String name;

  private double orbitalPeriodDays;

  private double dayLengthHours;

  private double distanceFromSunAu;

  private Collection<Moon> moons;

  public Planet(String name, double orbitalPeriod, double dayLength, double distanceFromSun) {
    this.name = name;
    this.orbitalPeriodDays = orbitalPeriod;
    this.dayLengthHours = dayLength;
    this.distanceFromSunAu = distanceFromSun;
  }

  public String getName() {
    return name;
  }

  public double getDistanceFromSunAu() {
    return distanceFromSunAu;
  }

  public double getDistanceFromSunKm() {
    return distanceFromSunAu * 149_597_871;
  }

  public double getOrbitalPeriodDays() {
    return orbitalPeriodDays;
  }

  public double getDayLengthHours() {
    return dayLengthHours;
  }

  public void setMoons(Collection<Moon> moons) {
    this.moons = moons;
  }

  public boolean hasMoons() {
    return !moons.isEmpty();
  }

  public Collection<Moon> getMoons() {
    return moons;
  }

  @Override
  public boolean equals(Object obj) {
    return switch (obj) {
      case Planet other -> name.equals(other.getName());
      case null, default -> false;
    };
  }
}
