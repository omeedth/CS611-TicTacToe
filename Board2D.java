/*
 * Author: Alex Thomas
 * Date: 9/29/2020
 * 
 * Class Definition: Defines a 2 dimensional board for use in board games
 * that contain Tile objects
 * 
*/

public class Board2D {

    // Attributes
    private Tile2D[][] matrix;
    private int width, height;

    // Constructors

    public Board2D(int width, int height) {
        this.setWidth(width);
        this.setHeight(height);
        this.matrix = new Tile2D[this.getHeight()][this.getWidth()];
    }

    public Board2D(int dimensions) {
        this(dimensions, dimensions);
    }

    // Getter Methods

    // Returns 2D array of Tile objects (Tile2D[][])
    public Tile2D[][] getMatrix() {
        return this.matrix;
    }

    // Returns width of board (int)
    public int getWidth() {
        return this.width;
    }

    // Returns height of board (int)
    public int getHeight() {
        return this.height;
    }

    // Returns Tile2D object if valid coordinates (Tile2D)
    public Tile2D getTile(int x, int y) {
        if(y < 0 || y > this.getHeight()) {
            throw new IllegalArgumentException();
        } else if(x < 0 || x > this.getWidth()) {
            throw new IllegalArgumentException();
        }
        return this.matrix[y][x];
    }

    // Returns Tile2D object if valid coordinates (Tile2D)
    public Tile2D getTile(int[] coords) {
        if (coords.length != 2) {
            throw new IllegalArgumentException();
        }
        return getTile(coords[0],coords[1]);
    }

    // Setter Methods

    // Sets the Board's class attribute width to a new valid value
    public void setWidth(int newWidth) {
        if(newWidth < 0) {
            throw new IllegalArgumentException();
        }
        this.width = newWidth;
    }

    // Sets the Board's class attribute height to a new valid value
    public void setHeight(int newHeight) {
        if(newHeight < 0) {
            throw new IllegalArgumentException();
        }
        this.height = newHeight;
    }

    // Board Logic Methods

    // Not Named Well
    // This is a generic fillBoard() method that fills entire board
    // with GenericTile objects
    public void fillBoard() {
        for(int row = 0; row < this.getHeight(); row++) {
            for(int col = 0; col < this.getWidth(); col++) {
                this.matrix[row][col] = new GenericTile(col,row);
            }
        }
    }

    // Get's rid of all the pieces placed on the board
    public void emptyBoard() {
        for(int row = 0; row < this.getHeight(); row++) {
            for(int col = 0; col < this.getWidth(); col++) {
                this.getTile(col, row).empty();
            }
        }
    }

    // Returns true or false whether or not the coordinates are on the board
    public boolean areCoordsValid(int x, int y) {
        return (x >= 0 && x < this.getWidth()) && (y >= 0 && y < this.getHeight());
    }

    // Returns true or false whether or not the coordinates are on the board
    public boolean areCoordsValid(int[] coords) {
        return (coords.length == 2) && areCoordsValid(coords[0], coords[1]);
    }

    // Returns true or false whether or not tile has pieces on it already
    public boolean isTileOccupied(int[] coords) {
        return !this.matrix[coords[1]][coords[0]].getPlacedPieces().isEmpty();
    }

    // Adds a piece on the Tile object specified at (x,y)
    public void placePieceAt(int x, int y, BoardGamePiece piece) {
        this.matrix[y][x].addPiece(piece);
    }

    // Displays the Board on the console
    public void displayBoard() {

        // Top Part
        for(int col = 0; col < this.getWidth(); col++) {
            System.out.print("+---");
        }
        System.out.println("+");

        // Middle Part
        for(int row = 0; row < this.getHeight(); row++) {
            for(int col = 0; col < this.getWidth(); col++) {
                System.out.print("| " + this.getTile(col,row) + " ");
            }
            System.out.println("|");
            for(int col = 0; col < this.getWidth(); col++) {
                System.out.print("+---");
            }
            System.out.println("+");
        }        

    }

}
