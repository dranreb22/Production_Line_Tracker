package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    /*Parent root = FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));
    primaryStage.setTitle("Hello World");
    primaryStage.setScene(new Scene(root, 1920, 900));
    primaryStage.show();*/
    Parent root = FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));
    //Group root = new Group();
    Scene scene = new Scene(root, 500, 200);
    stage.setScene(scene);

    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

    //set Stage boundaries to visible bounds of the main screen
    stage.setX(primaryScreenBounds.getMinX());
    stage.setY(primaryScreenBounds.getMinY());
    stage.setWidth(primaryScreenBounds.getWidth());
    stage.setHeight(primaryScreenBounds.getHeight());

    stage.show();
  }
}
