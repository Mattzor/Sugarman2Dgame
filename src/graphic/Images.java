package graphic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * Created by MattiasE on 2014-03-22.
 */
public class Images {

    /**
     * Loads and stores all the images for the game.
     */

    /* This will give a warning in the codeinspection because it is hardcoded */
    private String path = "/Images/";

    private int blockWidth, blockHeight;

    private BufferedImage menuBackgroundImg = null;

    private BufferedImage play1Img = null;
    private BufferedImage play2Img = null;

    private BufferedImage options1Img = null;
    private BufferedImage options2Img = null;

    private BufferedImage howToPlayImg = null;
    private BufferedImage howToPlay1Img = null;
    private BufferedImage howToPlay2Img = null;

    private BufferedImage quit1Img = null;
    private BufferedImage quit2Img = null;

    private BufferedImage backgroundMusic1Img = null;
    private BufferedImage backgroundMusic2Img = null;

    private BufferedImage gameSounds1Img = null;
    private BufferedImage gameSounds2Img = null;

    private BufferedImage goalImg = null;
    private BufferedImage coinImg = null;

    private BufferedImage playerImg = null;
    private BufferedImage playerRunImg = null;
    private BufferedImage playerFallingImg = null;

    private BufferedImage blockImg = null;
    private BufferedImage blockBImg = null;
    private BufferedImage blockLavaImg = null;

    private BufferedImage backgroundImg = null;

    private BufferedImage enemy1Img = null;
    private BufferedImage enemy2Img = null;

    private BufferedImage boss1Img = null;
    private BufferedImage boss2Img = null;

    private BufferedImage axe1Img = null;
    private BufferedImage axe2Img = null;
    private BufferedImage axe3Img = null;
    private BufferedImage axe4Img = null;

    private BufferedImage fastPowerUpImg = null;
    private BufferedImage bigPowerUpImg = null;
    private BufferedImage axePowerUpImg = null;


    public Images(){
        System.out.println(this.getClass().getResource("/Images/menuBackground.png"));
        try{

            menuBackgroundImg = ImageIO.read(this.getClass().getResource(path + "menuBackground.png"));

            play1Img = ImageIO.read(this.getClass().getResource(path + "play1.png"));
            play2Img = ImageIO.read(this.getClass().getResource(path + "play2.png"));

            options1Img = ImageIO.read(this.getClass().getResource(path + "options1.png"));
            options2Img = ImageIO.read(this.getClass().getResource(path + "options2.png"));

            howToPlayImg = ImageIO.read(this.getClass().getResource(path + "howToPlay.png"));
            howToPlay1Img = ImageIO.read(this.getClass().getResource(path + "howToPlay1.png"));
            howToPlay2Img = ImageIO.read(this.getClass().getResource(path + "howToPlay2.png"));

            quit1Img = ImageIO.read(this.getClass().getResource(path + "quit1.png"));
            quit2Img = ImageIO.read(this.getClass().getResource(path + "quit2.png"));

            backgroundMusic1Img = ImageIO.read(this.getClass().getResource(path + "backgroundMusic1.png"));
            backgroundMusic2Img = ImageIO.read(this.getClass().getResource(path + "backgroundMusic2.png"));

            gameSounds1Img = ImageIO.read(this.getClass().getResource(path + "gameSounds1.png"));
            gameSounds2Img = ImageIO.read(this.getClass().getResource(path + "gameSounds2.png"));

            playerImg = ImageIO.read(this.getClass().getResource(path +"Hero.png"));
            playerRunImg = ImageIO.read(this.getClass().getResource(path+"Hero1.png"));
            playerFallingImg = ImageIO.read(this.getClass().getResource(path+"Herofalling.png"));

            blockImg = ImageIO.read(this.getClass().getResource(path +"ground.png"));
            blockWidth = blockImg.getWidth();
            blockHeight = blockImg.getHeight();
            blockBImg = ImageIO.read(this.getClass().getResource(path +"groundB.png"));
            blockLavaImg = ImageIO.read(this.getClass().getResource(path +"lavaBlock.png"));

            backgroundImg = ImageIO.read(this.getClass().getResource(path + "bakgrund1.png"));

            enemy1Img = ImageIO.read(this.getClass().getResource(path + "enemy1.png"));
            enemy2Img = ImageIO.read(this.getClass().getResource(path + "enemy2.png"));

            goalImg = ImageIO.read(this.getClass().getResource(path + "goal.png"));

            coinImg = ImageIO.read(this.getClass().getResource(path + "coin.png"));

            boss1Img = ImageIO.read(this.getClass().getResource(path + "boss1.png"));
            boss2Img = ImageIO.read(this.getClass().getResource(path + "boss2.png"));

            axe1Img = ImageIO.read(this.getClass().getResource(path + "axe.png"));
            axe2Img = ImageIO.read(this.getClass().getResource(path + "axe1.png"));
            axe3Img = ImageIO.read(this.getClass().getResource(path + "axe2.png"));
            axe4Img = ImageIO.read(this.getClass().getResource(path + "axe3.png"));

            fastPowerUpImg = ImageIO.read(this.getClass().getResource(path + "fastPowerup.png"));
            bigPowerUpImg = ImageIO.read(this.getClass().getResource(path + "bigPowerup.png"));
            axePowerUpImg = ImageIO.read(this.getClass().getResource(path + "axePowerup.png"));
        } catch (IOException e){
            e.printStackTrace();

        }
    }

