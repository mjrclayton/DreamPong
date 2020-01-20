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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
    public static final String LEVEL_SCREEN_NEXT_LEVEL = "nextlevel.png";
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
    public static final String PU_STICKYPADDLE= "pu_stickypaddle.png";
    public static final String PU_SLOWBALL= "pu_slowball.png";
    public static final String PU_POWERBALL= "pu_powerball.png";
    public static final String PU_LASER= "pu_laser.png";
    public static final String DREAMBUCK = "dreambuck.png";
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
    public static final double STICKY_PADDLE_HOLD_TIME = 2.5;
    private boolean bigPaddleActive;
    private double bigPaddleTimer;
    private boolean slowBallActive;
    private double slowBallTimer;
    private int stickyPaddleHits;
    private double stickyPaddleTimer;
    private boolean stuck;
    private boolean powerBallActive;
    private double powerBallTimer;
    private boolean laserActive;
    private double laserTimer;
    public static final Point2D startingBallVelocity = new Point2D(0, 283);
    private Point2D ballVelocity = startingBallVelocity;
    private double preStickyBallVelocity;
    private Group levelRoot;
    private ImageView levelScreenNextLevel;
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
    private HashSet<ImageView> dbSet = new HashSet<ImageView>();
    private int oldLevel = 1;
    public final int MAX_LEVEL = 5;

    //congrats screen objects
    public static final String CONGRATS_TEXT = "congratulationstext.png";
    public static final String CONGRATS_BUTTON = "congratulationshome.png";
    public static final String CONGRATS_BUTTON_HOVER = "congratulationshomehover.png";
    private ImageView congratsText;
    private Button congratsHome;
    private Scene congratsScene;

    //Game over screen
    public static final String GAME_OVER_TEXT = "gameovertext.png";
    public static final String GAME_OVER_BUTTON = "congratulationshome.png";
    public static final String GAME_OVER_BUTTON_HOVER = "congratulationshomehover.png";
    private ImageView gameOverText;
    private Scene gameOverScene;

    Player player;

    @Override
    public void start (Stage stage){
        player = new Player();
        loadScenes();
        initializeButtons(stage);
        stage.setTitle(STAGE_TITLE);
        stage.setScene(startScene);
        currentScene = startScene;
        stage.show();
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY, stage));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private void loadScenes(){
        startScene = setupStartScene(X_SIZE, Y_SIZE, WHITE_BACKGROUND);
        rulesScene = setupRulesScene(X_SIZE, Y_SIZE, WHITE_BACKGROUND);
        levelScene = setupLevelScene(X_SIZE, Y_SIZE, WHITE_BACKGROUND, getCurrentLevelFileString(player.getLevel()));
        congratsScene = setupCongratsScene(X_SIZE, Y_SIZE, Color.BLACK);
        gameOverScene = setupGameOverScene(X_SIZE, Y_SIZE, Color.BLACK);
    }

    private void initializeButtons(Stage stage){
        setStartScreenButtonProperties(stage);
        setRulesScreenButtonProperties(stage);
        setLevelScreenButtonProperties(stage);
    }


    private void step (double elapsedTime, Stage stage) {
        if(currentScene == levelScene) {
            checkForLevelChange(stage);
            checkLoseLife(stage);
            if(secSinceSceneTransition() > 3) { //time dependent physics so game doesn't start right away
                activeBallMovement(elapsedTime);
            } else {
                if(secSinceSceneTransition() > 1.5 && player.getLevel() > 1){
                    levelRoot.getChildren().remove(levelScreenNextLevel);
                }
                preStartBallMovement(elapsedTime);
            }
            checkPowerUpEffects(); //physics that are always on
            updateBallPaddleCollisions(); //stickyPaddle powerup handled in here
            updatePaddle(elapsedTime);
            updateBallBoxCollisions();
            updateBallBrickCollisions();
            powerUpMovementHandler(elapsedTime);
            dreambuckMovementHandler(elapsedTime);
        }
    }

    private void checkLoseLife(Stage stage){
        if(ball.getBoundsInParent().getMinY() > paddle.getBoundsInParent().getMaxY()){
            player.setLives(player.getLives() - 1);
            currentLives.setText(Integer.toString(player.getLives()));
            ball.setX(paddle.getBoundsInParent().getMinX() + ball.getBoundsInParent().getWidth()/1.65);
            ball.setY(paddle.getBoundsInParent().getMinY() - ball.getBoundsInParent().getHeight()*2.08);
            ballVelocity = startingBallVelocity;
            markerTime = System.currentTimeMillis();
        }
        if(player.getLives() == 0){
            currentScene = gameOverScene;
            stage.setScene(currentScene);
        }
    }

    private void checkForLevelChange(Stage stage){
        if(player.getLevel() > MAX_LEVEL){
            currentScene = congratsScene;
            markerTime = System.currentTimeMillis();
            stage.setScene(currentScene);
        }
        else if(oldLevel != player.getLevel()){
            player.setDreamBucks(player.getDreamBucks()+5);
            levelScene = setupLevelScene(X_SIZE, Y_SIZE, WHITE_BACKGROUND, getCurrentLevelFileString(player.getLevel()));
            oldLevel = player.getLevel();
            currentScene = levelScene;
            markerTime = System.currentTimeMillis();
            resetPowerUps();
            ballVelocity = startingBallVelocity;
            stage.setScene(currentScene);
        }
    }

    private void checkPowerUpEffects(){
        if(bigPaddleActive){
            handleBigPaddlePU();
        }
        if(slowBallActive){
            handleSlowBallPU();
        }
    }

    private void handleBigPaddlePU(){
        bigPaddleTimer -= (1.0/60);
        if(bigPaddleTimer < 0){
            scaleImage(paddle, ONE_THIRD_SCALEDOWN);
            bigPaddleActive = false;
            if(stuck){
                ensureBallWithinPaddle();
                ballVelocity = startingBallVelocity;
            }
        }
    }

    private void handleSlowBallPU(){
        slowBallTimer -= (1.0/60);
        if(slowBallTimer < 0){
            ballVelocity = ballVelocityScale(ballVelocity, 1/SLOWBALL_SLOWDOWN_PERCENT);
            if(stuck){
                preStickyBallVelocity *= (1/SLOWBALL_SLOWDOWN_PERCENT);
            }
            slowBallActive = false;
        }
    }

    private Point2D ballVelocityScale(Point2D point, double scale){
        Double angleInRadians = Math.atan(point.getY()/point.getX());
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
        if(!stuck) {
            ball.setX(ball.getX() - ballVelocity.getX() * elapsedTime);
            ball.setY(ball.getY() - ballVelocity.getY() * elapsedTime);
        } else{
            preStartBallMovement(elapsedTime);
        }
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
        if(brickGrid.getChildren().isEmpty()){
            player.setLevel(player.getLevel() + 1);
        }
    }

    private void updateBallPaddleCollisions(){
        if(ball.getBoundsInParent().intersects(paddle.getBoundsInParent())){
            if(stickyPaddleHits <= 0) {
                handleBallPaddleCollision(ball, paddle);
            } if(stickyPaddleHits > 0){
                stuck = true;
                handleBallStickyPaddleCollision(ball, paddle);
            }
        } else{
            preStickyBallVelocity = Math.sqrt(ballVelocity.getX() * ballVelocity.getX()
                    + ballVelocity.getY() * ballVelocity.getY());
        }
    }

    private void handleBallStickyPaddleCollision(ImageView ball, ImageView paddle){
        ensureBallWithinPaddle();
        double newX, newY;
        if(stickyPaddleTimer > 0){
            newX = 0;
            newY = 0;
            stickyPaddleTimer -= (1.0/60);
        } else{
            player.setScore(player.getScore() + 5);
            currentScore.setText(Integer.toString(player.getScore()));
            newX = 0;
            newY = preStickyBallVelocity;
            stickyPaddleHits--;
            stuck = false;
            stickyPaddleTimer = STICKY_PADDLE_HOLD_TIME;
        }
        ballVelocity = new Point2D(newX, newY);
    }

    private void ensureBallWithinPaddle(){
        if(ball.getBoundsInParent().getMaxX() > paddle.getBoundsInParent().getMaxX()){
            ball.setX(ball.getX() - (ball.getBoundsInParent().getMaxX() - paddle.getBoundsInParent().getMaxX()));
        }
        if(ball.getBoundsInParent().getMinX() < paddle.getBoundsInParent().getMinX()){
            ball.setX(ball.getX() + (paddle.getBoundsInParent().getMinX() - ball.getBoundsInParent().getMinX()));
        }
        if(ball.getBoundsInParent().getMinY() > paddle.getBoundsInParent().getMaxY()){
            ball.setY(ball.getY() + (paddle.getBoundsInParent().getMaxY() - ball.getBoundsInParent().getMinY()));
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

    private void dreambuckMovementHandler(double elapsedTime){
        ArrayList<Node> levelRootCopy = new ArrayList<Node>(levelRoot.getChildren());
        for(Node node : levelRootCopy) {
            Node copy = node;
            if(dbSet.contains(node)){
                ((ImageView)node).setY(((ImageView)node).getY() + POWERUP_SPEED * elapsedTime);
                if(((ImageView)node).getBoundsInParent().intersects(paddle.getBoundsInParent())) {
                    player.setDreamBucks(player.getDreamBucks() + 1);
                    currentDreamBucks.setText(Integer.toString(player.getDreamBucks()));
                    levelRoot.getChildren().remove(copy);
                    dbSet.remove(node);
                }
                if(! brickGrid.getBoundsInParent().contains(node.getBoundsInParent())){
                    levelRoot.getChildren().remove(copy);
                    powerUpMap.remove(node);
                    dbSet.remove(node);
                }
            }
        }
    }

    private void activatePowerUp(PowerUp pu){
        if(pu.getType() == PowerUp.PowerUpType.BIG_PADDLE){
            scaleImage(paddle, 1.5/3);
            if(stuck){
                ensureBallWithinPaddle();
            }
            bigPaddleActive = true;
            bigPaddleTimer = 10;
        } else if(pu.getType() == PowerUp.PowerUpType.STICKY_PADDLE){
            stickyPaddleHits = 3;
            stickyPaddleTimer = STICKY_PADDLE_HOLD_TIME;
        } else if(pu.getType() == PowerUp.PowerUpType.SLOW_BALL){
            if(! slowBallActive) {
                ballVelocity = ballVelocityScale(ballVelocity, SLOWBALL_SLOWDOWN_PERCENT);
            } if(stuck){
                preStickyBallVelocity = preStickyBallVelocity * SLOWBALL_SLOWDOWN_PERCENT;
            }
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
        if(getCenterPoint(ball).getY() < getOverallBrickYBound(brickNode) || getCenterPoint(ball).getY() >
                getOverallBrickYBound(brickNode) + brickNode.getBoundsInParent().getHeight()){
            ballVelocity = new Point2D(ballVelocity.getX(), -ballVelocity.getY());
        } else if (getCenterPoint(ball).getX() < getOverallBrickXBound(brickNode) || getCenterPoint(ball).getX() >
                getOverallBrickXBound(brickNode) + brickNode.getBoundsInParent().getWidth()){
            ballVelocity = new Point2D(-ballVelocity.getX(), ballVelocity.getY());
        }
        hitBrickObject.registerHit();
        if(hitBrickObject.getRemainingHits() == 0) {
            player.setScore(player.getScore() + hitBrickObject.getPoints());
            currentScore.setText(Integer.toString(player.getScore()));
            handleDrops(brickNode, hitBrickObject);
            brickGrid.getChildren().remove(brickNode);
            brickMap.remove(brickNode, hitBrickObject);
        } else {
            updateTexture(brickNode, hitBrickObject);
        }
    }

    private void handleDrops(Node brickNode, Brick hitBrickObject){
        if (hitBrickObject.doesContainPowerUp()) {
            dropPowerUp(brickNode);
        }
        if (hitBrickObject.doesContainDreamBuck()){
            dropDreamBuck(brickNode);
        }
    }

    private void dropPowerUp(Node brickNode){
        PowerUp pu = new PowerUp(player.getOwnedPowerUps());
        Image puImage;
        if(pu.getType() == PowerUp.PowerUpType.BIG_PADDLE){
            puImage = createImageFromResourceStream(PU_BIGPADDLE);
        } else if(pu.getType() == PowerUp.PowerUpType.STICKY_PADDLE){
            puImage = createImageFromResourceStream(PU_STICKYPADDLE);
        } else if(pu.getType() == PowerUp.PowerUpType.SLOW_BALL){
            puImage = createImageFromResourceStream(PU_SLOWBALL);
        } else if(pu.getType() == PowerUp.PowerUpType.POWER_BALL){
            puImage = createImageFromResourceStream(PU_POWERBALL);
        } else {
            puImage = createImageFromResourceStream(PU_LASER);
        }
        ImageView puImageView = new ImageView(puImage);
        formatDrop(brickNode, puImageView);
        powerUpMap.put(puImageView, pu);
        levelRoot.getChildren().add(puImageView);
    }

    private void dropDreamBuck(Node brickNode){
        Image dbImage = createImageFromResourceStream(DREAMBUCK);
        ImageView dbImageView = new ImageView(dbImage);
        formatDrop(brickNode, dbImageView);
        dbSet.add(dbImageView);
        levelRoot.getChildren().add(dbImageView);
    }

    public void formatDrop(Node brickNode, ImageView dropImageView){
        scaleImage(dropImageView, ONE_THIRD_SCALEDOWN);
        dropImageView.setX(getOverallBrickXBound(brickNode) - brickNode.getBoundsInParent().getWidth()*0.8);
        dropImageView.setY(getOverallBrickYBound(brickNode));
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
        double newX, newY;
        double returnAngle = 60 - ((getCenterPoint(ball).getX() - paddle.getBoundsInParent().getMinX()) * PADDLE_BOUNCE_RANGE
                / (paddle.getBoundsInParent().getWidth()));
        newX = velocityMagnitude * Math.sin(returnAngle * (3.14159 / 180)); //degrees -> radians
        newY = velocityMagnitude * Math.cos(returnAngle * (3.14159 / 180)); //degress -> to radians
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
        else if(code.isDigitKey() && Integer.parseInt(code.getChar()) != 0){
            handleDigitInput(code);
        }
        else if(code == KeyCode.R){
            System.out.println("RESET");
            System.out.println(ball.getX());
            System.out.println(ball.getY());
            handleReset();
        }
        else if(code == KeyCode.L){
            handleLCheat();
        }
    }

    private void handleDigitInput(KeyCode code){
        if(Integer.parseInt(code.getChar()) <= MAX_LEVEL) {
            player.setLevel(Integer.parseInt(code.getChar()));
        }
        else{
            player.setLevel(MAX_LEVEL);
        }
        oldLevel = MAX_LEVEL + 1; //this will cause the step function to load a new lever
    }

    private void handleLCheat(){
        player.setLives(player.getLives()+1);
        currentLives.setText(Integer.toString(player.getLives()));
    }

    private void handleReset(){
        ball.setX(paddle.getBoundsInParent().getMinX() + ball.getBoundsInParent().getWidth()/1.65);
        ball.setY(paddle.getBoundsInParent().getMinY() - ball.getBoundsInParent().getHeight()*2.08);
        ballVelocity = startingBallVelocity;
        markerTime = System.currentTimeMillis();
        resetPowerUps();
    }

    private void resetPowerUps(){
        bigPaddleTimer = 0;
        slowBallTimer = 0;
        stickyPaddleHits = 0;
        stickyPaddleTimer = 0;
        stuck = false;
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
        createAndPlaceStartScreenNodes(width, height);
        ImageView[] startScreenImageViews = new ImageView[]{titleScreenTitle, titleScreenCloud1, titleScreenCloud2, titleScreenSignature};
        Button[] startScreenButtons = new Button[]{titleScreenPlay, titleScreenStore, titleScreenRules};
        root.getChildren().addAll(startScreenImageViews);
        root.getChildren().addAll(startScreenButtons);
        Scene scene = new Scene(root, width, height, background);
        return scene;
    }

    private void createAndPlaceStartScreenNodes(int width, int height){
        titleScreenTitle = createImageViewAndPlace(TITLE_SCREEN_TITLE, width, height, ONE_THIRD_SCALEDOWN,
                TRANSLATE_NONE, ONE_SIXTH_TRANSLATE);
        titleScreenCloud1 = createImageViewAndPlace(TITLE_SCREEN_CLOUD, width, height, ONE_THIRD_SCALEDOWN,
                ONE_THIRD_TRANSLATE, TRANSLATE_NONE);
        titleScreenCloud2 = createImageViewAndPlace(TITLE_SCREEN_CLOUD, width, height, ONE_THIRD_SCALEDOWN,
                -ONE_THIRD_TRANSLATE, ONE_THIRD_TRANSLATE);
        titleScreenPlay = createButtonFromImageAndPlace(TITLE_SCREEN_PLAY, width, height, ONE_THIRD_SCALEDOWN,
                TRANSLATE_NONE, -ONE_TWELVETH_TRANSLATE);
        titleScreenStore = createButtonFromImageAndPlace(TITLE_SCREEN_STORE, width, height, ONE_THIRD_SCALEDOWN, TRANSLATE_NONE,
                -(ONE_SIXTH_TRANSLATE + ONE_FIFTIETH_TRANSLATE));
        titleScreenRules = createButtonFromImageAndPlace(TITLE_SCREEN_RULES, width, height, ONE_THIRD_SCALEDOWN, TRANSLATE_NONE,
                -(ONE_SIXTH_TRANSLATE + ONE_TWELVETH_TRANSLATE + (2*ONE_FIFTIETH_TRANSLATE)));
        titleScreenSignature = createImageViewAndPlace(TITLE_SCREEN_SIGNATURE, width, height, ONE_THIRD_SCALEDOWN,
                TRANSLATE_NONE, -ALMOST_HALF_TRANSLATE);
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

    private Scene setupCongratsScene(int width, int height, Paint background){
        Group root = new Group();
        congratsText = createImageViewAndPlace(CONGRATS_TEXT, width, height, ONE_THIRD_SCALEDOWN, TRANSLATE_NONE,
                TRANSLATE_NONE);
        root.getChildren().add(congratsText);
        Scene scene = new Scene(root, width, height, background);
        return scene;
    }

    private Scene setupGameOverScene(int width, int height, Paint background){
        Group root = new Group();
        gameOverText = createImageViewAndPlace(GAME_OVER_TEXT, width, height, ONE_THIRD_SCALEDOWN, TRANSLATE_NONE,
                TRANSLATE_NONE);
        root.getChildren().add(gameOverText);
        Scene scene = new Scene(root, width, height, background);
        return scene;
    }

    private Scene setupLevelScene(int width, int height, Paint background, String level){
        levelRoot = new Group();
        createAndPlaceLevelScreenNodes(width, height);
        loadLevel(brickGrid, level);
        Label[] levelScreenLabels = new Label[]{currentLevel, currentScore, currentDreamBucks, currentLives};
        ImageView[] levelScreenImages = new ImageView[]{levelScreenLayout, paddle, ball};
        levelRoot.getChildren().addAll(levelScreenImages);
        levelRoot.getChildren().add(levelScreenMenuButton);
        levelRoot.getChildren().add(brickGrid);
        levelRoot.getChildren().addAll(levelScreenLabels);
        if(player.getLevel() > 1){
            levelRoot.getChildren().add(levelScreenNextLevel);
        }
        Scene scene = new Scene(levelRoot, width, height, background);
        scene.setOnKeyPressed(e -> handleKeyPressedInput(e.getCode()));
        scene.setOnKeyReleased(e -> handleKeyReleasedInput(e.getCode()));
        return scene;
    }

    private void createAndPlaceLevelScreenNodes(int width, int height){
        levelScreenLayout = createImageViewAndPlace(LEVEL_SCREEN_LAYOUT, width, height, ONE_THIRD_SCALEDOWN, TRANSLATE_NONE,
                TRANSLATE_NONE);
        levelScreenMenuButton = createButtonFromImageAndPlace(LEVEL_SCREEN_MENU_BUTTON, width, height, ONE_THIRD_SCALEDOWN*.66,
                -(ONE_THIRD_TRANSLATE + 1.0/100), ONE_THIRD_TRANSLATE);
        levelScreenNextLevel = createImageViewAndPlace(LEVEL_SCREEN_NEXT_LEVEL, width, height, ONE_THIRD_SCALEDOWN,
                TRANSLATE_NONE, TRANSLATE_NONE);
        brickGrid = createAndAlignBrickGrid(width, height);
        paddle = createImageViewAndPlace(player.getPaddle().getPaddleImg(), width, height, ONE_THIRD_SCALEDOWN, (1.0/9.0), -(1.5/5.0));
        ball = createImageViewAndPlace(player.getBall().getBallImg(), width, height, ONE_THIRD_SCALEDOWN, (1.0/9.0), -(1.387/5.0));
        currentLives = createAndAlignLabel(player.getLives(), 340, 312);
        currentLevel = createAndAlignLabel(player.getLevel(), 340, 404);
        currentScore = createAndAlignLabel(player.getScore(), 330, 499);
        currentDreamBucks = createAndAlignLabel(player.getDreamBucks(), 195, 552);
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
                    alignProperlyInCell(tempImageView, grid, j, i);
                    grid.getChildren().add(tempImageView);
                }
            }
        }
    }

    private void alignProperlyInCell(ImageView tempImageView, GridPane grid, int col, int row){
        grid.setConstraints(tempImageView, col, row);
        grid.setHalignment(tempImageView, HPos.CENTER);
        grid.setValignment(tempImageView, VPos.CENTER);
    }

    private void setStartScreenButtonProperties(Stage stage){
        setButtonHoverPropery(titleScreenPlay, TITLE_SCREEN_PLAY, TITLE_SCREEN_PLAY_HOVER);
        setButtonHoverPropery(titleScreenStore, TITLE_SCREEN_STORE, TITLE_SCREEN_STORE_HOVER);
        setButtonHoverPropery(titleScreenRules, TITLE_SCREEN_RULES, TITLE_SCREEN_RULES_HOVER);
        setButtonSceneTransition(titleScreenRules, rulesScene, stage);
        setButtonSceneTransition(titleScreenPlay, levelScene, stage);
    }

    private String getCurrentLevelFileString(int playerLevel){
        return new String("Level" + Integer.toString(playerLevel) + ".txt");
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

    private void setButtonSceneTransitionReset(Button button, Scene newScene, Stage stage){
        button.setOnAction(value -> {
            stage.setScene(newScene);
            currentScene = newScene;
            markerTime = System.currentTimeMillis();
            player.setLevel(1);
            resetPowerUps();
            ballVelocity = startingBallVelocity;
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
