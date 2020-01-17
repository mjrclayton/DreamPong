package breakout;

public class Paddle {
    public enum PaddleType {STANDARD, WINGS, BOOK};

    private PaddleType type = PaddleType.STANDARD;

    public PaddleType getPaddleType(){
        return this.type;
    }

    public void setPaddleType(PaddleType type){
        this.type = type;
    }
}
