package domain.planets;

import data.entity.Entity;

public record Planet(
    String name,
    Double orbitalPeriod,
    Double dayLength,
    Double distanceFromSun
) implements Entity<String> {

  @Override
  public boolean equals(Object obj) {
    return switch (obj) {
      case Planet other -> getKey().equals(other.getKey());
      case null, default -> false;
    };
  }

  @Override
  public String getKey() {
    return name;
  }
}
