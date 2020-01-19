package breakout;

import java.util.ArrayList;
import java.util.Random;

public class PowerUp {
    public enum PowerUpType{BIG_PADDLE, SLOW_BALL, MULTI_BALL, POWER_BALL, LASER};
    private int points = 30;

    private PowerUpType type;

    PowerUp(ArrayList<PowerUpType> ownedPowerUps){
        Random random = new Random();
        int rand = random.nextInt(ownedPowerUps.size());
        this.type = ownedPowerUps.get(rand);
    }

    public PowerUpType getType(){
        return this.type;
    }

    public int getPoints(){
        return this.points;
    }
}
