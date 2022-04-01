package Enum;

public enum Direction {
    LEFT,
    RIGHT;

    public Direction next(){
        switch (this){
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
        }
        return null;
    }
}
