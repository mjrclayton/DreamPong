package breakout;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {
    private int dreamBucks = 0;
    private int score = 0;
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
        this.score = score;
    }

    public int getDreamBucks(){
        return this.dreamBucks;
    }

    public void setDreamBucks(int dreamBucks){
        this.dreamBucks = dreamBucks;
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
