package rest;

import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;

public class IngredientResourceTest extends BaseResourceTest {
    @Test
    public void testGetAllIngredients() {
        String token = getUserToken();
        given()
                .header("x-access-token",token)
                .contentType(ContentType.JSON)
                .get("ingredient")
                .then()
                .assertThat()
                .statusCode(200)
                .body("amount", hasSize(5));
    }
}