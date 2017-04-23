package staticobjects;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by MattiasE on 2014-03-25.
 */
public class Coin extends StaticObject{

    private BufferedImage coinImg;

    public Coin(int x, int y, BufferedImage coinImg, int id){
        super(x, y, coinImg.getWidth(), coinImg.getHeight(), id);
        this.coinImg = coinImg;
    }
    @Override
    public void render(Graphics g){
        g.drawImage(coinImg, x, y, width, height, null);
    }
    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }
}
