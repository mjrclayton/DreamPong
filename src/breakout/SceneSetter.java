package breakout;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SceneSetter extends Application {
    //Magic Numbers
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final int X_SIZE = 400;
    public static final int Y_SIZE = 600;
    public static final double ONE_THIRD_SCALEDOWN = (1.0/3.0);
    public static final double ONE_THIRD_TRANSLATE = (1.0/3.0);
    public static final double ONE_SIXTH_TRANSLATE = (1.0/6.0);
    public static final double ONE_FIFTIETH_TRANSLATE = (1.0/50.0);
    public static final double ALMOST_HALF_TRANSLATE = (24.0/50.0);
    public static final double ONE_TWELVETH_TRANSLATE = (1.0/12.0);
    public static final double TRANSLATE_NONE = 0;


    //Start Screen Objects
    public static final Paint WHITE_BACKGROUND = Color.WHITE;
    public static final String STAGE_TITLE = "DreamPong";
    public static final String TITLE_SCREEN_TITLE = "title.png";
    public static final String TITLE_SCREEN_CLOUD = "cloud.png";
    public static final String TITLE_SCREEN_PLAY = "playbutton.png";
    public static final String TITLE_SCREEN_PLAY_HOVER = "playbuttonhover.png";
    public static final String TITLE_SCREEN_RULES = "rulesbutton.png";
    public static final String TITLE_SCREEN_RULES_HOVER = "rulesbuttonhover.png";
    public static final String TITLE_SCREEN_STORE = "storebutton.png";
    public static final String TITLE_SCREEN_STORE_HOVER = "storebuttonhover.png";
    public static final String TITLE_SCREEN_SIGNATURE = "signature.png";
    private Scene startScene;
    private ImageView titleScreenTitle;
    private ImageView titleScreenCloud1;
    private ImageView titleScreenCloud2;
    private Button titleScreenPlay;
    private Button titleScreenRules;
    private Button titleScreenStore;
    private ImageView titleScreenSignature;

    //Rules Screen Objects
    private Scene rulesScene;
    public static final String RULES_SCREEN_TEXT = "rulestext.png";
    public static final String BACK_BUTTON = "backbutton.png";
    public static final String BACK_BUTTON_HOVER = "backbuttonhover.png";
    private ImageView rulesScreenText;
    private Button rulesScreenBackButton;

    //Level Screen Objects
    private Scene levelScene;
    public static final String LEVEL_SCREEN_LAYOUT = "levellayout.png";
    public static final String LEVEL_SCREEN_MENU_BUTTON = "menubutton.png";
    public static final String LEVEL_SCREEN_MENU_BUTTON_HOVER = "menubuttonhover.png";
    public static final String STANDARD_BRICK = "standardbrick.png";
    public static final String HEAVY_BRICK = "heavybrick.png";
    public static final String EXTRA_HEAVY_BRICK = "extraheavybrick.png";
    public static final String LEVEL_1 = "Level1.txt";
    public static final String LEVEL_2 = "Level2.txt";
    public static final String LEVEL_3 = "Level3.txt";
    public static final String LEVEL_4 = "Level4.txt";
    public static final String LEVEL_5 = "Level5.txt";
    private ImageView levelScreenLayout;
    private Button levelScreenMenuButton;
    private GridPane brickGrid;

    @Override
    public void start (Stage stage){
        /*
        Need to follow 3 steps within the start method
            1)Prepare a scene graph with required notes
            2)Prepare a scene with the required dimensions and add the scene graph to it
            3)Prepare a stage and add the scene to the stage and display the contents of the stage
         */

        //Steps 1) and 2)
        startScene = setupStartScene(X_SIZE, Y_SIZE, WHITE_BACKGROUND);
        rulesScene = setupRulesScene(X_SIZE, Y_SIZE, WHITE_BACKGROUND);
        levelScene = setupLevelScene(X_SIZE, Y_SIZE, WHITE_BACKGROUND, LEVEL_5);

        //Set button properties after creating scene because of null scene bugs that happened if set them in
        //scene constructing methods
        setStartScreenButtonProperties(stage);
        setRulesScreenButtonProperties(stage);
        setLevelScreenButtonProperties(stage);

        //Step 3)
        stage.setTitle(STAGE_TITLE);
        stage.setScene(startScene);
        stage.show();
    }

    private Scene setupStartScene(int width, int height, Paint background){
        Group root = new Group();

        titleScreenTitle = createImageViewAndPlace(TITLE_SCREEN_TITLE, width, height, ONE_THIRD_SCALEDOWN, TRANSLATE_NONE, ONE_SIXTH_TRANSLATE);
        titleScreenCloud1 = createImageViewAndPlace(TITLE_SCREEN_CLOUD, width, height, ONE_THIRD_SCALEDOWN, ONE_THIRD_TRANSLATE, TRANSLATE_NONE);
        titleScreenCloud2 = createImageViewAndPlace(TITLE_SCREEN_CLOUD, width, height, ONE_THIRD_SCALEDOWN, -ONE_THIRD_TRANSLATE, ONE_THIRD_TRANSLATE);
        titleScreenPlay = createButtonFromImageAndPlace(TITLE_SCREEN_PLAY, width, height, ONE_THIRD_SCALEDOWN, TRANSLATE_NONE, -ONE_TWELVETH_TRANSLATE);
        titleScreenStore = createButtonFromImageAndPlace(TITLE_SCREEN_STORE, width, height, ONE_THIRD_SCALEDOWN, TRANSLATE_NONE,
                -(ONE_SIXTH_TRANSLATE + ONE_FIFTIETH_TRANSLATE));
        titleScreenRules = createButtonFromImageAndPlace(TITLE_SCREEN_RULES, width, height, ONE_THIRD_SCALEDOWN, TRANSLATE_NONE,
                -(ONE_SIXTH_TRANSLATE + ONE_TWELVETH_TRANSLATE + (2*ONE_FIFTIETH_TRANSLATE)));
        titleScreenSignature = createImageViewAndPlace(TITLE_SCREEN_SIGNATURE, width, height, ONE_THIRD_SCALEDOWN, TRANSLATE_NONE, -ALMOST_HALF_TRANSLATE);

        ImageView[] startScreenImageViews = new ImageView[]{titleScreenTitle, titleScreenCloud1, titleScreenCloud2, titleScreenSignature};
        Button[] startScreenButtons = new Button[]{titleScreenPlay, titleScreenStore, titleScreenRules};

        root.getChildren().addAll(startScreenImageViews);
        root.getChildren().addAll(startScreenButtons);

        Scene scene = new Scene(root, width, height, background);
        return scene;
    }

    private Scene setupRulesScene(int width, int height, Paint background){
        Group root = new Group();

        rulesScreenText = createImageViewAndPlace(RULES_SCREEN_TEXT, width, height, ONE_THIRD_SCALEDOWN, TRANSLATE_NONE,
                TRANSLATE_NONE);
        rulesScreenBackButton = createButtonFromImageAndPlace(BACK_BUTTON, width, height, ONE_THIRD_SCALEDOWN,
                -(1.9/5.0), -(2.1/5.0));

        root.getChildren().add(rulesScreenText);
        root.getChildren().add(rulesScreenBackButton);

        Scene scene = new Scene(root, width, height, background);
        return scene;
    }

    private Scene setupLevelScene(int width, int height, Paint background, String level){
        Group root = new Group();

        levelScreenLayout = createImageViewAndPlace(LEVEL_SCREEN_LAYOUT, width, height, ONE_THIRD_SCALEDOWN, TRANSLATE_NONE,
                TRANSLATE_NONE);
        levelScreenMenuButton = createButtonFromImageAndPlace(LEVEL_SCREEN_MENU_BUTTON, width, height, ONE_THIRD_SCALEDOWN*.66,
                -ONE_THIRD_TRANSLATE, ONE_THIRD_TRANSLATE);
        brickGrid = createAndAlignBrickGrid(width, height);

        loadLevel(brickGrid, level);

        root.getChildren().add(levelScreenLayout);
        root.getChildren().add(levelScreenMenuButton);
        root.getChildren().add(brickGrid);

        Scene scene = new Scene(root, width, height, background);
        return scene;
    }

    private ImageView createImageViewAndPlace(String imageName, int sceneWidth, int sceneHeight, double scale, double translateWidthScale,
                                        double translateHeightScale){
        //translateWidthScale and HeightScale are the the fractions of the scenes total width and height that you want
        //to move the image left/right and up/down
        Image image = createImageFromResourceStream(imageName);
        ImageView tempImageView = new ImageView(image);
        tempImageView.setX(sceneWidth / 2 - (sceneWidth * translateWidthScale) - tempImageView.getBoundsInLocal().getWidth() / 2);
        tempImageView.setY(sceneHeight / 2 - (sceneHeight * translateHeightScale) - tempImageView.getBoundsInLocal().getHeight() / 2);
        scaleImage(tempImageView, scale);
        return tempImageView;
    }

    private Button createButtonFromImageAndPlace(String imageName, int sceneWidth, int sceneHeight, double scale, double translateWidthScale,
                                                 double translateHeightScale){
        Image image = createImageFromResourceStream(imageName);
        ImageView tempImageView = new ImageView(image);
        Button button = new Button("", tempImageView);
        button.setLayoutX(sceneWidth / 2 - (sceneWidth * translateWidthScale) - tempImageView.getBoundsInLocal().getWidth() / 2);
        button.setLayoutY(sceneHeight / 2 - (sceneHeight * translateHeightScale) - tempImageView.getBoundsInLocal().getHeight() / 2);
        scaleButton(button, scale);
        button.setStyle("fx-border-color: transparent");
        button.setStyle("-fx-background-color: transparent");
        return button;
    }

    private GridPane createAndAlignBrickGrid(int sceneWidth, int sceneHeight){
        GridPane grid = new GridPane();
        grid.setMaxSize(265,498);
        grid.setMinSize(265,498);
        final int cols = 8, rows = 10;
        for(int i = 0; i < cols; i++){
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setPercentWidth(100.0/cols);
            grid.getColumnConstraints().add(colConstraints);
        }
        for(int i = 0; i < rows; i++){
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(100.0/rows);
            grid.getRowConstraints().add(rowConstraints);
        }
        grid.setLayoutX(sceneWidth / 2 - 176.6 - grid.getBoundsInLocal().getWidth() / 2);
        grid.setLayoutY(sceneHeight / 2 - 259 - grid.getBoundsInLocal().getHeight() / 2);
        return grid;
    }

    private void loadLevel(GridPane grid, String level){
        Scanner s = new Scanner(this.getClass().getClassLoader().getResourceAsStream(level));
        int rows = grid.getRowCount();
        int cols = grid.getColumnCount();
        String[] brickTypes = new String[]{STANDARD_BRICK, HEAVY_BRICK, EXTRA_HEAVY_BRICK};
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int currInt = s.nextInt();
                if(currInt != 0) {
                    Image image = createImageFromResourceStream(brickTypes[currInt - 1]);
                    ImageView tempImageView = new ImageView(image);
                    scaleImage(tempImageView, ONE_THIRD_SCALEDOWN);
                    grid.setConstraints(tempImageView, j, i);
                    grid.setHalignment(tempImageView, HPos.CENTER);
                    grid.setValignment(tempImageView, VPos.CENTER);
                    grid.getChildren().add(tempImageView);
                }
            }
        }
    }

    private void setStartScreenButtonProperties(Stage stage){
        setButtonHoverPropery(titleScreenPlay, TITLE_SCREEN_PLAY, TITLE_SCREEN_PLAY_HOVER);
        setButtonHoverPropery(titleScreenStore, TITLE_SCREEN_STORE, TITLE_SCREEN_STORE_HOVER);
        setButtonHoverPropery(titleScreenRules, TITLE_SCREEN_RULES, TITLE_SCREEN_RULES_HOVER);
        setButtonSceneTransition(titleScreenRules, rulesScene, stage);
        setButtonSceneTransition(titleScreenPlay, levelScene, stage);
    }

    private void setRulesScreenButtonProperties(Stage stage){
        setButtonHoverPropery(rulesScreenBackButton, BACK_BUTTON, BACK_BUTTON_HOVER);
        setButtonSceneTransition(rulesScreenBackButton, startScene, stage);
    }

    private void setLevelScreenButtonProperties(Stage stage){
        setButtonHoverPropery(levelScreenMenuButton, LEVEL_SCREEN_MENU_BUTTON, LEVEL_SCREEN_MENU_BUTTON_HOVER);
        setButtonSceneTransition(levelScreenMenuButton, startScene, stage);
    }

    private void setButtonHoverPropery(Button button, String oldImageFileName, String newImageFileName){
        button.setOnMouseEntered(value ->  {
            changeButtonImageView(button, newImageFileName);
        });
        button.setOnMouseExited(value ->  {
            changeButtonImageView(button, oldImageFileName);
        });
    }

    private void setButtonSceneTransition(Button button, Scene newScene, Stage stage){
        button.setOnAction(value -> {
            stage.setScene(newScene);
        });
    }

    private void changeButtonImageView(Button button, String imageName){
        //takes in an image file name, makes it an ImageView and sets that to be the button graphic
        Image newImage = createImageFromResourceStream(imageName);
        ImageView newImageView = new ImageView(newImage);
        button.setGraphic(newImageView);
    }

    private Image createImageFromResourceStream(String fileName){
        return new Image(this.getClass().getClassLoader().getResourceAsStream(fileName));
    }

    private void scaleImage(ImageView image, double factor){
        image.setScaleX(factor);
        image.setScaleY(factor);
    }

    private void scaleButton(Button button, double factor){
        button.setScaleX(factor);
        button.setScaleY(factor);
    }

    //Start the program.
    public static void main(String[] args){
        launch(args);
    }
}
