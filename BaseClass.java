package Package1;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
	        // Open the Excel file
	        FileInputStream fis = new FileInputStream("C:\\Users\\aishwarya.pathak\\Documents\\TestData.xlsx");
	        // Access the required test data sheet
	        XSSFWorkbook wb = new XSSFWorkbook(fis);
	        XSSFSheet sheet = wb.getSheet("Testdata");
	        // Loop through all rows in the sheet
	        // Start at row 1 as row 0 is our header row
	        for(int count = 1;count<=sheet.getLastRowNum();count++){
	            XSSFRow row = sheet.getRow(count);
	            System.out.println("Running test case " + row.getCell(0).toString());
	            // Run the test for the current test data row
	            runTest(row.getCell(1).toString(),row.getCell(2).toString());
	        }
	        fis.close();
	    } catch (IOException e) {
	        System.out.println("Test data file not found");
	    }   
	
	}

	private static void runTest(String strSearchString, String strPageTitle) {
		
		//Set system property
		System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		//Instantiate the web driver
		WebDriver mydriver = new ChromeDriver();
		//Navigate to Google
		mydriver.get("https://www.google.com/");
		
		WebElement element = mydriver.findElement(By.name("q"));
        element.sendKeys(strSearchString);
        element.submit();
        
        if (mydriver.getTitle().equals(strPageTitle)) {
            System.out.println("Page title is " + strPageTitle + ", as expected");
        } else {
            System.out.println("Expected page title was " + strPageTitle + ", but was " + mydriver.getTitle() + " instead");
        }
        
        //Close the browser
        mydriver.quit();
	}

}
