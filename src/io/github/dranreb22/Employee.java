package io.github.dranreb22;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Class to create an Employee. Checks to see if the Employees entered first name and last name
 * match the criteria. If it does, it sets the email and password based on what was entered. If it
 * doesn't match the criteria, it sets the username to default and the password to pw.
 * </p>
 *
 * @author Bernard Georges 9/26/2019
 */
class Employee {

  private final String name;
  private String username;
  private final String password;
  private String email;

  /**
   * Constructor that passes either a valid name entry to set the username and password, or passes
   * default values if the entry isn't valid.
   *
   * @param name     The name the employee entered (as first name last name).
   * @param password The password of the employee.
   */
  Employee(String name, String password) {
    String[] splitName = name.split(" ");
    if (checkName(name)) {
      String firstInitialLastName =
          toCapital(splitName[0].substring(0, 1)) + toCapital(splitName[1]);
      String firstNameDotLastName = splitName[0].toLowerCase() + "." + splitName[1].toLowerCase();
      this.name = toCapital(splitName[0]) + " " + toCapital(splitName[1]);
      setUsername(firstInitialLastName);
      setEmail(firstNameDotLastName);
    } else {
      this.name = toCapital(name);
      setUsername("default");
      setEmail("user");
    }

    if (isValidPassword(password)) {
      this.password = password;
    } else {
      this.password = "pw";
    }
  }

  /**
   * Sets username for the employee based on the constructor.
   * @param name The name of the employee (first name last name)
   */
  private void setUsername(String name) {
    this.username = name;
  }

  /**
   * Method to check the validity of the name (checks for a space)
   * @param name The name of the employee (first name last name)
   * @return A boolean based on whether first name last name matches appropriately.
   */
  private boolean checkName(String name) {
    String regex = ".+ .+";
    Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
    Matcher matcher = pattern.matcher(name);
    return matcher.matches();
  }

  /**
   * Accepts the from constructor and generates the email based on that.
   * @param name The name of the employee or the word user.
   */
  private void setEmail(String name) {
    this.email = name + "@oracleacademy.Test";
  }


  /**
   * Check a regular expression matching for the password (Capital, lowercase, and exclamation in any order).
   * @param password Password entered by the user.
   * @return A boolean of if the password matches the proper formatting.
   */
  private boolean isValidPassword(String password) {
    String regex = "^(?=.*[!@#$%^&*()\\-+=]+.*)(?=.*[A-Z]+.*)(?=.*[a-z]+.*)[\\x21-\\x7e]{3,}$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(password);
    return matcher.find();
  }

  /**
   * Capitizes first letter of the word and lower cases the remaining characters.
   * @param word The word being entered.
   * @return The capitalized version of said word.
   */
  private String toCapital(String word) {
    word = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    return word;
  }

  /**
   * Overloaded to string method printing out the Employee Details.
   * @return
   */
  @Override
  public String toString() {
    return "Employee Details:\n\n"
        + "Name : " + name + "\n"
        + "Username : " + username.toLowerCase() + "\n"
        + "Email : " + email + "\n"
        + "Initial Password : " + password;
  }
}