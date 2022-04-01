package Game;

import Constants.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame implements KeyListener {
    private GamePanel gamePanel = new GamePanel(this);
    private MenuPanel menuPanel = new MenuPanel();
    private boolean started = false;

    public GameFrame(){
        initialize();
    }

    public void showMenu(){
        this.remove(gamePanel);
        this.add(menuPanel);
        menuPanel.repaint();
    }

    public void showGame(){
        this.remove(menuPanel);
        this.add(gamePanel);
    }

    private void initialize(){
        this.setTitle(Constants.TITLE);
        this.addKeyListener(this);
        showMenu();
        this.setFocusable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void start(){
        this.showGame();
        this.gamePanel.begin();
        this.started = true;
    }

    public void end(){
        this.started = false;
        this.showMenu();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (started)
            this.gamePanel.keyNotify(e);
        else
            this.start();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
