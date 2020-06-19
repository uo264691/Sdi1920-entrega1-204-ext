package com.uniovi.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.*;
import org.openqa.grid.web.servlet.handler.SeleniumBasedRequest;
import org.openqa.grid.web.servlet.handler.SeleniumBasedResponse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.SeleniumServer;
import org.springframework.beans.factory.annotation.Autowired;

import com.uniovi.services.UsersService;
import com.uniovi.tests.pageobjects.*;
import com.uniovi.tests.util.SeleniumUtils;

import org.junit.runners.MethodSorters;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests {

//	static String PathFirefox65 = "D:\\ProgramFiles\\Mozilla Firefox Portable\\FirefoxPortable.exe";
//	static String Geckdriver024 = "G:\\My Drive\\Universidad\\3º\\SDI\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
//	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\Firefox.exe";
//	static String Geckdriver024 = "C:\\Users\\UO266754\\Documents\\PL-SDI-Sesion5-material\\geckodriver024win64.exe";
	
	//Ordenador Luis
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\Propietario\\Desktop\\Clase\\SDI\\P5Material\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
	

	// Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicaciónn
	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	}
	
	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}
	
	//[Prueba1]Registro de Usuario con datos válidos
	@Test
	public void Prueba01() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "jony@gmail.com", "Jonathan", "Perez", "123456", "123456");
		
		PO_HomeView.checkElement(driver, "text", "jony@gmail.com");
		
	}	
	
	//[Prueba2]Registro de Usuario con datos inválidos (email vacío, nombre vacío, apellidos vacíos).
	@Test
	public void Prueba02() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "", "Luis", "Perez", "123456", "123456");
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
		
		PO_RegisterView.fillForm(driver, "luis1@gmail.com", "", "Perez", "123456", "123456");
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
		
		PO_RegisterView.fillForm(driver, "luis1@gmail.com", "Luis", "", "123456", "123456");
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
		
	}
	
	//[Prueba3]Registro de Usuario con datos inválidos(repetición de contraseña inválida)
		@Test
		public void Prueba03() {
			// Vamos al formulario de registro
			PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
			// Rellenamos el formulario.
			PO_RegisterView.fillForm(driver, "luis1@gmail.com", "Luis", "Perez", "778727", "123456");
			PO_RegisterView.checkKey(driver, "Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());
				
		}
	
	//[Prueba4]Registro de Usuario con datos inválidos (email existente).
		@Test
		public void Prueba04() {
			// Vamos al formulario de registro
			PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
			// Rellenamos el formulario.
			PO_RegisterView.fillForm(driver, "luis@gmail.com", "Luis", "Fuertes", "123456", "123456");
			PO_RegisterView.checkKey(driver, "Error.signup.username.duplicate", PO_Properties.getSPANISH());
				
		}
		
	//[Prueba5]Inicio de sesión con datos válidos (administrador
	@Test
	public void Prueba05() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		// Comprobamos el login correcto.
		PO_NavView.checkElement(driver, "text", "admin@email.com");
		
	}
	//Prueba6]Inicio de sesión con datos válidos (usuario estándar)
		@Test
		public void Prueba06() {
		
			// Vamos al formulario de registro
			PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
			// Rellenamos el formulario.
			PO_LoginView.fillForm(driver, "pedro33", "123456");
			// Comprobamos el login correcto.
			PO_NavView.checkElement(driver, "text", "pedro33");
			
		}
		
	//[Prueba7]Inicio de sesión con datos inválidos (usuario estándar,campo email y contraseña vacíos).
//		@Test
//		public void Prueba07() {
//			// Vamos al formulario de registro
//			PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
//			// Rellenamos el formulario, correo vacio.
//			PO_LoginView.fillForm(driver, "", "123456");
//			PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
//		
//		
//		// Rellenamos el formulario, password vacio.
//			PO_LoginView.fillForm(driver, "pedro33", "");
//			PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
//			
//			;
//			
//		}
		
		//[Prueba8]Inicio  de  sesión  con  datos  válidos  (usuario  estándar, email  existente,  pero  contraseña incorrecta).
