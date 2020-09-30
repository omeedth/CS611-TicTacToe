/*
 * Author: Alex Thomas
 * Date: 9/29/2020
 * 
 * Class Definition: Manages the allocation of BoardGamePiece objects
 * with Team objects, and contains the full list of pieces associated
 * with the Board Game
 * 
*/

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class BoardGamePieceManager {
    
    // Attributes
    private HashMap<Team,List<BoardGamePiece>> teamPieces;
    private ArrayList<BoardGamePiece> boardGamePieces;    

    // Constructors

    public BoardGamePieceManager(List<Team> teams, List<List<BoardGamePiece>> pieces, List<BoardGamePiece> boardGamePieces) {
        if (teams.size() != pieces.size()) {
            System.out.println("List Mismatch: The lengths of the two lists are not the same!");
            System.exit(-1);
        }        
        this.teamPieces = new HashMap<Team,List<BoardGamePiece>>();
        this.boardGamePieces = new ArrayList<BoardGamePiece>(boardGamePieces);
        for(int i = 0; i < teams.size(); i++){
            Team t = teams.get(i);
            List<BoardGamePiece> t_pieces = pieces.get(i);
            teamPieces.put(t,t_pieces);
        }
    }

    public BoardGamePieceManager(Team[] teams, BoardGamePiece[][] pieces, BoardGamePiece[] boardGamePieces) {
        this(Arrays.asList(teams),Arrays.stream(pieces).map(Arrays::asList).collect(Collectors.toList()),Arrays.asList(boardGamePieces));
    }

    public BoardGamePieceManager() {
        this.teamPieces = new HashMap<Team,List<BoardGamePiece>>();
        this.boardGamePieces = new ArrayList<BoardGamePiece>();
    }

    // Getter Methods

    // Returns the List of BoardGamePiece objects mapped to a Team
    public List<BoardGamePiece> getTeamsPieces(Team t) {
        return this.teamPieces.getOrDefault(t,null);
    }

    // Returns the List of BoardGamePiece objects associated with the BoardGame object using this
    public List<BoardGamePiece> getBoardGamePieces() {
        return this.boardGamePieces;
    }

    // Setter Methods

    // Maps a List of BoardGamePiece objects (that MUST be a subset of
    // the BoardGamePiece objects defined in the BoardGame) to a Team
    public void setTeamAndBoardGamePieceMapping(Team t, List<BoardGamePiece> pieces) {  
        if(t == null || pieces == null) {
            System.out.println("The team or the pieces are null!");
            System.exit(-1);
        } else if(!this.boardGamePieces.containsAll(pieces)) {
            System.out.println("Attempting to map pieces that don't exist in the board game");
            System.exit(-1);
        }      
        this.teamPieces.put(t,pieces);
    }

    // Maps an array of BoardGamePiece objects (that MUST be a subset of
    // the BoardGamePiece objects defined in the BoardGame) to a Team
    public void setTeamAndBoardGamePieceMapping(Team t, BoardGamePiece[] pieces) {
        this.setTeamAndBoardGamePieceMapping(t, Arrays.asList(pieces));
    }

    // Sets the List of BoardGamePiece objects associated with the BoardGame
    public void setBoardGamePieces(ArrayList<BoardGamePiece> pieces) {
        this.boardGamePieces = pieces;
    }

    // Sets the Array of BoardGamePiece objects associated with the BoardGame
    public void setBoardGamePieces(BoardGamePiece[] pieces) {
        setBoardGamePieces(new ArrayList<BoardGamePiece>(Arrays.asList(pieces)));
    }

    // BoardGamePieceManager Logic Methods    

    // Removes the mapping for the specified Team (Might be redundant)
    public void removeTeamAndBoardGamePieceMapping(Team t) {
        this.teamPieces.remove(t);
    }

    // Removes all mappings for Teams and Lists of BoardGamePieces (Might be redundant)
    public void clearMappings() {
        this.teamPieces.clear();
    }

}
