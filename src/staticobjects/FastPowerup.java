package staticobjects;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by MattiasE on 2014-04-10.
 */
public class FastPowerup extends StaticObject {

    private BufferedImage fastImg;

    public FastPowerup(int x, int y, BufferedImage fastImg, int id) {
        super(x,y,fastImg.getWidth(),fastImg.getHeight(), id);
        this.fastImg = fastImg;
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(fastImg,x,y,width,height,null);
    }
    public Rectangle getBounds() {
        return new Rectangle(x,y,width,height);
    }
}
