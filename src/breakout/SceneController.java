package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class SceneController extends Application {
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

    //Overall Objects For Animation
    private Scene currentScene;
    private long markerTime;

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
    public static final String HEAVY_BRICK_1_HIT = "heavybrick_after1hit.png";
    public static final String EXTRA_HEAVY_BRICK = "extraheavybrick.png";
    public static final String EXTRA_HEAVY_BRICK_1_HIT = "extraheavybrick_after1hit.png";
    public static final String EXTRA_HEAVY_BRICK_2_HITS = "extraheavybrick_after2hits.png";
    public static final String EXTRA_HEAVY_BRICK_3_HITS = "extraheavybrick_after3hits.png";
    public static final String PU_BIGPADDLE= "pu_bigpaddle.png";
    public static final String PU_MULTIBALL= "pu_multiball.png";
    public static final String PU_SLOWBALL= "pu_slowball.png";
    public static final String PU_POWERBALL= "pu_powerball.png";
    public static final String PU_LASER= "pu_laser.png";
    public static final String LEVEL_1 = "Level1.txt";
    public static final String LEVEL_2 = "Level2.txt";
    public static final String LEVEL_3 = "Level3.txt";
    public static final String LEVEL_4 = "Level4.txt";
    public static final String LEVEL_5 = "Level5.txt";
    public static final double MAX_PADDLE_X = 153.8;
    public static final double MIN_PADDLE_X = -42.7;
    public static final double PADDLE_BOUNCE_RANGE = 120;
    public static final int POWERUP_SPEED = 100;
    public static final double SLOWBALL_SLOWDOWN_PERCENT = 0.75;
    private boolean bigPaddleActive;
    private double bigPaddleTimer;
    private boolean slowBallActive;
    private double slowBallTimer;
    private boolean powerBallActive;
    private double powerBallTimer;
    private boolean laserActive;
    private double laserTimer;
    private Point2D ballVelocity = new Point2D(200, 200);
    private Group levelRoot;
    private ImageView levelScreenLayout;
    private ImageView paddle;
    private ImageView ball;
    private Button levelScreenMenuButton;
    private GridPane brickGrid;
    private Label currentLevel;
    private Label currentScore;
    private Label currentLives;
    private Label currentDreamBucks;
    private int paddleSpeed = 0;
    private HashMap<ImageView, Brick> brickMap = new HashMap<ImageView, Brick>();
    private HashMap<ImageView, PowerUp> powerUpMap = new HashMap<ImageView, PowerUp>();

    Player player;

    @Override
    public void start (Stage stage){
        /*
        Need to follow 3 steps within the start method
            1)Prepare a scene graph with required notes
            2)Prepare a scene with the required dimensions and add the scene graph to it
            3)Prepare a stage and add the scene to the stage and display the contents of the stage
         */
        player = new Player();

        //Steps 1) and 2)
        startScene = setupStartScene(X_SIZE, Y_SIZE, WHITE_BACKGROUND);
        rulesScene = setupRulesScene(X_SIZE, Y_SIZE, WHITE_BACKGROUND);
        levelScene = setupLevelScene(X_SIZE, Y_SIZE, WHITE_BACKGROUND, LEVEL_4, player);

        //Set button properties after creating scene because of null scene bugs that happened if set them in
        //scene constructing methods
        setStartScreenButtonProperties(stage);
        setRulesScreenButtonProperties(stage);
        setLevelScreenButtonProperties(stage);

        //Step 3)
        stage.setTitle(STAGE_TITLE);
        stage.setScene(startScene);
        currentScene = startScene;
        stage.show();

        // attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private void handleBigPaddlePU(){
        bigPaddleTimer -= (1.0/60);
        if(bigPaddleTimer < 0){
            scaleImage(paddle, ONE_THIRD_SCALEDOWN);
            bigPaddleActive = false;
        }
    }

    private void handleSlowBallPU(){
        slowBallTimer -= (1.0/60);
        if(slowBallTimer < 0){
            ballVelocity = ballVelocityScale(ballVelocity, 1/SLOWBALL_SLOWDOWN_PERCENT);
            slowBallActive = false;
        }
    }

    private Point2D ballVelocityScale(Point2D point, double scale){
        Double angleInRadians = Math.atan(point.getY()/point.getX());
        System.out.println(angleInRadians * 1/(3.14158/180));
        double velocityMagnitude = Math.sqrt(point.getX() * point.getX()
                + ballVelocity.getY() * ballVelocity.getY());
        double newVelocityMagnitude = velocityMagnitude * scale;
        double newX = Math.cos(angleInRadians) * newVelocityMagnitude;
        double newY = Math.sin(angleInRadians) * newVelocityMagnitude;
        if(point.getX() < 0 && newX > 0 || point.getX() > 0 && newX < 0){
            newX = -newX;
        }
        if(point.getY() < 0 && newY > 0 || point.getY() > 0 && newY < 0){
            newY = -newY;
        }
        return new Point2D(newX, newY);
    }

    private void activeBallMovement(double elapsedTime){
        ball.setX(ball.getX() - ballVelocity.getX() * elapsedTime);
        ball.setY(ball.getY() - ballVelocity.getY() * elapsedTime);
    }

    private void preStartBallMovement(double elapsedTime){
        if(paddleMovementInBounds(paddle, paddleSpeed)){
            ball.setX(ball.getX() + paddleSpeed * elapsedTime);
        }
    }

    private void updatePaddle(double elapsedTime){
        if(paddleMovementInBounds(paddle, paddleSpeed)) {
            paddle.setX(paddle.getX() + paddleSpeed * elapsedTime);
        }
    }

    private void updateBallBrickCollisions(){
        for(Node brick : brickGrid.getChildren()){
            if(ball.getBoundsInParent().intersects(getOverallBrickXBound(brick), getOverallBrickYBound(brick),
                    brick.getBoundsInParent().getWidth(), brick.getBoundsInParent().getHeight())){
                handleBallBrickCollision(ball, brick);
                break;
            }
        }
    }

    private void updateBallPaddleCollisions(){
        if(ball.getBoundsInParent().intersects(paddle.getBoundsInParent())){
            handleBallPaddleCollision(ball, paddle);
        }
    }

    private void updateBallBoxCollisions(){
        if(! brickGrid.getBoundsInParent().contains(ball.getBoundsInParent())){
            handleBallBoxCollision(ball, brickGrid);
        }
    }

    private void powerUpMovementHandler(double elapsedTime){
        //create levelRootCopy to avoid concurrent modification exception
        ArrayList<Node> levelRootCopy = new ArrayList<Node>(levelRoot.getChildren());
        for(Node node : levelRootCopy){
            Node copy = node;
            if(powerUpMap.containsKey(node)){
                ((ImageView)node).setY(((ImageView)node).getY() + POWERUP_SPEED * elapsedTime);
                if(((ImageView)node).getBoundsInParent().intersects(paddle.getBoundsInParent())){
                    activatePowerUp(powerUpMap.get(node));
                    player.setScore(player.getScore() + powerUpMap.get(node).getPoints());
                    currentScore.setText(Integer.toString(player.getScore()));
                    levelRoot.getChildren().remove(copy);
                    powerUpMap.remove(node);
                }
                if(! brickGrid.getBoundsInParent().contains(node.getBoundsInParent())){
                    levelRoot.getChildren().remove(copy);
                    powerUpMap.remove(node);
                }
            }
        }
    }


    private void step (double elapsedTime) {
        if(currentScene == levelScene) {
            //powerups affect checks
            if(bigPaddleActive){
                handleBigPaddlePU();
            }
            if(slowBallActive){
                handleSlowBallPU();
            }
            //time dependent physics so game doesn't start right away
            if(secSinceSceneTransition() > 2) {
                activeBallMovement(elapsedTime);
            } else {
                preStartBallMovement(elapsedTime);
            }
            //physics that are always on
            updatePaddle(elapsedTime);
            updateBallPaddleCollisions();
            updateBallBoxCollisions();
            updateBallBrickCollisions();
            powerUpMovementHandler(elapsedTime);

        }
    }

    private void activatePowerUp(PowerUp pu){
        if(pu.getType() == PowerUp.PowerUpType.BIG_PADDLE){
            scaleImage(paddle, 1.5/3);
            bigPaddleActive = true;
            bigPaddleTimer = 10;
        } else if(pu.getType() == PowerUp.PowerUpType.MULTI_BALL){

        } else if(pu.getType() == PowerUp.PowerUpType.SLOW_BALL){
            ballVelocity = ballVelocityScale(ballVelocity, SLOWBALL_SLOWDOWN_PERCENT);
            slowBallActive = true;
            slowBallTimer = 10;
        } else if(pu.getType() == PowerUp.PowerUpType.POWER_BALL){
            powerBallActive = true;
            powerBallTimer = 7;
        } else {
            laserActive = true;
            laserTimer = 20;
        }
    }

    //two methods below allow for entire scene brick positioning rather than just relative to gridPane
    private double getOverallBrickXBound(Node brick){
        return brick.getBoundsInParent().getMinX() + brickGrid.getBoundsInParent().getMinX();
    }

    private double getOverallBrickYBound(Node brick){
        return brick.getBoundsInParent().getMinY() + brickGrid.getBoundsInParent().getMinY();
    }

    private double secSinceSceneTransition(){
        return (System.currentTimeMillis() - markerTime) / 1000.0;
    }

    private void handleBallBrickCollision(ImageView ball, Node brickNode){
        Brick hitBrickObject = brickMap.get(brickNode);
        //bounce section
        if(getCenterPoint(ball).getY() < getOverallBrickYBound(brickNode) || getCenterPoint(ball).getY() >
                getOverallBrickYBound(brickNode) + brickNode.getBoundsInParent().getHeight()){
            ballVelocity = new Point2D(ballVelocity.getX(), -ballVelocity.getY());
        } else if (getCenterPoint(ball).getX() < getOverallBrickXBound(brickNode) || getCenterPoint(ball).getX() >
                getOverallBrickXBound(brickNode) + brickNode.getBoundsInParent().getWidth()){
            ballVelocity = new Point2D(-ballVelocity.getX(), ballVelocity.getY());
        }
        //destroy brick, powerup section
        hitBrickObject.registerHit();
        if(hitBrickObject.getRemainingHits() == 0) {
            player.setScore(player.getScore() + hitBrickObject.getPoints());
            currentScore.setText(Integer.toString(player.getScore()));
            if (hitBrickObject.doesContainPowerUp()) {
                dropPowerUp(brickNode);
                //START HERE, DROP POWER UP INTO THE GAME, FALLING FROM WHERE BRICK WAS
            }
            brickGrid.getChildren().remove(brickNode);
            brickMap.remove(brickNode);
        } else {
            updateTexture(brickNode, hitBrickObject);
        }
    }

    private void dropPowerUp(Node brickNode){
        PowerUp pu = new PowerUp(player.getOwnedPowerUps());
        Image puImage;
        if(pu.getType() == PowerUp.PowerUpType.BIG_PADDLE){
            puImage = createImageFromResourceStream(PU_BIGPADDLE);
        } else if(pu.getType() == PowerUp.PowerUpType.MULTI_BALL){
            puImage = createImageFromResourceStream(PU_MULTIBALL);
        } else if(pu.getType() == PowerUp.PowerUpType.SLOW_BALL){
            puImage = createImageFromResourceStream(PU_SLOWBALL);
        } else if(pu.getType() == PowerUp.PowerUpType.POWER_BALL){
            puImage = createImageFromResourceStream(PU_POWERBALL);
        } else {
            puImage = createImageFromResourceStream(PU_LASER);
        }
        ImageView puImageView = new ImageView(puImage);
        scaleImage(puImageView, ONE_THIRD_SCALEDOWN);
        puImageView.setX(getOverallBrickXBound(brickNode) - brickNode.getBoundsInParent().getWidth()*0.8);
        puImageView.setY(getOverallBrickYBound(brickNode));
        powerUpMap.put(puImageView, pu);
        levelRoot.getChildren().add(puImageView);
    }

    private void updateTexture(Node brickNode, Brick hitBrickObject){
        Image newImage;
        if(hitBrickObject.getType() == Brick.BrickType.HEAVY){
            newImage = createImageFromResourceStream(HEAVY_BRICK_1_HIT);
            ((ImageView) brickNode).setImage(newImage);
        }
        else if(hitBrickObject.getType() == Brick.BrickType.EXTRA_HEAVY){
            if(hitBrickObject.getRemainingHits() == 3){
                newImage = createImageFromResourceStream(EXTRA_HEAVY_BRICK_1_HIT);
            } else if (hitBrickObject.getRemainingHits() == 2){
                newImage = createImageFromResourceStream(EXTRA_HEAVY_BRICK_2_HITS);
            } else {
                newImage = createImageFromResourceStream(EXTRA_HEAVY_BRICK_3_HITS);
            }
            ((ImageView) brickNode).setImage(newImage);
        }
    }

    private void handleBallBoxCollision(ImageView ball, GridPane grid){
        if(ball.getBoundsInParent().getMinY() <= grid.getLayoutY() ||
                ball.getBoundsInParent().getMaxY() >= grid.getMaxHeight()){
            ballVelocity = new Point2D(ballVelocity.getX(), -ballVelocity.getY());
        }
        if(ball.getBoundsInParent().getMinX() <= grid.getLayoutX() ||
                ball.getBoundsInParent().getMaxX() >= grid.getMaxWidth()){
            ballVelocity = new Point2D(- ballVelocity.getX(), ballVelocity.getY());
        }
    }

    private void handleBallPaddleCollision(ImageView ball, ImageView paddle){
        player.setScore(player.getScore() + 5);
        currentScore.setText(Integer.toString(player.getScore()));
        //goal is to give the player more control by altering the return angle of the ball to be dependent on
        //where the ball hits the paddle
        double velocityMagnitude = Math.sqrt(ballVelocity.getX() * ballVelocity.getX()
                + ballVelocity.getY() * ballVelocity.getY());
        double returnAngle = 60 - ((getCenterPoint(ball).getX() - paddle.getBoundsInParent().getMinX()) * PADDLE_BOUNCE_RANGE
                / (paddle.getBoundsInParent().getWidth()));
        double newX = velocityMagnitude * Math.sin(returnAngle * (3.14159/180)); //degrees -> radians
        double newY = velocityMagnitude * Math.cos(returnAngle * (3.14159/180)); //degress -> to radians
        ballVelocity = new Point2D(newX, newY);
    }

    private Point2D getCenterPoint(ImageView image){
        double x = ball.getBoundsInParent().getMinX() + ball.getBoundsInParent().getWidth()/2.0;
        double y = ball.getBoundsInParent().getMinY() + ball.getBoundsInParent().getHeight()/2.0;
        return new Point2D(x, y);
    }

    private double getFirstThirdThreshold(ImageView image){
        return image.getBoundsInParent().getMinX() + (1.0/3.0) * image.getBoundsInParent().getWidth();
    }

    private double getSecondThirdThreshold(ImageView image){
        return image.getBoundsInParent().getMaxX() - (1.0/3.0) * image.getBoundsInParent().getWidth();
    }

    // What to do each time a key is pressed
    private void handleKeyPressedInput (KeyCode code) {
        if (code == KeyCode.D) {
            paddleSpeed = 150;
        }
        else if (code == KeyCode.A) {
            paddleSpeed = -150;
        }
    }

    private void handleKeyReleasedInput (KeyCode code) {
        if (code == KeyCode.D && paddleSpeed > 0) {
            paddleSpeed = 0;
        }
        else if (code == KeyCode.A && paddleSpeed < 0) {
            paddleSpeed = 0;
        }
    }

    private boolean paddleMovementInBounds(ImageView paddle, int paddleSpeed){
        double paddleMinX = paddle.getBoundsInParent().getMinX();
        double paddleMaxX = paddle.getBoundsInParent().getMaxX();
        double gridMinX = brickGrid.getBoundsInParent().getMinX();
        double gridMaxX = brickGrid.getBoundsInParent().getMinX() + brickGrid.getBoundsInParent().getWidth();
        return (paddleMaxX < gridMaxX && paddleMinX > gridMinX || paddleMaxX >= gridMaxX
                && paddleSpeed < 0 || paddleMinX <= gridMinX && paddleSpeed > 0);
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

    private Scene setupLevelScene(int width, int height, Paint background, String level, Player player){
        //TOO LONG
        levelRoot = new Group();
        levelScreenLayout = createImageViewAndPlace(LEVEL_SCREEN_LAYOUT, width, height, ONE_THIRD_SCALEDOWN, TRANSLATE_NONE,
                TRANSLATE_NONE);
        levelScreenMenuButton = createButtonFromImageAndPlace(LEVEL_SCREEN_MENU_BUTTON, width, height, ONE_THIRD_SCALEDOWN*.66,
                -(ONE_THIRD_TRANSLATE + 1.0/100), ONE_THIRD_TRANSLATE);
        brickGrid = createAndAlignBrickGrid(width, height);
        paddle = createImageViewAndPlace(player.getPaddle().getPaddleImg(), width, height, ONE_THIRD_SCALEDOWN, (1.0/9.0), -(1.5/5.0));
        ball = createImageViewAndPlace(player.getBall().getBallImg(), width, height, ONE_THIRD_SCALEDOWN, (1.0/9.0), -(1.387/5.0));
        loadLevel(brickGrid, level);
        currentLives = createAndAlignLabel(player.getLives(), 340, 312);
        currentLevel = createAndAlignLabel(player.getLevel(), 340, 404);
        currentScore = createAndAlignLabel(player.getScore(), 330, 499);
        currentDreamBucks = createAndAlignLabel(player.getDreamBucks(), 195, 552);

        Label[] levelScreenLabels = new Label[]{currentLevel, currentScore, currentDreamBucks, currentLives};
        ImageView[] levelScreenImages = new ImageView[]{levelScreenLayout, paddle, ball};

        levelRoot.getChildren().addAll(levelScreenImages);
        levelRoot.getChildren().add(levelScreenMenuButton);
        levelRoot.getChildren().add(brickGrid);
        levelRoot.getChildren().addAll(levelScreenLabels);

        Scene scene = new Scene(levelRoot, width, height, background);
        scene.setOnMouseClicked(e -> {
            System.out.println("X: " + e.getX());
            System.out.println("Y: " + e.getY());
        });
        scene.setOnKeyPressed(e -> handleKeyPressedInput(e.getCode()));
        scene.setOnKeyReleased(e -> handleKeyReleasedInput(e.getCode()));
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

    private Label createAndAlignLabel(int score, double xCord, double yCord){
        String str = Integer.toString(score);
        Label label = new Label(str);
        label.setFont(new Font("Arial", 26));
        label.relocate(xCord, yCord);
        return label;
    }

    private GridPane createAndAlignBrickGrid(int sceneWidth, int sceneHeight){
        GridPane grid = new GridPane();
        grid.setMaxSize(265,498);
        grid.setMinSize(265,498);
        //grid.setGridLinesVisible(true);
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
        Brick.BrickType[] brickTypes = new Brick.BrickType[]{Brick.BrickType.STANDARD, Brick.BrickType.HEAVY,
                Brick.BrickType.EXTRA_HEAVY};
        String[] brickTypeImages = new String[]{STANDARD_BRICK, HEAVY_BRICK, EXTRA_HEAVY_BRICK};
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int currInt = s.nextInt();
                if(currInt != 0) {
                    Image image = createImageFromResourceStream(brickTypeImages[currInt - 1]);
                    ImageView tempImageView = new ImageView(image);
                    scaleImage(tempImageView, ONE_THIRD_SCALEDOWN);
                    brickMap.put(tempImageView, new Brick(brickTypes[currInt - 1]));
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
            currentScene = newScene;
            markerTime = System.currentTimeMillis();
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
