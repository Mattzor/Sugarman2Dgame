package movableobjects;

import frame.GamePanel;

import identities.Identities;
import staticobjects.StaticObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by MattiasE on 2014-03-29.
 */
public class Boss extends MovableObject {

    private final static int JUMPSPEED = 15;
    private final static int FIREBALL_SPAWN_Y = 30;
    private final static int WAIT_TIME = 500;
    private final static int NANO_TO_MILLI = 1000000;

    private BufferedImage boss1Img;
    private BufferedImage boss2Img;

    private boolean angry = true;

    private int speed = 8;

    private int health = 5;

    private long time = 0L;


    private Random rnd = new Random();

    public Boss(int x, int y, int id){
        super(x,y, GamePanel.getImages().getBoss1Img().getWidth(), GamePanel.getImages().getBoss1Img().getHeight(), id);
        boss1Img = GamePanel.getImages().getBoss1Img();
        boss2Img = GamePanel.getImages().getBoss2Img();

    }
    @Override
    public void update(){
        super.update();
        if((System.nanoTime() - time) / NANO_TO_MILLI > WAIT_TIME){
            velocityX = 0;
            angry = false;
            nextMove();
        }
        fall();
        collision();
    }

    private void nextMove(){
        int num = rnd.nextInt(6);
        if(num == 0){
            velocityX = speed;
        }
        else if(num == 1 && velocityY == 0){
            jump();
        }
        else if(num == 2 && velocityY == 0){
            int num2 = rnd.nextInt(3);
            jump();
            if (num2 == 0){
                velocityX = speed;
                jump();
            }
            else  if(num2 == 2){
                velocityX = -speed;
                jump();
            }
        }
        else if(num == 3){
            angry = true;
            velocityX = -speed;
        }
        else if(num == 4){
            angry = true;

        }
        else if(num == 5){
            shoot();
        }
        time = System.nanoTime();


    }

    private void jump(){
        angry = true;
        velocityY = -JUMPSPEED;
    }

    private void shoot(){
        Fireball fireball = new Fireball(x-2, y+FIREBALL_SPAWN_Y, Identities.FIREBALL_ID);
        GamePanel.getFireballs().add(fireball);
    }




    private void collision() {
        for( StaticObject obj : GamePanel.getStaticObjects()){
            int deltaX = Math.abs(obj.getX()-x);
            int id = obj.getId();
            if(deltaX < nearDistance && (id != Identities.COIN_ID)){
                if( getBottomBounds().intersects(obj.getTopBounds()) ){
                    velocityY = 0;
                    y = obj.getY() - height;
                    falling = false;
                    angry = false;
                }
                if( getTopBounds().intersects(obj.getBottomBounds())){
                    velocityY = 0;
                    y = obj.getY() + obj.getHeight();
                    falling = true;
                }
                if( getRightBounds().intersects(obj.getLeftBounds())){
                    velocityX = -getVelocityX();
                    x = obj.getX() - width;
                }
                if( getLeftBounds().intersects(obj.getRightBounds())){
                    velocityX = -getVelocityX();
                    x = obj.getX() + obj.getWidth();
                }
            }
        }
    }

    @Override
    public void render(Graphics g){
        if(!angry){
            g.drawImage(boss1Img, x, y, width, height, null);
        }
        else{
            g.drawImage(boss2Img, x, y, width, height, null);
        }
    }

    public boolean getAngry(){
        return angry;
    }

    public void loseHealth(){
        health -= 1;
        int num = rnd.nextInt(2);
        if(num == 1){
            velocityX = speed;
        }
        else{
            velocityX = -speed;
        }
    }


    public int getHealth() {
        return health;
    }
}
