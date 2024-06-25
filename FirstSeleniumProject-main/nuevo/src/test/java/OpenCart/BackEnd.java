package OpenCart;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import reportes.ReportFactory;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BackEnd {

    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/BackEnd-Test.html");
    static ExtentReports extent;

    private static int customerId = 19316;
    private static int fromAccountId = 24111;
    private static int toAccountId = 24222;


    private static String username = "puschiasisPrueboJaja";
    private static String password = "123456";

    @BeforeAll
    public static void setup() {
        System.out.println("<<< COMIENZAN LOS TEST DE BACKEND >>>");
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
    }

//    @BeforeAll
//    public static void setUp() {
//        System.out.println("<<< COMIENZAN LOS TEST DE BACKEND >>>");
//
//
//        // Construir la URL de login con placeholders para el usuario y contraseña
//        String loginUrl = "https://parabank.parasoft.com/parabank/services/bank/login/{username}/{password}";
//
//        // Realizar la solicitud GET para el login
//        Response response = given()
//                .pathParam("username", username)
//                .pathParam("password", password)
//                .when()
//                .get(loginUrl);
//
//        // Verificar que la respuesta no sea nula
//        assertEquals(200, response.getStatusCode(), "Error al autenticar. Código de estado: " + response.getStatusCode());
//
//        // Extraer el authToken del XML de respuesta (asumiendo que el XML contiene el campo 'customer.id')
//        int customerId = response.xmlPath().getInt("customer.id");
//
//        // Utilizar customerId como authToken para las pruebas posteriores
//        authToken = customerId;
//
//        System.out.println("<<< FINALIZAN LOS TEST DE BACKEND >>>");
//    }





    @Test
    @Order(1)
    @Tag("GET")
    public void Registro() {
        System.out.println("Iniciando Primer Test Get");
        ExtentTest test = extent.createTest("GET - Registro");
        test.log(Status.INFO, "Comienza el Test");
        test.log(Status.INFO, "Iniciando test registro tipo GET");

        Response responseGet = RestAssured.get("https://parabank.parasoft.com/parabank/register.htm");
        System.out.println(responseGet.getBody().asString());
        System.out.println(responseGet.statusCode());
        System.out.println(responseGet.getHeader("Date"));
        System.out.println(responseGet.getTime());
        test.log(Status.INFO, "Intentando hacer GET a la url https://parabank.parasoft.com/parabank/register.htm");

        // Aserción para comprobar que el status code es 200
        assertEquals(200, responseGet.statusCode(), "El status code debería ser 200");

        System.out.println("Primer Test Get finalizado");
        test.log(Status.PASS, "GET - Registro finalizado");

    }


    @Test
    @Order(2)
    @Tag("POST")
    public void AbrirCuenta() {
        System.out.println("Iniciando Primer Test Post");
        ExtentTest test = extent.createTest("POST - Abrir Cuenta");
        test.log(Status.INFO, "Comienza el Test");
        test.log(Status.INFO, "Iniciando test Abrir Cuenta tipo POST");

        // Definir los datos para la solicitud
//        int customerId = authToken;
        int newAccountType = 1;

        // Construir el cuerpo de la solicitud
        JsonObject request = new JsonObject();
        request.addProperty("id", 15120); // Ejemplo de respuesta, no necesitas enviar este campo
        request.addProperty("customerId", customerId);
        request.addProperty("type", "SAVINGS");
        request.addProperty("balance", 0);

        // Imprimir el cuerpo de la solicitud para depuración
        System.out.println("Request Body: " + request.toString());

        // Enviar la solicitud con RestAssured
        Response response = given()
                .auth().basic(username, password)
                .contentType("application/json")
                .body(request.toString()) // Convertir JsonObject a String
                .post("https://parabank.parasoft.com/parabank/services_proxy/bank/createAccount?customerId=" + customerId + "&newAccountType=" + newAccountType + "&fromAccountId=" + fromAccountId);

        // Imprimir los detalles de la respuesta para depuración
        test.log(Status.INFO, "Intentando hacer POST a la url https://parabank.parasoft.com/parabank/services_proxy/bank/createAccount?customerId=");

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        // Validar el código de estado esperado
        response.then().statusCode(200);

        System.out.println("Primer Test Post finalizado");
        test.log(Status.PASS, "POST - Abrir Cuenta finalizado");

    }


//    @Test
//    @Order(3)
//    @Tag("GET")
//    public void Resumen() {
//        System.out.println("Iniciando Test de Obtener Estado de Cuenta");
//
//        // URL con el token incluido
//        String url = "https://parabank.parasoft.com/parabank/overview.htm?token=FE9A088C4B8AEEAA3247327C91D2B577";
//
//        // Construir la solicitud GET con la URL que contiene el token
//        given()
//                .when()
//                .get(url)
//                .then()
//                .statusCode(200)
//                .log().status()
//                .log().body();
//
//        System.out.println("Test de Obtener Estado de Cuenta finalizado");
//    }



    @Test
    @Order(4)
    @Tag("POST")
    public void DescargarFondos() {
        System.out.println("Iniciando Test de Descargar Fondos");
        ExtentTest test = extent.createTest("POST - Descargar Fondos");
        test.log(Status.INFO, "Comienza el Test");
        test.log(Status.INFO, "Iniciando test Descargar Fondos tipo POST");
        // Datos de autenticación básica


        // Parámetros de la transferencia
        int amount = 500;

        // Enviar la solicitud de transferencia con RestAssured
        Response response = given()
                .auth().basic(username, password)
                .post("https://parabank.parasoft.com/parabank/services_proxy/bank/transfer?fromAccountId=" + fromAccountId + "&toAccountId=" + toAccountId + "&amount=" + amount);


        test.log(Status.INFO, "Intentando hacer POST a la url https://parabank.parasoft.com/parabank/services_proxy/bank/transfer?fromAccountId=");

        // Imprimir los detalles de la respuesta para depuración
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        // Validar el código de estado esperado
        assertEquals(200, response.getStatusCode(), "Error: El código de estado no es 200");

        System.out.println("Test de Descargar Fondos finalizado");
        test.log(Status.PASS, "POST - Descargar Fondos finalizado");

    }

    @Test
    @Order(5)
    @Tag("GET")
    public void ActividadCuenta() {
        System.out.println("Iniciando Test de Actividad de Cuenta");
        ExtentTest test = extent.createTest("GET - Actividad Cuenta");
        test.log(Status.INFO, "Comienza el Test");
        test.log(Status.INFO, "Iniciando test registro tipo GET");
        // Datos de autenticación básica


        // ID de cuenta para la cual se desea obtener la actividad
//        int accountId = 24111;

        // Construir la URL con los parámetros requeridos
        String url = "https://parabank.parasoft.com/parabank/services_proxy/bank/accounts/" + fromAccountId + "/transactions/month/All/type/All";
        test.log(Status.INFO, "Intentando hacer GET a la url https://parabank.parasoft.com/parabank/services_proxy/bank/accounts/");

        // Enviar la solicitud GET con RestAssured
        Response response = given()
                .auth().basic(username, password)
                .get(url);

        // Imprimir los detalles de la respuesta para depuración
        System.out.println("Request URL: " + url);
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        // Validar el código de estado esperado
        assertEquals(200, response.getStatusCode(), "Error: El código de estado no es 200");

        System.out.println("Test de Actividad de Cuenta finalizado");
        test.log(Status.PASS, "GET - Actividad Cuenta finalizado");

    }


    @AfterAll
    public static void saveReport() {
        extent.flush();
        System.out.println("<<< FINALIZAN LOS TEST DE BACKEND >>>");
    }


}
