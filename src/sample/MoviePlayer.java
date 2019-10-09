package sample;

public class MoviePlayer extends Product implements MultimediaControl {

  private Screen screen;
  private MonitorType monitorType;

  MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
    super(name, manufacturer, "VISUAL");
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

  public String toString() {
    return super.toString() + "\n"
        + screen.toString() + "\n"
        + "Monitor Type: " + monitorType.toString();
  }
}
