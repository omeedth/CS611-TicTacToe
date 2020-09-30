/*
 * Author: Alex Thomas
 * Date: 9/29/2020
 * 
 * Class Definition: Defines the statistics of wins, losses, stalemates,
 * and points and some functions to incrememnt and calculate Score stats
 * 
*/

public class Score {
    
    // Attributes
    private int points;
    private int wins;
    private int losses;
    private int stalemates;

    // Constructors

    public Score() {
        points = wins = losses = stalemates = 0;        
    }

    // Getter Methods

    public int getPoints() {
        return this.points;
    }

    public int getWins() {
        return this.wins;
    }

    public int getLosses() {
        return this.losses;
    }

    public int getStalemates() {
        return this.stalemates;
    }

    // Setter Methods

    // Score Logic Methods

    // Add points to current amount
    public void addPoints(int points) {
        this.points += points;
    }

    // Increase win count by 1
    public void incrementWins() {
        this.wins++;
    }

    // Increase loss count by 1
    public void incrementLosses() {
        this.losses++;
    }

    // Increase stalemate count by 1
    public void incrementStalemates() {
        this.stalemates++;
    }

    // Set points to 0
    public void resetPoints() {
        this.points = 0;
    }

    // Sets points, wins, losses, and stalemates to 0
    public void resetScore() {
        this.resetPoints();
        this.wins = this.losses = this.stalemates = 0;
    }

    // Displays the stats
    public void show() {     
        System.out.printf("Victories: %d\tLosses: %d\tStalemates: %d\n",this.getWins(),this.getLosses(),this.getStalemates());
    }

}
