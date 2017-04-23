package game;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 * Created by MattiasE on 2014-04-02.
 */
public class Sounds {

    /**
     * Loads and stores all the sounds for the game.
     */


    private AudioClip backgroundMusic = null;
    private AudioClip jumpSound = null;
    private AudioClip coinSound = null;
    private AudioClip damageSound = null;
    private AudioClip gameoverSound = null;
    private AudioClip finishedSound = null;
    private AudioClip bossLaughSound = null;

    /* This will give a warning in the codeinspection because it is hardcoded. */
    private String path = "/Audio/";

    public Sounds() {

        try {
            bossLaughSound = Applet.newAudioClip(this.getClass().getResource(path + "bossLaughSound.wav"));
            jumpSound = Applet.newAudioClip(this.getClass().getResource(path + "jumpSound.wav"));
            coinSound = Applet.newAudioClip(this.getClass().getResource(path + "coinSound.wav"));
            damageSound = Applet.newAudioClip(this.getClass().getResource(path + "damageSound.wav"));
            gameoverSound = Applet.newAudioClip(this.getClass().getResource(path + "gameoverSound.wav"));
            finishedSound = Applet.newAudioClip(this.getClass().getResource(path + "finishedSound.wav"));
            backgroundMusic = Applet.newAudioClip(this.getClass().getResource(path + "KnowIt.wav"));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public AudioClip getBossLaughSound(){ return bossLaughSound; }

    public AudioClip getBackgroundMusic() {
        return backgroundMusic;
    }

    public AudioClip getJumpSound() {
        return jumpSound;
    }

    public AudioClip getCoinSound() {
        return coinSound;
    }

    public AudioClip getDamageSound() {
        return damageSound;
    }

    public AudioClip getGameoverSound() {
        return gameoverSound;
    }

    public AudioClip getFinishedSound() {
        return finishedSound;
    }

}
