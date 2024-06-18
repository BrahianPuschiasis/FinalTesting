package OpenCart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FrontEndPage extends BasePage{


    public FrontEndPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }


    /** Login */
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

    /** Registro */
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

    public void clickRegister() throws InterruptedException {
        this.click(btnRegister);
        Thread.sleep(1000);
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



    /** Nueva Cuenta */
    private By newAccount = By.xpath("//a[normalize-space()='Open New Account']");


    private By openNewAccount = By.cssSelector("input[value='Open New Account']");
    private By accountConfirmed = By.xpath("//p[normalize-space()='Congratulations, your account is now open.']");

    public void clickNewAccount() throws InterruptedException {
        this.click(newAccount);
    }
    public void accountType() throws InterruptedException {
        By accountTypeLocator = By.xpath("//select[@id='type']");
        int savingsOptionValue = 1;

        // Llamar al método de BasePage para seleccionar la opción "SAVINGS"
        selectOptionFromDropdown(accountTypeLocator, savingsOptionValue);

    }
    public void setAccountConfirmed() throws InterruptedException {
        this.click(openNewAccount);
        Thread.sleep(1000);

    }
    public String validaCuentaNueva() throws InterruptedException {
        System.out.println("Se valida mensaje de cuenta creada: " + this.getText(accountConfirmed));
        return this.getText(accountConfirmed);
    }




    /** Resumen de cuenta */
    private By resumenClick = By.xpath("//a[normalize-space()='Accounts Overview']");
    private By resumeConfirmed = By.xpath("//td[contains(text(),'*Balance includes deposits that may be subject to ')]");
    public void resumeClick() throws InterruptedException {
        this.click(resumenClick);
    }

    public String resumeConfirmed() throws InterruptedException {
        System.out.println("Se valida mensaje de resumen: " + this.getText(resumeConfirmed));
        return this.getText(resumeConfirmed);
    }



    /** Transferir fondos */
    private By btnTransfer = By.xpath("//a[normalize-space()='Transfer Funds']");
    private By transferText = By.xpath("//h1[normalize-space()='Transfer Funds']");
    private By amount = By.xpath("//input[@id='amount']");

    private By confirmTransfer = By.xpath("//input[@value='Transfer']");
    private By transferComplete = By.xpath("//h1[normalize-space()='Transfer Complete!']");


    public void clickBtnTransfer() throws InterruptedException {
        this.click(btnTransfer);
        Thread.sleep(1000);

    }

    public String transferText() throws InterruptedException {
        System.out.println("Se valida mensaje de advertencia del deposito: " + this.getText(transferText));
        return this.getText(transferText);
    }
    public void escribirMonto(String monto) throws InterruptedException {
        this.sendText(monto, amount);
    }

    public void clickConfirmTransfer() throws InterruptedException {
        this.click(confirmTransfer);
        Thread.sleep(1000);

    }

    public String transferComplete() throws InterruptedException {
        System.out.println("Se valida mensaje de transferencia completada: " + this.getText(transferComplete));
        return this.getText(transferComplete);
    }


    public void cmbxTransfer() throws InterruptedException {
        By accountTypeLocator = By.id("toAccountId");
        int savingsOptionValue = 1;

        // Llamar al método de BasePage para seleccionar la opción "SAVINGS"
        selectOptionFromDropdown(accountTypeLocator, savingsOptionValue);
        Thread.sleep(1000);


    }



    /** Actividad por mes */

    private By monthActivity = By.xpath("/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/a[1]");
    private By accountDetails = By.xpath("//h1[normalize-space()='Account Details']");
    private By accountGo = By.xpath("//input[@value='Go']");

    public void monthActivity() throws InterruptedException {
        this.click(monthActivity);
        Thread.sleep(1000);

    }

    public String accountDetails() throws InterruptedException {
        System.out.println("Se valida mensaje de detalles de cuenta" + this.getText(accountDetails));
        return this.getText(accountDetails);
    }

    public void activityPeriod() throws InterruptedException {
        By accountTypeLocator = By.id("month");
        int savingsOptionValue = 0;

        selectOptionFromDropdown(accountTypeLocator, savingsOptionValue);
        Thread.sleep(1000);


    }

    public void activityType() throws InterruptedException {
        By accountTypeLocator = By.id("transactionType");
        int savingsOptionValue = 0;

        selectOptionFromDropdown(accountTypeLocator, savingsOptionValue);
        Thread.sleep(1000);


    }

    public void accountGo() throws InterruptedException {
        this.click(accountGo);
        Thread.sleep(1000);

    }








}
