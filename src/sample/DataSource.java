package sample;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author sqlitetutorial.net
 */
public class DataSource {
    /**
     * Connect to a sample database
     */

    private static Connection conn;

    public static boolean open() {
        try {
            String url = "jdbc:sqlite:/Users/craigparis/IdeaProjects/MealSizer/src/sample/RecipeDB.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to db established.");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection." + e.getMessage());
        }
    }

    public static ArrayList<Recipe> loadRecipes() {

        if(open()) {
            try {
                Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery("SELECT * FROM Recipe");
                ArrayList<Recipe> recipeArrayList = new ArrayList<>();
                while(results.next()) {
                    Recipe recipe = new Recipe(results.getString("RECIPE_NAME"));
                    recipeArrayList.add(recipe);
                }
                close();
                return recipeArrayList;
            } catch (Exception e) {
                System.out.println("Unable to connect");
                e.getMessage();
            }
        }
        return new ArrayList<>();
    }

    public static ArrayList<Unit> loadUnits() {

        if(open()) {
            try {
                Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery("SELECT * FROM units");
                ArrayList<Unit> unitArrayList = new ArrayList<>();
                while(results.next()) {
                    Unit unit = new Unit(results.getString("name"),
                            results.getDouble("conversion"),
                            results.getString("type"));
                    unitArrayList.add(unit);
                }
                close();
                return unitArrayList;
            } catch (Exception e) {
                System.out.println("Unable to connect");
                e.getMessage();
            }
        }
        return new ArrayList<>();
    }

    public static void addRecipe(Recipe recipe) {
        if(open()) {
            try {
                Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery("INSERT INTO `Recipe`(`RECIPE_NAME`) VALUES ('" +
                        recipe.getName() + "')");
                close();
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    public static ArrayList<Recipe> loadIngredients(ArrayList<Recipe> recipeList) {

        if(open()) {
            for (int i = 0; i < recipeList.size(); i++) {
                String recipeName = recipeList.get(i).getName();
                try {
                    Statement statement = conn.createStatement();
                    ResultSet results = statement.executeQuery("SELECT * FROM Ingredients WHERE " +
                            "Recipe = '" + recipeName + "'");
                    while (results.next()) {
                        String ingredientName = results.getString("Name");
                        Double ingredientAmount = results.getDouble("Amount");
                        String unitName = results.getString("Unit");
                        Unit ingredientUnit;
                        for (int j = 0; j < Main.unitArrayList.size(); j++) {
                            if (Main.unitArrayList.get(j).getName().equals(unitName)) {
                                Double conversion = Main.unitArrayList.get(j).getConversion();
                                String type = Main.unitArrayList.get(j).getType();
                                ingredientUnit = new Unit(unitName, conversion, type);
                                Ingredient ingredient = new Ingredient(ingredientName, ingredientAmount, ingredientUnit);
                                recipeList.get(i).addIngredient(ingredient);
                                break;
                            }
                        }

                    }
                } catch (Exception e) {
                    System.out.println("Error loading ingredients for " + recipeName);
                    e.getMessage();
                }
            }
            close();
        }
        return recipeList;
    }

    public static void addIngredient(Recipe recipe, Ingredient ingredient) {

        if(open()) {
            try {
                Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery("INSERT INTO Ingredients (Name, Amount, Unit, Recipe)" +
                        "VALUES ('" + ingredient.getName() + "','" + ingredient.getAmount() + "','" +
                        ingredient.getUnits() + "','" + recipe.getName() + "')");
                close();
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    public static void removeIngredient(Recipe recipe, Ingredient ingredient) {
        if(open()) {
            try {
                Statement statement = conn.createStatement();
                statement.executeUpdate("DELETE FROM Ingredients WHERE Recipe='" + recipe.getName() +
                        "' AND Name='" + ingredient.getName() + "';");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            close();
        }
    }

    public static void removeRecipe(Recipe recipe) {
        if (open()) {
            try {
                Statement statement = conn.createStatement();
                statement.executeUpdate("DELETE FROM Recipe WHERE RECIPE_NAME='" + recipe.getName() + "'");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        close();
    }

}
