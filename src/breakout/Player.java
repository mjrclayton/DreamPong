package breakout;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {
    public final int MAX_LEVEL = 5;
    public final int MAX_SCORE = 9999;
    public final int MAX_DREAMBUCKS = 999;

    private int dreamBucks = 0;
    private int score = 0;
    private int level = 1;
    private int lives = 3;
    private Ball ball = new Ball();
    private Paddle paddle = new Paddle();
    private ArrayList<PowerUp.PowerUpType> ownedPowerUps = new ArrayList<PowerUp.PowerUpType>();
    private ArrayList<Paddle.PaddleType> ownedPaddles = new ArrayList<Paddle.PaddleType>();
    private ArrayList<Ball.BallType> ownedBalls = new ArrayList<Ball.BallType>();

    Player(){
        ownedPowerUps.add(PowerUp.PowerUpType.BIG_PADDLE);
        ownedPowerUps.add(PowerUp.PowerUpType.MULTI_BALL);
        ownedPowerUps.add(PowerUp.PowerUpType.SLOW_BALL);
        ownedBalls.add(Ball.BallType.STANDARD);
        ownedPaddles.add(Paddle.PaddleType.STANDARD);
    }

    public int getScore(){
        return this.score;
    }

    public void setScore(int score){
        this.score = score <= MAX_SCORE? score : MAX_SCORE;
    }

    public int getDreamBucks(){
        return this.dreamBucks;
    }

    public void setDreamBucks(int dreamBucks){
        this.dreamBucks = dreamBucks <= MAX_DREAMBUCKS? dreamBucks : MAX_DREAMBUCKS;
    }

    public int getLevel(){
        return this.level;
    }

    public void setLevel(int level){
        this.level = level <= MAX_LEVEL? level : MAX_LEVEL;
    }

    public int getLives(){
        return this.lives;
    }

    public void setLives(int lives){
        this.lives = lives;
    }

    public Paddle getPaddle() {
        return this.paddle;
    }

    public Ball getBall(){
        return this.ball;
    }

    public ArrayList<PowerUp.PowerUpType> getOwnedPowerUps(){
        return this.ownedPowerUps;
    }

    public void addPowerUpType(PowerUp.PowerUpType type){
        this.ownedPowerUps.add(type);
    }

    public ArrayList<Ball.BallType> getOwnedBalls(){
        return this.ownedBalls;
    }

    public void addBallType(Ball.BallType type){
        this.ownedBalls.add(type);
    }

    public ArrayList<Paddle.PaddleType> getOwnedPaddles(){
        return this.ownedPaddles;
    }

    public void addPaddleType(Paddle.PaddleType type){
        this.ownedPaddles.add(type);
    }
}
