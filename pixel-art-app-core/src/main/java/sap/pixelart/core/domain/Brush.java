package sap.pixelart.core.domain;

/**
 * 
 * Brush entity
 * 
 */
public class Brush {
    private int x, y;
    private RGBColor color;

    public Brush(int x, int y, RGBColor color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void updatePosition(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    // write after this getter and setters
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public RGBColor getColor(){
        return this.color;
    }
    public void setColor(RGBColor color){
        this.color = color;
    }
}
