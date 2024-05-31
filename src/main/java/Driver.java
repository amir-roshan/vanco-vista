package main.java;

import main.java.address.Address;
import main.java.address.AddressReader;
import main.java.property.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

/**
 * Represents a driver class for the real estate agency.
 * The driver class initializes
 */
public class Driver
{
    private static final int ADDRESS_INDEX_COUNTER = 0;

    private static final String ADDRESS_FILE_PATH = "address_data.txt";
    private static final String PROPERTY_FILE_PATH = "property_data.txt";

    private static final int PROPERTY_PRICE_IN_USD_INDEX = 0;

    private static final int RESIDENCE_NUMBER_OF_BEDROOMS_INDEX = 1;
    private static final int RESIDENCE_SWIMMING_POOL_INDEX = 2;
    private static final int RESIDENCE_STRATA_INDEX = 5;
    private static final int RESIDENCE_PROPERTY_TYPE_INDEX = 3;
    private static final int RESIDENCE_PROPERTY_ID_INDEX = 4;

    private static final int COMMERCIAL_PROPERTY_TYPE_INDEX = 1;
    private static final int COMMERCIAL_PROPERTY_ID_INDEX = 2;
    private static final int COMMERCIAL_LOADING_DOCK_INDEX = 3;
    private static final int COMMERCIAL_HIGHWAY_ACCESS_INDEX = 4;

    private static final int RETAIL_PROPERTY_TYPE_INDEX = 1;
    private static final int RETAIL_PROPERTY_ID_INDEX = 2;
    private static final int RETAIL_SQUARE_FOOTAGE_INDEX = 3;
    private static final int RETAIL_CUSTOMER_PARKING_INDEX = 4;

    private final Scanner scanner;
    private final Agency agency;

    /**
     * Constructs a new driver object with the specified parameters.
     * @param agency the real estate agency
     */
    public Driver(final Agency agency)
    {
        this.scanner = new Scanner(System.in);
        this.agency = agency;
    }

    /**
     * The main method.
     * @param args the command line arguments
     * @throws FileNotFoundException if the file is not found
     */
    public static void main(final String[] args) throws FileNotFoundException
    {
        final Agency agency;
        final Driver d;

        agency = new Agency("VancoVista");

        d = new Driver(agency);
        d.init();
        d.doSearches();
    }

    /**
     * Initializes the real estate agency with the property data.
     * @throws FileNotFoundException if the file is not found
     */
    public void init() throws FileNotFoundException
    {
        final ArrayList<Address> addresses;
        final ArrayList<String> properties;
        final File addressfile;
        final File propertyfile;

        int addressesIndex;

        addressfile = new File(ADDRESS_FILE_PATH);
        addresses = AddressReader.readAddressData(addressfile);
        propertyfile = new File(PROPERTY_FILE_PATH);
        properties = PropertyReader.readPropertyData(propertyfile);

        addressesIndex = ADDRESS_INDEX_COUNTER;

        for(final String line : properties)
        {
            final String[] rawData;
            rawData = line.split("\\|");

            if(rawData[RESIDENCE_PROPERTY_TYPE_INDEX].equalsIgnoreCase("residence"))
            {
                agency.addProperty(new Residence(Double.parseDouble(rawData[PROPERTY_PRICE_IN_USD_INDEX]),
                                                 addresses.get(addressesIndex),
                                                 Integer.parseInt(rawData[RESIDENCE_NUMBER_OF_BEDROOMS_INDEX]),
                                                 Boolean.parseBoolean(rawData[RESIDENCE_SWIMMING_POOL_INDEX]),
                                                 rawData[RESIDENCE_PROPERTY_TYPE_INDEX],
                                                 rawData[RESIDENCE_PROPERTY_ID_INDEX],
                                                 Boolean.parseBoolean(rawData[RESIDENCE_STRATA_INDEX])));
            }
            else if(rawData[COMMERCIAL_PROPERTY_TYPE_INDEX].equalsIgnoreCase("commercial"))
            {
                agency.addProperty(new Commercial(Double.parseDouble(rawData[PROPERTY_PRICE_IN_USD_INDEX]),
                                                  addresses.get(addressesIndex),
                                                  rawData[COMMERCIAL_PROPERTY_TYPE_INDEX],
                                                  rawData[COMMERCIAL_PROPERTY_ID_INDEX],
                                                  Boolean.parseBoolean(rawData[COMMERCIAL_LOADING_DOCK_INDEX]),
                                                  Boolean.parseBoolean(rawData[COMMERCIAL_HIGHWAY_ACCESS_INDEX])));
            }
            else
            {
                agency.addProperty(new Retail(Double.parseDouble(rawData[PROPERTY_PRICE_IN_USD_INDEX]),
                                              addresses.get(addressesIndex),
                                              rawData[RETAIL_PROPERTY_TYPE_INDEX],
                                              rawData[RETAIL_PROPERTY_ID_INDEX],
                                              Integer.parseInt(rawData[RETAIL_SQUARE_FOOTAGE_INDEX]),
                                              Boolean.parseBoolean(rawData[RETAIL_CUSTOMER_PARKING_INDEX])));
            }

            addressesIndex++;
        }
    }

