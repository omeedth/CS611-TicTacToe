/*
 * Author: Alex Thomas
 * Date: 9/29/2020
 * 
 * Class Definition: Defines the game logic for OrderAndChaos
 * 
*/

import java.util.List;

public class OrderAndChaos extends TicTacToe {
    
    // Attributes

    // Constructors

    public OrderAndChaos(int boardWidth, int boardHeight, BoardGamePieceManager pieces, TeamManager teams, int winningCondition) {
        super(boardWidth,boardWidth,pieces,teams,winningCondition);                 
    }

    public OrderAndChaos(int boardDimensions, BoardGamePieceManager pieces, TeamManager teams, int winningCondition) {
        this(boardDimensions,boardDimensions,pieces,teams,winningCondition);                
    }

    public OrderAndChaos() {
        this(6, new BoardGamePieceManager(), new TeamManager(), 5);        
    }

    // Getter Methods

    // Setter Methods

    // TicTacToe Logic Methods

    @Override
    public void init() {
        // Ensure the game has the correct logic required to run the game
        if(this.getTeamManager().getTeams().size() != 2) {
            System.out.println("Requires EXACTLY two teams to play");
            System.exit(-1);
        } else if(this.getBoardGamePieceManager().getBoardGamePieces().size() != 2) {
            System.out.println("Requires EXACTLY two pieces to play");
            System.exit(-1);
        }
    }

    @Override
    public void gameLogic() {

        this.getBoard().displayBoard();

        int[] coords = (int[])playTurn();

        Team winner = this.playerWon(coords);

        // Increment the score of the winners and losers if a winner 
        // was found, and ask the user if they would like to play again
        if (winner != null) {
            this.getBoard().displayBoard();

            System.out.printf("Team: %s, Won!\n",winner);
            this.getTeamManager().updateWinsLosses(winner);

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
    public Team playerWon(Object argument) {
        int[] move = (int[])argument;
        Team winner = (kConsecutiveValuesFrom(this.getWinningCondition(),move[0],move[1]) ? this.getTeamManager().getTeams().get(0) : null);

        // If Order did NOT win AND there are no more moves possible (Chaos Wins)
        if(winner == null) {
            if(outOfMoves(null)) {
                winner = this.getTeamManager().getTeams().get(1);
            }
        }            
        
        return winner;
    }

}
