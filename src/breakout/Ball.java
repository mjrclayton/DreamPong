package breakout;

public class Ball {
    public enum BallType {STANDARD, MOON, DREAMCATCHER};

    private BallType type = BallType.STANDARD;

    public BallType getBallType(){
        return this.type;
    }

    public void setBallType(BallType type){
        this.type = type;
    }
}
