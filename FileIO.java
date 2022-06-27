package GamePlay;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*FileIO Class which manage opening, closing, reading and writing to the file*/

public class FileIO
{
    // Method to initialise a new class FileIO and read the specified file
    public static void main(String[] args)
    {
        FileIO fileIO = new FileIO();
        ArrayList<String> strList = fileIO.readFromFile();
        for (String str: strList)
        {
            String[] strArray = str.split(",");
            Car car = new Car(strArray[0], Integer.parseInt(strArray[1]), Integer.parseInt(strArray[2]), 0, Integer.parseInt(strArray[3]));
            System.out.println(car.display());
        }
    }

    // Method which does the file reading process
    public ArrayList<String> readFromFile()
    {
        ArrayList<String> strList = new ArrayList<>();
        try 
        {
            FileReader fileReader = new FileReader("vehicles.txt");
            try (Scanner fileInput = new Scanner(fileReader))
            {
                while(fileInput.hasNext())
                {
                    strList.add(fileInput.nextLine());
                }
            }
            try
            {
                fileReader.close();
                System.out.println("File read successful!");
            }
            catch(Exception e)
            {
                System.out.println("Close error!");
            }
        } catch(FileNotFoundException e)
        {
            System.out.println(e);
            System.out.println("File read error!");
        }
        return strList;
    }

    // Method to write the outcomes and append a string passed as an argument to the file specified by the object
    public void writeToFile(String path, String content)
    {
        try
        {
            FileWriter fileWriter = new FileWriter(path, true);
            fileWriter.append(content);
            fileWriter.append("\n");
            
            try
            {
                fileWriter.close();
                System.out.println("\nFile write successful!");
            }
            catch(Exception e)
            {
                System.out.println("Close error!");
            }
        } catch(FileNotFoundException e)
        {
            System.out.println("File not found error!");
        } catch(IOException e1)
        {
            System.out.println("File has output error!");
        }
    }
}