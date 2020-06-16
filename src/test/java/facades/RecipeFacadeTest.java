package facades;

import dtos.RecipeDTO;
import entity.Ingredient;
import entity.Recipe;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeFacadeTest extends BaseFacadeTest {
    private RecipeFacade recipeFacade = RecipeFacade.getRecipeFacade(entityManagerFactory);

    @Test
    public void test_getAllRecipes() {
        int expectedSize = 2;
        List<RecipeDTO> results = recipeFacade.getAllRecipes();
        int actualSize = results.size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void test_getRecipe_with_less_than_5_min_prepTime() {
        long expectedId = r2.getId();
        List<RecipeDTO> results = recipeFacade.getByLTPrepTime(5);
        assertEquals(1, results.size());
        assertEquals((long)expectedId, (long)results.get(0).getId());
    }

    @Test
    public void test_getRecipe_with_specific_ingredients() {
        long expectedId = r1.getId();
        List<Ingredient> searchIngredient = Utils.toList(in3, in4);
        List<RecipeDTO> results = recipeFacade.getByIngredients(searchIngredient);
        assertEquals(1, results.size());
        assertEquals((long)expectedId, (long)results.get(0).getId());
    }

    @Test
    public void test_createRecipe(){
        RecipeDTO recipe = new RecipeDTO(inList1,200, "Step 2");
        recipeFacade.createRecipe(recipe);
        assertNotNull(recipe.getId());
    }

    @Test
    public void test_updateRecipe_with_valid_recipe() {
        int expectedTime = 20;
        RecipeDTO r1DTO = new RecipeDTO(r1);
        r1DTO.setPreparationTime(expectedTime);
        recipeFacade.updateRecipe(r1DTO);
        assertEquals(expectedTime, recipeFacade.getById(r1.getId()).getPreparationTime());
    }

    @Test
    public void test_deleteRecipe_with_valid_recipe() {
        long expectedNewId = -1L;
        RecipeDTO result = recipeFacade.deleteRecipe(new RecipeDTO(r1));
        assertEquals((long)expectedNewId, (long)result.getId());
    }
}