package Objects;

import Math.*;

public class Rectangle {
    private Vector2d position;
    private final int xSize;
    private final int ySize;

    public Rectangle(Vector2d position, int sizeX, int sizeY){
        this.position = position;
        this.xSize = sizeX;
        this.ySize = sizeY;
    }

    public Rectangle(Rectangle another){
        //copy constructor
        this.position = another.position;
        this.xSize = another.xSize;
        this.ySize = another.ySize;
    }

    public Vector2d getPosition() {
        return position;
    }

    public boolean isIn(Vector2d objectPosition) {
        Vector2d rightBottom = position;
        Vector2d leftTop = new Vector2d(position.x + xSize, position.y+ySize);
        return rightBottom.precedes(objectPosition) && leftTop.follows(objectPosition);
    }

    public boolean bucketInside(Vector2d objectPosition) {
        return this.position.x <= objectPosition.x && objectPosition.x <= this.position.x+xSize && objectPosition.y <= this.position.y+ySize;
    }

    public boolean haveCommonPart(Rectangle rect){
        if (rect.position.x > this.position.x+xSize || this.position.x > rect.position.x+ rect.xSize) {
            return false;
        }
        else if (rect.position.y > this.position.y+ySize || this.position.y > rect.position.y+ rect.ySize) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean isIncluded(Rectangle rect){
        //checks if rect is included in this
        if (rect.position.x >= this.position.x && rect.position.x+rect.xSize <= this.position.x+this.xSize &&
                rect.position.y >= this.position.y && rect.position.y+rect.ySize <= this.position.y+this.ySize)
            return true;
        return false;
    }

    public Vector2d getCenter(){
        return new Vector2d((2*position.x + xSize)/2,(2*position.y + ySize)/2);
    }

    public Vector2d getDecentredPosition(Vector2d center){
        return new Vector2d(center.x-xSize/2, center.y-ySize/2);
    }

    public int getXSize(){return xSize;}
    public int getYSize(){return ySize;}

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Rectangle other = (Rectangle) obj;
        if (!this.position.equals(other.getPosition()))
            return false;
        if (this.xSize != other.getXSize() || this.ySize != other.getYSize())
            return false;
        return true;
    }

    public void changePosition(Vector2d newPosition){
        this.position = newPosition;
    }
}
