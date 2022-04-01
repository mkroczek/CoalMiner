package Objects;
import Math.Vector2d;

import java.awt.*;

public class Stone extends MineObject {
    public Stone(int xSize, int ySize, Vector2d position, Image image, int value){
        super(xSize, ySize, position, image, value);
    }
    public Stone(Rectangle rectangle, Image image, int value){
        super(rectangle, image, value);
    }

}
