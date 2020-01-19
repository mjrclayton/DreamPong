package breakout;

public class Ball {
    public enum BallType {STANDARD, MOON, DREAMCATCHER};
    public static final String STANDARD_BALL = "standardball.png";
    public static final String MOON_BALL = "moonball.png";
    public static final String DREAMCATCHER_BALL = "dreamcatcherball.png";

    private BallType type = BallType.STANDARD;
    private String BallImg = STANDARD_BALL;

    public BallType getBallType(){
        return this.type;
    }

    public void setBallType(BallType type){
        if(type == BallType.STANDARD){
            this.BallImg = STANDARD_BALL;
        } else if(type == BallType.MOON){
            this.BallImg = MOON_BALL;
        } else{
            this.BallImg = DREAMCATCHER_BALL;
        }
        this.type = type;
    }

    public String getBallImg(){
        return this.BallImg;
    }
}
