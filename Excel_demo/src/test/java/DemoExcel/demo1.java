package DemoExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class demo1 
{
 WebDriver driver;
	
    @BeforeSuite 
	public void openBrowser()
	{
		System.out.println("Open browser : Before suite");
       System.setProperty("webdriver.chrome.driver", "E:\\Tb_16\\103\\chromedriver.exe");
	    
		driver=new ChromeDriver();
	}
     @BeforeTest
    public void enterUrl()
    {
    	System.out.println("Enter url : Before ");
    	
        driver.get("https://demo.guru99.com/test/newtours/index.php");
      
    }
     @BeforeClass
     public void maximize()
     {
    	 System.out.println("Maximize browser : Before class");
    	 driver.manage().window().maximize();
     }
     @BeforeMethod
     public void getCookies()
     {
    	 System.out.println("getcookies : Before method");
    	Set<Cookie> ck=driver.manage().getCookies();
    	System.out.println(ck.size());
     }
     @Test
     public void loginCheck() throws IOException
     {
    		//to read the data from the excel sheet
   	  FileInputStream fis=new FileInputStream("E:\\Excel_sheet\\Demo\\Demo1.xlsx");
   	  
      XSSFWorkbook wb= new XSSFWorkbook(fis);
	  
	  XSSFSheet sheet= wb.getSheet("Sheet1");
	 // XSSFRow row=sheet.getRow(0);
	  XSSFRow row1=sheet.getRow(1);
	  XSSFCell cell=row1.getCell(0);
	  XSSFCell cell1=row1.getCell(1);
   	 String username= cell.getStringCellValue();
   	 String pass= cell1.getStringCellValue();
   	 
    	 driver.findElement(By.name("userName")).sendKeys(username);
    	 driver.findElement(By.name("password")).sendKeys(pass);
    	 driver.findElement(By.name("submit")).click();
    	 System.out.println("login success");
    	 
    	 
     }
     @AfterMethod
     public void capturesc() throws IOException
     {
    	File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	FileUtils.copyFile(src, new File("E:\\Tb_16\\Screenshot_file\\mercury.png"));
     }
     @AfterClass
     public void deleteCookies()
     {
    	 System.out.println("deleteCookies : AfterClass");
    }
     @AfterTest
     public void dbclose()
     {
    	 System.out.println("dbclose : AfterTest ");
     }
      @AfterSuite
        public void closeBrowser()
     {
    	//  driver.close();
    	 System.out.println("closeBrowser : ");
     }
}