//		@Test
//		public void Prueba08() {
//			// Vamos al formulario de registro
//			PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
//			// Rellenamos el formulario, contraseña incorrecta.
//			PO_LoginView.fillForm(driver, "pedro33", "100000");
//			PO_RegisterView.checkKey(driver, "Error.login.incorrect.password", PO_Properties.getSPANISH());
//			
//			;
//			
//		}
		
		//PRUEBA 7 y 8
		@Test
		public void Prueba07y08() {
			// Vamos al formulario de registro
			PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
			// Rellenamos el formulario, correo vacio.
			PO_LoginView.fillForm(driver, "", "123456");
			PO_HomeView.checkElement(driver, "text", "Your username and password is invalid.");
		
		
		// Rellenamos el formulario, password vacio.
			PO_LoginView.fillForm(driver, "pedro33", "");
			PO_HomeView.checkElement(driver, "text", "Your username and password is invalid.");
			
			//Rellenamos todo, pero de forma incorrecta
			PO_LoginView.fillForm(driver, "pedro33", "100000");
			PO_HomeView.checkElement(driver, "text", "Your username and password is invalid.");
		}
		
		
		
		
		
		//[Prueba9]Hacer click en la opción de salir de sesión y comprobar que se redirige a la página de inicio de sesión(Login).
		@Test
		public void Prueba09() {
			//Se logea
			PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
			PO_LoginView.fillForm(driver, "pedro33", "123456");
			//Sale de sesión
			PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
			//Comprueba que esta en la URL correcta
			assertEquals(true, PO_NavView.isThisURL(driver, "http://localhost:8090/login?logout"));
		}
		
		//[Prueba10]Comprobar que el botón cerrar sesión no está visible si el usuario no está autenticado.
		@Test
		public void Prueba10() {
			
			//Compruebo que no esta 
			SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Desconectar", PO_View.getTimeout());
		}
		
		//[Prueba11]Mostrar  el  listado  de usuarios  y  comprobar  que  se  muestran  todos  los  que  existen  en  el sistema
		@Test
		public void Prueba11() {
			//Se logea
			PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
			PO_LoginView.fillForm(driver, "luis@gmail.com", "123456");
			
			//Redirige a usuarios
			List<WebElement> elementos = PO_View.checkElement(driver, "free","//li[contains(@id, 'users-menu')]/a");
			elementos.get(0).click();
			elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
			elementos.get(0).click();
			
			
			//Comprueba
			
			elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
			
			elementos = PO_View.checkElement(driver, "text", "pedro33");
			elementos = PO_View.checkElement(driver, "text", "lucasñez");
			elementos = PO_View.checkElement(driver, "text", "mariiia");
			elementos = PO_View.checkElement(driver, "text", "martalmonde");			
			elementos = PO_View.checkElement(driver, "text", "jony@gmail.com");
			
			
		
			//elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
			//elementos.get(2).click();
			
			
		}
		
		//Prueba12]Hacer  una  búsqueda  con  el  campo  vacío  y comprobar  que  se  muestra  la  página  que correspondecon el listado usuariosexistentes en el sistema
		@Test
		public void Prueba12() {
			//Se logea
			PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
			PO_LoginView.fillForm(driver, "luis@gmail.com", "123456");
			
			//Redirige a usuarios
			List<WebElement> elementos = PO_View.checkElement(driver, "free","//li[contains(@id, 'users-menu')]/a");
			elementos.get(0).click();
			elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
			elementos.get(0).click();
			
			//Comprueba
			PO_ListUsers.fillForm(driver, "");
			elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
			
			elementos = PO_View.checkElement(driver, "text", "pedro33");
			elementos = PO_View.checkElement(driver, "text", "lucasñez");
			elementos = PO_View.checkElement(driver, "text", "mariiia");
			elementos = PO_View.checkElement(driver, "text", "martalmonde");			
			elementos = PO_View.checkElement(driver, "text", "jony@gmail.com");
		}
		
		//[Prueba13]Hacer  una  búsqueda escribiendo  en  el  campo  un  texto  que  no  exista  y  comprobar  que  se muestrala página que corresponde, con la listade usuariosvacía
		@Test
		public void Prueba13() {
			//Se logea
			PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
			PO_LoginView.fillForm(driver, "luis@gmail.com", "123456");
			
			//Redirige a usuarios
			List<WebElement> elementos = PO_View.checkElement(driver, "free","//li[contains(@id, 'users-menu')]/a");
			elementos.get(0).click();
			elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
			elementos.get(0).click();
			
			PO_ListUsers.fillForm(driver, "wq");
			
			elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
			
			SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "wq", PO_View.getTimeout());
		
			
		}
		
		//[Prueba14]Hacer  una  búsqueda  conun texto específico y  comprobar  que  se muestrala  página  que corresponde,
		//con la listade usuarios en los que el texto especificados sea parte de su nombre,apellidos o de su email
				@Test
				public void Prueba14() {
					//Se logea
					PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
					PO_LoginView.fillForm(driver, "luis@gmail.com", "123456");
					
					//Redirige a usuarios
					List<WebElement> elementos = PO_View.checkElement(driver, "free","//li[contains(@id, 'users-menu')]/a");
					elementos.get(0).click();
					elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
					elementos.get(0).click();
					
					PO_ListUsers.fillForm(driver, "íaz");
					
					elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
					
					elementos = PO_View.checkElement(driver, "text", "pedro33");
					
				
					
				}
		//[Prueba15]Desde el listado de usuarios de la aplicación,enviar una invitación de amistad a un usuario. Comprobar que la solicitud deamistad apareceen el listado de invitaciones (punto siguiente).
				@Test
				public void Prueba15() {
					//Se logea
					PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
					PO_LoginView.fillForm(driver, "luis@gmail.com", "123456");
					
					//Redirige a usuarios
					List<WebElement> elementos = PO_View.checkElement(driver, "free","//li[contains(@id, 'users-menu')]/a");
					elementos.get(0).click();
					elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
					elementos.get(0).click();
					
					elementos = PO_View.checkElement(driver, "free", "//td[contains(text(), 'pedro33')]/following-sibling::*/a[contains(@href, 'friend/add')]");
					elementos.get(0).click();
					PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
					
					//Se logea
					PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
					PO_LoginView.fillForm(driver, "pedro33", "123456");
					
					//Redirige a amigos
					elementos = PO_View.checkElement(driver, "free","//li[contains(@id, 'friends-menu')]/a");
					elementos.get(0).click();
					elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'request/list')]");
					elementos.get(0).click();
					
					PO_View.checkElement(driver, "text", "luis@gmail.com");
				}
				
			//Prueba16]Desde el listado de usuarios de la aplicación,enviar una invitación de amistad a un usuario al que ya le habíamos enviadola invitación previamente. No debería dejarnos enviar la invitación, se podría ocultar el botón de enviar invitación o notificar que ya había sido enviada previamente.
				@Test
				public void Prueba16() {
					//Se logea
					PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
					PO_LoginView.fillForm(driver, "luis@gmail.com", "123456");
					
					//Redirige a usuarios
					List<WebElement> elementos = PO_View.checkElement(driver, "free","//li[contains(@id, 'users-menu')]/a");
					elementos.get(0).click();
					elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
					elementos.get(0).click();
					
					elementos = PO_View.checkElement(driver, "free", "//td[contains(text(), 'pedro33')]/following-sibling::*/a[contains(@href, 'friend/add')]");
					elementos.get(0).click();
					
					
					//Oservamos mensaje de que no se puede
					PO_View.checkElement(driver, "text", "Ya es amigo o ya existe una solicitud pendiente");
				}
				
				//Prueba17]Mostrar  el  listado  de  invitaciones  de  amistad  recibidas.  Comprobar con  un  listadoque contenga variasinvitacionesrecibidas.
				@Test
				public void Prueba17() {
					//Se logea
					PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
					PO_LoginView.fillForm(driver, "pedro33", "123456");
					
					//Redirige a amigos
					List<WebElement> elementos = PO_View.checkElement(driver, "free","//li[contains(@id, 'friends-menu')]/a");
					elementos.get(0).click();
					elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'request/list')]");
					elementos.get(0).click();
					
					PO_View.checkElement(driver, "text", "martalmonde");
					PO_View.checkElement(driver, "text", "luis@gmail.com");
					
				}
				
				
				//Prueba18]Sobre  el  listado  de invitacionesrecibidas. Hacer click en  el botón/enlacedeuna  de ellas  y comprobar que dicha solicitud desaparecedel listado de invitaciones
				@Test
				public void Prueba18() {
					//Se logea
					PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
					PO_LoginView.fillForm(driver, "pedro33", "123456");
					
					//Redirige a amigos
					List<WebElement> elementos = PO_View.checkElement(driver, "free","//li[contains(@id, 'friends-menu')]/a");
					elementos.get(0).click();
					elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'request/list')]");
					elementos.get(0).click();
					
					
					elementos = PO_View.checkElement(driver, "free", "//td[contains(text(), 'luis@gmail.com')]/following-sibling::*/a[contains(@href, 'request/accept')]");
					elementos.get(0).click();
					
					//Volvemos a las peticiones a revisar
					elementos = PO_View.checkElement(driver, "free","//li[contains(@id, 'friends-menu')]/a");
					elementos.get(0).click();
					elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'request/list')]");
					elementos.get(0).click();
					
					SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "luis@gmail.com", PO_View.getTimeout());
				}
				
				//[Prueba19]Mostrar  el  listadode  amigos  de  unusuario.  Comprobar  que  el  listado  contiene  los  amigosque deben ser.
				
				@Test
				public void Prueba19() {
					//Se logea
					PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
					PO_LoginView.fillForm(driver, "pedro33", "123456");
					
					//Redirige a amigos
					List<WebElement> elementos = PO_View.checkElement(driver, "free","//li[contains(@id, 'friends-menu')]/a");
					elementos.get(0).click();
					elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'friend/list')]");
					elementos.get(0).click();
					
					
					elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
					
					elementos = PO_View.checkElement(driver, "text", "lucasñez");
					elementos = PO_View.checkElement(driver, "text", "mariiia");
					elementos = PO_View.checkElement(driver, "text", "luis@gmail.com");
					
				}
				
				//[Prueba20]Visualizar  al  menos cuatropáginas  en  Español/Inglés/Español(comprobando que  algunas de las etiquetas cambian al idioma correspondiente). Ejemplo,Página principal/Opciones Principales de Usuario/Listado de Usuarios.
				@Test
				public void Prueba20() {
					//Se logea
					PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
					//cambia idioma
					PO_HomeView.checkChangeIdiomaEsEnEs(driver, "Identifícate", "Log in");
					PO_LoginView.fillForm(driver, "luis@gmail.com", "123456");
					
					//cambia idioma
					PO_HomeView.checkChangeIdiomaEsEnEs(driver, "autenticado", "authenticated");
					
					
					List<WebElement> elementos = PO_View.checkElement(driver, "free","//li[contains(@id, 'users-menu')]/a");
					elementos.get(0).click();
					elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
					elementos.get(0).click();
					
					//cambia idioma
					PO_HomeView.checkChangeIdiomaEsEnEs(driver, "Usuarios", "Users");
					
					
					elementos = PO_View.checkElement(driver, "free","//li[contains(@id, 'friends-menu')]/a");
					elementos.get(0).click();
					elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'friend/list')]");
					elementos.get(0).click();
					
					//cambia idioma
					PO_HomeView.checkChangeIdiomaEsEnEs(driver, "Amigos", "Friends");
					
					
					
				}
				
				//[Prueba21]Intentar acceder sin estar autenticado a la opción de listado de usuarios. Se deberá volver al formulario de login.
				@Test
				public void Prueba21() {
					driver.navigate().to(URL+"/user/list");
					assertEquals(true, PO_NavView.isThisURL(driver, "http://localhost:8090/login"));

				}
				
				//[Prueba22]Intentar acceder sin estar autenticado a la opción de listado de amigos de un usuario estándar. Se deberá volver al formulario de login.
				@Test
				public void Prueba22() {
					driver.navigate().to(URL+"/friend/list");
					assertEquals(true, PO_NavView.isThisURL(driver, "http://localhost:8090/login"));

				}
				
				//[Prueba23]Estando  autenticado  como  usuario  estándar  intentar  acceder  a una  opción disponible  solo para usuarios administradores(Se puede añadir una opción cualquieraen el menú).  Se deberá indicar un mensaje de acción prohibida
				@Test
				public void Prueba23() {
					//Se logea
					PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
					PO_LoginView.fillForm(driver, "pedro33", "123456");
					
					driver.navigate().to(URL+"/user/add");
					//PO_View.checkElement(driver, "text", "HTTP Status 403 – Forbidden");
					SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Agregar usuario", PO_View.getTimeout());
					
				}
				
				//[Prueba24]Ir  alformulario crearpublicaciones, rellenarla  con  datos  válidos  y  pulsar  el  botón  Submit. Comprobar que lapublicaciónsaleenel listado de publicacionesde dicho usuario.
				@Test
				public void Prueba24() {
					//Se logea
					PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
					PO_LoginView.fillForm(driver, "pedro33", "123456");
					
					//Redirige a amigos
					List<WebElement> elementos = PO_View.checkElement(driver, "free","//li[contains(@id, 'publicacion-menu')]/a");
					elementos.get(0).click();
					elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'publicacion/add')]");
					elementos.get(0).click();
					
					PO_PublicacionView.fillForm(driver, "Titulazo", "Textazo");
					
					elementos = PO_View.checkElement(driver, "text", "Titulazo");
					elementos = PO_View.checkElement(driver, "text", "Textazo");
					
					
					
				}
		//[Prueba25]Ir al formulario de crear publicaciones, rellenarla con datos inválidos (campo título vacío) y pulsar el botón Submit. Comprobar que se muestra el mensaje de campo obligatorio.
				@Test
				public void Prueba25() {
					//Se logea
					PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
					PO_LoginView.fillForm(driver, "pedro33", "123456");
					
					//Redirige a amigos
					List<WebElement> elementos = PO_View.checkElement(driver, "free","//li[contains(@id, 'publicacion-menu')]/a");
					elementos.get(0).click();
					elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'publicacion/add')]");
					elementos.get(0).click();
					
					PO_PublicacionView.fillForm(driver, "", "Textazo");
					
					elementos = PO_View.checkElement(driver, "text", "Este campo no puede ser vacío");
					
				}
				//[Prueba26]Mostrar el listado de publicacionesde unusuario y comprobar que se muestran todas las que existen para dichousuario. 
				@Test
				public void Prueba26() {
					//Se logea
					PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
					PO_LoginView.fillForm(driver, "pedro33", "123456");
					
					//Redirige a amigos
					List<WebElement> elementos = PO_View.checkElement(driver, "free","//li[contains(@id, 'publicacion-menu')]/a");
					elementos.get(0).click();
					elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'publicacion/list')]");
					elementos.get(0).click();
					
					elementos = PO_View.checkElement(driver, "text", "Titulazo");
					elementos = PO_View.checkElement(driver, "text", "Textazo");
					
				}
				
				//[Prueba31]Mostrar  el  listado  de  usuarios  y  comprobar  que  se  muestran  todos  los  que  existen  en  el sistema.
				@Test
				public void Prueba31() {
					//Se logea
					PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
					PO_LoginView.fillForm(driver, "admin@email.com", "admin");
					
					//Redirige a usuarios
					List<WebElement> elementos = PO_View.checkElement(driver, "free","//li[contains(@id, 'users-menu')]/a");
					elementos.get(0).click();
					elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
					elementos.get(0).click();
					
					
					//Comprueba
					
					elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
					
					elementos = PO_View.checkElement(driver, "text", "pedro33");
					elementos = PO_View.checkElement(driver, "text", "lucasñez");
					elementos = PO_View.checkElement(driver, "text", "mariiia");
					elementos = PO_View.checkElement(driver, "text", "martalmonde");			
					elementos = PO_View.checkElement(driver, "text", "luis@gmail.com");
				
					elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
					elementos.get(2).click();
					
					elementos = PO_View.checkElement(driver, "text", "admin@email.com");			
					elementos = PO_View.checkElement(driver, "text", "jony@gmail.com");
					
				}
				
				//Reiniciar Tests
				@Test
				public void ReinicioTests() {
					//Se logea
					PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
					PO_LoginView.fillForm(driver, "admin@email.com", "admin");
					
					//Redirige a usuarios
					
					List<WebElement> elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'test/reiniciar')]");
					elementos.get(0).click();
					
				}
}