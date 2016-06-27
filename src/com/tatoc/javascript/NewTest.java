package com.tatoc.javascript;

import java.util.List;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class NewTest{
	public static void main(String ar[]) throws InterruptedException{
		WebDriver driver = new FirefoxDriver();
		driver.get("http://10.0.1.86/tatoc/basic/grid/gate");
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		//Grid Gate
		@SuppressWarnings("unchecked")
		List<WebElement> gridGate =(List<WebElement>) js.executeScript(" return document.querySelectorAll('div.greenbox');");
		gridGate.get(0).click(); 
		
		//Frame Dungeon
		driver.switchTo().frame(0);
		List<WebElement> frameDungeon =(List<WebElement>) js.executeScript(" return document.querySelectorAll('div#answer');");
		String find= frameDungeon.get(0).getAttribute("class");
		String s="xyz"; List<WebElement> ss,sd;
		while (s.equals(find)== false){
			driver.switchTo().frame("child");
			ss = (List<WebElement>) js.executeScript(" return document.querySelectorAll('div#answer');");
			s = ss.get(0).getAttribute("class");
			if(s.equals(find)== true)
				break;
			driver.switchTo().defaultContent();
			driver.switchTo().frame(0);
			sd =(List<WebElement>) js.executeScript("return document.querySelectorAll('a')");
			sd.get(0).click();
			
			
		}
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		sd =(List<WebElement>) js.executeScript("return document.querySelectorAll('a')");
		sd.get(1).click();
		
		//Drag
		Actions action = new Actions(driver);
		List<WebElement> Sourcelocator= (List<WebElement>) js.executeScript(" return document.querySelectorAll('div#dragbox');");
		List<WebElement> Destinationlocator = (List<WebElement>) js.executeScript(" return document.querySelectorAll('div#dropbox');");
		action.dragAndDrop(Sourcelocator.get(0), Destinationlocator.get(0)).build().perform();
		sd =(List<WebElement>) js.executeScript("return document.querySelectorAll('a')");
		sd.get(0).click();
		
		//window
		sd =(List<WebElement>) js.executeScript("return document.querySelectorAll('a')");
		sd.get(0).click();
		String  handle= driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		sd =(List<WebElement>) js.executeScript("return document.querySelectorAll('#name')");
		sd.get(0).sendKeys("Nikhil");
		ss =(List<WebElement>) js.executeScript("return document.querySelectorAll('#submit')");
		ss.get(0).click();
		driver.switchTo().window(handle);
		sd =(List<WebElement>) js.executeScript("return document.querySelectorAll('a')");
		sd.get(1).click();
		
		//cookie handling
		sd =(List<WebElement>) js.executeScript("return document.querySelectorAll('a')");
		sd.get(0).click();
		ss =(List<WebElement>) js.executeScript("return document.querySelectorAll('#token')");
		String ck =ss.get(0).getText();
		int y= ck.length(); String z="";
		for(int x=7;x<y ; x++){
			z+=ck.charAt(x);
		}
		Cookie token = new Cookie("Token", z);
		driver.manage().addCookie(token);
		sd.get(1).click();
		Thread.sleep(2000);
		driver.quit();
		
		
		 
	}
}
	
	


