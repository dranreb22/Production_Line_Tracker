package io.github.dranreb22;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import io.github.dranreb22.interfaces.Item;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

public class DatabaseManager {

    private Connection conn;
    private String productQuery;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private final String jdbcDriver = "org.h2.Driver";
    private final String db_Url = "jdbc:h2:./res/production";
    //Database credentials
    // to create a database username and password,
    // type Create USER [username] WITH PASSWORD "[password]"
    // to allow the user to edit the database use GRANT ALTER ANY SCHEMA TO [username]; in console
    private final String user = "";
    private final String pass = "";

    public void initializeDb() {

        try {
            // STEP 1: Register JDBC driver
            Class.forName(jdbcDriver);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(db_Url, user, pass);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(String name, String manufacturer, String type) {
        String[] product = {name, manufacturer, type};
        int index = 1;
        try {

            //Execute a query
            productQuery = "INSERT INTO PRODUCT(NAME, MANUFACTURER, TYPE) VALUES(?,?,?)";
            preparedStatement = conn.prepareStatement(productQuery);
            for (String s : product) {
                //System.out.println(s);
                preparedStatement.setString(index, s);
                index++;
            }
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Product> getAvailableProducts() {
      List<Product>  productLine = new ArrayList<>();
      try {
            productQuery = "SELECT * FROM PRODUCT";
            preparedStatement = conn.prepareStatement(productQuery);
            result = preparedStatement.executeQuery();


            while (result.next()) {
                String name = result.getString("NAME");
                String manufacturer = result.getString("MANUFACTURER");
                String type = result.getString("TYPE");
                productLine.add(new Widget(name, manufacturer, ItemType.valueOf(type)));
//                if (type.equals("AUDIO")) {
//                  productLine.add(new AudioPlayer(name, manufacturer, ItemType.valueOf(type)));
//                }
//                else if (type.equals("VISUAL"))
//                {
//                  //productLine.add(new MoviePlayer(name, manufacturer, ItemType.valueOf(type)));
//                }
//                else if (type.equals("VISUALMOBILE")) {
//                  //productLine.add(new AudioPlayer(name, manufacturer, ItemType.valueOf(type)));
//                }
//                else {
//                  //productLine.add(new AudioPlayer(name, manufacturer, ItemType.valueOf(type)));
//                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
      return productLine;
    }

    public void testMethod(){

    }
}