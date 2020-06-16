package rest;

import facades.BaseFacadeTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

abstract public class BaseResourceTest extends BaseFacadeTest {
    protected static final int SERVER_PORT = 7777;
    protected static final String SERVER_URL = "http://localhost/api";

    protected static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    protected static HttpServer httpServer;
    protected static EntityManagerFactory emf;

    protected static Properties testProps = new Properties();
    protected static Map<String,String> userInfo = new HashMap<>();

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() throws IOException {
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.CREATE);
        httpServer = startServer();
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    protected String getUserToken() {
        return given().contentType(ContentType.JSON).body("{username:\""+user.getUserName()+"\",password:\"this is a good password\"}")
                .when().post("login").jsonPath().get("token");
    }
    protected String getAdminToken() {
        return given().contentType(ContentType.JSON).body("{username:\""+admin.getUserName()+"\",password:\"this is a very good password\"}")
                .when().post("login").jsonPath().get("token");
    }
    protected String getUserAdminToken() {
        return given().contentType(ContentType.JSON).body("{username:\""+both.getUserName()+"\",password:\"could this be better\"}")
                .when().post("login").jsonPath().get("token");
    }
}
