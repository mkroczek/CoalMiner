package Game;

import javax.swing.*;

public class ResultPanel extends JPanel {

    private int result;
    private int goal;

    public ResultPanel(int result, int goal){
        this.result = result;
        this.goal = goal;
    }

    private void initialize(){
        setOpaque(false);
    }
}
