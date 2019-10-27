package io.github.dranreb22;

import io.github.dranreb22.interfaces.MultimediaControl;

public class AudioPlayer extends Product implements MultimediaControl {

  private String audioSpecification;
  private String audioType;

  public AudioPlayer(String name, String manufacturer, ItemType audioType) {
    super(name, manufacturer, ItemType.AUDIO);
    this.audioSpecification = audioType.toString();
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
    System.out.println("Previous");
  }

  @Override
  public void next() {
    System.out.println("Next");
  }

  public String toString() {
    return super.toString() + "\n"
        + "Audio Spec: " + audioSpecification;
  }
}
