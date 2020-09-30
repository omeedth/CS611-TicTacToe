/*
 * Author: Alex Thomas
 * Date: 9/29/2020
 * 
 * Class Definition: An abstract definition of what a BoardGamePiece is
 * (It may be missing some useful logic like position)
 * 
*/

abstract class BoardGamePiece {
    
    // Attributes
    private String pieceName;

    // Constructors

    public BoardGamePiece(String pieceName) {
        this.setPieceName(pieceName);
    }

    public BoardGamePiece() {
        String defaultName = new String("DefaultName");
        this.setPieceName(defaultName);
    }

    // Getter Methods

    // Returns the name of this BoardGamePiece 
    public String getPieceName() {
        return this.pieceName;
    }

    // Setter Methods

    // Sets the name of this BoardGamePiece
    public void setPieceName(String newPieceName) {
        this.pieceName = newPieceName;
    }

    // BoardGamePiece Logic Methods

    @Override
    public String toString() {
        return this.getPieceName();
    }

    @Override
    public boolean equals(Object other) {          
        return other != null && this.getPieceName().equals( ((BoardGamePiece)other).getPieceName() );
    }    

}
