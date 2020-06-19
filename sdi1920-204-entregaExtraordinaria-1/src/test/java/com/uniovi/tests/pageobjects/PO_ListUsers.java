package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_ListUsers {
	static public void fillForm(WebDriver driver, String name)
	{
		WebElement usernameField = driver.findElement(By.name("searchText"));
		usernameField.click();
		usernameField.clear();
		usernameField.sendKeys(name);
		
		// Pulsar el boton de Alta.
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
}
