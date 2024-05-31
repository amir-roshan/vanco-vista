package main.java;

import main.java.address.Address;
import main.java.property.Commercial;
import main.java.property.Property;
import main.java.property.Residence;
import main.java.property.Retail;

import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Represents an agency which manages real estate properties. This class allows for adding, removing,
 * and querying properties based on various criteria such as price range, presence of swimming pools,
 * number of bedrooms, and location. It also provides functionality to calculate the total value of
 * all properties and to list properties of a specific type with detailed information.
 *
 * @author Amir Roshan
 * @version 1.0
 */
public class Agency
{
    private static final int MIN_CHARS_LENGTH = 1;
    private static final int MAX_CHARS_LENGTH = 30;

    private final String name;
    private final Map<String, Property> properties;

    /**
     * Constructs a new Agency instance with a specified name.
     * Initializes an empty collection to hold Property objects managed by the agency.
     *
     * @param name The name of the agency.
     */
    public Agency(final String name)
    {
        if(name.length() < MIN_CHARS_LENGTH || name.length() > MAX_CHARS_LENGTH)
        {
            throw new IllegalArgumentException("Invalid name");
        }

        this.name = name;
        properties = new HashMap<>();
    }

    /**
     * Adds a new Property to the agency's collection if the property is not null.
     * The property is identified by its unique ID within the collection.
     *
     * @param property The Property object to be added.
     */
    public void addProperty(final Property property)
    {
        if(property != null && property.getPropertyId() != null)
        {
            properties.put(property.getPropertyId(), property);
        }
    }

    /**
     * Removes a Property from the agency's collection based on its unique ID.
     * If the property with the specified ID does not exist, no action is taken.
     *
     * @param propertyId The unique ID of the Property to be removed.
     */
    public void removeProperty(final String propertyId)
    {
        properties.remove(propertyId);
    }

    /**
     * Retrieves a Property from the agency's collection by its unique ID.
     * If no Property with the specified ID exists, null is returned.
     * @param propertyId The unique ID of the Property to retrieve.
     * @return The Property object with the specified ID, or null if not found.
     */
    public Property getProperty(final String propertyId)
    {
        final Set<String> keys;
        keys = properties.keySet();

        for(final String key : keys)
        {
            if(propertyId.equals(key))
            {
                return properties.get(key);
            }
        }

        return null;
    }

    /**
     * Calculates the total value of all properties managed by the agency in USD.
     *
     * @return The total value of all properties in USD as an integer.
     */
    public int getTotalPropertyValues()
    {
        int totalPriceInUsd;
        final Set<String> keys;

        totalPriceInUsd = 0;
        keys = properties.keySet();

        for(final String key : keys)
        {
            totalPriceInUsd += properties.get(key).getPriceUsd();
        }

        return totalPriceInUsd;
    }

    /**
     * Finds all properties within the agency's collection that have a swimming pool.
     *
     * @return A List of Property objects that have a swimming pool.
     */
    public ArrayList<Residence> getPropertiesWithPools()
    {
        final ArrayList<Residence> propertiesWithPools;
        final Set<String> keys;

        propertiesWithPools = new ArrayList<>();
        keys = properties.keySet();

        for(final String key : keys)
        {
            if(properties.get(key).getType().equalsIgnoreCase("residence"))
            {
                final Residence residence;
                residence = (Residence) properties.get(key);
                if(residence.hasSwimmingPool())
                {
                    propertiesWithPools.add(residence);
                }
            }
        }

        return propertiesWithPools;
    }

