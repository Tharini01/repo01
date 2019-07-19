package Casestudy;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Casestudy.Capture;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class OnlineShoppingTest {
	WebDriver driver;
	ExtentReports extent;
	ExtentTest logger;
	
	
  @Test(priority=1)
  public void testRegistration() throws InterruptedException, IOException {
	 
	  //REGISTRATION PROCEDURE:
	 driver.findElement(By.partialLinkText("SignUp")).click();
	 
	 //takes to the SignIn page
	 driver.findElement(By.name("userName")).sendKeys("latha123");
	 driver.findElement(By.name("firstName")).click();
	 Thread.sleep(2000);
	 String text=driver.findElement(By.id("err")).getText();
	 
	 //to check whether the username exists
	 if(text.equalsIgnoreCase("Available"))
	 {
		  driver.findElement(By.name("firstName")).sendKeys("admin");
		  driver.findElement(By.name("lastName")).sendKeys("usernames");
		  driver.findElement(By.name("password")).sendKeys("admin123");
		  driver.findElement(By.name("confirmPassword")).sendKeys("admin123");
		  driver.findElement(By.xpath("//input[@value='Female']")).click();
		  driver.findElement(By.name("emailAddress")).sendKeys("admin@gmail.com");
		  driver.findElement(By.name("mobileNumber")).sendKeys("9876543210");
		  driver.findElement(By.name("dob")).sendKeys("01/02/1997");
		  driver.findElement(By.name("address")).sendKeys("New Street, Chennai");
		  Select a=new Select(driver.findElement(By.id("securityQuestion")));
		  a.selectByIndex(1);
		  driver.findElement(By.id("answer")).sendKeys("blue");
		  driver.findElement(By.name("Submit")).click();
		  logger.log(LogStatus.PASS, "Registration Passed", "Registration has been made successfully");
	 }
	 else
	 {
		 System.out.println("Provided username already exists");
		 logger.log(LogStatus.FAIL,"Username already exists", logger.addScreenCapture(Capture.takeScreenshot(driver)));
		 driver.findElement(By.partialLinkText("Home")).click();
		 driver.findElement(By.partialLinkText("SignIn")).click();
	 }
	 
  }
  
  @Test(priority=2)
  public void testLogin() throws IOException {
	
	//SIGNIN PROCEDURE: 
	  	 driver.findElement(By.name("userName")).sendKeys("lalitha");
		 driver.findElement(By.name("password")).sendKeys("Password123");
		 driver.findElement(By.name("Login")).click();
		 
		//to check whether the SignIn is successful	 
		 if(driver.getTitle().contains("Home"))
		{
			 System.out.println("Successfully Logged into the Home Page");
			 logger.log(LogStatus.PASS, "Login Passed", "Successfully Logged into the Home Page");
		}
		 else
		 {
			 System.out.println("Username or password is incorrect");
			 logger.log(LogStatus.FAIL,"Login Failed", logger.addScreenCapture(Capture.takeScreenshot(driver)));
		 }
  }
  
  @Test(priority=3)
  public void testCart() throws IOException {
	  
	  //Product availability
		 driver.findElement(By.name("products")).sendKeys("Headphone");
		 driver.findElement(By.xpath("//input[@value='FIND DETAILS']")).click();
		 driver.findElement(By.linkText("Add to cart")).click();
		 driver.findElement(By.partialLinkText("Cart")).click();
		 String a=driver.findElement(By.className("nomargin")).getText();
		 //Assert.assertEquals("Headphone", a)
		 
		 if(a.equals("Headphone"))
		 {
			 System.out.println("The product is available");
			 logger.log(LogStatus.PASS, "Add_to_Cart Passed", "Product has been successfully added to the Cart");
			
		 }
		 else
		 {
			 System.out.println("The product is not available");
			 logger.log(LogStatus.FAIL,"Product doesn't exist", logger.addScreenCapture(Capture.takeScreenshot(driver)));
		 }
			
  }
  
  @Test(priority=4)
  public void testPayment() throws InterruptedException, IOException {
	  
	  //payment details
	  driver.findElement(By.partialLinkText("Checkout")).click();
	  driver.findElement(By.xpath("//input[@value='Proceed to Pay']")).click();
	  Thread.sleep(7000);
	 // driver.findElement(By.xpath("//input[@value='Icici Bank']")).click();
	  
	  //directs to the bank page
	  driver.findElement(By.cssSelector("#swit > div:nth-child(1) > div > label > i")).click();
	  driver.findElement(By.id("btn")).click();
	  
	  //provide the respective bank login details
	  driver.findElement(By.name("username")).sendKeys("123456");
	  driver.findElement(By.name("password")).sendKeys("Pass@456");
	  driver.findElement(By.xpath("//input[@value='LOGIN']")).click();
	  driver.findElement(By.name("transpwd")).sendKeys("Trans@456");
	  driver.findElement(By.xpath("//input[@value='PayNow']")).click();
	  String title=driver.getTitle();
	  if(title.equals("Order Details"))
	  {
		  System.out.println("The payment is done successfully");
		  logger.log(LogStatus.PASS, "Payment Passed", "The payment is done successfully");
	  }
	  else
	  {
		  System.out.println("Payment failed");
		  logger.log(LogStatus.FAIL,"Payment failed", logger.addScreenCapture(Capture.takeScreenshot(driver)));
	  }  
  }


  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.chrome.driver","C:\\Users\\ishwarya.devanathan\\Desktop\\chromedriver.exe");
	  driver=new ChromeDriver();
	  driver.get("http://10.232.237.143:443/TestMeApp");
	  extent=new ExtentReports("C:\\Selenium\\myreport.html",false);
	  logger=extent.startTest("OnlineShoppingTest");
  }

  @AfterTest
  public void afterTest() {
	  driver.close();
	  extent.flush();
	  extent.endTest(logger);
	  
  }

}
