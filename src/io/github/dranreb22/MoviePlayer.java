package io.github.dranreb22;

import io.github.dranreb22.interfaces.MultimediaControl;

/**
 * Class MoviePlayer extends Product and is meant to serve as a type of product to be created based
 * on ItemTypes chosen.
 *
 * @author Bernard
 */

public class MoviePlayer extends Product implements MultimediaControl {

  private final Screen screen;
  private final MonitorType monitorType;

  MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
    super(name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  @Override
  public void play() {
    System.out.println("Playing");
  }

  @Override
  public void stop() {
    System.out.println("Stopping");
  }

  @Override
  public void previous() {
    System.out.println("Previous Movie");
  }

  @Override
  public void next() {
    System.out.println("Next Movie");
  }

  @Override
  public String toString() {
    return super.toString() + "\n"
        + screen.toString() + "\n"
        + "Monitor Type: " + monitorType.toString();
  }
}
