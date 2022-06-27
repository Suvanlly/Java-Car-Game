package GamePlay;

public class Obstacle {
	
	private String obstacleName;
	private int addFuel;
	private int damage;
	
	public Obstacle()
	{
		
	}

	public Obstacle(String obstacleName, int addFuel, int damage) {
		this.obstacleName = obstacleName;
		this.addFuel = addFuel;
		this.damage = damage;
	}

	/**
	 * @return the obstacleName
	 */
	public String getObstacleName() {
		return obstacleName;
	}

	/**
	 * @return the addFuel
	 */
	public int getAddFuel() {
		return addFuel;
	}

	/**
	 * @return the damage
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * @param obstacleName the obstacleName to set
	 */
	public void setObstacleName(String obstacleName) {
		this.obstacleName = obstacleName;
	}

	/**
	 * @param addFuel the addFuel to set
	 */
	public void setAddFuel(int addFuel) {
		this.addFuel = addFuel;
	}

	/**
	 * @param damage the damage to set
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}


	public String display() {
		return "Obstacle [obstacleName=" + obstacleName + ", addFuel=" + addFuel + ", damage=" + damage + "]";
	}
	
	

}
