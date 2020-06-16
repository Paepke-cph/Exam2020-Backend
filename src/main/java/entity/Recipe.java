package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long id;
    @OneToMany
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
