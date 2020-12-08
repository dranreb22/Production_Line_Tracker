package io.github.dranreb22;


/**
 * Class AudioPlayer extends Product and is meant to serve as a type of product to be created based
 * on ItemTypes chosen.
 *
 * @author Bernard Georges 9/26/2019
 */

public class AudioPlayer extends Product {

  private final String supportedAudioFormats;
  private final String supportedPlaylistFormats;

  /**
   * Constructor method for class AudioPlayer, enabling users to pass defined parameters below.
   *
   * @param name                     Name of the audio player.
   * @param manufacturer             Manufacturer of the audio player.
   * @param audioType                ItemType (will always be AUDIO).
   * @param supportedAudioFormats    the formats of audio that the player supports.
   * @param supportedPlaylistFormats the format of the playlists that the player supports.
   */

  public AudioPlayer(Integer id, String name, String manufacturer, ItemType audioType,
      String supportedAudioFormats, String supportedPlaylistFormats) {
    super(id, name, manufacturer, audioType);
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }

  AudioPlayer(Integer id, String name, String manufacturer) {
    super(id, name, manufacturer, ItemType.AUDIO);
    this.supportedPlaylistFormats = "default";
    this.supportedAudioFormats = "default";
  }

  /**
   * toString method returns a formatted String of audio formats and playlist formats for the
   * player.
   *
   * @return supportedAudioFormats + supportedPlaylistFormats
   */
  public String toString() {
    return super.toString() + "\n"
        + "Supported Audio Formats: " + supportedAudioFormats
        + "\nSupported Playlist Formats: " + supportedPlaylistFormats;
  }
}
