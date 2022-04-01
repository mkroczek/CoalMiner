package Objects;
import Math.Vector2d;

import java.awt.*;

public class Coal extends MineObject {
    public Coal(int xSize, int ySize, Vector2d position, Image image,int value){
        super(xSize, ySize, position, image, value);
    }
    public Coal(Rectangle rectangle, Image image, int value){
        super(rectangle, image, value);
    }
}
