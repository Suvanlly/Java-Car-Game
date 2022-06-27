package GamePlay;

import java.util.ArrayList;
/* Player Class contains the details of the player characteristics */

public class Player
{
	private String playerName;
	
	public Player()
	{
		
	}

	public Player(String playerName) 
	{
		this.playerName = playerName;
	}

	/**
	 * @return the playerName
	 */
	public String getPlayerName() 
	{
		return playerName;
	}

	/**
	 * @param playerName the playerName to set
	 */
	public void setPlayerName(String playerName) 
	{
		this.playerName = playerName;
	}

	public String display() 
	{
		return "Player [playerName=" + playerName + "]";
	}
	
	
	
	
}