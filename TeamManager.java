/*
 * Author: Alex Thomas
 * Date: 9/29/2020
 * 
 * Class Definition: Manages groups of Teams, their ordering, and their
 * functions
 * 
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class TeamManager {
    
    // Attributes
    private ArrayList<Team> teams;
    private int currentTeamIndex;

    // Constructors

    public TeamManager(List<Team> teams) {
        this.teams = new ArrayList<Team>(teams);      
        this.currentTeamIndex = 0;
    }

    public TeamManager(Team teams[]) {
        this(Arrays.asList(teams));
    }

    public TeamManager() {
        this(new Team[1]);        
    }

    // Getter Methods

    // Returns the List of the Team objects
    public ArrayList<Team> getTeams() {
        return this.teams;
    }

    // Return the currentTeamIndex - (Can expand for more complicated ordering)
    public int getCurrentTeamIndex() {
        return this.currentTeamIndex;
    }

    // Setter Methods

    // Sets the List of Team objects that this TeamManager will manage
    public void setTeams(ArrayList<Team> newTeam) {
        if(newTeam == null) {
            throw new IllegalArgumentException();
        }
        this.teams = newTeam;
    }

    // Sets the List of Team objects that this TeamManager will manage
    public void setTeams(Team[] newTeam) {
        if(newTeam == null) {
            throw new IllegalArgumentException();
        }
        ArrayList<Team> teams = new ArrayList<>(Arrays.asList(newTeam));
        this.setTeams(teams);
    }

    // TeamManager Logic Methods

    /**
     * Goes to the next Team and returns it
     */
    public Team nextTeam() {
        nextTeamIndex();
        return getTeamAtIndex(this.currentTeamIndex);
    }

    /**
     * Returns the current team without moving pointer forward
     */
    public Team currTeam() {
        return getTeamAtIndex(this.currentTeamIndex);
    }

    /**
     * Returns the current player of the current team
     */
    public Player currPlayer() {
        return currTeam().currPlayer();
    }

    // Increments this Team's currentPlayerIndex to the next one
    public void nextPlayerIndex() {
        currTeam().nextPlayerIndex();
    }

    // Get's the next Team in the order - (Can expand for more complicated ordering)
    public void nextTeamIndex() {
        if(this.currentTeamIndex == this.teams.size() - 1) {
            this.currentTeamIndex = 0;
        } else {
            this.currentTeamIndex++;
        }
    }

    // Updates the wins and losses of all the Team objects
    // Passed in team is the "Winning Team" and the rest are losers
    public void updateWinsLosses(Team team) {
        for(Team t : this.teams) {
            if(t != team) {
                t.getScore().incrementLosses();
            } else {
                t.getScore().incrementWins();
            }
        }
    }

    // Increments the stalemate count for ALL Team objects
    public void updateStalemate() {
        for(Team t : this.teams) {
            t.getScore().incrementStalemates();
        }
    }

    // In the case of a tie, return the Team with the most points as 
    // the winner
    public Team getTieBreaker() {
        Team tiebreaker = null;
        int scoreToBeat = currTeam().getScore().getPoints();
        for(Team t : this.teams) {
            if(t.getScore().getPoints() > scoreToBeat) {
                tiebreaker = t;
                scoreToBeat = t.getScore().getPoints();
            }
        }
        return tiebreaker;
    }

    // Returns the Team at the specifies index if valid
    public Team getTeamAtIndex(int index) {
        if(index < 0 || index >= teams.size()) {
            throw new IndexOutOfBoundsException();
        }
        return this.teams.get(index);
    }

    // Reset the points for ALL Team objects
    public void resetPoints() {
        for(Team t : this.teams) {
            t.getScore().resetPoints();
        }
    }

    // Reset the score for ALL Team objects
    public void resetScore() {
        for(Team t : this.teams) {
            t.getScore().resetScore();
        }
    }

    // Displays the Score for ALL Team objects
    public void showScore() {
        for(Team t : this.teams) {
            t.showScore();
        }
    }

    // Reset the player order in ALL Team objects
    public void resetPlayerOrder() {
        this.currentTeamIndex = 0;
        for(Team t : this.teams) {
            t.resetPlayerOrdering();
        }
    }

}
