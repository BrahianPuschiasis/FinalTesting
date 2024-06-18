package OpenCart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage{


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


    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }




    public void clickRegister() throws InterruptedException {
        this.click(btnRegister);
    }

    public void escribirNombre(String name) throws InterruptedException {
        this.sendText(name, nombre);
    }


    public void escribirApellido(String name) throws InterruptedException {
        this.sendText(name, apellido);
    }

    public void escribirDireccion(String name) throws InterruptedException {
        this.sendText(name, adress);
    }


    public void escribirCity(String name) throws InterruptedException {
        this.sendText(name, city);
    }

    public void escribirState(String name) throws InterruptedException {
        this.sendText(name, state);
    }

    public void escribirZip(String name) throws InterruptedException {
        this.sendText(name, zipCode);
    }

    public void escribirTelephone(String name) throws InterruptedException {
        this.sendText(name, telephone);
    }


    public void escribirSSN(String name) throws InterruptedException {
        this.sendText(name, ssn);
    }
    public void escribirUserName(String name) throws InterruptedException {
        this.sendText(name, userName);
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
