// Missing imports, just for demonstration use.
public final class Recipe{
  private String name;
  private List<Ingredient> ingredients;
  private List<Step> steps;

  public Recipe(String name, Ingredient[] ingredients, Step[] steps){
    this.name = name;
    ingredients = new ArrayList<>();
    for (Ingredient ingredient : ingredients) {
      this.ingredients.add(ingredient);
    }
    steps = new ArrayList<>();
    for (Step step : steps) {
      this.steps.add(step);
    }
  }

  public List<Ingredient> getIngredients(){
    return new ArrayList<Ingredient>(ingredients);
    // Defensive copy, assuming the Ingredient and Step classes are immutable designed.
  }

  public String getName(){
    return name; // String is immutable, no need for defensive copy.
  }

  public List<Step> getSteps(){
    return new ArrayList<Step>(steps); // Defensive copy, what if the Step Class is mutable?
  }
}
