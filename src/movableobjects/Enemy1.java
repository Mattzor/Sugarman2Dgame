package movableobjects;

import frame.GamePanel;
import identities.Identities;
import staticobjects.StaticObject;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by MattiasE on 2014-03-18.
 */
public class Enemy1 extends MovableObject {


    private BufferedImage enemy1Img;

    public Enemy1(int x, int y, int id){
        super(x, y, GamePanel.getImages().getEnemy1Img().getWidth(), GamePanel.getImages().getEnemy1Img().getHeight(), id);
        enemy1Img = GamePanel.getImages().getEnemy1Img();
        setVelocityX(3);
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(enemy1Img, x, y, width, height, null);
    }

    @Override
    public void update() {
        super.update();
        fall();
        collision();

    }

    /* Check if the enemy1 collides with any block in the near distance */
    protected void collision() {
        for( StaticObject obj : GamePanel.getStaticObjects()){
            int deltaX = Math.abs(obj.getX()-x);
            int id = obj.getId();
            if(deltaX < nearDistance && (id == Identities.BLOCK_TOP_ID || id == Identities.BLOCK_BID || id == Identities.BLOCK_LAVA)){
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



}
