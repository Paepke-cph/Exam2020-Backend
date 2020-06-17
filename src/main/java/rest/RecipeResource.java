package rest;

import com.google.gson.*;
import dtos.RecipeDTO;
import entity.Ingredient;
import entity.Item;
import facades.RecipeFacade;
import jdk.nashorn.internal.parser.JSONParser;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

@Provider
@Path("recipe")
public class RecipeResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final RecipeFacade RECIPE_FACADE = RecipeFacade.getRecipeFacade(EMF);
    private Gson gson = new Gson();

    @GET
    @RolesAllowed({"user","admin"})
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllRecipes() {
        List<RecipeDTO> all = RECIPE_FACADE.getAllRecipes();
        return Response
                .ok(all)
                .build();
    }

    @GET
    @RolesAllowed({"user","admin"})
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/{id}")
    public Response getRecipeById(@PathParam("id") String input) {
        long id = Long.parseLong(input);
        return Response.ok(RECIPE_FACADE.getById(id)).build();
    }

    @POST
    @RolesAllowed({"user","admin"})
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getRecipeByIngredients(String json) {
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        List<Ingredient> ingredients = new ArrayList<>();
        for(int i = 0; i < array.size(); i++) {
            JsonObject element = array.get(i).getAsJsonObject();
            int amount = element.get("amount").getAsInt();
            Long id = element.get("id").getAsLong();
            Item item = gson.fromJson(element.get("ingredient").toString(), Item.class);
            Ingredient ingredient = new Ingredient(item,amount);
            ingredient.setId(id);
            ingredients.add(ingredient);
    }
        return Response.ok(RECIPE_FACADE.getByIngredients(ingredients)).build();
    }

    @POST
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("create")
    public Response createRecipe(String input){
        // We cant deserialize the RecipeDTO in entity stream, apparently.....
        RecipeDTO recipe = gson.fromJson(input, RecipeDTO.class);
        if(recipe == null) return Response.status(Response.Status.BAD_REQUEST).build();
        recipe = RECIPE_FACADE.createRecipe(recipe);
        return Response.ok(recipe).build();
    }

    @POST
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("update")
    public Response updateRecipe(String input) {
        // We cant deserialize the RecipeDTO in entity stream, apparently.....
        RecipeDTO recipe = gson.fromJson(input, RecipeDTO.class);
        if(recipe == null) return Response.status(Response.Status.BAD_REQUEST).build();
        recipe = RECIPE_FACADE.updateRecipe(recipe);
        return Response.ok(recipe).build();
    }

    @POST
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("delete")
    public Response deleteRecipe(String input) {
        // We cant deserialize the RecipeDTO in entity stream, apparently.....
        RecipeDTO recipe = gson.fromJson(input, RecipeDTO.class);
        if(recipe == null) return Response.status(Response.Status.BAD_REQUEST).build();
        recipe = RECIPE_FACADE.deleteRecipe(recipe);
        return Response.ok(recipe).build();
    }
}
