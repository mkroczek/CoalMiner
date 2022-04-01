package Enum;

public enum MoveDirection {
    FORWARD,
    BACKWARD;

    public MoveDirection next(){
        switch (this){
            case FORWARD:
                return BACKWARD;
            case BACKWARD:
                return FORWARD;
        }
        return null;
    }
}
