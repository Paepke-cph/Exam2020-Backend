package entity;

import dtos.RecipeDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class WeekMenuPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weekMenuPlan_id")
    private Long id;

    @ManyToOne
    @JoinTable(
            name = "weekMenuPlan_id",
            joinColumns = {@JoinColumn(name = "weekMenuPlan_id", referencedColumnName = "weekMenuPlan_id")},
            inverseJoinColumns = {@JoinColumn(name ="recipe_id", referencedColumnName = "recipe_id")}
    )
    private List<Recipe> recipes = new ArrayList<>();

    @Column(name = "weekMenuPlan_id_weekNumber", nullable = false)
    private Integer weekNumber;
    @Column(name = "weekMenuPlan_id_year", nullable = false)
    private Integer year;

    public WeekMenuPlan() {}

    public WeekMenuPlan(List<Recipe> recipes, Integer weekNumber, Integer year) {
        this.recipes = recipes;
        this.weekNumber = weekNumber;
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public List<RecipeDTO> getRecipesAsDTOs() {
        List<RecipeDTO> dtos = new ArrayList<>();
        recipes.forEach((recipe -> {dtos.add(new RecipeDTO(recipe));}));
        return dtos;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
