package GamePlay;

import java.util.ArrayList;
import java.util.Scanner;
/*Game Class contains main() and handles most of the game play*/

public class Game
{
    private enum Difficulty
    {
        EASY,
        MEDIUM,
        HARD
    }

    private ArrayList<Car> carList;
    private int highwayLength;
    private int playerPosition;
    private Player player;
    private Difficulty difficultyMode;
    private Car currentCar;
    private int numOfObstacles;
    private ArrayList<Obstacle[]> obstacles;
    private int carXIndex;
    private int carYIndex;

    
    public Game()
    {
    	carList = new ArrayList<>();
    	player = new Player();
    	difficultyMode = Difficulty.EASY;
    	highwayLength = 0;
    	currentCar = new Car();
    	numOfObstacles = 0;
    	obstacles = new ArrayList<>();
    	carXIndex = 0;
    	carYIndex = 0;
//    	obstacles = new int[3][];
    }

    public void start()
    {
    	readVehiclesFromFile();
    	enterPlayerName();
    	difficultyMode = selectDifficultyLevel();
    	currentCar = selectCar();
    	checkDifficulty(difficultyMode);
    	System.out.println(currentCar.display());
    	System.out.println("Highway length is " + highwayLength);
    	placeObstacleOnHighWay();
    	randomStartPosition();
    	printHighWay(0,100);
    	while (carXIndex < highwayLength 
    			&& currentCar.getCurrentFuel() > 0 
    			&& currentCar.getMaxSustainableDamage() > 0)
    	{
    		playerSelectAction();
        	printHighWay(0,100);

//        	printHighWay(carXIndex,carXIndex + 10);
        	System.out.println(currentCar.display());
        	if (carXIndex == highwayLength - 1)
        	{
        		System.out.println("Congrats! You win the game.");
        	}
    	}
    	System.out.println("Game Over");
    	FileIO fileIO = new FileIO();
		fileIO.writeToFile("src/GamePlay/vehiclesOutput.txt", currentCar.display());
    }
    
    public void playerSelectAction()
    {
    	Scanner console = new Scanner(System.in);
    	System.out.println("\n Please select your action: ");
    	System.out.println("\n Enter 1 means Move Forward");
    	System.out.println("\n Enter 2 means Swerve up");
    	System.out.println("\n Enter 3 means Swerve down");
    	System.out.println("\n Enter 4 means Boost");

    	String select = console.nextLine().trim();
    	while (!select.equals("1") && !select.equals("2") 
    			&& !select.equals("3") && !select.equals("4"))
    	{
        	System.out.println("\n Please re-select your action: ");
        	System.out.println("\n Enter 1 means Move Forward");
        	System.out.println("\n Enter 2 means Swerve up");
        	System.out.println("\n Enter 3 means Swerve down");
        	System.out.println("\n Enter 4 means Boost");
        	System.out.println("\n You can only enter 1,2,3,4");
        	select = console.nextLine().trim();
    	}
    	switch(select)
    	{
	    	case "1":
	    		moveForward();
	    		break;
	    	case "2":
	    		moveSwerveUp();
	    		break;
	    	case "3":
	    		moveSwerveDown();
	    		break;
	    	case "4":
	    		moveBoost();
	    		break;
    	}
    }
    
    private void readVehiclesFromFile()
    {
			FileIO fileIO = new FileIO();
			ArrayList<String> strList = fileIO.readFromFile("vehicles.txt");
			for (String str: strList)
			{
				String[] strArray = str.split(",");
				Car car = new Car(strArray[0],
						Integer.parseInt(strArray[1]),
						Integer.parseInt(strArray[2]),
						0,
						Integer.parseInt(strArray[3]));
				System.out.println(car.display());
				carList.add(car);
			}

    }
    
    public void enterPlayerName()
    {
    	Scanner console = new Scanner(System.in);
    	System.out.println("\n Please enter your name: ");
    	String name = console.nextLine().trim();
    	while (name.length() < 3 || name.length() > 12)
    	{
    		System.out.println("Please re-enter your name, the length must between 3~12: ");
        	name = console.nextLine().trim();
    	}
    	player.setPlayerName(name);
    	System.out.println("Welcome " + player.getPlayerName());

    }
    
