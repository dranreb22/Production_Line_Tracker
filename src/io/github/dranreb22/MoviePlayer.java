package io.github.dranreb22;

import io.github.dranreb22.interfaces.MultimediaControl;

/**
 * Class MoviePlayer extends Product and implements MultimediaControl; meant to serve as a type of
 * product to be created based on ItemTypes chosen.
 *
 * @author Bernard Georges 9/26/2019
 */

public class MoviePlayer extends Product implements MultimediaControl {

  private final Screen screen;
  private final MonitorType monitorType;

  /**
   * MoviePlayer constructor accepting the name, manufacturer, screen specs (resolution, refresh
   * rate, response time), and monitorType parameters.
   *
   * @param name         Name of product.
   * @param manufacturer Manufacturer of product.
   * @param screen       Screen resolution, response time, and refresh rate
   * @param monitorType  Enum of value either LCD or LED
   */

  MoviePlayer(Integer ID, String name, String manufacturer, ItemType itemType, Screen screen,
      MonitorType monitorType) {
    super(ID, name, manufacturer, itemType);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  /**
   * Plays current movie.
   */
  @Override
  public void play() {
    System.out.println("Playing");
  }

  /**
   * Stops current movie.
   */
  @Override
  public void stop() {
    System.out.println("Stopping");
  }

  /**
   * Goes to previous movie.
   */
  @Override
  public void previous() {
    System.out.println("Previous Movie");
  }

  /**
   * Goes to next movie.
   */
  @Override
  public void next() {
    System.out.println("Next Movie");
  }

  /**
   * Returns the toString of the parent method plus the toString of the screen object based on the
   * Screen class, and the monitor type based on its toString.
   *
   * @return Formatted as a string object.
   */
  @Override
  public String toString() {
    return super.toString() + "\n"
        + screen.toString() + "\n"
        + "Monitor Type: " + monitorType.toString();
  }
}
