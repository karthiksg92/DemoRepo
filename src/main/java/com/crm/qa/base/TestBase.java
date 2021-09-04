package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop = new Properties();
	public Logger log = Logger.getLogger(getClass());
	
	public static void initialization() {
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(prop.getProperty("browser").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
					+ "\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		EventFiringWebDriver eventdriver = new EventFiringWebDriver(driver);
		WebEventListener webEventListener = new WebEventListener();
		eventdriver.register(webEventListener);
		driver = eventdriver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	}
}