    public Difficulty selectDifficultyLevel()
    {
    	Difficulty defaultLevel = Difficulty.EASY;
    	Scanner console = new Scanner(System.in);
    	System.out.println("\n Please select your difficulty level: ");
    	System.out.println("\n Enter 1 means Easy level");
    	System.out.println("\n Enter 2 means Moderate level");
    	System.out.println("\n Enter 3 means Hard level");

    	String select = console.nextLine().trim();
    	while (!select.equals("1") && !select.equals("2") && !select.equals("3"))
    	{
        	System.out.println("\n Please re-select your difficulty level: ");
        	System.out.println("\n Enter 1 means Easy level");
        	System.out.println("\n Enter 2 means Moderate level");
        	System.out.println("\n Enter 3 means Hard level");        	
        	System.out.println("\n You can only enter 1,2,3");
        	select = console.nextLine().trim();

    	}
    	switch(select)
    	{
    	case "1":
    		defaultLevel = Difficulty.EASY;
    		break;
    	case "2":
    		defaultLevel = Difficulty.MEDIUM;
    		break;
    	case "3":
    		defaultLevel = Difficulty.HARD;
    		break;
    	
    	}
    	System.out.println("You select level " + defaultLevel);
    	return defaultLevel;
    }
    
    public void difficcultyConfiguration(int highWayLengthFloor, int highWayLengthCeiling, double maxFuel, int numOfObstacles)
    {
    	Random random = new Random();
    	highwayLength = random.generateRandom(highWayLengthFloor, highWayLengthCeiling);
    	currentCar.setMaxFuel((int)(currentCar.getMaxFuel() * maxFuel));
    	currentCar.setCurrentFuel(currentCar.getMaxFuel());
    	this.numOfObstacles = numOfObstacles;
    	obstacles.add(new Obstacle[highwayLength]);
    	obstacles.add(new Obstacle[highwayLength]);
    	obstacles.add(new Obstacle[highwayLength]);
//    	Obstacle[] oArry = obstacles.get(0);
//    	System.out.println("Default " +oArry[0]);
    }
    
    public void checkDifficulty(Difficulty defaultLevel)
    {
    	if (defaultLevel == Difficulty.EASY)
    	{
    		difficcultyConfiguration(10,15,1.0,12);
    	}
    	else if (defaultLevel == Difficulty.MEDIUM)
    	{
    		difficcultyConfiguration(15,30,0.8,24);
    	}
    	else if (defaultLevel == Difficulty.HARD)
    	{
    		difficcultyConfiguration(30,50,0.5,45);
    	}
    }
    
    public Car selectCar()
    {
    	System.out.println("Please select car number in below list: ");
    	for (int i = 1; i <= carList.size();i++)
    	{
    		System.out.println(i + carList.get(i-1).display());
    	}
    	Scanner console = new Scanner(System.in);
    	int selectIndex = 0; 
    	try {
        	selectIndex = Integer.parseInt(console.nextLine());
    	}
    	catch (Exception e){
    		System.out.println("You can only enter a number");
    	}
		System.out.println("You select car is " + selectIndex);
//		System.out.println(carList.get(selectIndex - 1).display());
    	return carList.get(selectIndex - 1);
    }
    
    public void placeObstacleOnHighWay()
    {
    	int perLineObstacle = numOfObstacles/3;
    	Random random = new Random();
    	for (int j = 0 ;j < 3 ; j++)
    	{
    		Obstacle[] obstacleArray = obstacles.get(j);
        	for (int i = 0; i < perLineObstacle; i++)
        	{
        		int index = random.generateRandom(3, highwayLength-1);
        		if (obstacleArray[index] == null)
        		{
        			obstacleArray[index] = generateOneObstacle();
        		}
        		else
        		{
            		i--;
        		}

        	}
        	obstacles.set(j,obstacleArray);
    	}
    	
    }
    
