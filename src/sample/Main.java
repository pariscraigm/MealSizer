package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Optional;


public class Main extends Application {

    public static Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
    public static ArrayList<Recipe> recipeList = DataSource.loadRecipes();
    public static ObservableList<Recipe> observableRecipeList = FXCollections.observableArrayList(recipeList);
    public static ArrayList<Ingredient> ingredientArrayList = new ArrayList<Ingredient>();
    public static Recipe currentRecipe;
    public static ArrayList<Unit> unitArrayList = DataSource.loadUnits();

    /**
     * Title Componenets
     */
    public static Label titleLabel = new Label("MealSizer");
    public static Button titleStartButton = new Button("Start");
    public static Button titleAboutButton = new Button("About");
    public static Button titleExitButton = new Button("Exit MealSizer");
    public static VBox titleButtonBox = new VBox(10);
    public static TilePane titleTilePane = new TilePane();
    public static Scene titleScene = new Scene(titleTilePane, 500, 500);


    /**
     * Recipe View Components
     */
    public static Stage recipeViewStage = new Stage();
    public static BorderPane recipeViewBorderPane = new BorderPane();
    public static Scene recipeViewScene = new Scene(recipeViewBorderPane, visualBounds.getWidth(),
            visualBounds.getHeight());
    public static Label recipeViewLabel = new Label("MealSizer");
    public static ListView<Recipe> recipeViewListView = new ListView<Recipe>();
    public static Button recipeViewSizeRecipeButton = new Button("Size Recipe Selection");
    public static Button recipeViewAddRecipeButton = new Button("Add Recipe");
    public static Button recipeViewRemoveRecipeButton = new Button("Remove Recipe");
    public static Button recipeViewEditRecipeButton = new Button("Edit Recipe Selection");
    public static Button recipeViewExitButton = new Button("Exit MealSizer");
    public static VBox recipeViewVBox = new VBox(10);

    /**
     * Edit Recipe View Components
     */
    public static Stage editRecipeStage = new Stage();
    public static BorderPane editRecipeBorderPane = new BorderPane();
    public static Scene editRecipeScene = new Scene(editRecipeBorderPane, visualBounds.getWidth(),
            visualBounds.getHeight());
    public static VBox editRecipeVBox = new VBox(10);
    public static ListView ingredientListView = new ListView();
    public static Label editRecipeLabel = new Label("MealSizer");
    public static Button editRecipeAddIngredientButton = new Button("Add Ingredient");
    public static Button editRecipeRemoveIngredientButton = new Button("Remove Ingredient");
    public static Button editRecipeBackButton = new Button("Back to Recipe View");
    public static Button editRecipeExitButton = new Button("Exit MealSizer");
    public static Text editRecipeName = new Text();


    /**
     * Add Recipe View Components
     */
    public static VBox addRecipeVBox = new VBox(10);
    public static Text addRecipeText = new Text("Please enter the name of the recipe you would like to add.");
    public static TextField addRecipeTextField = new TextField();
    public static HBox addRecipeButtonBox = new HBox(10);
    public static Button addRecipeAddButton = new Button("Add Recipe");
    public static Button addRecipeCancelButton = new Button("Cancel");

    /**
     * Add Ingredient View Components
     */
    public static VBox addIngredientViewVBox = new VBox(10);
    public static Text addIngredientViewInstructions = new Text("Please enter the name of the ingredient you would like to add.");
    public static TextField addIngredientViewNameTextField = new TextField();
    public static TextField addIngredientViewAmountTextField = new TextField();
    public static HBox addIngredientViewButtonBox = new HBox(10);
    public static Button addIngredientViewAddButton = new Button("Add Ingredient");
    public static Button addIngredientViewCancelButton = new Button("Cancel");
    public static Text addIngredientViewUnitsText = new Text("Units: ");
    public static HBox addIngredientViewEntryBoxes = new HBox(10);
    public static ComboBox addIngredientViewComboBox = new ComboBox();

