package entity;

import javax.persistence.*;

@Entity
@Table(name = "storage")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "storage_item", referencedColumnName = "item_id", nullable = false)
    private Item ingredient;
    @Column(name = "storage_amount", nullable = false)
    private int amount;

    public Storage() {
    }

    public Storage(Item ingredient, int amount) {
        this.ingredient = ingredient;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
