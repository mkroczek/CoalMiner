package Game;

import Objects.Line;
import Objects.MineObject;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.*;

import Constants.*;

import Math.*;
import Objects.Rectangle;
import Objects.*;
import Utils.LevelGenerator;
import Enum.*;

public class MinePanel extends JPanel implements ActionListener {
    private ArrayList<MineObject> minerals = new ArrayList<>();
    private Timer refresh;
    private LevelGenerator generator;
    private Random rand = new Random();
    private Line line;
    public final int level;
    private final int goal;
    private Vector2d lineAnchor;
    private boolean isSpinning = true;
    private boolean isShooting = false;
    private MineObject attachedObject = null;
    private Rectangle mineralsArea;
    private StatisticsArea statistics;
    private int result;
    private int dynamites;

    public MinePanel(int level, int result, int goal, int dynamites){
        this.goal = goal;
        this.result = result;
        this.level = level;
        this.dynamites = dynamites;
        initialize();
    }

    private void initialize(){
        this.setLayout(null);
        setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        this.generator = new LevelGenerator(this);
        this.lineAnchor = new Vector2d(Constants.WINDOW_WIDTH/2, Constants.OUTSIDE_HEIGHT-Constants.MINER_HEIGHT/3);
        this.refresh = new Timer(Constants.DELAY, this);
        this.createMineralsArea();
        double fiMax = Math.atan(((double)Constants.MINE_WIDTH/(2*(Constants.MINE_SHIFT+Constants.OUTSIDE_HEIGHT-lineAnchor.y))));
        double fiMin = -fiMax;
        Polar linePosition = new Polar(Constants.LINE_LENGTH, 0);
        this.line = new Line(Constants.LINE_LENGTH, linePosition, lineAnchor, fiMax, fiMin, this);
        this.generator.generate();
        this.statistics = new StatisticsArea(this, this.goal, this.level, this.result, Constants.LEVEL_LENGTH, this.dynamites);
        this.add(statistics);
    }

    private void createMineralsArea(){
        Vector2d rightBottom = new Vector2d(0, (int) (Constants.OUTSIDE_HEIGHT+Constants.MINE_SHIFT));
        Vector2d leftTop = new Vector2d(Constants.MINE_WIDTH, Constants.WINDOW_HEIGHT);
        this.mineralsArea = new Rectangle(rightBottom, leftTop.x-rightBottom.x, leftTop.y- rightBottom.y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateObjects();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g.drawImage(Constants.SKY, 0, 0, this);
        g.drawImage(Constants.UNDERGROUND, 0, Constants.OUTSIDE_HEIGHT, this);
        g.drawImage(Constants.MINER, (Constants.OUTSIDE_WIDTH-Constants.MINER_WIDTH)/2,Constants.OUTSIDE_HEIGHT-Constants.MINER_HEIGHT, this);
        this.paintMinerals(g);
        g2.setColor(Color.DARK_GRAY);
        g2.setStroke(new BasicStroke(4));
        g2.draw(new Line2D.Float(this.lineAnchor.x, this.lineAnchor.y, this.line.getPosition().x, this.line.getPosition().y));
    }

    private void paintMinerals(Graphics g){
        for (MineObject mineral : this.minerals){
            g.drawImage(mineral.getImage(), mineral.getPosition().x, mineral.getPosition().y, this);
        }
    }

    public void shoot(){
        this.isSpinning = false;
        this.isShooting = true;
    }

    public void addMineral(MineObject object){
        this.minerals.add(object);
    }

    public void start(){
        this.refresh.start();
    }

    public void stop(){
        this.refresh.stop();
        this.setFocusable(false);
        this.removeAll();
    }

    public void evaluateGain(MineObject object){
        if (object instanceof Sack && rand.nextInt(2) == 1) {
            this.dynamites += 1;
            this.statistics.updateDynamites(this.dynamites);
        }
        else
            this.result += object.getValue();
        this.statistics.updateResult(this.result);
    }

    public void updateTime(int time){
        this.statistics.updateTime(time);
    }

    public void updateObjects() {
        if (isSpinning)
            this.line.spin();
        else if (attachedObject != null) {
            if (this.line.isRolled()) {
                this.evaluateGain(attachedObject);
                this.detach();
            } else {
                this.line.move(MoveDirection.BACKWARD, attachedObject.getRectangle().getXSize());
                this.attachedObject.changePosition(this.line.getPosition());
            }
        } else if (isOccupied(this.line.getPosition())) {
            this.attach(this.objectAt(this.line.getPosition()));
            this.isShooting = false;
        }
        else if (isShooting) {
            if (this.mineralsArea.bucketInside(this.line.getPosition())) {
                this.line.move(MoveDirection.FORWARD, 0);
            }
            else {
                this.isShooting = false;
                this.line.move(MoveDirection.BACKWARD, 0);
            }
        }
        else{
            this.line.move(MoveDirection.BACKWARD, 0);
            if (this.line.isRolled()) {
                this.line.restore();
                isSpinning = true;
            }
        }
        this.repaint();
    }

    private void attach(MineObject mineral){
        this.attachedObject = mineral;
        this.line.changePosition(mineral.getCenter());
    }

    private void detach(){
        this.minerals.remove(this.attachedObject);
        this.attachedObject = null;
        this.line.restore();
        this.isSpinning = true;
    }

    private void explode(){
        this.detach();
        this.dynamites -= 1;
        this.statistics.updateDynamites(this.dynamites);
    }

    public MineObject objectAt(Vector2d position){
        for (MineObject mineral : this.minerals) {
            Rectangle rect = mineral.getRectangle();
            if (rect.isIn(position)) {
                return mineral;
            }
        }
        return null;
    }

    public boolean isOccupied(Vector2d position){
        for (MineObject mineral : this.minerals) {
            Rectangle rect = mineral.getRectangle();
            if (rect.isIn(position))
                return true;
        }
        return false;
    }

    public boolean doCollide(Rectangle rectangle){
        for (MineObject mineral : this.minerals) {
            Rectangle rect = mineral.getRectangle();
            if (rect.haveCommonPart(rectangle))
                return true;
        }
        return false;
    }

    public void keyNotify(KeyEvent e){
        if (e.getKeyCode() == 40 && isSpinning) {
            this.shoot();
        }
        else if (e.getKeyCode() == 38 && attachedObject != null && dynamites > 0){
            this.explode();
        }
    }

    public Rectangle getMineralsArea(){return this.mineralsArea;}
    public int getResult(){return this.result;}
    public int getDynamites(){return this.dynamites;}

}
