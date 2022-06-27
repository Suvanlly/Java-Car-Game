package GamePlay;
/*Obstacle Class which manage various random obstacles generated for highway*/

public class Obstacle
{
    private String obstacleName;
    private int addFuel;
    private int damage;

    // Default Constructor
    public Obstacle()
    {
        obstacleName = "unknown";
        addFuel = 0;
        damage = 0;
    }

    // Constructor method which initialise the object of class Obstacle
    public Obstacle(String obstacleName, int addFuel, int damage)
    {
        this.obstacleName = obstacleName;
        this.addFuel = addFuel;
        this.damage = damage;
    }

    // Method to return a string that contains the details of the obstacle object
    public String display()
    {
        return "Obstacle [obstacleName= " + obstacleName + ", addFuel= " + addFuel + ", damage= " + damage + "]";
    }

    // Accessor method to get access to the add fuel
    public int getAddFuel()
    {
        return addFuel;
    }

    // Accessor method to get access to the damage
    public int getDamage()
    {
        return damage;
    }

    // Accessor method to get access to the obstacle name
    public String getObstacleName()
    {
        return obstacleName;
    }

    // Mutator method to set the new value of add fuel
    public void setAddFuel(int addFuel)
    {
        this.addFuel = addFuel;
    }

    // Mutator method to set the new value of damage
    public void setDamage(int damage)
    {
        this.damage = damage;
    }

    // Mutator method to set the new value of obstacle name
    public void setObstacleName(String obstacleName)
    {
        this.obstacleName = obstacleName;
    }
}