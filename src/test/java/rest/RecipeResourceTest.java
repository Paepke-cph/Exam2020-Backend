package rest;

import dtos.RecipeDTO;
import entity.Ingredient;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RecipeResourceTest extends BaseResourceTest {
    @Test
    public void testGetAllRecipes_with_user_role()  {
        String token = getUserToken();
        given()
                .header("x-access-token", token)
                .contentType(ContentType.JSON)
                .get("recipe")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", hasItems(r1.getId().intValue(), r1.getId().intValue()));
    }

    @Test
    public void testGetAllRecipes_with_admin_role() {
        String token = getAdminToken();
        given()
                .header("x-access-token", token)
                .contentType(ContentType.JSON)
                .get("recipe")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", hasItems(r1.getId().intValue(), r1.getId().intValue()));
    }

    @Test
    public void testGetAllRecipes_with_user_and_admin_role() {
        String token = getUserAdminToken();
        given()
                .header("x-access-token", token)
                .contentType(ContentType.JSON)
                .get("recipe")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", hasItems(r1.getId().intValue(), r1.getId().intValue()));
    }

    @Test
    public void testGetAllRecipes_without_sign_in() {
        given()
                .contentType(ContentType.JSON)
                .get("recipe")
                .then()
                .assertThat()
                .statusCode(403)
                .body("message", notNullValue());
    }

    @Test
    public void testGetRecipeByIngredients() {
        List<Ingredient> ingredients = Utils.toList(in3,in4);
        String token = getUserToken();
        given()
                .header("x-access-token",token)
                .contentType(ContentType.JSON)
                .body(gson.toJson(ingredients))
                .post("recipe")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", hasItems(r1.getId().intValue()));
    }

    @Test
    public void testGetRecipeByIngredients_with_not_in_use_ingredient() {
        List<Ingredient> ingredients = Utils.toList(notInUseIngredient);
        String token = getUserToken();
        given()
                .header("x-access-token",token)
                .contentType(ContentType.JSON)
                .body(gson.toJson(ingredients))
                .post("recipe")
                .then()
                .assertThat()
                .statusCode(200)
                .body("", hasSize(0));
    }

    @Test
    public void testCreateRecipe() {
        RecipeDTO recipe = new RecipeDTO(Utils.toList(in1,in3),20,"Step 1, Step 2");
        recipe.setId(0L);
        String token = getAdminToken();
        given()
                .header("x-access-token",token)
                .contentType(ContentType.JSON)
                .body(gson.toJson(recipe))
                .post("recipe/create")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().jsonPath().get("id").equals(recipe.getId());
    }

    @Test
    public void testUpdateRecipe() {
        int newPrepTime = 999;
        r1.setPreparationTime(newPrepTime);
        String token = getAdminToken();
        given()
                .header("x-access-token",token)
                .contentType(ContentType.JSON)
                .body(gson.toJson(r1))
                .post("recipe/update")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().jsonPath().get("preparationTime").equals(newPrepTime);
    }

    @Test
    public void testDeleteRecipe() {
        String token = getAdminToken();
        given()
                .header("x-access-token",token)
                .contentType(ContentType.JSON)
                .body(gson.toJson(r1))
                .post("recipe/delete")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().jsonPath().get("id").equals("");
    }
}