    /**
     * Retrieves properties whose price falls within a specified range, inclusive.
     *
     * @param minUsd The minimum price (inclusive) in USD.
     * @param maxUsd The maximum price (inclusive) in USD.
     * @return An array of Property objects within the specified price range, or null if none found.
     */
    public Property[] getPropertiesBetween(final double minUsd, final double maxUsd)
    {
        final List<Property> matchedProperties;
        final Set<String> keys;

        matchedProperties = new ArrayList<>();
        keys = properties.keySet();

        for(final String key : keys)
        {
            final double price = properties.get(key).getPriceUsd();
            if(price >= minUsd && price <= maxUsd)
            {
                matchedProperties.add(properties.get(key));
            }
        }

        if(matchedProperties.isEmpty())
        {
            return null;
        }
        else
        {
            final Property[] result;
            result = new Property[matchedProperties.size()];

            for(int i = 0; i < matchedProperties.size(); i++)
            {
                result[i] = matchedProperties.get(i);
            }

            return result;
        }
    }

    /**
     * Finds properties located on a specific street.
     *
     * @param streetName The name of the street.
     * @return A List of Address objects for properties on the specified street, or null if none found.
     */
    public ArrayList<Address> getPropertiesOn(final String streetName)
    {
        final ArrayList<Address> propertiesAddress;
        final Set<String> keys;

        propertiesAddress = new ArrayList<>();
        keys = properties.keySet();

        for(final String key : keys)
        {
            if(properties.get(key).getAddress().streetName().equals(streetName))
            {
                propertiesAddress.add(properties.get(key).getAddress());
            }
        }
        if(propertiesAddress.size() == 0)
        {
            return null;
        }
        return propertiesAddress;
    }

    /**
     * Retrieves properties with a number of bedrooms within a specified range, inclusive.
     *
     * @param minBedrooms The minimum number of bedrooms (inclusive).
     * @param maxBedrooms The maximum number of bedrooms (inclusive).
     * @return A HashMap where keys are property IDs and values are Property objects meeting the bedroom criteria, or null if none found.
     */
    public HashMap<String, Residence> getPropertiesWithBedrooms(final int minBedrooms,
                                                                final int maxBedrooms)
    {
        final HashMap<String, Residence> propertiesWithBedrooms;
        final Set<String> keys;

        propertiesWithBedrooms = new HashMap<>();
        keys = properties.keySet();

        for(final String key : keys)
        {
            if(properties.get(key).getType().equalsIgnoreCase("residence"))
            {
                final Residence residence;
                residence = (Residence) properties.get(key);
                if(residence.getNumberOfBedrooms() >= minBedrooms &&
                   residence.getNumberOfBedrooms() <= maxBedrooms)
                {
                    propertiesWithBedrooms.put(key, residence);
                }
            }
        }

        if(propertiesWithBedrooms.size() == 0)
        {
            return null;
        }

        return propertiesWithBedrooms;
    }

    /**
     * Generates a formatted list of properties of a specific type, including detailed information such as address, price, and amenities.
     *
     * @param propertyType The type of property to search.
     * @return An ArrayList of formatted properties representing each property of the specified type, or a message indicating none were found.
     */
    public ArrayList<Property> getPropertiesOfType(final String propertyType)
    {
        final ArrayList<Property> propertiesOfTypeList;
        propertiesOfTypeList = new ArrayList<>();

        for(final Property property : properties.values())
        {
            if(property.getType().equalsIgnoreCase(propertyType))
            {
                propertiesOfTypeList.add(property);
            }
        }

        return propertiesOfTypeList;
    }

    /**
     * Converts a string to title case, where the first letter of each word is capitalized and the rest are lower case.
     *
     * @param name The string to be converted to title case.
     * @return The title-cased version of the input string.
     */
    public String titleCase(final String name)
    {
        final String[] words;
        final StringBuilder result;

        words = name.split(" ");
        result = new StringBuilder();
        for(final String word : words)
        {
            if(!word.isEmpty())
            {
                String firstLetter = word.substring(0, 1).toUpperCase();
                String restOfWord = word.substring(1).toLowerCase();
                result.append(firstLetter).append(restOfWord).append(" ");
            }
        }
        final String titleCase;
        titleCase = result.toString().trim();

        return titleCase;
    }

