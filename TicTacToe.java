/*
 * Author: Alex Thomas
 * Date: 9/29/2020
 * 
 * Class Definition: Defines the game logic for TicTacToe
 * 
*/

import java.util.List;

public class TicTacToe extends BoardGame {
    
    // Attributes
    private int winningCondition; // Number of tiles with the same piece in a row required to win

    // Constructors

    public TicTacToe(int boardWidth, int boardHeight, BoardGamePieceManager pieces, TeamManager teams, int winningCondition) {
        super(boardWidth,boardWidth,pieces,teams);        
        this.setWinningCondition(winningCondition);
    }

    public TicTacToe(int boardDimensions, BoardGamePieceManager pieces, TeamManager teams, int winningCondition) {
        this(boardDimensions,boardDimensions,pieces,teams,winningCondition);                
    }

    public TicTacToe() {
        this(3, new BoardGamePieceManager(), new TeamManager(), 3);        
    }

    // Getter Methods

    // Returns the winningCondition
    public int getWinningCondition() {
        return this.winningCondition;
    }

    // Setter Methods

    // Sets the winningCondition
    public void setWinningCondition(int winningCondition) {
        if(winningCondition <= 0 || winningCondition > this.getBoard().getWidth()) {
            throw new IllegalArgumentException();
        }
        this.winningCondition = winningCondition;
    }

    // TicTacToe Logic Methods

    @Override
    public void init() {

    }

    // Get rid of all the pieces placed on the board, and set the order
    // of the players back to the start
    public void reset() {
        this.getBoard().emptyBoard();
        this.getTeamManager().resetPlayerOrder();
    }

    @Override
    public void gameLogic() {

        this.getBoard().displayBoard();

        int[] coords = (int[])playTurn();

        Team winner = playerWon(coords);
        boolean noPossibleMoves = outOfMoves(coords);

        if (winner != null || noPossibleMoves) {
            this.getBoard().displayBoard();
        }

        // Increment the score of the winners and losers if a winner 
        // was found
        if (winner != null) {
            System.out.printf("Team: %s, Won!\n",winner);
            this.getTeamManager().updateWinsLosses(winner);
        } else if (noPossibleMoves) {
            Team tiebreaker = this.getTeamManager().getTieBreaker();
            if(tiebreaker == null) {
                System.out.printf("Stalemate!\n");
                this.getTeamManager().updateStalemate();
            } else {
                System.out.printf("Team: %s, Won from a tiebreaker!\n",tiebreaker);
                this.getTeamManager().updateWinsLosses(tiebreaker);
            }
        }

        // Ask the user if they would like to play again
        if (winner != null || noPossibleMoves) {
            this.getTeamManager().resetPoints();
            String response = promptTillValid("Would you like to continue playing (y/n): ", "^(y|n)$");
            if (response.equals("n")) {
                this.getTeamManager().showScore();
                stopRunning();                
            } else {
                this.reset();
                return;
            }
        }

        this.getTeamManager().nextPlayerIndex();
        this.getTeamManager().nextTeamIndex();

    }

    @Override
    public Object playTurn() {

        System.out.printf("Team: %s, Player: %s\'s turn!\n",this.getTeamManager().currTeam(),this.getTeamManager().currPlayer());

        // Define the boundaries of the Board
        int startRow = 0;
        int startCol = 0;
        int endRow = this.getBoard().getHeight() - 1;
        int endCol = this.getBoard().getWidth() - 1;

        // Continuously prompt user until they type a valid cell to place
        // their BoardGamePiece
        boolean occupiedCell = true;
        int[] coords = null;
        while(occupiedCell) {
            String response = promptTillValid("Where would you like to place your piece (x,y): ", "^([" + startRow + "-" + endRow + "],[" + startCol + "-" + endCol + "])$");
            coords = csvToIntArray(response);
            occupiedCell = this.getBoard().isTileOccupied(coords);
        }

        // Prompt the user to choose one of the pieces they would like
        // to place out of their mapped pieces (Continue until valid)
        List<BoardGamePiece> teamsPieces = this.getBoardGamePieceManager().getTeamsPieces(this.getTeamManager().currTeam());
        boolean validPiece = false;
        int index = -1;
        while(!validPiece) {
            String response = promptTillValid("Which piece would you like to use?\nAvailable Pieces: " + teamsPieces + "\nInput Here (piece index): ","^[0-9]*$");
            // Response is now definitely a digit
            index = Integer.parseInt(response);
            if(index >= 0 && index < teamsPieces.size()) {
                validPiece = true;
            }
        }
        
        this.getBoard().placePieceAt(coords[0], coords[1], teamsPieces.get(index));
        return coords;

    }

