package OpenCart;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FrontEnd {
    private WebDriver driver;
    private WebDriverWait wait;
    private String username = "1uiyhiuhiuhi";
    private String password = "123456";


    @BeforeAll
    public static void createReport() {
        System.out.println("<<< COMIENZAN LOS TEST DE REGISTRO >>>");
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        driver = new ChromeDriver(options);        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        FrontEndPage frontEndPage = new FrontEndPage(driver, wait);

        frontEndPage.setup();
        frontEndPage.getUrl("https://parabank.parasoft.com/parabank/index.htm");


    }


    public void Login() throws InterruptedException {
        FrontEndPage frontEndPage = new FrontEndPage(driver, wait);

        frontEndPage.LoginUsername(username);
        frontEndPage.LoginPass(password);
        frontEndPage.clickbtnLogin();

    }



    @Test
    @Order(1)
    @Tag("REGISTRO")
    @Tag("EXITOSO")
    public void register() throws InterruptedException {
        FrontEndPage frontEndPage = new FrontEndPage(driver, wait);
        frontEndPage.clickRegister();


        frontEndPage.escribirNombre("Brahian");
        frontEndPage.escribirApellido("Puschiasis");
        frontEndPage.escribirDireccion("Calle inventada 3121");
        frontEndPage.escribirCity("Montevideo");
        frontEndPage.escribirState("Casado");
        frontEndPage.escribirZip("111212");
        frontEndPage.escribirTelephone("23141153");
        frontEndPage.escribirSSN("12312");

        frontEndPage.escribirUserName(username);
        frontEndPage.escribirContraseña(password);
        frontEndPage.repetirContraseña(password);

        frontEndPage.clickRegistrarse();

        String resultado = frontEndPage.validaMailObligatorio();

        // Compara el resultado con el texto esperado
        assertEquals("Your account was created successfully. You are now logged in.", resultado);



    }


    @Test
    @Order(2)
    @Tag("ABRIR CUENTA")
    @Tag("EXITOSO")
    public void openAccount() throws InterruptedException {
        FrontEndPage frontEndPage = new FrontEndPage(driver, wait);

        Login();

        frontEndPage.clickNewAccount();
        frontEndPage.accountType();
        frontEndPage.setAccountConfirmed();


        String resultado = frontEndPage.validaCuentaNueva();

        // Compara el resultado con el texto esperado
        assertEquals("Congratulations, your account is now open.", resultado);
    }




    @AfterEach
    public void cerrar() {
        FrontEndPage frontEndPage = new FrontEndPage(driver, wait);
        frontEndPage.close();
    }

    @AfterAll
    public static void saveReport() {
        System.out.println("<<< FINALIZAN LOS TEST DE REGISTRO >>>");
    }
}
