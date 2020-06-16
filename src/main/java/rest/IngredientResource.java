package rest;

import com.google.gson.Gson;
import entity.IngredientDTO;
import facades.IngredientFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.List;

@Provider
@Path("ingredient")
public class IngredientResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final IngredientFacade INGREDIENT_FACADE = IngredientFacade.getIngredientFacade(EMF);
    private Gson gson = new Gson();

    @GET
    @RolesAllowed({"user","admin"})
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllRecipes() {
        List<IngredientDTO> all = INGREDIENT_FACADE.getAllIngredients();
        return Response
                .ok(all)
                .build();
    }
}
