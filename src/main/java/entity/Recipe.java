package entity;

import dtos.RecipeDTO;

import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipes")
@NamedQueries({
        @NamedQuery(
                name = "Recipe.dropAll",
                query = "delete from Recipe"
        ),
        @NamedQuery(
                name = "Recipe.getAllRecipes",
                query = "select r from Recipe r"
        ),
        @NamedQuery(
                name = "Recipe.getByLTPrepTime",
                query = "select r from Recipe r where r.preparationTime <= :time"
        ),
        @NamedQuery(
                name = "Recipe.getByIngredients",
                query = "select distinct r from Recipe r where r.ingredients in :ingredients"
        ),
})
@NamedNativeQuery(
        name = "Recipe.dropRelated",
        query = "delete from recipe_ingredient where recipe_ingredient.recipe_id = ?"
)
public class Recipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long id;
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "recipe_ingredient",
            joinColumns = {@JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id")},
            inverseJoinColumns = {@JoinColumn(name = "ingredient_id", referencedColumnName = "ingredient_id")}
    )
    private List<Ingredient> ingredients = new ArrayList<>();

    @Column(name = "recipe_preparationTime", nullable = false)
    private int preparationTime;
    @Column(name = "recipe_directions")
    private String directions;


    public Recipe() {
    }

    public Recipe(List<Ingredient> ingredients, int preparationTime, String directions) {
        this.ingredients = ingredients;
        this.preparationTime = preparationTime;
        this.directions = directions;
    }

    public Recipe(RecipeDTO dto) {
        this.id = dto.getId();
        this.ingredients = dto.getIngredients();
        this.preparationTime = dto.getPreparationTime();
        this.directions = dto.getDirections();
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
