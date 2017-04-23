package movableobjects;

import frame.GamePanel;

import java.awt.*;

/**
 * Created by MattiasE on 2014-03-13.
 */
public abstract class MovableObject {
    protected int height;
    protected int width;

    protected boolean falling = true;
    protected boolean moveLeft = true;
    protected boolean moveRight = true;
    protected int x;
    protected int y;
    protected int nearDistance = 100;
    protected int velocityX = 0;
    protected int velocityY = 0;
    protected int maxVelocityY = 10;

    protected int id;
    protected static int gravity = 1;


    protected MovableObject(int x, int y, int width, int height, int id){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
    }

    protected MovableObject(int id){
        this.id = id;
        this.width = GamePanel.getImages().getPlayerImg().getWidth();
        this.height = GamePanel.getImages().getPlayerImg().getHeight();
    }

    public abstract void render( Graphics g);

    public void update(){
        x += velocityX;
        y += velocityY;
    }




    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }



    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVelocityX() {
        return velocityX;
    }



    public int getId() {
        return id;
    }

    protected void fall(){
        if(velocityY < maxVelocityY){
            velocityY += gravity;
        }
    }

    protected Rectangle getTopBounds(){
        return new Rectangle(x, y, width, height/2-5);
    }

    protected Rectangle getBottomBounds(){
        return new Rectangle(x, y+(height/2)+5, width, height/2-5);
    }

    protected Rectangle getLeftBounds(){
        return new Rectangle(x, y, width/2, height);
    }

    protected Rectangle getRightBounds(){
        return new Rectangle(x+(width/2), y, width/2, height);
    }

    protected Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }


    public boolean isFalling() {
        return falling;
    }

    public boolean getMoveLeft() {
        return moveLeft;
    }

    public boolean getMoveRight() {
        return moveRight;
    }



    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
