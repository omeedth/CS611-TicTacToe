# CS611 - Object Oriented TicTacToe Overview

We are creating a TicTacToe game using Object Oriented Design Principles.
We are decomposing TicTacToe into it's sub parts and thinking about the game in a physical sense as if we are physically building it.
(REEVALUATION OF INITIAL TICTACTOE IS AT THE BOTTOM)

# Installation

Download all of the .java files into it's own folder and continue to usage

# Usage

When Running the program you can compile all of the java source code into runnable byte code by running the following command

```bash
javac App.java
```

You can run the program by using this command

```bash
java App
```

You can remove all of the class files in the current folder with this command. (Be VERY CAREFUL. This will remove all class files in the folder)

```bash
rm *.class
```

# Game Setup

You MUST define the ruleset of the game before you start the game! This means that while there are empty constructors for the games like TicTacToe.java and ChaosAndOrder.java, you still must initialize the teams, players, pieces, etc. before running the game

```java
public static void main(String args[]) {

    /* Not Viable */
    TicTacToe game = new TicTacToe();
    game.start();   // doesn't know the players, teams, and pieces

    /* Viable */
    TicTacToe game2 = new TicTacToe();

    // Creating Players, Filling Teams with players, Creating Manager
    Player[] team1 = {new Player("Alex")};
    Player[] team2 = {new Player("Peter")};
    Team[] teams = {new Team(team1,"Awesome Squad"), new Team(team2,"Kool Katz"), new Team(team3, "Phenomenal People")};
    game2.getTeamManager().setTeams(teams);

    // Creating Pieces for BoardGame, and assigning teams subset of pieces
    TicTacToePiece[] pieces = {new TicTacToePiece("X"),new TicTacToePiece("O")};   
    game2.getBoardGamePieceManager().setBoardGamePieces(pieces);
    game2.getBoardGamePieceManager().setTeamAndBoardGamePieceMapping(teams[0],Arrays.copyOfRange(pieces,0,1));
    game2.getBoardGamePieceManager().setTeamAndBoardGamePieceMapping(teams[1],Arrays.copyOfRange(pieces,1,2));

    game2.start();

}
```

# Classes

***App.java*** - The Launcher for the code

***TicTacToe.java*** - Contains logic specific to TicTacToe and extends from BoardGame.java

***OrderAndChaos.java*** - Contains logic specific to OrderAndChaos and extends from BoardGame.java (*)

***BoardGame.java*** - Contains abstract logic of what a board game is, and defines some functionality that must be written in classes extending it

***Board2D.java*** - Defines a 2D grid of Tile objects and acts as a backbone for BoardGame objects

***Tile2D.java*** - Defines an abstract Tile (probably is redundant to say 2D). A tile contains a 2D coordinate of where it is on the board, and a list of pieces that may or may not be placed on it

***GenericTile.java*** - This is a concrete Tile Object that defines logic for the tileLogic() method written in Tile2D.java.

***Coordinate2D.java*** - Defines a set of (x,y) coordinates

***BoardGamePieceManager.java*** - Manages the allocation of board game pieces for teams, and keeps track of what pieces the board game uses

***BoardGamePiece.java*** - An abstract class that defines the basic functionality a BoardGamePiece might have

***TicTacToePiece.java*** - A concrete class that extends BoardGamePiece (*)

***TeamManager.java*** - Manages the order in which teams play the game, and which players of which team is currently playing. Also keeps track of all teams

***Team.java*** - Defines a vector of Player objects that have basic functionality to allow access to current player, etc.

***Player.java*** - Defines a Player object and allows basic functionality in accessing player name, etc.

***Score.java*** - Defines win/loss statistics and some basic functionality to display it to the screen

# Improvements

1. I can restrict some variables and make them protected to ensure the developer only uses appropriate functions

2. I can add Interfaces such as Turnable.java for BoardGame.java to make it more abstract, or Movable.java interface for BoardGamePiece.java, or isPlaceable.java interface for tiles that are able to have pieces placed on them etc.

3. I can improve my functionality for initializing the games and empty constructors to make it easier to define rule sets for developers, or allow developers to write their rulesets in a text file and construct the game from that

4. I can speak to others to try to figure out whether my classes have logic that surpasses its jurisdiction, or if the class doesn't have enough logic put in etc. 

5. Possible Utility functions that can be separated from certain classes and put into a separate file

6. I have multiple Java Classes that contain functions that are one line and only use the built-in function of an object contained by the class. (I'm not sure if this is redundant or not)

# Reevaluation

My initial TicTacToe used only 2 classes:
1. App.java
2. TicTacToe.java

It worked great for specifically a 1v1 3x3 Board of characters, but if I wanted to expand the board I would have had to manually change the board AND my function to detect if
someone won was hardcoded for a 3x3 board. Gathering the knowledge from the lectures I decomposed TicTacToe into it's base parts thinking about what it was.

TicTacToe IS A BoardGame... so what's a BoardGame? Since I am not perfect AND I also wanted to finish this project without over complicating it for myself I made a BoardGame
include a Board of Tiles, A BoardGamePieceManager to manage the pieces for the BoardGame, A Team Manager to manage the teams, and a Scanner for player input. I did not make BoardGame
implement "TurnBased.java" or add any more abstraction. Adding interfaces on what a BoardGame could be could make this project more extendable but I ended up still focusing on only creating
pertinent Classes for TicTacToe. For example maybe in some BoardGames you CAN'T place pieces on certain tiles. I was thinking I could add IsPlaceable.java interface or similarly Moveable.java
interface for BoardGamePiece objects. Instead I am writing it down here that I thought about the possibility of games with these properties and chose not to implement it for this project
because they would not be used.

All in all, I made sure my functions were abstract and returned the desired result no matter what the input. This being said, if an input is invalid it will input null or some indication that 
the function couldn't calculate the value/find the value etc.

This is a huge leap from my previous TicTacToe, which to be honest I don't have much to talk about besides it being very hard coded.


