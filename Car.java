package GamePlay;

/* Car Class contains the details of the car types, fuel level, speed level and maxSustainableDamage level */

public class Car
{
    private String carType;
    private int boostSpeed;
    private int maxFuel;
    private int currentFuel;
    private int maxSustainableDamage;

    // Default Constructor
    public Car()
    {
        carType = "unknown";
        boostSpeed = 0;
        maxFuel = 0;
        currentFuel = 0;
        maxSustainableDamage = 0;
    }
    
    // Constructor method which initialise object of class Car
    public Car(String carType, int boostSpeed, int maxFuel, int currentFuel, int maxSustainableDamage)
    {
        this.carType = carType;
        this.boostSpeed = boostSpeed;
        this.maxFuel = maxFuel;
        this.currentFuel = currentFuel;
        this.maxSustainableDamage = maxSustainableDamage;
    }

    // Method to return a string that contains the details of the Car object
    public String display()
    {
        return "[carType: " + carType + ", boostSpeed: " + boostSpeed + ", maxFuel: " + maxFuel + ", currentFuel: "
            + currentFuel + ", maxSustainableDamage: " + maxSustainableDamage + "]";
    }

    // Accessor method to get access to the boost speed
    public int getBoostSpeed()
    {
        return boostSpeed;
    }

    // Accessor method to get access to the current fuel
    public int getCurrentFuel()
    {
        return currentFuel;
    }

    // Accessor method to get access to the maximum fuel
    public int getMaxFuel()
    {
        return maxFuel;
    }

    // Accessor method to get access to the maximum sustainable damage
    public int getMaxSustainableDamage()
    {
        return maxSustainableDamage;
    }

    // Mutator method to set the new value of the car type
    public void setCarType(String carType)
    {
        this.carType = carType;
    }

    // Mutator method to set the new value of the boost speed
    public void setBoostSpeed(int boostSpeed)
    {
        this.boostSpeed = boostSpeed;
    }

    // Mutator method to set the new value of the current fuel
    public void setCurrentFuel(int currentFuel)
    {
        this.currentFuel = currentFuel;
    }

    // Mutator method to set the new value of the maximum fuel
    public void setMaxFuel(int maxFuel)
    {
        this.maxFuel = maxFuel;
    }

    // Mutator method to set the new value of the maximum sustainable damage
    public void setMaxSustainableDamage(int maxSustainableDamage)
    {
        this.maxSustainableDamage = maxSustainableDamage;
    }

}