package movableobjects;

import frame.GamePanel;
import frame.GameState;
import game.Sounds;
import identities.Identities;
import staticobjects.*;


import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by MattiasE on 2014-03-09.
 */
public class Player extends MovableObject {

    private final static int NEW_LIFE = 20;
    private final static int CHANGE_PIC = 20;
    private final static int JUMP_SPEED = -20;
    private final static int ENEMY_CONTACT_SPEED = 12;
    private final static int PROTECT_TIME_MILLI = 1500;
    private final static int NANO_TO_MILLI = 1000000;

    private String powerUp = "";
    private int k = 1;
    private int moveSpeed = 5;
    private int health = 3;
    private int score = 0;
    private long protectStart = 0;
    private boolean success = false;

    private BufferedImage playerFallingImg;
    private BufferedImage playerImg;
    private BufferedImage playerRunImg;

    private Sounds sounds = GamePanel.getSounds();

    /** The different player modes */
    public enum PLAYERSTATE {
        /** Normal player mode */
        NORMAL,
        /** Bigger player */
        BIGGER,
        /** Faster player */
        FASTER,
        /** Axe throwing player */
        AXE }

    /** Current player direction */
    public enum HEADINGS {
        /** Current direction left */
        LEFT,
        /** Current direction right */
        RIGHT}

    private PLAYERSTATE playerMode = PLAYERSTATE.NORMAL;
    private HEADINGS heading = HEADINGS.RIGHT;

    public Player(int id){
        super(id);
        playerFallingImg = GamePanel.getImages().getPlayerFallingImg();
        playerImg = GamePanel.getImages().getPlayerImg();
        playerRunImg = GamePanel.getImages().getPlayerRunImg();

    }

    @Override
    public void render(Graphics g) {
        if(falling){
            g.drawImage(playerFallingImg, x, y, width, height, null);
        }
        else if( (k>0 && k < 10) || velocityX == 0 ){
            g.drawImage(playerImg, x, y, width, height, null);
        }
        else{
            g.drawImage(playerRunImg, x, y, width, height, null);
            if(k>=CHANGE_PIC){ k= 1;}
        }
        k+=1;
    }


    @Override
    public void update() {
        super.update();
        if(health < 1){
            die();
        }
        if((System.nanoTime() - protectStart)/NANO_TO_MILLI >= PROTECT_TIME_MILLI){
            protectStart = 0;}
        if(score >= NEW_LIFE){
            score = 0;
            health++;
        }

        fall();
        collision();

    }


    private void collision() {
        collisionStaticObjects();
        collisionMovableObjects();
    }



    private void collisionStaticObjects(){
        StaticObject object = null;
        for( StaticObject obj : GamePanel.getStaticObjects()){
            int deltaX = Math.abs(obj.getX()-x);
            if(deltaX < nearDistance){
                if(obj.getId() < 10){
                   collisionBlock(obj);
                }
                if(obj.getId() == Identities.GOAL_ID){
                    collisionGoal(obj);
                }
                else if(obj.getId() == Identities.COIN_ID){
                    if(collisionCoin(obj)){
                        object = obj;
                    }
                }
                else if (obj.getId() == Identities.AXEP_ID){
                    if(collisionAxePowerUp(obj)){
                        object = obj;
                    }
                }
                else if (obj.getId() == Identities.BIG_ID){
                    if(collisionBigPowerUP(obj)){
                        object = obj;
                    }
                }
                else if (obj.getId() == Identities.FAST_ID){
                    if(collisionFastPowerUp(obj)){
                        object = obj;
                    }
                }
            }
        }
        if(object != null){
            GamePanel.getStaticObjects().remove(object);
        }



    }

    private void collisionBlock(StaticObject obj){
        boolean leftFree = true;
        boolean rightFree = true;

        if( getBottomBounds().intersects(obj.getTopBounds()) ){
            velocityY = 0;
            y = obj.getY() - height;
            falling = false;
            if(obj.getId() == Identities.BLOCK_LAVA){
                loseHealth();
            }
        }
        if( getTopBounds().intersects(obj.getBottomBounds())){
            velocityY = 0;
            y = obj.getY() + obj.getHeight();
            falling = true;
        }
        if( getRightBounds().intersects(obj.getLeftBounds())){
            velocityX = 0;
            x = obj.getX() - height;
            rightFree = false;
            moveRight = false;
        }
        if( getLeftBounds().intersects(obj.getRightBounds())){
            velocityX = 0;
            x = obj.getX() + obj.getWidth();
            leftFree = false;
            moveLeft = false;
        }
        if(rightFree){moveRight = true;}
        if(leftFree){moveLeft = true;}

    }

    private void collisionGoal(StaticObject obj){
        Goal goal = (Goal) obj;
        if(getBounds().intersects(goal.getBounds())){
            success = true;
        }
        else{success = false;}
    }

    private boolean collisionCoin(StaticObject obj){
        Coin coin = (Coin) obj;
        if(getBounds().intersects(coin.getBounds())){
            if(GamePanel.isPlayerSoundON()){
                sounds.getCoinSound().play();
            }
            score+=1;
            return true;
        }
        return false;
    }