    /**
     * Meal Sizer View Components
     */
    public static Stage mealSizerViewStage = new Stage();
    public static BorderPane mealSizerViewBorderPane = new BorderPane();
    public static Scene mealSizerViewScene = new Scene(mealSizerViewBorderPane, visualBounds.getWidth(),
            visualBounds.getHeight());
    public static Label mealSizerViewLabel = new Label("MealSizer");
    public static Button mealSizerViewSizeButton = new Button("Size Recipe");
    public static Button mealSizerViewBackButton = new Button("Back to Recipe View");
    public static Button mealSizerViewExitButton = new Button("Exit MealSizer");
    public static VBox mealSizerViewVBox = new VBox(10);
    public static GridPane mealSizerViewGridPane = new GridPane();
    public static Text nameText = new Text("Name");
    public static Text amountText = new Text("Amount");
    public static Text unitsText = new Text("Units");
    public static Text instructionsText = new Text("Please enter the amount you have");

    /**
     * Alert dialogs
     */
    Alert noRecipeSelectedAlert = new Alert(Alert.AlertType.ERROR, "No recipe selected.", ButtonType.OK);
    Alert emptyIngredientString = new Alert(Alert.AlertType.ERROR, "Ingredient name cannot be an " +
            "empty string.", ButtonType.OK);


