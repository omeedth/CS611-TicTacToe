/*
 * Author: Alex Thomas
 * Date: 9/29/2020
 * 
 * Class Definition: Contains a 2D coordinate (x,y)
 * 
*/

public class Coordinate2D {
    
    // Attributes
    private int x, y; // y -> row, x -> col

    // Constructors

    public Coordinate2D(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public Coordinate2D(int coordinates[]) {
        this.setCoordinates(coordinates);
    }

    public Coordinate2D() {
        this(0,0);
    }

    // Getter Methods

    // Returns X coordinate
    public int getX() {
        return this.x;
    }

    // Returns Y coordinate
    public int getY() {
        return this.y;
    }

    // Returns Y coordinate (Ease of use for working on matrices)
    public int getRow() {
        return this.getY();
    }
    
    // Returns X coordinate (Ease of use for working on matrices)
    public int getCol() {
        return this.getX();
    }

    // Setter Methods

    // Set's the X coordinate for this Coordinate Object
    public void setX(int newX) {
        this.x = newX;
    }

    // Set's the Y coordinate for this Coordinate Object
    public void setY(int newY) {
        this.y = newY;
    }

    // Set's the X coordinate for this Coordinate Object (Ease of use for working on matrices)
    public void setCol(int newX) {
        this.setX(newX);
    }

    // Set's the Y coordinate for this Coordinate Object (Ease of use for working on matrices)
    public void setRow(int newY) {
        this.setY(newY);
    }

    // Sets BOTH X and Y given an array of (x,y) values
    public void setCoordinates(int coordinates[]) {
        if(coordinates == null) {
            throw new IllegalArgumentException();
        } else if(coordinates.length != 2) {
            throw new IllegalArgumentException();
        }
        this.setX(coordinates[0]);
        this.setY(coordinates[1]);
    }

    // Coordinate2D Logic Methods

    @Override
    public String toString() {
        return "(" + getX() + "," + getY() + ")";
    }

    @Override
    public boolean equals(Object other) {
        Coordinate2D otherCoord = (Coordinate2D) other;
        return this.getX() == otherCoord.getX() && this.getY() == otherCoord.getY();
    }

}
