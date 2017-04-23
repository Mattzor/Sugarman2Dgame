package menu;

import frame.GamePanel;

import java.awt.*;


/**
 * Created by MattiasE on 2014-03-25.
 */
public class Menu {

    /**
     * Set up the menu. Creates all buttons and checks witch one that's "activated".
     */

    private final static  int BUTTON_X = (GamePanel.WIDTH/2) - (GamePanel.getImages().getPlay1Img().getWidth()/2);
    private final static int BUTTON_Y = 300;
    private final static int NEXT_Y = 120;

    private Button play, options, howToPlay,  quit;
    private Button backgroundSound, playerSound;
    private Button[] buttons;
    private Button[] optionsButtons;
    /* The index of the choosed Button in the buttons Array */
    private int theChoosenOne;
    /* The index of the choosed Button in the optionsButtons Array */
    private int theChoosenOne2;
    /* If you´re in the options menu this will be true otherwise false */
    private boolean opt = false;




    public Menu(){

        play = new Button(BUTTON_X, BUTTON_Y, GamePanel.getImages().getPlay2Img(), GamePanel.getImages().getPlay1Img());
        options = new Button(BUTTON_X, BUTTON_Y + NEXT_Y, GamePanel.getImages().getOptions2Img(), GamePanel.getImages().getOptions1Img());
        howToPlay = new Button(BUTTON_X, BUTTON_Y + 2*NEXT_Y, GamePanel.getImages().getHowToPlay2Img(), GamePanel.getImages().getHowToPlay1Img());
        quit = new Button(BUTTON_X, BUTTON_Y + 3*NEXT_Y, GamePanel.getImages().getQuit2Img(), GamePanel.getImages().getQuit1Img());
        backgroundSound = new Button(BUTTON_X, BUTTON_Y, GamePanel.getImages().getBackgroundMusic2Img(), GamePanel.getImages().getBackgroundMusic1Img());
        playerSound = new Button(BUTTON_X, BUTTON_Y+NEXT_Y, GamePanel.getImages().getGameSounds2Img(), GamePanel.getImages().getGameSounds1Img());
        buttons = new Button[] {play, options, howToPlay, quit};
        optionsButtons = new Button[] {backgroundSound, playerSound};
        backgroundSound.setChoosed(true);

        theChoosenOne = 0;
        theChoosenOne2 = 0;
        play.setChoosed(true);
    }

    /* This method will change the chosed button to the one under or if the
     * chosed button is the lowest one then the top button will be chosed. */
    public void down(){
        if(!opt){
            buttons[theChoosenOne].setChoosed(false);
            if(theChoosenOne < buttons.length-1){
                theChoosenOne++;
                buttons[theChoosenOne].setChoosed(true);
            }
            else{
                theChoosenOne = 0;
                buttons[theChoosenOne].setChoosed(true);
            }
        }
        else{
            optionsButtons[theChoosenOne2].setChoosed(false);
            if(theChoosenOne2 < optionsButtons.length-1){
                theChoosenOne2++;
                optionsButtons[theChoosenOne2].setChoosed(true);
            }
            else{
                theChoosenOne2 = 0;
                optionsButtons[theChoosenOne2].setChoosed(true);
            }

        }
    }

    /* This method will change the chosed button to the one over or if the
     * chosed button is the one on top the button at the buttom will be chosed */
    public void up(){
        if(!opt){
            buttons[theChoosenOne].setChoosed(false);
            if(theChoosenOne == 0){
                theChoosenOne = buttons.length-1;
                buttons[theChoosenOne].setChoosed(true);
            }
            else{
                theChoosenOne--;
                buttons[theChoosenOne].setChoosed(true);
            }
        }
        else{
            optionsButtons[theChoosenOne2].setChoosed(false);
            if(theChoosenOne2 == 0){
                theChoosenOne2 = optionsButtons.length-1;
                optionsButtons[theChoosenOne2].setChoosed(true);
            }
            else{
                theChoosenOne2--;
                optionsButtons[theChoosenOne2].setChoosed(true);
            }
        }
    }


    public void render(Graphics g){
        g.drawImage(GamePanel.getImages().getMenuBackgroundImg(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
        if(!opt){
            for(Button button : buttons){
                button.drawButton(g);
            }
        }
        else{
            for(Button button : optionsButtons){
                button.drawButton(g);
            }
            int x = BUTTON_X+GamePanel.getImages().getBackgroundMusic1Img().getWidth();
            int y = BUTTON_Y + GamePanel.getImages().getBackgroundMusic1Img().getHeight()/2;
            if(GamePanel.isBackgroundSoundON()){
                g.drawString("ON", x, y);
            }
            else{
                g.drawString("OFF", x, y);
            }
            if(GamePanel.isPlayerSoundON()){
                g.drawString("ON", x, y+NEXT_Y);
            }
            else{
                g.drawString("OFF", x, y+NEXT_Y);
            }
        }
    }

    public int getTheChoosenOne() {
        return theChoosenOne;
    }

    public int getTheChoosenOne2(){ return theChoosenOne2; }

    public void setOpt(boolean opt) {
        this.opt = opt;
    }

    public boolean isOpt() {
        return opt;
    }
}
