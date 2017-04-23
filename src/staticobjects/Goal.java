package staticobjects;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by MattiasE on 2014-03-25.
 */
public class Goal extends StaticObject{

    private BufferedImage goalImg;


    public Goal(int x, int y, BufferedImage goalImg, int id){
        super(x, y, goalImg.getWidth(), goalImg.getHeight(), id);
        this.goalImg = goalImg;

    }

    @Override
    public void render(Graphics g){
        g.drawImage(goalImg, x, y, width, height, null);
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }
}
