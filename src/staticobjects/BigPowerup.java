package staticobjects;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by MattiasE on 2014-04-10.
 */
public class BigPowerup extends StaticObject {

    private BufferedImage bigImg;

    public BigPowerup(int x, int y, BufferedImage bigImg, int id) {
        super(x, y, bigImg.getWidth(), bigImg.getHeight(), id);
        this.bigImg = bigImg;
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(bigImg,x,y,width,height,null);
    }
    public Rectangle getBounds() {
        return new Rectangle(x,y,width,height);
    }
}
