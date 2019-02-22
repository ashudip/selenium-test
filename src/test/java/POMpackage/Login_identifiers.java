package POMpackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login_identifiers {
	private static WebElement element = null;
	 
	 public static WebElement username(WebDriver driver){
	 
	    element = driver.findElement(By.xpath("//input[@id='txtUsername']"));
	 
	    return element;
	   }
	
	 public static WebElement password(WebDriver driver){
		 
		    element = driver.findElement(By.xpath("//input[@id='txtPassword']"));
		 
		    return element;
		   } 
	
	 public static WebElement clickloginbtn(WebDriver driver){
		 
		    element = driver.findElement(By.xpath("//input[@id='btnLogin']"));
		 
		    return element;
		   }
	
	 public static WebElement spanmsg(WebDriver driver){
		 
		    element = driver.findElement(By.xpath("//span[@id='spanMessage']"));
		 
		    return element;
		   }
}
