package GamePlay;
import java.util.ArrayList;

/* Player Class contains the details of the player characteristics */

public class Player
{
    private String playerName;

    // Default Constructor
    public Player()
    {
        playerName = "unknown";
    }
    // Constructor method which initialise object of class Player
    public Player(String playerName)
    {
        this.playerName = playerName;
    }

    // Method to return a string that contains the details of the player object
    public String display()
    {
        return "Player [playerName=" + playerName + "]";
    }
    
    // Accessor method to get access to the player name
    public String getPlayerName()
    {
        return playerName;
    }

    // Mutator method to set the new value of the player name
    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }
}