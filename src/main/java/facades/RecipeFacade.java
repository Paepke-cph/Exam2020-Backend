package facades;

import dtos.RecipeDTO;
import entity.Ingredient;
import entity.IngredientDTO;
import entity.Recipe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class RecipeFacade {
    private static RecipeFacade instance;
    private static EntityManagerFactory entityManagerFactory;

    private RecipeFacade() {}

    public static RecipeFacade getRecipeFacade (EntityManagerFactory _emf) {
        if (instance == null) {
            entityManagerFactory = _emf;
            instance = new RecipeFacade();
        }
        return instance;
    }

    public static RecipeFacade getInstance() { return instance; }

    public RecipeDTO getById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        RecipeDTO result = null;
        try {
            entityManager.getTransaction().begin();
            result = new RecipeDTO(entityManager.find(Recipe.class, id));
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        return result;
    }

    public List<RecipeDTO> getAllRecipes() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<RecipeDTO> results = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            List<Recipe> recipes = entityManager
                    .createNamedQuery("Recipe.getAllRecipes", Recipe.class)
                    .getResultList();
            toDTOList(results, recipes);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        return results;
    }

    public List<RecipeDTO> getByLTPrepTime(int time) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<RecipeDTO> results = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            List<Recipe> recipes = entityManager
                    .createNamedQuery("Recipe.getByLTPrepTime", Recipe.class)
                    .setParameter("time",time)
                    .getResultList();
            toDTOList(results, recipes);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        return results;
    }



    public List<RecipeDTO> getByIngredients(List<Ingredient> searchIngredient) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        fetchIngredientIds(searchIngredient);
        List<RecipeDTO> results = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            List<Recipe> recipes = entityManager
                    .createNamedQuery("Recipe.getByIngredients", Recipe.class)
                    .setParameter("ingredients", searchIngredient)
                    .getResultList();
            toDTOList(results, recipes);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        return results;
    }

    public RecipeDTO createRecipe(RecipeDTO recipe) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Recipe rec = new Recipe(recipe);
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(rec);
            entityManager.getTransaction().commit();
            recipe.setId(rec.getId());
        } finally {
            entityManager.close();
        }
        return recipe;
    }

    public RecipeDTO updateRecipe(RecipeDTO recipe) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Recipe rec = new Recipe(recipe);
            entityManager.merge(rec);
            entityManager.getTransaction().commit();
            recipe.setId(rec.getId());
        } catch(EntityNotFoundException e) {
            //TODO(Benjamin): Handle with custom error!
        } finally {
            entityManager.close();
        }
        return recipe;
    }

    public RecipeDTO deleteRecipe(RecipeDTO recipe) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.createNamedQuery("Recipe.dropRelated")
                    .setParameter(1, recipe.getId())
                    .executeUpdate();
            Recipe rec = entityManager.find(Recipe.class, recipe.getId());
            if(rec != null) {
                entityManager.remove(rec);
                recipe.setId(-1L);
            }
            entityManager.getTransaction().commit();
        } catch(EntityNotFoundException e) {
            //TODO(Benjamin): Handle with custom error!
        } finally {
            entityManager.close();
        }
        return recipe;
    }

    private void toDTOList(List<RecipeDTO> results, List<Recipe> recipes) {
        if (!recipes.isEmpty()) {
            recipes.forEach((recipe) -> {
                results.add(new RecipeDTO(recipe));
            });
        }
    }

    private void fetchIngredientIds(List<Ingredient> searchIngredient) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        for (Ingredient ingredient : searchIngredient) {
            Ingredient temp = entityManager
                    .createNamedQuery("Ingredient.getByItemName", Ingredient.class)
                    .setParameter("name", ingredient.getIngredient().getName())
                    .getSingleResult();
            ingredient.setId(temp.getId());
        }
        entityManager.close();
    }
}
