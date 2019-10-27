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

public class DatabaseManager {

    private static Connection conn;
    private static String productQuery;
    private static PreparedStatement preparedStatement;
    private ResultSet result;

    public static Connection initializeDb() {
        final String jdbcDriver = "org.h2.Driver";
        final String db_Url = "jdbc:h2:./res/production";

        //  Database credentials
        // to create a database username and password,
        // type Create USER [username] WITH PASSWORD "[password]"
        // to allow the user to edit the database use GRANT ALTER ANY SCHEMA TO [username]; in console
        final String user = "";
        final String pass = "";

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

        return conn;
    }

    public static void addProduct(String name, String manufacturer, String type) {
        String[] product = {name, manufacturer, type};
        int index = 1;
        try {

            //Execute a query
            productQuery = "INSERT INTO product(name, manufacturer, TYPE) VALUES (?,?,?)";

            preparedStatement = conn.prepareStatement(productQuery);
            for (String s : product) {
                System.out.println(s);
                preparedStatement.setString(index, s);
                index++;
            }
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Product> getAvailableProducts() {
        try {
            productQuery = "SELECT * FROM PRODUCT";
            preparedStatement = conn.prepareStatement(productQuery);
            result = preparedStatement.executeQuery();
            Product product;
            ArrayList<Product> productLine = new ArrayList<>();

            while (result.next()) {
                String name = result.getString("NAME");
                String manufacturer = result.getString("MANUFACTURER");
                String type = result.getString("TYPE");
                if (type == "audio".toUpperCase())
                    product = new AudioPlayer(name, manufacturer, ItemType.valueOf(type));
                else if (type == "visual".toUpperCase())
                    continue;
                else if (type == "visualmobile".toUpperCase())
                    continue;
                else {
                    continue;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}