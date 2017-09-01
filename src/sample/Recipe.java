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

    public ArrayList<Ingredient> getConvertedIngredientList() {
        ArrayList<Ingredient> convertedList = new ArrayList<>();
        for (int i = 0; i < this.list.size(); i++) {
            Ingredient ingredient = this.list.get(i);
            ingredient.setAmount(ingredient.getAmount() * ingredient.getUnits().getConversion());
            if (ingredient.getUnits().getType().equals("volume")) {
                ingredient.getUnits().setName(Unit.DEFAULT_VOLUME_NAME);
            }
            convertedList.add(ingredient);
        }
        return convertedList;
    }

    public String toString() {
        return this.name;
    }
}