    @Override
    public Team playerWon(Object argument) {
        int[] move = (int[])argument;
        return (kConsecutiveValuesFrom(this.winningCondition,move[0],move[1]) ? this.getTeamManager().currTeam() : null);
    }

    protected boolean kConsecutiveValuesFrom(int k, int x, int y) {

        // Defines the boundary you need to check to see if the player
        // won given the coordinates (x,y) where they placed their piece
        int up = Math.max(y - (k - 1), 0);
        int down = Math.min(y + (k - 1), this.getBoard().getHeight() - 1);
        int left = Math.max(x - (k - 1), 0);
        int right = Math.min(x + (k - 1), this.getBoard().getWidth() - 1); 
        
        int ltDiag, l, rbdiag, b;
        ltDiag = l = rbdiag = b = 1;

        // Loop over this winnable boundary to see if we find "k" tiles
        // in a row with the same placed piece (Order Wins)
        int difference = Math.max(down - up, right - left);        
        for(int i = 0; i < difference; i++) {
            if((this.getBoard().areCoordsValid(left + i, up + i) && this.getBoard().areCoordsValid(left + i + 1, up + i + 1)) && (!this.getBoard().getTile(left + i, up + i).getPlacedPieces().isEmpty()) && this.getBoard().getTile(left + i, up + i).getPlacedPieces().equals(this.getBoard().getTile(left + i + 1, up + i + 1).getPlacedPieces())) ltDiag++;
            else ltDiag = 1;

            if((this.getBoard().areCoordsValid(left + i, y) && this.getBoard().areCoordsValid(left + i + 1, y)) && (!this.getBoard().getTile(left + i, y).getPlacedPieces().isEmpty()) && this.getBoard().getTile(left + i, y).getPlacedPieces().equals(this.getBoard().getTile(left + i + 1, y).getPlacedPieces())) l++;
            else l = 1;

            if((this.getBoard().areCoordsValid(left + i, down - i) && this.getBoard().areCoordsValid(left + i + 1, down - i - 1)) && (!this.getBoard().getTile(left + i, down - i).getPlacedPieces().isEmpty()) && this.getBoard().getTile(left + i, down - i).getPlacedPieces().equals(this.getBoard().getTile(left + i + 1, down - i - 1).getPlacedPieces())) rbdiag++;
            else rbdiag = 1;

            if((this.getBoard().areCoordsValid(x, down - i) && this.getBoard().areCoordsValid(x, down - i - 1)) && (!this.getBoard().getTile(x, down - i).getPlacedPieces().isEmpty()) && this.getBoard().getTile(x, down - i).getPlacedPieces().equals(this.getBoard().getTile(x, down - i - 1).getPlacedPieces())) b++;
            else b = 1;

            if(ltDiag >= k || l >= k || rbdiag >= k || b >= k) return true;
        }

        return false;
    }

    @Override
    public boolean outOfMoves(Object argument) {  
        // O(n^2) - VERY BAD, but it is just a prototype    
        // Checks every single Tile on the board to see if it has a placed piece    
        for(int row = 0; row < this.getBoard().getHeight(); row++) {
            for(int col = 0; col < this.getBoard().getWidth(); col++) {
                if(this.getBoard().getTile(col, row).getPlacedPieces().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

}
