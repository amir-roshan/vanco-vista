package main.java.property;

import main.java.address.Address;

/**
 * Represents a residence property.
 *
 * @author Amir Roshan
 * @version 1.0
 */
public class Residence extends Property
{
    private static final int MIN_BEDROOMS = 1;
    private static final int MAX_BEDROOMS = 20;

    private final int numberOfBedrooms;
    private final boolean swimmingPool;
    private final boolean strata;

    /**
     * Constructs a new Residence object with the specified parameters.
     *
     * @param priceInUsd the price of the residence in USD
     * @param address the address of the residence
     * @param numberOfBedrooms the number of bedrooms in the residence
     * @param swimmingPool true if the residence has a swimming pool, false otherwise
     * @param propertyId the unique ID of the residence
     * @param strata true if the residence is a strata, false otherwise
     * @throws NullPointerException if address or propertyId is null
     */
    public Residence(final double priceInUsd,
                     final Address address,
                     final int numberOfBedrooms,
                     final boolean swimmingPool,
                     final String type,
                     final String propertyId,
                     final boolean strata)
    {
        super(priceInUsd, address, type, propertyId);

        // Validate number of bedrooms
        if(numberOfBedrooms < MIN_BEDROOMS || numberOfBedrooms > MAX_BEDROOMS)
        {
            throw new IllegalArgumentException("Invalid number of bedrooms: " + numberOfBedrooms);
        }

        this.numberOfBedrooms = numberOfBedrooms;
        this.swimmingPool = swimmingPool;
        this.strata = strata;
    }

    /**
     * Returns a string representation of the residence.
     *
     * @return a string representation of the residence
     */
    @Override
    public String toString()
    {
        return super.toString() + "Number of bedrooms: " + numberOfBedrooms + "\n" +
               "Swimming pool: " + (swimmingPool ? "Yes" : "No") + "\n" +
               "Strata: " + (strata ? "Strata: Yes" : "Strata: No");
    }


    /**
     * Returns true if the residence has a swimming pool, false otherwise.
     *
     * @return true if the residence has a swimming pool, false otherwise
     */
    public boolean hasSwimmingPool()
    {
        return swimmingPool;
    }

    /**
     * Returns true if the residence is a strata, false otherwise.
     *
     * @return true if the residence is a strata, false otherwise
     */
    public boolean hasStrata()
    {
        return strata;
    }


    /**
     * Returns the number of bedrooms in the residence.
     *
     * @return the number of bedrooms in the residence
     */
    public int getNumberOfBedrooms()
    {
        return numberOfBedrooms;
    }
}
