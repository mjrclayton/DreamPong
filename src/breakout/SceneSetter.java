package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FirstClass extends Application {
    //Constant magic numbers and other resources to be used in game
    public static final int X_SIZE = 400;
    public static final int Y_SIZE = 600;
    public static final double ONE_THIRD_SCALEDOWN = (1.0/3.0);
    public static final double ONE_THIRD_TRANSLATE = (1.0/3.0);
    public static final double ONE_SIXTH_TRANSLATE = (1.0/6.0);
    public static final double ONE_FIFTIETH_TRANSLATE = (1.0/50.0);
    public static final double ALMOST_HALF_TRANSLATE = (24.0/50.0);
    public static final double ONE_TWELVETH_TRANSLATE = (1.0/12.0);
    public static final double TRANSLATE_NONE = 0;
    public static final Paint START_BACKGROUND = Color.WHITE;
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


    //Other things/objects needed for game

    //Start Screen Objects
    private Scene startScene;
    private ImageView titleScreenTitle;
    private ImageView titleScreenCloud1;
    private ImageView titleScreenCloud2;
    private Button titleScreenPlay;
    private Button titleScreenRules;
    private Button titleScreenStore;
    private ImageView titleScreenSignature;


    @Override
    public void start (Stage stage){
        /*
        Need to follow 3 steps within the start method
            1)Prepare a scene graph with required notes
            2)Prepare a scene with the required dimensions and add the scene graph to it
            3)Prepare a stage and add the scene to the stage and display the contents of the stage
         */

        //Steps 1) and 2)
        startScene = setupStartScene(X_SIZE, Y_SIZE, START_BACKGROUND);
        //titleScreenRules.mouseover

        setTitleButtonHoverProperties(titleScreenPlay, TITLE_SCREEN_PLAY, TITLE_SCREEN_PLAY_HOVER);
        setTitleButtonHoverProperties(titleScreenStore, TITLE_SCREEN_STORE, TITLE_SCREEN_STORE_HOVER);
        setTitleButtonHoverProperties(titleScreenRules, TITLE_SCREEN_RULES, TITLE_SCREEN_RULES_HOVER);
        
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
                -ONE_SIXTH_TRANSLATE - ONE_FIFTIETH_TRANSLATE);
        titleScreenRules = createButtonFromImageAndPlace(TITLE_SCREEN_RULES, width, height, ONE_THIRD_SCALEDOWN, TRANSLATE_NONE,
                -ONE_SIXTH_TRANSLATE - ONE_TWELVETH_TRANSLATE - (2*ONE_FIFTIETH_TRANSLATE));
        titleScreenSignature = createImageViewAndPlace(TITLE_SCREEN_SIGNATURE, width, height, ONE_THIRD_SCALEDOWN, TRANSLATE_NONE, -ALMOST_HALF_TRANSLATE);

        ImageView[] startScreenImageViews = new ImageView[]{titleScreenTitle, titleScreenCloud1, titleScreenCloud2, titleScreenSignature};
        Button[] startScreenButtons = new Button[]{titleScreenPlay, titleScreenStore, titleScreenRules};

        root.getChildren().addAll(startScreenImageViews);
        root.getChildren().addAll(startScreenButtons);
        //create a place to see the nodes - the Scene
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

    private void setTitleButtonHoverProperties(Button button, String oldImageFileName, String newImageFileName){
        button.setOnMouseEntered(value ->  {
            changeButtonImageView(button, newImageFileName);
        });
        button.setOnMouseExited(value ->  {
            changeButtonImageView(button, oldImageFileName);
        });
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

    /**
     * Start the program.
     */
    public static void main(String[] args){
        launch(args);
    }
}
