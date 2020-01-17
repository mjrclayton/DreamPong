package breakout;

import java.util.Random;

public class Brick {
    public enum BrickType{STANDARD, HEAVY, EXTRA_HEAVY};
    private int remainingHits;
    private boolean containsPowerUp;
    private boolean containsDreamBuck;
    private BrickType type;

    Brick(BrickType type){
        this.containsPowerUp = generateContainsPowerUp();
        this.containsDreamBuck = generateContainsDreamBuck();
        this.type = type;
        if(type == BrickType.STANDARD){
            this.remainingHits = 1;
        } else if(type == BrickType.HEAVY){
            this.remainingHits = 2;
        } else{
            this.remainingHits = 4;
        }
    }

    public int getRemainingHits(){
        return this.remainingHits;
    }

    public void registerHit(){
        this.remainingHits = this.getRemainingHits() - 1;
    }

    public void setRemainingHits(int remainingHits){
        this.remainingHits = remainingHits;
    }

    private boolean generateContainsPowerUp(){
        //20% chance of power up
        Random random = new Random();
        int num = random.nextInt(99);
        if(num < 20){
            return true;
        }
        return false;
    }

    public boolean doesContainPowerUp(){
        return this.containsPowerUp;
    }

    private boolean generateContainsDreamBuck(){
        //10% chance of DreamBuck
        Random random = new Random();
        int num = random.nextInt(99);
        if(num < 10){
            return true;
        }
        return false;
    }

    public boolean doesContainDreamBuck(){
        return this.containsDreamBuck;
    }

    public static void main(String[] args){
        for(int i = 0; i < 10; i++){
            Brick newBrick = new Brick(BrickType.EXTRA_HEAVY);
            System.out.println("Remaining hits: " + newBrick.getRemainingHits());
            newBrick.registerHit();
            System.out.println("Hit by ball, now remaining hits = " + newBrick.getRemainingHits());
            System.out.println("Power-Up? " + newBrick.doesContainPowerUp());
            System.out.println("DreamBuck? " + newBrick.doesContainDreamBuck());
        }
    }
}
