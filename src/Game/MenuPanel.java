package Game;

import Constants.Constants;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    MenuPanel(){
        initialize();
    }
    private void initialize(){
        setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(Constants.MENU,0,0,this);
    }
}
