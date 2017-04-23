package staticobjects;

import java.awt.*;

/**
 * Created by MattiasE on 2014-03-25.
 */
public abstract class StaticObject {

    protected int x, y;
    protected int id;
    protected int width, height;
    protected Rectangle topBounds;
    protected Rectangle leftBounds;
    protected Rectangle rightBounds;
    protected Rectangle bottomBounds;

    public abstract void render(Graphics g);

    protected StaticObject(int x, int y, int width, int height, int id){
        this.x = x;
        this.y = y;
        this.id = id;
        this.width = width;
        this.height = height;

        topBounds = new Rectangle(x, y, width, height/2-5);
        bottomBounds = new Rectangle(x, y+(height/2)+5, width, height/2-5);
        leftBounds = new Rectangle(x, y, width/2, height);
        rightBounds = new Rectangle(x+(width/2), y, width/2, height);
    }

    public Rectangle getTopBounds(){
        return topBounds;
    }

    public Rectangle getLeftBounds(){
        return leftBounds;
    }

    public Rectangle getRightBounds(){
        return rightBounds;
    }

    public Rectangle getBottomBounds(){
        return bottomBounds;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public int getId(){
        return id;
    }

}
