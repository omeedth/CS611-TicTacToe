/*
 * Author: Alex Thomas
 * Date: 9/29/2020
 * 
 * Class Definition: An abstract representation of what a board game is 
 * and defines functionality that is required
 * 
*/

import java.util.ArrayList;
import java.util.Scanner;

abstract class BoardGame {
    
     // Attributes
     private Board2D board;
     private BoardGamePieceManager pieces;
     private TeamManager teams;  
     private Scanner input;  
     
     private boolean running = false;
 
     // Constructors

     public BoardGame(int boardWidth, int boardHeight, BoardGamePieceManager pieces, TeamManager teams) {
         this.board = new Board2D(boardWidth,boardHeight);
         this.board.fillBoard();
         this.pieces = pieces;
         this.teams = teams;                       
     }
 
     // Getter Methods
 
     // Returns the BoardGame class attribute's Board object
     public Board2D getBoard() {
         return this.board;
     }
 
     // Returns the BoardGame class attribute's BoardGamePieceManager object
     public BoardGamePieceManager getBoardGamePieceManager() {
         return this.pieces;
     }
 
     // Returns the BoardGame class attribute's TeamManager object
     public TeamManager getTeamManager() {
         return this.teams;
     }
 
     // Setter Methods

     // Set the running boolean variable to true
     public void startRunning() {
        this.running = true;
     }

     // Set the running boolean variable to false
     public void stopRunning() {
         this.running = false;
     }
 
     // BoardGame Logic Methods
 
     // Starts the game - Initializes BoardGame, Runs until told to stop, and run gameLogic()
     void start() {
         init();
         startRunning();
         this.input = new Scanner(System.in);
         while(running) {
            gameLogic();
         }  
         this.input.close();       
     }

     // Possible Utility Functions To Make Separate vvv ///////////////////////////////////////////////

     // Prompts user for input with a specified prompt statement
     public String prompt(String promptString) {
        System.out.print(promptString);
        String response = "";
        try {
            response = this.input.nextLine();
        } catch(Exception e) {
            e.printStackTrace();
        }        
        return response;
     }

     // Continuously prompts user for input with a specified prompt statement
     // until String is deemed valid according to the regular expression
     public String promptTillValid(String promptString, String regex) {
         boolean invalid = true;
         String response = "";
         while(invalid) {
            response = prompt(promptString);
            if(isValidResponse(response, regex)) {
                invalid = false;
            }
         }
         return response;
     }

     // Ensures that a String matches a regex (Might be redundant)
     public boolean isValidResponse(String response, String regex) {
        return response.matches(regex);
     }

     // Attempts to convert a comma separated string into an array of ints
     public int[] csvToIntArray(String csv) {
        String[] values = csv.split(",");
        int[] intVals = new int[values.length];        
        for (int i = 0; i < values.length; i++) {
            try {
                intVals[i] = Integer.parseInt(values[i]);
            } catch(Exception e) {
                e.printStackTrace();
            }            
        }
        return intVals;
     }

     //////////////////////////////////////////////////////////////////////////////////

     abstract void init(); // setup the board game
     abstract void gameLogic(); // Defines the logic of the specific game
     abstract Object playTurn(); // Defines logic of a players turn
     abstract Team playerWon(Object argument); // Returns the Team who won or null if no one won
     abstract boolean outOfMoves(Object argument); // Returns true or false whether there are no more moves possible in the game

}
