package com.uniovi.tests.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;
import java.util.List;


public class PO_HomeView extends PO_NavView
{
	static public void checkWelcome(WebDriver driver, int language)
	{
		//Esperamos a que se cargue el saludo de bienvenida en Español
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("welcome.message",language), getTimeout());
	}
	
	static public void checkChangeLanguage(WebDriver driver, String textLanguage1, String textLanguage2, int locale1, int locale2 )
	{
		//Esperamos a que se cargue el saludo de bienvenida en Español
		PO_HomeView.checkWelcome(driver, locale1);
		//Cambiamos a segundo idioma
		PO_HomeView.changeLanguage(driver, textLanguage2);
		//Comprobamos que el texto de bienvenida haya cambiado a segundo idioma
		PO_HomeView.checkWelcome(driver, locale2);
		//Volvemos a Español.
		PO_HomeView.changeLanguage(driver, textLanguage1);
		//Esperamos a que se cargue el saludo de bienvenida en Español
		PO_HomeView.checkWelcome(driver, locale1);
	}
	
	static public void checkChangeIdiomaEsEnEs(WebDriver driver, String text1, String text2)
	{
		List<WebElement> elementos = PO_View.checkElement(driver, "text", text1);
		
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "btnLanguage", getTimeout());
		elementos.get(0).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "btnEnglish", getTimeout());
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "text", text2);
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "btnLanguage", getTimeout());
		elementos.get(0).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "btnSpanish", getTimeout());
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "text", text1);
		
	}
}
