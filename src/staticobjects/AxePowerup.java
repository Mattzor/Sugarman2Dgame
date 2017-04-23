package staticobjects;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by MattiasE on 2014-04-10.
 */
public class AxePowerup extends StaticObject {

    private BufferedImage axeImg;

    public AxePowerup(int x, int y, BufferedImage axeImg, int id) {
        super(x,y,axeImg.getWidth(),axeImg.getHeight(), id);
        this.axeImg = axeImg;
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(axeImg,x,y,width,height,null);
    }
    public Rectangle getBounds() {
        return new Rectangle(x,y,width,height);
    }
}
