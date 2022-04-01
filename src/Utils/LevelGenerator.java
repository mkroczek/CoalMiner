package Utils;

import Constants.Constants;
import Game.MinePanel;
import Enum.*;
import Math.*;
import Objects.*;
import Objects.Rectangle;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LevelGenerator {
    private Random generator = new Random();
    private Map<Size, Integer> coalProportions = new HashMap<Size, Integer>();
    private Map<Size, Integer> stoneProportions = new HashMap<Size, Integer>();
    private Map<Size, Integer> coalValues = new HashMap<Size, Integer>();
    private Map<Size, Integer> stoneValues = new HashMap<Size, Integer>();
    private Map<Size, Integer> coalSizes = new HashMap<Size, Integer>();
    private Map<Size, Integer> stoneSizes = new HashMap<Size, Integer>();
    private Map<Size, Image> coalImages = new HashMap<Size, Image>();
    private Map<Size, Image> stoneImages = new HashMap<Size, Image>();
    private MinePanel mine;
    private int sacks;
    private int sackSize;

    public LevelGenerator(MinePanel mine){
        this.mine = mine;
        this.coalProportions.put(Size.BIG,2);
        this.coalProportions.put(Size.MEDIUM,3);
        this.coalProportions.put(Size.SMALL,5);
        this.stoneProportions.put(Size.BIG,2);
        this.stoneProportions.put(Size.MEDIUM,3);
        this.stoneProportions.put(Size.SMALL,5);
        this.coalValues.put(Size.BIG,500);
        this.coalValues.put(Size.MEDIUM,250);
        this.coalValues.put(Size.SMALL,100);
        this.stoneValues.put(Size.BIG,50);
        this.stoneValues.put(Size.MEDIUM,30);
        this.stoneValues.put(Size.SMALL,20);
        this.coalSizes.put(Size.BIG,Constants.BIG_MINERAL);
        this.coalSizes.put(Size.MEDIUM,Constants.MEDIUM_MINERAL);
        this.coalSizes.put(Size.SMALL,Constants.SMALL_MINERAL);
        this.stoneSizes.put(Size.BIG,Constants.BIG_MINERAL);
        this.stoneSizes.put(Size.MEDIUM,Constants.MEDIUM_MINERAL);
        this.stoneSizes.put(Size.SMALL,Constants.SMALL_MINERAL);
        this.coalImages.put(Size.BIG,Constants.BIG_COAL);
        this.coalImages.put(Size.MEDIUM,Constants.MEDIUM_COAL);
        this.coalImages.put(Size.SMALL,Constants.SMALL_COAL);
        this.stoneImages.put(Size.BIG,Constants.BIG_STONE);
        this.stoneImages.put(Size.MEDIUM,Constants.MEDIUM_STONE);
        this.stoneImages.put(Size.SMALL,Constants.SMALL_STONE);
        this.sacks = generator.nextInt(3);
        this.sackSize = Constants.SACK_SIZE;
    }

    private Vector2d getRandomPosition(){
        Rectangle mineralsArea = this.mine.getMineralsArea();
        int x = generator.nextInt(mineralsArea.getXSize())+mineralsArea.getPosition().x;
        int y = generator.nextInt(mineralsArea.getYSize())+mineralsArea.getPosition().y;
        return new Vector2d(x,y);
    }

    public void generate(){
        for (Size size : Size.values()){
            generateCoal(size);
            generateStone(size);
        }
        generateSacks();
    }

    public void generateCoal(Size size){
        int number = coalProportions.get(size);
        Vector2d position = getRandomPosition();
        Rectangle rectangle = new Rectangle(position, coalSizes.get(size), coalSizes.get(size));
        MineObject mineral;
        for (int i = 1; i <= number; i++){
            while (!this.mine.getMineralsArea().isIncluded(rectangle) || this.mine.doCollide(rectangle)){
                position = getRandomPosition();
                rectangle = new Rectangle(position, coalSizes.get(size), coalSizes.get(size));
            }
            mineral = new Coal(new Rectangle(rectangle), this.coalImages.get(size), this.coalValues.get(size));
            this.mine.addMineral(mineral);
        }
    }

    public void generateStone(Size size){
        int number = stoneProportions.get(size);
        Vector2d position = getRandomPosition();
        Rectangle rectangle = new Rectangle(position, stoneSizes.get(size), stoneSizes.get(size));
        MineObject mineral;
        for (int i = 1; i <= number; i++){
            while (!this.mine.getMineralsArea().isIncluded(rectangle) || this.mine.doCollide(rectangle)){
                position = getRandomPosition();
                rectangle.changePosition(position);
            }
            mineral = new Stone(new Rectangle(rectangle), this.stoneImages.get(size), this.stoneValues.get(size));
            this.mine.addMineral(mineral);
        }
    }

    public void generateSacks(){
        Vector2d position = getRandomPosition();
        Rectangle rectangle = new Rectangle(position, this.sackSize, this.sackSize);
        MineObject mineral;
        for (int i = 1; i <= sacks; i++){
            while (!this.mine.getMineralsArea().isIncluded(rectangle) || this.mine.doCollide(rectangle)){
                position = getRandomPosition();
                rectangle.changePosition(position);
            }
            mineral = new Sack(new Rectangle(rectangle), Constants.SACK);
            this.mine.addMineral(mineral);
        }
    }
}
