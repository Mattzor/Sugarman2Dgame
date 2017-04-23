package movableobjects;

import frame.GamePanel;
import identities.Identities;
import staticobjects.StaticObject;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by MattiasE on 2014-03-21.
 */
public class Enemy2 extends MovableObject {

    private long time = 0;
    private long currentTime = 0;
    private final static int RELOAD_TIME = 2000;

    private BufferedImage enemy2Img;


    public Enemy2(int x, int y, int id){
        super(x, y, GamePanel.getImages().getEnemy2Img().getWidth(), GamePanel.getImages().getEnemy2Img().getHeight(), id);
        enemy2Img = GamePanel.getImages().getEnemy2Img();
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(enemy2Img, x, y, width, height, null);
    }

    @Override
    public void update() {
        super.update();
        fall();
        collision();
        currentTime = System.currentTimeMillis();
        if(currentTime-time >= RELOAD_TIME){
            shootFireBall();
            time = System.currentTimeMillis();
        }
    }

    private void shootFireBall(){
        Fireball fireball = new Fireball(x-2, y+8, Identities.FIREBALL_ID);
        GamePanel.getFireballs().add(fireball);

    }

    /* Check if the enemy2 collides with any block in the near distance */
    private void collision(){
        for( StaticObject obj : GamePanel.getStaticObjects()){
            int deltaX = Math.abs(obj.getX()-x);
            if(deltaX < nearDistance && obj.getId() < 10){
                if( getBottomBounds().intersects(obj.getTopBounds()) ){
                    velocityY = 0;
                    y = obj.getY() - height;
                    falling = false;
                }
                if( getTopBounds().intersects(obj.getBottomBounds())){
                    velocityY = 0;
                    y = obj.getY() + obj.getHeight();
                    falling = true;
                }
                if( getRightBounds().intersects(obj.getLeftBounds())){
                    velocityX = 0;
                    x = obj.getX() - width;
                }
                if( getLeftBounds().intersects(obj.getRightBounds())){
                    velocityX = 0;
                    x = obj.getX() + obj.getWidth();
                }
            }
        }
    }

}
