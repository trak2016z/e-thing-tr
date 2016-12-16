/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ething.config;

/**
 *
 * @author Koksik
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator{

	  private Pattern pattern;
	  private Matcher matcher;

	  private static final String PASSWORD_PATTERN =
              "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\\\S+$).{8,}";

	  public PasswordValidator(){
		  pattern = Pattern.compile(PASSWORD_PATTERN);
	  }

	  /**
	   * Validate password with regular expression
	   * @param password password for validation
	   * @return true valid password, false invalid password
	   */
	  public boolean validate(final String password){

		  matcher = pattern.matcher(password);
		  return matcher.matches();

	  }
}