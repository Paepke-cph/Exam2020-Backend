package facades;

import dtos.ItemDTO;
import entity.Ingredient;
import entity.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class ItemFacade {
    private static ItemFacade instance;
    private static EntityManagerFactory entityManagerFactory;

    private ItemFacade() {}

    public static ItemFacade getItemFacade (EntityManagerFactory _emf) {
        if (instance == null) {
            entityManagerFactory = _emf;
            instance = new ItemFacade();
        }
        return instance;
    }

    public List<ItemDTO> getAllItems() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<ItemDTO> results = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            List<Item> items = entityManager
                    .createNamedQuery("Item.getAll", Item.class)
                    .getResultList();
            if(!items.isEmpty()) {
                items.forEach((item -> {results.add(new ItemDTO(item));}));
            }
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        return results;
    }
}
