/*
 * Author: Alex Thomas
 * Date: 9/29/2020
 * 
 * Class Definition: Defines a group of Players on the same Team,
 * and contains basic information on the team and a way to get 
 * Players within the team (May have redundant logic with TeamManager)
 * 
*/

import java.util.ArrayList;
import java.util.Arrays;

public class Team {
    
    // Attributes
    private String teamName;
    private int teamId;
    private ArrayList<Player> players;
    private Score score;
    private int currentPlayerIndex;

    private static int currTeamId = 0;

    // Constructors

    public Team(Player[] players, String teamName) {
        this.players = new ArrayList<Player>(Arrays.asList(players));
        this.setTeamName(teamName);
        this.setTeamId(currTeamId);        
        this.score = new Score();
        currentPlayerIndex = 0;
        currTeamId++;
    }

    public Team(String teamName) {
        this(new Player[1],teamName);
    }

    public Team() {
        this(new Player[1],"Team " + currTeamId);
    }

    // Getter Methods

    // Returns List of Player objects on the Team
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    // Returns number of Player objects (May be redundant)
    public int getPlayerCount() {
        return this.players.size();
    }

    // Returns Team's name
    public String getTeamName() {
        return this.teamName;
    }

    // Returns the Team's ID
    public int getTeamId() {
        return this.teamId;
    }

    // Returns the CurrentPlayerIndex
    public int getCurrentPlayerIndex() {
        return this.currentPlayerIndex;
    }    

    // Returns the Team's Score object
    public Score getScore() {
        return this.score;
    }

    // Setter Methods

    // Set the List of Player objects this team has
    public void setPlayers(ArrayList<Player> players) {
        if(players == null) {
            throw new IllegalArgumentException();
        }
        this.players = players;
    }

    // Set's the Team's name
    public void setTeamName(String name) {
        this.teamName = name;
    }

    // Set's the Team's ID
    private void setTeamId(int Id) {
        this.teamId = Id;
    }    

    // Team Logic Methods

    /**
     * Goes to the next player index and returns it
     * @return
     */
    public Player nextPlayer() {
        nextPlayerIndex();
        return getPlayerAtIndex(this.getCurrentPlayerIndex());
    }

    /**
     * Returns the current player
     * @return Player
     */
    public Player currPlayer() {
        return getPlayerAtIndex(this.getCurrentPlayerIndex());
    }

    // Returns the player at a specified index (May be redundant)
    public Player getPlayerAtIndex(int index) {
        if(index < 0 || index > this.getPlayerCount()) {
            throw new IndexOutOfBoundsException();
        }
        return this.players.get(index);
    }

    // Finds the next Player's index
    public void nextPlayerIndex() {
        if(this.currentPlayerIndex == this.players.size() - 1) {
            this.currentPlayerIndex = 0;
        } else {
            this.currentPlayerIndex++;
        }
    }

    // Displays this Team's Score
    public void showScore() {
        System.out.println("-------------------------------------------");
        System.out.println("Team: " + this);
        this.score.show();
    }

    // Reset this Team's Score
    public void resetScore() {
        this.score.resetScore();
    }

    // Reset the player order of this Team
    public void resetPlayerOrdering() {
        this.currentPlayerIndex = 0;
    }

    @Override
    public String toString() {
        return this.getTeamName();
    }

}
