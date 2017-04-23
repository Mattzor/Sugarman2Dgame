package frame;

import javax.swing.*;

/**
 * Created by MattiasE on 2014-03-09.
 */
public class GameFrame{

    /**
     * Creates the window for the game and adds a GamePanel to the window.
     */

    private JFrame frame;
    private GamePanel area;


    public GameFrame(){
        area = new GamePanel();
        frame = new JFrame("Wonder Sugarman");
        frame.add(area);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

}
