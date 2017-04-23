package movableobjects;

import frame.GamePanel;
import identities.Identities;
import staticobjects.StaticObject;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by MattiasE on 2014-04-10.
 */
public class Axe extends MovableObject {

    private Player player = GamePanel.getPlayer();
    private BufferedImage axeImg = null;
    private BufferedImage axeImg1 = null;
    private BufferedImage axeImg2 = null;
    private BufferedImage axeImg3 = null;

    private int k = 1;
    private int spinTime = 5;

    public Axe(int x, int y,int id) {
        super(x,y,10,10,id);
        switch (player.getHeading()) {
            case LEFT:
                setVelocityX(-7);
                this.axeImg = GamePanel.getImages().getAxe4Img();
                this.axeImg1 = GamePanel.getImages().getAxe3Img();
                this.axeImg2 = GamePanel.getImages().getAxe2Img();
                this.axeImg3 = GamePanel.getImages().getAxe1Img();
                break;
            case RIGHT:
                setVelocityX(7);
                this.axeImg = GamePanel.getImages().getAxe1Img();
                this.axeImg1 = GamePanel.getImages().getAxe2Img();
                this.axeImg2 = GamePanel.getImages().getAxe3Img();
                this.axeImg3 = GamePanel.getImages().getAxe4Img();
                break;
        }
    }
    @Override
    public void render(Graphics g) {
        if (k < spinTime){
            g.drawImage(axeImg,x,y,width,height,null);
        }
        else if (k < spinTime*2){
            g.drawImage(axeImg1,x,y,width,height,null);
        }
        else if (k < spinTime*3){
            g.drawImage(axeImg2,x,y,width,height,null);
        }
        else if (k < spinTime*4){
            g.drawImage(axeImg3,x,y,width,height,null);
        }
        if (k>=spinTime*4){
            k=1;
        }
        else {
            k+=1;
        }

    }
    @Override
    public void update() {
        super.update();
        collision();
    }
    private void collision() {
        for(StaticObject obj : GamePanel.getStaticObjects()) {
            int deltaX = Math.abs(obj.getX() - x);
            if(deltaX < nearDistance && (obj.getId() == Identities.BLOCK_TOP_ID || obj.getId() == Identities.BLOCK_BID)) {
                if( getRightBounds().intersects(obj.getLeftBounds()) ) {
                    x = obj.getX() - width;
                    GamePanel.addRemoveAxe(this);
                }
                if( getLeftBounds().intersects(obj.getRightBounds()) ) {
                    x = obj.getX() - width;
                    GamePanel.addRemoveAxe(this);
                }
            }
        }
        MovableObject object = null;
        for( MovableObject obj : GamePanel.getMoveableObjets() ) {
            if(obj.getId() == Identities.ENEMY1_ID || obj.getId() == Identities.ENEMY2_ID) {
                if( getBounds().intersects(obj.getBounds()) ) {
                    object = obj;
                    GamePanel.addRemoveAxe(this);
                }
            }
            else if(obj.getId() == Identities.BOSS_ID) {
                Boss boss = (Boss) obj;
                if( getBounds().intersects(obj.getBounds()) ){
                    boss.loseHealth();
                    GamePanel.addRemoveAxe(this);
                    if( boss.getHealth() <= 0) {
                        object = obj;
                    }
                }
            }
        }
        if( object != null) {
            GamePanel.addRemoveMovableObject(object);
            player.addScore();
        }
    }


}
