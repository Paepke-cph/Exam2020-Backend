package dtos;

import entity.Ingredient;
import entity.Recipe;
import java.util.ArrayList;
import java.util.List;

public class RecipeDTO {
    private Long id;
    private List<Ingredient> ingredients = new ArrayList<>();
    private int preparationTime;
    private String directions;

    public RecipeDTO(List<Ingredient> ingredients, int preparationTime, String directions) {
        this.ingredients = ingredients;
        this.preparationTime = preparationTime;
        this.directions = directions;
    }

    public RecipeDTO(Recipe recipe) {
        this.id = recipe.getId();
        this.ingredients = recipe.getIngredients();
        this.preparationTime = recipe.getPreparationTime();
        this.directions = recipe.getDirections();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }
}
