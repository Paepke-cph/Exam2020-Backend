package entity;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
public class Ingredient {
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
