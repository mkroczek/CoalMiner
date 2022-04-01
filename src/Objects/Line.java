package Objects;

import Constants.Constants;
import Game.MinePanel;
import Math.*;
import Enum.*;

public class Line {
    private final double startLength;
    private Polar position;
    private Vector2d shift;
    private double angularVelocity = Math.PI/1000*Constants.DELAY/4;
    private double fiMin, fiMax;
    private double deltaR = Constants.SHOOTING_SPEED;
    private Direction direction;
    private MinePanel mine;

    public Line(double startLength, Polar startPosition, Vector2d shift, double fiMax, double fiMin, MinePanel mine){
        this.fiMax = fiMax;
        this.fiMin = fiMin;
        this.direction = Direction.RIGHT;
        this.shift = shift;
        this.startLength = startLength;
        this.position = startPosition;
        this.mine = mine;
    }

    public Vector2d getPosition(){
        Vector2d pos = this.position.toVector2d();
        return new Vector2d(pos.x+shift.x, pos.y+shift.y);
    }

    public void changePosition(Vector2d position){
        this.position = position.toPolar(this.shift);
    }

    public boolean isRolled(){
        return this.position.r <= this.startLength;
    }

    public void restore(){
        this.position.r = startLength;
    }

    public void move(MoveDirection direction, int size){
        double velocity;

        if (size <= Constants.SMALL_MINERAL)
            velocity = deltaR;
        else if (size > Constants.SMALL_MINERAL && size <= Constants.MEDIUM_MINERAL)
            velocity = deltaR*0.5;
        else
            velocity = deltaR*0.2;

        switch (direction){
            case FORWARD -> this.position = this.position.add(velocity, 0);
            case BACKWARD -> this.position = this.position.diff(velocity,0);
        }
    }

    public void spin(){
        if (this.position.fi+angularVelocity > fiMax || this.position.fi-angularVelocity < fiMin) {
            this.direction = this.direction.next();
        }
        switch (this.direction){
            case LEFT -> this.position = this.position.diff(0,angularVelocity);
            case RIGHT -> this.position = this.position.add(0,angularVelocity);
        }
    }
}
