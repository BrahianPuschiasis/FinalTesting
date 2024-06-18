package OpenCart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FrontEndPage extends BasePage{


    //Login

    private By txtLoginUsername = By.xpath("//input[@name='username']");
    private By txtLoginPass = By.xpath("//input[@name='password']");
    private By btnLogin = By.xpath("//input[@value='Log In']");

    public void LoginUsername(String name) throws InterruptedException {
        this.sendText(name, txtLoginUsername);
    }

    public void LoginPass(String pass) throws InterruptedException {
        this.sendText(pass, txtLoginPass);
    }

    public void clickbtnLogin() throws InterruptedException {
        this.click(btnLogin);
    }

    //Registro
    private By btnRegister = By.xpath("//a[normalize-space()='Register']");


    private By nombre = By.id("customer.firstName");
    private By apellido = By.id("customer.lastName");

    private By adress = By.id("customer.address.street");


    private By city = By.id("customer.address.city");
    private By state = By.id("customer.address.state");

    private By zipCode = By.id("customer.address.zipCode");

    private By telephone = By.id("customer.phoneNumber");
    private By ssn = By.id("customer.ssn");

    private By userName = By.id("customer.username");
    private By password = By.id("customer.password");
    private By repassword = By.id("repeatedPassword");

    private By btnContinue = By.xpath("//input[@value='Register']");

    private By successMessage = By.xpath("//p[contains(text(),'Your account was created successfully. You are now')]");


    //Nueva Cuenta

    private By newAccount = By.xpath("//a[normalize-space()='Open New Account']");


    private By openNewAccount = By.xpath("//input[@value='Open New Account']");
    private By accountConfirmed = By.xpath("//p[normalize-space()='Congratulations, your account is now open.']");



    //Resumen Cuentas

    //Transferir Fondos

    //Actividad por mes


    //Nueva Cuenta
    public void clickNewAccount() throws InterruptedException {
        this.click(newAccount);
    }
    public void accountType() throws InterruptedException {
        By accountTypeLocator = By.xpath("//select[@id='type']");
        String savingsOptionValue = "1";

        // Llamar al método de BasePage para seleccionar la opción "SAVINGS"
        selectOptionFromDropdown(accountTypeLocator, savingsOptionValue);

    }
    public void setAccountConfirmed() throws InterruptedException {
        this.click(openNewAccount);
    }
    public String validaCuentaNueva() throws InterruptedException {
        System.out.println("Se valida mensaje de cuenta creada: " + this.getText(successMessage));
        return this.getText(accountConfirmed);
    }


    //////////////



    public FrontEndPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    public void clickRegister() throws InterruptedException {
        this.click(btnRegister);
    }

    public void escribirNombre(String name) throws InterruptedException {
        this.sendText(name, nombre);
    }


    public void escribirApellido(String lastname) throws InterruptedException {
        this.sendText(lastname, apellido);
    }

    public void escribirDireccion(String address) throws InterruptedException {
        this.sendText(address, adress);
    }


    public void escribirCity(String City) throws InterruptedException {
        this.sendText(City, city);
    }

    public void escribirState(String State) throws InterruptedException {
        this.sendText(State, state);
    }

    public void escribirZip(String zip) throws InterruptedException {
        this.sendText(zip, zipCode);
    }

    public void escribirTelephone(String phone) throws InterruptedException {
        this.sendText(phone, telephone);
    }


    public void escribirSSN(String Ssn) throws InterruptedException {
        this.sendText(Ssn, ssn);
    }
    public void escribirUserName(String UserName) throws InterruptedException {
        this.sendText(UserName, userName);
    }

        public void escribirContraseña(String pass) throws InterruptedException {
        this.sendText(pass, password);
    }

    public void repetirContraseña(String pass) throws InterruptedException {
        this.sendText(pass, repassword);
    }


    public void clickRegistrarse() throws InterruptedException {
        this.click(btnContinue);
        Thread.sleep(1000);
    }



    public String validaMailObligatorio() throws InterruptedException {
        System.out.println("Se valida mensaje de cuenta creada: " + this.getText(successMessage));
        return this.getText(successMessage);
    }



}
