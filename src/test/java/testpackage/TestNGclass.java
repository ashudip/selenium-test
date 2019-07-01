package testpackage;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import POMpackage.Login_identifiers;

public class TestNGclass {
	
	WebDriver driver;
    String xl_path,xl_tcRes,xl_TsRes;
    int xRows_TC, xRows_TS, xCols_TC, xCols_TS,randno;
    String[][] xlTC, xlTS;
    String vKW, vIP1, vIP2,Vactual,vExpected;
    String vTS_Res, vTC_Res;
    String res;
    
	@BeforeTest
    public void init() throws Exception
    {
        xl_path = "/home/codemaxpc-01/Desktop/BTA/seleniumTC/Testcase.xlsx";
        xl_tcRes = "/home/codemaxpc-01/Desktop/BTA/seleniumTC/tcres_gmailtest.xlsx";
        xl_TsRes = "/home/codemaxpc-01/Desktop/BTA/seleniumTC/tsres_gmailtest.xlsx";
        xlTC = ReadExcel.readXL(xl_path,"Test cases");
        xlTS = ReadExcel.readXL(xl_path,"Test Steps");
    xRows_TC = xlTC.length;
    xCols_TC = xlTC[0].length;
    System.out.println("TC Rows are " + xRows_TC);
    System.out.println("TC Cols are " + xCols_TC);
    
    xRows_TS = xlTS.length;
    xCols_TS = xlTS[0].length;
    System.out.println("TS Rows are " + xRows_TS);
    System.out.println("TS Cols are " + xCols_TS);
}
	@Test
  public void f() throws IOException {
		for (int i = 1; i <xRows_TC; i++) {

            if(xlTC[i][2].equals("Y"))
            {
                System.out.println("Test case ready for execution :-"+xlTC[i][0]);
                vTC_Res = "pass";
                for (int j = 1; j < xRows_TS; j++) {
                    if(xlTC[i][0].equals(xlTS[j][0]))
                    {
                        vKW  = xlTS[j][3];
                        vIP1 = xlTS[j][4];
                        vIP2 = xlTS[j][5];
                        vExpected = xlTS[j][6];  
                        vTS_Res = "pass";
                        System.out.println("KW: " + vKW);
                        System.out.println("IP1: " + vIP1);
                        System.out.println("IP2: " + vIP2);
                        System.out.println("vExpected: " +vExpected);
                        try {
                        
                       res = execute(vKW,vIP1,vIP2,vExpected);
                                if(vTS_Res.equals("pass"))
                                {
                                    vTS_Res = "pass";
                                   // takeScreenshot("/home/codemaxpc-01/Desktop/BTA/PassScreenshot/pass"+xlTC[i][0]+"_"+j+".png");
                                    
                                }
                            else {
                                 
                                vTS_Res = "verfication failed";
                                vTC_Res = "fail";
                               xlTS[j][9] = "look at the screenshot";
                               takeScreenshot("/home/codemaxpc-01/Desktop/BTA/FailScreenshot/fail"+xlTC[i][0]+"_"+j+".png");
                                    
                            }
                        } catch (Exception e) {
                            // TODO: handle exception
                            System.out.println("My coding errror "+e);
                            vTC_Res = "fail";
                            vTS_Res = "fail";
                            xlTS[j][9] = "look at screenshot";
                            takeScreenshot("/home/codemaxpc-01/Desktop/BTA/FailScreenshot/fail"+xlTC[i][0]+"_"+j+".png");
                         }
                        xlTS[j][7] = res;
                        xlTS[j][8] = vTS_Res;
                    }
                }
                xlTC[i][3] = vTC_Res;
        }
            else
            {
                System.out.println("test case not ready for execution"+xlTC[i][0]);
            }
        }
        
    }
	@AfterTest
    public void teardown() throws Exception
    {
        WriteExcel.writeXL(xl_tcRes, "Test cases", xlTC);
        WriteExcel.writeXL(xl_TsRes, "Test step", xlTS);
        //driver.quit();        
    }
	
    public String execute(String KW,String IP1,String IP2,String expected) throws InterruptedException {
    	if(KW.equalsIgnoreCase("LaunchBrowser"))
        {
            LaunchBrowser();
            return null;
        }
    	else if(KW.equalsIgnoreCase("enter_url"))
    	{
    		enter_url(IP1);
    		return null;
    	}
    	else if (KW.equalsIgnoreCase("Enter_Username"))
    	{
    		enter_Username(IP1);
    		return null;
    	}
    	else if(KW.equalsIgnoreCase("password"))
    	{
    		password(IP1);
    		return null;
    	}
    	else if(KW.equalsIgnoreCase("clicklogin"))
    	{
    		String res;
    		res = click_login(expected);
    		return res;
    	}
    	else if(KW.equalsIgnoreCase("closebrowser"))
    	{
    		closebrowser();
    		return null;
    	}
    	else {
    		return null;
    	}
    }
    
    public void LaunchBrowser()
    {
    	System.setProperty("webdriver.gecko.driver","/home/codemaxpc-01/Desktop/BTA/geckodriver");	   
    	driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    driver.manage().window().maximize();
    }
    public void enter_url(String ip1)
    {
    	 driver.navigate().to(ip1);	        
    }
   public void enter_Username(String ip1)
   {
	   WebDriverWait wait = new WebDriverWait(driver,10);
	   wait.until(ExpectedConditions.elementToBeClickable(Login_identifiers.username(driver)));
	   Login_identifiers.username(driver).sendKeys(ip1);
   }
   public void password(String ip1)
   {
	   Login_identifiers.password(driver).sendKeys(ip1);
   }
   public String click_login(String exp) throws InterruptedException
   {
	   Login_identifiers.clickloginbtn(driver).click();
	   Thread.sleep(1000);
	    String res = Login_identifiers.spanmsg(driver).getText();
	   if(res.equalsIgnoreCase(exp))
	   {
		   vTS_Res = "pass";
		   vTC_Res = "pass";
		   return res;
	   }
	   else 
	   {
		   vTS_Res = "fail"; 
		   vTC_Res = "fail";
		   return res;
 	   }
	   
   }
   
 public void closebrowser() {
	 driver.quit();
 }
  
 public void takeScreenshot(String path) throws IOException
 {
	 File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 FileUtils.copyFile(src, new File(path));
 }

}
