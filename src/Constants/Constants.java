package Constants;

import java.awt.*;

public class Constants {
    private static Toolkit t = Toolkit.getDefaultToolkit();
    private Constants(){}

    public static final String TITLE = "Silesian Coal Miner";
    public static final int WINDOW_WIDTH = 900;
    public static final int WINDOW_HEIGHT = 750;
    public static final int OUTSIDE_HEIGHT = 200;
    public static final int OUTSIDE_WIDTH = WINDOW_WIDTH;
    public static final int MINE_WIDTH = WINDOW_WIDTH;
    public static final int MINE_HEIGHT = WINDOW_HEIGHT-OUTSIDE_HEIGHT;
    public static final int MINE_SHIFT = MINE_HEIGHT/8;
    public static final int LEVEL_LENGTH = 30;
    public static final int START_GOAL = 500;
    public static final int DELAY = 40;
    public static final int LEVEL_TIME = 1000;
    public static final int MINER_HEIGHT = 123;
    public static final int MINER_WIDTH = 70;
    public static final int BIG_MINERAL = 60;
    public static final int MEDIUM_MINERAL = 40;
    public static final int SMALL_MINERAL = 20;
    public static final int SACK_SIZE = 40;
    public static final double LINE_LENGTH = (double)MINE_HEIGHT/10;
    public static final int SHOOTING_SPEED = 15;
    public static final Image UNDERGROUND = t.getImage("images/dirt.jpg");
    public static final Image SKY = t.getImage("images/sky.jpg");
    public static final Image MINER = t.getImage("images/miner.png");
    public static final Image SACK = t.getImage("images/sack.png");
    public static final Image STONE = t.getImage("images/stone.png");
    public static final Image COAL = t.getImage("images/coal.png");
    public static final Image MENU = t.getImage("images/menu.png");
    public static final Image GAME_OVER = t.getImage("images/gameOver.png");
    public static final Image NEXT_LEVEL = t.getImage("images/nextLevel.png");
    public static final Image BIG_STONE = STONE.getScaledInstance(BIG_MINERAL,BIG_MINERAL,Image.SCALE_DEFAULT);
    public static final Image MEDIUM_STONE = STONE.getScaledInstance(MEDIUM_MINERAL,MEDIUM_MINERAL,Image.SCALE_DEFAULT);
    public static final Image SMALL_STONE = STONE.getScaledInstance(SMALL_MINERAL,SMALL_MINERAL,Image.SCALE_DEFAULT);
    public static final Image BIG_COAL = COAL.getScaledInstance(BIG_MINERAL,BIG_MINERAL,Image.SCALE_DEFAULT);
    public static final Image MEDIUM_COAL = COAL.getScaledInstance(MEDIUM_MINERAL,MEDIUM_MINERAL,Image.SCALE_DEFAULT);
    public static final Image SMALL_COAL = COAL.getScaledInstance(SMALL_MINERAL,SMALL_MINERAL,Image.SCALE_DEFAULT);

    public static final int STATISTICS_SHIFT = 15;
    public static final int STATISTICS_HEIGHT = OUTSIDE_HEIGHT-2*STATISTICS_SHIFT;
    public static final int STATISTICS_WIDTH = STATISTICS_HEIGHT;
    public static final Font STATISTICS_FONT = new Font("Comic Sans",0,20);
}
