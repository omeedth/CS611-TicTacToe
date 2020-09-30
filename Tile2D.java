/*
 * Author: Alex Thomas
 * Date: 9/29/2020
 * 
 * Class Definition: Defines an abstract representation of a 2D Tile object with basic logic required for more complicated tiles
 * (Tile may need to be more abstract and not allow BoardGamePiece objects to be placed on it and instead have an interface)
 * 
*/

import java.util.List;
import java.util.ArrayList;

public abstract class Tile2D {
    
    // Attributes
    private Coordinate2D coords;
    private List<BoardGamePiece> placedPieces;

    // Constructors

    public Tile2D(int x, int y) {
        if(x < 0 || y < 0) {
            System.out.println("Invalid Coordinates!");
            System.exit(-1);
        }
        coords = new Coordinate2D(x,y);   
        this.placedPieces = new ArrayList<BoardGamePiece>();     
    }

    public Tile2D(int[] position) {
        if(position.length != 2) {
            System.out.println("Invalid Argument Count!");
            System.exit(-1);
        } else if(position[0] < 0 || position[1] < 0) {
            System.out.println("Invalid Coordinates!");
            System.exit(-1);
        }
        coords = new Coordinate2D(position);
        this.placedPieces = new ArrayList<BoardGamePiece>(); 
    }

    public Tile2D() {
        this(0,0);
    }

    // Getter Methods

    // Return the 2D coordinates of the Tile
    public Coordinate2D getCoords() {
        return coords;
    } 
    
    // Returned the List of placed BoardGamePiece objects on this Tile
    public List<BoardGamePiece> getPlacedPieces() {
        return this.placedPieces;
    }

    // Setter Methods    

    // Tile Logic Methods  

    // Defines the logic that a specific Tile object might have
    public abstract Object tileLogic(Object[] args);  

    // Removes all pieces from a Tile (Might be redundant)
    public void empty() {
        this.placedPieces.clear();
    }

    // Add's a BoardgamePiece on to the List of BoardGamePiece objects this Tile contains
    public void addPiece(BoardGamePiece piece) {
        this.placedPieces.add(piece);
    }

    @Override
    public String toString() {
        // return "<Tile: " + this.getCoords().toString() + ">";
        String result;
        if(this.placedPieces.isEmpty()) {
            result = " ";
        } else if(this.placedPieces.size() == 1) {
            result = this.placedPieces.get(0).toString();
        } else {
            result = this.placedPieces.toString();
        }
        return result;
    }
    
    @Override
    public boolean equals(Object other) {
        return this.getCoords().equals(((Tile2D)other).getCoords());
    }
    
}
