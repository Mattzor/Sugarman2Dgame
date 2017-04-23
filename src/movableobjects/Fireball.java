package movableobjects;

import frame.GamePanel;
import identities.Identities;
import staticobjects.StaticObject;

import java.awt.*;

/**
 * Created by MattiasE on 2014-03-21.
 */
public class Fireball extends MovableObject {

    private Player player = GamePanel.getPlayer();

    public Fireball(int x, int y, int id){
        super(x, y, 4, 4, id);
        setVelocityX(-5);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, width, height);
    }

    @Override
    public void update() {
        super.update();
        collision();
    }

    /** Checks if the fireball collide with anything in the near distance and if
     * it does it will add itself to the removeFireballs ArrayList in GamePanel.
     * If it is the player that it collides with the players loseHealth method will be called.*/
    private void collision() {
        for( StaticObject obj : GamePanel.getStaticObjects()){
            int deltaX = Math.abs(obj.getX()-x);
            if(deltaX < nearDistance){
                if(obj.getId() == Identities.BLOCK_TOP_ID || obj.getId() == Identities.BLOCK_BID){
                    if( getRightBounds().intersects(obj.getLeftBounds())){
                        x = obj.getX() - width;
                        GamePanel.addRemoveFireball(this);

                    }
                    if( getLeftBounds().intersects(obj.getRightBounds())){
                        x = obj.getX() + obj.getWidth();
                        GamePanel.addRemoveFireball(this);

                    }
                    if(getBounds().intersects(player.getBounds())){
                        player.loseHealth();
                        GamePanel.addRemoveFireball(this);
                    }
                }
            }
        }

    }

}
