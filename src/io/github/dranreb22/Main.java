package io.github.dranreb22;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage stage) throws Exception {

    Parent root = FXMLLoader.load(getClass().getResource("./fxml_design/ProductionTab.fxml"));
    stage.setTitle("Production Line Tracker");
    Scene scene = new Scene(root, 600, 500);
    stage.setScene(scene);

    stage.show();
  }
}