    public void printHighWay(int lowIndex, int highIndex)
    {
    	System.out.println("================================");
    	for (int i = 0; i < 3; i++)
    	{
    		Obstacle[] obstacleArray = obstacles.get(i);
    		for (int j = lowIndex; j < obstacleArray.length && j < highIndex; j++)
    		{
    			if (carYIndex == i && carXIndex == j && obstacleArray[j] == null)
    			{
    				System.out.print("@ | ");
    			}
    			else if (obstacleArray[j] != null)
    			{
//    				if (j <= carXIndex && i == carYIndex)
//    				{
//    					hitObstacle(obstacleArray[j]);
//    					obstacleArray[j] = null;
//    					if (j <= carXIndex && i == carYIndex)
//    					{
//    						System.out.print("@ | ");
//    					}
//    				}
//    				else
//    				{
    					System.out.print(obstacleArray[j].getObstacleName() + " | ");
//    				}
    			}
    			else
    			{
    				System.out.print("  | ");
    			}
    		}
    		System.out.print("\n");
    	}
    	System.out.println("================================");
    }
    
    public void hitObstacle(Obstacle obstacle)
    {
    	if (obstacle.getObstacleName().equals("F"))
    	{
    		int newFuel = currentCar.getCurrentFuel() + 10;
    		if (newFuel <= currentCar.getMaxFuel())
    		{
        		currentCar.setCurrentFuel(currentCar.getCurrentFuel() + 10);
    		}
    		else
    		{
        		currentCar.setCurrentFuel(currentCar.getMaxFuel());
    		}
    	}
    	else 
    	{
    		currentCar.setMaxSustainableDamage(
    				currentCar.getMaxSustainableDamage() - obstacle.getDamage());
    	}
    	
    }
    
    public Obstacle generateOneObstacle()
    {
    	Obstacle obstacle = new Obstacle();
    	Random random = new Random();

    		int randomNum = random.generateRandom(1, 100);
    		if (randomNum <= 30)
    		{
    			obstacle.setObstacleName("F");
    			obstacle.setAddFuel(10);
    		}
    		else if (randomNum > 30 && randomNum <= 70)
    		{
    			obstacle.setObstacleName("B");
    			obstacle.setDamage(20);
    		}
    		else if (randomNum > 70 && randomNum <= 90)
    		{
    			obstacle.setObstacleName("S");
    			obstacle.setDamage(45);
    		}
    		else if (randomNum > 90 && randomNum <= 100)
    		{
    			obstacle.setObstacleName("O");
    			obstacle.setDamage(60);
    		}
    			return obstacle;
    }
    
    public void randomStartPosition()
    {
    	Random random = new Random();
    	carXIndex = 0;
    	carYIndex = random.generateRandom(0, 2);
    }
    
    public void moveForward()
    {
    	currentCar.setCurrentFuel(currentCar.getCurrentFuel() - 1);
    	carXIndex++;
    	Obstacle[] obstacleArray = obstacles.get(carYIndex);
    	if (obstacleArray[carXIndex] != null)
		{
			hitObstacle(obstacleArray[carXIndex]);
			obstacleArray[carXIndex] = null;
		}
    	
    	
    }
    
    public void moveSwerveUp()
    {
    	currentCar.setCurrentFuel(currentCar.getCurrentFuel() - 2);
    	carXIndex++;
    	carYIndex--;
    	Obstacle[] obstacleArray = obstacles.get(carYIndex);
    	if (obstacleArray[carXIndex] != null)
		{
			hitObstacle(obstacleArray[carXIndex]);
			obstacleArray[carXIndex] = null;
		}
    }
    
    public void moveSwerveDown()
    {
    	currentCar.setCurrentFuel(currentCar.getCurrentFuel() - 2);
    	carXIndex++;
    	carYIndex++;
    	Obstacle[] obstacleArray = obstacles.get(carYIndex);
    	if (obstacleArray[carXIndex] != null)
		{
			hitObstacle(obstacleArray[carXIndex]);
			obstacleArray[carXIndex] = null;
		}
    }
    
    public void moveBoost()
    {
    	currentCar.setCurrentFuel(currentCar.getCurrentFuel() - currentCar.getBoostSpeed() * 3);
    	Obstacle[] obstacleArray = obstacles.get(carYIndex);
    	for (int i = carXIndex; i <= carXIndex + currentCar.getBoostSpeed();i++)
    	{
    		if (obstacleArray[i] != null)
				{
    			hitObstacle(obstacleArray[i]);
    			obstacleArray[i] = null;
				}
    	}
    	carXIndex += currentCar.getBoostSpeed();

    }
    
    public static void main(String[] args) 
    {
    	Game game = new Game();
    	game.start();
    }
    
    
}

