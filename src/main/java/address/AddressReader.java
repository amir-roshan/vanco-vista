package main.java.address;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class which reads address data from a file and returns an ArrayList of Address objects.
 *
 *  @author Amir Roshan
 * @version 1.0
 */
public class AddressReader
{
    private static final int UNIT_NUMBER_INDEX = 0;
    private static final int STREET_NUMBER_INDEX = 1;
    private static final int STREET_NAME_INDEX = 2;
    private static final int POSTAL_CODE_INDEX = 3;
    private static final int CITY_INDEX = 4;

    /**
     * Reads address data from the specified file and returns an ArrayList of Address objects.
     *
     * @param file the file to read from
     * @return an ArrayList of Address objects
     * @throws FileNotFoundException if the file is not found
     */
    public static ArrayList<Address> readAddressData(final File file) throws FileNotFoundException
    {
        final ArrayList<Address> addresses;
        final Scanner scanner;

        String line;
        String[] parts;
        String unitNumber;
        String streetName;
        String postalCode;
        String city;
        int streetNumber;

        addresses = new ArrayList<>();
        scanner = new Scanner(file);

        while(scanner.hasNextLine())
        {
            line = scanner.nextLine();
            parts = line.split("\\|");

            unitNumber = parts[UNIT_NUMBER_INDEX];
            streetNumber = Integer.parseInt(parts[STREET_NUMBER_INDEX]);
            streetName = parts[STREET_NAME_INDEX];
            postalCode = parts[POSTAL_CODE_INDEX];
            city = parts[CITY_INDEX];

            Address address = new Address(unitNumber, streetNumber, streetName, postalCode, city);
            addresses.add(address);
        }

        scanner.close();
        return addresses;
    }
}
