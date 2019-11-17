package io.github.dranreb22;

import java.util.Comparator;
import java.util.Scanner;
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
/*    Scanner scan = new Scanner(System.in);

    Product product1 = new Product("Galaxy Note 10", "Samsung", null);

    //Product product2 = new Product("Pixel 4", "Google");

    System.out.print("Enter product name: ");
    String pName = scan.nextLine();
    System.out.print("Enter product manufacturer: ");
    String pMan = scan.nextLine();

    Product product2 = new Product(pName, pMan, null);

    Comparator<Product> compare = Comparator.comparing((Product::getName));

*//*    Comparator<Product> productComparatorLambda = (Product product1, Product product2) -> {
      return (product1.getName().compareTo(product2.getName()));
    };*//*

    if (compare.compare(product1, product2) < 1) {
      System.out.println("product1 comes first alphabetically");
    }
    else {
      System.out.println("product2 comes first alphabetically");
    }*/



    Parent root = FXMLLoader.load(getClass().getResource("./fxml_design/ProductionTab.fxml"));
    stage.setTitle("Production Line Tracker");
    Scene scene = new Scene(root, 600, 500);
    stage.setScene(scene);

    stage.show();
  }
}