    @Override
    public void start(Stage primaryStage) throws Exception {

        /** Load Ingredients into recipeList */
        recipeList = DataSource.loadIngredients(recipeList);

        /**Title page settings*/
        primaryStage.setTitle("Title Page");
        titleTilePane.setAlignment(Pos.CENTER);
        titleTilePane.setOrientation(Orientation.VERTICAL);
        titleTilePane.setPadding(new Insets(25, 25, 25, 25));
        titleLabel.setPadding(new Insets(50, 50, 50, 50));
        titleLabel.setMaxWidth(Double.MAX_VALUE);
        titleLabel.setTextAlignment(TextAlignment.CENTER);
        titleLabel.setAlignment(Pos.CENTER);
        titleStartButton.setMaxWidth(Double.MAX_VALUE);
        titleAboutButton.setMaxWidth(Double.MAX_VALUE);
        titleExitButton.setMaxWidth(Double.MAX_VALUE);
        titleButtonBox.getChildren().addAll(titleStartButton, titleAboutButton, titleExitButton);
        titleTilePane.getChildren().addAll(titleLabel, titleButtonBox);
        titleScene.getStylesheets().add(Main.class.getResource("Main.css").toExternalForm());
        primaryStage.setScene(titleScene);
        primaryStage.show();

        /**RecipeView Settings*/
        recipeViewStage.setTitle("Recipe View");
        recipeViewLabel.setPadding(new Insets(0, 50, 50, 50));
        recipeViewLabel.setMaxWidth(Double.MAX_VALUE);
        recipeViewLabel.setTextAlignment(TextAlignment.CENTER);
        recipeViewLabel.setAlignment(Pos.CENTER);
        recipeViewSizeRecipeButton.setMaxWidth(Double.MAX_VALUE);
        recipeViewAddRecipeButton.setMaxWidth(Double.MAX_VALUE);
        recipeViewRemoveRecipeButton.setMaxWidth(Double.MAX_VALUE);
        recipeViewEditRecipeButton.setMaxWidth(Double.MAX_VALUE);
        recipeViewExitButton.setMaxWidth(Double.MAX_VALUE);
        recipeViewBorderPane.setCenter(recipeViewListView);
        recipeViewBorderPane.setLeft(recipeViewVBox);
        recipeViewBorderPane.setPadding(new Insets(50, 50, 50, 0));
        recipeViewVBox.setPadding(new Insets(10, 10, 10, 10));
        recipeViewVBox.getChildren().setAll(recipeViewLabel, recipeViewSizeRecipeButton, recipeViewAddRecipeButton,
                recipeViewRemoveRecipeButton, recipeViewEditRecipeButton, recipeViewExitButton);
        recipeViewScene.getStylesheets().add(Main.class.getResource("Main.css").toExternalForm());
        recipeViewScene.setFill(null);
        recipeViewStage.setScene(recipeViewScene);
        recipeViewListView.setItems(observableRecipeList);
        recipeViewListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


        /**Edit Recipe Settings*/
        editRecipeStage.setTitle("Edit Recipe");
        editRecipeLabel.setPadding(new Insets(0, 50, 50, 50));
        editRecipeLabel.setMaxWidth(Double.MAX_VALUE);
        editRecipeLabel.setTextAlignment(TextAlignment.CENTER);
        editRecipeLabel.setAlignment(Pos.CENTER);
        editRecipeBorderPane.setCenter(ingredientListView);
        editRecipeAddIngredientButton.setMaxWidth(Double.MAX_VALUE);
        editRecipeRemoveIngredientButton.setMaxWidth(Double.MAX_VALUE);
        editRecipeBackButton.setMaxWidth(Double.MAX_VALUE);
        editRecipeExitButton.setMaxWidth(Double.MAX_VALUE);
        editRecipeVBox.setPadding(new Insets(10, 10, 10, 10));
        editRecipeVBox.getChildren().addAll(editRecipeLabel, editRecipeAddIngredientButton,
                editRecipeRemoveIngredientButton, editRecipeBackButton, editRecipeExitButton, editRecipeName);
        editRecipeScene.setFill(null);
        editRecipeScene.getStylesheets().add(Main.class.getResource("Main.css").toExternalForm());
        editRecipeBorderPane.setLeft(editRecipeVBox);
        editRecipeBorderPane.setPadding(new Insets(50, 50, 50, 0));
        editRecipeStage.setScene(editRecipeScene);

        /**Add Recipe Settings*/
        addRecipeVBox.setPadding(new Insets(10, 10, 10, 10));
        addRecipeVBox.getChildren().addAll(addRecipeText, addRecipeTextField, addRecipeButtonBox);
        addRecipeButtonBox.getChildren().addAll(addRecipeAddButton, addRecipeCancelButton);

        /**Add Ingredient Settings*/
        addIngredientViewVBox.setPadding(new Insets(10, 10, 10, 10));
        addIngredientViewNameTextField.setPromptText("Name");
        addIngredientViewAmountTextField.setPromptText("Amount");
        addIngredientViewComboBox.getItems().setAll(unitArrayList);
        addIngredientViewEntryBoxes.getChildren().setAll(addIngredientViewNameTextField,
                addIngredientViewAmountTextField, addIngredientViewUnitsText, addIngredientViewComboBox);
        addIngredientViewButtonBox.getChildren().addAll(addIngredientViewAddButton, addIngredientViewCancelButton);
        addIngredientViewVBox.getChildren().setAll(addIngredientViewInstructions, addIngredientViewEntryBoxes,
                addIngredientViewButtonBox);

        /**Meal Sizer View Settings*/
        mealSizerViewStage.setTitle("Meal Sizer View");
        mealSizerViewLabel.setPadding(new Insets(50, 50, 50, 50));
        mealSizerViewVBox.setPadding(new Insets(10, 10, 10, 10));
        mealSizerViewLabel.setMaxWidth(Double.MAX_VALUE);
        mealSizerViewLabel.setTextAlignment(TextAlignment.CENTER);
        mealSizerViewLabel.setAlignment(Pos.CENTER);
        mealSizerViewSizeButton.setMaxWidth(Double.MAX_VALUE);
        mealSizerViewBackButton.setMaxWidth(Double.MAX_VALUE);
        mealSizerViewExitButton.setMaxWidth(Double.MAX_VALUE);
        mealSizerViewVBox.getChildren().addAll(mealSizerViewLabel,mealSizerViewSizeButton, mealSizerViewBackButton,
                mealSizerViewExitButton);
        mealSizerViewBorderPane.setLeft(mealSizerViewVBox);
        mealSizerViewGridPane.add(nameText, 0, 0);
        mealSizerViewGridPane.add(amountText, 1, 0);
        mealSizerViewGridPane.add(unitsText, 2, 0);
        mealSizerViewGridPane.add(instructionsText, 3, 0);
        mealSizerViewGridPane.setVgap(10);
        mealSizerViewGridPane.setHgap(10);
        mealSizerViewGridPane.setPadding(new Insets(100, 10, 10, 10));
        mealSizerViewBorderPane.setCenter(mealSizerViewGridPane);
        mealSizerViewScene.setFill(null);
        mealSizerViewScene.getStylesheets().add(Main.class.getResource("Main.css").toExternalForm());
        mealSizerViewStage.setScene(mealSizerViewScene);

        titleStartButton.setOnAction((e) -> {
            recipeViewStage.show();
            primaryStage.close();
        });

        titleAboutButton.setOnAction((e) -> {
            //TODO: Figure out what to put in the about page.
        });

        titleExitButton.setOnAction((e) -> {
            System.exit(1);
        });

        recipeViewSizeRecipeButton.setOnAction((e) -> {
            if (getSelectedRecipe()) {
                recipeViewStage.close();
                addNodesToSizerView();
                mealSizerViewStage.show();
            } else {
                noRecipeSelectedAlert.show();
            }
        });


        recipeViewEditRecipeButton.setOnAction((e) -> {
            if (getSelectedRecipe()) {
                recipeViewStage.close();
                editRecipeName.setText("Current recipe: " + currentRecipe.toString());
                ingredientArrayList = currentRecipe.getIngredientList();
                ObservableList<Ingredient> observableIngredientList =
                        FXCollections.observableArrayList(ingredientArrayList);
                ingredientListView.setItems(observableIngredientList);
                editRecipeStage.show();
            } else {
                noRecipeSelectedAlert.show();
            }
        });

        /**
         * Opens the add recipe view. The add recipe view is a VBox that pops up in the top right corner of the
         * recipe view.
         */
        recipeViewAddRecipeButton.setOnAction((e) -> {
            recipeViewBorderPane.setRight(addRecipeVBox);
        });

        recipeViewRemoveRecipeButton.setOnAction((e) -> {
            if (getSelectedRecipe()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you" +
                        "want to delete recipe " + currentRecipe.getName() + "?");
                Optional<ButtonType> result = alert.showAndWait();
                if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                    recipeList.remove(currentRecipe);
                    DataSource.removeRecipe(currentRecipe);
                    observableRecipeList = FXCollections.observableArrayList(recipeList);
                    recipeViewListView.setItems(observableRecipeList);

                }
            } else {
                noRecipeSelectedAlert.show();
            }
        });

        /**
         * Exits the program.
         */
        recipeViewExitButton.setOnAction((e) -> {
            System.exit(1);
        });

        /**
         * The button that adds the recipe in the add recipe view. The add recipe view disappears after the recipe
         * is added.
         */
        addRecipeAddButton.setOnAction((e) -> {
            addRecipe();
            recipeViewBorderPane.setRight(null);
        });

        /**
         * The add recipe view disappears without a recipe being added.
         */
        addRecipeCancelButton.setOnAction((e) -> {
            recipeViewBorderPane.setRight(null);
        });

        /**
         * Opens the add ingredient view, which is a popup on the right of the edit recipe view.
         */
        editRecipeAddIngredientButton.setOnAction((e) -> {
            editRecipeBorderPane.setRight(addIngredientViewVBox);
        });

        editRecipeRemoveIngredientButton.setOnAction((e) -> {
            removeSelectedIngredient();
        });

        /**
         * Returns the user to the recipe view.
         */
        editRecipeBackButton.setOnAction((e) -> {
            editRecipeStage.close();
            editRecipeBorderPane.setRight(null);
            recipeViewStage.show();
        });

        /**
         * Adds the ingredient to the recipe as long as a name has been provided.
         */
        addIngredientViewAddButton.setOnAction((e) -> {
            if (!addIngredientViewNameTextField.getText().trim().equals("")) {
                addIngredientToRecipe();
                editRecipeBorderPane.setRight(null);
            } else {
                emptyIngredientString.show();
            }
        });

        /**
         * Closes the add ingredient view without adding any ingredient.
         */
        addIngredientViewCancelButton.setOnAction((e) -> {
            editRecipeBorderPane.setRight(null);
        });

        /**
         * Exits the program.
         */
        editRecipeExitButton.setOnAction((e) -> {
            System.exit(1);
        });

        mealSizerViewExitButton.setOnAction((e) -> {
            System.exit(1);
        });

        mealSizerViewBackButton.setOnAction((e) -> {
            recipeViewStage.show();
            mealSizerViewStage.close();
        });

        mealSizerViewSizeButton.setOnAction((e) -> {
            ObservableList<Node> gridChildList = mealSizerViewGridPane.getChildren();
            for (int i = 0; i < gridChildList.size(); i++) {
                if (gridChildList.get(i) instanceof HBox) {
                    HBox hbox = (HBox) gridChildList.get(i);
                    ObservableList<Node> hBoxChildList = hbox.getChildren();
                    TextField textField = (TextField) hBoxChildList.get(0);
                    ComboBox comboBox = (ComboBox) hBoxChildList.get(1);
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }


    public static boolean getSelectedRecipe() {
        if (!recipeViewListView.getSelectionModel().isEmpty()) {
            currentRecipe = recipeViewListView.getSelectionModel().getSelectedItem();
            return true;
        }
        return false;
    }

    public static void addRecipe() {
        String recipeName = addRecipeTextField.getText();
        Recipe recipe = new Recipe(recipeName);
        recipeList.add(recipe);
        observableRecipeList = FXCollections.observableArrayList(recipeList);
        recipeViewListView.setItems(observableRecipeList);
        DataSource.addRecipe(recipe);
    }

    public static void addIngredientToRecipe() {
        String name = addIngredientViewNameTextField.getText();
        Double amount = Double.parseDouble(addIngredientViewAmountTextField.getText());
        Unit unit = (Unit) addIngredientViewComboBox.getSelectionModel().getSelectedItem();
        Ingredient ingredient = new Ingredient(name, amount, unit);
        currentRecipe.addIngredient(ingredient);
        DataSource.addIngredient(currentRecipe, ingredient);
        ingredientArrayList = currentRecipe.getIngredientList();
        ObservableList<Ingredient> observableIngredientList =
                FXCollections.observableArrayList(ingredientArrayList);
        ingredientListView.setItems(observableIngredientList);
    }

    public static void removeSelectedIngredient() {
        if (!ingredientListView.getSelectionModel().isEmpty()) {
            Ingredient ingredient = (Ingredient) ingredientListView.getSelectionModel().getSelectedItem();
            currentRecipe.removeIngredient(ingredient);
            DataSource.removeIngredient(currentRecipe, ingredient);
            ingredientArrayList = currentRecipe.getIngredientList();
            ObservableList<Ingredient> observableIngredientList =
                    FXCollections.observableArrayList(ingredientArrayList);
            ingredientListView.setItems(observableIngredientList);
        }
    }

    public static void addNodesToSizerView() {
        for (int i = 0; i < currentRecipe.getIngredientList().size(); i++) {
            Text name = new Text(currentRecipe.getIngredientList().get(i).getName());
            Text amount = new Text("" + currentRecipe.getIngredientList().get(i).getAmount());
            Text unit = new Text(currentRecipe.getIngredientList().get(i).getUnits().toString());
            mealSizerViewGridPane.add(name, 0,i+1);
            mealSizerViewGridPane.add(amount, 1,i+1);
            mealSizerViewGridPane.add(unit, 2,i+1);
            HBox hbox = new HBox(10);
            TextField textField = new TextField();
            ComboBox<Unit> comboBox = new ComboBox<Unit>();
            comboBox.getItems().setAll(unitArrayList);
            hbox.getChildren().setAll(textField, comboBox);
            mealSizerViewGridPane.add(hbox, 3, i+1);
        }
    }

}
