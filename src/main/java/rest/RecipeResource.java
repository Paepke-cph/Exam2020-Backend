package rest;

import com.google.gson.Gson;
import dtos.RecipeDTO;
import entity.Ingredient;
import facades.RecipeFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
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

    @POST
    @RolesAllowed({"user","admin"})
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/id")
    public Response getRecipeById(String input) {
        long id = Long.parseLong(input);
        return Response.ok(RECIPE_FACADE.getById(id)).build();
    }

    @POST
    @RolesAllowed({"user","admin"})
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getRecipeByIngredients(List<Ingredient> ingredients) {
        List<RecipeDTO> recipes = RECIPE_FACADE.getByIngredients(ingredients);
        return Response.ok(recipes).build();
    }

    @POST
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("create")
    public Response createRecipe(String input){
        // We cant deserialize the RecipeDTO in entity stream, apparently.....
        RecipeDTO recipe = gson.fromJson(input, RecipeDTO.class);
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
        recipe = RECIPE_FACADE.deleteRecipe(recipe);
        return Response.ok(recipe).build();
    }
}