    /**
     * Handles the property searches.
     */
    public void doSearches()
    {
        int choice;
        boolean exit;

        exit = false;

        while(!exit)
        {
            System.out.println(" ");
            System.out.println("Welcome to our Property search.");
            System.out.println("""
                                       1. General Queries
                                       2. Residence Queries
                                       3. Commercial Queries
                                       4. Retail Queries
                                       5. Exit""");

            choice = scanner.nextInt();

            switch(choice)
            {
                case 1 -> handleGeneralQueries();
                case 2 -> handleResidenceQueries();
                case 3 -> handleCommercialQueries();
                case 4 -> handleRetailQueries();
                case 5 -> exit = handleExit(exit);
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /*
     * Handles the general queries.
     */
    private void handleGeneralQueries()
    {
        boolean exit;
        exit = false;

        while(!exit)
        {
            final String choiceString;
            final int choice;

            System.out.println(" ");
            System.out.println("General Queries");
            System.out.println("""
                                       1. By Property ID
                                       2. By Street
                                       3. By Price
                                       4. By Type
                                       5. Back""");

            choice = scanner.nextInt();

            switch(choice)
            {
                case 1 ->
                {
                    System.out.println("Enter the property ID:");
                    choiceString = scanner.next();

                    System.out.println(agency.getProperty(choiceString));
                }
                case 2 ->
                {
                    System.out.println("Enter the street:");
                    choiceString = scanner.next();

                    for(final Address p : agency.getPropertiesOn(choiceString))
                    {
                        System.out.println(p);
                    }
                }
                case 3 ->
                {
                    final int minPrice;
                    final int maxPrice;

                    System.out.println("Enter the min price:");
                    minPrice = scanner.nextInt();
                    System.out.println("Enter the max price:");
                    maxPrice = scanner.nextInt();

                    for(final Property p : agency.getPropertiesBetween(minPrice, maxPrice))
                    {
                        System.out.println(p);
                    }
                }
                case 4 ->
                {
                    System.out.println("Enter the type:");
                    choiceString = scanner.next();

                    for(final Property p : agency.getPropertiesOfType(choiceString))
                    {
                        System.out.println(p);
                    }
                }
                case 5 -> exit = true;
                default ->
                {
                    // Nothing to do, will return to main menu
                }
            }
        }
    }

    /*
     * Handles the residence queries.
     */
    private void handleResidenceQueries()
    {
        boolean exit;
        exit = false;

        while(!exit)
        {
            final int choice;

            System.out.println(" ");
            System.out.println("Residence Queries");
            System.out.println("""
                                       1. By Pool
                                       2. By Bedroom
                                       3. By Strata
                                       4. Back""");

            choice = scanner.nextInt();

            switch(choice)
            {
                case 1 ->
                {
                    for(final Property p : agency.getPropertiesWithPools())
                    {
                        System.out.println(p);
                    }
                }
                case 2 ->
                {
                    final int minBedrooms;
                    final int maxBedrooms;
                    final Set<String> keys;

                    System.out.println("Enter the minimum number of bedrooms:");
                    minBedrooms = scanner.nextInt();

                    System.out.println("Enter the maximum number of bedrooms:");
                    maxBedrooms = scanner.nextInt();

                    keys = agency.getPropertiesWithBedrooms(minBedrooms, maxBedrooms).keySet();

                    for(String key : keys)
                    {
                        System.out.println(agency.getPropertiesWithBedrooms(minBedrooms, maxBedrooms).get(key));
                    }
                }
                case 3 ->
                {
                    for(Property p : agency.getPropertiesWithStrata())
                    {
                        System.out.println(p);
                    }
                }
                case 4 -> exit = true;
                default ->
                {
                    // Nothing to do, will return to main menu
                }
            }
        }
    }

    /*
     * Handles the commercial queries.
     */
    private void handleCommercialQueries()
    {
        boolean exit;
        exit = false;

        while(!exit)
        {

            final int choice;

            System.out.println(" ");
            System.out.println("Commercial Queries");
            System.out.println("""
                                       1. By Loading Dock
                                       2. By Highway Access
                                       3. Back""");

            choice = scanner.nextInt();

            switch(choice)
            {
                case 1 ->
                {
                    for(Property p : agency.getPropertiesWithLoadingDocks())
                    {
                        System.out.println(p);
                    }
                    System.out.println("\n");
                }
                case 2 ->
                {
                    for(Property p : agency.getPropertiesWithHighwayAccess())
                    {
                        System.out.println(p);
                    }
                    System.out.println("\n");
                }
                case 3 -> exit = true;
                default ->
                {
                    // Nothing to do, will return to main menu
                }
            }
        }
    }

    /*
     * Handles the retail queries.
     */
    private void handleRetailQueries()
    {
        boolean exit;
        exit = false;

        while(!exit)
        {
            final int choice;

            System.out.println(" ");
            System.out.println("Retail Queries");
            System.out.println("""
                                       1. By Square Footage
                                       2. By Customer Parking
                                       3. Back""");

            choice = scanner.nextInt();

            switch(choice)
            {
                case 1 ->
                {
                    final int squareFootage;

                    System.out.println("Enter the square footage:");
                    squareFootage = scanner.nextInt();
                    System.out.println(agency.getPropertiesSquareFootage(squareFootage));
                }
                case 2 ->
                {
                    for(Property p : agency.getPropertiesWithCustomerParking())
                    {
                        System.out.println(p);
                    }
                }
                case 3 -> exit = true;
                default ->
                {
                    // Nothing to do, will return to main menu
                }
            }
        }
    }

    /*
     * Handles the exit.
     */
    private boolean handleExit(final boolean exit)
    {
        System.out.println("Goodbye for now!");
        return !exit;
    }

    /**
     * Gets the agency.
     * @return the agency
     */
    public Agency getAgency()
    {
        return agency;
    }
}
