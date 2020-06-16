package entity;

import dtos.IngredientDTO;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ingredients")
@NamedQueries({
        @NamedQuery(
                name = "Ingredient.dropAll",
                query = "delete from Ingredient"
        ),
        @NamedQuery(
                name = "Ingredient.getByItemName",
                query = "select i from Ingredient i where i.ingredient.name = :name"
        ),
        @NamedQuery(
                name = "Ingredient.getAll",
                query = "select i from Ingredient i"
        )
})
public class Ingredient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ingredient_item", referencedColumnName = "item_id")
    private Item ingredient;
    @Column(name = "ingredient_amount")
    private int amount;

    public Ingredient() {
    }

    public Ingredient(Item ingredient, int amount) {
        this.ingredient = ingredient;
        this.amount = amount;
    }

    public Ingredient(IngredientDTO dto)  {
        this.id = dto.getId();
        this.ingredient = dto.getIngredient();
        this.amount = dto.getAmount();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Item getIngredient() {
        return ingredient;
    }

    public void setIngredient(Item ingredient) {
        this.ingredient = ingredient;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
