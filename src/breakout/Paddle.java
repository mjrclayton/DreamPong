package breakout;

public class Paddle {
    public enum PaddleType {STANDARD, WINGS, BOOK};
    public static final String STANDARD_PADDLE = "standardpaddle.png";
    public static final String WINGS_PADDLE = "wingspaddle.png";
    public static final String BOOK_PADDLE = "bookpaddle.png";

    private PaddleType type = PaddleType.STANDARD;
    private String paddleImg = STANDARD_PADDLE;

    public PaddleType getPaddleType(){
        return this.type;
    }

    public void setPaddleType(PaddleType type){
        if(type == PaddleType.STANDARD){
            this.paddleImg = STANDARD_PADDLE;
        } else if(type == PaddleType.BOOK){
            this.paddleImg = BOOK_PADDLE;
        } else{
            this.paddleImg = WINGS_PADDLE;
        }
        this.type = type;
    }

    public String getPaddleImg(){
        return this.paddleImg;
    }
}
