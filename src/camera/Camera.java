package camera;


import movableobjects.Player;

/**
 * Created by MattiasE on 2014-03-20.
 */
public class Camera {

    /**
     * The Camera will give x and y values to graphics.translate method in GamePanel based on
     * where the player is so that you never can walk out of the screen.
     */

    private int offSetMaxX, offSetMaxY, camX, camY;
    private int viewSizeWidth, viewSizeHeight;

    private int offSetMinX = 0;
    private int offSetMinY = 0;
    private Player player;


    public Camera(int worldSizeWidth, int worldSizeHeight, Player player, int viewSizeWidth, int viewSizeHeight){
        this.player = player;
        this.viewSizeWidth = viewSizeWidth;
        this.viewSizeHeight = viewSizeHeight;
        offSetMaxX = worldSizeWidth - viewSizeWidth;
        offSetMaxY = worldSizeHeight - viewSizeHeight;
        camX = player.getX() - ( viewSizeWidth / 2);
        camY = player.getY() - ( viewSizeHeight / 2);
    }

    public void update(){
        camX = player.getX() - ( viewSizeWidth / 2);
        camY = player.getY() - ( viewSizeHeight / 2);
        if (camX > offSetMaxX){
            camX = offSetMaxX;
        }
        else if(camX < offSetMinX){
            camX = offSetMinX;
        }
        if (camY > offSetMaxY){
            camY = offSetMaxY;
        }
        if(camY < offSetMinY){
            camY = offSetMinY;
        }
    }

    public int getX() {
        return camX;
    }

    public int getY() {
        return camY;
    }

}
