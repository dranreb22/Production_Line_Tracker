package io.github.dranreb22;

import io.github.dranreb22.interfaces.ScreenSpec;

public class Screen implements ScreenSpec {

  private String resolution;
  private int refreshRate;
  private int responseTime;

  public Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.responseTime = responseTime;
    this.refreshRate = refreshRate;
  }

  @Override
  public String getResolution() {
    return resolution;
  }

  @Override
  public int getRefreshRate() {
    return refreshRate;
  }

  @Override
  public int getResponseTime() {
    return responseTime;
  }

  public String toString() {
    return "Resolution: " + resolution
        + "\nRefresh Rate: " + refreshRate
        + "\nResponse Time: " + responseTime;
  }
}
