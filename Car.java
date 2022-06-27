package GamePlay;

public class Car 
{
	
	private String carType;
	private int boostSpeed;
	private int maxFuel;
	private int currentFuel;
	private int maxSustainableDamage;
	
	public Car()
	{
		
	}
	
	public Car(String carType, int boostSpeed, int maxFuel, int currentFuel, int maxSustainableDamage) 
	{
		this.carType = carType;
		this.boostSpeed = boostSpeed;
		this.maxFuel = maxFuel;
		this.currentFuel = currentFuel;
		this.maxSustainableDamage = maxSustainableDamage;
	}

	/**
	 * @return the carType
	 */
	public String getCarType() {
		return carType;
	}

	/**
	 * @return the boostSpeed
	 */
	public int getBoostSpeed() {
		return boostSpeed;
	}

	/**
	 * @return the maxFuel
	 */
	public int getMaxFuel() {
		return maxFuel;
	}

	/**
	 * @return the currentFuel
	 */
	public int getCurrentFuel() {
		return currentFuel;
	}

	/**
	 * @return the maxSustainableDamage
	 */
	public int getMaxSustainableDamage() {
		return maxSustainableDamage;
	}

	/**
	 * @param carType the carType to set
	 */
	public void setCarType(String carType) {
		this.carType = carType;
	}

	/**
	 * @param boostSpeed the boostSpeed to set
	 */
	public void setBoostSpeed(int boostSpeed) {
		this.boostSpeed = boostSpeed;
	}

	/**
	 * @param maxFuel the maxFuel to set
	 */
	public void setMaxFuel(int maxFuel) {
		this.maxFuel = maxFuel;
	}

	/**
	 * @param currentFuel the currentFuel to set
	 */
	public void setCurrentFuel(int currentFuel) {
		this.currentFuel = currentFuel;
	}

	/**
	 * @param maxSustainableDamage the maxSustainableDamage to set
	 */
	public void setMaxSustainableDamage(int maxSustainableDamage) {
		this.maxSustainableDamage = maxSustainableDamage;
	}

	public String display() {
		return "Car [carType=" + carType + ", boostSpeed=" + boostSpeed + ", maxFuel=" + maxFuel + ", currentFuel="
				+ currentFuel + ", maxSustainableDamage=" + maxSustainableDamage + "]";
	}
	
	
}