    private boolean collisionFastPowerUp(StaticObject obj){
        FastPowerup fastPowerup = (FastPowerup) obj;
        if(getBounds().intersects(fastPowerup.getBounds())){
            playerMode = PLAYERSTATE.FASTER;
            powerUp = "Fast";
            width = GamePanel.getImages().getPlayerImg().getWidth();
            height = GamePanel.getImages().getPlayerImg().getHeight();
            return true;
        }
        return false;
    }

    private boolean collisionBigPowerUP(StaticObject obj){
        BigPowerup bigPowerup = (BigPowerup) obj;
        if(getBounds().intersects(bigPowerup.getBounds())){
            playerMode = PLAYERSTATE.BIGGER;
            powerUp = "Big";
            width = GamePanel.getImages().getPlayerImg().getWidth()*2;
            height = GamePanel.getImages().getPlayerImg().getHeight()*2;
            return true;
        }
        return false;
    }

    private boolean collisionAxePowerUp(StaticObject obj){
        AxePowerup axePowerup = (AxePowerup) obj;
        if(getBounds().intersects(axePowerup.getBounds())){
            playerMode = PLAYERSTATE.AXE;
            powerUp = "Axes";
            width = GamePanel.getImages().getPlayerImg().getWidth();
            height = GamePanel.getImages().getPlayerImg().getHeight();
            return true;
        }
        return false;
    }

    private void collisionMovableObjects(){
        MovableObject object = null;
        for( MovableObject obj : GamePanel.getMoveableObjets()){
            if (obj.getId() == Identities.ENEMY1_ID || obj.getId() == Identities.ENEMY2_ID){
                if (getBottomBounds().intersects(obj.getTopBounds())){
                    object = obj;
                }
                else if (getRightBounds().intersects(obj.getLeftBounds()) ){
                    if(protectStart <= 0){
                        loseHealth();
                        velocityX = -ENEMY_CONTACT_SPEED;
                    }
                }
                else if(getLeftBounds().intersects(obj.getRightBounds())){
                    if(protectStart <= 0){
                        loseHealth();
                        velocityX = ENEMY_CONTACT_SPEED;
                    }
                }
            }
            if(obj.getId() == Identities.BOSS_ID){
                Boss boss = (Boss) obj;
                if(getBottomBounds().intersects(boss.getTopBounds())){
                    if(boss.getAngry()){
                        if(protectStart <=0){
                            sounds.getBossLaughSound().play();
                            loseHealth();
                        }
                        velocityY = -ENEMY_CONTACT_SPEED;
                    }
                    else{
                        boss.loseHealth();
                        velocityY = -ENEMY_CONTACT_SPEED;
                        if(boss.getHealth() <= 0){
                            object = obj;
                        }
                    }
                }
                else if (getRightBounds().intersects(boss.getLeftBounds())){
                    if(protectStart <= 0){
                        loseHealth();
                        sounds.getBossLaughSound().play();
                    }
                    velocityX = -ENEMY_CONTACT_SPEED;
                }
                else if (getLeftBounds().intersects(boss.getRightBounds())){
                    if(protectStart <= 0){
                        loseHealth();
                        sounds.getBossLaughSound().play();
                    }
                    velocityX = ENEMY_CONTACT_SPEED;
                }
            }

        }
        if( object != null){
            GamePanel.addRemoveMovableObject(object);
            score += 1;
            velocityY = -moveSpeed;
        }
    }

    public void throwAxeRight(){
        Axe axe = new Axe(x+4, y+4, Identities.AXE_ID);
        GamePanel.getAxesInGame().add(axe);
    }

    public void throwAxeLeft(){
        Axe axe = new Axe(x-4, y-4, Identities.AXE_ID);
        GamePanel.getAxesInGame().add(axe);
    }


    private void die(){
        sounds.getGameoverSound().play();
        GamePanel.setGameState(GameState.GAMEOVER);
    }

    private void protection(){
        protectStart = System.nanoTime();
    }

    public void jump(){
        if(GamePanel.isPlayerSoundON()){
            sounds.getJumpSound().play();
        }
        velocityY = JUMP_SPEED;
        falling = true;
        moveLeft = true;
        moveRight = true;
    }

    public void moveToLeft(){
        if(playerMode == PLAYERSTATE.FASTER){
            moveSpeed = 10;
        }
        else{
            moveSpeed = 5;
        }
        velocityX = -moveSpeed;
        heading = HEADINGS.LEFT;
    }
    public void moveToRight(){
        if(playerMode == PLAYERSTATE.FASTER){
            moveSpeed = 10;
        }
        else{
            moveSpeed = 5;
        }
        velocityX = moveSpeed;
        heading = HEADINGS.RIGHT;
    }
    public int getHealth(){
        return health;
    }

    public int getScore() {
        return score;
    }

    public HEADINGS getHeading() {
        return heading;
    }

    public String getPowerUp() {
        return powerUp;
    }

    public void addScore(){score+=1;}

    public PLAYERSTATE getPlayerMode() {
        return playerMode;
    }

    public boolean getSuccess(){return success;}

    public void loseHealth(){
        if(protectStart <= 0){
            if(GamePanel.isPlayerSoundON()){
                sounds.getDamageSound().play();
            }
            health-=1;
            playerMode = PLAYERSTATE.NORMAL;
            width = playerImg.getWidth();
            height = playerImg.getHeight();
            protection();
        }
    }

    public void setSuccess(Boolean value){
        this.success = value;
    }


}
