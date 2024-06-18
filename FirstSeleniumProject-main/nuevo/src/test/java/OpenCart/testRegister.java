package OpenCart;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testRegister {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    public static void createReport() {
        System.out.println("<<< COMIENZAN LOS TEST DE REGISTRO >>>");
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        driver = new ChromeDriver(options);        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        RegisterPage registerPage = new RegisterPage(driver, wait);

        registerPage.setup();
        registerPage.getUrl("https://parabank.parasoft.com/parabank/index.htm");


    }


    @Test
    @Tag("REGISTRO")
    @Tag("EXITOSO")
    public void probandoBotones() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.clickRegister();


        registerPage.escribirNombre("Brahian");
        registerPage.escribirApellido("Puschiasis");
        registerPage.escribirDireccion("Calle inventada 3121");
        registerPage.escribirCity("Montevideo");
        registerPage.escribirState("Casado");
        registerPage.escribirZip("111212");
        registerPage.escribirTelephone("23141153");
        registerPage.escribirSSN("12312");

        registerPage.escribirUserName("ProbandoCositas");
        registerPage.escribirContraseña("123456");
        registerPage.repetirContraseña("123456");

        registerPage.clickRegistrarse();

        String resultado = registerPage.validaMailObligatorio();

        // Compara el resultado con el texto esperado
        assertEquals("Your account was created successfully. You are now logged in.", resultado);



    }


    @AfterEach
    public void cerrar() {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.close();
    }

    @AfterAll
    public static void saveReport() {
        System.out.println("<<< FINALIZAN LOS TEST DE REGISTRO >>>");
    }
}
