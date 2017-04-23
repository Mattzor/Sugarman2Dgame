package map;

import frame.GamePanel;
import movableobjects.Boss;
import movableobjects.Enemy1;
import movableobjects.Enemy2;
import staticobjects.*;
import identities.Identities;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by MattiasE on 2014-03-08.
 */
public class Map {

    /**
     * Read the map from a text file and then creates all blocks, enemys, coins etc.
     */


    private int height = 0;
    private int width = 0;

    public void transferValues(String mapName){
        List<ArrayList<Integer>> values = readMap(mapName);
        height = values.size();
        width = values.get(0).size();
        Random rnd = new Random();
        for(int row = 0; row < values.size(); row++){
            for( int col = 0; col < values.get(row).size(); col++){
                switch(values.get(row).get(col)){
                    case 0:
                        break;
                    case 1:
                        addTopBlock(col, row);
                        int t = rnd.nextInt(5);
                        if(t <= 1){
                            addCoin(col, row);
                        }
                        break;
                    case 2:
                        addBBlock(col, row);
                        break;
                    case 3:
                        int r = rnd.nextInt(2);
                        if( r == 1){
                            addEnemy1(col, row);
                        }
                        else{
                            addEnemy2(col, row);
                        }
                        break;
                    case 4:
                        int number = rnd.nextInt(3);
                        if(number==1){
                            addAxePowerup(col, row);
                        }
                        else if(number == 2){
                            addFastPowerup(col, row);
                        }
                        else{
                            addBiggerPowerup(col, row);
                        }
                        break;
                    case 5:
                        setPlayerPos(col, row);
                        break;
                    case 6:
                        addBlockLava(col, row);
                        break;
                    case 8:
                        addBoss(col, row);
                        break;
                    case 9:
                        addGoal(col, row);
                        break;
                    default:
                        break;
                }
            }
        }

    }

    public List<ArrayList<Integer>> readMap(String mapName) {
        /* Will give a warning in the codeinspection because it is hardcoded.. */
        String path = "resources/Maps/";
        List<ArrayList<Integer>> layout = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path + mapName))) {
            String currentLine = br.readLine();

            while((currentLine) != null){
                if(currentLine.isEmpty()){
                    break;
                }
                ArrayList<Integer> row = new ArrayList<>();
                String [] values = currentLine.trim().split(" ");

                for (String line : values){
                    if(!line.isEmpty()){
                        int id = Integer.parseInt(line);
                        row.add(id);
                    }
                }
                layout.add(row);
                currentLine = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return layout;
    }

    private void addTopBlock(int col, int row){
        BufferedImage blockimg = GamePanel.getImages().getBlockImg();
        Block block = new Block(col*blockimg.getWidth(), row*blockimg.getHeight(), blockimg, Identities.BLOCK_TOP_ID);
        GamePanel.addStaticObject(block);
    }

    private void addBBlock(int col, int row){
        BufferedImage blockImg2 = GamePanel.getImages().getBlockBImg();
        Block block1 = new Block(col*blockImg2.getWidth(),row* blockImg2.getHeight(), blockImg2, Identities.BLOCK_BID);
        GamePanel.addStaticObject(block1);
    }

    private void addBlockLava(int col, int row){
        BufferedImage blockLavaImg = GamePanel.getImages().getBlockLavaImg();
        Block block1 = new Block(col*blockLavaImg.getWidth(),row* blockLavaImg.getHeight(), blockLavaImg, Identities.BLOCK_LAVA);
        GamePanel.addStaticObject(block1);
    }

    private void addEnemy1(int col, int row){
        Enemy1 enemy1 = new Enemy1(col*GamePanel.getImages().getBlockWidth(), row*GamePanel.getImages().getBlockHeight(), Identities.ENEMY1_ID);
        GamePanel.addMovableObject(enemy1);
    }

    private void addEnemy2(int col, int row){
        Enemy2 enemy2 = new Enemy2(col*GamePanel.getImages().getBlockWidth(), row*GamePanel.getImages().getBlockHeight(), Identities.ENEMY2_ID);
        GamePanel.addMovableObject(enemy2);
    }

    private void addBoss(int col, int row){
        Boss boss = new Boss(col*GamePanel.getImages().getBlockBImg().getWidth(), row*GamePanel.getImages().getBlockBImg().getHeight(), Identities.BOSS_ID);
        GamePanel.getMoveableObjets().add(boss);
    }

    private void addGoal(int col, int row){
        Goal goal = new Goal(col*GamePanel.getImages().getBlockWidth(), row*GamePanel.getImages().getBlockHeight(), GamePanel.getImages().getGoalImg(), Identities.GOAL_ID);
        GamePanel.addStaticObject(goal);
    }

    private void addCoin(int col, int row){
        BufferedImage coinImg = GamePanel.getImages().getCoinImg();
        Coin coin = new Coin(col*GamePanel.getImages().getBlockWidth(), (row-1)*GamePanel.getImages().getBlockHeight(), coinImg, Identities.COIN_ID);
        GamePanel.addStaticObject(coin);
    }

    private void setPlayerPos(int col, int row){
        GamePanel.getPlayer().setX(col*GamePanel.getImages().getBlockBImg().getWidth());
        GamePanel.getPlayer().setY(row*GamePanel.getImages().getBlockBImg().getHeight());
        GamePanel.getPlayer().setVelocityY(0);
    }

    private void addFastPowerup(int col, int row){
        BufferedImage fastPowerupImg = GamePanel.getImages().getFastPowerUpImg();
        FastPowerup fastPowerup = new FastPowerup(col*GamePanel.getImages().getBlockWidth(), (row-1) * GamePanel.getImages().getBlockHeight(), fastPowerupImg, Identities.FAST_ID);
        GamePanel.addStaticObject(fastPowerup);
    }

    private void addBiggerPowerup(int col, int row){
        BufferedImage bigPowerupImg = GamePanel.getImages().getBigPowerUpImg();
        BigPowerup bigPowerup = new BigPowerup(col*GamePanel.getImages().getBlockWidth(), (row-1) * GamePanel.getImages().getBlockHeight(), bigPowerupImg, Identities.BIG_ID);
        GamePanel.addStaticObject(bigPowerup);
    }


    private void addAxePowerup(int col, int row){
        BufferedImage axePowerupImg = GamePanel.getImages().getAxePowerUpImg();
        AxePowerup axePowerup = new AxePowerup(col*GamePanel.getImages().getBlockWidth(), (row-1) * GamePanel.getImages().getBlockHeight(), axePowerupImg, Identities.AXEP_ID);
        GamePanel.addStaticObject(axePowerup);
    }

    public int getHEIGHT() {
        return height;
    }

    public int getWIDTH() {
        return width;
    }



}
