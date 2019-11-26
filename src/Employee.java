/*
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Employee {
  String name;
  String username;
  String password;
  String email;

  Employee(String name, String password) {
    if (checkName(name)){
      String[] splitName = name.split(" ");
      String firstInitialLastName = splitName[0].substring(0,1) + splitName[1];

      this.name[0].toString().substring(0,1) + splitName[1];
      setUsername(firstInitialLastName);
      setEmail(firstInitialLastName);
    }
    else{
      setUsername("default");
      //setEmail(new StringBuilder("user"));
    }
    //createUsername(name);
    this.password = password;
  }

  private void setUsername(String name){
    this.username = name;
  }
  public String getUsername(){
    return this.username.toString();
  }
  private boolean checkName(String name){
    String regex = ".+ .+";
    Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
    Matcher matcher = pattern.matcher(name);
    return matcher.matches();
  }

  // private void setEmail(email){
  //   this.email = email;
  // }

  private boolean isValidPassword(){
    return true;
  }
}
*/
