//package io.github.dranreb22;
//
//import io.github.dranreb22.interfaces.MultimediaControl;
//import java.util.ArrayList;
//
//public class MoviePlayerDriver {
//
//  public static void main(String[] args) {
//    AudioPlayer newAudioProduct = new AudioPlayer("DP-X1A", "Onkyo",
//        "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC");
//    Screen newScreen = new Screen("720x480", 40, 22);
//    MoviePlayer newMovieProduct = new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen,
//        MonitorType.LCD);
//    ArrayList<MultimediaControl> productList = new ArrayList<>();
//    productList.add(newAudioProduct);
//    productList.add(newMovieProduct);
//    for (MultimediaControl p : productList) {
//      System.out.println(p);
//      p.play();
//      p.stop();
//      p.next();
//      p.previous();
//    }
//  }
//}