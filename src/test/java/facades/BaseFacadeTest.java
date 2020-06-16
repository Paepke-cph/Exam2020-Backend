package facades;

import entity.Ingredient;
import entity.Item;
import entity.Recipe;
import org.junit.jupiter.api.BeforeEach;
import utils.EMF_Creator;
import utils.Utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class BaseFacadeTest {
    protected Item i1 = new Item(0L,"Bacon", 10);
    protected Item i2 = new Item(0L,"Salt", 1);
    protected Item i3 = new Item(0L,"Onion", 5);
    protected Item i4 = new Item(0L, "Flour", 2);

    protected Ingredient in1 = new Ingredient(i1,10);
    protected Ingredient in2 = new Ingredient(i2,1);
    protected Ingredient in3 = new Ingredient(i3,5);
    protected Ingredient in4 = new Ingredient(i4, 400);

    protected List<Ingredient> inList1 = Utils.toList(in1,in2,in3,in4);
    protected List<Ingredient> inList2 = Utils.toList(in1,in2);

    protected Recipe r1 = new Recipe(inList1,10,"Step 1");
    protected Recipe r2 = new Recipe(inList2, 2, "Step 1");

    protected EntityManagerFactory entityManagerFactory =
            EMF_Creator.createEntityManagerFactory(
                    EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);

    @BeforeEach
    public void setup() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery("delete from recipe_ingredient").executeUpdate();
        entityManager.createNamedQuery("Recipe.dropAll").executeUpdate();
        entityManager.createNamedQuery("Ingredient.dropAll").executeUpdate();
        entityManager.createNamedQuery("Item.dropAll").executeUpdate();

        entityManager.persist(i1);
        entityManager.persist(i2);
        entityManager.persist(i3);
        entityManager.persist(i4);

        entityManager.persist(in1);
        entityManager.persist(in2);
        entityManager.persist(in3);
        entityManager.persist(in4);

        entityManager.persist(r1);
        entityManager.persist(r2);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
