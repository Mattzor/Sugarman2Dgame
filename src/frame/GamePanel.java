package frame;

import javax.swing.*;
import camera.Camera;
import game.Sounds;
import identities.Identities;
import input.KeyInput;
import map.Map;
import menu.Menu;
import movableobjects.*;
import graphic.Images;
import staticobjects.StaticObject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * Created by MattiasE on 2014-03-09.
 */
public class GamePanel extends JPanel implements Runnable {

    private Menu menu = null;
    private Map map = new Map();
    private String[] maps = new String[]{"Map1.txt", "Map2.txt", "Map3.txt", "Map4.txt", "Map5.txt"};
    private int mapNumber = 0;

    private Thread thread = null;
    private volatile boolean running;

    /** The image all objects will paint to */
    private BufferedImage image = null;
    private Graphics g = null;
    private Camera camera = null;

    /** Width of the window */
    public final static int WIDTH = 1280;
    /** Height of the window */
    public final static int HEIGHT = 960;


    /** Frames per second */
    private final static int FPS = 60;
    /** Used to convert nanosec to milli sec  */
    private final static int NANO_TO_MILLI = 1000000;
    private final static int DISTANCE_BOSS = 1000;
    private final static int FONT_SIZE = 15;
    private final static int PAUSE_FONT_SIZE = 65;
    private final static int DISPLAY_TEXT_Y = 50;
    private final static int HEALTH_TEXT_X = 50;
    private final static int SCORE_TEXT_X = 150;
    private final static int BOSS_HEALTH_TEXT_X = 1100;

    private static boolean backgroundSoundON = true;
    private static boolean playerSoundON = true;

    /** Game images */
    private static Images images = new Images();
    /** Game sounds */
    private static Sounds sounds = new Sounds();
    /** Player */
    private static Player player  = new Player(Identities.PLAYER_ID);

    private static GameState gameState = GameState.MENU;
    private static List<MovableObject> moveableObjets = new ArrayList<>();
    private static List<MovableObject> removeMovableObjects = new ArrayList<>();
    private static List<StaticObject> staticObjects = new ArrayList<>();
    private static List<Fireball> fireballs = new ArrayList<>();
    private static List<Fireball> removeFireballs = new ArrayList<>();
    private static List<Axe> axesInGame = new ArrayList<>();
    private static List<Axe> removeAxes = new ArrayList<>();