    /**
     * Retrieves all commercial properties with a loading dock.
     *
     * @return An ArrayList of Commercial objects with a loading dock.
     */
    public ArrayList<Commercial> getPropertiesWithLoadingDocks()
    {
        final ArrayList<Commercial> propertiesWithLoadingDock;
        final Set<String> keys;

        propertiesWithLoadingDock = new ArrayList<>();
        keys = properties.keySet();

        for(final String key : keys)
        {
            if(properties.get(key).getType().equalsIgnoreCase("commercial"))
            {
                final Commercial commercial;
                commercial = (Commercial) properties.get(key);
                if(commercial.hasLoadingDock())
                {
                    propertiesWithLoadingDock.add(commercial);
                }
            }
        }

        return propertiesWithLoadingDock;
    }

    /**
     * Retrieves all commercial properties with highway access.
     *
     * @return An ArrayList of Commercial objects with highway access.
     */
    public ArrayList<Commercial> getPropertiesWithHighwayAccess()
    {
        final ArrayList<Commercial> propertiesWithHighwayAccess;
        final Set<String> keys;

        propertiesWithHighwayAccess = new ArrayList<>();
        keys = properties.keySet();

        for(final String key : keys)
        {
            if(properties.get(key).getType().equalsIgnoreCase("commercial"))
            {
                final Commercial commercial;
                commercial = (Commercial) properties.get(key);
                if(commercial.hasHighwayAccess())
                {
                    propertiesWithHighwayAccess.add(commercial);
                }
            }
        }

        return propertiesWithHighwayAccess;
    }

    /**
     * Retrieves all retail properties with a specified square footage.
     *
     * @param squareFootage The square footage of the retail properties to search for.
     * @return An ArrayList of Retail objects with the specified square footage.
     */
    public ArrayList<Retail> getPropertiesSquareFootage(final int squareFootage)
    {
        final ArrayList<Retail> propertiesWithSquareFootage;
        final Set<String> keys;

        propertiesWithSquareFootage = new ArrayList<>();
        keys = properties.keySet();

        for(final String key : keys)
        {
            if(properties.get(key).getType().equalsIgnoreCase("retail"))
            {
                final Retail retail;
                retail = (Retail) properties.get(key);
                if(retail.getSquareFootage() == squareFootage)
                {
                    propertiesWithSquareFootage.add(retail);
                }
            }
        }
        return propertiesWithSquareFootage;
    }

    /**
     * Retrieves all retail properties with customer parking.
     *
     * @return An ArrayList of Retail objects with customer parking.
     */
    public ArrayList<Retail> getPropertiesWithCustomerParking()
    {
        final ArrayList<Retail> propertiesWithCustomerParking;
        propertiesWithCustomerParking = new ArrayList<>();

        final Set<String> keys;
        keys = properties.keySet();

        for(final String key : keys)
        {
            if(properties.get(key).getType().equalsIgnoreCase("retail"))
            {
                final Retail retail;
                if(properties instanceof Retail)
                {
                    retail = (Retail) properties.get(key);
                    if(retail.isCustomerParking())
                    {
                        propertiesWithCustomerParking.add(retail);
                    }
                }
            }
        }
        return propertiesWithCustomerParking;
    }

    /**
     * Retrieves all properties that are strata's.
     *
     * @return An ArrayList of Residence objects that are strata's.
     */
    public ArrayList<Residence> getPropertiesWithStrata()
    {
        final ArrayList<Residence> propertiesWithStrata;
        final Set<String> keys;

        propertiesWithStrata = new ArrayList<>();
        keys = properties.keySet();

        for(final String key : keys)
        {
            if(properties.get(key).getType().equalsIgnoreCase("residence"))
            {
                final Residence residence;
                residence = (Residence) properties.get(key);
                if(residence.hasStrata())
                {
                    propertiesWithStrata.add(residence);
                }
            }
        }
        return propertiesWithStrata;
    }

    /**
     * Gets the name of the agency.
     *
     * @return The name of the agency as a String.
     */
    public String getName()
    {
        return name;
    }
}
