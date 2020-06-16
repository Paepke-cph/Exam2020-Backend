package entity;

import java.util.ArrayList;
import java.util.List;

public class WeekMenuPlan {
    private Long id;
    // TODO(Benjamin) This list must contain 7 items and be ordered (which ordering?)
    private List<Recipe> recipes = new ArrayList<>();
    private int weekNumber;
    private int year;
}
