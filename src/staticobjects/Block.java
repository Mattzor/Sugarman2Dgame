package staticobjects;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by MattiasE on 2014-03-14.
 */
public class Block extends StaticObject{

    private BufferedImage blockImg;


    public Block(int x, int y, BufferedImage blockImg, int id){
        super(x, y, blockImg.getWidth(), blockImg.getHeight(), id);
        this.blockImg = blockImg;
    }

    @Override
    public void render(Graphics g){
        g.drawImage(blockImg, x, y, width, height, null);
    }

}

