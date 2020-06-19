package com.uniovi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_PrivateView extends PO_NavView {
	static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp, String scorep) {
		//Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
		SeleniumUtils.esperarSegundos(driver, 5);
		// Seleccionamos el alumnos userOrder
		new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);
		// Rellenemos el campo de descripción
		WebElement description = driver.findElement(By.name("description"));
		description.clear();
		description.sendKeys(descriptionp);
		WebElement score = driver.findElement(By.name("score"));
		score.click();
		score.clear();
		score.sendKeys(scorep);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
	
//	public static boolean searchInPages(WebDriver driver, String str) {
//		List<WebElement> elementos;
//		// Esperamos a que se muestren los enlaces de paginación la lista de notas
//		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
//		elementos.get(0).click();
//		
//		elementos = PO_View.checkElement(driver, "text", str);
//		System.out.println(elementos.size());
//		if(elementos.size() > 0)
//			return true;
//		
//		
//		
//		while(PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]").size()>0)
//		{
//			// Nos vamos a la siguiente página
//			elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
//			elementos.get(2).click();
//			// Comprobamos que aparece la nota en la pagina
//			elementos = PO_View.checkElement(driver, "text", str);
//			if(elementos.size() > 0)
//				return true;
//		}
//		return false;
//	}
}