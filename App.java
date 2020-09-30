/*
 * Author: Alex Thomas
 * Assignment: CS 611 - TicTacToe2 Object Oriented Principles
 * Date: 9/29/2020
 * 
 * Class Definition: This is the launcher class that you put the
 * basic game launch code here
 * 
*/

import java.util.Arrays;
import java.util.ArrayList;

public class App {
    
    public static void main(String args[]) {

        /*------------------------Tic Tac Toe------------------------*/

        /* Define the rules for TicTacToe */
        // int boardDimensions = 6;
        // TicTacToePiece[] pieces = {new TicTacToePiece("X"),new TicTacToePiece("O"), new TicTacToePiece("P")};
        // Player[] team1 = {new Player("Alex")};
        // Player[] team2 = {new Player("Peter")};
        // Player[] team3 = {new Player("Leo")};
        // Team[] teams = {new Team(team1,"Awesome Squad"), new Team(team2,"Kool Katz"), new Team(team3, "Phenomenal People")};

        // BoardGamePieceManager boardGamePieceManager = new BoardGamePieceManager();
        // boardGamePieceManager.setBoardGamePieces(pieces);
        // boardGamePieceManager.setTeamAndBoardGamePieceMapping(teams[0],Arrays.copyOfRange(pieces,0,1));
        // boardGamePieceManager.setTeamAndBoardGamePieceMapping(teams[1],Arrays.copyOfRange(pieces,1,2));
        // boardGamePieceManager.setTeamAndBoardGamePieceMapping(teams[2],Arrays.copyOfRange(pieces,2,3));

        // TeamManager teamManager = new TeamManager(teams);
        // int winningCondition = 3;

        /* Instantiate the TicTacToe Game using the rule set */
        // TicTacToe game = new TicTacToe(boardDimensions,boardGamePieceManager,teamManager,winningCondition);
        // game.start();

        /*------------------------Order and Chaos------------------------*/

        /* Define the rules for Order and Chaos */
        int boardDimensions = 6;
        TicTacToePiece[] pieces = {new TicTacToePiece("X"),new TicTacToePiece("O")};
        Player[] team1 = {new Player("Alex")};
        Player[] team2 = {new Player("Peter")};
        Team[] teams = {new Team(team1,"Order"), new Team(team2,"Chaos")};

        BoardGamePieceManager boardGamePieceManager = new BoardGamePieceManager();
        boardGamePieceManager.setBoardGamePieces(pieces);
        boardGamePieceManager.setTeamAndBoardGamePieceMapping(teams[0],pieces);
        boardGamePieceManager.setTeamAndBoardGamePieceMapping(teams[1],pieces);

        TeamManager teamManager = new TeamManager(teams);
        int winningCondition = 5;

        /* Instantiate the Order and Chaos Game using the rule set */
        OrderAndChaos game = new OrderAndChaos(boardDimensions,boardGamePieceManager,teamManager,winningCondition);
        game.start();

    }

}
