package rest;

import com.google.gson.Gson;
import facades.ItemFacade;
import facades.RecipeFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@Path("item")
public class ItemResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final ItemFacade ITEM_FACADE = ItemFacade.getItemFacade(EMF);
    private Gson gson = new Gson();

    @GET
    @RolesAllowed({"user","admin"})
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllItems() {
        return Response.ok(ITEM_FACADE.getAllItems()).build();
    }
}
