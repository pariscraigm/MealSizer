package sample;

/**
 * Created by craigparis on 8/6/17.
 */
public class Ingredient {

    private String name;
    private double amount;
    private Unit units;

    public Ingredient(String name, double amount, Unit units) {
        this.name = name;
        this.amount = amount;
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public Unit getUnits() {
        return units;
    }

    public String toString() {
        return this.name;
    }

    public boolean equals(Object o) {
        if (o instanceof Ingredient) {
            Ingredient ingredient = (Ingredient) o;
            return this.name.equals(ingredient.getName()) && this.amount == ingredient.getAmount() &&
                    this.units.equals(ingredient.getUnits());
        }
        return false;
    }
}