    public int getBlockWidth() {
        return blockWidth;
    }

    public int getBlockHeight() {
        return blockHeight;
    }

    public BufferedImage getMenuBackgroundImg() {
        return menuBackgroundImg;
    }

    public BufferedImage getPlay1Img() {
        return play1Img;
    }

    public BufferedImage getPlay2Img() {
        return play2Img;
    }

    public BufferedImage getOptions1Img() {
        return options1Img;
    }

    public BufferedImage getOptions2Img() {
        return options2Img;
    }

    public BufferedImage getHowToPlay(){ return howToPlayImg; }

    public BufferedImage getHowToPlay1Img() {
        return howToPlay1Img;
    }

    public BufferedImage getHowToPlay2Img() {
        return howToPlay2Img;
    }

    public BufferedImage getQuit1Img() {
        return quit1Img;
    }

    public BufferedImage getQuit2Img() {
        return quit2Img;
    }

    public BufferedImage getBackgroundMusic1Img() {
        return backgroundMusic1Img;
    }

    public BufferedImage getBackgroundMusic2Img() {
        return backgroundMusic2Img;
    }

    public BufferedImage getGameSounds1Img() {
        return gameSounds1Img;
    }

    public BufferedImage getGameSounds2Img() {
        return gameSounds2Img;
    }

    public BufferedImage getGoalImg() {
        return goalImg;
    }

    public BufferedImage getCoinImg() {
        return coinImg;
    }

    public BufferedImage getPlayerImg() {
        return playerImg;
    }

    public BufferedImage getPlayerRunImg() {
        return playerRunImg;
    }

    public BufferedImage getPlayerFallingImg() {
        return playerFallingImg;
    }

    public BufferedImage getBlockImg() {
        return blockImg;
    }

    public BufferedImage getBlockBImg() {
        return blockBImg;
    }

    public BufferedImage getBlockLavaImg() {
        return blockLavaImg;
    }

    public BufferedImage getBackgroundImg() {
        return backgroundImg;
    }

    public BufferedImage getEnemy1Img() {
        return enemy1Img;
    }

    public BufferedImage getEnemy2Img() {
        return enemy2Img;
    }

    public BufferedImage getBoss1Img() {
        return boss1Img;
    }

    public BufferedImage getBoss2Img() {
        return boss2Img;
    }

    public BufferedImage getAxe1Img() {
        return axe1Img;
    }

    public BufferedImage getAxe2Img() {
        return axe2Img;
    }

    public BufferedImage getAxe3Img() {
        return axe3Img;
    }

    public BufferedImage getAxe4Img() {
        return axe4Img;
    }

    public BufferedImage getFastPowerUpImg() {
        return fastPowerUpImg;
    }

    public BufferedImage getBigPowerUpImg() {
        return bigPowerUpImg;
    }

    public BufferedImage getAxePowerUpImg() {
        return axePowerUpImg;
    }
}
