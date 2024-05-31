package main.java.property;

import main.java.address.Address;

/**
 * Represents a retail property.
 * Each retail property has a price, address, square footage, customer parking availability,
 * type and a unique property ID.
 *
 * @author Amir Roshan
 * @version 1.0
 */
public class Retail extends Property
{
    private static final int MIN_SQUARE_FOOTAGE = 0;

    private final int squareFootage;
    private final boolean customerParking;

    /**
     * Constructs a new Retail object with the specified parameters.
     *
     * @param priceInUsd the price of the retail property in USD
     * @param address the address of the retail property
     * @param squareFootage the square footage of the retail property
     * @param customerParking true if the retail property has customer parking, false otherwise
     * @param propertyId the unique ID of the retail property
     * @throws NullPointerException if address or propertyId is null
     */
    public Retail(final double priceInUsd,
                  final Address address,
                  final String type,
                  final String propertyId,
                  final int squareFootage,
                  final boolean customerParking)
    {
        super(priceInUsd, address, type, propertyId);

        if(squareFootage < MIN_SQUARE_FOOTAGE)
        {
            throw new IllegalArgumentException("Invalid square footage: " + squareFootage);
        }

        this.squareFootage = squareFootage;
        this.customerParking = customerParking;
    }

    /**
     * Returns the square footage of the retail property.
     *
     * @return the square footage of the retail property
     */
    public int getSquareFootage()
    {
        return squareFootage;
    }

    /**
     * Returns true if the retail property has customer parking, false otherwise.
     *
     * @return true if the retail property has customer parking, false otherwise
     */
    public boolean isCustomerParking()
    {
        return customerParking;
    }

    /**
     * Returns a string representation of the retail property.
     *
     * @return a string representation of the retail property
     */
    @Override
    public String toString()
    {
        return super.toString() + "Square Footage: " + squareFootage + "\n" +
               "Customer Parking: " + customerParking;
    }
}
