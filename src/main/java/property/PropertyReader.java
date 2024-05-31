package main.java.property;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class which reads data from a file
 * @author Amir Roshan
 * @version 1.0
 */
public class PropertyReader
{
    /**
     * Reads data from a file and returns it as an ArrayList of strings.
     * Each element in the ArrayList represents a line from the file.
     *
     * @param file the file to read data from
     * @return an ArrayList containing the data read from the file
     * @throws FileNotFoundException if the specified file does not exist
     */
    public static ArrayList<String> readPropertyData(final File file) throws FileNotFoundException
    {
        final ArrayList<String> propertyData;
        final Scanner scanner;

        propertyData = new ArrayList<>();
        scanner      = new Scanner(file);

        while(scanner.hasNextLine())
        {
            final String line = scanner.nextLine();
            propertyData.add(line);
        }

        scanner.close();
        return propertyData;
    }
}
