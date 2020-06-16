package dtos;

import entity.WeekMenuPlan;

import java.util.ArrayList;
import java.util.List;

public class WeekMenuPlanDTO {
    private Long id;
    private List<RecipeDTO> recipes = new ArrayList<>();
    private int weekNumber;
    private int year;

    public WeekMenuPlanDTO() {}

    public WeekMenuPlanDTO(Long id, List<RecipeDTO> recipes, int weekNumber, int year) {
        this.id = id;
        this.recipes = recipes;
        this.weekNumber = weekNumber;
        this.year = year;
    }

    public WeekMenuPlanDTO(WeekMenuPlan weekMenuPlan) {
        this.id = weekMenuPlan.getId();
        this.recipes = weekMenuPlan.getRecipesAsDTOs();
        this.weekNumber = weekMenuPlan.getWeekNumber();
        this.year = weekMenuPlan.getYear();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RecipeDTO> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeDTO> recipes) {
        this.recipes = recipes;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
