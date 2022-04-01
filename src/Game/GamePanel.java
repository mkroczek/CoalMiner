package Game;

import Constants.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements ActionListener {
    private GameFrame root;
    private MinePanel minePanel;
    private boolean special = false;
    private boolean isOver = false;
    private boolean nextLevel = false;
    private Timer levelTimer;
    private int level = 0;
    private int time;
    private int result = 0;
    private int dynamites = 0;
    private int goal;

    GamePanel(GameFrame root){
        this.root = root;
        initialize();
    }
    private void initialize(){
        setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        this.time = Constants.LEVEL_LENGTH;
        levelTimer = new Timer(Constants.LEVEL_TIME, this);
    }

    public void begin(){
        this.level = 1;
        this.goal = Constants.START_GOAL;
        this.startGame();
    }

    public void startGame(){
        minePanel = new MinePanel(this.level, this.result, this.goal, this.dynamites);
        this.minePanel.start();
        this.add(minePanel);
        levelTimer.start();
    }
    public void nextLevel(){
        this.level += 1;
        this.goal = level*Constants.START_GOAL;
        this.nextLevel = true;
        this.repaint();
    }

    private void endLevel(){
        this.levelTimer.stop();
        this.minePanel.stop();
        this.time = Constants.LEVEL_LENGTH;
        this.result = this.minePanel.getResult();
        this.dynamites = this.minePanel.getDynamites();
        this.remove(minePanel);
        if (this.result < this.goal)
            gameOver();
        else
            nextLevel();
    }

    public void gameOver(){
        this.isOver = true;
        repaint();
    }

    private void restart(){
        this.isOver = false;
        this.result = 0;
        this.level = 0;
        this.dynamites = 0;
        this.level = 0;
        this.goal = Constants.START_GOAL;
        this.root.end();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (isOver){
            g.drawImage(Constants.GAME_OVER, 0, 0, this);
        }
        else if (nextLevel){
            g.drawImage(Constants.NEXT_LEVEL,0,0,this);
        }
    }

    private void updateTime(){
        this.minePanel.updateTime(this.time);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.time -= 1;
        if (this.time < 0){
            this.endLevel();
        }
        else
            this.updateTime();
    }

    public void keyNotify(KeyEvent e){
        if (isOver){
            this.restart();
        }
        if (nextLevel){
            this.nextLevel = false;
            this.startGame();
        }
        else
            this.minePanel.keyNotify(e);
    }
}
