package GamePlay;

public class Random
{   
    // generate random Integer between range min and max 
    public int generateRandom(int min, int max)
    {
        return (int)(Math.random() * (max - min + 1) + min);
    }
}