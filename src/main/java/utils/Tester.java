package utils;

import entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.IllegalFormatPrecisionException;
import java.util.List;

public class Tester {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST,EMF_Creator.Strategy.DROP_AND_CREATE);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Item item = new Item(null, "chess", 10);
        Ingredient ingredient = new Ingredient(item, 100);
        Ingredient ingredient2 = new Ingredient(item, 400);

        Storage storage = new Storage(item, 1000);

        List<Ingredient> recipeIngredients = new ArrayList<>();
        recipeIngredients.add(ingredient);

        List<Ingredient> recipeIngredients2 = new ArrayList<>();
        recipeIngredients2.add(ingredient2);

        Recipe recipe = new Recipe(recipeIngredients, 70, "<ul><li>Step 1</li></ul>");
        Recipe recipe2 = new Recipe(recipeIngredients2, 90,"<ul><li>Step 1</li></ul>");

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe);
        recipes.add(recipe2);

        WeekMenuPlan weekMenuPlan = new WeekMenuPlan(recipes, 40, 2020);

        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.persist(ingredient);
        entityManager.persist(storage);
        entityManager.persist(recipe);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        entityManager.persist(ingredient2);
        entityManager.persist(recipe2);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        entityManager.persist(weekMenuPlan);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
