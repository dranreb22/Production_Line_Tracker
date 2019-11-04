package io.github.dranreb22;

import io.github.dranreb22.interfaces.ScreenSpec;

/**
 * Class Screen explains the features and specifications of the screen of the product entered.
 *
 * @author Bernard Georges 9/26/2019
 */

public class Screen implements ScreenSpec {


  private final String resolution;
  private final int refreshRate;
  private final int responseTime;

  /**
   * Constructor of class Screen accepting and setting its parameters.
   *
   * @param resolution   Resolution (ie 1920x1080)
   * @param refreshRate  Refresh rate of screen (in Hertz)
   * @param responseTime Response time in milliseconds
   */
  public Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.responseTime = responseTime;
    this.refreshRate = refreshRate;
  }

  /**
   * Method that enables access to resolution.
   *
   * @return resolution
   */
  @Override
  public String getResolution() {
    return resolution;
  }

  /**
   * Method that enables access to refresh rate.
   *
   * @return refreshRate
   */
  @Override
  public int getRefreshRate() {
    return refreshRate;
  }

  /**
   * Method that enables access to response time.
   *
   * @return responseTime
   */
  @Override
  public int getResponseTime() {
    return responseTime;
  }

  /**
   * Returns a formatted string of resolution, refreshRate, and responseTime.
   *
   * @return resolution + refreshRate + responseTime
   */
  public String toString() {
    return "Resolution: " + resolution
        + "\nRefresh Rate: " + refreshRate
        + "\nResponse Time: " + responseTime;
  }
}
