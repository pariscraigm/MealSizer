package sample;
import java.util.ArrayList;

/**
 * Created by craigparis on 8/6/17.
 */
public class Recipe {

    private String name;
    private ArrayList<Ingredient> list = new ArrayList<Ingredient>();

    public Recipe(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Ingredient> getIngredientList() {
        return this.list;
    }

    public void addIngredient(Ingredient ingredient) {
        this.list.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient) {
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).getName().equals(ingredient.getName())) {
                list.remove(i);
            }
        }
    }

    public String toString() {
        return this.name;
    }
}
