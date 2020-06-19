package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_LoginView extends PO_NavView
{
	static public void fillForm(WebDriver driver, String username, String password)
	{
		WebElement usernameField = driver.findElement(By.name("username"));
		usernameField.click();
		usernameField.clear();
		usernameField.sendKeys(username);
		WebElement passwordField = driver.findElement(By.name("password"));
		passwordField.click();
		passwordField.clear();
		passwordField.sendKeys(password);
		// Pulsar el boton de Alta.
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
}
