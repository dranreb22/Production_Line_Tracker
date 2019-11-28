package io.github.dranreb22;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee {

  private String name;
  private String username;
  private String password;
  private String email;

  Employee(String name, String password) {
    String[] splitName = name.split(" ");
    if (checkName(name)) {
      String firstInitialLastName = splitName[0].substring(0, 1) + toCapital(splitName[1]);
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

  private void setUsername(String name) {
    this.username = name;
  }

  private boolean checkName(String name) {
    String regex = ".+ .+";
    Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
    Matcher matcher = pattern.matcher(name);
    return matcher.matches();
  }

  private void setEmail(String name) {
    this.email = name + "@oracleacademy.Test";
  }


  private boolean isValidPassword(String password) {
    String regex = "^(?=.*[!@#$%^&*()\\-+=]+.*)(?=.*[A-Z]+.*)(?=.*[a-z]+.*)[\\x21-\\x7e]{3,}$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(password);
    return matcher.find();
  }

  private String toCapital(String word) {
    word = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    return word;
  }

  @Override
  public String toString() {
    return "Employee Details:\n\n"
        + "Name : " + name + "\n"
        + "Username : " + username.toLowerCase() + "\n"
        + "Email : " + email + "\n"
        + "Initial Password : " + password;
  }
}