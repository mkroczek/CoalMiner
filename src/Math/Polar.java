package Math;

import java.util.Map;

public class Polar {
    public double r;
    public double fi;

    public Polar(double r, double fi){
        this.r = r;
        this.fi = fi;
    }

    public Vector2d toVector2d(){
        int x = (int) (r*Math.sin(fi));
        int y = (int) (r*Math.cos(fi));
        return new Vector2d(x, y);
    }

    public Polar add(double r, double fi){
        return new Polar(r+this.r, fi+this.fi);
    }

    public Polar diff(double r, double fi){
        return new Polar(this.r-r, this.fi-fi);
    }

    @Override
    public String toString() {
        return "Polar(" +
                "r=" + r +
                ", fi=" + fi +
                ')';
    }
}
