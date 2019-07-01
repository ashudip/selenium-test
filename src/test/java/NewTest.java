import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class NewTest {
	WebDriver driver;
	@Test
  public void f() {
	  String path = System.getProperty("user.dir");
	    System.out.println(path); 
		System.setProperty("webdriver.gecko.driver",path+"/src/main/resources/geckodriver");	   
 	    driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    driver.navigate().to("https://stackoverflow.com/questions/48868717/set-relative-path-for-selenium-web-driver");
	    driver.close();
		
		
	}
}
