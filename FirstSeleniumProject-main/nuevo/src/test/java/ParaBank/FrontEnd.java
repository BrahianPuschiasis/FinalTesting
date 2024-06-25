package ParaBank;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportes.ReportFactory;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FrontEnd {

    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/FrontEnd-Test.html");
    static ExtentReports extent;

    private WebDriver driver;
    private WebDriverWait wait;
    private String username = "puschiasisPrueboJaja";
    private String password = "123456";

    @BeforeAll
    public static void createReport() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
        System.out.println("<<< COMIENZAN LOS TEST DE FRONTEND >>>");
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
//    @Tag("EXITOSO")
    public void register() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro");

        test.log(Status.INFO, "Comienza el Test");
        FrontEndPage frontEndPage = new FrontEndPage(driver, wait);
        frontEndPage.clickRegister();
        test.log(Status.INFO, "Click en Registro");


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
        test.log(Status.INFO, "Se ingresaron todos los datos del formulario");

        frontEndPage.clickRegistrarse();
        test.log(Status.INFO, "Click en terminar registro");

        String resultado = frontEndPage.validaMailObligatorio();

        // Compara el resultado con el texto esperado
        assertEquals("Your account was created successfully. You are now logged in.", resultado);
        test.log(Status.PASS, "Se registro exitosamente");



    }


    @Test
    @Order(2)
    @Tag("ABRIR_CUENTA")
    @Tag("EXITOSO")
    public void openAccount() throws InterruptedException {
        FrontEndPage frontEndPage = new FrontEndPage(driver, wait);
        ExtentTest test = extent.createTest("Abrir Cuenta");

        test.log(Status.INFO, "Comienza el Test");
        Login();
        test.log(Status.INFO, "Logueo exitoso");

        frontEndPage.clickNewAccount();
        frontEndPage.accountType();
        test.log(Status.INFO, "Nueva cuenta y tipo de cuenta");

        frontEndPage.setAccountConfirmed();

        frontEndPage.setAccountConfirmed();
        test.log(Status.INFO, "Click boton confirmar");

        String resultado = frontEndPage.validaCuentaNueva();

        // Compara el resultado con el texto esperado
        assertEquals("Congratulations, your account is now open.", resultado);
        test.log(Status.PASS, "Se abrio la cuenta exitosamente");

    }

    @Test
    @Order(3)
    @Tag("RESUMEN_CUENTA")
    @Tag("EXITOSO")
    public void resumeAccount() throws InterruptedException {
        FrontEndPage frontEndPage = new FrontEndPage(driver, wait);
        ExtentTest test = extent.createTest("Resumen Cuenta");

        test.log(Status.INFO, "Comienza el Test");
        Login();
        test.log(Status.INFO, "Logueo exitoso");

        frontEndPage.resumeClick();
        test.log(Status.INFO, "Resumen click");



        String resultado = frontEndPage.resumeConfirmed();

        // Compara el resultado con el texto esperado
        assertEquals("*Balance includes deposits that may be subject to holds", resultado);
        test.log(Status.PASS, "Resumen cuenta exitoso");

    }

    @Test
    @Order(4)
    @Tag("TRANSFERIR_FONDOS")
    @Tag("EXITOSO")
    public void transferFunds() throws InterruptedException {
        FrontEndPage frontEndPage = new FrontEndPage(driver, wait);
        ExtentTest test = extent.createTest("Transferir Fondos");

        test.log(Status.INFO, "Comienza el Test");
        Login();
        test.log(Status.INFO, "Logueo exitoso");

        frontEndPage.clickBtnTransfer();
        test.log(Status.INFO, "Click boton transferir");

        String resultado = frontEndPage.transferText();

        // Compara el resultado con el texto esperado
        assertEquals("Transfer Funds", resultado);

        frontEndPage.escribirMonto("5000");
        test.log(Status.INFO, "Cantidad de fondos escrita");

        frontEndPage.cmbxTransfer();
        test.log(Status.INFO, "Combo box seleccionado");

        frontEndPage.clickConfirmTransfer();
        test.log(Status.INFO, "Click en el boton transferir");



        String resultado2 = frontEndPage.transferComplete();

        // Compara el resultado con el texto esperado
        assertEquals("Transfer Complete!", resultado2);
        test.log(Status.PASS, "Transferir fondos exitoso");




    }


    @Test
    @Order(5)
    @Tag("ACCOUNT_ACTIVITY_PER_MONTH")
    @Tag("EXITOSO")
    public void accountActivityPerMonth() throws InterruptedException {
        FrontEndPage frontEndPage = new FrontEndPage(driver, wait);
        ExtentTest test = extent.createTest("Actividad de cuenta por mes");

        test.log(Status.INFO, "Comienza el Test");
        Login();
        test.log(Status.INFO, "Logueo exitoso");

        frontEndPage.resumeClick();
        test.log(Status.INFO, "Resumen click");



        String resultado = frontEndPage.resumeConfirmed();

        // Compara el resultado con el texto esperado
        assertEquals("*Balance includes deposits that may be subject to holds", resultado);

        frontEndPage.monthActivity();
        test.log(Status.INFO, "Actividad por mes click");

        String resultado2 = frontEndPage.accountDetails();

        // Compara el resultado con el texto esperado
        assertEquals("Account Details", resultado2);

        frontEndPage.activityPeriod();
        frontEndPage.activityType();
        test.log(Status.INFO, "Periodo y tipo de cuenta seleccionados");
        test.log(Status.INFO, "Click boton go");

        frontEndPage.accountGo();
        test.log(Status.PASS, "Actividad de cuenta por mes exitoso");


    }



    @AfterEach
    public void cerrar() {
        FrontEndPage frontEndPage = new FrontEndPage(driver, wait);
        frontEndPage.close();
    }

    @AfterAll
    public static void saveReport() {
        extent.flush();
        System.out.println("<<< FINALIZAN LOS TEST DE FRONTEND >>>");
    }
}