    public GamePanel(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    /** This will be called when the GamePanel is added to the GameFrame.
     * It calls JPanels addNotify method and also creates and starts the new thread if there is none. */
    @Override
    public void addNotify(){
        super.addNotify();
        if(thread == null){
            thread = new Thread(this);
            thread.start();
        }
    }

    /** The new thread will run this method */
    public void run(){
        running = true;

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g =  image.getGraphics();

        long targetTime = 1000/FPS;

        map.transferValues(maps[mapNumber]);
        menu = new Menu();
        this.addKeyListener(new KeyInput(player, menu));
        camera = new Camera(map.getWIDTH()*images.getBlockWidth(), map.getHEIGHT()*images.getBlockHeight(), player, WIDTH, HEIGHT);
        if(backgroundSoundON){
            sounds.getBackgroundMusic().loop();
        }

        /** The GameLoop */
        while(running){

            long startTime = System.nanoTime();

            gameUpdate();
            gameRender();
            gameDraw();

            long deltaMS = (System.nanoTime() - startTime) / NANO_TO_MILLI;
            if(deltaMS > targetTime){
                deltaMS = targetTime;
            }
            long waitTime = targetTime - deltaMS;
            /* The Thread.sleep() will give a warning in the codeispection. We have tried to fix this but
             * as you can see we didn't succeed.. */
            try{
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Tell all the object to update their positions etc.
     * Also removes objects that should be removed.
     */
    private void gameUpdate(){
        if(gameState == GameState.PLAYING){
            player.update();
            for(MovableObject obj: moveableObjets){
                obj.update();
            }
            for(MovableObject obj: fireballs){
                obj.update();
            }
            for(Axe axe: axesInGame){
                axe.update();
            }
            for(MovableObject obj : removeMovableObjects){
                moveableObjets.remove(obj);
            }
            for(Fireball fireball : removeFireballs){
                fireballs.remove(fireball);
            }
            for(Axe axe : removeAxes){
                axesInGame.remove(axe);
            }
            removeMovableObjects.clear();
            removeFireballs.clear();
            removeAxes.clear();
            camera.update();
        }
        else if(gameState == GameState.MAP_FINISHED){
            reset();
            mapNumber+=1;
            if(mapNumber < maps.length){
                map.transferValues(maps[mapNumber]);
            }
            camera = new Camera(map.getWIDTH()*images.getBlockWidth(), map.getHEIGHT()*images.getBlockHeight(), player, WIDTH, HEIGHT);
            GamePanel.setGameState(GameState.PLAYING);
        }
    }

    /**
     * Draws the background and call all objects draw methods.
     * Also draws the player health, score and powerup.
     * Everything will be drawn to the Buffered Image.
     */
    private void gameRender(){
        switch (gameState){
            case PLAYING:
                g.drawImage(images.getBackgroundImg(), 0, 0, WIDTH, HEIGHT,  null);
                Boss boss = null;

                g.translate(-camera.getX(), -camera.getY());

                for(StaticObject obj : staticObjects){
                    obj.render(g);
                }
                for(MovableObject obj: moveableObjets){
                    obj.render(g);
                    if(obj.getId() == Identities.BOSS_ID){
                        if(Math.abs(player.getX() - obj.getX()) < DISTANCE_BOSS){
                            boss = (Boss) obj;
                        }
                    }
                }
                for(MovableObject obj: fireballs){
                    obj.render(g);
                }
                for(Axe obj: axesInGame){
                    obj.render(g);
                }
                player.render(g);

                g.translate(camera.getX(), camera.getY());

                g.setColor(Color.green);
                Font font = new Font("Comic Sans M5", Font.BOLD, FONT_SIZE);
                g.setFont(font);
                g.drawString("Health: " + player.getHealth(), HEALTH_TEXT_X, DISPLAY_TEXT_Y);
                g.drawString("Score: " + player.getScore(), SCORE_TEXT_X, DISPLAY_TEXT_Y);
                if(player.getPlayerMode() != Player.PLAYERSTATE.NORMAL){
                    g.drawString("Powerup: " + player.getPowerUp() , HEALTH_TEXT_X, DISPLAY_TEXT_Y*2);
                }
                if(boss != null){
                    g.drawString("Boss Health: " + boss.getHealth(), BOSS_HEALTH_TEXT_X, DISPLAY_TEXT_Y);
                }
                break;
            case MENU:
                menu.render(g);
                break;
            case HOW_TO_PLAY:
                g.drawImage(images.getHowToPlay(), 0, 0, null);
                break;
            case PAUSE:
                g.setColor(Color.green);
                Font font2 = new Font("Comic Sans M5", Font.BOLD, PAUSE_FONT_SIZE);
                g.setFont(font2);
                g.drawString("PAUSE", WIDTH/2-DISPLAY_TEXT_Y, HEIGHT/2);
                break;
            case GAMEOVER:
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, WIDTH, HEIGHT);
                g.setColor(Color.GREEN);
                g.drawString("GAME OVER", WIDTH/2, HEIGHT/2);
                break;
            case MAP_FINISHED:
                break;
            default:
                break;
        }
    }

    /**
     * Draw the Buffered Image the the screen.
     */
    private void gameDraw(){
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }


    public static void setGameState(GameState newGameState){
        gameState = newGameState;

    }

    public static Player getPlayer() {
        return player;
    }

    public static Images getImages() {
        return images;
    }

    public static Sounds getSounds(){
        return sounds;
    }

    public static GameState getGameState(){
        return gameState;
    }

    public static List<MovableObject> getMoveableObjets(){
        return moveableObjets;
    }
    public static List<StaticObject> getStaticObjects() {
        return staticObjects;
    }
    public static List<Fireball> getFireballs() {
        return fireballs;
    }
    public static List<Axe> getAxesInGame(){ return axesInGame; }

    public static void addRemoveFireball(Fireball fireball){
        removeFireballs.add(fireball);
    }

    public static void addRemoveAxe(Axe axe){
        removeAxes.add(axe);
    }

    public static void addMovableObject(MovableObject obj){
        moveableObjets.add(obj);
    }

    public static void addStaticObject(StaticObject obj){
        staticObjects.add(obj);
    }

    private void reset(){
        staticObjects.clear();
        moveableObjets.clear();
        removeMovableObjects.clear();
        removeFireballs.clear();
        removeAxes.clear();
    }

    public static void addRemoveMovableObject(MovableObject obj){
        removeMovableObjects.add(obj);
    }

    public static void setBackgroundSoundON(boolean backgroundSoundON) {
        if(backgroundSoundON){
            sounds.getBackgroundMusic().loop();
        }
        else{
            sounds.getBackgroundMusic().stop();
        }
        GamePanel.backgroundSoundON = backgroundSoundON;
    }

    public static void setPlayerSoundON(boolean playerSoundON) {
        GamePanel.playerSoundON = playerSoundON;
    }

    public static boolean isBackgroundSoundON() {
        return backgroundSoundON;
    }

    public static boolean isPlayerSoundON() {
        return playerSoundON;
    }

}
