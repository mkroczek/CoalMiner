package Game;

import javax.swing.*;
import Constants.*;
import Enum.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class StatisticsArea extends JTextArea {

    private MinePanel mine;
    private Map<StatisticsNames, Integer> statistics = new LinkedHashMap<>();

    public StatisticsArea(MinePanel panel, int goal, int level, int result, int startTime, int dynamites) {
        this.statistics.put(StatisticsNames.TIME, startTime);
        this.statistics.put(StatisticsNames.LEVEL, level);
        this.statistics.put(StatisticsNames.GOAL, goal);
        this.statistics.put(StatisticsNames.RESULT, result);
        this.statistics.put(StatisticsNames.DYNAMITES, dynamites);
        this.mine = panel;
        initializeLayout();
        updateStatistics();
    }

    private void initializeLayout() {
        setBounds(Constants.STATISTICS_SHIFT, Constants.STATISTICS_SHIFT, Constants.STATISTICS_WIDTH, Constants.STATISTICS_HEIGHT);
        setEditable(false);
        setAutoscrolls(true);
        setFont(Constants.STATISTICS_FONT);
    }

    public void updateTime(int time){
        this.statistics.put(StatisticsNames.TIME, time);
        this.updateStatistics();
    }

    public void updateResult(int result){
        this.statistics.put(StatisticsNames.RESULT, result);
        this.updateStatistics();
    }

    public void updateDynamites(int dynamites){
        this.statistics.put(StatisticsNames.DYNAMITES, dynamites);
        this.updateStatistics();
    }

    private void updateStatistics() {
        //clear old statistics
        setText(null);

        //print updated statistics
        for (Map.Entry<StatisticsNames, Integer> entry : this.statistics.entrySet()) {
            append(entry.getKey().toString() + ": " + entry.getValue() + "\n");
        }
    }
}
