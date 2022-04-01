package Objects;

import java.awt.*;
import java.util.Random;

import Math.Vector2d;

public abstract class MineObject {
    protected Rectangle rectangle;
    protected final Image image;
    protected final int value;
    protected Random generator = new Random();

    public MineObject(int xSize, int ySize, Vector2d position, Image image, int value){
        this.rectangle = new Rectangle(position, xSize, ySize);
        this.image = image;
        this.value = value;
    }

    public MineObject(Rectangle rectangle, Image image, int value){
        this.rectangle = rectangle;
        this.image = image;
        this.value = value;
    }

    public MineObject(Rectangle rectangle, Image image){
        this.rectangle = rectangle;
        this.image = image;
        this.value = generator.nextInt(500);
    }

    public Vector2d getPosition(){
        return this.rectangle.getPosition();
    }

    public Rectangle getRectangle(){
        return this.rectangle;
    }

    public Vector2d getCenter(){
        return this.rectangle.getCenter();
    }

    public Image getImage(){return this.image;}

    public void changePosition(Vector2d newPosition){
        this.rectangle.changePosition(this.rectangle.getDecentredPosition(newPosition));
    }

    public int getValue(){return this.value;}

    public boolean includePosition(Vector2d position){
        return this.rectangle.isIn(position);
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MineObject other = (MineObject) obj;
        if (!this.rectangle.equals(other.rectangle))
            return false;
        return true;
    }
}
