package io.github.dranreb22;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Default main class starts the GUI
 *
 * @author Bernard Georges 9/26/2019
 */
public class Main extends Application {

  /**
   * Start method runs at the beginning of the program to obtain access to the GUI, set its title
   * and scene, then shows the GUI.
   *
   * @param stage Stage object provides methods for viewing the GUI
   * @throws Exception catches all exceptions
   */
  @Override
  public void start(Stage stage) throws Exception {

    Parent root = FXMLLoader.load(getClass().getResource("./fxml_design/ProductionTab.fxml"));
    stage.setTitle("Production Line Tracker");
    Scene scene = new Scene(root, 600, 500);
    stage.setScene(scene);

    stage.show();
  }
}
