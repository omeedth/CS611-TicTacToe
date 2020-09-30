/*
 * Author: Alex Thomas
 * Date: 9/29/2020
 * 
 * Class Definition: Defines a concrete Tile2D object with an empty
 * tileLogic() method
 * 
*/

public class GenericTile extends Tile2D {
    
    // Attributes

    // Constructors

    public GenericTile(int x, int y) {
        super(x,y);
    }

    public GenericTile(int[] coords) {
        super(coords);
    }

    public GenericTile() {
        super(0,0);
    }

    // Getter Methods

    // Setter Methods

    // GenericTile Logic Methods

    public Object tileLogic(Object[] args) {
        // Does Nothing
        return null;
    }

}
