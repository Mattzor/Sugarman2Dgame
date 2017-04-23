package menu;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by MattiasE on 2014-04-02.
 */
public class Button{

    /** Button class that our menu will use  */

    private int x, y, width, height;

    private boolean choosed;

    private BufferedImage img1;
    private BufferedImage img2;

    public Button(int x, int y, BufferedImage img1, BufferedImage img2){
        this.x = x;
        this.y = y;
        this.img1 = img1;
        this.img2 = img2;
        width = img1.getWidth();
        height = img1.getHeight();
        choosed = false;
    }


    public void drawButton(Graphics g){
        if(choosed){
            g.drawImage(img1, x, y, width, height, null);
        }
        else{
            g.drawImage(img2, x, y, width, height, null);
        }
    }

    public void setChoosed(boolean value){
        choosed = value;
    }
}
