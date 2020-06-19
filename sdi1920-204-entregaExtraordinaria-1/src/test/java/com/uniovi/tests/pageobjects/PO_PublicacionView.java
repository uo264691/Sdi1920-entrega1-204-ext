package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_PublicacionView extends PO_NavView
{
	static public void fillForm(WebDriver driver, String title, String text)
	{
		WebElement usernameField = driver.findElement(By.name("title"));
		usernameField.click();
		usernameField.clear();
		usernameField.sendKeys(title);
		WebElement passwordField = driver.findElement(By.name("text"));
		passwordField.click();
		passwordField.clear();
		passwordField.sendKeys(text);
		// Pulsar el boton de Alta.
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
}
