package sample;

/**
 * Created by craigparis on 8/6/17.
 */
public class Unit {

    private String name;
    private double conversion;
    private String type;

    public Unit(String name, double conversion, String type) {
        this.name = name;
        this.conversion = conversion;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public double getConversion() {
        return conversion;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Unit) {
            Unit unit = (Unit) o;
            if (this.name.equals(unit.getName()) && this.conversion == unit.getConversion() &&
                    this.type.equals(unit.getType())) {
                return true;
            }
        }
        return false;
    }
}

