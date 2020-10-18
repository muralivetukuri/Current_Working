package com.prokarma.qa.tests;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Normalization_Final {

	public static void main(String[] args) throws Exception {
		
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+File.separator+"TestInput\\TestInput1.xls");
		
		Workbook wb=new HSSFWorkbook(fis);
		Sheet sh=wb.getSheet("TestData");
		Row fourthrow=sh.getRow(4);
		
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\BrowserDrivers\\chromedriver.exe");

		    WebDriver driver=new ChromeDriver();
		 
			driver.get("https://staging.pk-ems.com/emsclientqav4/#/Login");
			
			driver.findElement(By.id("txtUsername")).sendKeys("administrator");
			
			driver.findElement(By.id("txtPassword")).sendKeys("welcome");
			driver.findElement(By.id("btnLogin")).click();
			driver.manage().window().maximize();
//			Thread.sleep(8000);
			Normalization_Final.explct_wait(driver, "//a[contains(text(),'WORKSPACE')]", 60);
			//naviagation part 
			driver.findElement(By.xpath("//a[contains(text(),'WORKSPACE')]")).click();
//			Thread.sleep(3000);
			Normalization_Final.explct_wait(driver, "//div[@class='clientClientDiv']", 60);
			driver.findElement(By.xpath("//div[@class='clientClientDiv']")).click();
			Thread.sleep(3000);
//			Normalization_Final.explct_wait(driver, "//img[contains(@alt,'Forest Grove')]", 60);
			
			
//			String client="Forest Grove";			
			String client=fourthrow.getCell(2).getStringCellValue();
			driver.findElement(By.xpath("//img[contains(@alt,'"+client+"')]")).click();
			Thread.sleep(5000);
//			Normalization2.explct_wait(driver, "//li[contains(@class,'navList0')]", 60);
			driver.findElement(By.xpath("//li[contains(@class,'navList0')]")).click();
			Thread.sleep(3000);
//			Normalization2.explct_wait(driver, "//div[@class='parent-nav']/ul/li[4]", 60);
			driver.findElement(By.xpath("//div[@class='parent-nav']/ul/li[4]")).click();
			Thread.sleep(4000);
			
//			Normalization2.explct_wait(driver, "//span[text()=' Bill ']", 60);
			
			Actions actionss = new Actions(driver);
			actionss.moveToElement(driver.findElement(By.xpath("//span[text()=' Bill ']"))).click().build().perform();
			
//			String month_to_select="January";
			String month_to_select=fourthrow.getCell(7).getStringCellValue();
//			String year_to_select="2020";
			String year_to_select=fourthrow.getCell(10).getStringCellValue();
			Normalization_Final.billInfo(driver, month_to_select,year_to_select);
			
//			String utility="ELECTRIC";
			String utility=fourthrow.getCell(5).getStringCellValue();
			Normalization_Final.accountInfo(driver, utility);
			
//			String site="VIKING";
//			String site="Cedar";
			String site=fourthrow.getCell(3).getStringCellValue();
			Normalization_Final.siteInfo(driver, site);
			
			Normalization_Final.filterRecords(driver);
			 
			Thread.sleep(4000);
			
			ArrayList<ArrayList<String>> before_list = Normalization_Final.prepare_list_data(driver);
			System.out.println("Before List : "+before_list);
			
			String[] Serviceperioddates = before_list.get(0).get(1).split("-");
			
			String fromDate=Serviceperioddates[0];
			String toDate=Serviceperioddates[1];
				
			String[] fromDateSplit=fromDate.split("/");
			String fromDate_month=fromDateSplit[0];
			String fromDate_date=fromDateSplit[1];
			String fromDate_year=fromDateSplit[2];
			   
			String[] toDateSplit=toDate.split("/");
			String toDate_month=toDateSplit[0];
			String toDate_date=toDateSplit[1];
			String toDate_year=toDateSplit[2];

			/*	String Currentcharges = list.get(3).substring(list.get(3).indexOf("$") + 1, list.get(3).indexOf("("));
			// current charges

			String Totaldays = list.get(1);
			String Demand = list.get(9);
			String Usage = list.get(10);
			// Totaldays
			int TD = Integer.valueOf(Totaldays);

			// total demand
			// float value conversions
			float demand = Float.parseFloat(Demand);
			float usage = Float.parseFloat(Usage);
			float currentcharges = Float.parseFloat(Currentcharges);

			// Values per day
			//float demandperday = demand / TD;
			float usageperday = usage / TD;
			float currentcharesperday = currentcharges / TD;
			*/
			
			HashMap<String,String> map=new HashMap<String,String>();
			   map.put("January","01");   
			   map.put("February","02");    
			   map.put("March","03");   
			   map.put("April","04");
			   map.put("May","05");  
			   map.put("June","06");    
			   map.put("July","07");   
			   map.put("August","08");
			   map.put("September","09"); 
			   map.put("October","10");    
			   map.put("November","11");   
			   map.put("December","12");
			   
			String monthkey=map.get(month_to_select);  
			
			if(monthkey.equals(fromDate_month) && !fromDate_date.equals("01")) {
				Thread.sleep(5000);
				
//				Normalization2.explct_wait(driver, "//div[contains(@class,'jqx-expander-arrow')]", 60);
				driver.findElement(By.xpath("//div[contains(@class,'jqx-expander-arrow')]")).click();
				
				HashMap<String,String> backmonthmap=new HashMap<String,String>();
				backmonthmap.put("January","December");  
				backmonthmap.put("February","January");    
				backmonthmap.put("March","February");   
				backmonthmap.put("April","March");
				backmonthmap.put("May","April");  
				backmonthmap.put("June","May");    
				backmonthmap.put("July","June");   
				backmonthmap.put("August","July");
				backmonthmap.put("September","August");  
				backmonthmap.put("October","September");    
				backmonthmap.put("November","October");   
				backmonthmap.put("December","November");
				
				if(month_to_select.equals("January")) {
					Normalization_Final.billInfo(driver, backmonthmap.get(month_to_select), String.valueOf(Integer.valueOf(year_to_select)-1));
				}else {
					Normalization_Final.billInfo(driver, backmonthmap.get(month_to_select), year_to_select);
				}
				
				Normalization_Final.filterRecords(driver);
						
			}else if(monthkey.equals(toDate_month) && !toDate_date.equals("01")){
					Thread.sleep(5000);
					
//					Normalization2.explct_wait(driver, "//div[contains(@class,'jqx-expander-arrow')]", 60);
					driver.findElement(By.xpath("//div[contains(@class,'jqx-expander-arrow')]")).click();
					
					HashMap<String,String> frontmonthmap=new HashMap<String,String>();
					frontmonthmap.put("January","February");  
					frontmonthmap.put("February","March");    
					frontmonthmap.put("March","April");   
					frontmonthmap.put("April","May");
					frontmonthmap.put("May","June");  
					frontmonthmap.put("June","July");    
					frontmonthmap.put("July","August");   
					frontmonthmap.put("August","September");
					frontmonthmap.put("September","October");  
					frontmonthmap.put("October","November");    
					frontmonthmap.put("November","December");   
					frontmonthmap.put("December","January");
					
					
					if(month_to_select.equals("December")) {
						Normalization_Final.billInfo(driver, frontmonthmap.get(month_to_select), String.valueOf(Integer.valueOf(year_to_select)+1));
					}else {
						Normalization_Final.billInfo(driver, frontmonthmap.get(month_to_select), year_to_select);
					}
					
					Normalization_Final.filterRecords(driver);
				
			}
			
			List<WebElement>billReportWindoww = driver.findElements(By.xpath("//*[contains(@id,'jqxScrollThumbhorizontalScrollBarjqxWidget')]"));
			WebElement billReportWindoww2=Normalization_Final.getRequiredElement(billReportWindoww);
			
			ArrayList<ArrayList<String>> after_list = Normalization_Final.prepare_list_data(driver);
			System.out.println("After List : "+after_list);
			
	}
	
	public static void billInfo(WebDriver driver,String month_to_select,String year) throws Exception {
		
		driver.findElement(By.xpath("//*[@id='billInfoTab']")).click();
		Thread.sleep(4000);
//		Normalization2.explct_wait(driver, "(//label[text()='Consumption Period ']//following::div[1]//angulardropdownlist)[1]//div[contains(@id,'dropdownlistContentjqxWidget')]", 60);
		
		boolean istatus=false;
		while(istatus==false) {
			try {
			  driver.findElement(By.xpath("(//label[text()='Consumption Period ']//following::div[1]//angulardropdownlist)[1]//div[contains(@id,'dropdownlistContentjqxWidget')]")).click();
			  istatus=true;
			}catch(Exception e) {
				Thread.sleep(4000);
			}
		}	
		
		Thread.sleep(4000);
		
		List<WebElement> reqElementList=driver.findElements(By.xpath("//*[contains(@id,'jqxScrollThumbverticalScrollBarinnerListBoxjqxWidget')]"));
		
		
		WebElement scroll=Normalization_Final.getRequiredElement(reqElementList);
		
		boolean scrollstatus;
		
		try {
			driver.findElement(
				By.xpath("//span[text()='"+month_to_select+"']")).isDisplayed();
			
			Actions dragger = new Actions(driver);
			dragger.moveToElement(driver.findElement(
					By.xpath("//span[text()='"+month_to_select+"']"))).click().build().perform();
			
//			driver.findElement(
//					By.xpath("//span[text()='"+month_to_select+"']")).click();
			scrollstatus=false;
		}catch(Exception e) {
			scrollstatus=true;
		}
				
		
		while(scrollstatus==true) {
			
			Actions dragger = new Actions(driver);
			dragger.moveToElement(scroll).clickAndHold().moveByOffset(0,20).build().perform();
			try {
				scrollstatus=driver.findElement(
					By.xpath("//span[text()='"+month_to_select+"']")).isDisplayed();
				dragger.moveToElement(driver.findElement(
						By.xpath("//span[text()='"+month_to_select+"']"))).click().build().perform();
				break;
			}catch(Exception e) {
				scrollstatus=true;
			}	
			
		}
		
		driver.findElement(By.xpath("(//label[text()='Consumption Period ']//following::div[1]//angulardropdownlist)[1]//div[contains(@id,'dropdownlistContentjqxWidget')]")).click();
		reqElementList=driver.findElements(By.xpath("//*[contains(@id,'jqxScrollThumbverticalScrollBarinnerListBoxjqxWidget')]"));
		WebElement scrollback=Normalization_Final.getRequiredElement(reqElementList);
		
		
		Actions actionsss = new Actions(driver);
		boolean scrollStatus=false;
		
		while(scrollStatus==false) {
//		for(int i=1;i<=5;i++) {
			try {
				actionsss.dragAndDropBy(scrollback, 200 , -400).perform();
			}catch(Exception e) {
				break;
			}
		//}
		}
		
		Thread.sleep(4000);
//		Normalization2.explct_wait(driver, "(//label[text()='Consumption Period ']//following::div[1]//angulardropdownlist)[2]//div[contains(@id,'dropdownlistContentjqxWidget')]", 60);
		driver.findElement(By.xpath("(//label[text()='Consumption Period ']//following::div[1]//angulardropdownlist)[2]//div[contains(@id,'dropdownlistContentjqxWidget')]")).click();
		
		driver.findElement(
				By.xpath("//span[text()='"+year+"']"))
				.click();	
		
	}
	
	public static void accountInfo(WebDriver driver, String utility) throws Exception {
		
				// navigate to Account info tab
				driver.findElement(By.xpath("//a[@id='accountInfoTab']")).click();

				// click on utility dropdown
				driver.findElement(By.xpath("//label[contains(text(),' Utility Type ')]/parent::div/angulardropdownlist/div"))
						.click();

				// select utility ELECTRIC
				driver.findElement(By.xpath("//span[contains(text(),'"+utility+"')]")).click();
		
	}
	
	public static void siteInfo(WebDriver driver,String site) throws Exception {
		
				// navigate to site info tab
				driver.findElement(By.xpath("//a[@id='siteInfoTab']")).click();
				Thread.sleep(5000);
//				Normalization2.explct_wait(driver, "//angularinput[@id='txtSiteNbr']//input", 60);
				driver.findElement(By.xpath("//angularinput[@id='txtSiteNbr']//input")).sendKeys(site);
				driver.findElement(By.xpath("//angularinput[@id='txtSiteNbr']//input")).sendKeys(Keys.ARROW_DOWN);
				driver.findElement(By.xpath("//angularinput[@id='txtSiteNbr']//input")).sendKeys(Keys.ENTER);
				Thread.sleep(6000);
		
	}
	
	
	public static void filterRecords(WebDriver driver) throws Exception {
		// click on search
		Normalization_Final.getRequiredElement(driver.findElements(By.xpath("//button[text()='SEARCH']"))).click();
		//	driver.findElement(By.xpath("//button[@id='detailsbillBtn']/parent::div/button[2]")).click();
	}
	
	public static boolean isLeapYear(String str_year) {
		
		int year = Integer.valueOf(str_year);
		
        boolean leap = false;

        if(year % 4 == 0)
        {
            if( year % 100 == 0)
            {
                // year is divisible by 400, hence the year is a leap year
                if ( year % 400 == 0)
                    leap = true;
                else
                    leap = false;
            }
            else
                leap = true;
        }
        else
            leap = false;
        
        return leap;

	}	
	
	public static WebElement getRequiredElement(List<WebElement> reqElementList) {
			
			WebElement requiredelement = null;
			  
			  for(WebElement e:reqElementList) {
				  try {
					  if(e.isDisplayed() && e.isEnabled()) {
						  requiredelement=e;
						  break;
					  }
				  }catch(Exception exception){
					  
				  }
			  }
			  
			  return requiredelement;
	}
	
	public static ArrayList<ArrayList<String>> prepare_list_data(WebDriver driver) throws Exception {
		
		Actions actionsss = new Actions(driver);
		List<WebElement>billReportWindoww = driver.findElements(By.xpath("//*[contains(@id,'jqxScrollThumbhorizontalScrollBarjqxWidget')]"));
		WebElement billReportWindoww2=Normalization_Final.getRequiredElement(billReportWindoww);
		
		ArrayList<ArrayList<String>> mainList = new ArrayList<ArrayList<String>>();
		List<WebElement> rowlist = driver.findElements(By.xpath("//div[contains(@id,'contenttable')]/div[@role='row']"));
		
		for(int i=0;i<rowlist.size();i++) {
			
			List<WebElement> rowlistvalues = driver.findElements(By.xpath("//div[contains(@id,'contenttable')]/div[@role='row']["+String.valueOf(i+1)+"]/div"));
			ArrayList<String> templist=new ArrayList<String>();
			
			ArrayList<String> dummylist = new ArrayList<String>();
			 dummylist.add("");
			 dummylist.add("");
			 dummylist.add("");
			 dummylist.add("");
			 dummylist.add("");
			 dummylist.add("");
			 dummylist.add("");
			 dummylist.add("");
			 dummylist.add("");
			 dummylist.add("");
			 dummylist.add("");
			 
			 for (WebElement e:rowlistvalues) {
				   rowlistvalues = driver.findElements(By.xpath("//div[contains(@id,'contenttable')]/div[@role='row']["+String.valueOf(i+1)+"]/div"));
				   templist.add(e.getText());
				   actionsss.dragAndDropBy(billReportWindoww2, 9, 200).perform();
				   rowlistvalues = driver.findElements(By.xpath("//div[contains(@id,'contenttable')]/div[@role='row']["+String.valueOf(i+1)+"]/div"));
			 }
			 
			 boolean scrollStatus=false;
				while(scrollStatus==false){
					try {
						actionsss.dragAndDropBy(billReportWindoww2, -400 , 200).perform();
					}catch(Exception e) {
						break;
					}
				}
				
			 driver.findElement(By.xpath("//div[@class='jqx-expander-arrow jqx-icon-arrow-down jqx-expander-arrow-top']")).click();
			 Thread.sleep(5000);
			 Normalization_Final.filterRecords(driver);
			 Thread.sleep(5000);
			 
			 if(templist.equals(dummylist)) {
				break;
			 }else {
				 mainList.add(templist);
			 }	
			 
//			System.out.println("the temp list is "+templist);
//			System.out.println("the dummy list is "+dummylist);
			
		}
		
//		System.out.println("the main list is "+mainList);
		
		return mainList;
		
	}
	
	public static void explct_wait(WebDriver driver,String element_xpath,int seconds) {

		WebDriverWait wait = new WebDriverWait(driver,seconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element_xpath)));
		
	}

}