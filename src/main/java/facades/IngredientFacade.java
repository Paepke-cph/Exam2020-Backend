package facades;

import entity.Ingredient;
import entity.IngredientDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class IngredientFacade {
    private static IngredientFacade instance;
    private static EntityManagerFactory entityManagerFactory;

    private IngredientFacade() {}

    public static IngredientFacade getIngredientFacade (EntityManagerFactory _emf) {
        if (instance == null) {
            entityManagerFactory = _emf;
            instance = new IngredientFacade();
        }
        return instance;
    }

    public static IngredientFacade getInstance() { return instance; }

    public List<IngredientDTO> getAllIngredients() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<IngredientDTO> results = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            List<Ingredient> ingredients = entityManager
                    .createNamedQuery("Ingredient.getAll", Ingredient.class)
                    .getResultList();
            if(!ingredients.isEmpty()) {
                ingredients.forEach((ingredient -> {results.add(new IngredientDTO(ingredient));}));
            }
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        return results;
    }
}
