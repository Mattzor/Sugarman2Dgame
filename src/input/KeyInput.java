package input;


import frame.GamePanel;
import frame.GameState;
import game.Sounds;
import menu.Menu;
import movableobjects.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by MattiasE on 2014-03-13.
 */
public class KeyInput extends KeyAdapter {

    /**
     * Hanldles the key-inputs.
     */

    private Player player;
    private Menu menu;
    private Sounds sounds = GamePanel.getSounds();


    private boolean[] keyDown = new boolean[3];

    public KeyInput( Player player, Menu menu){
        this.player = player;
        this.menu = menu;
    }

    @Override
    public void keyPressed(KeyEvent e){
        super.keyPressed(e);
        int key = e.getKeyCode();
        switch(GamePanel.getGameState()){
            case PLAYING:
                keyPressedPlaying(e);
                break;
            case MENU:
                keyPressedMenu(e);
                break;
            case PAUSE:
                if(key == KeyEvent.VK_P){
                    GamePanel.setGameState(GameState.PLAYING);
                }
                break;
            case MAP_FINISHED:
                break;
            case HOW_TO_PLAY:
                GamePanel.setGameState(GameState.MENU);
                break;
            case GAMEOVER:
                break;
            default:
                break;
        }
    }


    private void keyPressedPlaying(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W && !player.isFalling()){
            if(player.getSuccess()){
                if(GamePanel.isPlayerSoundON()){
                    sounds.getFinishedSound().play();
                }
                player.setSuccess(false);
                GamePanel.setGameState(GameState.MAP_FINISHED);
            }
            else {
                player.jump();
            }
        }
        if(key == KeyEvent.VK_A && player.getMoveLeft()){
            keyDown[0] = true;
            player.moveToLeft();
        }
        if(key == KeyEvent.VK_D && player.getMoveRight()){
            keyDown[1] = true;
            player.moveToRight();
        }
        if(key == KeyEvent.VK_SPACE){
            if(player.getPlayerMode() == Player.PLAYERSTATE.AXE && !keyDown[2]){
                keyDown[2] = true;
                switch (player.getHeading()){
                    case LEFT:
                        player.throwAxeLeft();
                        break;
                    case RIGHT:
                        player.throwAxeRight();
                        break;
                }
            }
        }
        if(key == KeyEvent.VK_P){
            keyDown[0] = false;
            keyDown[1] = false;
            player.setVelocityX(0);
            GamePanel.setGameState(GameState.PAUSE);
        }
        if(key == KeyEvent.VK_ESCAPE){
            GamePanel.setGameState(GameState.MENU);
            keyDown[0] = false;
            keyDown[1] = false;
        }

    }

    private void keyPressedMenu(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W){
            menu.up();
        }
        if(key == KeyEvent.VK_S){
            menu.down();
        }
        if(key == KeyEvent.VK_ENTER){
            if(!menu.isOpt()){
                switch(menu.getTheChoosenOne()){
                    case 0:
                        GamePanel.setGameState(GameState.PLAYING);
                        break;
                    case 1:
                        menu.setOpt(!menu.isOpt());
                        break;
                    case 2:
                        GamePanel.setGameState(GameState.HOW_TO_PLAY);
                        break;
                    case 3:
                        System.exit(1);
                }
            }
            else{
                switch (menu.getTheChoosenOne2()){
                    case 0:
                        GamePanel.setBackgroundSoundON(!GamePanel.isBackgroundSoundON());
                        break;
                    case 1:
                        GamePanel.setPlayerSoundON(!GamePanel.isPlayerSoundON());
                        break;
                }
            }
        }
        if( key == KeyEvent.VK_ESCAPE && menu.isOpt()){
            menu.setOpt(false);
        }

    }

    @Override
    public void keyReleased(KeyEvent e){
        super.keyReleased(e);
        if(GamePanel.getGameState() == GameState.PLAYING){
            int key = e.getKeyCode();

            if(key == KeyEvent.VK_A){
                keyDown[0] = false;
            }
            if(key == KeyEvent.VK_D){
                keyDown[1] = false;
            }
            if(key == KeyEvent.VK_SPACE){
                keyDown[2] = false;
            }
            if(keyDown[0] && !keyDown[1]){
                player.moveToLeft();
            }
            if(!keyDown[0] && keyDown[1]){
                player.moveToRight();
            }
            if(!keyDown[0] && !keyDown[1]){
                player.setVelocityX(0);
            }
        }
    }
}
