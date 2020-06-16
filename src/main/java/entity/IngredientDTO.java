package entity;

public class IngredientDTO {
    private Long id;
    private Item ingredient;
    private int amount;

    public IngredientDTO() {}

    public IngredientDTO(Long id, Item ingredient, int amount) {
        this.id = id;
        this.ingredient = ingredient;
        this.amount = amount;
    }

    public IngredientDTO(Ingredient ingredient) {
        this.id = ingredient.getId();
        this.ingredient = ingredient.getIngredient();
        this.amount = ingredient.getAmount();
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
