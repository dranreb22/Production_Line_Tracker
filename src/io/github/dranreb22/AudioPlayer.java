package io.github.dranreb22;

import io.github.dranreb22.interfaces.MultimediaControl;

/**
 * Class AudioPlayer extends Product and is meant to serve as a type of product to be created based
 * on ItemTypes chosen.
 *
 * @author Bernard
 */

public class AudioPlayer extends Product implements MultimediaControl {

  private final String supportedAudioFormats;
  private final String supportedPlaylistFormats;

  /**
   * Constructor method for class AudioPlayer, enabling users to pass defined parameters below.
   * @param name name of the audio player
   * @param manufacturer manufacturer of the audio player
   * @param audioType ItemType (will always be AUDIO)
   * @param supportedAudioFormats the formats of audio that the player supports
   * @param supportedPlaylistFormats the format of the playlists that the player supports
   */

  public AudioPlayer(String name, String manufacturer, ItemType audioType,
      String supportedAudioFormats, String supportedPlaylistFormats) {
    super(name, manufacturer, audioType);
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
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

  /**
   * toString method returns a formatted String of audio formats
   * and playlist formats for the player.
   * @return supportedAudioFormats + supportedPlaylistFormats
   */
  public String toString() {
    return super.toString() + "\n"
        + "Supported Audio Formats: " + supportedAudioFormats
        + "Supported Playlist Formats: " + supportedPlaylistFormats;
  }
}
