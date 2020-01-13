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
import javafx.stage.Stage;
import javafx.util.Duration;

public class FirstClass extends Application {
    //Constant magic numbers and other resources to be used in game
    public static final int X_SIZE = 400;
    public static final int Y_SIZE = 600;
    public static final Paint START_BACKGROUND = Color.WHITE;
    public static final String STAGE_TITLE = "DreamPong";
    public static final String TITLE_SCREEN_TITLE = "title.png";

    //Other things/objects needed for game
    private Scene startScene;
    private ImageView titleScreenTitle;

    @Override
    public void start (Stage stage){
        /*
        Need to follow 3 steps within the start method
            1)Prepare a scene graph with required notes
            2)Prepare a scene with the required dimensions and add the scene graph to it
            3)Prepare a stage and add the scene to the stage and display the contents of the stage
         */

        //Steps 1) and 2)
        startScene = setupScene(X_SIZE, Y_SIZE, START_BACKGROUND);
        //Step 3)
        stage.setTitle(STAGE_TITLE);
        stage.setScene(startScene);
        stage.show();
    }

    private Scene setupScene(int width, int height, Paint background){
        //create new top level group collection to organize the things in the scene - the Scene Graph
        Group root = new Group(); //likely want to change to chart b/c of grid nature of breakout games

        Image title = new Image(this.getClass().getClassLoader().getResourceAsStream(TITLE_SCREEN_TITLE));

        titleScreenTitle = new ImageView(title);
        titleScreenTitle.setX(width / 2 - titleScreenTitle.getBoundsInLocal().getWidth() / 2);
        titleScreenTitle.setY(height / 2 - (height/6) - titleScreenTitle.getBoundsInLocal().getHeight() / 2);
        titleScreenTitle.setScaleX(0.5);
        titleScreenTitle.setScaleY(0.5);
        root.getChildren().add(titleScreenTitle);
        //create a place to see the nodes - the Scene
        Scene scene = new Scene(root, width, height, background);


        return scene;
    }

    /**
     * Start the program.
     */
    public static void main(String[] args){
        launch(args);
    }
}
