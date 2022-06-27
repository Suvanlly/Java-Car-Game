package GamePlay;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {
	
	public ArrayList<String> readFromFile(String path)
	{
		ArrayList<String> strList = new ArrayList<>();
		try {
			
			FileReader fileReader = new FileReader(path);
			try (Scanner fileInput = new Scanner(fileReader)) {
				while(fileInput.hasNext())
				{
					strList.add(fileInput.nextLine());
//				System.out.println(fileInput.nextLine());
				}
			}
			try
			{
				fileReader.close();
				System.out.println("File read succeed");
			}
			catch(Exception e)
			{
				System.out.println("Close Error");
			}
		} catch (FileNotFoundException e) {
			System.out.println("File read error");
		}
		return strList;
	}
	
	public void writeToFile(String path, String content)
	{
//		ArrayList<String> strList = new ArrayList<>();
		try {
			
			FileWriter fileWriter = new FileWriter(path, true);
			fileWriter.append(content);
			fileWriter.append("\n");

			try
			{
				fileWriter.close();
				System.out.println("File write succeed");
			}
			catch(Exception e)
			{
				System.out.println("Close Error");
			}

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found error");
		} catch (IOException e1) {
			System.out.println("File Output error");

		}
	}

	public static void main(String[] args) {
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
		}

		
	}

}
