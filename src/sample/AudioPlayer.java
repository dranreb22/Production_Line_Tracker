package sample;

public class AudioPlayer extends Product implements MultimediaControl {

  private String audioSpecification;
  private String mediaType;

  AudioPlayer(String name, String manufacturer, String type, String audioSpecification) {
    super(name, manufacturer, type);
    this.audioSpecification = audioSpecification;
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
    System.out.println("Going to previous track");
  }

  @Override
  public void next() {
    System.out.println("Going to next track");
  }

  public String toString() {
    return super.toString() + "\n"
        + "Audio Spec: " + "\n"
        + "Type: ";
  }
}
