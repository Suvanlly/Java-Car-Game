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
    private ArrayList<Obstacle[]> obstacles;
    private int highwayLength;
    private int playerPosition;
    private int carXIndex;
    private int carYIndex;
    private int numOfObstacles;
    private String gameOutcome;
    private Difficulty difficultyMode;
    private Car currentCar;
    private Player player;

    //Constructor for objects of the class Game
    public Game()
    {
        carList = new ArrayList<>();
        currentCar = new Car();
        carXIndex = 0;
        carYIndex = 0;
        difficultyMode = Difficulty.EASY;
        gameOutcome = "unknown";
        highwayLength = 0;
        numOfObstacles = 0;
        obstacles = new ArrayList<>();
        player = new Player();
    }

    // Method to accept and set the difficulty level based on user's selection
    public void checkDifficulty(Difficulty defaultlevel)
    {
        if (defaultlevel == Difficulty.EASY)
        {
            difficultyConfiguration(10, 15, 1.0, 12);
        }
        else if (defaultlevel == Difficulty.MEDIUM)
        {
            difficultyConfiguration(15, 30, 0.8, 24);
        }
        else if (defaultlevel == Difficulty.HARD)
        {
            difficultyConfiguration(30, 50, 0.5, 45);
        }
    }

    // Method to set up various variabled based on different difficulty level
    public void difficultyConfiguration(int highwayLengthFloor, int highwayLengthCeiling, double maxFuel, int numOfObstacles)
    {
        Random random = new Random();
        highwayLength = random.generateRandom(highwayLengthFloor, highwayLengthCeiling);
        currentCar.setMaxFuel((int)(currentCar.getMaxFuel() * maxFuel));
        currentCar.setCurrentFuel(currentCar.getMaxFuel());
        this.numOfObstacles = numOfObstacles;
        // Add obstacles for each lane of total 3 lanes
        obstacles.add(new Obstacle[highwayLength]);
        obstacles.add(new Obstacle[highwayLength]);
        obstacles.add(new Obstacle[highwayLength]);
    }

    // Method to accept user input for the player name
    public void enterPlayerName()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("\nHi, Please enter your name: ");
        String name = console.nextLine().trim();
        while(name.length() < 3 || name.length() > 12)
        {
            System.out.println("\nInvalid Name! Please re-enter your name, the length of your name must between 3 to 12: ");
            name = console.nextLine().trim();
        }
        player.setPlayerName(name);
        System.out.println("\nWelcome " + player.getPlayerName() + "!");
    }

    // Method to generate different types of obstacles on highway
    public Obstacle generateOneObstacle()
    {
        Obstacle obstacle = new Obstacle();
        Random random = new Random();
        int randomNum = random.generateRandom(1, 100);

        if(randomNum <= 30)
        {
            obstacle.setObstacleName("F");
            obstacle.setAddFuel(10);
        }
        else if(randomNum > 30 && randomNum <= 70)
        {
            obstacle.setObstacleName("B");
            obstacle.setDamage(20);
        }
        else if(randomNum > 70 && randomNum <= 90)
        {
            obstacle.setObstacleName("S");
            obstacle.setDamage(45);
        }
        else if(randomNum > 90 && randomNum <= 100)
        {
            obstacle.setObstacleName("O");
            obstacle.setDamage(60);
        }
        return obstacle;
    }

    // Accessor method to get the value of game outcome
    public String getGameOutcome()
    {
        return gameOutcome;
    }

    // Method to manage various car variables when hit different obstacles
    public void hitObstacle(Obstacle obstacle)
    {
        if(obstacle.getObstacleName().equals("F"))
        {
            int newFuel = currentCar.getCurrentFuel() + 10;
            if(newFuel <= currentCar.getMaxFuel())
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
            currentCar.setMaxSustainableDamage(currentCar.getMaxSustainableDamage() - obstacle.getDamage());
        }
    }

    // Main method which initialise an instance of the Game calss and start the game
    public static void main(String[] args)
    {
        Game game = new Game();
        game.start();
    }

    // Method to manage car position and update properties when user select Boost action
    public void moveBoost()
    {
        currentCar.setCurrentFuel(currentCar.getCurrentFuel() - currentCar.getBoostSpeed() * 3);
        Obstacle[] obstacleArray = obstacles.get(carYIndex);
        for(int i = carXIndex; i <= carXIndex + currentCar.getBoostSpeed(); i++)
        {   //index of car must be less than highwayLength
            if(i < obstacleArray.length && obstacleArray[i] != null)
            {
                hitObstacle(obstacleArray[i]);
                obstacleArray[i] = null;
            }
        }
        carXIndex += currentCar.getBoostSpeed();
    }

    // Method to manage car position and update properties when user select forward action
    public void moveForward()
    {
        currentCar.setCurrentFuel(currentCar.getCurrentFuel() - 1);
        carXIndex++;
        Obstacle[] obstacleArray = obstacles.get(carYIndex);
        
        if(obstacleArray[carXIndex] != null)
        {
            hitObstacle(obstacleArray[carXIndex]);
            obstacleArray[carXIndex] = null;
        }
    }

    // Method to manage car position and update properties when user select swerve up action
    public void moveSwerveUp()
    {
        currentCar.setCurrentFuel(currentCar.getCurrentFuel() - 2);
        carXIndex++;
        carYIndex--;
        Obstacle[] obstacleArray = obstacles.get(carYIndex);

        if(obstacleArray[carXIndex] != null)
        {
            hitObstacle(obstacleArray[carXIndex]);
            obstacleArray[carXIndex] = null;
        }
    }

    // Method to manage car position and update properties when user select swerve down action
    public void moveSwerveDown()
    {
        currentCar.setCurrentFuel(currentCar.getCurrentFuel() - 2);
        carXIndex++;

        {
            carYIndex++;
        }
     
        Obstacle[] obstacleArray = obstacles.get(carYIndex);

        if(obstacleArray[carXIndex] != null)
        {
            hitObstacle(obstacleArray[carXIndex]);
            obstacleArray[carXIndex] = null;
        }
    }

    // Method to place random obstacles on highway for each lane
    public void placeObstacleOnHighway()
    {
        // There will be 1/3 chance of appearing obstacles on each lane
        int perLineObstacle = numOfObstacles/3;
        Random random = new Random();

        for(int j = 0; j < 3; j++)
        {
            Obstacle[] obstacleArray = obstacles.get(j);
            for(int i = 0; i < perLineObstacle; i++)
            {
                // There will be no obstacles on the first three sections of the highway
                int index = random.generateRandom(3, highwayLength - 1);
                // only add obstacle if there is no obstacle on that specific position
                if(obstacleArray[index] == null)
                {
                    obstacleArray[index] = generateOneObstacle();
                }
                // If there is already a obstacle on that section, re-generate that obstacle on a different random position
                else
                {
                    i--;
                }
            }
            obstacles.set(j, obstacleArray);
        }
    }

    // Method to accept user input for selecting the car move action and invoke corresponding function
    public void playerSelectAction()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("\n Please play the game by selecting your action: ");
        System.out.println("\n Enter 1 means move forward");
        System.out.println("\n Enter 2 means swerve up");
        System.out.println("\n Enter 3 means swerve down");
        System.out.println("\n Enter 4 means boost speed");

        String select = console.nextLine().trim();
        // While loop to let player play until they select the correct actions
        while(!select.equals("1") && !select.equals("2") && !select.equals("3") && !select.equals("4"))
        {
            System.out.println("\n Invalid selection! Please re-select your action: ");
            System.out.println("\n Enter 1 means move forward");
            System.out.println("\n Enter 2 means swerve up");
            System.out.println("\n Enter 3 means swerve down");
            System.out.println("\n Enter 4 means boost speed");
            select = console.nextLine().trim();
        }
        switch(select)
        {
            case "1":
                moveForward();
                break;
            case "2":
                // Need to make sure the Index of Player within the boundry of highway when swerve up
                if(carYIndex > 0)
                {
                    moveSwerveUp();
                }else
                {
                    System.out.println("\nAttention! Car can not swerve up anymore! Please re-select your action: ");
                }
                break;
            case "3":
                // Need to make sure the Index of Player within the boundry of highway when swerve down
                if(carYIndex < 2)
                {
                    moveSwerveDown();
                }else
                {
                    System.out.println("\nAttention! Car can not swerve down anymore! Please re-select your action: ");
                }
                break;
            case "4":
                moveBoost();
                break;
        }
    }

    // Method to created the highway in 2D with x and y axies and represent the play with "@" character
    public void printHighWay(int lowIndex, int highIndex)
    {
        // For loop to generate highway for each 3 lane
        for(int i = 0; i < 3; i++)
        {
            Obstacle[] obstacleArray = obstacles.get(i);
            for(int j = lowIndex; j < obstacleArray.length && j < highIndex; j++)
            {
                if(carYIndex == i && carXIndex == j && obstacleArray[j] == null)
                {
                    System.out.print("@ | ");
                }
                else if(obstacleArray[j] != null)
                {
                    System.out.print(obstacleArray[j].getObstacleName() + " | ");
                }
                else
                {
                    System.out.print("  | ");
                }
            }
            System.out.print("\n");
        }
    }

    // Method to generate random user starting position among three lanes
    public void randomStartPosition()
    {
        Random random = new Random();
        carXIndex = 0;
        carYIndex = random.generateRandom(0, 2);
    }

    // Method to read file and display the file content
    private void readVehiclesFromFile()
    {
        FileIO fileIO = new FileIO();
        ArrayList<String> strList = fileIO.readFromFile();
        for (String str: strList)
        {
            String[] strArray = str.split(",");
            Car car = new Car(strArray[0], Integer.parseInt(strArray[1]), Integer.parseInt(strArray[2]), 0, Integer.parseInt(strArray[3]));
            carList.add(car);
        }
    }

    // Method to accept user input for selecting the car and display the car properties they selected
    public Car selectCar()
    {
        System.out.println("\nPlease select your car from below: ");

        for(int i = 1; i <= carList.size(); i++)
        {
            System.out.println("Enter " + i + " means: Car-" + i + " " + carList.get(i - 1).display());
        }
        Scanner console = new Scanner(System.in);
        int selectIndex = 0;
        try
        {
            selectIndex = Integer.parseInt(console.nextLine());
        }
        catch (Exception e)
        {
            System.out.println("\nPlease only enter the number to select car");
        }
        System.out.println("\nGood Choice! The car you have selected is " + "Car-" + selectIndex);

        return carList.get(selectIndex - 1);
    }

    // Method to accept user input for selecting the difficulty level of the game and display the difficulty level they selected
    public Difficulty selectDifficultyLevel()
    {
        Difficulty defaultLevel = Difficulty.EASY;
        Scanner console = new Scanner(System.in);
        System.out.println("\nPlease select your difficulty level: ");
        System.out.println("Enter 1 means Easy level");
        System.out.println("Enter 2 means Moderate level");
        System.out.println("Enter 3 means Hard level");

        String select = console.nextLine().trim();
        // While loop for user to choose until they enter the correct difficulty level
        while(!select.equals("1") && !select.equals("2") && !select.equals("3"))
        {
            System.out.println("\nInvalid selection, Please re-select your difficulty level: ");
            System.out.println("Enter 1 means Easy level");
            System.out.println("Enter 2 means Moderate level");
            System.out.println("Enter 3 means Hard level");
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
        System.out.println("\nHi, You have selected level " + defaultLevel);
        return defaultLevel;
    }

    // Mutator method to set the new value of game outcome
    public void setGameOutcome(String gameOutcome)
    {
        this.gameOutcome = gameOutcome;
    }

    // Method to start the game and runs a loop which manage the game process and write the outcomes to the "gameOutput" file
    public void start()
    {
        readVehiclesFromFile();
        enterPlayerName();
        difficultyMode = selectDifficultyLevel();
        currentCar = selectCar();
        checkDifficulty(difficultyMode);

        System.out.println(currentCar.display());
        System.out.println("\nThe length of Highway is " + highwayLength + ", below shows the highway map of the game, you are represented with character:@");

        placeObstacleOnHighway();
        randomStartPosition();
        printHighWay(0, 100);
        // While loop for player to select actions to keep playing the game until they successfully win the game
        while(carXIndex < highwayLength - 1 && currentCar.getCurrentFuel() > 0 && currentCar.getMaxSustainableDamage() > 0)
        {
            playerSelectAction();
            printHighWay(0, 100);

            System.out.println(currentCar.display());
            if(carXIndex >= highwayLength - 1)
            {
                System.out.println("\nCongratulations! You have won the game!");
                setGameOutcome("Win");
            }
        }
        // If player's current fuel less than zero means they run out of fuel and fail the game
        if(currentCar.getCurrentFuel() <= 0)
        {
            System.out.println("\nSorry, Game Over... Because you have run out of fuel.");
            setGameOutcome("Lose");
        }
        // If player get damage more than their maximum sustainable damage they will fail the game
        else if(currentCar.getMaxSustainableDamage() <= 0)
        {
            System.out.println("\nSorry, Game Over... Because you have suffered damage greater than your max sustainable damage level");
            setGameOutcome("Lose");
        }

        FileIO fileIO = new FileIO();
        fileIO.writeToFile("gameOutput.txt", "Player Name: " + player.getPlayerName() + ", Game Outcome: " + getGameOutcome() + ", " + currentCar.display());
    }
}