package game;
import frame.GameFrame;

/**
 * Created by MattiasE on 2014-03-13.
 */
public final class Game {

    private Game() {
    }

    /**
     * Starts the Game..
     */

    public static void main(String [] args){

        /* This will give a warning in the codeinspection because frame is never used.. */
        new GameFrame();

    }

}